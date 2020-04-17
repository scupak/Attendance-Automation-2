/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.be.Student;
import attendance.automation.dal.DALFacadeFactory;
import java.util.Arrays;
import javafx.scene.chart.XYChart;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anton
 */
public class StudentManagerTest {
    
    public StudentManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
//    /**
//     * Test of checkCredStudent method, of class StudentManager.
//     */
    @Test
    public void testCheckCredStudent() throws Exception {
        //Arrange
        System.out.println("checkCredStudent");
        Student s = new Student("djkghsl", "mads69", "password", 0, "monday", 0);
        StudentManager instance = new StudentManager(DALFacadeFactory.CreateDALFacade(DALFacadeFactory.DALFacadeTypes.MOCK));
        boolean expResult = true;
        //Act
        boolean result = instance.checkCredStudent(s);
        //Assert
        assertEquals(expResult, result);        
    }

    /**
     * Test of pieChartData method, of class StudentManager.
     */
    @Test
    public void testPieChartData() throws Exception {
        //Arrange
        System.out.println("pieChartData");
        Student s  = new Student("djkghsl", "mads69", "password", 0, "monday", 0);
        int attendanceStatusCheck = 0;
        StudentManager instance = new StudentManager(DALFacadeFactory.CreateDALFacade(DALFacadeFactory.DALFacadeTypes.MOCK));
        double expResult = 66.6;
        //Act
        double result = instance.pieChartData(s, attendanceStatusCheck);
        //Assert
        assertEquals(expResult, result,  1E-1);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setPresence method, of class StudentManager.
     */
    @Test
    public void testSetPresence() throws Exception {
        
        //Arrange
        System.out.println("setPresence");
        Student s = new Student("djkghsl", "mads69", "password", 0, "monday", 0);
        int attendanceStatusCheck = 0;
        String columName = "";
        StudentManager instance = new StudentManager(DALFacadeFactory.CreateDALFacade(DALFacadeFactory.DALFacadeTypes.MOCK));
        
        XYChart.Series expResult = new XYChart.Series<>();
        
        
        expResult.setName(columName);
        expResult.getData().add(new XYChart.Data("Monday", 1));
        expResult.getData().add(new XYChart.Data("Tuesday", 1));
        expResult.getData().add(new XYChart.Data("Wednesday", 0));
        expResult.getData().add(new XYChart.Data("Thursday",0));
        expResult.getData().add(new XYChart.Data("Friday", 0));
        
        
        
        //Act
        XYChart.Series result = instance.setPresence(s, attendanceStatusCheck, columName);
     
        //Assert
        System.out.println(Arrays.toString(expResult.getData().toArray()));
        System.out.println(Arrays.toString(result.getData().toArray()));
        
        
        assertEquals(Arrays.toString(expResult.getData().toArray()), Arrays.toString(result.getData().toArray()));
       
    }
    
    
     @Test
    public void getmostabsentdayforstudent() throws Exception{
        
    //Arrange    
    System.out.println("getmostabsentdayforstudent");
    Student s = new Student("djkghsl", "mads69", "password", 0, "monday", 0);
    StudentManager instance = new StudentManager(DALFacadeFactory.CreateDALFacade(DALFacadeFactory.DALFacadeTypes.MOCK));
    String expResult = "monday tuesday";
    //Act 
    String result = instance.getmostabsentdayforstudent(s);
    //Assert
         assertEquals(expResult, result);
    
    }
    
    
}
