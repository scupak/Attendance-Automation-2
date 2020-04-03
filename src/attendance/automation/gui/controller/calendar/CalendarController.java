package attendance.automation.gui.controller.calendar;

import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.enums.UserMode;
import attendance.automation.gui.model.Interface.ModelFacadeInterface;
import attendance.automation.gui.model.ModelFacade;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

public class CalendarController implements Initializable{

    // Get the pane to put the calendar on
    @FXML public Pane calendarPane;
    @FXML
    private Pane maincalendarpane;
    private ModelFacadeInterface modelfacade;
    @FXML
    private Label userModeLabel;
    
    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try {
            /**
             *  We use get instance instead of new to make sure we use the same appmodel in all classes.
             */
            modelfacade = ModelFacade.getInstance();
            System.out.println("Current user mode is" + "  " +modelfacade.getCurrentUserMode());
            if (modelfacade.getCurrentUserMode() == UserMode.TEACHER)
            {
               userModeLabel.setText(modelfacade.getCurrentTeacher().getName() + " is currently in " +"Admin Mode " + "Accesing " + modelfacade.getCurrentStudent().getName() + "s profile");
            }
        } catch (IOException ex) {
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Student calendar view error!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Given wrong type!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * handles navigating back to the main view
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handelBackToMainView(ActionEvent event) {
        
        try
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendance/automation/gui/view/StudentMainView.fxml"));
             //update the StudentabsenceProcent in the database
            modelfacade.updateStudentabsenceProcent(modelfacade.getCurrentStudent(), modelfacade.getabsenceProcentforstudent(modelfacade.getCurrentStudent()) );
            //update the MostAbsentDay for this student
             modelfacade.updateStudentMostAbsentDay(modelfacade.getCurrentStudent(),modelfacade.getmostabsentdayforstudent(modelfacade.getCurrentStudent()));
            
            maincalendarpane.getChildren().setAll(pane);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Cannot handle going back to main view!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AttendanceAutomationDalException ex) {
            Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
