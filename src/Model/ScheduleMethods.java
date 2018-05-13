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
public class ScheduleMethods
{
    public static void writeSchedule(Schedule sch) throws IOException
    {
        // Define the variables
        File schFl = new File("files/schedule.ser");
        
        try
        {
            // If the file doesn't exist, create a new ObjectOutputStream to write the header
            if(!schFl.exists())
            {
                FileOutputStream fs1 = new FileOutputStream(schFl);
                ObjectOutputStream os1 = new ObjectOutputStream(fs1);
                os1.close();
            }

            // Create another ObjectOutputStream without the header to be able to write objects without overwriting
            FileOutputStream fs = new FileOutputStream(schFl, true);
            ObjectOutputStream os = new ObjectOutputStream(fs)
            {
                @Override
                protected void writeStreamHeader() throws IOException
                {
                    reset();
                }
            };
            
            // Write the object and close the file
            os.writeObject(sch);
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Schedule> writeScheduleArrayList() throws IOException
    {
        // Define the variables
        File schFl = new File("files/schedule.ser");
        ArrayList <Schedule> alSch = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(schFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Schedule sch = new Schedule(false);
                    sch = (Schedule) os.readObject();
                    alSch.add(sch);                    
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
        
        return alSch;
    }
    
    public static void writeSchFileFromArrayList(ArrayList <Schedule> alSch) throws IOException
    {
        // Define the variables
        int i;
        File schFl = new File("files/schedule.ser");
        
        try
        {
            FileOutputStream fs = new FileOutputStream(schFl);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            // Write the objects of the ArrayList and close the file
            for(i = 0; i < alSch.size(); ++i)
            {
                Schedule sch = alSch.get(i);
                os.writeObject(sch);
            }
            
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Schedule> searchScheduleArrayList(String search) throws IOException
    {
        // Define the variables
        File schFl = new File("files/schedule.ser");
        ArrayList <Schedule> alSchSearch = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(schFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Schedule sch = new Schedule(false);
                    sch = (Schedule) os.readObject();
                    
                    if(sch.getDiscipline().toLowerCase().contains(search))
                    {
                        alSchSearch.add(sch); 
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
        
        return alSchSearch;
    }
}
