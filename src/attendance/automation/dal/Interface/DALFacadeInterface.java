/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.Interface;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author kacpe
 */
public interface DALFacadeInterface {
    
    public List<Student> getAllStudents() throws AttendanceAutomationDalException;
    
    public Student getStudent(Student s) throws AttendanceAutomationDalException;
    
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException;
    
    public String getUsernameStudent();
  
    public void setUsernameStudent(String usernameStudent);
    
    public String getPasswordStudent();
    
    public void setPasswordStudent(String passwordStudent);
    
    public String getUsernameTeacher();
    
    public void setUsernameTeacher(String usernameTeacher);
    
    public String getPasswordTeacher();
    
    public void setPasswordTeacher(String passwordTeacher);
    
    public ObservableList<Student> teacherStudentList();
    
    public ObservableList teacherClassList();
    
    public boolean sendUpdateDayStudent(StudentDay sd);
}
