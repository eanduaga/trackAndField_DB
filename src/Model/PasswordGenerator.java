/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Eider
 */
public class PasswordGenerator
{
    public static boolean writePassword(String username, String password, String fullName, String email)
    {
        // Define the variables
        String flUser;
        boolean result = true;
        File flPass = new File("files/htpasswd.dat");
        
        try
        {            
            FileInputStream fis = new FileInputStream(flPass);
            DataInputStream dis = new DataInputStream(fis);
                
            try
            {
                while(true)
                {
                    flUser = dis.readUTF();
                    
                    if(username.equals(flUser))
                    {
                        result = false;
                    }
                }
            }
            catch(EOFException eof)
            {
                fis.close();
                dis.close();
            }
            
            FileOutputStream fs = new FileOutputStream(flPass, true);
            DataOutputStream ds = new DataOutputStream(fs);
            
            if(result == true)
            {
                ds.writeUTF(username);
                ds.writeUTF(password);
                ds.writeUTF(fullName);
                ds.writeUTF(email);
                ds.close();
                fs.close();
            }
            else
            {
                System.out.println("That username already exists. Please, enter the information again.");
            }
        }
        catch(IOException ex1)
        {
            
        }
        
        return result;
    }
    
    public static boolean readPassword(String username, String password)
    {
        // Define the variables
        String flUser, flPasswd, flFullName, flEmailAcc;
        boolean result = false;
        File flPass = new File("files/htpasswd.dat");
        
        try
        {            
            FileInputStream fis = new FileInputStream(flPass);
            DataInputStream dis = new DataInputStream(fis);
                
            try
            {
                while(true)
                {
                    flUser = dis.readUTF();
                    flPasswd = dis.readUTF();
                    flFullName = dis.readUTF();
                    flEmailAcc = dis.readUTF();
                    
                    if(username.equals(flUser) && password.equals(flPasswd))
                    {
                        result = true;
                    }
                }
            }
            catch(EOFException eof)
            {
                fis.close();
                dis.close();
            }
        }
        catch(IOException ex1)
        {
            
        }
        
        return result;
    }
    
    public static String[] passwordRecovery(String emailAcc)
    {
        // Define the variables
        String flUser, flPasswd, flFullName, flEmailAcc;
        String[] result = new String[3];
        File flPass = new File("files/htpasswd.dat");
        
        try
        {            
            FileInputStream fis = new FileInputStream(flPass);
            DataInputStream dis = new DataInputStream(fis);
                
            try
            {
                while(true)
                {
                    flUser = dis.readUTF();
                    flPasswd = dis.readUTF();
                    flFullName = dis.readUTF();
                    flEmailAcc = dis.readUTF();
                    
                    if(emailAcc.equals(flEmailAcc))
                    {
                        result[0] = flPasswd;
                        result[1] = flFullName;
                        result[2] = flEmailAcc;
                    }
                }
            }
            catch(EOFException eof)
            {
                fis.close();
                dis.close();
            }
        }
        catch(IOException ex1)
        {
            
        }
        
        return result;
    }
}
