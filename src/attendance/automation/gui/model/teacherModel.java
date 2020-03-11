/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.BLL.BLLManager;
import attendance.automation.be.Student;
import javafx.collections.ObservableList;

/**
 *
 * @author Christina
 */
public class teacherModel
{

    private BLLManager bll;

    public teacherModel()
    {
        bll = new BLLManager();
    }

    /**
     * Gets the list of teachers
     *
     * @return
     */
    public ObservableList classList()
    {
        return bll.getTeacherClassList();
    }

    /**
     * Gets the list of students
     *
     * @return
     */
    public ObservableList<Student> studentList()
    {
        return bll.getTeacherStudentList();
    }

    /**
     * Gets teacher username
     *
     * @return
     */
    public String getUsername()
    {
        return bll.getUsernameTeacher();
    }

    /**
     * Gets teacher password
     *
     * @return
     */
    public String getPassword()
    {
        return bll.getPasswordTeacher();
    }

}
