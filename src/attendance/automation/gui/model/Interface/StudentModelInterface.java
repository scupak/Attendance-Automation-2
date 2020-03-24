/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model.Interface;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author kacpe
 */
public interface StudentModelInterface 
{
    
    public Student getCurrentStudent();
    
    public void setCurrentStudent(Student currentStudent) throws AttendanceAutomationDalException;
    /**
     * sets the pie chart
     *
     * @return the pie chart
     */
    public ObservableList<PieChart.Data> setPiechartData(Student s) throws AttendanceAutomationDalException;
    
    
    /**
     * Sets the bar chart for present studens
     *
     * @return the bar chart
     */
    public XYChart.Series setPresence(Student s) throws AttendanceAutomationDalException;
    
    
     /**
     * Sets the bar chart for absent studens
     *
     * @return the bar chart
     */
    public XYChart.Series setAbsent(Student s) throws AttendanceAutomationDalException;
    
    
     /**
     * Sends an updated student day through the layers
     * @param sd
     * @return the update
     * @throws AttendanceAutomationDalException 
     */
    public boolean sendupdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException;
    
    
    /**
     * Checks if the student exists
     * @param username
     * @param date
     * @return if the student exists
     * @throws AttendanceAutomationDalException 
     */
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException;
    
    
    /**
     * Gets the student day
     * @param s
     * @param date
     * @return the student day
     * @throws AttendanceAutomationDalException 
     */
    public StudentDay getStudentDay(Student s, LocalDate date)throws AttendanceAutomationDalException;
    
    
    /**
     * Sets the status of the day
     * @param status
     * @throws AttendanceAutomationDalException 
     */
    public void setDayStatus(int status) throws AttendanceAutomationDalException;
    
    
    /**
     * Gets a list of all the daysfor a student
     * @param student
     * @return the list of the students days
     * @throws AttendanceAutomationDalException 
     */
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException;
    
    
    /**
     * gets a list of all the students
     * @return a list of all the students
     * @throws AttendanceAutomationDalException 
     */
    public List<Student> getallStudents() throws AttendanceAutomationDalException;
}
