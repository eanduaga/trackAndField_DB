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

/**
 *
 * @author Eider
 */
public class ScheduleMethods
{
    public static ArrayList <Schedule> fillTableSchedule() throws IOException
    {
        // Define the variables
        ArrayList <Schedule> alSch = new ArrayList();
        String sqlQuery;
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rss = null;
        
        try
        {
            // Get the information from the result table (DB)
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT schCode, schDate, schRound, schGender, compName, disName "
                    + "FROM (discipline dis JOIN schedule sch ON dis.disCode = sch.schDiscipline) JOIN competition comp ON sch.schCompetition = comp.compCode "
                    + "ORDER BY schDate";
            rss = stmt.executeQuery(sqlQuery);
            
            while(rss.next())
            {        
                Schedule sch = new Schedule(false);
                sch.setCode(rss.getString("schCode"));
                java.sql.Date sqlRsDate = rss.getDate("schDate");
                java.util.Date rsDate = new java.util.Date(sqlRsDate.getTime());
                sch.setDate(rsDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                sch.setRound(rss.getString("schRound"));
                sch.setGender(rss.getString("schGender"));
                sch.setCompetition(rss.getString("compName"));
                sch.setDiscipline(rss.getString("disName"));
                alSch.add(sch);
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(rss != null)
            {
                try { rss.close(); } catch (SQLException e) { /* ignored */ }
            }
            try { stmt.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }

        return alSch;
    }
    
    public static void insertSchedule(Schedule sch) throws IOException
    {
        // Define the variables
        ArrayList <Schedule> alSch = new ArrayList();
        Schedule schdl = new Schedule(false);
        int i;
        boolean result = false;
        DBConnection cnt = new DBConnection();
        String sqlQuery, schCompCode, schDisCode;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            alSch = fillTableSchedule();
            for(i = 0; i <alSch.size(); ++i)
            {
                schdl = alSch.get(i);
                if(sch.getCode().equals(schdl.getCode()))
                {
                    result = true;
                }
            }

            if(result == false)
            {
                // Get the code of the competition using the name
                sqlQuery = "SELECT compCode FROM competition WHERE compName = ?";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, sch.getCompetition());
                rss = ps.executeQuery();
                rss.next();
                schCompCode = rss.getString("compCode");
                
                // Get the code of the discipline using the name
                sqlQuery = "SELECT disCode FROM discipline WHERE disName = ?";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, sch.getDiscipline());
                rss = ps.executeQuery();
                rss.next();
                schDisCode = rss.getString("disCode");
                
                // Insert the information into the database
                sqlQuery = "INSERT INTO schedule VALUES (?, ?, ?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, sch.getCode());
                java.util.Date schDate = Date.from(sch.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlSchDate = new java.sql.Date(schDate.getTime());
                ps.setDate(2, sqlSchDate);
                ps.setString(3, sch.getRound());
                ps.setString(4, sch.getGender());
                ps.setString(5, schCompCode);
                ps.setString(6, schDisCode);
                ps.executeUpdate();
            }
            else
            {
                updateSchedule(sch);
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(rss != null)
            {
                try { rss.close(); } catch (SQLException e) { /* ignored */ }
            }
            if(ps != null)
            {
                try { ps.close(); } catch (SQLException e) { /* ignored */ }
            }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static void deleteSchedule(String tableSchCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "DELETE FROM schedule WHERE schCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableSchCode);
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            // MySQLIntegrityConstraintViolationException
            //JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
        }
        
        finally
        {
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static Schedule showUpdateViewSchedule(String tableSchCode) throws IOException
    {
        // Define the variables
        Schedule sch = new Schedule(false);
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT * FROM schedule WHERE schCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableSchCode);
            rss = ps.executeQuery();
            rss.next();
            sch.setCode(rss.getString("rsCode"));
            java.sql.Date sqlRsDate = rss.getDate("rsDate");
            java.util.Date rsDate = new java.util.Date(sqlRsDate.getTime());
            sch.setDate(rsDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            sch.setRound(rss.getString("rsRound"));
            sch.setGender(rss.getString("rsGender"));
            sch.setCompetition(rss.getString("rsCompetition"));
            sch.setDiscipline(rss.getString("rsDiscipline"));
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(rss != null)
            {
                try { rss.close(); } catch (SQLException e) { /* ignored */ }
            }
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
        
        return sch;
    }
    
    public static void updateSchedule(Schedule sch) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, schCompCode, schDisCode;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            // Get the code of the competition
            sqlQuery = "SELECT compCode FROM competition WHERE compName = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, sch.getCompetition());
            rss = ps.executeQuery();
            rss.next();
            schCompCode = rss.getString("compCode");
            
            // Get the code of the discipline
            sqlQuery = "SELECT disCode FROM discipline WHERE disName = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, sch.getDiscipline());
            rss = ps.executeQuery();
            rss.next();
            schDisCode = rss.getString("disCode");
            
            // Change the information in the database
            sqlQuery = "UPDATE schedule SET schDate = ?, schRound = ?, schGender = ?, schCompetition = ?, schDiscipline = ? WHERE schCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            java.util.Date schDate = Date.from(sch.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlSchDate = new java.sql.Date(schDate.getTime());
            ps.setDate(1, sqlSchDate);
            ps.setString(2, sch.getRound());
            ps.setString(3, sch.getGender());
            ps.setString(4, schCompCode);
            ps.setString(5, schDisCode);
            ps.setString(6, sch.getCode());
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            if(rss != null)
            {
                try { rss.close(); } catch (SQLException e) { /* ignored */ }
            }
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static ArrayList <Schedule> searchSchedule(String search) throws IOException
    {
        // Define the variables
        ArrayList <Schedule> alSch = new ArrayList();
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        Statement stmt = null;
        ResultSet rss = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT schCode, schDate, schRound, schGender, compName, disName	"
                    + "FROM (discipline dis JOIN schedule sch ON dis.disCode = sch.schDiscipline) JOIN competition comp ON sch.schCompetition = comp.compCode "
                    + "WHERE (schDate LIKE '%" + search + "%') OR (compName LIKE '%" + search + "%') OR (disName LIKE '%" + search + "%') "
                    + "ORDER BY schDate";
            rss = stmt.executeQuery(sqlQuery);
            
            while(rss.next())
            {
                Schedule sch = new Schedule(false);
                sch.setCode(rss.getString("schCode"));
                java.sql.Date sqlSchDate = rss.getDate("schDate");
                java.util.Date schDate = new java.util.Date(sqlSchDate.getTime());
                sch.setDate(schDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                sch.setRound(rss.getString("schRound"));
                sch.setGender(rss.getString("schGender"));
                sch.setCompetition(rss.getString("compName"));
                sch.setDiscipline(rss.getString("disName"));
                alSch.add(sch);
            }
        }
        catch(SQLException ex)
        {
            // JOptionPane.showMessageDialog(null, "Error, no se conecto");
        }
        
        finally
        {
            if(rss != null)
            {
                try { rss.close(); } catch (SQLException e) { /* ignored */ }
            }
            try { stmt.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }

        return alSch;
    }
    
    public static String getCompetitionNameSch(String schCompCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, schCompName = null;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT compName FROM competition WHERE compCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, schCompCode);
            rss = ps.executeQuery();
            rss.next();
            schCompName = rss.getString("compName");
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
            if(rss != null)
            {
                try { rss.close(); } catch (SQLException e) { /* ignored */ }
            }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
        
        return schCompName;
    }
    
    public static String getDisciplineNameSch(String schDisCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, schDisName = null;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT disName FROM discipline WHERE disCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, schDisCode);
            rss = ps.executeQuery();
            rss.next();
            schDisName = rss.getString("disName");
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
            if(rss != null)
            {
                try { rss.close(); } catch (SQLException e) { /* ignored */ }
            }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
        
        return schDisName;
    }
}
