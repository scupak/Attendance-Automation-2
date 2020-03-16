/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.dal.Interface.StudentDBDAOInterface;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lumby
 */
public class StudentDBDAO implements StudentDBDAOInterface
{

    public boolean sendUpdateDayStudent(StudentDay sd)
    {
        return false;
    }
    private final DatabaseConnector dbcon;
    
    public StudentDBDAO() throws IOException 
    {
        dbcon = new DatabaseConnector();
    }
    
    public List<Student> getAllStudents() throws AttendanceAutomationDalException
    {
        ArrayList<Student> students = new ArrayList<>();
        
        try(Connection con =dbcon.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int absence = rs.getInt("absenceProcent");
                String dayMostAbsent = rs.getString("dayMostAbsent");
                int classID = rs.getInt("classID");
                students.add(new Student(username, name, password, absence,dayMostAbsent, classID));
                
            }
            return students;
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new AttendanceAutomationDalException("could not get all students from database", ex);
        }
    }
    
    public Student getStudent(Student s) throws AttendanceAutomationDalException
    {
        if(!StudentExist(s)) return null;
        
        Student returnstudent;
     try(Connection con =dbcon.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student Where username = ?");
            
            ps.setString(1, s.getUsername());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int absence = rs.getInt("absenceProcent");
                String dayMostAbsent = rs.getString("dayMostAbsent");
                int classID = rs.getInt("classID");
                returnstudent = new Student(username, name, password, absence, dayMostAbsent, classID);
            }else
            {
                return null;
            }
            
            return returnstudent;
            
            
            
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new AttendanceAutomationDalException("could not find the student in the dataabase", ex);
        }
    }
    
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException
            {
        try (Connection con =dbcon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student WHERE username = ? ");
            ps.setString(1, s.getUsername());
           
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                return true;
            }
            
            return false;
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new AttendanceAutomationDalException("could not find the student in the dataabase", ex);
        }
            }
    
    public static void main(String[] args) throws IOException, AttendanceAutomationDalException
    {
        StudentDBDAO test = new StudentDBDAO();
        
       Student s = new Student("hello", "rwebleya", "dsængko", 0, "sgp", 0);
//        
//        for (Student student : test.getAllStudents())
//        {
//            System.out.println(student);
//        }
            

        System.out.println(test.getStudent(s));
        
    }
}
