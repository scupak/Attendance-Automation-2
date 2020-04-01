/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BLL.Interface.TeacherManagerInterface;
import attendance.automation.BLL.Interface.StudentManagerInterface;
import attendance.automation.BLL.Interface.BLLFacadeInterface;
import attendance.automation.BLL.Security.SecurityManager;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.be.Teacher;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.dal.DALFacade;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import attendance.automation.be.Class;
import attendance.automation.dal.DALFacadeFactory;
import attendance.automation.dal.Interface.DALFacadeInterface;

/**
 *
 * @author SKRUMM
 */
public class BLLFacade implements BLLFacadeInterface
{

    
    
    StudentManagerInterface studentmanager;
    TeacherManagerInterface teachermanager;
    SecurityManager securityManager;
   
    private DALFacadeInterface dalfacade;
    
    /**
     * Makes a new BLLFacade, that defines a new instance of StudentManager and TeacherManager
     * @throws IOException 
     */
    public BLLFacade() throws IOException, Exception
    {
        dalfacade = DALFacadeFactory.CreateDALFacade(DALFacadeFactory.DALFacadeTypes.PRODUCTION);
        studentmanager = new StudentManager(dalfacade);
        teachermanager = new TeacherManager(); 
        securityManager = new SecurityManager();
        
    }

    /**
     * Gets a list of the teachers students
     * @return a list of the teachers students
     */
    @Override
    public List<Student> getTeacherStudentList(int classid) throws AttendanceAutomationDalException 
    {
        return teachermanager.getTeacherStudentList(classid);
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
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student currentStudent, LocalDate date, LocalDate date0)  throws AttendanceAutomationDalException {
       return studentmanager.getAllDaysForAstudent(currentStudent, date, date0);
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

    @Override
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        return teachermanager.checkCredTeacher(t);
    }

    @Override
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        return teachermanager.getTeacher(t);
    }
    
    @Override
    public ObservableList<Class> getTeacherClasses(String username) throws AttendanceAutomationDalException
    {
        return teachermanager.getTeacherClasses(username);
    }

    @Override
    public void updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException {
        
        studentmanager.updateStudentabsenceProcent(currentStudent, absenceProcentforstudent);
    }
     
    
     @Override
    public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException
    {
      return teachermanager.getAllTeachers();
    }
    
     public String hashPassword(String password)
     {
        return securityManager.hashPassword(password);
         
     }

    @Override
    public String getmostabsentdayforstudent(Student currentStudent) throws AttendanceAutomationDalException {
        return studentmanager.getmostabsentdayforstudent(currentStudent);
    }

    @Override
    public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException {
       return studentmanager.updateStudentMostAbsentDay(currentStudent, mostabsentdayforstudent);
    }

    

   
}
