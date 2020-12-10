package edu.bsu.cs222.finalProject.controllers;

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
import java.net.URI;
import java.net.URISyntaxException;

import static edu.bsu.cs222.finalProject.LoginLogic.isValidPassword;
import static edu.bsu.cs222.finalProject.LoginLogic.isValidUserName;

public class LoginUIController
{
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
    public Label invalidUserInfo_Prompt;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private TextField usernameInput;

    public void initialize() {
       passwordVisibility.setImage(isNotVisible_PNG);
       invalidUserInfo_Prompt.setVisible(false);
       unMaskedPassword.setVisible(false);
    }

    final File isVisiblePNG_File = new File("src/main/resources/pngs/isVisible.png");
    final Image isVisible_PNG = new Image(isVisiblePNG_File.toURI().toString());

    final File isNotVisiblePNG_File = new File("src/main/resources/pngs/isNotVisible.png");
    final Image isNotVisible_PNG = new Image(isNotVisiblePNG_File.toURI().toString());

    public void launchDeliveryUI() throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/ui/deliveryUI.fxml"));
            rootPane.getChildren().setAll(pane);
    }

    public void showUnMaskedPassword() {
        if (checkBox.isSelected()) {
            passwordVisibility.setImage(isVisible_PNG);
            unMaskedPassword.setText(getPassword());
            unMaskedPassword.setVisible(true); }
        else { passwordVisibility.setImage(isNotVisible_PNG); unMaskedPassword.setVisible(false); }
    }

    public void openSiteToChangePassword() throws URISyntaxException, IOException {
        URI uri= new URI("https://password.bsu.edu/forgot.aspx");
        java.awt.Desktop.getDesktop().browse(uri);
    }

    public void verifyUserInfo() throws IOException {
        if (isValidUserName(getUsername()) && isValidPassword(getUsername(), getPassword()))
             { launchDeliveryUI(); }
        else { displayInvalidUserInfo_Prompt(); }
    }

    public void displayInvalidUserInfo_Prompt() {
        invalidUserInfo_Prompt.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
        visiblePause.setOnFinished(event -> invalidUserInfo_Prompt.setVisible(false));
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
}