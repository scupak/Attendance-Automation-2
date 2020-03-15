/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;
import attendance.automation.BLL.Interface.StudentManagerInterface;
import attendance.automation.dal.MockData;
/**
 *
 * @author kacpe
 */
public class StudentManager implements StudentManagerInterface
{
   
    private final MockData md;
    
    public StudentManager()
    {
        md = new MockData();
        
    }
    
     /**
     * Get student username
     *
     * @return getUsernameStudent
     */
    public String getUsernameStudent()
    {
        return md.getUsernameStudent();
    }

    /**
     * Get student password
     *
     * @return getPasswordStudent
     */
    public String getPasswordStudent()
    {
        return md.getPasswordStudent();
    }
}
