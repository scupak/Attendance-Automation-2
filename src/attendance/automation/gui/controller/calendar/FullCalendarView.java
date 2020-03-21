package attendance.automation.gui.controller.calendar;

import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.model.AppModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class FullCalendarView {

    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private VBox view;
    private Text calendarTitle;
    private YearMonth currentYearMonth;
    private AppModel appModel;

    /**
     * Create a calendar view
     * @param yearMonth year month to create the calendar of
     */
    public FullCalendarView(YearMonth yearMonth, AppModel apModel) throws AttendanceAutomationDalException {
        currentYearMonth = yearMonth;
        this.appModel = apModel;
        // Create the calendar grid pane
        GridPane calendar = new GridPane();
        calendar.setId("calendargrid");
   //previous sizes 379, 416
        calendar.setPrefSize(875, 307);
        calendar.setGridLinesVisible(true);
        // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode(this,appModel);
                   //previous sizes 56, 56
                ap.setPrefSize(130,130);
                calendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }
        // Days of the week labels
        Text[] dayNames = new Text[]{ new Text("S"), new Text("M"), new Text("T"),
                                        new Text("W"), new Text("T"), new Text("F"),
                                        new Text("S") };
        
        
        GridPane dayLabels = new GridPane();
       dayLabels.setId("daylables");
        //System.out.println(dayLabels.getStyleClass() +" Label");
       
        dayLabels.setPrefWidth(875);
        Integer col = 0;
        for (Text txt : dayNames) {
            AnchorPane ap = new AnchorPane();
            ap.setPrefSize(250, 5);
            
            
            System.err.println(col); 
          if(col == 0){
          
              ap.setBottomAnchor(txt, 5.0);
              ap.setTopAnchor(txt, 5.0);
              ap.setLeftAnchor(txt, 18.0);
          
          
          }
          else if(col == 1){
              
            ap.setBottomAnchor(txt, 5.0);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 14.0);
        
        
        }
         else if(col == 2){
              
            ap.setBottomAnchor(txt, 5.0);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 20.0);
         }
         else if(col == 3){
              
            ap.setBottomAnchor(txt, 5.0);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 16.0);
         }
          else if(col == 5){
              
            ap.setBottomAnchor(txt, 5.0);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 19.0);
         }
          else{
            ap.setBottomAnchor(txt, 5.0);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 19.0);
            
          
          
          }
            
            
           
            
            ap.getChildren().add(txt);
            dayLabels.add(ap, col++, 0);
        }
        // Create calendarTitle and buttons to change current month
        calendarTitle = new Text();
        //Button previousMonth = new Button();
        
        
        
       ImageView previousMonth  = new ImageView(new javafx.scene.image.Image("attendance/automation/gui/icons/leftarrow.png"));
       //previousMonth.setGraphic(image);
      
        previousMonth.setOnMouseClicked(e -> {
            try {
                previousMonth();
            } catch (AttendanceAutomationDalException ex) {
                Logger.getLogger(FullCalendarView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //Button nextMonth = new Button();
        
        ImageView nextMonth = new ImageView(new javafx.scene.image.Image("attendance/automation/gui/icons/rightarrow.png"));
        //nextMonth.setGraphic(image2);
        
        
        nextMonth.setOnMouseClicked(e -> {
            try {
                nextMonth();
            } catch (AttendanceAutomationDalException ex) {
                Logger.getLogger(FullCalendarView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        /*
        HBox titleBar = new HBox(previousMonth, calendarTitle ,nextMonth );
        titleBar.setAlignment(Pos.TOP_CENTER);
        titleBar.setSpacing(125.0);
        
        titleBar.fillHeightProperty().set(false);
        */
     
        BorderPane titleBar = new BorderPane(); 
        
        titleBar.setLeft(previousMonth);
        titleBar.setRight(nextMonth);
        titleBar.setCenter(calendarTitle);
        titleBar.setMargin(nextMonth,new Insets(12,12,12,12)); 
        titleBar.setMargin(previousMonth,new Insets(12,12,12,12)); 
        
       
        
        
        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);
        // Create the calendar view
        view = new VBox(titleBar, dayLabels, calendar);
        
       
        
        /*set id for the nodes of the calendar*/
        view.setId("calendarviewVBox");
        titleBar.setId("titleBar");
    }

    /**
     * Set the days of the calendar to correspond to the appropriate date
     * @param yearMonth year and month of month to render
     */
    public void populateCalendar(YearMonth yearMonth) throws AttendanceAutomationDalException {
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Populate the calendar with day numbers
        for (AnchorPaneNode ap : allCalendarDays) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            
            if(calendarDate.getDayOfMonth() < 10){
            
                   txt = new Text("0" + String.valueOf(calendarDate.getDayOfMonth()));
            
            
            }
            
            
            ap.setDate(calendarDate);
            ap.setTopAnchor(txt, 16.0);
            ap.setLeftAnchor(txt, 16.0);
            ap.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the calendar
        calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
    }

    /**
     * Move the month back by one. Repopulate the calendar with the correct dates.
     */
    private void previousMonth() throws AttendanceAutomationDalException {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Repopulate the calendar with the correct dates.
     */
    private void nextMonth() throws AttendanceAutomationDalException {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Gets the current view
     * @return view
     */
    public VBox getView() {
        return view;
    }

    /**
     * Gets all calendar days
     * @return all calendar days
     */
    public ArrayList<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }
    
    /**
     * Sets all calendar days
     * @param allCalendarDays 
     */
    public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }

}
            