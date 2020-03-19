/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BLL.Interface.TeacherManagerInterface;
import attendance.automation.BLL.Interface.StudentManagerInterface;
import attendance.automation.BLL.Interface.BLLFacadeInterface;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author kacpe
 */
public class BLLFacade implements BLLFacadeInterface
{

    
    
    StudentManagerInterface studentmanager;
    TeacherManagerInterface teachermanager;
    
    public BLLFacade() throws IOException
    {
        studentmanager = new StudentManager();
        teachermanager = new TeacherManager(); 
    }
    
    @Override
    public String getUsernameStudent() 
    {
        return studentmanager.getUsernameStudent();
    }

    @Override
    public String getPasswordStudent() 
    {
       return studentmanager.getPasswordStudent();
    }

    @Override
    public ObservableList<Student> getTeacherStudentList() 
    {
        return teachermanager.getTeacherStudentList();
    }

    @Override
    public ObservableList getTeacherClassList() 
    {
       return teachermanager.getTeacherClassList();
    }

    @Override
    public String getUsernameTeacher() 
    {
        return teachermanager.getUsernameTeacher();
    }

    @Override
    public String getPasswordTeacher() 
    {
       return teachermanager.getPasswordTeacher();
    }

    
    public int checkDay(String username) throws AttendanceAutomationDalException
    {
        return studentmanager.checkDay(username);
    }
    
    @Override
    public boolean sendUpdateDayStudent(StudentDay sd)
    {
        return studentmanager.sendUpdateDayStudent(sd);
    }
    
    @Override
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException
    {
        return studentmanager.checkCredStudent(s);
    }
    
    @Override
     public Student getStudent(Student s) throws AttendanceAutomationDalException
     {
         return studentmanager.getStudent(s);
     }

    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException
    {
        studentmanager.setDayStatus(status, username);
    }
    

    @Override
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException {
       return studentmanager.getAllDaysForAstudent(student);
    }
}
