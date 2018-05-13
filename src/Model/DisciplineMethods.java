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
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DisciplineMethods
{
    public static void writeDiscipline(Discipline dis) throws IOException
    {
        // Define the variables
        File disFl = new File("files/discipline.ser");
        
        try
        {
            // If the file doesn't exist, create a new ObjectOutputStream to write the header
            if(!disFl.exists())
            {
                FileOutputStream fs1 = new FileOutputStream(disFl);
                ObjectOutputStream os1 = new ObjectOutputStream(fs1);
                os1.close();
            }

            // Create another ObjectOutputStream without the header to be able to write objects without overwriting
            FileOutputStream fs = new FileOutputStream(disFl, true);
            ObjectOutputStream os = new ObjectOutputStream(fs)
            {
                @Override
                protected void writeStreamHeader() throws IOException
                {
                    reset();
                }
            };
            
            // Write the object and close the file
            os.writeObject(dis);
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Discipline> writeDisciplineArrayList() throws IOException
    {
        // Define the variables
        File disFl = new File("files/discipline.ser");
        ArrayList <Discipline> alDis = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(disFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Discipline dis = new Discipline(false);
                    dis = (Discipline) os.readObject();
                    alDis.add(dis);                    
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
        
        return alDis;
    }
    
    public static void writeDisFileFromArrayList(ArrayList <Discipline> alDis) throws IOException
    {
        // Define the variables
        int i;
        File disFl = new File("files/discipline.ser");
        
        try
        {
            FileOutputStream fs = new FileOutputStream(disFl);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            // Write the objects of the ArrayList and close the file
            for(i = 0; i < alDis.size(); ++i)
            {
                Discipline dis = alDis.get(i);
                os.writeObject(dis);
            }
            
            os.close();
            fs.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found.");
        }
    }
    
    public static ArrayList <Discipline> searchDisciplineArrayList(String search) throws IOException
    {
        // Define the variables
        File disFl = new File("files/discipline.ser");
        ArrayList <Discipline> alDisSearch = new ArrayList();
        
        try
        {
            FileInputStream fs = new FileInputStream(disFl);
            ObjectInputStream os = new ObjectInputStream(fs);
            
            try
            {
                while(true)
                {
                    Discipline dis = new Discipline(false);
                    dis = (Discipline) os.readObject();
                    
                    if(dis.getName().toLowerCase().contains(search))
                    {
                        alDisSearch.add(dis); 
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
        
        return alDisSearch;
    }
}
