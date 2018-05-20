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
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Competition implements Serializable
{
    // Define the members
    private String code;
    private String name;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    
    // Define the constructor
    public Competition(boolean bl)
    {
        
    }
    
    // Define the setters
    public void setCode(String CompetitionCode)
    {
        code = CompetitionCode;
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
    
    public void setName(String CompetitionName)
    {
        name = CompetitionName;
    }
    
    public void setDescription(String CompetitionDescription)
    {
        description = CompetitionDescription;
    }
    
    public void setLocation(String CompetitionLocation)
    {
        location = CompetitionLocation;
    }
    
    public void setStartDate(Date CompetitionStartDate)
    {
        startDate = CompetitionStartDate;
    }
    
    public void setEndDate(Date CompetitionEndDate)
    {
        endDate = CompetitionEndDate;
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
    
    public String getDescription()
    {
        return description;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public Date getStartDate()
    {
        return startDate;
    }
    
    public Date getEndDate()
    {
        return endDate;
    }
}
