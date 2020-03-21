/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.BLL.BLLFacade;
import java.util.Calendar;
import attendance.automation.gui.controller.SignInViewController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;

/**
 *
 * @author kacpe
 */
public class AppModel {

    private static AppModel appmodel = null;
    Calendar calendar = Calendar.getInstance();

    private final String monday = "Monday";
    private final String tuesday = "Tuesday";
    private final String wednesday = "Wednesday";
    private final String thursday = "Thursday";
    private final String friday = "Friday";
    private final BLLFacade bllfacade;
    private ObservableList<PieChart.Data> pieChartData;
    private Student currentStudent;

    private AppModel() throws IOException {
        bllfacade = new BLLFacade();
    }

    /**
     * Utilizing the singleton pattern to make sure there is only one instance
     * of appmodel.
     */
    public static AppModel getInstance() throws IOException {
        if (appmodel == null) {
            appmodel = new AppModel();
        }

        return appmodel;
    }

    /**
     * Gets the list of teachers
     *
     * @return
     */
    public ObservableList classList() {
        return bllfacade.getTeacherClassList();
    }

    /**
     * Gets the list of students
     *
     * @return
     */
    public ObservableList<Student> studentList() {
        return bllfacade.getTeacherStudentList();
    }

    /**
     * sets the pie chart
     *
     * @return
     */
    public ObservableList<PieChart.Data> setPiechartData(Student s) throws AttendanceAutomationDalException {

        ArrayList<StudentDay> days = new ArrayList<StudentDay>();
        ArrayList<StudentDay> presence = new ArrayList<StudentDay>();
        ArrayList<StudentDay> absent = new ArrayList<StudentDay>();
        ArrayList<StudentDay> notSet = new ArrayList<StudentDay>();
        
        days.addAll(bllfacade.getAllDaysForAstudent(s));
        System.out.println(days.size() + "the size of days");
        
        
        for (StudentDay day : days)
        {
            if(day.getAttendanceStatus() == 1)
            {
                presence.add(day);
            }
            else if(day.getAttendanceStatus() == 0)
            {
                absent.add(day);
            }
            else if(day.getAttendanceStatus() == -1)
            {
                notSet.add(day);
            }
        }
        
        double presenceProcent;
        double absentProcent;
        double notSetProcent; 
       
        if(presence.size() != 0)
       {
           double p = presence.size();
           
           presenceProcent = (p / days.size()) * 100;
       } 
        else
        {
            presenceProcent = presence.size();
        }
        if(absent.size() != 0)
       {
           double a = absent.size();
           absentProcent = (a / days.size()) * 100;
       }
        else
        {
            absentProcent = absent.size();
        }
        if(notSet.size() != 0)
       {
           double n = notSet.size();
           notSetProcent = (n / days.size()) * 100;
       }
        else
        {
            notSetProcent = notSet.size();
        }
            
       
        
        
        
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Presence " + presenceProcent + "%",presenceProcent),
                new PieChart.Data("Absent " + absentProcent + "%", absentProcent),
                new PieChart.Data("not set " + notSetProcent + "%", notSetProcent));

        
        return pieChartData;

    }
    
    public static void main(String[] args) throws IOException, AttendanceAutomationDalException
    {
        AppModel ap = new AppModel();
        
        
        System.out.println(ap.setPiechartData(new Student("mads", "mads69", "password", 0, "monday", 0)));
    }
 

    /**
     * updates existing Data-Object if name matches
     */
    public void addData() {

        for (Data d : pieChartData) {
            if (d.getName().equals("Absent")) {
                d.setPieValue(60);
                return;
            }
        }

    }

    /**
     * Sets the bar chart for present studens
     *
     * @return
     */
    public XYChart.Series setPresence() {

        XYChart.Series presence = new XYChart.Series<>();

        presence.setName("Presence");
        presence.getData().add(new XYChart.Data(monday, 5));
        presence.getData().add(new XYChart.Data(tuesday, 4));
        presence.getData().add(new XYChart.Data(wednesday, 8));
        presence.getData().add(new XYChart.Data(thursday, 3));
        presence.getData().add(new XYChart.Data(friday, 2));

        return presence;
    }

    /**
     * Sets the bar chart for absent studens
     *
     * @return
     */
    public XYChart.Series setAbsent() {

        XYChart.Series absent = new XYChart.Series<>();

        absent.setName("Absent");
        absent.getData().add(new XYChart.Data(monday, 3));
        absent.getData().add(new XYChart.Data(tuesday, 2));
        absent.getData().add(new XYChart.Data(wednesday, 1));
        absent.getData().add(new XYChart.Data(thursday, 0));
        absent.getData().add(new XYChart.Data(friday, 10));

        return absent;
    }

    /**
     * Gets teacher username
     *
     * @return
     */
    public String getTeahcerUsername() {
        return bllfacade.getUsernameTeacher();
    }

    /**
     * Gets teacher password
     *
     * @return
     */
    public String getTeacherPassword() {
        return bllfacade.getPasswordTeacher();
    }

    /**
     * Gets student username
     *
     * @return
     */
    public String getStudentUsername() {
        return bllfacade.getUsernameStudent();
    }

    /**
     * Gets student password
     *
     * @return
     */
    public String getStudentPassword() {
        return bllfacade.getPasswordStudent();
    }

    /**
     * opens a new window
     *
     * @throws IOException
     */
    public void handelLogout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = (Parent) fxmlLoader.load(getClass().getResource("/attendance/automation/gui/view/SignInView.fxml").openStream());
        SignInViewController cont = (SignInViewController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Sign in");
        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
        stage.show();
    }

    public boolean updateDayStudent(StudentDay sd) throws AttendanceAutomationDalException{

        return bllfacade.sendUpdateDayStudent(sd);
    }

    /**
     * Get the current month
     *
     * @return Month as an int
     */
    public int getCurrentMonth() {

        return calendar.get(Calendar.MONTH);

    }

    /**
     * Get the current Week
     *
     * @return Week as an int
     */
    public int getCurrentWeek() {

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) throws AttendanceAutomationDalException {

        this.currentStudent = bllfacade.getStudent(currentStudent);
    }

    /**
     * Get the year
     *
     * @return year as an int
     */
    public int getYear() {
        return calendar.get(Calendar.YEAR);

    }

    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        return bllfacade.checkCurrentDay(username);
    }
    
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException
    {
        return bllfacade.doesStudentDayExist(username, date);
    }
    
    public StudentDay getStudentDay(Student s, LocalDate date)throws AttendanceAutomationDalException
    {
        return bllfacade.getStudentDay(s,date);
    }

    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException {
        return bllfacade.checkCredStudent(s);
    }

    public void setDayStatus(int status) throws AttendanceAutomationDalException
    {
        String username = getCurrentStudent().getUsername();
        bllfacade.setDayStatus(status, username);
    }

    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException {
       return bllfacade.getAllDaysForAstudent(student);
    }
    
    public List<Student> getallStudents() throws AttendanceAutomationDalException
    {
        return bllfacade.getallStudents();
    }
}
