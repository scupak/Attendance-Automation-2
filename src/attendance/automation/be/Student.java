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
   
    public Student(StringProperty name, StringProperty username, StringProperty password, int absenceProcent, String dayMostAbsent)
    {
        super(name, username, password);
        this.absenceProcent = new SimpleIntegerProperty(absenceProcent);
        this.dayMostAbsent = new SimpleStringProperty(dayMostAbsent);
    }

    public IntegerProperty getAbsenceProcent()
    {
        return absenceProcent;
    }

    public void setAbsenceProcent(IntegerProperty absenceProcent)
    {
        this.absenceProcent = absenceProcent;
    }

    public StringProperty getDayMostAbsent()
    {
        return dayMostAbsent;
    }

    public void setDayMostAbsent(StringProperty dayMostAbsent)
    {
        this.dayMostAbsent = dayMostAbsent;
    }

    public StringProperty getUsername()
    {
        return username;
    }

    public StringProperty getPassword()
    {
        return password;
    }

    public StringProperty getName()
    {
        return name;
    }

    
    

    
}
    
    
