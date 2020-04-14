/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL;

import attendance.automation.BLL.Interface.StudentManagerInterface;
import attendance.automation.be.Student;
import attendance.automation.be.StudentDay;
import attendance.automation.be.WeekDayCounter;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.dal.Interface.DALFacadeInterface;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javafx.scene.chart.XYChart;

/**
 *
 * @author SKRUMM
 */
public class StudentManager implements StudentManagerInterface
{

    private final DALFacadeInterface dalfacade;

    public StudentManager(DALFacadeInterface dalfacade) throws IOException
    {
        this.dalfacade = dalfacade;
    }

    /**
     * Checks the current day according to the given username
     *
     * @param username
     * @return the current day, and relevant info
     * @throws AttendanceAutomationDalException
     */
    @Override
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException
    {
        return dalfacade.checkCurrentDay(username);
    }

    /**
     * Gets a list of all students
     *
     * @return a list of all students
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<Student> getallStudents() throws AttendanceAutomationDalException
    {
        return dalfacade.getAllStudents();
    }

    /**
     * Checks the students credentials and compares them to the given info, to
     * see if it matches
     *
     * @param s
     * @return if the info matches the info in the DB
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException
    {
        Student rs;

        rs = dalfacade.getStudent(s);
        if (rs == null)
        {
            return false;
        }
        if (rs.getUsername().equals(s.getUsername()))
        {
            if (rs.getPassword().equals(s.getPassword()))
            {
                System.out.println("true");
                return true;
            }
        }

        System.out.println("false");
        return false;
    }

    /**
     * Gets a student
     *
     * @param s
     * @return a student
     * @throws AttendanceAutomationDalException
     */
    @Override
    public Student getStudent(Student s) throws AttendanceAutomationDalException
    {
        return dalfacade.getStudent(s);
    }

    /**
     * Sends an update to DayStudent between layers
     *
     * @param sd
     * @return the update
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean sendUpdateDayStudent(StudentDay sd) throws AttendanceAutomationDalException
    {
        return dalfacade.sendUpdateDayStudent(sd);
    }

    /**
     * Sets the day status for the given user
     *
     * @param status
     * @param username
     * @throws AttendanceAutomationDalException
     */
    @Override
    public void setDayStatus(int status, String username) throws AttendanceAutomationDalException
    {
        dalfacade.setDayStatus(status, username);
    }

    /**
     * Gets a list of all days for the current student
     *
     * @param student
     * @return a list of days for the current student
     * @throws AttendanceAutomationDalException
     */
    @Override
    public List<StudentDay> getAllDaysForAstudent(Student student) throws AttendanceAutomationDalException
    {
        return dalfacade.getAllDaysForStudent(student);
    }

    @Override
    public List<StudentDay> getAllDaysForAstudent(Student currentStudent, LocalDate date, LocalDate date0) throws AttendanceAutomationDalException
    {
        return dalfacade.getAllDaysForStudent(currentStudent, date, date0);
    }

    /**
     * Checks wether or not the given student exists in the DB
     *
     * @param username
     * @param date
     * @return wether or not the given student exists
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean doesStudentDayExist(String username, LocalDate date) throws AttendanceAutomationDalException
    {
        return dalfacade.doesStudentDayExist(username, date);
    }

    /**
     * Gets the students day
     *
     * @param s
     * @param date
     * @return the students day
     * @throws AttendanceAutomationDalException
     */
    @Override
    public StudentDay getStudentDay(Student s, LocalDate date) throws AttendanceAutomationDalException
    {
        return dalfacade.getStudentDay(s, date);
    }

    /**
     * sets the pie chart
     *
     * @param s
     * @param attendanceStatusCheck
     * @return the pie chart
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    @Override
    public double pieChartData(Student s, int attendanceStatusCheck) throws AttendanceAutomationDalException
    {
        ArrayList<StudentDay> days = new ArrayList<StudentDay>();
        ArrayList<StudentDay> absent = new ArrayList<StudentDay>();

        days.addAll(getAllDaysForAstudent(s));

        for (StudentDay day : days)
        {
            if (day.getAttendanceStatus() == attendanceStatusCheck)
            {
                absent.add(day);
            }
        }

        double procent;

        if (!absent.isEmpty())
        {
            double p = absent.size();
            procent = (p / days.size()) * 100;
        } else
        {
            procent = absent.size();
        }

        return procent;
    }

    /**
     * Sets a barChart depentet on attendanceStatusCheck and columName
     *
     * @param s
     * @param attendanceStatusCheck
     * @param columName
     * @return the bar chart
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    @Override
    public XYChart.Series setPresence(Student s, int attendanceStatusCheck, String columName) throws AttendanceAutomationDalException
    {

        XYChart.Series presence = new XYChart.Series<>();
        ArrayList<StudentDay> p = new ArrayList<>();
        int mondayC = 0;
        int tuesdayC = 0;
        int wednesdayC = 0;
        int thursdayC = 0;
        int fridayC = 0;

        for (StudentDay studentDay : getAllDaysForAstudent(s))
        {
            if (studentDay.getAttendanceStatus() == attendanceStatusCheck)
            {
                p.add(studentDay);
            }

        }

        for (StudentDay day : p)
        {
            if ("monday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                mondayC++;
            } else if ("tuesday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                tuesdayC++;
            } else if ("wednesday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                wednesdayC++;
            } else if ("thursday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                thursdayC++;
            } else if ("friday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                fridayC++;
            }

        }

        presence.setName(columName);
        presence.getData().add(new XYChart.Data("Monday", mondayC));
        presence.getData().add(new XYChart.Data("Tuesday", tuesdayC));
        presence.getData().add(new XYChart.Data("Wednesday", wednesdayC));
        presence.getData().add(new XYChart.Data("Thursday", thursdayC));
        presence.getData().add(new XYChart.Data("Friday", fridayC));

        return presence;
    }

    /**
     * updates a students absence procent
     *
     * @param currentStudent
     * @param absenceProcentforstudent
     * @throws AttendanceAutomationDalException
     */
    @Override
    public void updateStudentabsenceProcent(Student currentStudent, double absenceProcentforstudent) throws AttendanceAutomationDalException
    {

        dalfacade.updateStudentabsenceProcent(currentStudent, absenceProcentforstudent);
    }

    /**
     * gets the most absesnt day for a student
     *
     * @param currentStudent
     * @return mostabsentdayname
     * @throws AttendanceAutomationDalException
     */
    @Override
    public String getmostabsentdayforstudent(Student currentStudent) throws AttendanceAutomationDalException
    {

        ArrayList<StudentDay> p = new ArrayList<>();
        ArrayList<WeekDayCounter> WeekDayCounters = new ArrayList<>();
        int monday = 0;
        WeekDayCounter mondayc = new WeekDayCounter("monday", 0);
        int tuesday = 0;
        WeekDayCounter tuesdayc = new WeekDayCounter("tuesday", 0);
        int wednesday = 0;
        WeekDayCounter wednesdayc = new WeekDayCounter("wednesday", 0);
        int thursday = 0;
        WeekDayCounter thursdayc = new WeekDayCounter("thursday", 0);
        int friday = 0;
        WeekDayCounter fridayc = new WeekDayCounter("friday", 0);

        for (StudentDay studentDay : getAllDaysForAstudent(currentStudent))
        {
            if (studentDay.getAttendanceStatus() == StudentDay.notAttendant)
            {
                p.add(studentDay);
            }

        }

        if (p.isEmpty())
        {
            return "none";

        }

        for (StudentDay day : p)
        {
            if ("monday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                monday++;
                mondayc.setCounter(mondayc.getCounter() + 1);

            } else if ("tuesday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                tuesday++;
                tuesdayc.setCounter(tuesdayc.getCounter() + 1);
            } else if ("wednesday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                wednesday++;
                wednesdayc.setCounter(wednesdayc.getCounter() + 1);

            } else if ("thursday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                thursday++;
                thursdayc.setCounter(thursdayc.getCounter() + 1);

            } else if ("friday".equals(day.getDate().getDayOfWeek().toString().toLowerCase()))
            {
                friday++;
                fridayc.setCounter(fridayc.getCounter() + 1);

            }

        }
        Comparator<WeekDayCounter> bycounter = (WeekDayCounter day1, WeekDayCounter day2) -> day2.getCounter().compareTo(day1.getCounter());

        WeekDayCounters.add(mondayc);
        WeekDayCounters.add(tuesdayc);
        WeekDayCounters.add(wednesdayc);
        WeekDayCounters.add(thursdayc);
        WeekDayCounters.add(fridayc);

        WeekDayCounters.sort(bycounter);

        WeekDayCounter mostabsentday = WeekDayCounters.get(0);
        String mostabsentdayname = WeekDayCounters.get(0).getWeekday();
        WeekDayCounters.remove(0);

        int dayTextCounter = 0;

        for (WeekDayCounter WeekDayCounter1 : WeekDayCounters)
        {

            if (Objects.equals(mostabsentday.getCounter(), WeekDayCounter1.getCounter()) && dayTextCounter == 0)
            {

                dayTextCounter++;
                mostabsentdayname = mostabsentdayname + " " + WeekDayCounter1.getWeekday();

            } else if (Objects.equals(mostabsentday.getCounter(), WeekDayCounter1.getCounter()) && dayTextCounter == 1)
            {

                dayTextCounter++;
                mostabsentdayname = mostabsentdayname + "...";

            }

        }

        return mostabsentdayname;
    }

    /**
     * updates a students most absent day
     *
     * @param currentStudent
     * @param mostabsentdayforstudent
     * @return boolean
     * @throws AttendanceAutomationDalException
     */
    @Override
    public boolean updateStudentMostAbsentDay(Student currentStudent, String mostabsentdayforstudent) throws AttendanceAutomationDalException
    {

        return dalfacade.updateStudentMostAbsentDay(currentStudent, mostabsentdayforstudent);
    }

}
