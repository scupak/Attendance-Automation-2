/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

/**
 *
 * @author lumby
 */
public class AttendanceAutomationDalException extends Exception
{

     public AttendanceAutomationDalException() {
        super();
    }

    public AttendanceAutomationDalException(String message) {
        super(message);
    }

    public AttendanceAutomationDalException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttendanceAutomationDalException(String message, Exception ex) {
        super(message, ex);
    }
}
