/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.dal.Interface.MockDataInterface;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SKRUMM
 */
public class MockData implements MockDataInterface
{

    private String usernameStudent;
    private String passwordStudent;
    private String usernameTeacher;
    private String passwordTeacher;
    private StudentDay sd;

    public MockData()
    {
        usernameStudent = "mads";
        passwordStudent = "jensen";
        usernameTeacher = "jeppe";
        passwordTeacher = "baby";
        
       

    }

    /**
     * Get student username
     *
     * @return usernameStudent
     */
    public String getUsernameStudent()
    {
        return usernameStudent;
    }

    /**
     * Set student username
     *
     * @param usernameStudent
     */
    public void setUsernameStudent(String usernameStudent)
    {
        this.usernameStudent = usernameStudent;
    }

    /**
     * Get student password
     *
     * @return student password
     */
    public String getPasswordStudent()
    {
        return passwordStudent;
    }

    /**
     * Set student password
     *
     * @param passwordStudent
     */
    public void setPasswordStudent(String passwordStudent)
    {
        this.passwordStudent = passwordStudent;
    }

    /**
     * Get teacher username
     *
     * @return usernameTeacher
     */
    public String getUsernameTeacher()
    {
        return usernameTeacher;
    }

    /**
     * Set teacher username
     *
     * @param usernameTeacher
     */
    public void setUsernameTeacher(String usernameTeacher)
    {
        this.usernameTeacher = usernameTeacher;
    }

    /**
     * Get teacher password
     *
     * @return passwordTeacher
     */
    public String getPasswordTeacher()
    {
        return passwordTeacher;
    }

    /**
     * Sets teacher password
     *
     * @param passwordTeacher
     */
    public void setPasswordTeacher(String passwordTeacher)
    {
        this.passwordTeacher = passwordTeacher;
    }

    /**
     * Create and add students to an ObservableLIst
     *
     * @return TeacherStudentList
     */
    public ObservableList<Student> teacherStudentList()
    {
        ObservableList<Student> studentList = FXCollections.observableArrayList(
               new Student("Mads Jensen" , "mads1999","11111" , 16, "Tuesday", 1),
                new Student("Sascha Mikkelsen","sas89898","loli", 28, "Thursday", 1)
               
           
        );

        return studentList;
   }

    /**
     * Create and add classes to an ObservableList
     *
     * @return teacherClassList
     */
    public ObservableList teacherClassList()
    {
        ObservableList<String> teacherClassList = FXCollections.observableArrayList();
        teacherClassList.add("CSe2019A");

        return teacherClassList;
    }
    
    
    public static void main(String[] args) {
        
        StudentDay sd = new StudentDay(LocalDate.now(), new Student("Sascha Mikkelsen","sas89898","loli", 28, "Thursday", 1),StudentDay.notAttendant);
        
        
        System.out.println(sd.getDate());
        System.out.println(sd.getStudent());
        System.out.println(sd.getAttendanceStatus());
        
    }

    /**
     * Checks the day
     * @return false
     */
    public boolean checkDay()
    {
        return false;
    }

}
