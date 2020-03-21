package attendance.automation.gui.controller.calendar;

import attendance.automation.gui.controller.StudentCalenderViewController;
import attendance.automation.gui.model.AppModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CalendarController implements Initializable{

    // Get the pane to put the calendar on
    @FXML public Pane calendarPane;
    @FXML
    private Pane maincalendarpane;
    private AppModel appmodel;
    
    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try {
            /**
             *  We use get instance instead of new to make sure we use the same appmodel in all classes.
             */
            appmodel = AppModel.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(StudentCalenderViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Handles logging out of the system
     * @param event
     * @throws IOException 
     */
    @FXML
    private void HandleLogout(ActionEvent event) throws IOException {
        
        Window window = maincalendarpane.getScene().getWindow();

        if (window instanceof Stage)
        {
            ((Stage) window).close();
        }
        appmodel.handelLogout();
    }

    /**
     * handles navigating back to the main view
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handelBackToMainView(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/attendance/automation/gui/view/StudentMainView.fxml"));

         maincalendarpane.getChildren().setAll(pane);
        
        
    }

}
