/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.gui.model.AppModel;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SKRUMM
 */
public class StatusSelectController implements Initializable 
{

    private AppModel appmodel;
    
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
            appmodel = AppModel.getInstance();
         } 
         catch (IOException ex) 
         {
            Logger.getLogger(SignInViewController.class.getName()).log(Level.SEVERE, null, ex);
         }
        //A check to see if were woriking with the same instance of appmodel.appmodel = AppModel.getInstance();
        System.out.println("Instance ID: " + System.identityHashCode(appmodel));
    }    

    /**
     * Handles confirming
     * @param event 
     */
    @FXML
    private void handleconfirm(ActionEvent event) 
    {
        
        if(absentButton.isSelected())
        {
            System.out.println("absent");
           Stage stage = (Stage) confirmButton.getScene().getWindow();
           stage.close();
        }
        
        else if(presentButton.isSelected())
        {
            System.out.println("present");
           Stage stage = (Stage) confirmButton.getScene().getWindow();
           stage.close();
        }
        
        else
        {
            System.out.println("notset");
           Stage stage = (Stage) confirmButton.getScene().getWindow();
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
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }

}
