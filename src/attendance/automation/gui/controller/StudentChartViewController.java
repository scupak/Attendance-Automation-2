/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.model.AppModel;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    private HBox hBox;
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
        } catch (IOException ex) {
            Logger.getLogger(StudentChartViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //A check to see if were woriking with the same instance of appmodel.
        System.out.println("Instance ID: " + System.identityHashCode(modelfacade));
        try
        {
            setPieChartData();
            setBarData();
        } catch (AttendanceAutomationDalException ex)
        {
            Logger.getLogger(StudentChartViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

    /**
     * logs out the current user, and opens the signInView
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void HandleLogout(ActionEvent event) throws IOException
    {
        Window window = studentChartRootpane.getScene().getWindow();

        if (window instanceof Stage)
        {
            ((Stage) window).close();
        }
        modelfacade.handelLogout();
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

        studentChartRootpane.getChildren().setAll(pane);
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
