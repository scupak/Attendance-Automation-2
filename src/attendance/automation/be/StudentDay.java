/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import java.time.LocalDate;

/**
 *
 * @author Zanaxdk <https://github.com/zanaxdk>
 */
public class StudentDay
{
    public final static int attendant = 1;
    public final static int notAttendant = 0;
    public final static int notSetAtt = -1;
    private int attendanceStatus = -1;
    private LocalDate Date;
    private Student student;

    public StudentDay(LocalDate Date, Student student, int attendanceStatus) {
        this.Date = Date;
        this.student = student;
        this.attendanceStatus = attendanceStatus;
    }

    public StudentDay(LocalDate Date, Student student) {
        this.Date = Date;
        this.student = student;
    }
    

    public int getAttendant()
    {
        return attendant;
    }
    public int getNotAttendant()
    {
        return notAttendant;
    }
    public int getNotSetAtt()
    {
        return notSetAtt;
    }

    public LocalDate getDate()
    {
        return Date;
    }

    public void setDate(LocalDate Date)
    {
        this.Date = Date;
    }

    public int getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(int attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
    
}
