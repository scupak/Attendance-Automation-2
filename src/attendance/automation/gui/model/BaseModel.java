/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.BLL.BLLFacade;
import attendance.automation.BLL.Interface.BLLFacadeInterface;
import attendance.automation.be.Student;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.enums.UserMode;
import attendance.automation.gui.controller.SignInViewController;
import attendance.automation.gui.model.Interface.BaseModelInterface;
import java.io.IOException;
import java.util.Calendar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kacpe
 */
public class BaseModel implements BaseModelInterface
{
    private final BLLFacadeInterface bllfacade;
    Calendar calendar = Calendar.getInstance();
    private boolean isStatusSelectOpen = false;
    private int threadcounter = 0;
    private UserMode usermode;

    
    public BaseModel() throws IOException, Exception
    {
        bllfacade = new BLLFacade();
    }
    
    /**
     * opens a new window
     *
     * @throws IOException
     */
    @Override
    public void handelLogout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = (Parent) fxmlLoader.load(getClass().getResource("/attendance/automation/gui/view/SignInView.fxml").openStream());
        SignInViewController cont = (SignInViewController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Sign in");
        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
        stage.show();
    }

    
    
     /**
     * Get the year
     *
     * @return year as an int
     */
    @Override
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }
    
     /**
     * Get the current month
     *
     * @return Month as an int
     */
    @Override
    public int getCurrentMonth() {
       return calendar.get(Calendar.MONTH);
    }
    
    
    /**
     * Get the current Week
     *
     * @return Week as an int
     */
    @Override
    public int getCurrentWeek() {
         return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    
    /**
     * Checks the current day
     * @param username
     * @return the current day
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public int checkCurrentDay(String username) throws AttendanceAutomationDalException {
       return bllfacade.checkCurrentDay(username);
    }
    
    
    /**
     * Checks the students credentials, and if they match
     * @param s
     * @return wether or not the credentials match
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean checkCredStudent(Student s) throws AttendanceAutomationDalException {
         return bllfacade.checkCredStudent(s);
    }

    @Override
    public boolean getIsStatusSelectOpen() {
        return isStatusSelectOpen;
    }

    @Override
    public void setIsStatusSelectOpen(boolean isStatusSelectOpen) {
        this.isStatusSelectOpen = isStatusSelectOpen;
    }

    @Override
    public int getThreadcounter() {
        return threadcounter;
    }

    @Override
    public void setThreadcounter(int threadcounter) {
        this.threadcounter = threadcounter;
    }

    /**
     * Sets the parameter that tells the program if its a teacher or student using the program. 
     * @param usermode 
     */
    @Override
    public void setCurrentUserMode(UserMode usermode) {
        this.usermode = usermode;
    }

    
    /**
     * Get the currentuserMode
     * @return 
     */
    @Override
    public UserMode getCurrentUserMode() {
        return usermode;
    }
    
    
    
}
