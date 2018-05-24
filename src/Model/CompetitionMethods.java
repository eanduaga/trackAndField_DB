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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eider
 */
public class CompetitionMethods
{
    public static ArrayList <Competition> fillTableCompetition() throws IOException
    {
        // Define the variables
        ArrayList <Competition> alComp = new ArrayList();
        String sqlQuery;
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT * FROM competition";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Competition comp = new Competition(false);
                comp.setCode(rs.getString("compCode"));
                comp.setName(rs.getString("compName"));
                comp.setDescription(rs.getString("compDescription"));
                comp.setLocation(rs.getString("compLocation"));
                java.sql.Date sqlCompStartDate = rs.getDate("compStartDate");
                java.util.Date compStartDate = new java.util.Date(sqlCompStartDate.getTime());
                comp.setStartDate(compStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                java.sql.Date sqlCompEndDate = rs.getDate("compStartDate");
                java.util.Date compEndDate = new java.util.Date(sqlCompEndDate.getTime());
                comp.setEndDate(compEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                alComp.add(comp);
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

        return alComp;
    }
    
    public static void insertCompetition(Competition comp) throws IOException
    {
        // Define the variables
        ArrayList <Competition> alComp = new ArrayList();
        Competition cmpt = new Competition(false);
        int i;
        boolean result = false;
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            alComp = fillTableCompetition();
            for(i = 0; i <alComp.size(); ++i)
            {
                cmpt = alComp.get(i);
                if(comp.getCode().equals(cmpt.getCode()))
                {
                    result = true;
                }
            }

            if(result == false)
            {
                sqlQuery = "INSERT INTO competition VALUES (?, ?, ?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, comp.getCode());
                ps.setString(2, comp.getName());
                ps.setString(3, comp.getDescription());
                ps.setString(4, comp.getLocation());
                java.util.Date compStartDate = Date.from(comp.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlCompStartDate = new java.sql.Date(compStartDate.getTime());
                ps.setDate(5, sqlCompStartDate);
                java.util.Date compEndDate = Date.from(comp.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlCompEndDate = new java.sql.Date(compEndDate.getTime());
                ps.setDate(6, sqlCompEndDate);
                ps.executeUpdate();
            }
            else
            {
                updateCompetition(comp);
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
    
    public static void deleteCompetition(String tableCompCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "DELETE FROM competition WHERE compCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableCompCode);
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            // MySQLIntegrityConstraintViolationException
            JOptionPane.showMessageDialog(null, "The information of the competition you are trying to remove is on the sections result, schedule or "
                    + "registration. Please, check those records before deleting the competition.", "Dependency error", JOptionPane.ERROR_MESSAGE);
        }
        
        finally
        {
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static Competition showUpdateViewCompetition(String tableCompCode) throws IOException
    {
        // Define the variables
        Competition comp = new Competition(false);
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT * FROM competition WHERE compCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableCompCode);
            rs = ps.executeQuery();
            rs.next();
            comp.setCode(rs.getString("compCode"));
            comp.setName(rs.getString("compName"));
            comp.setDescription(rs.getString("compDescription"));
            comp.setLocation(rs.getString("compLocation"));
            java.sql.Date sqlCompStartDate = rs.getDate("compStartDate");
            java.util.Date compStartDate = new java.util.Date(sqlCompStartDate.getTime());
            comp.setStartDate(compStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            java.sql.Date sqlCompEndDate = rs.getDate("compStartDate");
            java.util.Date compEndDate = new java.util.Date(sqlCompEndDate.getTime());
            comp.setEndDate(compEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
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
        
        return comp;
    }
    
    public static void updateCompetition(Competition comp) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "UPDATE competition SET compName = ?, compDescription = ?, compLocation = ?, compStartDate = ?, compEndDate = ? WHERE compCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, comp.getName());
            ps.setString(2, comp.getDescription());
            ps.setString(3, comp.getLocation());
            java.util.Date compStartDate = Date.from(comp.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlcompStartDate = new java.sql.Date(compStartDate.getTime());
            ps.setDate(4, sqlcompStartDate);
            java.util.Date compEndDate = Date.from(comp.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlcompEndDate = new java.sql.Date(compEndDate.getTime());
            ps.setDate(5, sqlcompEndDate);
            ps.setString(6, comp.getCode());
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
    
    public static ArrayList <Competition> searchCompetition(String search) throws IOException
    {
        // Define the variables
        ArrayList <Competition> alComp = new ArrayList();
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT * FROM competition "
                    + "WHERE (compName LIKE '%" + search + "%') OR (compDescription LIKE '%" + search + "%') OR (compLocation LIKE '%" + search + "%') "
                    + "ORDER BY compStartDate, compEndDate";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Competition comp = new Competition(false);
                comp.setCode(rs.getString("compCode"));
                comp.setName(rs.getString("compName"));
                comp.setDescription(rs.getString("compDescription"));
                comp.setLocation(rs.getString("compLocation"));
                java.sql.Date sqlCompStartDate = rs.getDate("compStartDate");
                java.util.Date compStartDate = new java.util.Date(sqlCompStartDate.getTime());
                comp.setStartDate(compStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                java.sql.Date sqlCompEndDate = rs.getDate("compStartDate");
                java.util.Date compEndDate = new java.util.Date(sqlCompEndDate.getTime());
                comp.setEndDate(compEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                alComp.add(comp);
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

        return alComp;
    }
}
