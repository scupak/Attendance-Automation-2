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
public class DALMockFacade implements DALFacadeInterface{
    
    /*lav en liste af students/dage som du kan give til  */

    @Override
    public String getUsernameStudent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUsernameStudent(String usernameStudent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPasswordStudent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPasswordStudent(String passwordStudent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsernameTeacher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUsernameTeacher(String usernameTeacher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPasswordTeacher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPasswordTeacher(String passwordTeacher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public ObservableList teacherClassList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> getAllStudents() throws AttendanceAutomationDalException {
           ObservableList<Student> studentList = FXCollections.observableArrayList(
               new Student("Mads Jensen" , "mads1999","11111" , 16, "Tuesday", 1),
                new Student("Sascha Mikkelsen","sas89898","loli", 28, "Thursday", 1),
                new Student("djkghsl", "mads69", "password", 0, "monday", 0)
               
           
        );

        return studentList;
           
       
    }

    @Override
    public Student getStudent(Student s) throws AttendanceAutomationDalException {
        return new Student("djkghsl", "mads69", "password", 0, "monday", 0);
    }

    @Override
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentDay> getAllDaysForStudent(Student student) throws AttendanceAutomationDalException {
        ObservableList<StudentDay> studentdayList = FXCollections.observableArrayList(
               new StudentDay(LocalDate.of(2020, Month.MARCH, 21), student, 0),
                new StudentDay(LocalDate.of(2020, Month.MARCH, 22), student, 0),
                new StudentDay(LocalDate.of(2020, Month.MARCH, 23), student, 0)
               
           
        );
        
        return studentdayList;
    }

    @Override
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentDay getStudentDay(Student s, LocalDate date) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean TeacherExist(Teacher t) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Class> getTeacherClasses(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Student> teacherStudentList(int classid) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
