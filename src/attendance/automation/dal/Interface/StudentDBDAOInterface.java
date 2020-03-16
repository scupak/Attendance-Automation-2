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

/**
 *
 * @author kacpe
 */
public interface StudentDBDAOInterface 
{
    
    public List<Student> getAllStudents() throws AttendanceAutomationDalException;
    
    public Student getStudent(Student s) throws AttendanceAutomationDalException;
    
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException;

    public boolean sendUpdateDayStudent(StudentDay sd);
}
