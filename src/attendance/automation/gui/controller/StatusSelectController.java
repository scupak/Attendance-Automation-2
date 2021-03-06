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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            /**
             * We use get instance instead of new to make sure we use the same
             * appmodel in all classes.
             */
            modelfacade = ModelFacade.getInstance();

        } catch (IOException ex)
        {
            Logger.getLogger(SignInViewController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Sign in view controller error!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();

        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Given wrong type!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StatusSelectController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    /**
     * Handles confirming
     *
     * @param event
     */
    @FXML
    private void handleconfirm(ActionEvent event)
    {
        modelfacade.setIsStatusSelectOpen(false);

        try
        {
            if (absentButton.isSelected())
            {

                Stage stage = (Stage) confirmButton.getScene().getWindow();
                //updates database
                modelfacade.sendupdateDayStudent(new StudentDay(date, modelfacade.getCurrentStudent(), StudentDay.notAttendant));

                //updates the  anchorpanenodes studentday
                anchorpanenode.setStudentday(new StudentDay(date, modelfacade.getCurrentStudent(), StudentDay.notAttendant));
                //updates the anchorpanenodes background color
                anchorpanenode.updateAnchorPaneNodeStudentDay();
                stage.close();
            } else if (presentButton.isSelected())
            {

                Stage stage = (Stage) confirmButton.getScene().getWindow();
                //updates database
                modelfacade.sendupdateDayStudent(new StudentDay(date, modelfacade.getCurrentStudent(), StudentDay.attendant));
                //updates the  anchorpanenodes studentday
                anchorpanenode.setStudentday(new StudentDay(date, modelfacade.getCurrentStudent(), StudentDay.attendant));
                //updates the anchorpanenodes background color
                anchorpanenode.updateAnchorPaneNodeStudentDay();
                stage.close();
            } else
            {

                Stage stage = (Stage) confirmButton.getScene().getWindow();
                //updates database
                modelfacade.sendupdateDayStudent(new StudentDay(date, modelfacade.getCurrentStudent(), StudentDay.notSetAtt));
                //updates the  anchorpanenodes studentday
                anchorpanenode.setStudentday(new StudentDay(date, modelfacade.getCurrentStudent(), StudentDay.notSetAtt));
                //updates the anchorpanenodes background color
                anchorpanenode.updateAnchorPaneNodeStudentDay();
                stage.close();
            }

        } catch (AttendanceAutomationDalException ex)
        {
            JOptionPane.showMessageDialog(null, "Cannot update to/from DB!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    /**
     * Handles cancelling
     *
     * @param event
     */
    @FXML
    private void handleCancel(ActionEvent event)
    {
        modelfacade.setIsStatusSelectOpen(false);
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;

        dateLabel.setText(date.toString());
    }

    public AnchorPaneNode getAnchorpanenode()
    {
        return anchorpanenode;
    }

    public void setAnchorpanenode(AnchorPaneNode anchorpanenode)
    {
        this.anchorpanenode = anchorpanenode;
    }

}
