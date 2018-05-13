/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Eider
 */
public class RegistrationMethods
{
    public static void writeRegistration(Registration reg) throws IOException
    {
        // Define the variables
        File regFl = new File("files/registration.ser");
        
        try
        {
            // If the file doesn't exist, create a new ObjectOutputStream to write the header
            if(!regFl.exists())
            {
                FileOutputStream fs1 = new FileOutputStream(regFl);
                ObjectOutputStream os1 = new ObjectOutputStream(fs1);
                os1.close();
            }

            // Create another ObjectOutputStream without the header to be able to write objects without overwriting
            FileOutputStream fs = new FileOutputStream(regFl, true);
            ObjectOutputStream os = new ObjectOutputStream(fs)
            {
                @Override
                protected void writeStreamHeader() throws IOException
                {
                    reset();
                }
            };
            
            // Write the object and close the file
            os.writeObject(reg);
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Registration> writeRegistrationArrayList() throws IOException
    {
        // Define the variables
        File regFl = new File("files/registration.ser");
        ArrayList <Registration> alReg = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(regFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Registration reg = new Registration(false);
                    reg = (Registration) os.readObject();
                    alReg.add(reg);                    
                }
            }
            catch(EOFException ex1)
            {
                fs.close();
                os.close();
            }
        }
        catch(IOException | ClassNotFoundException ex1)
        {
            
        }
        
        return alReg;
    }
    
    public static void writeRegFileFromArrayList(ArrayList <Registration> alReg) throws IOException
    {
        // Define the variables
        int i;
        File regFl = new File("files/registration.ser");
        
        try
        {
            FileOutputStream fs = new FileOutputStream(regFl);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            // Write the objects of the ArrayList and close the file
            for(i = 0; i < alReg.size(); ++i)
            {
                Registration reg = alReg.get(i);
                os.writeObject(reg);
            }
            
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Registration> searchRegistrationArrayList(String search) throws IOException
    {
        // Define the variables
        File regFl = new File("files/registration.ser");
        ArrayList <Registration> alRegSearch = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(regFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Registration reg = new Registration(false);
                    reg = (Registration) os.readObject();
                    
                    if(reg.getAthlete().toLowerCase().contains(search))
                    {
                        alRegSearch.add(reg); 
                    }                
                }
            }
            catch(EOFException ex1)
            {
                fs.close();
                os.close();
            }
        }
        catch(IOException | ClassNotFoundException ex1)
        {
            
        }
        
        return alRegSearch;
    }
}
