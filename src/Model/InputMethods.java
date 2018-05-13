/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

/**
 *
 * @author Eider
 */
public class InputMethods
{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    // Static method to read strings
    public static String readString(String line)
    {
        // Define the variables
        String st = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Ask for the string and store the answer
        System.out.print(line);
        try
        {
            st = reader.readLine();
        }
        catch(IOException ex1)
        {
            System.out.println("Couldn't read.");
        }
        
        // Return the answer
        return st;
    }
    
    // Static method to read integers
    public static int readInt(String line)
    {
        // Define the variables
        String st = null;
        int number = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Ask for the string and store the answer
        System.out.print(line);
        try
        {
            st = reader.readLine();
        }
        catch(IOException ex1)
        {
            System.out.println("Couldn't read.");
        }
        
        // Parse the information
        try
        {
            number = Integer.parseInt(st);
        }
        catch(InputMismatchException ex1)
        {
            System.out.println(ANSI_RED + "Please, enter an integer number." + ANSI_RESET);
        }
        
        // Return the answer
        return number;
    }
    
    // Static method to read floats
    public static float readFloat(String line)
    {
        // Define the variables
        String st = null;
        float number = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // Ask for the string and store the answer
        System.out.print(line);
        try
        {
            st = reader.readLine();
        }
        catch(IOException ex1)
        {
            System.out.println("Couldn't read.");
        }
        
        // Parse the information
        try
        {
            number = Float.parseFloat(st);
        }
        catch(InputMismatchException ex1)
        {
            System.out.println(ANSI_RED + "Please, enter a float number using the dot." + ANSI_RESET);
        }
        
        // Return the answer
        return number;
    }
}
