/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;
import attendance.automation.BLL.Interface.StudentManagerInterface;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.dal.DALFacade;
import java.io.IOException;
import java.util.List;

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
    
     
     /**
     * Get student username
     *
     * @return getUsernameStudent
     */
    @Override
    public String getUsernameStudent()
    {
        return dalfacade.getUsernameStudent();
    }

    /**
     * Get student password
     *
     * @return getPasswordStudent
     */
    @Override
    public String getPasswordStudent()
    {
        return dalfacade.getPasswordStudent();
    }
    
    public List<Student> getallStudents() throws AttendanceAutomationDalException
    {
        return dalfacade.getAllStudents();
        
    }
    
    @Override
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException
    {
        Student rs;
        
        rs = dalfacade.getStudent(s);
        if(rs == null)
        {
            return false;
        }
        if(rs.getUsername().equals(s.getUsername()))
        {
            if(rs.getPassword().equals(s.getPassword()))
            {
                System.out.println("true");
                System.out.println(rs.getUsername());
                System.out.println(rs.getPassword());
                return true;
            }
        }
        System.out.println(rs.getPassword());
        System.out.println(rs.getUsername());
        System.out.println("false");
        return false;
    }
    
    /**
     *
     * @param s
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public Student getStudent(Student s) throws AttendanceAutomationDalException
    {
        return dalfacade.getStudent(s);
    }
    
    public static void main(String[] args) throws IOException, AttendanceAutomationDalException
    {
        StudentManager test = new StudentManager();
        Student se = new Student("djkghsl", "rwebleya", "MckxbMH", 0, "monday", 0);
       
        System.out.println(se.getPassword());
        System.out.println(se.getUsername());
        
        test.checkCredStudent(se);
    }
        
        
    

    @Override
    public boolean sendUpdateDayStudent(StudentDay sd) {
        return dalfacade.sendUpdateDayStudent(sd);
    }
}
