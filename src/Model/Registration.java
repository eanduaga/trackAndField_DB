/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Eider
 */

// Import the libraries
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Registration implements Serializable
{
    // Define the members
    private String code;
    private String athlete;
    private String competition;
    private LocalDate regDate;
    
    // Define the constructor
    public Registration(boolean bl)
    {
        
    }
    
    // Define the setters
    public void setCode(String RegistrationCode)
    {
        code = RegistrationCode;
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
        
        // Store the generated code in the member ID
        code = randomID.toString();
    }
    
    public void setAthlete(String RegistrationAthlete)
    {
        athlete = RegistrationAthlete;
    }
    
    public void setCompetition(String RegistrationCompetition)
    {
        competition = RegistrationCompetition;
    }
    
    public void setRegDate(LocalDate RegistrationDate)
    {
        regDate = RegistrationDate;
    }
    public void setRegDate()
    {
        // Save the current date
        regDate = LocalDate.now();
    }
    
    // Define the getters
    public String getCode()
    {
        return code;
    }
    
    public String getAthlete()
    {
        return athlete;
    }
    
    public String getCompetition()
    {
        return competition;
    }
    
    public LocalDate getRegDate()
    {
        return regDate;
    }
}
