/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.BLL.BLLFacade;
import attendance.automation.BLL.Interface.BLLFacadeInterface;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.model.Interface.StudentModelInterface;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author kacpe
 */
public class StudentModel implements StudentModelInterface
{

    private Student currentStudent;
    private BLLFacadeInterface bllfacade;
    private final int present = 1;
    private final int absent = 0;
    private final int notSet = -1;
    private ObservableList<PieChart.Data> pieChartData;

    public StudentModel() throws IOException, Exception
    {
        bllfacade = new BLLFacade();
    }

    /**
     * gets the current student
     *
     * @return currentStudent
     */
    @Override
    public Student getCurrentStudent()
    {
        return currentStudent;
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
        this.currentStudent = bllfacade.getStudent(currentStudent);
    }

    /**
     * sets the pie chart
     *
     * @return the pie chart
     */
    @Override
    public ObservableList<PieChart.Data> setPiechartData(Student s) throws AttendanceAutomationDalException
    {
        double presenceProcent = bllfacade.pieChartData(s, present);
        double absentProcent = bllfacade.pieChartData(s, absent);
        double notSetProcent = bllfacade.pieChartData(s, notSet);

        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Presence " + Math.round(presenceProcent) + "%", presenceProcent),
                new PieChart.Data("Absent " + Math.round(absentProcent) + "%", absentProcent),
                new PieChart.Data("not set " + Math.round(notSetProcent) + "%", notSetProcent));

        return pieChartData;
    }

    /**
     * Sets the bar chart for present studens
     *
     * @return the bar chart
     */
    @Override
    public XYChart.Series setPresence(Student s) throws AttendanceAutomationDalException
    {
        return bllfacade.setPresence(s, present, "present");
    }

    /**
     * Sets the bar chart for absent studens
     *
     * @return the bar chart
     */
    @Override
    public XYChart.Series setAbsent(Student s) throws AttendanceAutomationDalException
    {
        return bllfacade.setPresence(s, absent, "absent");
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
        return bllfacade.sendUpdateDayStudent(sd);
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
        return bllfacade.doesStudentDayExist(username, date);
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
        return bllfacade.getStudentDay(s, date);
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
        String username = getCurrentStudent().getUsername();
        bllfacade.setDayStatus(status, username);
    }

    /**
     * gets all the days for a student
     *
     * @param student
     * @return list of studetnDays
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException
    {
        return bllfacade.getAllDaysForAstudent(student);
    }

    /**
     * gets all the days between two dates for a student
     *
     * @param currentStudent
     * @param date
     * @param date0
     * @return list of studentDays
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student currentStudent, LocalDate date, LocalDate date0) throws AttendanceAutomationDalException
    {
        return bllfacade.getAllDaysForAstudent(currentStudent, date, date0);
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
        return bllfacade.getallStudents();
    }

    /**
     * gets the absence procebt for a student
     *
     * @param s
     * @return absent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public double getabsenceProcentforstudent(Student s) throws AttendanceAutomationDalException
    {

        return bllfacade.pieChartData(s, absent);
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
        bllfacade.updateStudentabsenceProcent(currentStudent, absenceProcentforstudent);
    }

    /**
     * gets the most absent day for a student
     *
     * @param currentStudent
     * @return mostabsentdayforstudent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public String getmostabsentdayforstudent(Student currentStudent) throws AttendanceAutomationDalException
    {
        return bllfacade.getmostabsentdayforstudent(currentStudent);
    }

    /**
     * updates a students most absent day
     *
     * @param currentStudent
     * @param mostabsentdayforstudent
     * @return mostabsentdayforstudent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException
    {
        return bllfacade.updateStudentMostAbsentDay(currentStudent, mostabsentdayforstudent);
    }

}
