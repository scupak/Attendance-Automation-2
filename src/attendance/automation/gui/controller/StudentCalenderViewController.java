/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.gui.model.AppModel;
import attendance.automation.gui.model.ModelFacade;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author SKRUMM
 */
public class StudentCalenderViewController implements Initializable
{

    @FXML
    private AnchorPane studentCalenderRootpane;
    @FXML
    private RadioButton rbHeretoday;
    @FXML
    private ToggleGroup precent;
    @FXML
    private RadioButton rbNotHeretoday;
    @FXML
    private Button submitprecense;
    @FXML
    private RadioButton rbHeretoday1;
    @FXML
    private ToggleGroup precent1;
    @FXML
    private RadioButton rbNotHeretoday1;
    @FXML
    private Button submitprecense1;
    @FXML
    private RadioButton rbHeretoday2;
    @FXML
    private ToggleGroup precent2;
    @FXML
    private RadioButton rbNotHeretoday2;
    @FXML
    private Button submitprecense2;
    @FXML
    private RadioButton rbHeretoday3;
    @FXML
    private ToggleGroup precent3;
    @FXML
    private RadioButton rbNotHeretoday3;
    @FXML
    private Button submitprecense3;
    @FXML
    private RadioButton rbHeretoday4;
    @FXML
    private ToggleGroup precent4;
    @FXML
    private RadioButton rbNotHeretoday4;
    @FXML
    private Button submitprecense4;
    @FXML
    private Label weekLabel;

    private ModelFacade modelfacade;

    public StudentCalenderViewController()
    {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        try {
            /**
             *  We use get instance instead of new to make sure we use the same appmodel in all classes.
             */
            modelfacade = ModelFacade.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(StudentCalenderViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //A check to see if were woriking with the same instance of appmodel.
        System.out.println("Instance ID: " + System.identityHashCode(modelfacade));
        setWeekLabel();

    }

    /**
     * opens the studentMainView
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handelBackToMainView(ActionEvent event) throws IOException
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendance/automation/gui/view/StudentMainView.fxml"));

        studentCalenderRootpane.getChildren().setAll(pane);

    }

    /**
     * logs out the curent user, and opens the signInView
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void HandleLogout(ActionEvent event) throws IOException
    {
        Window window = studentCalenderRootpane.getScene().getWindow();

        if (window instanceof Stage)
        {
            ((Stage) window).close();
        }
        modelfacade.handelLogout();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handelSubmit(ActionEvent event)
    {
    }

    /**
     * sets the weekLabels text
     */
    private void setWeekLabel()
    {
        String label = "Week " + modelfacade.getCurrentWeek() + " of"
                + " " + modelfacade.getYear();

        weekLabel.setText(label);
    }
}
