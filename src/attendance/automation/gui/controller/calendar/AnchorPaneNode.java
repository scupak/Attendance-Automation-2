package attendance.automation.gui.controller.calendar;

import attendance.automation.be.StudentDay;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.enums.UserMode;
import attendance.automation.gui.controller.StatusSelectController;
import attendance.automation.gui.model.Interface.ModelFacadeInterface;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane
{

    // Date associated with this pane
    private LocalDate date;
    private FullCalendarView view;
    private ModelFacadeInterface modelfacade;
    private StudentDay studentday;
    

    /**
     * Create a anchor pane node.Date is not assigned in the constructor.
     *
     * @param View
     * @param modelfacade
     * @param children children of the anchor pane
     * @throws attendance.automation.dal.AttendanceAutomationDalException
     */
    public AnchorPaneNode(FullCalendarView View, ModelFacadeInterface modelfacade, Node... children) throws AttendanceAutomationDalException
    {
        super(children);
        this.view = View;
        this.modelfacade = modelfacade;

        // Add action handler for mouse clicked
        this.setOnMouseClicked(e ->
        {

            if (this.modelfacade.getIsStatusSelectOpen() == false && studentday != null)
            {

                if (studentday.getDate().equals(LocalDate.now()) || modelfacade.getCurrentUserMode() == UserMode.TEACHER)
                {

                    this.modelfacade.setIsStatusSelectOpen(true);

                    try
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StatusSelect.fxml"));

                        Stage primaryStage = new Stage();

                        primaryStage.setTitle("StatusSelect");
                        Scene scene = new Scene(loader.load());
                        scene.getStylesheets().add(getClass().getResource("/attendance/automation/gui/css/Graphics.css").toExternalForm());
                        primaryStage.setScene((scene));
                        // Get the controller and add the calendar view to it

                        StatusSelectController controller = loader.getController();
                        controller.setDate(date);
                        controller.setAnchorpanenode(this);

                        primaryStage.setHeight(415);
                        primaryStage.setWidth(393);
                        primaryStage.setResizable(false);

                        primaryStage.setOnCloseRequest((WindowEvent event) ->
                        {
                            System.out.println("window closed");

                            this.modelfacade.setIsStatusSelectOpen(false);
                        });

                        primaryStage.show();

                    } catch (IOException ex)
                    {

                    }
                }
            }

            if (studentday != null && !studentday.getDate().equals(LocalDate.now()) && modelfacade.getCurrentUserMode() == UserMode.STUDENT)
            {

                System.out.println(studentday);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Oops");
                alert.setHeaderText("Oops, something went wrong");
                alert.setContentText("As a student you can only edit the current day!");
                alert.showAndWait();

            } else if (studentday != null)
            {

                System.out.println(studentday);

            } else if (studentday == null)
            {

                System.out.println("studentday is null");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Oops");
                alert.setHeaderText("Oops, something went wrong");
                alert.setContentText("No classes set for this day, you can only edit days with classes");
                alert.showAndWait();

            }

        });
    }

    /**
     * Updates the anchor pane node
     *
     * @throws AttendanceAutomationDalException
     */
    public void updateAnchorPaneNodeStudentDay() throws AttendanceAutomationDalException
    {

        //Makes a new thread to run updates
        setBackground(Background.EMPTY);
        if (studentday != null)
        {

            setBackground(Background.EMPTY);

            if (studentday.getAttendanceStatus() == StudentDay.attendant)
            {
                BackgroundFill myBF = new BackgroundFill(Color.rgb(120, 245, 66), new CornerRadii(0),
                        new Insets(0.0, 0.0, 0.0, 0.0));// or null for the padding
                //then you set to your node or container or layout
                setBackground(new Background(myBF));

            } else if (studentday.getAttendanceStatus() == StudentDay.notAttendant)
            {
                BackgroundFill myBF = new BackgroundFill(Color.rgb(245, 66, 66), new CornerRadii(0),
                        new Insets(0.0, 0.0, 0.0, 0.0));// or null for the padding
                //then you set to your node or container or layout
                setBackground(new Background(myBF));

            } else if (studentday.getAttendanceStatus() == StudentDay.notSetAtt)
            {
                BackgroundFill myBF = new BackgroundFill(Color.rgb(66, 170, 245), new CornerRadii(0),
                        new Insets(0.0, 0.0, 0.0, 0.0));// or null for the padding
                //then you set to your node or container or layout
                setBackground(new Background(myBF));

            }

        }

    }

    /**
     * Gets the local date
     *
     * @return the local date
     */
    public LocalDate getDate()
    {
        return date;
    }

    /**
     * Sets the date to the current new value
     *
     * @param date
     * @throws AttendanceAutomationDalException
     */
    public void setDate(LocalDate date) throws AttendanceAutomationDalException
    {
        this.date = date;

    }

    /**
     * Gets a student day
     *
     * @return student day
     */
    public StudentDay getStudentday()
    {
        return studentday;
    }

    /**
     * Sets the student day to the current info
     *
     * @param studentday
     */
    public void setStudentday(StudentDay studentday)
    {
        this.studentday = studentday;
    }

}
