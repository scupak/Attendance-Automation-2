/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import java.time.LocalDate;

/**
 *
 * @author SKRUMM
 */
public class StudentDay
{
    public final static int attendant = 1;
    public final static int notAttendant = 0;
    public final static int notSetAtt = -1;
    private LocalDate Date;
    private Student student;
    private int attendanceStatus;
    public StudentDay(LocalDate Date, Student student, int attendanceStatus) {
        this.Date = Date;
        this.student = student;
        this.attendanceStatus = attendanceStatus;
    }

    /**
     * Defines a students day
     * @param Date
     * @param student 
     */
    public StudentDay(LocalDate Date, Student student) {
        this.Date = Date;
        this.student = student;
        this.attendanceStatus = -1;
    }

    /**
     * Polymorphed student day
     */
    public StudentDay() {
        this.Date = LocalDate.now();
        this.student = null;
        this.attendanceStatus = -1;
    }
    
    /**
     * Gets attendant final int
     * @return attendant final int
     */
    public int getAttendant()
    {
        return attendant;
    }
    
    /**
     * Gets NotAttendant final int
     * @return NotAttendant final int
     */
    public int getNotAttendant()
    {
        return notAttendant;
    }
    
    /**
     * Gets the NotSetAtt final int
     * @return NotSetAtt final int
     */
    public int getNotSetAtt()
    {
        return notSetAtt;
    }

    /**
     * Gets the local date
     * @return the local date
     */
    public LocalDate getDate()
    {
        return Date;
    }

    /**
     * Sets the local date
     * @param Date 
     */
    public void setDate(LocalDate Date)
    {
        this.Date = Date;
    }

    /**
     * Gets attendance status
     * @return the attendance status
     */
    public int getAttendanceStatus() {
        return attendanceStatus;
    }

    /**
     * Sets the attendance status with the final ints
     * @param attendanceStatus 
     */
    public void setAttendanceStatus(int attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    /**
     * Gets the selected student
     * @return the selected student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the student to the chosen one
     * @param student 
     */
    public void setStudent(Student student) {
        this.student = student;
    }
   
    /**
     * gets all relevant info for the student and turns this into a string
     * @return all the relevant info for the student
     */
    @Override
    public String toString() {
        return "StudentDay{" + "Date=" + Date + ", student=" + student + ", attendanceStatus=" + attendanceStatus + '}';
    }
}
