package attendance.automation.gui.controller.calendar;

import attendance.automation.gui.model.AppModel;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;
    private FullCalendarView view;
    private AppModel appModel;

    /**
     * Create a anchor pane node.Date is not assigned in the constructor.
     * @param View
     * @param appModel
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(FullCalendarView View,AppModel appModel,Node... children) {
        super(children);
        this.view = View;
        this.appModel = appModel;
        
        
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> {
            
            for (AnchorPaneNode node : this.view.getAllCalendarDays()) {
                
                
                node.setBackground(Background.EMPTY);
                
            }
               /* 
                System.out.println("This pane's date is: " + date);
                setStyle(" -fx-background-image: url(\"elipse.png\");"
                        + "  -fx-background-size: 50 50;"
                        + "-fx-background-position: center center;"
                        + " -fx-background-color: #FBBB2C;");*/
                
               BackgroundFill myBF = new BackgroundFill(Color.rgb(251, 187, 44), new CornerRadii(0),
         new Insets(0.0,0.0,0.0,0.0));// or null for the padding
        //then you set to your node or container or layout
        
            setBackground(new Background(myBF));
            
            if(date.isEqual(LocalDate.of(2020, Month.FEBRUARY, 21) )){
            
                // appModel.setClassMockData(1);
            
            
            }
            else if(date.isEqual(LocalDate.of(2020, Month.FEBRUARY, 22))){
            
                // appModel.setClassMockData(2);
            
            
            }
            else{
            
                 //appModel.setClassMockData(0);
            
            
            } 
                
        
        
        
        
        } );
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
