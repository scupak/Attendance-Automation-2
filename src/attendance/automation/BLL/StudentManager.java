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
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author SKRUMM
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

    /**
     * Checks the current day according to the given username
     * @param username
     * @return the current day, and relevant info
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        return dalfacade.checkCurrentDay(username);
    }
    
    /**
     * Gets a list of all students
     * @return a list of all students
     * @throws AttendanceAutomationDalException 
     */
    public List<Student> getallStudents() throws AttendanceAutomationDalException
    {
        return dalfacade.getAllStudents();
    }
    
    /**
     * Checks the students credentials and compares them to the given info, to see if it matches
     * @param s
     * @return if the info matches the info in the DB
     * @throws AttendanceAutomationDalException 
     */
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
     * Gets a student
     * @param s
     * @return a student
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
        
        
    
    /**
     * Sends an update to DayStudent between layers
     * @param sd
     * @return the update
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException {
        return dalfacade.sendUpdateDayStudent(sd);
    }

    /**
     * Sets the day status for the given user
     * @param status
     * @param username
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException
    {
        dalfacade.setDayStatus(status, username);
    }
    
    /**
     * Gets a list of all days for the current student
     * @param student
     * @return a list of days for the current student
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException {
        return dalfacade.getAllDaysForStudent(student);
    }

    /**
     * Checks wether or not the given student exists in the DB
     * @param username
     * @param date
     * @return wether or not the given student exists
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException{
        return dalfacade.doesStudentDayExist(username, date);
    }
    
    /**
     * Gets the students day
     * @param s
     * @param date
     * @return the students day
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public StudentDay getStudentDay(Student s, LocalDate date) throws AttendanceAutomationDalException {
        return dalfacade.getStudentDay(s,date);
    }

}
