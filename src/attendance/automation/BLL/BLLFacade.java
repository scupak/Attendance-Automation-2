/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BLL.Interface.TeacherManagerInterface;
import attendance.automation.BLL.Interface.StudentManagerInterface;
import attendance.automation.BLL.Interface.BLLFacadeInterface;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.dal.DALFacade;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author SKRUMM
 */
public class BLLFacade implements BLLFacadeInterface
{

    
    
    StudentManagerInterface studentmanager;
    TeacherManagerInterface teachermanager;
    
    /**
     * Makes a new BLLFacade, that defines a new instance of StudentManager and TeacherManager
     * @throws IOException 
     */
    public BLLFacade() throws IOException
    {
        studentmanager = new StudentManager(new DALFacade());
        teachermanager = new TeacherManager(); 
    }
    
    /**
     * Gets the username of the selectted student
     * @return the students username
     */
    @Override
    public String getUsernameStudent() 
    {
        return studentmanager.getUsernameStudent();
    }

    /**
     * Gets the password of the student
     * @return the students password
     */
    @Override
    public String getPasswordStudent() 
    {
       return studentmanager.getPasswordStudent();
    }

    /**
     * Gets a list of the teachers students
     * @return a list of the teachers students
     */
    @Override
    public ObservableList<Student> getTeacherStudentList() 
    {
        return teachermanager.getTeacherStudentList();
    }
    
    /**
    * Gets a teachers classes as a list
    * @return the teachers classes as a list
    */
    @Override
    public ObservableList getTeacherClassList() 
    {
       return teachermanager.getTeacherClassList();
    }

    /**
     * Gets the username of the selected teacher
     * @return the username of the teacher
     */
    @Override
    public String getUsernameTeacher() 
    {
        return teachermanager.getUsernameTeacher();
    }

    /**
     * Gets the selected teachers password
     * @return the password of the teacher
     */
    @Override
    public String getPasswordTeacher() 
    {
       return teachermanager.getPasswordTeacher();
    }

    
    /**
     * Checks the current day for the selected user
     * @param username
     * @return the current day for the username
     * @throws AttendanceAutomationDalException 
     */
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        return studentmanager.checkCurrentDay(username);
    }
    
    /**
     * Sends an update from DayStudent between layers
     * @param sd
     * @return StudentDay
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException
    {
        return studentmanager.sendUpdateDayStudent(sd);
    }
    
    /**
     * Checks the students credentials
     * @param s
     * @return if the credentials are accurate
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException
    {
        return studentmanager.checkCredStudent(s);
    }
    
    /**
     * Gets the selected student
     * @param s
     * @return the student
     * @throws AttendanceAutomationDalException 
     */
    @Override
     public Student getStudent(Student s) throws AttendanceAutomationDalException
     {
         return studentmanager.getStudent(s);
     }

    /**
     * Sets the status of the day
     * @param status
     * @param username
     * @throws AttendanceAutomationDalException 
     */
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException
    {
        studentmanager.setDayStatus(status, username);
    }
    

    /**
     * Gets a list of all the days for a speciific student
     * @param student
     * @return the list of days for the stuent
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException {
       return studentmanager.getAllDaysForAstudent(student);
    }

    /**
     * Gets all students as alist
     * @return a list of all students
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public List<Student> getallStudents() throws AttendanceAutomationDalException {
       return  studentmanager.getallStudents();
    }

    /**
     * Checks if the student day exists
     * @param username
     * @param date
     * @return if the student day exists in the DB
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException{
        return studentmanager.doesStudentDayExist(username, date);
    }
    
    /**
     * Gets the student day
     * @param s
     * @param date
     * @return the student day
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public StudentDay getStudentDay(Student s, LocalDate date) throws AttendanceAutomationDalException {
       return studentmanager.getStudentDay(s,date);
    }
    
    /**
     * sets the pie chart
     * @param s
     * @param attendanceStatusCheck
     * @return
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public double pieChartData(Student s, int attendanceStatusCheck) throws AttendanceAutomationDalException
    {
        return studentmanager.pieChartData(s, attendanceStatusCheck);
    }
   
    /**
     * sets a bar chart
     * @param s
     * @param attendanceStatusCheck
     * @param columName
     * @return
     * @throws AttendanceAutomationDalException 
     */
     public XYChart.Series setPresence(Student s , int attendanceStatusCheck, String columName) throws AttendanceAutomationDalException
     {
         return studentmanager.setPresence(s ,attendanceStatusCheck, columName);

     }
     
    
}
