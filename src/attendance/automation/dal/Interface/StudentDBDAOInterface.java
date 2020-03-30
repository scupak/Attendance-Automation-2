/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.Interface;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author SKRUMM
 */
public interface StudentDBDAOInterface 
{
    
    /**
     * Gets a list of all the students
     * @return a list of all students
     * @throws AttendanceAutomationDalException 
     */
    public List<Student> getAllStudents() throws AttendanceAutomationDalException;
    
    /**
     * Gets a student
     * @param s
     * @return a student
     * @throws AttendanceAutomationDalException 
     */
    public Student getStudent(Student s) throws AttendanceAutomationDalException;
    
    /**
     * Checks if the students exists
     * @param s
     * @return if the student exists
     * @throws AttendanceAutomationDalException 
     */
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException;

    /**
     * Checks if the day is today for the current username
     * @param username
     * @return the current day
     * @throws AttendanceAutomationDalException 
     */
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException;
    
    /**
     * Sends an update throught the layers
     * @param sd
     * @return the updated studentSay
     * @throws AttendanceAutomationDalException 
     */
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException;

    /**
     * Sets the statuds of the selected day for the username
     * @param status
     * @param username
     * @throws AttendanceAutomationDalException 
     */
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException;
    
    /**
     * Gets a list of all of the studends days
     * @param student
     * @return the list of all of the students days
     * @throws AttendanceAutomationDalException 
     */
    public List<StudentDay> getAllDaysForStudent(Student student) throws AttendanceAutomationDalException;
    
    /**
     * Checks if the students day exists
     * @param username
     * @param date
     * @return if the day exists
     * @throws AttendanceAutomationDalException 
     */
    public boolean doesStudentDayExist(String username, LocalDate date)throws AttendanceAutomationDalException;
    
    /**
     * Gets the students day
     * @param s
     * @param date
     * @return the students day
     * @throws AttendanceAutomationDalException 
     */
    public StudentDay getStudentDay(Student s, LocalDate date)throws AttendanceAutomationDalException;
    
    
    public boolean updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException;
    
     public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException;
}
