package edu.bsu.cs222.finalProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
        if (isValidUsername( username ))
            {return username;}
        else
            {return "Invalid Username";}
    }

    @SuppressWarnings( "unused" ) // will be used later
    public String getPassword() {
        String password;
        password = passwordInput.getText();
        return password;
    }

    private boolean isValidUsername(String username) {
        String userNameRegex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(userNameRegex);
        if (username == null) return false;
        return pattern.matcher(username).matches();
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
