/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.be.Teacher;
import attendance.automation.dal.Interface.DALFacadeInterface;
import attendance.automation.dal.Interface.StudentDBDAOInterface;
import attendance.automation.dal.Interface.TeacherDBDAOInterface;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import attendance.automation.be.Class;

/**
 *
 * @author SKRUMM
 */
public class DALFacade implements DALFacadeInterface
{

    StudentDBDAOInterface studentdbdao;
    TeacherDBDAOInterface teacherdbdao;

    public DALFacade() throws IOException, Exception
    {
        studentdbdao = new StudentDBDAO();
        teacherdbdao = new TeacherDBDAO();
    }

    /**
     * Gets a list of all students in database
     *
     * @return students list
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<Student> getAllStudents() throws AttendanceAutomationDalException
    {
        return studentdbdao.getAllStudents();
    }

    /**
     * Get a specific student based on username
     *
     * @param s
     * @return returnStudent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public Student getStudent(Student s) throws AttendanceAutomationDalException
    {
        return studentdbdao.getStudent(s);
    }

    /**
     * Checks if a student exists in the databases
     *
     * @param s
     * @return boolean
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException
    {
        return studentdbdao.StudentExist(s);
    }

    /**
     * TODO make a method that chekcs if the studentDay exists
     *
     * @param username
     * @return the current day
     * @throws AttendanceAutomationDalException
     */
    @Override
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        return studentdbdao.checkCurrentDay(username);
    }

    /**
     * Sends an update from DayStudent between layers
     *
     * @param sd
     * @return boolean
     */
    @Override
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException
    {
        return studentdbdao.sendUpdateDayStudent(sd);
    }

    /**
     * Make it so this method only updates the day and does not make a new day
     *
     * @param status
     * @param username
     */
    @Override
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException
    {

        studentdbdao.setDayStatus(status, username);
    }

    /**
     * Get a list of days for a student
     *
     * @param student
     * @return studentDays
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForStudent(Student student) throws AttendanceAutomationDalException
    {
        return studentdbdao.getAllDaysForStudent(student);
    }

    /**
     * gets all the days for a student between to given days
     *
     * @param currentStudent
     * @param date
     * @param date0
     * @return studentDays
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForStudent(Student currentStudent, LocalDate date, LocalDate date0) throws AttendanceAutomationDalException
    {
        return studentdbdao.getAllDaysForStudent(currentStudent, date, date0);
    }

    /**
     * Checks if the student day exists
     *
     * @param username
     * @param date
     * @return if the student day exists
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException
    {
        return studentdbdao.doesStudentDayExist(username, date);
    }

    /**
     * Gets the student day
     *
     * @param s
     * @param date
     * @return the student day
     * @throws AttendanceAutomationDalException
     */
    @Override
    public StudentDay getStudentDay(Student s, LocalDate date) throws AttendanceAutomationDalException
    {
        return studentdbdao.getStudentDay(s, date);
    }

    /**
     * gets a list of all teachers in database
     *
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException
    {
        return teacherdbdao.getAllTeachers();
    }

    /**
     * get a specific teacher based on username
     *
     * @param t
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        return teacherdbdao.getTeacher(t);
    }

    /**
     * checks if a Teacher exist in the databases
     *
     * @param t
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean TeacherExist(Teacher t) throws AttendanceAutomationDalException
    {
        return teacherdbdao.TeacherExist(t);
    }

    @Override
    public ObservableList<Class> getTeacherClasses(String username) throws AttendanceAutomationDalException
    {
        return teacherdbdao.getTeacherClasses(username);
    }

    @Override
    public List<Student> teacherStudentList(int classid) throws AttendanceAutomationDalException
    {
        return teacherdbdao.teacherStudentList(classid);
    }

    @Override
    public boolean updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException
    {
        return studentdbdao.updateStudentabsenceProcent(currentStudent, absenceProcentforstudent);
    }

    @Override
    public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException
    {
        return studentdbdao.updateStudentMostAbsentDay(currentStudent, mostabsentdayforstudent);
    }

}
