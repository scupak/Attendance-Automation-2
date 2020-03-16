/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL.Interface;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import javafx.collections.ObservableList;

/**
 *
 * @author kacpe
 */
public interface BLLFacadeInterface 
{
    
    /**
     * Get student username
     *
     * @return getUsernameStudent
     */
    public String getUsernameStudent(); 
    
    /**
     * Get student password
     *
     * @return getPasswordStudent
     */
    public String getPasswordStudent();
    
    /**
     * Gets the ObservableList of students
     *
     * @return teacherStudentList
     */
    public ObservableList<Student> getTeacherStudentList();
    
     /**
     * get the ObservableList of classes
     *
     * @return teacherClassList
     */
    public ObservableList getTeacherClassList();
    
    /**
     * Get teacher username
     *
     * @return getUsernameTeacher
     */
    public String getUsernameTeacher();
    
    /**
     * Get teacher password
     *
     * @return getPasswordTeacher
     */
    public String getPasswordTeacher();
    
    public boolean sendUpdateDayStudent(StudentDay sd);
    
}
