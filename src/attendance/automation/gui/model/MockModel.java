/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.BLL.BLLFacade;
import attendance.automation.BLL.Interface.BLLFacadeInterface;
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
    private BLLFacadeInterface bllfacade;
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
//    @Override
//    public ObservableList classList(String username) {
//        return bllfacade.getTeacherClassList();
//    }
    
    
    /**
     * Gets the list of students
     *
     * @return the list of students
     */
//    @Override
//    public ObservableList<Student> studentList() throws AttendanceAutomationDalException 
//    {
//        ObservableList<Student> students = FXCollections.observableArrayList();
//        for (Student student : bllfacade.getTeacherStudentList())
//        {
//            students.add(student);
//        }
//        return students;
//    }
    
    
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
