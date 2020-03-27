/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL.Interface;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.chart.XYChart;

/**
 *
 * @author SKRUMM
 */
public interface StudentManagerInterface 
{
    
    /**
     * Get student username
     *
     * @return getUsernameStudent
     */
    public String getUsernameStudent();
    
    /**
     * Get student password
     *
     * @return getPasswordStudent
     */
    public String getPasswordStudent();

    /**
     * Checks the vurrent day for the student.
     * @param username
     * @return the current day
     * @throws AttendanceAutomationDalException 
     */
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException;
    
    /**
     * Sends an update, to update DayStudent
     * @param sd
     * @return StudentDay
     * @throws AttendanceAutomationDalException 
     */
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException;
    
    /**
     * Checks the students credentials, if they match with the assigned credentials in the DB.
     * @param s
     * @return credentials
     * @throws AttendanceAutomationDalException 
     */
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException;
    
    /**
     * Gets student info
     * @param s
     * @return stident info
     * @throws AttendanceAutomationDalException 
     */
    public Student getStudent(Student s) throws AttendanceAutomationDalException;

    /**
     * Sets the status of the day
     * @param status
     * @param username
     * @throws AttendanceAutomationDalException 
     */
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException; 
    
    /**
     * Gets all days for specific student as a list
     * @param student
     * @return all the days for the specific student as a list
     * @throws AttendanceAutomationDalException 
     */
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException;
    
    /**
     * Gets all students as a list
     * @return all students as a list
     * @throws AttendanceAutomationDalException 
     */
    public List<Student> getallStudents() throws AttendanceAutomationDalException;
     
    /**
     * Checks if the current student exists in the DB
     * @param username
     * @param date
     * @return if the student exists based on the given info
     * @throws AttendanceAutomationDalException 
     */
    public boolean doesStudentDayExist(String username, LocalDate date)throws AttendanceAutomationDalException;
    
    /**
     * Gets studentDay
     * @param s
     * @param date
     * @return the studentDay
     * @throws AttendanceAutomationDalException 
     */
    public StudentDay getStudentDay(Student s, LocalDate date)throws AttendanceAutomationDalException;
    
    /**
     * sets the pie chart
     * @param s
     * @param attendanceStatusCheck
     * @return
     * @throws AttendanceAutomationDalException 
     */
    public double pieChartData(Student s, int attendanceStatusCheck) throws AttendanceAutomationDalException;
    
    /**
     * sets a bar chart
     * @param s
     * @param attendanceStatusCheck
     * @param columName
     * @return
     * @throws AttendanceAutomationDalException 
     */
    public XYChart.Series setPresence(Student s , int attendanceStatusCheck, String columName) throws AttendanceAutomationDalException;
    
    
    public void updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException ;
     
}
