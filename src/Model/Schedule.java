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

public class Schedule implements Serializable
{
    // Define the members
    private String code;
    private String competition;
    private String discipline;
    private LocalDate sDate;
    private String round;
    private String gender;
    
    // Define the constructor
    public Schedule(boolean bl)
    {
        
    }
    
    // Define the setters
    public void setCode(String ScheduleCode)
    {
        code = ScheduleCode;
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

    public void setCompetition(String ScheduleCompetition)
    {
        competition = ScheduleCompetition;
    }
    
    public void setDiscipline(String ScheduleDiscipline)
    {
        discipline = ScheduleDiscipline;
    }
    
    public void setDate(LocalDate ScheduleDate)
    {
        sDate = ScheduleDate;
    }
    
    public void setRound(String ScheduleRound)
    {
        round = ScheduleRound;
    }
    
    public void setGender(String ScheduleGender)
    {
        gender = ScheduleGender;
    }
    
    
    // Define the getters
    public String getCode()
    {
        return code;
    }
    
   public String getCompetition()
   {
       return competition;
   }
   
   public String getDiscipline()
   {
       return discipline;
   }
   
   public LocalDate getDate()
   {
       return sDate;
   }
   
   public String getRound()
    {
        return round;
    }
    
    public String getGender()
    {
        return gender;
    }
}
