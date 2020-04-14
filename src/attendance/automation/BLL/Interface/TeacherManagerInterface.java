/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL.Interface;

import attendance.automation.be.Student;
import attendance.automation.be.Teacher;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.util.List;
import javafx.collections.ObservableList;
import attendance.automation.be.Class;

/**
 *
 * @author SKRUMM
 */
public interface TeacherManagerInterface
{

    /**
     * Gets the ObservableList of students
     *
     * @param classid
     * @return teacherStudentList
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    public List<Student> getTeacherStudentList(int classid) throws AttendanceAutomationDalException;

    /**
     * checks the username and password for a teacher
     *
     * @param t
     * @return boolean
     * @throws AttendanceAutomationDalException
     */
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException;

    /**
     * gets a teacher
     *
     * @param t
     * @return Teacher
     * @throws AttendanceAutomationDalException
     */
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException;

    /**
     * gets list of class for a teacher
     *
     * @param username
     * @return list of classes
     * @throws AttendanceAutomationDalException
     */
    public ObservableList<Class> getTeacherClasses(String username) throws AttendanceAutomationDalException;

    /**
     * gets all teahcers in database
     *
     * @return Teachers
     * @throws AttendanceAutomationDalException
     */
    public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException;

}
