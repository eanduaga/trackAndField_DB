/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DM3-1-03
 */

// Import the libraries
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DisciplineMethods
{
    public static ArrayList <Discipline> fillTableDiscipline() throws IOException
    {
        // Define the variables
        ArrayList <Discipline> alDis = new ArrayList();
        float[] wr = new float[2];
        String sqlQuery;
        DBConnection cnt = new DBConnection();
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT * FROM discipline";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Discipline dis = new Discipline(false);
                dis.setCode(rs.getString("disCode"));
                dis.setName(rs.getString("disName"));
                dis.setDescription(rs.getString("disDescription"));
                wr[0] = rs.getFloat("disMaleWR");
                wr[1] = rs.getFloat("disFemaleWR");
                dis.setWorldRecord(wr);
                alDis.add(dis);
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

        return alDis;
    }
    
    public static void insertDiscipline(Discipline dis) throws IOException
    {
        // Define the variables
        ArrayList <Discipline> alDis = new ArrayList();
        Discipline displ = new Discipline(false);
        int i;
        boolean result = false;
        float[] wr = new float[2];
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            alDis = fillTableDiscipline();
            for(i = 0; i <alDis.size(); ++i)
            {
                displ = alDis.get(i);
                if(dis.getCode().equals(displ.getCode()))
                {
                    result = true;
                }
            }

            if(result == false)
            {
                sqlQuery = "INSERT INTO discipline VALUES (?, ?, ?, ?, ?)";
                ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
                ps.setString(1, dis.getCode());
                ps.setString(2, dis.getName());
                ps.setString(3, dis.getDescription());
                wr = dis.getWorldRecord();
                ps.setFloat(4, wr[0]);
                ps.setFloat(5, wr[1]);
                ps.executeUpdate();
            }
            else
            {
                updateDiscipline(dis);
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
    
    public static void deleteDiscipline(String tableDisCode) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "DELETE FROM discipline WHERE disCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableDisCode);
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            // MySQLIntegrityConstraintViolationException
            JOptionPane.showMessageDialog(null, "The information of the discipline you are trying to remove is on the sections result or schedule. "
                    + "Please, check those records before deleting the discipline.", "Dependency error", JOptionPane.ERROR_MESSAGE);
        }
        
        finally
        {
            try { ps.close(); } catch (SQLException e) { /* ignored */ }
            try { cnt.disconnect(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static Discipline showUpdateViewDiscipline(String tableDisCode) throws IOException
    {
        // Define the variables
        Discipline dis = new Discipline(false);
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        float[] wr = new float[2];
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "SELECT * FROM discipline WHERE disCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            ps.setString(1, tableDisCode);
            rs = ps.executeQuery();
            rs.next();
            dis.setCode(rs.getString("disCode"));
            dis.setName(rs.getString("disName"));
            dis.setDescription(rs.getString("disDescription"));
            wr[0] = rs.getFloat("disMaleWR");
            wr[1] = rs.getFloat("disFemaleWR");
            dis.setWorldRecord(wr);
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
        
        return dis;
    }
    
    public static void updateDiscipline(Discipline dis) throws IOException
    {
        // Define the variables
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        float[] wr = new float[2];
        PreparedStatement ps = null;
        
        try
        {
            sqlQuery = "UPDATE discipline SET disMaleWR = ?, disFemaleWR = ? WHERE disCode = ?";
            ps = (PreparedStatement) cnt.getDBConnection().prepareStatement(sqlQuery);
            wr = dis.getWorldRecord();
            ps.setFloat(1, wr[0]);
            ps.setFloat(2, wr[1]);
            ps.setString(4, dis.getCode());
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
    
    public static ArrayList <Discipline> searchDiscipline(String search) throws IOException
    {
        // Define the variables
        ArrayList <Discipline> alDis = new ArrayList();
        DBConnection cnt = new DBConnection();
        String sqlQuery;
        float[] wr = new float[2];
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = (Statement) cnt.getDBConnection().createStatement();
            sqlQuery = "SELECT * FROM discipline "
                    + "WHERE (disName LIKE '%" + search + "%') OR (disDescription LIKE '%" + search + "%') "
                    + "ORDER BY disName";
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next())
            {
                Discipline dis = new Discipline(false);
                dis.setCode(rs.getString("disCode"));
                dis.setName(rs.getString("disName"));
                dis.setDescription(rs.getString("disDescription"));
                wr[0] = rs.getFloat("disMaleWR");
                wr[1] = rs.getFloat("disFemaleWR");
                dis.setWorldRecord(wr);
                alDis.add(dis);
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

        return alDis;
    }
}
