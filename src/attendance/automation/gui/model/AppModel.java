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
 * @author SKRUMM
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
     * @return the list of teachers
     */
    public ObservableList classList() {
        return bllfacade.getTeacherClassList();
    }

    /**
     * Gets the list of students
     *
     * @return the list of students
     */
    public ObservableList<Student> studentList() {
        return bllfacade.getTeacherStudentList();
    }

    /**
     * sets the pie chart
     *
     * @return the pie chart
     */
    public ObservableList<PieChart.Data> setPiechartData(Student s) throws AttendanceAutomationDalException {

//        ArrayList<StudentDay> days = new ArrayList<StudentDay>();
//        ArrayList<StudentDay> presence = new ArrayList<StudentDay>();
//        ArrayList<StudentDay> absent = new ArrayList<StudentDay>();
//        ArrayList<StudentDay> notSet = new ArrayList<StudentDay>();
//        
//        days.addAll(bllfacade.getAllDaysForAstudent(s));
//        System.out.println(days.size() + "the size of days");
//        
//        
//        for (StudentDay day : days)
//        {
//            if(day.getAttendanceStatus() == 1)
//            {
//                presence.add(day);
//            }
//            else if(day.getAttendanceStatus() == 0)
//            {
//                absent.add(day);
//            }
//            else if(day.getAttendanceStatus() == -1)
//            {
//                notSet.add(day);
//            }
//        }
//        
//        double presenceProcent;
//        double absentProcent;
//        double notSetProcent; 
//       
//        if(presence.size() != 0)
//       {
//           double p = presence.size();
//           
//           presenceProcent = (p / days.size()) * 100;
//       } 
//        else
//        {
//            presenceProcent = presence.size();
//        }
//        if(absent.size() != 0)
//       {
//           double a = absent.size();
//           absentProcent = (a / days.size()) * 100;
//           
//       }
//        else
//        {
//            absentProcent = absent.size();
//        }
//        if(notSet.size() != 0)
//       {
//           double n = notSet.size();
//          notSetProcent = (n / days.size()) * 100;
//          
//       }
//        else
//        {
//            notSetProcent = notSet.size();
//        }
//        
        double presenceProcent = bllfacade.pieChartDataPresence(s);
        double absentProcent = bllfacade.pieChartDataAbsent(s);
        double notSetProcent = bllfacade.pieChartDataNotSet(s);
        
        
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Presence " + Math.round(presenceProcent) + "%",presenceProcent),
                new PieChart.Data("Absent " + Math.round(absentProcent) + "%",absentProcent),
                new PieChart.Data("not set " + Math.round(notSetProcent) + "%", notSetProcent));

        
        return pieChartData;

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
     * @return the bar chart
     */
    public XYChart.Series setPresence(Student s) throws AttendanceAutomationDalException {

        XYChart.Series presence = new XYChart.Series<>();
        ArrayList<StudentDay> p = new ArrayList<>();
        int mondayC = 0;
        int tuesdayC = 0;
        int wednesdayC = 0;
        int thursdayC = 0;
        int fridayC = 0;
        int saturdayC = 0;
        int sundayC = 0;
        
        for (StudentDay studentDay : appmodel.getAllDaysForAstudent(s))
        {
            if(studentDay.getAttendanceStatus() == 1)
            {
                p.add(studentDay);
            }
            
        }
        
        for (StudentDay day : p)
        {
            if("monday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                mondayC++;
            }
            else if("tuesday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                tuesdayC++;
            }
            else if("wednsesday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                wednesdayC++;
            }
            else if("thursday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                thursdayC++;
            }
            else if("friday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                fridayC++;
            }
            
        }

        presence.setName("Presence");
        presence.getData().add(new XYChart.Data(monday, mondayC));
        presence.getData().add(new XYChart.Data(tuesday, tuesdayC));
        presence.getData().add(new XYChart.Data(wednesday, wednesdayC));
        presence.getData().add(new XYChart.Data(thursday, thursdayC));
        presence.getData().add(new XYChart.Data(friday, fridayC));

        return presence;
    }
   

    /**
     * Sets the bar chart for absent studens
     *
     * @return the bar chart
     */
    public XYChart.Series setAbsent(Student s) throws AttendanceAutomationDalException {

        XYChart.Series absent = new XYChart.Series<>();
        
         ArrayList<StudentDay> a = new ArrayList<>();
        int mondayC = 0;
        int tuesdayC = 0;
        int wednesdayC = 0;
        int thursdayC = 0;
        int fridayC = 0;
        int saturdayC = 0;
        int sundayC = 0;
        
        for (StudentDay studentDay : appmodel.getAllDaysForAstudent(s))
        {
            if(studentDay.getAttendanceStatus() == 0)
            {
                a.add(studentDay);
            }
            
        }
        
        for (StudentDay day : a)
        {
            if("monday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                mondayC++;
            }
            else if("tuesday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                tuesdayC++;
            }
            else if("wednsesday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                wednesdayC++;
            }
            else if("thursday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                thursdayC++;
            }
            else if("friday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                fridayC++;
            }
            
        }
        

        absent.setName("Absent");
        absent.getData().add(new XYChart.Data(monday, mondayC));
        absent.getData().add(new XYChart.Data(tuesday, tuesdayC));
        absent.getData().add(new XYChart.Data(wednesday, wednesdayC));
        absent.getData().add(new XYChart.Data(thursday, thursdayC));
        absent.getData().add(new XYChart.Data(friday, fridayC));

        return absent;
    }

    /**
     * Gets teacher username
     *
     * @return the teachers username
     */
    public String getTeahcerUsername() {
        return bllfacade.getUsernameTeacher();
    }

    /**
     * Gets teacher password
     *
     * @return the teachers password
     */
    public String getTeacherPassword() {
        return bllfacade.getPasswordTeacher();
    }

    /**
     * Gets student username
     *
     * @return the students user name
     */
    public String getStudentUsername() {
        return bllfacade.getUsernameStudent();
    }

    /**
     * Gets student password
     *
     * @return the students password
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

    /**
     * Sends an updated student day through the layers
     * @param sd
     * @return the update
     * @throws AttendanceAutomationDalException 
     */
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

    /**
     * Checks the current day
     * @param username
     * @return the current day
     * @throws AttendanceAutomationDalException 
     */
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        return bllfacade.checkCurrentDay(username);
    }
    
    /**
     * Checks if the student exists
     * @param username
     * @param date
     * @return if the student exists
     * @throws AttendanceAutomationDalException 
     */
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException
    {
        return bllfacade.doesStudentDayExist(username, date);
    }
    
    /**
     * Gets the student day
     * @param s
     * @param date
     * @return the student day
     * @throws AttendanceAutomationDalException 
     */
    public StudentDay getStudentDay(Student s, LocalDate date)throws AttendanceAutomationDalException
    {
        return bllfacade.getStudentDay(s,date);
    }

    /**
     * Checks the students credentials, and if they match
     * @param s
     * @return wether or not the credentials match
     * @throws AttendanceAutomationDalException 
     */
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException {
        return bllfacade.checkCredStudent(s);
    }

    /**
     * Sets the status of the day
     * @param status
     * @throws AttendanceAutomationDalException 
     */
    public void setDayStatus(int status) throws AttendanceAutomationDalException
    {
        String username = getCurrentStudent().getUsername();
        bllfacade.setDayStatus(status, username);
    }

    /**
     * Gets a list of all the daysfor a student
     * @param student
     * @return the list of the students days
     * @throws AttendanceAutomationDalException 
     */
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException {
       return bllfacade.getAllDaysForAstudent(student);
    }
    
    /**
     * gets a list of all the students
     * @return a list of all the students
     * @throws AttendanceAutomationDalException 
     */
    public List<Student> getallStudents() throws AttendanceAutomationDalException
    {
        return bllfacade.getallStudents();
    }
}
