/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
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
public class TeamMethods
{
    public static ArrayList <Team> fillTableTeam() throws IOException
    {
        // Define the variables
        ArrayList <Team> alTm = new ArrayList();
        String sqlQuery;
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT * FROM team";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Team tm = new Team(false);
                tm.setCode(rs.getString("tmCode"));
                tm.setName(rs.getString("tmName"));
                tm.setCountry(rs.getString("tmCountry"));
                tm.setTown(rs.getString("tmTown"));
                alTm.add(tm);
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

        return alTm;
    }
    
    public static void insertTeam(Team tm) throws IOException
    {
        // Define the variables
        ArrayList <Team> alTm = new ArrayList();
        Team team = new Team(false);
        int i;
        boolean result = false;
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            alTm = fillTableTeam();
            for(i = 0; i <alTm.size(); ++i)
            {
                team = alTm.get(i);
                if(tm.getCode().equals(team.getCode()))
                {
                    result = true;
                }
            }

            if(result == false)
            {
                sqlQuery = "INSERT INTO team VALUES (?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, tm.getCode());
                ps.setString(2, tm.getName());
                ps.setString(3, tm.getCountry());
                ps.setString(4, tm.getTown());
                ps.executeUpdate();
            }
            else
            {
                updateTeam(tm);
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
    
    public static void deleteTeam(String tableTmCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "DELETE FROM team WHERE tmCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableTmCode);
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            // MySQLIntegrityConstraintViolationException
            JOptionPane.showMessageDialog(null, "Some atheletes or coaches are part of the team you are trying to remove. "
                    + "First change the team of the corresponding athlete or coach, or just delete them.", "Dependency error", JOptionPane.ERROR_MESSAGE);
        }
        
        finally
        {
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static Team showUpdateViewTeam(String tableTmCode) throws IOException
    {
        // Define the variables
        Team tm = new Team(false);
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT * FROM team WHERE tmCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableTmCode);
            rs = ps.executeQuery();
            rs.next();
            tm.setCode(rs.getString("tmCode"));
            tm.setName(rs.getString("tmName"));
            tm.setCountry(rs.getString("tmCountry"));
            tm.setTown(rs.getString("tmTown"));
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
        
        return tm;
    }
    
    public static void updateTeam(Team tm) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "UPDATE team SET tmName = ?, tmCountry = ?, tmTown = ? WHERE tmCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tm.getName());
            ps.setString(2, tm.getCountry());
            ps.setString(3, tm.getTown());
            ps.setString(4, tm.getCode());
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
    
    public static ArrayList <Team> searchTeam(String search) throws IOException
    {
        // Define the variables
        ArrayList <Team> alTm = new ArrayList();
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT * FROM team WHERE tmName LIKE '%" + search + "%' GROUP BY tmName";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Team tm = new Team(false);
                tm.setCode(rs.getString("tmCode"));
                tm.setName(rs.getString("tmName"));
                tm.setCountry(rs.getString("tmCountry"));
                tm.setTown(rs.getString("tmTown"));
                alTm.add(tm);
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

        return alTm;
    }
}
