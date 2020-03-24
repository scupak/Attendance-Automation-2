/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Teacher;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lumby
 */
public class TeacherDBDAO
{
    
    private final DatabaseConnector dbCon;
    
    public TeacherDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
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
                students.add(new Teacher(username, name, password));

            }
            return students;
        } catch (SQLException ex)
        {
            Logger.getLogger(StudentDBDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            throw new AttendanceAutomationDalException("could not find the Teacher in the dataabase", ex);
        }
    }
}
