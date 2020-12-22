package edu.bsu.cs222.finalProject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static edu.bsu.cs222.finalProject.LoginLogic.isValidPassword;
import static edu.bsu.cs222.finalProject.LoginLogic.isValidUserName;
import static edu.bsu.cs222.finalProject.Main.displayPromptFor3secs;

public class LoginUIController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ImageView passwordVisibility_ImageView;
    @FXML
    private Label unMaskedPassword_Label;
    @FXML
    public Label invalidUserInfo_Prompt;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private TextField usernameInput;

    public Button forgotPasswordButton;

    public void initialize()
    { passwordVisibility_ImageView.setImage(isNotVisible_PNG); }

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
            passwordVisibility_ImageView.setImage(isVisible_PNG);
            unMaskedPassword_Label.setText(passwordInput.getText());
            unMaskedPassword_Label.setVisible(true); }
        else { passwordVisibility_ImageView.setImage(isNotVisible_PNG); unMaskedPassword_Label.setVisible(false); }
    }

    public void openSiteToChangePassword() throws URISyntaxException, IOException {
        URI uri= new URI("https://password.bsu.edu/forgot.aspx");
        java.awt.Desktop.getDesktop().browse(uri);
    }

    public void verifyUserInfo() throws IOException {
        if (isValidUserName(usernameInput.getText()) && isValidPassword(usernameInput.getText(), passwordInput.getText()))
             { launchDeliveryUI(); }
        else { displayInvalidUserInfo_Prompt(); }
    }

    public void displayInvalidUserInfo_Prompt()
    { displayPromptFor3secs(invalidUserInfo_Prompt); }
}