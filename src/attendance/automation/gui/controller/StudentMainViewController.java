/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.Student;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.controller.calendar.CalendarController;
import attendance.automation.gui.controller.calendar.FullCalendarView;
import attendance.automation.gui.model.AppModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author zilot
 */
public class StudentMainViewController implements Initializable
{

    @FXML
    private Button btLogout;
    @FXML
    private Button btCalenderView;
    @FXML
    private ToggleGroup precent;
    @FXML
    private RadioButton rbHeretoday;
    @FXML
    private RadioButton rbNotHeretoday;
    @FXML
    private Button submitprecense;
    @FXML
    private AnchorPane studentRootPane;
    @FXML
    private Button btPiecharView;
    @FXML
    private Label lbWelcome;

    private Student user;
    private AppModel appmodel;

    @FXML
    private Label currentClassText;
    @FXML
    private HBox hBox;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

        try {
            //Student mads = new Student("Mads Jensen", 5, "mads", "jensen");
            //setName(mads);
            
            /**
             *  We use get instance instead of new to make sure we use the same appmodel in all classes.
             */
            appmodel = AppModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //A check to see if were woriking with the same instance of appmodel.
        System.out.println("Instance ID: " + System.identityHashCode(appmodel));
        
        try
        {
            checkCurrentDay();
        } catch (AttendanceAutomationDalException ex)
        {
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(appmodel.getCurrentStudent());
    }

    public void checkCurrentDay() throws AttendanceAutomationDalException
    {
         
            String username = appmodel.getCurrentStudent().getUsername();
            int status = appmodel.checkCurrentDay(username);
        
            if (status == 0 || status == 1)
            {
                thankYouMessage();
            }
            else if(status == 2)
            {
                failMessage();
            }
        
    }
    
    
    
    /**
     * Sets the lbWelcome text to the logged in student
     *
     * @param stud
     */
    public void setName(Student stud)
    {

        lbWelcome.setText("Welcome " + stud.getName() + "!");

    }

    /**
     * Logs out the current user, and opens the signInView
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void HandleLogout(ActionEvent event) throws IOException
    {

        Window window = studentRootPane.getScene().getWindow();

        if (window instanceof Stage)
        {
            ((Stage) window).close();
        }
        appmodel.handelLogout();
    }

    /**
     * opens the StudentCalenderView
     *
     * @param event
     */
    @FXML
    private void handelCalenderview(ActionEvent event)
    {

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/FullCalendar.fxml"));
            
            
            Pane pane = loader.load();
            
            pane.getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
            

            studentRootPane.getChildren().setAll(pane);
            
            
            CalendarController controller = loader.getController();
            
            controller.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now(),appmodel).getView());

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    /**
     * Opens the StudentChartView
     *
     * @param event
     */
    @FXML
    private void handelPieChart(ActionEvent event)
    {

        try
        {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendance/automation/gui/view/StudentChartView.fxml"));

            studentRootPane.getChildren().setAll(pane);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }

    }

    /**
     *
     * @param event
     */
    @FXML
    private void handelSubmit(ActionEvent event) throws AttendanceAutomationDalException
    {

        //sm.addData();
        if (rbHeretoday.isSelected())
        {
            appmodel.setDayStatus(1);
        }
        else if(rbNotHeretoday.isSelected())
        {
            appmodel.setDayStatus(0);
        }
        
        thankYouMessage();

    }
    
    private void thankYouMessage()
    {
        studentRootPane.getChildren().remove(hBox);
        currentClassText.setText("Thank you!");
    }
    
    private void failMessage()
    {
        studentRootPane.getChildren().remove(hBox);
        currentClassText.setText("You have the day off!");
    }
    
}
