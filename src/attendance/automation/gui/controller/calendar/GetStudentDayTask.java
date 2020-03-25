/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller.calendar;

import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.model.Interface.ModelFacadeInterface;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anton
 */
public class GetStudentDayTask implements Runnable {
    
    private AnchorPaneNode ap;
    private LocalDate date;
    private ModelFacadeInterface modelfacade;

    public GetStudentDayTask(AnchorPaneNode ap, LocalDate date, ModelFacadeInterface modelfacade) {
        this.ap = ap;
        this.date = date;
        this.modelfacade = modelfacade;
    }
    
    

    @Override
    public void run() {
        
        try { 
            ap.setStudentday(modelfacade.getStudentDay(modelfacade.getCurrentStudent(), date));
            
            System.out.println(Thread.currentThread().getName());
        } catch (AttendanceAutomationDalException ex) {
            Logger.getLogger(GetStudentDayTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
