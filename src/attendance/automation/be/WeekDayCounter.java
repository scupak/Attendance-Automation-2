/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

/**
 *
 * @author anton
 */
public class WeekDayCounter implements Comparable<WeekDayCounter> {
    
    private final String weekDay;
    private Integer counter; 

    public WeekDayCounter(String weekday, int counter) {
        this.weekDay = weekday;
        this.counter = counter;
    }

    /**
     * gets the week day
     * @return weekDay
     */
    public String getWeekday() {
        return weekDay;
    }

    /**
     * gets the counter
     * @return counter
     */
    public Integer getCounter() {
        return counter;
    }

    /**
     * sets the counter 
     * @param counter 
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * compares to weekDayCounters
     * @param other
     * @return comparison between two weekDayCounter
     */
    @Override
    public int compareTo(WeekDayCounter other) {
        return this.counter.compareTo(other.counter);
    }

    @Override
    public String toString() {
        return "WeekDayCounter{" + "weekday=" + weekDay + ", counter=" + counter + '}';
    }
    
        
    
        
}
