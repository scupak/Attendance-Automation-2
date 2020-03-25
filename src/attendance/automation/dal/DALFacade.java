/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.be.Teacher;
import attendance.automation.dal.Interface.DALFacadeInterface;
import attendance.automation.dal.Interface.MockDataInterface;
import attendance.automation.dal.Interface.StudentDBDAOInterface;
import attendance.automation.dal.Interface.TeacherDBDAOInterface;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author SKRUMM
 */
public class DALFacade implements DALFacadeInterface
{

    
    
    StudentDBDAOInterface studentdbdao;
    MockDataInterface mockdata;
    TeacherDBDAOInterface teacherdbdao;
    
    public DALFacade() throws IOException
    {
        studentdbdao = new StudentDBDAO();
        mockdata = new MockData();
        teacherdbdao = new TeacherDBDAO();
    }
    
    /**
     * Gets a list of all students in database
     * @return students list
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public List<Student> getAllStudents() throws AttendanceAutomationDalException 
    {
        return studentdbdao.getAllStudents();
    }

    /**
     * Get a specific student based on username
     * @param s
     * @return returnStudent
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public Student getStudent(Student s) throws AttendanceAutomationDalException 
    {
        return studentdbdao.getStudent(s);
    }

    /**
     * Checks if a student exists in the databases
     * @param s
     * @return boolean
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException 
    {
        return studentdbdao.StudentExist(s);
    }

    /**
     * Get student username
     *
     * @return usernameStudent
     */
    @Override
    public String getUsernameStudent() 
    {
       return mockdata.getUsernameStudent();
    }

    /**
     * Set student username
     *
     * @param usernameStudent
     */
    @Override
    public void setUsernameStudent(String usernameStudent) 
    {
         mockdata.setUsernameStudent(usernameStudent);
    }

    /**
     * Get student password
     *
     * @return the password
     */
    @Override
    public String getPasswordStudent() 
    {
        return mockdata.getPasswordStudent();
    }

    /**
     * Set student password
     *
     * @param passwordStudent
     */
    @Override
    public void setPasswordStudent(String passwordStudent) 
    {
        mockdata.setPasswordStudent(passwordStudent);
    }

    /**
     * Get teacher username
     *
     * @return usernameTeacher
     */
    @Override
    public String getUsernameTeacher() 
    {
        return mockdata.getUsernameTeacher();
    }

    /**
     * Set teacher username
     *
     * @param usernameTeacher
     */
    @Override
    public void setUsernameTeacher(String usernameTeacher) 
    {
        mockdata.setUsernameTeacher(usernameTeacher);
    }

    /**
     * Get teacher password
     *
     * @return passwordTeacher
     */
    @Override
    public String getPasswordTeacher() 
    {
        return mockdata.getPasswordTeacher();
    }

    /**
     * Sets teacher password
     *
     * @param passwordTeacher
     */
    @Override
    public void setPasswordTeacher(String passwordTeacher) 
    {
       mockdata.setPasswordTeacher(passwordTeacher);
    }

    /**
     * Create and add students to an ObservableLIst
     *
     * @return TeacherStudentList
     */
    @Override
    public List<Student> teacherStudentList() throws AttendanceAutomationDalException 
    {
        return studentdbdao.getAllStudents();
    }

    /**
     * Create and add classes to an ObservableList
     *
     * @return teacherClassList
     */
    @Override
    public ObservableList teacherClassList() 
    {
        return mockdata.teacherClassList();
    }

    /**
     * TODO make a method that chekcs if the studentDay exists
     * @param username
     * @return the current day
     * @throws AttendanceAutomationDalException 
     */
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        return studentdbdao.checkCurrentDay(username);
    }
    
    /**
     * Sends an update from DayStudent between layers
     * @param sd
     * @return boolean
     */
    @Override
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException
    {
        return studentdbdao.sendUpdateDayStudent(sd);
    }

    /**
     * Make it so this method only updates the day and does not make a new day
     * @param status
     * @param username 
     */
    @Override
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException
    {
        
        studentdbdao.setDayStatus(status, username);
    }
    
     /**
     * Get a list of days for a student
     * @param student
     * @return studentDays
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public List<StudentDay> getAllDaysForStudent(Student student) throws AttendanceAutomationDalException {
      return studentdbdao.getAllDaysForStudent(student);
    }

    /**
     * Checks if the student day exists
     * @param username
     * @param date
     * @return if the student day exists
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean doesStudentDayExist(String username, LocalDate date)throws AttendanceAutomationDalException {
        return studentdbdao.doesStudentDayExist(username, date);
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
       return studentdbdao.getStudentDay(s,date);
    }
    
    /**
     * gets a list of all teachers in database
     * @return
     * @throws AttendanceAutomationDalException 
     */
    public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException
    {
        return teacherdbdao.getAllTeachers();
    }
    
    /**
     * get a specific teacher based on username
     * @param t
     * @return
     * @throws AttendanceAutomationDalException 
     */
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        return teacherdbdao.getTeacher(t);
    }
    
    /**
     * checks if a Teacher exist in the databases
     * @param t
     * @return
     * @throws AttendanceAutomationDalException 
     */
    public boolean TeacherExist(Teacher t) throws AttendanceAutomationDalException
    {
        return teacherdbdao.TeacherExist(t);
    }
}
