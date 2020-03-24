/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.Student;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.controller.StudentMainViewController;
import attendance.automation.gui.model.Interface.ModelFacadeInterface;
import attendance.automation.gui.model.ModelFacade;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author SKRUMM
 */
public class SignInViewController implements Initializable
{

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button signInButton;
    @FXML
    private Label label;

    private List<Student> StudentList;
    private ModelFacadeInterface modelfacade;
    public Student stud;

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
            Logger.getLogger(SignInViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //A check to see if were woriking with the same instance of appmodel.appmodel = AppModel.getInstance();
        System.out.println("Instance ID: " + System.identityHashCode(modelfacade));

    }

    /**
     * handels the sign in function, checks if the person signing in is a
     * teacher or a student. If it the person does not exist, it gives an alert
     * to the user
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleSignIn(ActionEvent event) throws IOException, AttendanceAutomationDalException
    {

        String user = username.getText();
        String pass = password.getText();
        
        //login with database
        Student s = new Student("hello", user, pass, 0, "everyday", 0);
        
        Stage signInView = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (modelfacade.checkCredStudent(s) == true )
        {
            modelfacade.setCurrentStudent(s);
            
            FXMLLoader fxmlLoader = new FXMLLoader();

            Parent root;
            root = (Parent) fxmlLoader.load(getClass().getResource("/attendance/automation/gui/view/StudentMainView.fxml").openStream());
            StudentMainViewController cont = (StudentMainViewController) fxmlLoader.getController();
            
            Stage stage = new Stage();
            stage.setTitle("Attendance - Student");
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
            stage.show();
            signInView.close();
        } else if (user.toLowerCase().equals(modelfacade.getTeahcerUsername()) && pass.equals(modelfacade.getTeacherPassword()))
        {
            FXMLLoader fxmlLoader = new FXMLLoader();

            Parent root = (Parent) fxmlLoader.load(getClass().getResource("/attendance/automation/gui/view/TeacherMainView.fxml").openStream());
            TeacherMainViewController cont = (TeacherMainViewController) fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle("Attendance - Teacher");
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
            stage.show();
            signInView.close();
        } else
        {
            FXMLLoader fxmlLoader = new FXMLLoader();

            /*Parent root = (Parent) fxmlLoader.load(getClass().getResource("/attendance/automation/gui/view/StatusSelect.fxml").openStream());
            StatusSelectController cont = (StatusSelectController) fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle("Attendance - Select");
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
            stage.show();*/
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oops");
            alert.setHeaderText("Oops, something went wrong");
            alert.setContentText("Wrong username or password, please try again");
            alert.showAndWait();
        }

    }

}
