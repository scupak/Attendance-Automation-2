/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SKRUMM
 */
public abstract class Person
{
    public StringProperty name;
    public StringProperty username;
    public StringProperty password;

    /**
     * This defines what a person is, to the program
     * @param name
     * @param username
     * @param password 
     */
    public Person(String name, String username, String password)
    {
        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }
    
    
}
