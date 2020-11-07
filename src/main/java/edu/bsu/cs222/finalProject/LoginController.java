package edu.bsu.cs222.finalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.awt.*;
import java.io.IOException;

public class LoginController
{
    @FXML
    private TextField usernameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private AnchorPane rootPane;

    @SuppressWarnings( "unused" )
    public void launchMainUI( ActionEvent actionEvent  ) throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource( "/ui/MainUI.fxml" ));
            rootPane.getChildren().setAll( pane );
    }

    public String getUsername() {
        String username;
        username = usernameInput.getText();
        return username;
    }

    public String getPassword() {
        String password;
        password = passwordInput.getText();
        return password;
    }
}
