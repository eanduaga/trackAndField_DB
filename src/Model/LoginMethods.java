/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eider
 */
public class LoginMethods
{
    public static boolean createUser(String username, String password, String fullName, String email)
    {
        // Define the variables
        ArrayList <String> lgUser = new ArrayList();
        boolean result = false;
        int i;
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {            
            lgUser = checkUsername();
                
            for(i = 0; i < lgUser.size(); ++i)
            {
                String dbUser = lgUser.get(i);
                if(username.equals(dbUser))
                {
                    result = true;
                }
            }
            
            if(result == false)
            {
                sqlQuery = "INSERT INTO users VALUES (?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, fullName);
                ps.setString(4, email);
                ps.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoginMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(ps != null)
            {
                try { ps.close(); } catch (SQLException e) { /* ignored */ }
            } 
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
        
        return result;
    }
    
    public static int readUser(String username, String password)
    {
        // Define the variables
        String sqlQuery;
        int result = 0;
        DBConnection cnt = new DBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {            
            sqlQuery = "SELECT userValidation (?, ?)";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            result = rs.getInt(1);
        }
        catch(SQLException ex)
        {
            Logger.getLogger(LoginMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(ps != null)
            {
                try { ps.close(); } catch (SQLException e) { /* ignored */ }
            } 
            try { rs.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
        
        return result;
    }
    
    public static ArrayList <String> checkUsername()
    {
        // Define the variables
        ArrayList <String> lgUser = new ArrayList();
        String sqlQuery;
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT username FROM users";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                String dbUsername;
                dbUsername = (rs.getString("username"));
                lgUser.add(dbUsername);
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(LoginMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(stmt != null)
            {
                try { stmt.close(); } catch (SQLException e) { /* ignored */ }
            } 
            try { rs.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
        
        return lgUser;
    }
    
    public static String[] passwordRecovery(String emailAcc)
    {
        // Define the variables
        ArrayList <String> lgUser = new ArrayList();
        String sqlQuery;
        String[] result = new String[4];
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT * FROM users";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                String dbUsername = (rs.getString("username"));
                String dbPasswd = (rs.getString("password"));
                String dbFullName = (rs.getString("fullName"));
                String dbEmail = (rs.getString("emailAcc"));
                
                if(emailAcc.equals(dbEmail))
                {
                    result[0] = dbUsername;
                    result[1] = dbPasswd;
                    result[2] = dbFullName;
                    result[3] = dbEmail;
                }
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(LoginMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(stmt != null)
            {
                try { stmt.close(); } catch (SQLException e) { /* ignored */ }
            } 
            try { rs.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
        
        return result;
    }
}
