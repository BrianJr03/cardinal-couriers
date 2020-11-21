package edu.bsu.cs222.finalProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {
    @FXML
    private javafx.scene.control.TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label unMaskedPassword;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView passwordVisibility;

    AlertBox alertBox = new AlertBox();

    public void initialize() {
       passwordVisibility.setImage( isNotVisibleEye );
    }

    File isVisibleEye_File = new File( "src/main/resources/pngs/eye.png" );
    Image isVisibleEye = new Image( isVisibleEye_File.toURI().toString() );

    File isNotVisibleEye_File = new File( "src/main/resources/pngs/eye-slash.png" );
    Image isNotVisibleEye = new Image( isNotVisibleEye_File.toURI().toString() );

    public void launchMainUI() throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource( "/ui/mainUI.fxml" ));
            rootPane.getChildren().setAll( pane );
    }

    public void showUnMaskedPassword() {
        if (checkBox.isSelected()) {
            passwordVisibility.setImage(isVisibleEye);
            unMaskedPassword.setText( getPassword() );
        }
        else {
            passwordVisibility.setImage( isNotVisibleEye );
            unMaskedPassword.setText( "" );
        }
    }

    public void showHowToChangePassword() {
        alertBox.display( "Recover Password",
                "  Please visit 'password.bsu.edu' to recover your password  " );
    }

    public void verifyUserInfo() throws IOException {
        if (isValidUserName( getUsername() ) && isValidPassword( getPassword() ))
             { launchMainUI(); }
        else
            { alertBox.display( "Warning" ,"Invalid username or password" ); }
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

    private boolean isValidUserName(String username) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+(@bsu\\.edu)?");
        Matcher matcher = pattern.matcher(username);
        return (matcher.find() && matcher.group().equals(username));
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+([+-]?[0-9]+)?");
        Matcher matcher = pattern.matcher(password);
        return (matcher.find() && matcher.group().equals(password));
    }

    @SuppressWarnings( "unused" ) // will be used later
    public ArrayList<String> getUserInfo() {
        ArrayList<String> userInfo = new ArrayList <>();
        if(isValidUserName( getUsername() ) && isValidPassword( getPassword() )) {
            String username = this.getUsername();
            String password = this.getPassword();
            userInfo.add( username );
            userInfo.add( password );
        }
        return userInfo;
    }
}