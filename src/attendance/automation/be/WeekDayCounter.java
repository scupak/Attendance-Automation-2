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
    
    private final String weekday;
    private Integer counter; 

    public WeekDayCounter(String weekday, int counter) {
        this.weekday = weekday;
        this.counter = counter;
    }

    public String getWeekday() {
        return weekday;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int compareTo(WeekDayCounter other) {
        return this.counter.compareTo(other.counter);
    }

    @Override
    public String toString() {
        return "WeekDayCounter{" + "weekday=" + weekday + ", counter=" + counter + '}';
    }
    
        
    
        
}
