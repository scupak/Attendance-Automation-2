/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Teacher;
import attendance.automation.be.Class;
import attendance.automation.be.Student;
import attendance.automation.dal.Interface.TeacherDBDAOInterface;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author lumby
 */
public class TeacherDBDAO implements TeacherDBDAOInterface
{
    
    private final DatabaseConnector dbCon;
    
    public TeacherDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
    /**
     * gets a list of all teachers in database
     * @return
     * @throws AttendanceAutomationDalException 
     */
     public List<Teacher> getAllTeachers() throws AttendanceAutomationDalException
    {
        ArrayList<Teacher> students = new ArrayList<>();

        try (Connection con = dbCon.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Teacher");
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                students.add(new Teacher(name, username, password));

            }
            return students;
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Could not get all students from database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("could not get all students from database", ex);
        }
    }
     /**
      * get a specific teacher based on username
      * @param t
      * @return
      * @throws AttendanceAutomationDalException 
      */
     public Teacher getTeacher(Teacher t) throws AttendanceAutomationDalException
    {
        if (!TeacherExist(t))
        {
            return null;
        }

        Teacher returnTeacher;
        try (Connection con = dbCon.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Teacher Where username = ?");

            ps.setString(1, t.getUsername());

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                returnTeacher = new Teacher(name, username, password);
            } else
            {
                return null;
            }

            return returnTeacher;

        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not find the student in the dataabase!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new AttendanceAutomationDalException("could not find the student in the dataabase", ex);
        }
    }

    /**
     * checks if a Teacher exist in the databases
     * @param t
     * @return boolean
     * @throws AttendanceAutomationDalException 
     */
    public boolean TeacherExist(Teacher t) throws AttendanceAutomationDalException
    {
        try (Connection con = dbCon.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Teacher WHERE username = ? ");
            ps.setString(1, t.getUsername());

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                return true;
            }

            return false;
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not find the teacher in the dataabase!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new AttendanceAutomationDalException("could not find the Teacher in the dataabase", ex);
        }
    }
    
    @Override
    public ObservableList<Class> getTeacherClasses(String username)
    {
        ObservableList<Class> classes = FXCollections.observableArrayList();
        
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT Class.className, Class.classID "
                        + "FROM [Class] "
                        + "JOIN [ClassTeacher] "
                        + "ON Class.classID = ClassTeacher.classID "
                        + "WHERE ClassTeacher.teacherUsername = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                String name = rs.getString("className");
                int id = rs.getInt("classID");
                
                System.out.println(name);
                System.out.println(id);
                
                classes.add(new Class(name, id));
            }
            
        }
        catch (SQLException ex)
        {
                    
        }
        
        return classes;
    }
    @Override
     public List<Student>  teacherStudentList(int classIdInClass) throws AttendanceAutomationDalException
    {
         ObservableList<Student> students = FXCollections.observableArrayList();

        try (Connection con = dbCon.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT Class.className, Class.classID ,Student.username,Student.name,Student.password,Student.absenceProcent,Student.dayMostAbsent "
                                                      + "FROM [Class] "
                                                      + "JOIN [Student] " 
                                                      + "ON Class.classID = Student.classID "
                                                      + "WHERE Class.classID = ?");
            
            ps.setInt(1, classIdInClass);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int absence = rs.getInt("absenceProcent");
                String dayMostAbsent = rs.getString("dayMostAbsent");
                int classID = rs.getInt("classID");
                students.add(new Student(name, username, password, absence, dayMostAbsent, classID));

            }
            return students;
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Could not get all students from database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("could not get all students from database", ex);
        }
    }
    
    public static void main(String[] args) throws IOException, AttendanceAutomationDalException {
        TeacherDBDAO tb = new TeacherDBDAO();
        
        //System.out.println(tb.getTeacherClasses("jeppe123"));
//
//        for (Student allStudentsInClas : tb.getAllStudentsInClass(2)) {
//
//            System.out.println(allStudentsInClas);
//        }

    }
    
}
