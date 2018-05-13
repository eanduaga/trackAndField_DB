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

public class Person implements Serializable
{
    // Define the members
    private String ID;
    private String name;
    private String surname;
    private String country;
    private String homeTown;
    private String address;
    private String nationality;
    private LocalDate birthDate;
    private String email;
    private int phoneNum;
    private String team;
    
    // Define the constructor
    public Person(boolean bl)
    {
        
    }
    
    // Define the setters
    public void setID(String PersonID)
    {
        ID = PersonID;
    }

    public void setName(String PersonName)
    {
        name = PersonName;
    }
    
    public void setSurname(String PersonSurname)
    {
        surname = PersonSurname;
    }
    
    public void setCountry(String PersonCountry)
    {
        country = PersonCountry;
    }
    
    public void setHomeTown(String PersonHomeTown)
    {
        homeTown = PersonHomeTown;
    }
    
    public void setAddress(String PersonAddress)
    {
        address = PersonAddress;
    }
    
    public void setNationality(String PersonNationality)
    {
        nationality = PersonNationality;
    }
    
    public void setBirthDate(LocalDate PersonBirthDate)
    {
        birthDate = PersonBirthDate;
    }
    
    public void setEmail(String PersonEmail)
    {
        email = PersonEmail;
    }
    
    public void setPhoneNum(int PersonPhoneNum)
    {
        phoneNum = PersonPhoneNum;
    }
    
    public void setTeam(String PersonTeam)
    {
        team = PersonTeam;
    }
    
    
    // Define the getters
    public String getID()
    {
        return ID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getSurname()
    {
        return surname;
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public String getHomeTown()
    {
        return homeTown;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public String getNationality()
    {
        return nationality;
    }
    
    public LocalDate getBirthDate()
    {
        return birthDate;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public int getPhoneNum()
    {
        return phoneNum;
    }
    
    public String getTeam()
    {
        return team;
    }  
}
