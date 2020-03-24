/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

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

    public ArrayList<String> getClassID()
    {
        return classID;
    }

    public void setClassID(ArrayList<String> classID)
    {
        this.classID = classID;
    }
    
   
    public StringProperty getName()
    {
        return name;
    }

    public void setName(StringProperty name)
    {
        this.name = name;
    }

    public StringProperty getUsername()
    {
        return username;
    }

    public void setUsername(StringProperty username)
    {
        this.username = username;
    }

    public StringProperty getPassword()
    {
        return password;
    }

    public void setPassword(StringProperty password)
    {
        this.password = password;
    }
    
    
    
}
