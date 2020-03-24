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

    public ArrayList<String> getClassID()
    {
        return classID;
    }

    public void setClassID(ArrayList<String> classID)
    {
        this.classID = classID;
    }
    
   
    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public String getUsername()
    {
        return username.get();
    }

    public void setUsername(String username)
    {
        this.username.set(username);
    }

    public String getPassword()
    {
        return password.get();
    }

    public void setPassword(String password)
    {
        this.password.set(password);
    }
    
    
    
}
