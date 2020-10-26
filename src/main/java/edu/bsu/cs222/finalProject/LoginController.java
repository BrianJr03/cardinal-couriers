package edu.bsu.cs222.finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController
{
    public void launchMainUI( ActionEvent actionEvent )
    {
        try
        {
            Stage secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource( "/MainUI.fxml" ).openStream());
            secondaryStage.setScene( new Scene( root, 725, 615 ) );
            secondaryStage.setTitle("Grocery Shop");
            secondaryStage.showAndWait();
        }

        catch ( IOException e )
        { e.printStackTrace(); }
    }
}
