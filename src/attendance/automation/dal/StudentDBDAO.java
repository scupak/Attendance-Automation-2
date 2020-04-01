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
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.JOptionPane;


/**
 *
 * @author SKRUMM
 */
public class StudentDBDAO implements StudentDBDAOInterface
{
    final static int ABSENT = 0;
    final static int PRESENT = 1;
    final static int UNKNOWN = -1;
    final static int DAY_OFF = 2;
   

    private final ConnectionPool conPool;  

    public StudentDBDAO() throws IOException, Exception
    {
        this.conPool = ConnectionPool.getInstance();
       
    }

    /**
     * gets a list of all students in database
     * @return students list
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public List<Student> getAllStudents() throws AttendanceAutomationDalException
    {
        ArrayList<Student> students = new ArrayList<>();
        Connection con = conPool.checkOut();

        try 
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
        finally
        {
            conPool.checkIn(con);
        }
        
    }

    /**
     * get a specific student based on username
     * @param s
     * @return returnStudent
     * @throws AttendanceAutomationDalException 
     */
    public Student getStudent(Student s) throws AttendanceAutomationDalException
    {
        Connection con = conPool.checkOut();
        if (!StudentExist(s))
        {
            return null;
        }

        Student returnstudent;
        try
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
            JOptionPane.showMessageDialog(null, "Could not find the student in the database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("could not find the student in the dataabase", ex);
        }
        finally
        {
            conPool.checkIn(con);
        }
    }

    /**
     * checks if a student exist in the databases
     * @param s
     * @return boolean
     * @throws AttendanceAutomationDalException 
     */
    public boolean StudentExist(Student s) throws AttendanceAutomationDalException
    {
        Connection con = conPool.checkOut();
        try 
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
            JOptionPane.showMessageDialog(null, "Could not find the student in the database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("could not find the student in the dataabase", ex);
        }
        finally
        {
            conPool.checkIn(con);
        }
    }

    
    /**
     * TODO make a method that chekcs if the studentDay exists
     * @param username
     * @return the current day
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
         Connection con = conPool.checkOut();
        try
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
            JOptionPane.showMessageDialog(null, "Could not access day, or it is a day off!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            conPool.checkIn(con);
        }
        return DAY_OFF;
    }

    /**
     * Sends an update to DayStudent between layers
     * @param sd
     * @return boolean
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    @Override
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException
    {
        Connection con = conPool.checkOut();
        if(!doesStudentDayExist(sd.getStudent().getUsername(), sd.getDate()))
        {
            
            return false;
        }
        
        try
        {
            
            java.sql.Date sqlDate = java.sql.Date.valueOf(sd.getDate());

            String sql = "SELECT id FROM [Day] WHERE date = ?";
            String sql2 = "UPDATE [Student_day] SET status = ?, last_changed = GETDATE()  WHERE studentUsername = ? AND dayId = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = con.prepareStatement(sql2);

            ps.setDate(1, sqlDate);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int dayId = rs.getInt("id");

                ps2.setInt(1, sd.getAttendanceStatus());
                ps2.setString(2, sd.getStudent().getUsername());
                ps2.setInt(3, dayId);

                ps2.executeUpdate();
               return true;
            }
            
            return false;
            
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Could not update the student's day!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("sendUpdateDayStudent error", ex);
        }
        finally
        {
            conPool.checkIn(con);
        }
    }
    
    /**
     * make it so this method only updates the day and does not make a new day
     * @param status
     * @param username 
     */
    @Override
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException
    {
        Connection con = conPool.checkOut();
        try
        {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String sql = "SELECT id FROM [Day] WHERE date = ?";
            String sql2 = "UPDATE [Student_day] SET status = ?, last_changed = GETDATE() WHERE studentUsername = ? AND dayId = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = con.prepareStatement(sql2);

            ps.setDate(1, sqlDate);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int dayId = rs.getInt("id");

                ps2.setInt(1, status);
                ps2.setString(2, username);
                ps2.setInt(3, dayId);

                ps2.executeUpdate();
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Could not set status of the day!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.out.println("SQL Error: setDayStatus  " + ex);
        }
        finally
        {
            conPool.checkIn(con);
        }

    }

    /**
     * get a list of days for a student
     * @param student
     * @return studentDays
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public List<StudentDay> getAllDaysForStudent(Student student) throws AttendanceAutomationDalException {
        ArrayList<StudentDay> studentdays = new ArrayList<>();
        Connection con = conPool.checkOut();
        try  {
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
            JOptionPane.showMessageDialog(null, "Could not get all students the database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("could not get all students from database", ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Could not get all students the database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("could not get all students from database", ex);
        }
         finally
        {
            conPool.checkIn(con);
        }
    }
    
    @Override
    public List<StudentDay> getAllDaysForStudent(Student currentStudent, LocalDate startdate, LocalDate enddate) throws AttendanceAutomationDalException {
         ArrayList<StudentDay> studentdays = new ArrayList<>();
        
        try ( Connection con = dbcon.getConnection()) {
            
            
             java.sql.Date sqlstartDate = java.sql.Date.valueOf(startdate);
             java.sql.Date sqlendDate = java.sql.Date.valueOf(enddate);
             
            PreparedStatement ps = con.prepareStatement("SELECT "
                    + "Student.username, Student.name, Student.password,Student.absenceProcent,Student.dayMostAbsent,Student.dayMostAbsent,Student.classID, Student_day.dayId,Student_day.status,Day.weekDay, Day.date  "
                    + "FROM Student "
                    + "INNER JOIN Student_day ON Student.username = Student_day.studentUsername "
                    + "inner JOIN Day ON Student_day.dayId = Day.id "
                    + "WHERE Student.username = ? AND Day.date > ? AND Day.date < ? "
                    + "ORDER BY  Day.date ASC");
            
            ps.setString(1, currentStudent.getUsername());
            ps.setDate(2, sqlstartDate);
            ps.setDate(3, sqlendDate);
            
            
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
            JOptionPane.showMessageDialog(null, "Could not get all students the database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("could not get all students from database", ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Could not get all students the database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("could not get all students from database", ex);
        }
    }

    /**
     * Checks if the students day exists
     * @param username
     * @param date
     * @return if the day exists
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException
    {
        Connection con = conPool.checkOut();
        try 
        {
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);

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
                return true;
            }
            System.out.println("doesStudentDayExist = false");
            return false;
            
            
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Could not find day in database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("Could not find day in database", ex);
        }
        finally
        {
            conPool.checkIn(con);
        }
      
       
    }

    /**
     * Gets the student day
     * @param s
     * @param date
     * @return the student day
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public StudentDay getStudentDay(Student s, LocalDate date) throws AttendanceAutomationDalException
    {
        Connection con = conPool.checkOut();
        if(!doesStudentDayExist(s.getUsername(), date))
        {
            return null;
        }
        StudentDay returnStudentDay;
          
        
         try
        {
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);

            String sql = "SELECT status, date FROM [Student_day] "
                    + "JOIN [Day] ON Student_day.dayId = Day.id "
                    + "WHERE Student_day.studentUsername = ? "
                    + "AND Day.date = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, s.getUsername());
            ps.setDate(2, sqlDate);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                LocalDate daydate = rs.getDate("date").toLocalDate();
                int status = rs.getInt("status");
                returnStudentDay = new StudentDay(daydate, s, status);
            }
            else
            {
                return null;  
            }
            return returnStudentDay;

           
            
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Could not find day in database!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("Could not find day in database", ex);
        }
         finally
        {
            conPool.checkIn(con);
        }
    }

       
       /*implement the method*/

    @Override
    public boolean updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException {
        
        System.err.println("updateStudentabsenceProcent");
        Connection con = conPool.checkOut();
        if(!StudentExist(currentStudent))
        {
            
            return false;
        }
        
        try 
        {
            

            String sql = "UPDATE [Student] SET absenceProcent = ? WHERE username = ? ";

           
            PreparedStatement ps = con.prepareStatement(sql);
            
           
            ps.setInt(1, (int) Math.round(absenceProcentforstudent));
            ps.setString(2, currentStudent.getUsername());
           int updatedRows = ps.executeUpdate();

             return updatedRows > 0;
            
            
            
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Could not update the student's day!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new AttendanceAutomationDalException("sendUpdateDayStudent error", ex);
        }
        finally
        {
            conPool.checkIn(con);
        }
    }
    
     protected boolean updateStudentpassword(Student currentStudent) throws AttendanceAutomationDalException {
        
        System.err.println("updateStudentabsenceProcent");
          Connection con = conPool.checkOut();
        if(!StudentExist(currentStudent))
        {
            
            return false;
        }
        
        try 
        {
            

            String sql = "UPDATE [Student] SET password = ? WHERE username = ? ";

           
            PreparedStatement ps = con.prepareStatement(sql);
            
           
            ps.setString(1, currentStudent.getPassword());
            ps.setString(2, currentStudent.getUsername());
           int updatedRows = ps.executeUpdate();

             return updatedRows > 0;
            
            
            
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Could not update the student's day!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new AttendanceAutomationDalException("sendUpdateDayStudent error", ex);
        }
        finally
        {
            conPool.checkIn(con);
        }
    }

    @Override
    public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException {
         Connection con = conPool.checkOut();
        if(!StudentExist(currentStudent))
        {
            
            return false;
        }
        
        try
        {
            

            String sql = "UPDATE [Student] SET dayMostAbsent = ? WHERE username = ? ";

           
            PreparedStatement ps = con.prepareStatement(sql);
            
           
            ps.setString(1,  mostabsentdayforstudent);
            ps.setString(2, currentStudent.getUsername());
           int updatedRows = ps.executeUpdate();

             return updatedRows > 0;
            
            
            
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Could not update the student's day!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            throw new AttendanceAutomationDalException("sendUpdateDayStudent error", ex);
        }
        finally
        {
            conPool.checkIn(con);
        }
    }
     
    public static void main(String[] args) throws IOException, AttendanceAutomationDalException, Exception {
        ArrayList<StudentDay> studentdays = new ArrayList<>();
        StudentDBDAO test = new StudentDBDAO();
          Student se = new Student("djkghsl", "mads69", "password", 0, "monday", 0);  
        //System.err.println(test.updateStudentMostAbsentDay(se, "monday"));
        
       // System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH)));
       
        LocalDate startdate = LocalDate.of(2020, Month.MARCH, 19);
        LocalDate enddate = LocalDate.of(2020, Month.MARCH, 25);
        
        studentdays.addAll(test.getAllDaysForStudent(se, startdate, enddate));
        
        
        for (Iterator<StudentDay> iterator = studentdays.iterator(); iterator.hasNext();) {
            StudentDay next = iterator.next();
            
            System.out.println(next.toString());
            
        }
        
        
       
       
        
        
    }

    
  
}

