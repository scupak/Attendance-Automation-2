/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.enums.UserMode;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SKRUMM
 */
public class StudentChartViewController implements Initializable
{

    @FXML
    private AnchorPane studentChartRootpane;
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private ModelFacadeInterface modelfacade;
    @FXML
    private Label userModeLabel;

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

            if (modelfacade.getCurrentUserMode() == UserMode.TEACHER)
            {
                userModeLabel.setText(modelfacade.getCurrentTeacher().getName() + " is currently in " + "Admin Mode " + "Accesing " + modelfacade.getCurrentStudent().getName() + "s profile");
            }
        } catch (IOException ex)
        {
            Logger.getLogger(StudentChartViewController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Chart view error!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Given wrong type!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StudentChartViewController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        try
        {
            setPieChartData();
            setBarData();
        } catch (AttendanceAutomationDalException ex)
        {
            JOptionPane.showMessageDialog(null, "Cannot get required data!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(StudentChartViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * opens the studentMainView
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handelBackToMainView(ActionEvent event)
    {

        try
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendance/automation/gui/view/StudentMainView.fxml"));

            studentChartRootpane.getChildren().setAll(pane);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Cannot handle back to main view!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StudentChartViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * sets the pie chart
     */
    public void setPieChartData() throws AttendanceAutomationDalException
    {
        pieChart.getData().addAll(modelfacade.setPiechartData(modelfacade.getCurrentStudent()));
        pieChart.setTitle("Total Overview");
    }

    /**
     * sets the bar chart
     */
    public void setBarData() throws AttendanceAutomationDalException
    {
        barChart.setTitle("Week overview");
        barChart.getData().addAll(modelfacade.setPresence(modelfacade.getCurrentStudent()), modelfacade.setAbsent(modelfacade.getCurrentStudent()));

    }

}
