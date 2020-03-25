/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.controller.calendar.AnchorPaneNode;
import attendance.automation.gui.model.Interface.ModelFacadeInterface;
import attendance.automation.gui.model.ModelFacade;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SKRUMM
 */
public class StatusSelectController implements Initializable 
{

    private ModelFacadeInterface modelfacade;
    
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelbutton;
    @FXML
    private RadioButton presentButton;
    @FXML
    private ToggleGroup toggleAbsence;
    @FXML
    private RadioButton absentButton;
    @FXML
    private RadioButton notSetButton;
    
    private LocalDate date;
    @FXML
    private Label dateLabel;
    
    private AnchorPaneNode anchorpanenode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
         try 
         {
            /**
             *  We use get instance instead of new to make sure we use the same appmodel in all classes.
             */
            modelfacade = ModelFacade.getInstance();
            
         } 
         catch (IOException ex) 
         {
            Logger.getLogger(SignInViewController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Sign in view controller error!", "Error", JOptionPane.ERROR_MESSAGE);
         }
        //A check to see if were woriking with the same instance of appmodel.appmodel = AppModel.getInstance();
        System.out.println("Instance ID: " + System.identityHashCode(modelfacade));
        
    }    

    /**
     * Handles confirming
     * @param event 
     */
    @FXML
    private void handleconfirm(ActionEvent event) throws AttendanceAutomationDalException 
    {
        modelfacade.setIsStatusSelectOpen(false);
        
        if(absentButton.isSelected())
        {
            System.out.println("absent");
           Stage stage = (Stage) confirmButton.getScene().getWindow();
           modelfacade.sendupdateDayStudent(new StudentDay(date, modelfacade.getCurrentStudent(),  StudentDay.notAttendant));
           anchorpanenode.updateAnchorPaneNodeStudentDay();
           stage.close();
        }
        
        else if(presentButton.isSelected())
        {
            System.out.println("present");
           Stage stage = (Stage) confirmButton.getScene().getWindow();
           modelfacade.sendupdateDayStudent(new StudentDay(date, modelfacade.getCurrentStudent(),  StudentDay.attendant));
           anchorpanenode.updateAnchorPaneNodeStudentDay();
           stage.close();
        }
        
        else
        {
            System.out.println("notset");
           Stage stage = (Stage) confirmButton.getScene().getWindow();
           modelfacade.sendupdateDayStudent(new StudentDay(date, modelfacade.getCurrentStudent(),  StudentDay.notSetAtt));
           anchorpanenode.updateAnchorPaneNodeStudentDay();
           stage.close();
        }
        
    }

    /**
     * Handles cancelling
     * @param event 
     */
    @FXML
    private void handleCancel(ActionEvent event) 
    {
        modelfacade.setIsStatusSelectOpen(false);
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        
        dateLabel.setText(date.toString());
    }

    public AnchorPaneNode getAnchorpanenode() {
        return anchorpanenode;
    }

    public void setAnchorpanenode(AnchorPaneNode anchorpanenode) {
        this.anchorpanenode = anchorpanenode;
    }
    
    
    
    

}
