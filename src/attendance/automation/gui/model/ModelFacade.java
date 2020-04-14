/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.be.Teacher;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.enums.UserMode;
import attendance.automation.gui.model.Interface.BaseModelInterface;
import attendance.automation.gui.model.Interface.ModelFacadeInterface;
import attendance.automation.gui.model.Interface.StudentModelInterface;
import attendance.automation.gui.model.Interface.TeacherModelInterface;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import attendance.automation.be.Class;

/**
 *
 * @author kacpe
 */
public class ModelFacade implements ModelFacadeInterface
{

    private static ModelFacade modelfacade = null;
    private StudentModelInterface studentmodel;
    private TeacherModelInterface teachermodel;
    private BaseModelInterface basemodel;

    private ModelFacade() throws IOException, Exception
    {
        studentmodel = new StudentModel();
        teachermodel = new TeacherModel();
        basemodel = new BaseModel();
    }

    /**
     * Utilizing the singleton pattern to make sure there is only one instance
     * of modelFacade.
     *
     * @return modelfacade
     * @throws java.io.IOException
     */
    public static ModelFacade getInstance() throws IOException, Exception
    {
        if (modelfacade == null)
        {
            modelfacade = new ModelFacade();
        }
        return modelfacade;
    }

    /**
     * Gets the list of teachers
     *
     * @param username
     * @return the list of teachers
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    @Override
    public ObservableList<Class> classList(String username) throws AttendanceAutomationDalException
    {
        return teachermodel.classList(username);
    }

    /**
     * Gets the list of students
     *
     * @param classid
     * @return the list of students
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    @Override
    public ObservableList<Student> teacherStudentList(int classid) throws AttendanceAutomationDalException
    {
        return teachermodel.teacherStudentList(classid);
    }

    /**
     * sets the pie chart
     *
     * @param s
     * @return the pie chart
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    @Override
    public ObservableList<PieChart.Data> setPiechartData(Student s) throws AttendanceAutomationDalException
    {
        return studentmodel.setPiechartData(s);
    }

    /**
     * Sets the bar chart for present studens
     *
     * @param s
     * @return the bar chart
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    @Override
    public XYChart.Series setPresence(Student s) throws AttendanceAutomationDalException
    {
        return studentmodel.setPresence(s);
    }

    /**
     * Sets the bar chart for absent studens
     *
     * @param s
     * @return the bar chart
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    @Override
    public XYChart.Series setAbsent(Student s) throws AttendanceAutomationDalException
    {
        return studentmodel.setAbsent(s);
    }

    /**
     * Sends an updated student day through the layers
     *
     * @param sd
     * @return the update
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean sendupdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException
    {
        return studentmodel.sendupdateDayStudent(sd);
    }

    /**
     * Checks if the student exists
     *
     * @param username
     * @param date
     * @return if the student exists
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException
    {
        return studentmodel.doesStudentDayExist(username, date);
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
        return studentmodel.getStudentDay(s, date);
    }

    /**
     * Sets the status of the day
     *
     * @param status
     * @throws AttendanceAutomationDalException
     */
    @Override
    public void setDayStatus(int status) throws AttendanceAutomationDalException
    {
        studentmodel.setDayStatus(status);
    }

    /**
     * gets all days for a student
     *
     * @param student
     * @return list of student days
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException
    {
        return studentmodel.getAllDaysForAstudent(student);
    }

    /**
     * gets all students
     *
     * @return list of students
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<Student> getallStudents() throws AttendanceAutomationDalException
    {
        return studentmodel.getallStudents();
    }

    /**
     * opens a new window
     *
     * @throws IOException
     */
    @Override
    public void handelLogout() throws IOException
    {
        basemodel.handelLogout();
    }

    /**
     * gets the current student
     *
     * @return currentStudent
     */
    @Override
    public Student getCurrentStudent()
    {
        return studentmodel.getCurrentStudent();
    }

    /**
     * sets the current student
     *
     * @param currentStudent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public void setCurrentStudent(Student currentStudent) throws AttendanceAutomationDalException
    {
        studentmodel.setCurrentStudent(currentStudent);
    }

    /**
     * Get the year
     *
     * @return year as an int
     */
    @Override
    public int getYear()
    {
        return basemodel.getYear();
    }

    /**
     * Get the current month
     *
     * @return Month as an int
     */
    @Override
    public int getCurrentMonth()
    {
        return basemodel.getCurrentMonth();
    }

    /**
     * Get the current Week
     *
     * @return Week as an int
     */
    @Override
    public int getCurrentWeek()
    {
        return basemodel.getCurrentWeek();
    }

    /**
     * Checks the current day
     *
     * @param username
     * @return the current day
     * @throws AttendanceAutomationDalException
     */
    @Override
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        return basemodel.checkCurrentDay(username);
    }

    /**
     * Checks the students credentials, and if they match
     *
     * @param s
     * @return wether or not the credentials match
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException
    {
        return basemodel.checkCredStudent(s);
    }

    /**
     * gets the status select
     *
     * @return statusSelect
     */
    @Override
    public boolean getIsStatusSelectOpen()
    {
        return basemodel.getIsStatusSelectOpen();
    }

    /**
     * sets the statusSelect
     *
     * @param isStatusSelectOpen
     */
    @Override
    public void setIsStatusSelectOpen(boolean isStatusSelectOpen)
    {
        basemodel.setIsStatusSelectOpen(isStatusSelectOpen);
    }

    /**
     * gets the threadcounter
     *
     * @return threadcounter
     */
    @Override
    public int getThreadcounter()
    {
        return basemodel.getThreadcounter();
    }

    /**
     * sets the threadcounter
     *
     * @param threadcounter
     */
    @Override
    public void setThreadcounter(int threadcounter)
    {
        basemodel.setThreadcounter(threadcounter);
    }

    /**
     * Sets the parameter that tells the program if its a teacher or student
     * using the program.
     *
     * @param usermode
     */
    @Override
    public void setCurrentUserMode(UserMode usermode)
    {
        basemodel.setCurrentUserMode(usermode);
    }

    /**
     * Get the currentuserMode
     *
     * @return
     */
    @Override
    public UserMode getCurrentUserMode()
    {
        return basemodel.getCurrentUserMode();
    }

    /**
     * sets the current teacher
     *
     * @param currentTeacher
     * @throws AttendanceAutomationDalException
     */
    @Override
    public void setCurrentTeacher(Teacher currentTeacher) throws AttendanceAutomationDalException
    {
        teachermodel.setCurrentTeacher(currentTeacher);
    }

    /**
     * checks teacher username and password
     *
     * @param t
     * @return boolean
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        return teachermodel.checkCredTeacher(t);
    }

    /**
     * gets the current teacher
     *
     * @return currentTeacher
     */
    @Override
    public Teacher getCurrentTeacher()
    {
        return teachermodel.getCurrentTeacher();
    }

    /**
     * gets the current class
     *
     * @return currentClass
     */
    @Override
    public Class getCurrentClass()
    {
        return teachermodel.getCurrentClass();
    }

    /**
     * sets the current class
     *
     * @param currentClass
     */
    @Override
    public void setCurrentClass(Class currentClass)
    {
        teachermodel.setCurrentClass(currentClass);
    }

    /**
     * encrypts the passwords using hashing
     *
     * @param password
     * @return password
     */
    @Override
    public String hashPassword(String password)
    {
        return basemodel.hashPassword(password);
    }

    /**
     * gets the absence procent for a student
     *
     * @param s
     * @return absentProcent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public double getabsenceProcentforstudent(Student s) throws AttendanceAutomationDalException
    {
        return studentmodel.getabsenceProcentforstudent(s);
    }

    /**
     * updates the absence procent for a student
     *
     * @param currentStudent
     * @param absenceProcentforstudent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public void updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException
    {
        studentmodel.updateStudentabsenceProcent(currentStudent, absenceProcentforstudent);
    }

    /**
     * gets the most absent day for a student
     *
     * @param currentStudent
     * @return mostAbsesntDayForStudent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public String getmostabsentdayforstudent(Student currentStudent) throws AttendanceAutomationDalException
    {

        return studentmodel.getmostabsentdayforstudent(currentStudent);
    }

    /**
     * updates the most absesnt day for a student
     *
     * @param currentStudent
     * @param mostabsentdayforstudent
     * @return mostabsentdayforstudent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException
    {
        return studentmodel.updateStudentMostAbsentDay(currentStudent, mostabsentdayforstudent);
    }

    /**
     * gets all days between two dates for a student
     *
     * @param currentStudent
     * @param date
     * @param date0
     * @return list af studentDays
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student currentStudent, LocalDate date, LocalDate date0) throws AttendanceAutomationDalException
    {
        return studentmodel.getAllDaysForAstudent(currentStudent, date, date0);
    }

}
