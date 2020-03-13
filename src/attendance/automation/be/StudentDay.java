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
    private int attStat;
    private String timeAndDate;

    public int getAttStat()
    {
        return attStat;
    }

    public void setAttStat(int attStat)
    {
        this.attStat = attStat;
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
