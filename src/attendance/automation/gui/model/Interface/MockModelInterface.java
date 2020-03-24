/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model.Interface;

import attendance.automation.be.Student;
import javafx.collections.ObservableList;

/**
 *
 * @author kacpe
 */
public interface MockModelInterface 
{
    /**
     * Gets the list of teachers
     *
     * @return the list of teachers
     */
    public ObservableList classList();
    
    /**
     * Gets the list of students
     *
     * @return the list of students
     */
    public ObservableList<Student> studentList();
    
    /**
     * updates existing Data-Object if name matches
     */
    public void addData();
    
    
     /**
     * Gets teacher username
     *
     * @return the teachers username
     */
    public String getTeahcerUsername();
    
    
    /**
     * Gets teacher password
     *
     * @return the teachers password
     */
    public String getTeacherPassword();
    
    
    /**
     * Gets student username
     *
     * @return the students user name
     */
    public String getStudentUsername();
    
    
    /**
     * Gets student password
     *
     * @return the students password
     */
    public String getStudentPassword();
}
