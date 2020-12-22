package edu.bsu.cs222.finalProject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;

import static edu.bsu.cs222.finalProject.LoginLogic.isValidPassword;
import static edu.bsu.cs222.finalProject.LoginLogic.isValidUserName;
import static edu.bsu.cs222.finalProject.Main.displayPromptFor3secs;


public class SignUpUIController {

    public AnchorPane rootPane;
    public TextField usernameInput;
    public CheckBox checkBox1;
    public Label unMaskedPassword_Label;
    public ImageView passwordVisibility_ImageView1;
    public Label invalidUserInfo_Prompt;
    public CheckBox checkBox2;
    public ImageView passwordVisibility_ImageView2;
    @FXML
    private PasswordField passwordInput1;
    @FXML
    private PasswordField passwordInput2;

    public void initialize() {
        passwordVisibility_ImageView1.setImage(isNotVisible_PNG);
        passwordVisibility_ImageView2.setImage( isNotVisible_PNG );
        invalidUserInfo_Prompt.setVisible( false );
    }

    final File isVisiblePNG_File = new File("src/main/resources/pngs/isVisible.png");
    final Image isVisible_PNG = new Image(isVisiblePNG_File.toURI().toString());
    final File isNotVisiblePNG_File = new File("src/main/resources/pngs/isNotVisible.png");
    final Image isNotVisible_PNG = new Image(isNotVisiblePNG_File.toURI().toString());

    public void showUnMaskedPassword_Field1() {
        if (checkBox1.isSelected()) {
            passwordVisibility_ImageView1.setImage(isVisible_PNG);
            unMaskedPassword_Label.setText("Field 1: " + passwordInput1.getText());
            unMaskedPassword_Label.setVisible(true); }
        else { passwordVisibility_ImageView1.setImage(isNotVisible_PNG); unMaskedPassword_Label.setVisible(false); }
    }

    public void showUnMaskedPassword_Field2() {
        if (checkBox2.isSelected()) {
            passwordVisibility_ImageView2.setImage(isVisible_PNG);
            unMaskedPassword_Label.setText("Field 2: " + passwordInput2.getText());
            unMaskedPassword_Label.setVisible(true); }
        else { passwordVisibility_ImageView2.setImage(isNotVisible_PNG); unMaskedPassword_Label.setVisible(false); }
    }

    public void launchDeliveryUI() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/ui/deliveryUI.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void verifyUserInfo() throws IOException
    {
        if (isValidUserName(usernameInput.getText()) && isValidPassword(usernameInput.getText(),
                passwordInput1.getText()) && passwordInput1.getText().equals( passwordInput2.getText() ))
        { launchDeliveryUI(); }
        else { displayInvalidUserInfo_Prompt(); }
    }

    private void displayInvalidUserInfo_Prompt( )
    { displayPromptFor3secs( invalidUserInfo_Prompt ); }
}
