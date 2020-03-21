package attendance.automation.gui.controller.calendar;

import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.model.AppModel;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane{

    // Date associated with this pane
    private LocalDate date;
    private FullCalendarView view;
    private AppModel appModel;
    private StudentDay studentday;
    private IntegerProperty status;

    /**
     * Create a anchor pane node.Date is not assigned in the constructor.
     * @param View
     * @param appModel
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(FullCalendarView View,AppModel appModel,Node... children) throws AttendanceAutomationDalException {
        super(children);
        this.view = View;
        this.appModel = appModel;
        
       // updateAnchorPaneNodeStudentDay();
        
        
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> {
            /*
            for (AnchorPaneNode node : this.view.getAllCalendarDays()) {
                
                
                node.setBackground(Background.EMPTY);
                
            }
                
               BackgroundFill myBF = new BackgroundFill(Color.rgb(251, 187, 44), new CornerRadii(0),
         new Insets(0.0,0.0,0.0,0.0));// or null for the padding
        //then you set to your node or container or layout
        
            setBackground(new Background(myBF));
            
            */
            
            if(studentday != null){
            
             System.out.println(studentday);
            
            
            }
            else{System.out.println("studentday is null");
            }
            
           
          
            
           
                
        
        
        
        
        } );
    }
    
    public void updateAnchorPaneNodeStudentDay() throws AttendanceAutomationDalException{
       /* if(appModel.doesStudentDayExist(appModel.getCurrentStudent().getUsername(), date)){
        studentday = this.appModel.getStudentDay(appModel.getCurrentStudent(), date);
        }*/
       
      // studentday = this.appModel.getStudentDay(appModel.getCurrentStudent(), date);
       for (AnchorPaneNode node : this.view.getAllCalendarDays()) {
                
                
                node.setBackground(Background.EMPTY);
                
            }
       
       
       new Thread(new Runnable() {
            @Override
            public void run() {
                
                try {
                    studentday = appModel.getStudentDay(appModel.getCurrentStudent(), date);
                    
                    if(studentday != null){
                        
                        
                   
                        if(studentday.getAttendanceStatus() == StudentDay.attendant){
                        BackgroundFill myBF = new BackgroundFill(Color.rgb(120, 245, 66), new CornerRadii(0),
                             new Insets(0.0,0.0,0.0,0.0));// or null for the padding
                       //then you set to your node or container or layout
                         setBackground(new Background(myBF));
                        
                        
                        
                        }
                        else if(studentday.getAttendanceStatus() == StudentDay.notAttendant){
                        BackgroundFill myBF = new BackgroundFill(Color.rgb(245, 66, 66), new CornerRadii(0),
                             new Insets(0.0,0.0,0.0,0.0));// or null for the padding
                       //then you set to your node or container or layout
                         setBackground(new Background(myBF));
                        
                        
                        
                        }
                        else if(studentday.getAttendanceStatus() == StudentDay.notSetAtt){
                        BackgroundFill myBF = new BackgroundFill(Color.rgb(66, 170, 245), new CornerRadii(0),
                             new Insets(0.0,0.0,0.0,0.0));// or null for the padding
                       //then you set to your node or container or layout
                         setBackground(new Background(myBF));
                        
                        
                        
                        }
                        
                        
                        
                        
                    }
                    
                    
                    
                    
                    
                    
            
            
            
            
            
                } catch (AttendanceAutomationDalException ex) {
                    Logger.getLogger(AnchorPaneNode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    
    
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws AttendanceAutomationDalException {
        this.date = date;
        updateAnchorPaneNodeStudentDay();
    }

    public StudentDay getStudentday() {
        return studentday;
    }

    public void setStudentday(StudentDay studentday) {
        this.studentday = studentday;
    }
    
}
