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

public class Athlete extends Person implements Serializable
{
    // Define the members
    private String favouriteDiscipline;
    private float personalBest;
    private float seasonBest;
    private int numMedals;
    
    // Define the constructor
    public Athlete(boolean bl)
    {
        super(false);
    }
    
    // Define the setters
    public void setFavouriteDiscipline(String AthleteFavDiscipline)
    {
        favouriteDiscipline = AthleteFavDiscipline;
    }
    
    public void setPersonalBest(float AthletePersonalBest)
    {
        personalBest = AthletePersonalBest;
    }
    
    public void setSeasonBest(float AthleteSeasonBest)
    {
        seasonBest = AthleteSeasonBest;
    }
    
    public void setNumMedals(int AthleteNumMedals)
    {
        numMedals = AthleteNumMedals;
    }
    
    // Define the getters
    public String getFavouriteDiscipline()
    {
        return favouriteDiscipline;
    }
    
    public float getPersonalBest()
    {
        return personalBest;
    }
    
    public float getSeasonBest()
    {
        return seasonBest;
    }
    
    public int getNumMedals()
    {
        return numMedals;
    } 
}
