/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.DALMockFacade;
import java.time.LocalDate;
import java.util.List;
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

    /**
     * Test of getUsernameStudent method, of class StudentManager.
     */
//    @Test
//    public void testGetUsernameStudent() {
//        System.out.println("getUsernameStudent");
//        StudentManager instance = null;
//        String expResult = "";
//        String result = instance.getUsernameStudent();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPasswordStudent method, of class StudentManager.
//     */
//    @Test
//    public void testGetPasswordStudent() {
//        System.out.println("getPasswordStudent");
//        StudentManager instance = null;
//        String expResult = "";
//        String result = instance.getPasswordStudent();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkCurrentDay method, of class StudentManager.
//     */
//    @Test
//    public void testCheckCurrentDay() throws Exception {
//        System.out.println("checkCurrentDay");
//        String username = "";
//        StudentManager instance = null;
//        int expResult = 0;
//        int result = instance.checkCurrentDay(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getallStudents method, of class StudentManager.
//     */
//    @Test
//    public void testGetallStudents() throws Exception {
//        System.out.println("getallStudents");
//        StudentManager instance = null;
//        List<Student> expResult = null;
//        List<Student> result = instance.getallStudents();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkCredStudent method, of class StudentManager.
//     */
    @Test
    public void testCheckCredStudent() throws Exception {
        System.out.println("checkCredStudent");
        Student s = new Student("djkghsl", "mads69", "password", 0, "monday", 0);
        StudentManager instance = new StudentManager(new DALMockFacade());
        boolean expResult = true;
        boolean result = instance.checkCredStudent(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of getStudent method, of class StudentManager.
//     */
//    @Test
//    public void testGetStudent() throws Exception {
//        System.out.println("getStudent");
//        Student s = null;
//        StudentManager instance = null;
//        Student expResult = null;
//        Student result = instance.getStudent(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of main method, of class StudentManager.
//     */
//    @Test
//    public void testMain() throws Exception {
//        System.out.println("main");
//        String[] args = null;
//        StudentManager.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of sendUpdateDayStudent method, of class StudentManager.
//     */
//    @Test
//    public void testSendUpdateDayStudent() throws Exception {
//        System.out.println("sendUpdateDayStudent");
//        StudentDay sd = null;
//        StudentManager instance = null;
//        boolean expResult = false;
//        boolean result = instance.sendUpdateDayStudent(sd);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDayStatus method, of class StudentManager.
//     */
//    @Test
//    public void testSetDayStatus() throws Exception {
//        System.out.println("setDayStatus");
//        int status = 0;
//        String username = "";
//        StudentManager instance = null;
//        instance.setDayStatus(status, username);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAllDaysForAstudent method, of class StudentManager.
//     */
//    @Test
//    public void testGetAllDaysForAstudent() throws Exception {
//        System.out.println("getAllDaysForAstudent");
//        Student student = null;
//        StudentManager instance = null;
//        List<StudentDay> expResult = null;
//        List<StudentDay> result = instance.getAllDaysForAstudent(student);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of doesStudentDayExist method, of class StudentManager.
//     */
//    @Test
//    public void testDoesStudentDayExist() throws Exception {
//        System.out.println("doesStudentDayExist");
//        String username = "";
//        LocalDate date = null;
//        StudentManager instance = null;
//        boolean expResult = false;
//        boolean result = instance.doesStudentDayExist(username, date);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getStudentDay method, of class StudentManager.
//     */
//    @Test
//    public void testGetStudentDay() throws Exception {
//        System.out.println("getStudentDay");
//        Student s = null;
//        LocalDate date = null;
//        StudentManager instance = null;
//        StudentDay expResult = null;
//        StudentDay result = instance.getStudentDay(s, date);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of pieChartData method, of class StudentManager.
//     */
//    @Test
//    public void testPieChartData() throws Exception {
//        System.out.println("pieChartData");
//        Student s = null;
//        int attendanceStatusCheck = 0;
//        StudentManager instance = null;
//        double expResult = 0.0;
//        double result = instance.pieChartData(s, attendanceStatusCheck);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setPresence method, of class StudentManager.
//     */
//    @Test
//    public void testSetPresence() throws Exception {
//        System.out.println("setPresence");
//        Student s = null;
//        int attendanceStatusCheck = 0;
//        String columName = "";
//        StudentManager instance = null;
//        XYChart.Series expResult = null;
//        XYChart.Series result = instance.setPresence(s, attendanceStatusCheck, columName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
