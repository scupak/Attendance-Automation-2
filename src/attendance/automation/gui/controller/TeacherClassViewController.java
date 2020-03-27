/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.Student;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.model.Interface.ModelFacadeInterface;
import attendance.automation.gui.model.ModelFacade;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SKRUMM
 */
public class TeacherClassViewController implements Initializable
{

    @FXML
    private TableView<Student> classTableView;
    @FXML
    private Label classNameLabel;
    @FXML
    private Button backButton;
    @FXML
    private PieChart pieChart;

    private ModelFacadeInterface modelfacade;

    @FXML
    public TableColumn<Student, String> name;
    @FXML
    public TableColumn<Student, Integer> absenceProcent;
    @FXML
    public TableColumn<Student, String> dayMostAbsent;

    /**
     * Initializes the controller class.
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
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Teacher class view error!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(TeacherClassViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Given wrong type!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TeacherClassViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //A check to see if were woriking with the same instance of appmodel.
        System.out.println("Instance ID: " + System.identityHashCode(modelfacade));
        try
        {
            populateList();
            fillPieChart();
        } catch (AttendanceAutomationDalException ex)
        {
            JOptionPane.showMessageDialog(null, "Cannot populate list, or fill the pie chart!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(TeacherClassViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        classNameLabel.setText(modelfacade.getCurrentClass().getClassName());
        classNameLabel.setAlignment(Pos.CENTER);
        absenceProcent.setSortType(TableColumn.SortType.DESCENDING);
        classTableView.getSortOrder().addAll(absenceProcent);
    }

    /**
     * Button that sends the user back to the TeacherMainView.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleBack(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherMainView.fxml"));
        Parent root = loader.load();
        TeacherMainViewController TMVController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Attendance - Teacher");
        stage.getScene().getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
        stage.show();

        Stage oldStage = (Stage) backButton.getScene().getWindow();
        oldStage.close();
    }

    /**
     * Populates the TableView.
     */
    private void populateList() throws AttendanceAutomationDalException
    {
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        absenceProcent.setCellValueFactory(new PropertyValueFactory<Student, Integer>("absenceProcent"));
        dayMostAbsent.setCellValueFactory(new PropertyValueFactory<Student, String>("dayMostAbsent"));
        classTableView.setItems(modelfacade.teacherStudentList(modelfacade.getCurrentClass().getClassid()));
    }

    /**
     * Fills the pie chart with information.
     */
    private void fillPieChart() throws AttendanceAutomationDalException
    {
        int totalAbsence = 0;
        for (Student student : modelfacade.teacherStudentList(modelfacade.getCurrentClass().getClassid()))
        {
            totalAbsence = totalAbsence + student.getAbsenceProcent();
        }
        totalAbsence = totalAbsence / modelfacade.teacherStudentList(modelfacade.getCurrentClass().getClassid()).size();

        int totalPresence = 100 - totalAbsence;

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Total Class Absence: " + totalAbsence + "%", totalAbsence),
                new PieChart.Data("Total Class Presence: " + totalPresence + "%", totalPresence)
        );

        pieChart.setData(pieChartData);
        pieChart.setTitle("Class Absence");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(false);
        pieChart.setLegendVisible(true);
        pieChart.setStartAngle(180);
        pieChart.setMinSize(100, 100);

    }

    @FXML
    private void handleOpenStudentsView(MouseEvent event) throws IOException, AttendanceAutomationDalException
    {
        if (event.getClickCount() == 2)
        {
            modelfacade.setCurrentStudent(classTableView.getSelectionModel().getSelectedItem());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentMainView.fxml"));
            Parent root = loader.load();
            StudentMainViewController SCVController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(classTableView.getSelectionModel().getSelectedItem().getName());
            stage.show();
            
            Stage oldstage = (Stage) ((Node) classTableView).getScene().getWindow();
            oldstage.close();
            
        }
    }

}
