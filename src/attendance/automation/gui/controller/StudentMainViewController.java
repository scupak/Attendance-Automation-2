/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.Student;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.enums.UserMode;
import attendance.automation.gui.controller.calendar.CalendarController;
import attendance.automation.gui.controller.calendar.FullCalendarView;
import attendance.automation.gui.model.Interface.ModelFacadeInterface;
import attendance.automation.gui.model.ModelFacade;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SKRUMM
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
    private ModelFacadeInterface modelfacade;

    @FXML
    private Label currentClassText;
    @FXML
    private HBox hBox;
    @FXML
    private Label userModeLabel;
    

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
            modelfacade = ModelFacade.getInstance();
            System.out.println("Current user mode is" + "  " +modelfacade.getCurrentUserMode());
            if (modelfacade.getCurrentUserMode() == UserMode.TEACHER)
            {
                userModeLabel.setText(modelfacade.getCurrentTeacher().getName() + " is currently in " +"Admin Mode " + "Accesing " + modelfacade.getCurrentStudent().getName() + "s profile");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Student main view error!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //A check to see if were woriking with the same instance of appmodel.
        System.out.println("Instance ID: " + System.identityHashCode(modelfacade));
        
        checkDay();
        /*TODO needs to be changed so that both a teachers  or a students name can be displayed*/
        setName(modelfacade.getCurrentStudent());
        
        System.out.println(modelfacade.getCurrentStudent());
    }

    public void checkDay()
    {
         
        try
        {
            String username = modelfacade.getCurrentStudent().getUsername();
            int status = modelfacade.checkCurrentDay(username);
        
            if (status == 0 || status == 1)
            {
                thankYouMessage();
            }
            else if(status == 2)
            {
                failMessage();
            }
        } catch (AttendanceAutomationDalException ex)
        {
            JOptionPane.showMessageDialog(null, "Cannot check day for selected username!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void HandleLogout(ActionEvent event)
    {

        try
        {
            Window window = studentRootPane.getScene().getWindow();
            
            if (modelfacade.getCurrentUserMode() == UserMode.STUDENT)
            {
                modelfacade.handelLogout();
            }
            else if (modelfacade.getCurrentUserMode() == UserMode.TEACHER)
                {
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherClassView.fxml"));
                    Parent root = loader.load();
                    TeacherClassViewController TCVController = loader.getController();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Attendance - Teacher");
                    stage.getScene().getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
                    stage.show();  
                }
                        
            if (window instanceof Stage)
            {
                ((Stage) window).close();
            }

           
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Cannot handle logout!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StudentMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            
            controller.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now(),modelfacade).getView());

        } catch (IOException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Cannot read FXML file(s)!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(AttendanceAutomationDalException ex){
            JOptionPane.showMessageDialog(null, "Cannot access calendar from DB", "Error", JOptionPane.ERROR_MESSAGE);
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
     * Handles submit
     * @param event
     */
    @FXML
    private void handelSubmit(ActionEvent event)
    {
        try{
        //sm.addData();
        if (rbHeretoday.isSelected())
        {
            modelfacade.setDayStatus(1);
        }
        else if(rbNotHeretoday.isSelected())
        {
            modelfacade.setDayStatus(0);
        }
        
        thankYouMessage();
        }
        catch(AttendanceAutomationDalException ex){
            JOptionPane.showMessageDialog(null, "Unable to update status for student in DB!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Creates a thank you message for the user
     */
    private void thankYouMessage()
    {
        studentRootPane.getChildren().remove(hBox);
        currentClassText.setText("Thank you!");
    }
    
    /**
     * Creates a day off message for the user
     */
    private void failMessage()
    {
        studentRootPane.getChildren().remove(hBox);
        currentClassText.setText("You have the day off!");
    }
    
}
