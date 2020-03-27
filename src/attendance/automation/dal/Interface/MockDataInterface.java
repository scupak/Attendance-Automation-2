/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.Interface;

import javafx.collections.ObservableList;

/**
 *
 * @author SKRUMM
 */
public interface MockDataInterface 
{
    
    /**
     * Gets the students username
     * @return the username of the student
     */
    public String getUsernameStudent();
  
    /**
     * Sets the students username to the current one
     * @param usernameStudent 
     */
    public void setUsernameStudent(String usernameStudent);
    
    /**
     * Gets the students password
     * @return the password of the current student
     */
    public String getPasswordStudent();
    
    /**
     * Sets the password of the student to the current one
     * @param passwordStudent 
     */
    public void setPasswordStudent(String passwordStudent);
    
    /**
     * Gets the teachers username
     * @return the username of the current teacher
     */
    public String getUsernameTeacher();
    
    /**
     * Sets the username of the teacher to the current one
     * @param usernameTeacher 
     */
    public void setUsernameTeacher(String usernameTeacher);
    
    /**
     * Gets the teachers username
     * @return the username of the teacher
     */
    public String getPasswordTeacher();
    
    /**
     * Sets the password of the teacher to the current one
     * @param passwordTeacher 
     */
    public void setPasswordTeacher(String passwordTeacher);
    
    
    /**
     * Gets a list of all the classes that a teacher has
     * @return a list of all the teachers classes
     */
    public ObservableList teacherClassList();
}
