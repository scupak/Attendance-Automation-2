/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import java.util.Calendar;

/**
 *
 * @author Charlotte
 */
public class CalendarModel
{

    Calendar calendar = Calendar.getInstance();

    /**
     * Get the current month
     *
     * @return Month as an int
     */
    public int getCurrentMonth()
    {

        return calendar.get(Calendar.MONTH);

    }

    /**
     * Get the current Week
     *
     * @return Week as an int
     */
    public int getCurrentWeek()
    {

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Get the year
     *
     * @return year as an int
     */
    public int getYear()
    {
        return calendar.get(Calendar.YEAR);

    }
}
