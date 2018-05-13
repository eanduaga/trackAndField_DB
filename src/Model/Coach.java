/*
 * To change this license header, choose License Headers in
 * Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DM3-1-03
 */

// Import the libraries
import java.io.Serializable;
import java.time.Year;

public class Coach extends Person implements Serializable
{
    // Define the members
    private Year startYear;
    
    // Define the constructor
    public Coach(boolean bl)
    {
        super(false);
    }
    
    // Define the setters
    public void setStartYear(Year CoachStartYear)
    {
        startYear = CoachStartYear;
    }
    
    // Define the getters
    public Year getStartYear()
    {
        return startYear;
    }
}
