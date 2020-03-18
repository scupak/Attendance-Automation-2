/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.Interface.DALFacadeInterface;
import attendance.automation.dal.Interface.MockDataInterface;
import attendance.automation.dal.Interface.StudentDBDAOInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author kacpe
 */
public class DALFacade implements DALFacadeInterface
{

    
    
    StudentDBDAOInterface studentdbdao;
    MockDataInterface mockdata;
    
    public DALFacade() throws IOException
    {
        studentdbdao = new StudentDBDAO();
        mockdata = new MockData();
    }
    
    
    @Override
    public List<Student> getAllStudents() throws AttendanceAutomationDalException 
    {
        return studentdbdao.getAllStudents();
    }

    @Override
    public Student getStudent(Student s) throws AttendanceAutomationDalException 
    {
        return studentdbdao.getStudent(s);
    }

    @Override
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException 
    {
        return studentdbdao.StudentExist(s);
    }

    @Override
    public String getUsernameStudent() 
    {
       return mockdata.getUsernameStudent();
    }

    @Override
    public void setUsernameStudent(String usernameStudent) 
    {
         mockdata.setUsernameStudent(usernameStudent);
    }

    @Override
    public String getPasswordStudent() 
    {
        return mockdata.getPasswordStudent();
    }

    @Override
    public void setPasswordStudent(String passwordStudent) 
    {
        mockdata.setPasswordStudent(passwordStudent);
    }

    @Override
    public String getUsernameTeacher() 
    {
        return mockdata.getUsernameTeacher();
    }

    @Override
    public void setUsernameTeacher(String usernameTeacher) 
    {
        mockdata.setUsernameTeacher(usernameTeacher);
    }

    @Override
    public String getPasswordTeacher() 
    {
        return mockdata.getPasswordTeacher();
    }

    @Override
    public void setPasswordTeacher(String passwordTeacher) 
    {
       mockdata.setPasswordTeacher(passwordTeacher);
    }

    @Override
    public ObservableList<Student> teacherStudentList()
    {
        return mockdata.teacherStudentList();
    }

    @Override
    public ObservableList teacherClassList() 
    {
        return mockdata.teacherClassList();
    }

    
    public boolean checkDay(String username) throws SQLException
    {
        return studentdbdao.checkDay(username);
    }
    
    @Override
    public boolean sendUpdateDayStudent(StudentDay sd)
    {
        return studentdbdao.sendUpdateDayStudent(sd);
    }

    public void setDayStatus(int status, String username) throws SQLException
    {
        
        studentdbdao.setDayStatus(status, username);
    }
    
}
