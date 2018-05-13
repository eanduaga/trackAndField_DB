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
import java.util.Random;

public class Team implements Serializable
{
    // Define the members
    private String code;
    private String name;
    private String country;
    private String town;
    
    // Define the constructor
    public Team(boolean bl)
    {
        
    }
    
    // Define the setters
    public void setCode(String TeamCode)
    {
        code = TeamCode;
    }
    public void setCode()
    {
        // Define the variables
        int i, digit;
        char letter;
        
        Random rnd = new Random();
        StringBuffer randomID = new StringBuffer();
        
        // Generate the random ID
        // First get the letters
        for(i = 0; i <= 1; ++i)
        {
            letter = (char) (rnd.nextInt(26) + 'A');
            randomID.append(letter);
        }
        
        // Then get the digits
        for(i = 2; i <= 3; ++i)
        {
            digit = rnd.nextInt(9) + 0;
            randomID.append(digit);
        }
        
        // Store the generated code in the member code
        code = randomID.toString();
    }
    
    public void setName(String TeamName)
    {
        name = TeamName;
    }
    
    public void setCountry(String TeamCountry)
    {
        country = TeamCountry;
    }
    
    public void setTown(String TeamTown)
    {
        town = TeamTown;
    }
    
    
    // Define the getters
    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public String getTown()
    {
        return town;
    }
}
