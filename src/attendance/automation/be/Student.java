/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SKRUMM
 */
public class Student extends Person
{
    private IntegerProperty absenceProcent;
    private StringProperty dayMostAbsent;
    private IntegerProperty classID;
    
   /**
    * This defines a student to the programm
    * @param name
    * @param username
    * @param password
    * @param absenceProcent
    * @param dayMostAbsent
    * @param classID 
    */
    public Student(String name, String username, String password, int absenceProcent, String dayMostAbsent, int classID)
    {
        super(name, username, password);
        this.absenceProcent = new SimpleIntegerProperty(absenceProcent);
        this.dayMostAbsent = new SimpleStringProperty(dayMostAbsent);
        this.classID = new SimpleIntegerProperty(classID);
    }

    /**
     * Gets the absence percentage of the student
     * @return the absence percentage
     */
    public int getAbsenceProcent()
    {
        return absenceProcent.get();
    }

    /**
     * Sets the absence percentage of the student
     * @param absenceProcent 
     */
    public void setAbsenceProcent(int absenceProcent)
    {
        this.absenceProcent.set(absenceProcent);
    }

    /**
     * Gets the most absent day of the student (days of the week)
     * @return the day the student is the most absent
     */
    public String getDayMostAbsent()
    {
        return dayMostAbsent.get();
    }

    /**
     * Sets the most absent day for the student
     * @param dayMostAbsent 
     */
    public void setDayMostAbsent(String dayMostAbsent)
    {
        this.dayMostAbsent.set(dayMostAbsent);
    }
    
    /**
     * Gets the username of the student
     * @return the username
     */
    public String getUsername()
    {
        return username.get();
    }

    /**
     * Gets the password of the student
     * @return the password
     */
    public String getPassword()
    {
        return password.get();
    }

    /**
     * Gets the name of the student
     * @return the name
     */
    public String getName()
    {
        return name.get();
    }
    
    /**
     * Sets the students class id
     * @param classID 
     */
    public void setclassID(int classID)
    {
        this.classID.set(classID);
    }
    
    /**
     * Gets the current class id for the student
     * @return the class id of the student
     */
    public int getclassID()
    {
        return classID.get();
    }

    /**
     * Sets the students username
     * @param username 
     */
    public void setUsername(StringProperty username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password.set(password);
    }

    /**
     * Prints all the cariables of the student
     * @return all the relevant info for the student
     */
    @Override
    public String toString()
    {
        return username.get() + "  " + name.get() + "  " + password.get() + "  " + absenceProcent.get() + "  " + dayMostAbsent.get() + "  " + classID.get(); 
    }

    
    

    
}
    
    
