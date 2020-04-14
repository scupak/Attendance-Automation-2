/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BLL.Interface.TeacherManagerInterface;
import attendance.automation.BLL.Interface.StudentManagerInterface;
import attendance.automation.BLL.Interface.BLLFacadeInterface;
import attendance.automation.BLL.Interface.SecurityManagerInterface;
import attendance.automation.BLL.Security.SecurityManager;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.be.Teacher;
import attendance.automation.dal.AttendanceAutomationDalException;
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
    SecurityManagerInterface securityManager;

    private DALFacadeInterface dalfacade;

    /**
     * Makes a new BLLFacade, that defines a new instance of StudentManager and
     * TeacherManager
     *
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
     *
     * @param classid
     * @return a list of the teachers students
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    @Override
    public List<Student> getTeacherStudentList(int classid) throws AttendanceAutomationDalException
    {
        return teachermanager.getTeacherStudentList(classid);
    }

    /**
     * Checks the current day for the selected user
     *
     * @param username
     * @return the current day for the username
     * @throws AttendanceAutomationDalException
     */
    @Override
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        return studentmanager.checkCurrentDay(username);
    }

    /**
     * Sends an update from DayStudent between layers
     *
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
     *
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
     *
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
     *
     * @param status
     * @param username
     * @throws AttendanceAutomationDalException
     */
    @Override
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException
    {
        studentmanager.setDayStatus(status, username);
    }

    /**
     * Gets a list of all the days for a specific student
     *
     * @param student
     * @return the list of days for the stuent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException
    {
        return studentmanager.getAllDaysForAstudent(student);
    }

    /**
     * gets list of days between two specific days for a student
     *
     * @param currentStudent
     * @param date
     * @param date0
     * @return list of days for student
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student currentStudent, LocalDate date, LocalDate date0) throws AttendanceAutomationDalException
    {
        return studentmanager.getAllDaysForAstudent(currentStudent, date, date0);
    }

    /**
     * Gets all students as alist
     *
     * @return a list of all students
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<Student> getallStudents() throws AttendanceAutomationDalException
    {
        return studentmanager.getallStudents();
    }

    /**
     * Checks if the student day exists
     *
     * @param username
     * @param date
     * @return if the student day exists in the DB
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException
    {
        return studentmanager.doesStudentDayExist(username, date);
    }

    /**
     * Gets the student day
     *
     * @param s
     * @param date
     * @return the student day
     * @throws AttendanceAutomationDalException
     */
    @Override
    public StudentDay getStudentDay(Student s, LocalDate date) throws AttendanceAutomationDalException
    {
        return studentmanager.getStudentDay(s, date);
    }

    /**
     * sets the pie chart
     *
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
     *
     * @param s
     * @param attendanceStatusCheck
     * @param columName
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public XYChart.Series setPresence(Student s, int attendanceStatusCheck, String columName) throws AttendanceAutomationDalException
    {
        return studentmanager.setPresence(s, attendanceStatusCheck, columName);

    }

    /**
     * check a teachers username and password
     *
     * @param t
     * @return boolean
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        return teachermanager.checkCredTeacher(t);
    }

    /**
     * gets a specific teacher
     *
     * @param t
     * @return teacher
     * @throws AttendanceAutomationDalException
     */
    @Override
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        return teachermanager.getTeacher(t);
    }

    /**
     * gets list a classes for a teacher
     *
     * @param username
     * @return list of classes
     * @throws AttendanceAutomationDalException
     */
    @Override
    public ObservableList<Class> getTeacherClasses(String username) throws AttendanceAutomationDalException
    {
        return teachermanager.getTeacherClasses(username);
    }

    /**
     * updates a students absence procent
     *
     * @param currentStudent
     * @param absenceProcentforstudent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public void updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException
    {

        studentmanager.updateStudentabsenceProcent(currentStudent, absenceProcentforstudent);
    }

    /**
     * gets list of teahcers
     *
     * @return getAllTeachers
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException
    {
        return teachermanager.getAllTeachers();
    }

    /**
     * encrypts passwords with hash
     *
     * @param password
     * @return password
     */
    @Override
    public String hashPassword(String password)
    {
        return securityManager.hashPassword(password);

    }

    /**
     * gets the most absent daay for a student
     *
     * @param currentStudent
     * @return mostAbsentDay
     * @throws AttendanceAutomationDalException
     */
    @Override
    public String getmostabsentdayforstudent(Student currentStudent) throws AttendanceAutomationDalException
    {
        return studentmanager.getmostabsentdayforstudent(currentStudent);
    }

    /**
     * updates the most absent day for a student
     *
     * @param currentStudent
     * @param mostabsentdayforstudent
     * @return mostAbsentDay
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException
    {
        return studentmanager.updateStudentMostAbsentDay(currentStudent, mostabsentdayforstudent);
    }

}
