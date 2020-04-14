/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

/**
 *
 * @author SKRUMM
 */
public class AttendanceAutomationDalException extends Exception
{

    public AttendanceAutomationDalException()
    {
        super();
    }

    /**
     * Outputs a message if this exception occurs
     *
     * @param message
     */
    public AttendanceAutomationDalException(String message)
    {
        super(message);
    }

    /**
     * Outputs a message if this exception occurs, throws a cause
     *
     * @param message
     * @param cause
     */
    public AttendanceAutomationDalException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Outputs a message if this exception occurs, throws an exception
     *
     * @param message
     * @param ex
     */
    public AttendanceAutomationDalException(String message, Exception ex)
    {
        super(message, ex);
    }
}
