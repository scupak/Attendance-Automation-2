/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.Interface;

import attendance.automation.be.Teacher;
import attendance.automation.be.Student;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author lumby
 */
public interface TeacherDBDAOInterface
{
    
    public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException;
    
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException;
     
    public boolean TeacherExist(Teacher t) throws AttendanceAutomationDalException;
    
    public ObservableList<attendance.automation.be.Class> getTeacherClasses(String username);
    
     public List<Student> teacherStudentList(int classid) throws AttendanceAutomationDalException;
    
}
