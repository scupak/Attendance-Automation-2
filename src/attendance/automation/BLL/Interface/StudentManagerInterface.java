/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL.Interface;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kacpe
 */
public interface StudentManagerInterface 
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

    public int checkDay(String username) throws AttendanceAutomationDalException;
    public boolean sendUpdateDayStudent(StudentDay sd);
    
    
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException;
    
    public Student getStudent(Student s) throws AttendanceAutomationDalException;

    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException; 
    
     public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException;
}
