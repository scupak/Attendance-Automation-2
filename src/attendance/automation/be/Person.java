/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Christina
 */
public abstract class Person
{
    public StringProperty name;
    public IntegerProperty id;
//    private StringProperty email;
//    private IntegerProperty phoneNumber;
    public StringProperty username;
    public StringProperty password;

    
    public Person(StringProperty name, IntegerProperty id, StringProperty username, StringProperty password)
    {
        this.name = name;
        this.id = id;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }
    
    
}
