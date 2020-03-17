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
import java.io.IOException;
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

    public boolean checkDay()
    {
        return studentmanager.checkDay();
    }
    
}
