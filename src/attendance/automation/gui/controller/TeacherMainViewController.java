/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.gui.controller.TeacherClassViewController;
import attendance.automation.gui.model.Interface.ModelFacadeInterface;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import attendance.automation.be.Class;

/**
 * FXML Controller class
 *
 * @author SKRUMM
 */
public class TeacherMainViewController implements Initializable
{

    @FXML
    private ListView<Class> classListView;
    @FXML
    private Button nextButton;

    @FXML
    private Label welcomeMessage;
    @FXML
    private Button logOutButton;

    private ModelFacadeInterface modelfacade;

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
            System.out.println("Current user mode is" + "  " +modelfacade.getCurrentUserMode());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Teacher main view error!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TeacherMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //A check to see if were woriking with the same instance of appmodel.
        System.out.println("Instance ID: " + System.identityHashCode(modelfacade));
        
        populateList(modelfacade.getCurrentTeacher().getUsername());
        welcomeMessage.setText("Welcome " + modelfacade.getCurrentTeacher().getName() + "!");
        welcomeMessage.setAlignment(Pos.CENTER);
    }

    /**
     * Button that sends the user to the next window when the user has chosen a
     * class. If the user has not chosen a class, an alert will pop up.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleNext(ActionEvent event)
    {
        try{
        if (classListView.getSelectionModel().getSelectedItem() != null)
        {
            modelfacade.setCurrentClass(classListView.getSelectionModel().getSelectedItem());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherClassView.fxml"));
            Parent root = loader.load();
            TeacherClassViewController TCVController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Attendance - Teacher");
            stage.getScene().getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
            stage.show();

            Stage oldStage = (Stage) nextButton.getScene().getWindow();
            oldStage.close();
        } else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oops");
            alert.setHeaderText("Oops, something went wrong");
            alert.setContentText("Please select a class to continue");
            alert.showAndWait();
        }
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Cannot read FXML file(s)!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Populates the ListView
     * @param username
     */
    public void populateList(String username)
    {
        classListView.setItems(modelfacade.classList(username));
    }

    /**
     * Button that logs the user out.
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void handleLogOut(ActionEvent event)
    {
        try
        {
            modelfacade.handelLogout();
            Stage oldStage = (Stage) logOutButton.getScene().getWindow();
            oldStage.close();
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Cannot handle logout!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TeacherMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
