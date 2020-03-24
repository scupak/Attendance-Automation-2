/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.BLL.BLLFacade;
import attendance.automation.be.Student;
import attendance.automation.gui.model.Interface.MockModelInterface;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author kacpe
 */
public class MockModel implements MockModelInterface
{
    private BLLFacade bllfacade;
     private ObservableList<PieChart.Data> pieChartData;
    
    public MockModel() throws IOException
    {
        bllfacade = new BLLFacade();
    }
    
    
    /**
     * Gets the list of teachers
     *
     * @return the list of teachers
     */
    @Override
    public ObservableList classList() {
        return bllfacade.getTeacherClassList();
    }
    
    
    /**
     * Gets the list of students
     *
     * @return the list of students
     */
    @Override
    public ObservableList<Student> studentList() {
        return bllfacade.getTeacherStudentList();
    }
    
    
     /**
     * updates existing Data-Object if name matches
     */
    @Override
    public void addData() {
        for (PieChart.Data d : pieChartData) {
            if (d.getName().equals("Absent")) {
                d.setPieValue(60);
                return;
            }
        }
    }
    
    
    /**
     * Gets teacher username
     *
     * @return the teachers username
     */
    @Override
    public String getTeahcerUsername() {
       return bllfacade.getUsernameTeacher();
    }
    
    
     /**
     * Gets teacher password
     *
     * @return the teachers password
     */
    @Override
    public String getTeacherPassword() {
         return bllfacade.getPasswordTeacher();
    }
    
    
    /**
     * Gets student username
     *
     * @return the students user name
     */
    @Override
    public String getStudentUsername() {
        return bllfacade.getUsernameStudent();
    }
    
    
    /**
     * Gets student password
     *
     * @return the students password
     */
    @Override
    public String getStudentPassword() {
        return bllfacade.getPasswordStudent();
    }
    
}
