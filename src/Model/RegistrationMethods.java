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
public class RegistrationMethods
{
    public static ArrayList <Registration> fillTableRegistration() throws IOException
    {
        // Define the variables
        ArrayList <Registration> alReg = new ArrayList();
        String sqlQuery;
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rss = null;
        
        try
        {
            // Get the information from the result table (DB)
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT regCode, regDate, compName, CONCAT(perName, ' ', perSurname) AS athName "
                    + "FROM ((competition comp JOIN registration reg ON comp.compCode = reg.regCompetition) "
                    + "JOIN athlete ath ON reg.regAthlete = ath.athPerID) JOIN person per ON ath.athPerID = per.perID "
                    + "ORDER BY regDate";
            rss = stmt.executeQuery(sqlQuery);
            
            while(rss.next())
            {        
                Registration reg = new Registration(false);
                reg.setCode(rss.getString("regCode"));
                java.sql.Date sqlRegDate = rss.getDate("regDate");
                java.util.Date regDate = new java.util.Date(sqlRegDate.getTime());
                reg.setRegDate(regDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                reg.setCompetition(rss.getString("compName"));
                reg.setAthlete(rss.getString("athName"));
                alReg.add(reg);
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

        return alReg;
    }
    
    public static void insertRegistration(Registration reg) throws IOException
    {
        // Define the variables
        ArrayList <Registration> alReg = new ArrayList();
        Registration regtr = new Registration(false);
        int i;
        boolean result = false;
        DBConnection cnt = new DBConnection();
        String sqlQuery, regAthID, regCompCode;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            alReg = fillTableRegistration();
            for(i = 0; i <alReg.size(); ++i)
            {
                regtr = alReg.get(i);
                if(reg.getCode().equals(regtr.getCode()))
                {
                    result = true;
                }
            }

            if(result == false)
            {
                // Get the ID of the athlete using the name
                sqlQuery = "SELECT perId FROM person WHERE CONCAT(perName, ' ', perSurname) = ?";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, reg.getAthlete());
                rss = ps.executeQuery();
                rss.next();
                regAthID = rss.getString("perId");
                
                // Get the code of the competition using the name
                sqlQuery = "SELECT compCode FROM competition WHERE compName = ?";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, reg.getCompetition());
                rss = ps.executeQuery();
                rss.next();
                regCompCode = rss.getString("compCode");
                
                // Insert the information into the database
                sqlQuery = "INSERT INTO registration VALUES (?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, reg.getCode());
                java.util.Date rsDate = Date.from(reg.getRegDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlRsDate = new java.sql.Date(rsDate.getTime());
                ps.setDate(2, sqlRsDate);
                ps.setString(3, regCompCode);
                ps.setString(4, regAthID);
                ps.executeUpdate();
            }
            else
            {
                updateRegistration(reg);
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
    
    public static void deleteRegistration(String tableRegCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "DELETE FROM registration WHERE regCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableRegCode);
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
    
    public static Registration showUpdateViewRegistration(String tableRegCode) throws IOException
    {
        // Define the variables
        Registration reg = new Registration(false);
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT * FROM registration WHERE regCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableRegCode);
            rss = ps.executeQuery();
            rss.next();
            reg.setCode(rss.getString("regCode"));
            java.sql.Date sqlRsDate = rss.getDate("regDate");
            java.util.Date rsDate = new java.util.Date(sqlRsDate.getTime());
            reg.setRegDate(rsDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            reg.setCompetition(rss.getString("regCompetition"));
            reg.setAthlete(rss.getString("regAthlete"));
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
        
        return reg;
    }
    
    public static void updateRegistration(Registration reg) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, regAthID, regCompCode;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            // Get the code of the athlete
            sqlQuery = "SELECT perID FROM person WHERE CONCAT(perName, ' ', perSurname) = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, reg.getAthlete());
            rss = ps.executeQuery();
            rss.next();
            regAthID = rss.getString("perID");
            
            // Get the code of the competition
            sqlQuery = "SELECT compCode FROM competition WHERE compName = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, reg.getCompetition());
            rss = ps.executeQuery();
            rss.next();
            regCompCode = rss.getString("compCode");
            
            // Change the information in the database
            sqlQuery = "UPDATE registration SET regCompetition = ?, regAthlete = ? WHERE regCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, regCompCode);
            ps.setString(2, regAthID);
            ps.setString(3, reg.getCode());
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
    
    public static ArrayList <Registration> searchRegistration(String search) throws IOException
    {
        // Define the variables
        ArrayList <Registration> alReg = new ArrayList();
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        Statement stmt = null;
        ResultSet rss = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT regCode, regDate, compName, CONCAT(perName, ' ', perSurname) AS athName "
                    + "FROM ((competition comp JOIN registration reg ON comp.compCode = reg.regCompetition) JOIN athlete ath ON reg.regAthlete = ath.athPerID) "
                    + "JOIN person per ON ath.athPerID = per.perID "
                    + "HAVING (athName LIKE '%" + search + "%') OR (compName LIKE '%" + search + "%') "
                    + "ORDER BY regDate";
            rss = stmt.executeQuery(sqlQuery);
            
            while(rss.next())
            {
                Registration reg = new Registration(false);
                reg.setCode(rss.getString("regCode"));
                java.sql.Date sqlRsDate = rss.getDate("regDate");
                java.util.Date rsDate = new java.util.Date(sqlRsDate.getTime());
                reg.setRegDate(rsDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                reg.setCompetition(rss.getString("compName"));
                reg.setAthlete(rss.getString("athName"));
                alReg.add(reg);
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

        return alReg;
    }
    
    public static String getAthleteNameReg(String regAthID) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, regAthName = null;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT CONCAT(perName, ' ', perSurname) AS athName FROM person WHERE perID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, regAthID);
            rss = ps.executeQuery();
            rss.next();
            regAthName = rss.getString("athName");
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
        
        return regAthName;
    }
    
    public static String getCompetitionNameReg(String regCompCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, regCompName = null;
        ResultSet rss = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT compName FROM competition WHERE compCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, regCompCode);
            rss = ps.executeQuery();
            rss.next();
            regCompName = rss.getString("compName");
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
        
        return regCompName;
    }
    
}
