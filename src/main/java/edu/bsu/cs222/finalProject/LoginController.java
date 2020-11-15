package edu.bsu.cs222.finalProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    @FXML
    private javafx.scene.control.TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private AnchorPane rootPane;

    public void launchMainUI() throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource( "/ui/mainUI.fxml" ));
            rootPane.getChildren().setAll( pane );
    }

    @SuppressWarnings( "unused" ) // will be used later
    public String getUsername() {
        String username;
        username = usernameInput.getText();
        return username;
    }

    @SuppressWarnings( "unused" ) // will be used later
    public String getPassword() {
        String password;
        password = passwordInput.getText();
        return password;
    }

    @SuppressWarnings( "unused" ) // will be used later
    public ArrayList<String> getUserInfo() {
        ArrayList<String> userInfo = new ArrayList <>();
        String username = this.getUsername();
        String password = this.getPassword();
        userInfo.add( username );
        userInfo.add( password );
        return userInfo;
    }
}
