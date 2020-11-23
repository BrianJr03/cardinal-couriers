package edu.bsu.cs222.finalProject;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    public Button forgotPassword_Btn;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView passwordVisibility;

    @FXML
    private Label unMaskedPassword;

    @FXML
    public Label forgotPassword_MSG;

    @FXML
    public Label invalidUserInfo_MSG;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;

    public void initialize() {
       passwordVisibility.setImage( isNotVisible_PNG );
        forgotPassword_MSG.setVisible( false );
        invalidUserInfo_MSG.setVisible( false );
        unMaskedPassword.setVisible( false );
    }

    File isVisiblePNG_File = new File( "src/main/resources/pngs/isVisible.png" );
    Image isVisible_PNG = new Image( isVisiblePNG_File.toURI().toString() );

    File isNotVisiblePNG_File = new File( "src/main/resources/pngs/isNotVisible.png" );
    Image isNotVisible_PNG = new Image( isNotVisiblePNG_File.toURI().toString() );

    public void launchMainUI() throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource( "/ui/mainUI.fxml" ));
            rootPane.getChildren().setAll( pane );
    }

    public void showUnMaskedPassword() {
        if (checkBox.isSelected()) {
            passwordVisibility.setImage( isVisible_PNG );
            unMaskedPassword.setText( getPassword() );
            unMaskedPassword.setVisible( true ); }
        else { passwordVisibility.setImage( isNotVisible_PNG ); unMaskedPassword.setVisible( false ); }
    }

    public void showHowToChangePassword_MSG()  {
        forgotPassword_MSG.setVisible( true );
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished( event -> forgotPassword_MSG.setVisible(false) );
        visiblePause.play();
    }

    public void verifyUserInfo() throws IOException {
        if (isValidUserName( getUsername() ) && isValidPassword( getPassword() ))
             { launchMainUI(); }
        else { displayInvalidUserInfo_MSG(); }
    }

    public void displayInvalidUserInfo_MSG() {
        invalidUserInfo_MSG.setVisible( true );
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
        visiblePause.setOnFinished( event -> invalidUserInfo_MSG.setVisible(false) );
        visiblePause.play();
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
        if (getUsername().length() < 5) {return false;}
        return (matcher.find() && matcher.group().equals(username));
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("([$&+,:;=?@#|'<>.^*()%!-]?+[a-zA-Z]+([+-]?[0-9]+)?+" +
                "[$&+,:;=?@#|'<>.^*()%!-]?)");
        Matcher matcher = pattern.matcher(password);
        if (getPassword().length() < 12 || getPassword().contains( getUsername() ) )
        {return false;}
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