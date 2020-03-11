/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.gui.SignInViewController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lumby
 */
public class LogOutModel
{

    /**
     * opens a new window
     *
     * @throws IOException
     */
    public void handelLogout() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = (Parent) fxmlLoader.load(getClass().getResource("/attendance/automation/gui/SignInView.fxml").openStream());
        SignInViewController cont = (SignInViewController) fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Sign in");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
