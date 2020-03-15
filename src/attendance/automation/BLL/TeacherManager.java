/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;
import attendance.automation.BLL.Interface.TeacherManagerInterface;
import attendance.automation.be.Student;
import attendance.automation.dal.DALFacade;
import java.io.IOException;
import javafx.collections.ObservableList;
/**
 *
 * @author kacpe
 */
public class TeacherManager implements TeacherManagerInterface
{
    
    private final DALFacade dalfacade;
    
    public TeacherManager() throws IOException
    {
        dalfacade = new DALFacade();
        
       

    }
    
     /**
     * Gets the ObservableList of students
     *
     * @return teacherStudentList
     */
    public ObservableList<Student> getTeacherStudentList()
    {
        return dalfacade.teacherStudentList();
    }

    /**
     * get the ObservableList of classes
     *
     * @return teacherClassList
     */
    public ObservableList getTeacherClassList()
    {
        return dalfacade.teacherClassList();
    }
    
    /**
     * Get teacher username
     *
     * @return getUsernameTeacher
     */
    public String getUsernameTeacher()
    {
        return dalfacade.getUsernameTeacher();
    }

    /**
     * Get teacher password
     *
     * @return getPasswordTeacher
     */
    public String getPasswordTeacher()
    {
        return dalfacade.getPasswordTeacher();
    }
}
