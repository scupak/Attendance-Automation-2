/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;
import attendance.automation.BLL.Interface.TeacherManagerInterface;
import attendance.automation.be.Student;
import attendance.automation.be.Teacher;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.dal.DALFacade;
import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;
import attendance.automation.be.Class;
/**
 *
 * @author SKRUMM
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
    public List<Student> getTeacherStudentList(int classid) throws AttendanceAutomationDalException
    {
        return dalfacade.teacherStudentList(classid);
    }
    
    /**
     * gets all the teachers in the database
     * @return teachers
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException
    {
        return dalfacade.getAllTeachers();
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
    
    /**
     * Checks the teacher credentials and compares them to the given info, to see if it matches
     * @param t
     * @return if the info matches the info in the DB
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        Teacher rt;
        
        rt = dalfacade.getTeacher(t);
        if(rt == null)
        {
            return false;
        }
        if(rt.getUsername().equals(t.getUsername()))
        {
            if(rt.getPassword().equals(t.getPassword()))
            {
                System.out.println("true");
                System.out.println(rt.getUsername());
                System.out.println(rt.getPassword());
                return true;
            }
        }
        System.out.println(rt.getPassword());
        System.out.println(rt.getUsername());
        System.out.println("false");
        return false;
    }
    
    /**
     * Gets a teacher
     * @param t
     * @return a teacher
     * @throws AttendanceAutomationDalException
     */
    @Override
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        return dalfacade.getTeacher(t);
    }
    
    @Override
    public ObservableList<Class> getTeacherClasses(String username)
    {
        return dalfacade.getTeacherClasses(username);
    }
}
