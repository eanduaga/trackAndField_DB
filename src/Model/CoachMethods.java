/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eider
 */
public class CoachMethods
{
    public static ArrayList <Coach> fillTableCoach() throws IOException
    {
        // Define the variables
        ArrayList <Coach> alCh = new ArrayList();
        String sqlQuery;
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT chPerID, perName, perSurname, perEmail, perPhoneNum FROM person JOIN coach ON perID = chPerID ORDER BY perName";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Coach ch = new Coach(false);
                ch.setID(rs.getString("chPerID"));
                ch.setName(rs.getString("perName"));
                ch.setSurname(rs.getString("perSurname"));
                ch.setEmail(rs.getString("perEmail"));
                ch.setPhoneNum(rs.getInt("perPhoneNum"));
                alCh.add(ch);
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            try { rs.close(); } catch (SQLException e) { /* ignored */ }
            try { stmt.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }

        return alCh;
    }
    
    public static void insertCoach(Coach ch) throws IOException
    {
        // Define the variables
        ArrayList <Coach> alCh = new ArrayList();
        Coach cch = new Coach(false);
        int i;
        boolean result = false;
        DBConnection cnt = new DBConnection();
        String sqlQuery, chTmCode;
        ResultSet rs;
        PreparedStatement ps = null;
        
        try
        {
            alCh = fillTableCoach();
            for(i = 0; i <alCh.size(); ++i)
            {
                cch = alCh.get(i);
                if(ch.getID().equals(cch.getID()))
                {
                    result = true;
                }
            }

            if(result == false)
            {
                sqlQuery = "SELECT tmCode FROM team WHERE tmName = ?";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, ch.getTeam());
                rs = ps.executeQuery();
                rs.next();
                chTmCode = rs.getString("tmCode");
                
                sqlQuery = "INSERT INTO person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, ch.getID());
                ps.setString(2, ch.getName());
                ps.setString(3, ch.getSurname());
                ps.setString(4, ch.getCountry());
                ps.setString(5, ch.getHomeTown());
                ps.setString(6, ch.getAddress());
                ps.setString(7, ch.getNationality());
                java.util.Date chBirthDate = Date.from(ch.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlChBirthDate = new java.sql.Date(chBirthDate.getTime());
                ps.setDate(8, sqlChBirthDate);
                ps.setString(9, ch.getEmail());
                ps.setInt(10, ch.getPhoneNum());
                ps.setString(11, chTmCode);
                ps.executeUpdate();
                
                sqlQuery = "INSERT INTO coach VALUES (?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, ch.getID());
                ps.setString(2, String.valueOf(ch.getStartYear()));
                ps.executeUpdate();
            }
            else
            {
                updateCoach(ch);
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(ps != null)
            {
                try { ps.close(); } catch (SQLException e) { /* ignored */ }
            } 
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static void deleteCoach(String tableChID) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "DELETE FROM coach WHERE chPerID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableChID);
            ps.executeUpdate();
            
            sqlQuery = "DELETE FROM person WHERE perID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableChID);
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static Coach showUpdateViewCoach(String tableChID) throws IOException
    {
        // Define the variables
        Coach ch = new Coach(false);
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT * FROM person WHERE perID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableChID);
            rs = ps.executeQuery();
            rs.next();
            ch.setID(rs.getString("perID"));
            ch.setName(rs.getString("perName"));
            ch.setSurname(rs.getString("perSurname"));
            ch.setCountry(rs.getString("perCountry"));
            ch.setHomeTown(rs.getString("perHomeTown"));
            ch.setAddress(rs.getString("perAddress"));
            ch.setNationality(rs.getString("perNationality"));
            java.sql.Date sqlChBirthDate = rs.getDate("perBirthDate");
            java.util.Date chBirthDate = new java.util.Date(sqlChBirthDate.getTime());
            ch.setBirthDate(chBirthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            ch.setEmail(rs.getString("perEmail"));
            ch.setPhoneNum(rs.getInt("perPhoneNum"));
            ch.setTeam(rs.getString("perTeam"));
            
            sqlQuery = "SELECT * FROM coach WHERE chPerID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableChID);
            rs = ps.executeQuery();
            rs.next();
            ch.setStartYear(Year.parse(rs.getString("chStartYear"), DateTimeFormatter.ISO_DATE));
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            try { rs.close(); } catch (SQLException e) { /* ignored */ }
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
        
        return ch;
    }
    
    public static void updateCoach(Coach ch) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, chTmCode = null;
        ResultSet rs;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT tmCode FROM team WHERE tmName = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, ch.getTeam());
            rs = ps.executeQuery();
            rs.next();
            chTmCode = rs.getString("tmCode");
            
            sqlQuery = "UPDATE person SET perCountry = ?, perAddress = ?, perNationality = ?, perEmail = ?, perPhoneNum = ?, perTeam = ? WHERE perID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, ch.getCountry());
            ps.setString(2, ch.getAddress());
            ps.setString(3, ch.getNationality());
            ps.setString(4, ch.getEmail());
            ps.setInt(5, ch.getPhoneNum());
            ps.setString(6, chTmCode);
            ps.setString(7, ch.getID());
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static ArrayList <Coach> searchCoach(String search) throws IOException
    {
        // Define the variables
        ArrayList <Coach> alCh = new ArrayList();
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT chPerID, perName, perSurname, perEmail, perPhoneNum FROM person JOIN coach ON perID = chPerID "
                    + "WHERE (perName LIKE '%" + search + "%') OR (perSurname LIKE '%" + search + "%') ORDER BY perName";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Coach ch = new Coach(false);
                ch.setID(rs.getString("athPerID"));
                ch.setName(rs.getString("perName"));
                ch.setSurname(rs.getString("perSurname"));
                ch.setEmail(rs.getString("perEmail"));
                ch.setPhoneNum(rs.getInt("perPhoneNum"));
                alCh.add(ch);
            }
        }
        catch(SQLException ex)
        {
            // JOptionPane.showMessageDialog(null, "Error, no se conecto");
        }
        
        finally
        {
            try { rs.close(); } catch (SQLException e) { /* ignored */ }
            try { stmt.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }

        return alCh;
    }
    
    public static String getTeamNameCh(String chTmCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, chTmName = null;
        ResultSet rs;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT tmName FROM team WHERE tmCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, chTmCode);
            rs = ps.executeQuery();
            rs.next();
            chTmName = rs.getString("tmName");
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(ps != null)
            {
                try { ps.close(); } catch (SQLException e) { /* ignored */ }
            } 
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
        
        return chTmName;
    }
}
