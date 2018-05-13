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
public class AthleteMethods
{
    public static ArrayList <Athlete> fillTableAthlete() throws IOException
    {
        // Define the variables
        ArrayList <Athlete> alAth = new ArrayList();
        String sqlQuery;
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT athPerID, perName, perSurname, perEmail, perPhoneNum FROM person JOIN athlete ON perID = athPerID GROUP BY perName";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Athlete ath = new Athlete(false);
                ath.setID(rs.getString("athPerID"));
                ath.setName(rs.getString("perName"));
                ath.setSurname(rs.getString("perSurname"));
                ath.setEmail(rs.getString("perEmail"));
                ath.setPhoneNum(rs.getInt("perPhoneNum"));
                alAth.add(ath);
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

        return alAth;
    }
    
    public static void insertAthlete(Athlete ath) throws IOException
    {
        // Define the variables
        ArrayList <Athlete> alAth = new ArrayList();
        Athlete athl = new Athlete(false);
        int i;
        boolean result = false;
        DBConnection cnt = new DBConnection();
        String sqlQuery, athTmCode;
        ResultSet rs;
        PreparedStatement ps = null;
        
        try
        {
            alAth = fillTableAthlete();
            for(i = 0; i <alAth.size(); ++i)
            {
                athl = alAth.get(i);
                if(ath.getID().equals(athl.getID()))
                {
                    result = true;
                }
            }

            if(result == false)
            {
                sqlQuery = "SELECT tmCode FROM team WHERE tmName = ?";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, ath.getTeam());
                rs = ps.executeQuery();
                rs.next();
                athTmCode = rs.getString("tmCode");
                
                sqlQuery = "INSERT INTO person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, ath.getID());
                ps.setString(2, ath.getName());
                ps.setString(3, ath.getSurname());
                ps.setString(4, ath.getCountry());
                ps.setString(5, ath.getHomeTown());
                ps.setString(6, ath.getAddress());
                ps.setString(7, ath.getNationality());
                java.util.Date athBirthDate = Date.from(ath.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlAthBirthDate = new java.sql.Date(athBirthDate.getTime());
                ps.setDate(8, sqlAthBirthDate);
                ps.setString(9, ath.getEmail());
                ps.setInt(10, ath.getPhoneNum());
                ps.setString(11, athTmCode);
                ps.executeUpdate();
                
                sqlQuery = "INSERT INTO athlete VALUES (?, ?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, ath.getID());
                ps.setString(2, ath.getFavouriteDiscipline());
                ps.setFloat(3, ath.getPersonalBest());
                ps.setFloat(4, ath.getSeasonBest());
                ps.setInt(5, ath.getNumMedals());
                ps.executeUpdate();
            }
            else
            {
                updateAthlete(ath);
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
    
    public static void deleteAthlete(String tableAthID) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "DELETE FROM athlete WHERE athPerID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableAthID);
            ps.executeUpdate();
            
            sqlQuery = "DELETE FROM person WHERE perID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableAthID);
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            // MySQLIntegrityConstraintViolationException
            JOptionPane.showMessageDialog(null, "The information of the athlete you are trying to remove is on the sections result, schedule or "
                    + "registration. Please, check those records before deleting the athlete.", "Dependency error", JOptionPane.ERROR_MESSAGE);
        }
        
        finally
        {
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static Athlete showUpdateViewAthlete(String tableAthID) throws IOException
    {
        // Define the variables
        Athlete ath = new Athlete(false);
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT * FROM person WHERE perID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableAthID);
            rs = ps.executeQuery();
            rs.next();
            ath.setID(rs.getString("perID"));
            ath.setName(rs.getString("perName"));
            ath.setSurname(rs.getString("perSurname"));
            ath.setCountry(rs.getString("perCountry"));
            ath.setHomeTown(rs.getString("perHomeTown"));
            ath.setAddress(rs.getString("perAddress"));
            ath.setNationality(rs.getString("perNationality"));
            java.sql.Date sqlAthBirthDate = rs.getDate("perBirthDate");
            java.util.Date athBirthDate = new java.util.Date(sqlAthBirthDate.getTime());
            ath.setBirthDate(athBirthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            ath.setEmail(rs.getString("perEmail"));
            ath.setPhoneNum(rs.getInt("perPhoneNum"));
            ath.setTeam(rs.getString("perTeam"));
            
            sqlQuery = "SELECT * FROM athlete WHERE athPerID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableAthID);
            rs = ps.executeQuery();
            rs.next();
            ath.setFavouriteDiscipline(rs.getString("athFavDiscipline"));
            ath.setPersonalBest(rs.getFloat("athPB"));
            ath.setSeasonBest(rs.getFloat("athSB"));
            ath.setNumMedals(rs.getInt("athNumMedals"));
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
        
        return ath;
    }
    
    public static void updateAthlete(Athlete ath) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, athTmCode = null;
        ResultSet rs;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT tmCode FROM team WHERE tmName = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, ath.getTeam());
            rs = ps.executeQuery();
            rs.next();
            athTmCode = rs.getString("tmCode");
                
            sqlQuery = "UPDATE person SET perCountry = ?, perAddress = ?, perNationality = ?, perEmail = ?, perPhoneNum = ?, perTeam = ? WHERE perID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, ath.getCountry());
            ps.setString(2, ath.getAddress());
            ps.setString(3, ath.getNationality());
            ps.setString(4, ath.getEmail());
            ps.setInt(5, ath.getPhoneNum());
            ps.setString(6, athTmCode);
            ps.setString(7, ath.getID());
            ps.executeUpdate();
            
            sqlQuery = "UPDATE athlete SET athFavDiscipline = ?, athPB = ?, athSB = ?, athNumMedals = ? WHERE athPerID = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, ath.getFavouriteDiscipline());
            ps.setFloat(2, ath.getPersonalBest());
            ps.setFloat(3, ath.getSeasonBest());
            ps.setInt(4, ath.getNumMedals());
            ps.setString(5, ath.getID());
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
    
    public static ArrayList <Athlete> searchAthlete(String search) throws IOException
    {
        // Define the variables
        ArrayList <Athlete> alAth = new ArrayList();
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT athPerID, perName, perSurname, perEmail, perPhoneNum FROM person JOIN athlete ON perID = athPerID "
                    + "WHERE (perName LIKE '%" + search + "%') OR (perSurname LIKE '%" + search + "%') GROUP BY perName";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Athlete ath = new Athlete(false);
                ath.setID(rs.getString("athPerID"));
                ath.setName(rs.getString("perName"));
                ath.setSurname(rs.getString("perSurname"));
                ath.setEmail(rs.getString("perEmail"));
                ath.setPhoneNum(rs.getInt("perPhoneNum"));
                alAth.add(ath);
            }
        }
        catch(SQLException ex)
        {
            // JOptionPane.showMessageDialog(null, "Error, no se conecto");
        }
        
        finally
        {
            if(rs != null)
            {
                try { rs.close(); } catch (SQLException e) { /* ignored */ }
            }
            try { stmt.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }

        return alAth;
    }
    
    public static String getTeamNameAth(String athTmCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery, athTmName = null;
        ResultSet rs;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT tmName FROM team WHERE tmCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, athTmCode);
            rs = ps.executeQuery();
            rs.next();
            athTmName = rs.getString("tmName");
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
        
        return athTmName;
    }
}
