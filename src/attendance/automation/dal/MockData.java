/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Charlotte
 */
public class MockData
{

    private String usernameStudent;
    private String passwordStudent;
    private String usernameTeacher;
    private String passwordTeacher;

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
     * @return
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
     * Get techer username
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
                new Student("Mads Jensen", 16, "Tuesday"),
                new Student("Sascha Mikkelsen", 28, "Thursday"),
                new Student("Karl Jensen", 25, "Monday"),
                new Student("Pernille Carlsen", 78, "Friday"),
                new Student("Anders Davidsen", 12, "Monday"),
                new Student("Rebecca Friis", 54, "Wednesday"),
                new Student("Margret Grief", 98, "Tuesday")
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

}
