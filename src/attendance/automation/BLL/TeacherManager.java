/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;
import attendance.automation.BLL.Interface.TeacherManagerInterface;
import attendance.automation.be.Student;
import attendance.automation.dal.MockData;
import javafx.collections.ObservableList;
/**
 *
 * @author kacpe
 */
public class TeacherManager implements TeacherManagerInterface
{
    
    private final MockData md;
    
    public TeacherManager()
    {
        md = new MockData();
        
       

    }
    
     /**
     * Gets the ObservableList of students
     *
     * @return teacherStudentList
     */
    public ObservableList<Student> getTeacherStudentList()
    {
        return md.teacherStudentList();
    }

    /**
     * get the ObservableList of classes
     *
     * @return teacherClassList
     */
    public ObservableList getTeacherClassList()
    {
        return md.teacherClassList();
    }
    
    /**
     * Get teacher username
     *
     * @return getUsernameTeacher
     */
    public String getUsernameTeacher()
    {
        return md.getUsernameTeacher();
    }

    /**
     * Get teacher password
     *
     * @return getPasswordTeacher
     */
    public String getPasswordTeacher()
    {
        return md.getPasswordTeacher();
    }
}
