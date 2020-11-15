package edu.bsu.cs222.finalProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainUIController {
    @FXML
    private AnchorPane rootPane;

    public void launchLoginUI() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource( "/ui/loginUI.fxml" ));
        rootPane.getChildren().setAll( pane );
    }
}
