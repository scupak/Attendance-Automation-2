/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.BLL.BLLFacade;
import attendance.automation.BLL.Interface.BLLFacadeInterface;
import attendance.automation.be.Teacher;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.model.Interface.TeacherModelInterface;
import java.io.IOException;

/**
 *
 * @author kacpe
 */
public class TeacherModel implements TeacherModelInterface
{
    private BLLFacadeInterface bllfacade;
    private Teacher currentTeacher;
    
    public TeacherModel() throws IOException
    {
        bllfacade = new BLLFacade();
    }

    public Teacher getCurrentTeacher()
    {
        return currentTeacher;
    }

    public void setCurrentTeacher(Teacher currentTeacher) throws AttendanceAutomationDalException
    {
        this.currentTeacher = bllfacade.getTeacher(currentTeacher);
    }
    
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException{
        return bllfacade.checkCredTeacher(t);
    }
            
    
    
            
}
