/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

/**
 *
 * @author Zanaxdk <https://github.com/zanaxdk>
 */
public class StudentDay
{
    public final int attendant = 1;
    public final int notAttendant = 0;
    public final int notSetAtt = -1;
    private String timeAndDate;

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

    public String getTimeAndDate()
    {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate)
    {
        this.timeAndDate = timeAndDate;
    }
    
}
