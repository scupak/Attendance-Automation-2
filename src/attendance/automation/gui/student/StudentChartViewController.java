/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.student;

import attendance.automation.gui.model.LogOutModel;
import attendance.automation.gui.model.StudentModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @author Charlotte
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
    private StudentModel sm;
    private LogOutModel lom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        sm = new StudentModel();
        lom = new LogOutModel();
        setPieChartData();
        setBarData();

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
        lom.handelLogout();
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

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendance/automation/gui/student/StudentMainView.fxml"));

        studentChartRootpane.getChildren().setAll(pane);
    }

    /**
     * sets the pie chart
     */
    public void setPieChartData()
    {
        pieChart.getData().addAll(sm.setPiechartData());
        pieChart.setTitle("Total Overview");
    }

    /**
     * sets the bar chart
     */
    public void setBarData()
    {
        barChart.setTitle("Week overview");
        barChart.getData().addAll(sm.setPresence(), sm.setAbsent());

    }

}
