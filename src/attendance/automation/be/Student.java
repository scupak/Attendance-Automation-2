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
 * @author Charlotte
 */
public class Student extends Person
{
    private IntegerProperty absenceProcent;
    private StringProperty dayMostAbsent;
    private IntegerProperty classID;
   
    public Student(String name, String username, String password, int absenceProcent, String dayMostAbsent, int classID)
    {
        super(name, username, password);
        this.absenceProcent = new SimpleIntegerProperty(absenceProcent);
        this.dayMostAbsent = new SimpleStringProperty(dayMostAbsent);
        this.classID = new SimpleIntegerProperty(classID);
    }

    public int getAbsenceProcent()
    {
        return absenceProcent.get();
    }

    public void setAbsenceProcent(int absenceProcent)
    {
        this.absenceProcent.set(absenceProcent);
    }

    public String getDayMostAbsent()
    {
        return dayMostAbsent.get();
    }

    public void setDayMostAbsent(String dayMostAbsent)
    {
        this.dayMostAbsent.set(dayMostAbsent);
    }

    public String getUsername()
    {
        return username.get();
    }

    public String getPassword()
    {
        return password.get();
    }

    public String getName()
    {
        return name.get();
    }
    
    public void setclassID(int classID)
    {
        this.classID.set(classID);
    }
    
    public int getclassID()
    {
        return classID.get();
    }

    @Override
    public String toString()
    {
        return username.get() + "  " + name.get() + "  " + password.get() + "  " + absenceProcent.get() + "  " + dayMostAbsent.get() + "  " + classID.get(); 
    }

    
    

    
}
    
    
