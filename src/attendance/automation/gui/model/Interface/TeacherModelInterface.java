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


/**
 *
 * @author kacpe
 */
public interface TeacherModelInterface 
{
   
    public void setCurrentTeacher(Teacher currentTeacher) throws AttendanceAutomationDalException;
    
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException;

    public ObservableList<Class> classList(String username);
    
}
