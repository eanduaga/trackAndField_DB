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

public class Result implements Serializable
{
    // Define the members
    private String code;
    private String competition;
    private String discipline;
    private String gender;
    private String athlete;
    private String round;
    private float time;
    private int position;
    private Date rDate;
    
    // Define the constructor
    public Result(boolean bl)
    {
        
    }
    
    // Define the setters
    public void setCode(String ResultCode)
    {
        code = ResultCode;
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

    public void setCompetition(String ResultCompetition)
    {
        competition = ResultCompetition;
    }
	
    public void setDiscipline(String ResultDiscipline)
    {
        discipline = ResultDiscipline;
    }
	
    public void setGender(String ResultGender)
    {
        gender = ResultGender;
    }

    public void setAthlete(String ResultAthlete)
    {
        athlete = ResultAthlete;
    }

    public void setRound(String ResultRound)
    {
        round = ResultRound;
    }

    public void setTime(float ResultTime)
    {
        time = ResultTime;
    }

    public void setPosition(int ResultPosition)
    {
        position = ResultPosition;
    }

    public void setDate(Date ResultDate)
    {
        rDate = ResultDate;
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

    public String getGender()
    {
        return gender;
    }

    public String getAthlete()
    {
        return athlete;
    }

    public String getRound()
    {
        return round;
    }

    public float getTime()
    {
        return time;
    }

    public int getPosition()
    {
        return position;
    }

    public Date getDate()
    {
        return rDate;
    } 
}
