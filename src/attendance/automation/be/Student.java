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
public class Student
{

    private StringProperty name;
    private IntegerProperty id;
    private IntegerProperty absenceProcent;
    private StringProperty dayMostAbsent;
    public StringProperty username;
    public StringProperty password;

    public Student(String name, int id, String username, String password)
    {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);

    }

    public Student(String name, int absenceProcent, String dayMostAbsent)
    {
        this.name = new SimpleStringProperty(name);
        this.absenceProcent = new SimpleIntegerProperty(absenceProcent);
        this.dayMostAbsent = new SimpleStringProperty(dayMostAbsent);
    }

    /**
     * get student username
     *
     * @return username
     */
    public String getUsername()
    {
        return this.username.get();
    }

    /**
     * Set student username
     *
     * @param username
     */
    public void setUsername(StringProperty username)
    {
        this.username = username;
    }

    /**
     * Get student password
     *
     * @return
     */
    public String getPassword()
    {
        return this.password.get();
    }

    /**
     * Set student password
     *
     * @param password
     */
    public void setpassword(StringProperty password)
    {
        this.password = password;
    }

    /**
     * Get student absences
     *
     * @return absenceProcent
     */
    public Integer getAbsenceProcent()
    {
        return this.absenceProcent.get();
    }

    /**
     * Set Student absence
     *
     * @param absenceProcent
     */
    public void setAbsenceProcent(Integer absenceProcent)
    {
        this.absenceProcent = new SimpleIntegerProperty(absenceProcent);
    }

    /**
     * Gets the day a student is most absent
     *
     * @return dayMostAbsent
     */
    public String getDayMostAbsent()
    {
        return this.dayMostAbsent.get();
    }

    /**
     * Sets the day a student is most absent
     *
     * @param dayMostAbsent
     */
    public void setDayMostAbsent(String dayMostAbsent)
    {
        this.dayMostAbsent = new SimpleStringProperty(dayMostAbsent);
    }

    /**
     * Get student name
     *
     * @return name
     */
    public String getName()
    {
        return this.name.get();
    }

    /**
     * Set Student name
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = new SimpleStringProperty(name);
    }

    /**
     * Get student id
     *
     * @return id
     */
    public Integer getId()
    {
        return this.id.get();
    }

    /**
     * Set student id
     *
     * @param id
     */
    public void setId(IntegerProperty id)
    {
        this.id = id;
    }

}
