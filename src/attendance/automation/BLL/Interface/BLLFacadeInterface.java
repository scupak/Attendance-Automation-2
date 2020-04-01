/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL.Interface;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.time.LocalDate;
import java.util.List;




/**
 *
 * @author SKRUMM
 */
public interface BLLFacadeInterface extends StudentManagerInterface, TeacherManagerInterface
{
    public String hashPassword(String password);

   

  

    
   
}
