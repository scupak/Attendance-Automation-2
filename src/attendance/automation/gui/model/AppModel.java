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

    private AppModel() throws IOException
    {
        bllfacade = new BLLFacade();
    }
    
     /**
         *  Utilizing the singleton pattern to make sure there is only one instance of appmodel.
         */
    public static AppModel getInstance() throws IOException
    {
        if(appmodel == null)
        {
            appmodel = new AppModel();
        }
        
        return appmodel;
    }
    /**
     * Gets the list of teachers
     *
     * @return
     */
    public ObservableList classList()
    {
        return bllfacade.getTeacherClassList();
    }
    
    
    /**
     * Gets the list of students
     *
     * @return
     */public ObservableList<Student> studentList()
   {
       return bllfacade.getTeacherStudentList();
  }
    
     /**
     * sets the pie chart
     *
     * @return
     */
    public ObservableList<PieChart.Data> setPiechartData()
    {

        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Presence", 48),
                new PieChart.Data("Absent", 52));

        return pieChartData;

    }
    
     /**
     * updates existing Data-Object if name matches
     */
    public void addData()
    {

        for (Data d : pieChartData)
        {
            if (d.getName().equals("Absent"))
            {
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
    public XYChart.Series setPresence()
    {

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
    public XYChart.Series setAbsent()
    {

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
    public String getTeahcerUsername()
    {
        return bllfacade.getUsernameTeacher();
    }

    /**
     * Gets teacher password
     *
     * @return
     */
    public String getTeacherPassword()
    {
        return bllfacade.getPasswordTeacher();
    }
    
    
    /**
     * Gets student username
     *
     * @return
     */
    public String getStudentUsername()
    {
        return bllfacade.getUsernameStudent();
    }

    /**
     * Gets student password
     *
     * @return
     */
    public String getStudentPassword()
    {
        return bllfacade.getPasswordStudent();
    }
    
    
    
    /**
     * opens a new window
     *
     * @throws IOException
     */
    public void handelLogout() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = (Parent) fxmlLoader.load(getClass().getResource("/attendance/automation/gui/view/SignInView.fxml").openStream());
        SignInViewController cont = (SignInViewController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Sign in");
        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
        stage.show();
    }
    
        public boolean updateDayStudent(StudentDay sd)
    {
        
        
        return bllfacade.sendUpdateDayStudent(sd);
    }
    
    
     /**
     * Get the current month
     *
     * @return Month as an int
     */
    public int getCurrentMonth()
    {

        return calendar.get(Calendar.MONTH);

    }
    
    
     /**
     * Get the current Week
     *
     * @return Week as an int
     */
    public int getCurrentWeek()
    {

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * Get the year
     *
     * @return year as an int
     */
    public int getYear()
    {
        return calendar.get(Calendar.YEAR);

    }
}
