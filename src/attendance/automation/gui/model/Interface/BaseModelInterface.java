/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model.Interface;

import attendance.automation.be.Student;
import attendance.automation.dal.AttendanceAutomationDalException;
import java.io.IOException;

/**
 *
 * @author kacpe
 */
public interface BaseModelInterface 
{
    /**
     * opens a new window
     *
     * @throws IOException
     */
    public void handelLogout() throws IOException;
    
    
    
    /**
     * Get the year
     *
     * @return year as an int
     */
    public int getYear();
            
     /**
     * Get the current month
     *
     * @return Month as an int
     */
    public int getCurrentMonth();
    
     /**
     * Get the current Week
     *
     * @return Week as an int
     */
    public int getCurrentWeek();
    
    /**
     * Checks the current day
     * @param username
     * @return the current day
     * @throws AttendanceAutomationDalException 
     */
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException;
    
    
    /**
     * Checks the students credentials, and if they match
     * @param s
     * @return wether or not the credentials match
     * @throws AttendanceAutomationDalException 
     */
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException;
    
    public boolean getIsStatusSelectOpen();
    
    public void setIsStatusSelectOpen(boolean isStatusSelectOpen);
    
    public int getThreadcounter();
    
    public void setThreadcounter(int threadcounter);
}
