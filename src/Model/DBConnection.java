/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adminpremialocal
 */
public class DBConnection
{
    // Define the members
    static String db = "trackandfield";
    static String username = "root";
    static String password = "hGiA5r6Ba";
    static String url = "jdbc:mysql://localhost/" + db;
    Connection cnt;
    
    public DBConnection()
    {
        try
        {
            cnt = (Connection) DriverManager.getConnection(url, username, password);
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getDBConnection()
    {
        return cnt;
    }
    
    public void disconnect()
    {
        try
        {
            cnt.close();
        } 
        catch(SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
