/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import java.util.ArrayList;

/**
 *
 * @author lumby
 */
public class Teacher extends Person
{
    private ArrayList<String> classID;

    
    public Teacher(String name, String username, String password, ArrayList classID)
    {
        super(name, username, password);
        this.classID = new ArrayList<>();
    }
    
    public Teacher(String name, String username, String password)
    {
        super(name, username, password);
        this.classID = new ArrayList<>();
    }

    /**
     * gets list of classID's
     * @return classID
     */
    public ArrayList<String> getClassID()
    {
        return classID;
    }

    /**
     * sets the list of classID's
     * @param classID 
     */
    public void setClassID(ArrayList<String> classID)
    {
        this.classID = classID;
    }
    
   /**
    * gets name of teacher
    * @return name
    */
    public String getName()
    {
        return name.get();
    }

    /**
     * sets name of teacher
     * @param name 
     */
    public void setName(String name)
    {
        this.name.set(name);
    }

    /**
     * gets username of teacher
     * @return username
     */
    public String getUsername()
    {
        return username.get();
    }

    /**
     * sets username of teacher
     * @param username 
     */
    public void setUsername(String username)
    {
        this.username.set(username);
    }

    /**
     * gets the password of teacher
     * @return password
     */
    public String getPassword()
    {
        return password.get();
    }

    /**
     * sets the password of teacher
     * @param password 
     */
    public void setPassword(String password)
    {
        this.password.set(password);
    }
    
    
    
}
