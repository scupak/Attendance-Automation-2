/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL.Interface;

import attendance.automation.be.Student;
import attendance.automation.be.Teacher;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.util.List;
import javafx.collections.ObservableList;
import attendance.automation.be.Class;

/**
 *
 * @author SKRUMM
 */
public interface TeacherManagerInterface 
{
    
    /**
     * Gets the ObservableList of students
     *
     * @return teacherStudentList
     */
    public List<Student> getTeacherStudentList() throws AttendanceAutomationDalException;
    
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
    
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException;
    
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException;
    
    public ObservableList<Class> getTeacherClasses(String username);
           
}
