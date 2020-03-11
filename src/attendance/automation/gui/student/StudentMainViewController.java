/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.student;

import attendance.automation.be.Student;
import attendance.automation.gui.model.LogOutModel;
import attendance.automation.gui.model.StudentModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private StudentModel sm;
    private LogOutModel lom;

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

        Student mads = new Student("Mads Jensen", 5, "mads", "jensen");
        setName(mads);
        lom = new LogOutModel();

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
        lom.handelLogout();
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
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendance/automation/gui/student/StudentCalenderView.fxml"));

            studentRootPane.getChildren().setAll(pane);

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

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendance/automation/gui/student/StudentChartView.fxml"));

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
    private void handelSubmit(ActionEvent event)
    {

        //sm.addData();
        studentRootPane.getChildren().remove(hBox);
        currentClassText.setText("Thank you!");

    }

}
