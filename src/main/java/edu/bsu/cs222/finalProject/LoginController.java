package edu.bsu.cs222.finalProject;

//import javafx.event.ActionEvent; (will be used when implementing GUI)
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginController
{
    @FXML
    private AnchorPane rootPane;

    public void launchMainUI( /*ActionEvent actionEvent (to be used once GUI is implemented)*/ ) throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource( "/MainUI.fxml" ));
            rootPane.getChildren().setAll( pane );
    }
}
