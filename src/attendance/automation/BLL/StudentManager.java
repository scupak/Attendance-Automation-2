/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;
import attendance.automation.BLL.Interface.StudentManagerInterface;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.DALFacade;
import java.io.IOException;

/**
 *
 * @author kacpe
 */
public class StudentManager implements StudentManagerInterface
{

   
   
    private final DALFacade dalfacade;
    
    public StudentManager() throws IOException
    {
        dalfacade = new DALFacade();
        
    }
    
     public boolean sendUpdateDayStudent(StudentDay sd)
    {
        return dalfacade.sendUpdateDayStudent(sd);
    }
     /**
     * Get student username
     *
     * @return getUsernameStudent
     */
    public String getUsernameStudent()
    {
        return dalfacade.getUsernameStudent();
    }

    /**
     * Get student password
     *
     * @return getPasswordStudent
     */
    public String getPasswordStudent()
    {
        return dalfacade.getPasswordStudent();
    }
}
