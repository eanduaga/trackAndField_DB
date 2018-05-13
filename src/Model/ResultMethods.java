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
public class ResultMethods
{
    public static void writeResult(Result rs) throws IOException
    {
        // Define the variables
        File rsFl = new File("files/result.ser");
        
        try
        {
            // If the file doesn't exist, create a new ObjectOutputStream to write the header
            if(!rsFl.exists())
            {
                FileOutputStream fs1 = new FileOutputStream(rsFl);
                ObjectOutputStream os1 = new ObjectOutputStream(fs1);
                os1.close();
            }

            // Create another ObjectOutputStream without the header to be able to write objects without overwriting
            FileOutputStream fs = new FileOutputStream(rsFl, true);
            ObjectOutputStream os = new ObjectOutputStream(fs)
            {
                @Override
                protected void writeStreamHeader() throws IOException
                {
                    reset();
                }
            };
            
            // Write the object and close the file
            os.writeObject(rs);
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Result> writeResultArrayList() throws IOException
    {
        // Define the variables
        File rsFl = new File("files/result.ser");
        ArrayList <Result> alRs = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(rsFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Result rs = new Result(false);
                    rs = (Result) os.readObject();
                    alRs.add(rs);                    
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
        
        return alRs;
    }
    
    public static void writeRsFileFromArrayList(ArrayList <Result> alRs) throws IOException
    {
        // Define the variables
        int i;
        File rsFl = new File("files/result.ser");
        
        try
        {
            FileOutputStream fs = new FileOutputStream(rsFl);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            // Write the objects of the ArrayList and close the file
            for(i = 0; i < alRs.size(); ++i)
            {
                Result rs = alRs.get(i);
                os.writeObject(rs);
            }
            
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Result> searchResultArrayList(String search) throws IOException
    {
        // Define the variables
        File rsFl = new File("files/result.ser");
        ArrayList <Result> alRsSearch = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(rsFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Result rs = new Result(false);
                    rs = (Result) os.readObject();
                    
                    if(rs.getAthlete().toLowerCase().contains(search))
                    {
                        alRsSearch.add(rs); 
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
        
        return alRsSearch;
    }
}
