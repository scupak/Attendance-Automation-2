/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model.Interface;

import attendance.automation.be.Teacher;
import attendance.automation.dal.AttendanceAutomationDalException;
import javafx.collections.ObservableList;
import attendance.automation.be.Class;
import attendance.automation.be.Student;
import java.util.List;


/**
 *
 * @author kacpe
 */
public interface TeacherModelInterface 
{
    public Teacher getCurrentTeacher();
    public void setCurrentTeacher(Teacher currentTeacher) throws AttendanceAutomationDalException;
    
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException;

    public ObservableList<Class> classList(String username) throws AttendanceAutomationDalException;
    
     public ObservableList<Student> teacherStudentList(int classid) throws AttendanceAutomationDalException;
     
      public Class getCurrentClass();
       public void setCurrentClass(Class currentClass);
    
}
