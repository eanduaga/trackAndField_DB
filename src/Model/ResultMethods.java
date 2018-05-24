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
public class ResultMethods
{
    public static ArrayList <Result> fillTableResult() throws IOException
    {
        // Define the variables
        ArrayList <Result> alRs = new ArrayList();
        String sqlQuery;
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rss = null;
        
        try
        {
            // Get the information from the result table (DB)
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT rsCode, rsGender, rsRound, rsTime, rsPosition, rsDate, disName, compName, CONCAT(perName, ' ', perSurname) AS athName "
                    + "FROM (((discipline dis JOIN result rs ON dis.disCode = rs.rsDiscipline) JOIN competition comp ON rs.rsCompetition = comp.compCode) "
                    + "JOIN athlete ath ON rs.rsAthlete = ath.athPerID) JOIN person per ON ath.athPerID = per.perID "
                    + "ORDER BY rsDate";
            rss = stmt.executeQuery(sqlQuery);
            
            while(rss.next())
            {        
                Result rs = new Result(false);
                rs.setCode(rss.getString("rsCode"));
                rs.setGender(rss.getString("rsGender"));
                rs.setRound(rss.getString("rsRound"));
                rs.setTime(rss.getFloat("rsTime"));
                rs.setPosition(rss.getInt("rsPosition"));
                java.sql.Date sqlRsDate = rss.getDate("rsDate");
                java.util.Date rsDate = new java.util.Date(sqlRsDate.getTime());
                rs.setDate(rsDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                rs.setDiscipline(rss.getString("disName"));
                rs.setCompetition(rss.getString("compName"));
                rs.setAthlete(rss.getString("athName"));
                alRs.add(rs);
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

        return alRs;
    }
    
    public static void insertResult(Result rs) throws IOException
    {
        // Define the variables
        ArrayList <Result> alRs = new ArrayList();
        Result rslt = new Result(false);
        int i;
        boolean result = false;
        DBConnection cnt = new DBConnection();
        String sqlQuery, rsAthID, rsCompCode, rsDisCode;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            alRs = fillTableResult();
            for(i = 0; i <alRs.size(); ++i)
            {
                rslt = alRs.get(i);
                if(rs.getCode().equals(rslt.getCode()))
                {
                    result = true;
                }
            }

            if(result == false)
            {
                // Get the ID of the athlete using the name
                sqlQuery = "SELECT perId FROM person WHERE CONCAT(perName, ' ', perSurname) = ?";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, rs.getAthlete());
                rss = ps.executeQuery();
                rss.next();
                rsAthID = rss.getString("perId");
                
                // Get the code of the competition using the name
                sqlQuery = "SELECT compCode FROM competition WHERE compName = ?";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, rs.getCompetition());
                rss = ps.executeQuery();
                rss.next();
                rsCompCode = rss.getString("compCode");
                
                // Get the code of the discipline using the name
                sqlQuery = "SELECT disCode FROM discipline WHERE disName = ?";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, rs.getDiscipline());
                rss = ps.executeQuery();
                rss.next();
                rsDisCode = rss.getString("disCode");
                
                // Insert the information into the database
                sqlQuery = "INSERT INTO result VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, rs.getCode());
                ps.setString(2, rs.getGender());
                ps.setString(3, rs.getRound());
                ps.setFloat(4, rs.getTime());
                ps.setInt(5, rs.getPosition());
                java.util.Date rsDate = Date.from(rs.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlRsDate = new java.sql.Date(rsDate.getTime());
                ps.setDate(6, sqlRsDate);
                ps.setString(7, rsDisCode);
                ps.setString(8, rsCompCode);
                ps.setString(9, rsAthID);
                ps.executeUpdate();
            }
            else
            {
                updateResult(rs);
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
    
    public static void deleteResult(String tableRsCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "DELETE FROM result WHERE rsCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableRsCode);
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
    
    public static Result showUpdateViewResult(String tableRsCode) throws IOException
    {
        // Define the variables
        Result rs = new Result(false);
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT * FROM result WHERE rsCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableRsCode);
            rss = ps.executeQuery();
            rss.next();
            rs.setCode(rss.getString("rsCode"));
            rs.setGender(rss.getString("rsGender"));
            rs.setRound(rss.getString("rsRound"));
            rs.setTime(rss.getFloat("rsTime"));
            rs.setPosition(rss.getInt("rsPosition"));
            java.sql.Date sqlRsDate = rss.getDate("rsDate");
            java.util.Date rsDate = new java.util.Date(sqlRsDate.getTime());
            rs.setDate(rsDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            rs.setDiscipline(rss.getString("rsDiscipline"));
            rs.setCompetition(rss.getString("rsCompetition"));
            rs.setAthlete(rss.getString("rsAthlete"));
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
        
        return rs;
    }
    
    public static void updateResult(Result rs) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, rsAthID = null, rsCompCode, rsDisCode;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            // Get the code of the athlete
            sqlQuery = "SELECT perID FROM person WHERE CONCAT(perName, ' ', perSurname) = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, rs.getAthlete());
            rss = ps.executeQuery();
            rss.next();
            rsAthID = rss.getString("perID");
            
            // Get the code of the competition
            sqlQuery = "SELECT compCode FROM competition WHERE compName = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, rs.getCompetition());
            rss = ps.executeQuery();
            rss.next();
            rsCompCode = rss.getString("compCode");
            
            // Get the code of the discipline
            sqlQuery = "SELECT disCode FROM discipline WHERE disName = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, rs.getDiscipline());
            rss = ps.executeQuery();
            rss.next();
            rsDisCode = rss.getString("disCode");
            
            // Change the information in the database
            sqlQuery = "UPDATE result SET rsGender = ?, rsRound = ?, rsTime = ?, rsPosition = ?, rsDate = ?, rsDiscipline = ?, rsCompetition = ?, rsAthlete = ? WHERE rsCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, rs.getGender());
            ps.setString(2, rs.getRound());
            ps.setFloat(3, rs.getTime());
            ps.setInt(4, rs.getPosition());
            java.util.Date rsDate = Date.from(rs.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlRsDate = new java.sql.Date(rsDate.getTime());
            ps.setDate(5, sqlRsDate);
            ps.setString(6, rsDisCode);
            ps.setString(7, rsCompCode);
            ps.setString(8, rsAthID);
            ps.setString(9, rs.getCode());
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
    
    public static ArrayList <Result> searchResult(String search) throws IOException
    {
        // Define the variables
        ArrayList <Result> alRs = new ArrayList();
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        Statement stmt = null;
        ResultSet rss = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT rsCode, rsGender, rsRound, rsTime, rsPosition, rsDate, disName, compName, CONCAT(perName, ' ', perSurname) AS athName "
                    + "FROM (((discipline dis JOIN result rs ON dis.disCode = rs.rsDiscipline) JOIN competition comp ON rs.rsCompetition = comp.compCode) "
                    + "JOIN athlete ath ON rs.rsAthlete = ath.athPerID) JOIN person per ON ath.athPerID = per.perID "
                    + "HAVING (rsDate LIKE '%" + search + "%') OR (athName LIKE '%" + search + "%') OR (disName LIKE '%" + search + "%') "
                    + "OR (compName LIKE '%" + search + "%') OR (rsRound LIKE '%" + search + "%') ORDER BY rsDate";
            rss = stmt.executeQuery(sqlQuery);
            
            while(rss.next())
            {
                Result rs = new Result(false);
                rs.setCode(rss.getString("rsCode"));
                rs.setGender(rss.getString("rsGender"));
                rs.setRound(rss.getString("rsRound"));
                rs.setTime(rss.getFloat("rsTime"));
                rs.setPosition(rss.getInt("rsPosition"));
                java.sql.Date sqlRsDate = rss.getDate("rsDate");
                java.util.Date rsDate = new java.util.Date(sqlRsDate.getTime());
                rs.setDate(rsDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                rs.setDiscipline(rss.getString("disName"));
                rs.setCompetition(rss.getString("compName"));
                rs.setAthlete(rss.getString("athName"));
                alRs.add(rs);
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

        return alRs;
    }

    public static String getAthleteNameRs(String rsAthID) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, rsAthName = null;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT CONCAT(perName, ' ', perSurname) AS athName FROM person WHERE perID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, rsAthID);
            rss = ps.executeQuery();
            rss.next();
            rsAthName = rss.getString("athName");
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
        
        return rsAthName;
    }
    
    public static String getCompetitionNameRs(String rsCompCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, rsCompName = null;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT compName FROM competition WHERE compCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, rsCompCode);
            rss = ps.executeQuery();
            rss.next();
            rsCompName = rss.getString("compName");
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
        
        return rsCompName;
    }
    
    public static String getDisciplineNameRs(String rsDisCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, rsDisName = null;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT disName FROM discipline WHERE disCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, rsDisCode);
            rss = ps.executeQuery();
            rss.next();
            rsDisName = rss.getString("disName");
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
        
        return rsDisName;
    }
}
