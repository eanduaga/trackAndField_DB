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
public class CompetitionMethods
{
    public static void writeCompetition(Competition cmp) throws IOException
    {
        // Define the variables
        File cmpFl = new File("files/competition.ser");
        
        try
        {
            // If the file doesn't exist, create a new ObjectOutputStream to write the header
            if(!cmpFl.exists())
            {
                FileOutputStream fs1 = new FileOutputStream(cmpFl);
                ObjectOutputStream os1 = new ObjectOutputStream(fs1);
                os1.close();
            }

            // Create another ObjectOutputStream without the header to be able to write objects without overwriting
            FileOutputStream fs = new FileOutputStream(cmpFl, true);
            ObjectOutputStream os = new ObjectOutputStream(fs)
            {
                @Override
                protected void writeStreamHeader() throws IOException
                {
                    reset();
                }
            };
            
            // Write the object and close the file
            os.writeObject(cmp);
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Competition> writeCompetitionArrayList() throws IOException
    {
        // Define the variables
        File compFl = new File("files/competition.ser");
        ArrayList <Competition> alComp = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(compFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Competition comp = new Competition(false);
                    comp = (Competition) os.readObject();
                    alComp.add(comp);                    
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
        
        return alComp;
    }
    
    public static void writeCompFileFromArrayList(ArrayList <Competition> alComp) throws IOException
    {
        // Define the variables
        int i;
        File cmpFl = new File("files/competition.ser");
        
        try
        {
            FileOutputStream fs = new FileOutputStream(cmpFl);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            // Write the objects of the ArrayList and close the file
            for(i = 0; i < alComp.size(); ++i)
            {
                Competition comp = alComp.get(i);
                os.writeObject(comp);
            }
            
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Competition> searchCompetitionArrayList(String search) throws IOException
    {
        // Define the variables
        File compFl = new File("files/competition.ser");
        ArrayList <Competition> alCompSearch = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(compFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Competition comp = new Competition(false);
                    comp = (Competition) os.readObject();
                    
                    if(comp.getName().toLowerCase().contains(search))
                    {
                        alCompSearch.add(comp); 
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
        
        return alCompSearch;
    }
}
