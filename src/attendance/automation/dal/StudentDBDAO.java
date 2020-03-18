/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.dal.Interface.StudentDBDAOInterface;
import attendance.automation.be.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import attendance.automation.be.StudentDay;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lumby
 */
public class StudentDBDAO implements StudentDBDAOInterface
{

    
   
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
                returnstudent = new Student(name, username, password, absence, dayMostAbsent, classID);
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
        
       Student s = new Student("hello", "rwebleya", "MckxbMH", 0, "sgp", 0);
//        
//        for (Student student : test.getAllStudents())
//        {
//            System.out.println(student);
//        }
            

        System.out.println(test.getStudent(s));
        
    }
    
    @Override
    public boolean checkDay(String username) throws AttendanceAutomationDalException
    {
        try(Connection con = dbcon.getConnection())
        {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            
            String sql = "SELECT status FROM [Student_day] "
                    + "JOIN [Day] ON Student_day.dayId = Day.id "
                    + "WHERE Student_day.studentUsername = ? "
                    + "AND Day.date = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, username);
            ps.setDate(2, sqlDate);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
                int status = rs.getInt("status");
                
                if(status == 1 || status == 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    /**
     *
     * @param sd
     * @return boolean 
     */
    @Override
     public boolean sendUpdateDayStudent(StudentDay sd)
    {
        return false;
    }

    @Override
    public void setDayStatus(int status, String username) 
    {
        try(Connection con = dbcon.getConnection())
        {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            
            String sql = "SELECT id FROM [Day] WHERE date = ?";
            String sql2 = "INSERT INTO [Student_day] VALUES (?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = con.prepareStatement(sql2);
            
            ps.setDate(1, sqlDate);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                int dayId = rs.getInt("id");
                
                ps2.setString(1, username);
                ps2.setInt(2, dayId);
                ps2.setInt(3, status);
                
                ps2.executeUpdate();
            }
        } 
        catch (SQLException ex)
        {
            System.out.println("SQL Error");
        }
          
    }

    public List<StudentDay> getAllDaysForStudent() throws AttendanceAutomationDalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
