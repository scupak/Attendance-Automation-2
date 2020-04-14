/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Class;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.be.Teacher;
import attendance.automation.dal.Interface.DALFacadeInterface;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author anton
 */
public class DALMockFacade implements DALFacadeInterface
{

    public DALMockFacade()
    {
    }

    /*lav en liste af students/dage som du kan give til */
    @Override
    public List<Student> getAllStudents() throws AttendanceAutomationDalException
    {
        ObservableList<Student> studentList = FXCollections.observableArrayList(
                new Student("Mads Jensen", "mads1999", "11111", 16, "Tuesday", 1),
                new Student("Sascha Mikkelsen", "sas89898", "loli", 28, "Thursday", 1),
                new Student("djkghsl", "mads69", "password", 0, "monday", 0)
        );

        return studentList;

    }

    /**
     * gets a student
     *
     * @param s
     * @return student
     * @throws AttendanceAutomationDalException
     */
    @Override
    public Student getStudent(Student s) throws AttendanceAutomationDalException
    {
        return new Student("djkghsl", "mads69", "password", 0, "monday", 0);
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param s
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param username
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param sd
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param status
     * @param username
     * @throws AttendanceAutomationDalException
     */
    @Override
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * gets a list of days for a student
     *
     * @param student
     * @return studentDays
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForStudent(Student student) throws AttendanceAutomationDalException
    {
        ObservableList<StudentDay> studentdayList = FXCollections.observableArrayList(
                new StudentDay(LocalDate.of(2020, Month.MARCH, 20), student, 1),
                new StudentDay(LocalDate.of(2020, Month.MARCH, 23), student, 0),
                new StudentDay(LocalDate.of(2020, Month.MARCH, 24), student, 0)
        );

        return studentdayList;
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param username
     * @param date
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param s
     * @param date
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public StudentDay getStudentDay(Student s, LocalDate date) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param t
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param t
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean TeacherExist(Teacher t) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param username
     * @return
     */
    @Override
    public ObservableList<Class> getTeacherClasses(String username)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param classid
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<Student> teacherStudentList(int classid) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param currentStudent
     * @param absenceProcentforstudent
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param currentStudent
     * @param mostabsentdayforstudent
     * @return
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * not used, but needed to implement interdaces
     *
     * @param currentStudent
     * @param date
     * @param date0
     * @return
     */
    @Override
    public List<StudentDay> getAllDaysForStudent(Student currentStudent, LocalDate date, LocalDate date0)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
