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
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lumby
 */
public class StudentDBDAO implements StudentDBDAOInterface
{
    final static int ABSENT = 0;
    final static int PRESENT = 1;
    final static int UNKNOWN = -1;
    final static int DAY_OFF = 2;
    

    private final DatabaseConnector dbcon;

    public StudentDBDAO() throws IOException
    {
        dbcon = new DatabaseConnector();
    }

    public List<Student> getAllStudents() throws AttendanceAutomationDalException
    {
        ArrayList<Student> students = new ArrayList<>();

        try (Connection con = dbcon.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student");
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int absence = rs.getInt("absenceProcent");
                String dayMostAbsent = rs.getString("dayMostAbsent");
                int classID = rs.getInt("classID");
                students.add(new Student(username, name, password, absence, dayMostAbsent, classID));

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
        if (!StudentExist(s))
        {
            return null;
        }

        Student returnstudent;
        try (Connection con = dbcon.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student Where username = ?");

            ps.setString(1, s.getUsername());

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int absence = rs.getInt("absenceProcent");
                String dayMostAbsent = rs.getString("dayMostAbsent");
                int classID = rs.getInt("classID");
                returnstudent = new Student(name, username, password, absence, dayMostAbsent, classID);
            } else
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
        try (Connection con = dbcon.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student WHERE username = ? ");
            ps.setString(1, s.getUsername());

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
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
    
        for (StudentDay day : test.getAllDaysForStudent(s)) {
             System.out.println(day);
        }
           

        
        
    }
    /*TODO make a method that chekcs if the studentDay exists */
    @Override
    public int checkDay(String username) throws AttendanceAutomationDalException
    {
        try (Connection con = dbcon.getConnection())
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

            if (rs != null)
            {
                while (rs.next())
                {
                    int status = rs.getInt("status");

                    if (status == 0)
                    {
                        return ABSENT;
                    } 
                    else if(status == 1)
                    {
                        return PRESENT;
                    }
                    else if (status == -1)
                    {
                        return UNKNOWN;
                    }
                }
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DAY_OFF;
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
    /*make it so this method only updates the day and does not make a new day*/
    @Override
    public void setDayStatus(int status, String username)
    {
        try (Connection con = dbcon.getConnection())
        {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String sql = "SELECT id FROM [Day] WHERE date = ?";
            String sql2 = "UPDATE [Student_day] SET status = ? WHERE username = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = con.prepareStatement(sql2);

            ps.setDate(1, sqlDate);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int dayId = rs.getInt("id");

                ps2.setInt(1, status);
                ps2.setString(2, username);

                ps2.executeUpdate();
            }
        } catch (SQLException ex)
        {
            System.out.println("SQL Error");
        }

    }

    public List<StudentDay> getAllDaysForStudent(Student student) throws AttendanceAutomationDalException {
        ArrayList<StudentDay> studentdays = new ArrayList<>();
        
        try ( Connection con = dbcon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT "
                    + "Student.username, Student.name, Student.password,Student.absenceProcent,Student.dayMostAbsent,Student.dayMostAbsent,Student.classID, Student_day.dayId,Student_day.status,Day.weekDay, Day.date  "
                    + "FROM Student "
                    + "INNER JOIN Student_day ON Student.username = Student_day.studentUsername "
                    + "inner JOIN Day ON Student_day.dayId = Day.id "
                    + "WHERE Student.username = ? "
                    + "ORDER BY  Day.date ASC");
            
            ps.setString(1, student.getUsername());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int absence = rs.getInt("absenceProcent");
                String dayMostAbsent = rs.getString("dayMostAbsent");
                int classID = rs.getInt("classID");
               Student returnstudent = new Student(name, username, password, absence, dayMostAbsent, classID);
               
               LocalDate date = rs.getDate("date").toLocalDate();
               
               int absenstatus = rs.getInt("status");
               
               studentdays.add(new StudentDay(date, returnstudent, absenstatus));
               
            
               
            }
            return studentdays;

        } catch (SQLServerException ex) {
            throw new AttendanceAutomationDalException("could not get all categories with movie from database", ex);
        } catch (SQLException ex) {
            throw new AttendanceAutomationDalException("could not get all categories with movie from database", ex);
        }
    }
}
