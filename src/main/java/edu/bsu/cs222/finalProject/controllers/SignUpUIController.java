package edu.bsu.cs222.finalProject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.io.File;
import java.io.IOException;
import static edu.bsu.cs222.finalProject.LoginLogic.isValidPassword;
import static edu.bsu.cs222.finalProject.LoginLogic.isValidUserName;
import static edu.bsu.cs222.finalProject.Main.displayPromptFor3secs;

public class SignUpUIController {

    @FXML
    public Label pwFieldsMustMatch_Label;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField usernameInput;
    @FXML
    private CheckBox checkBox1;
    @FXML
    private Label unMaskedPassword_Label;
    @FXML
    private ImageView passwordVisibility_ImageView1;
    @FXML
    private Label invalidUserInfo_Prompt;
    @FXML
    private CheckBox checkBox2;
    @FXML
    private ImageView passwordVisibility_ImageView2;
    @FXML
    private Label username_Label;
    @FXML
    private Label password_Label;
    @FXML
    private Label hasMinReq_Label;
    @FXML
    private Label usernameReq_Label;
    @FXML
    private Label notContainUserReq_Label;
    @FXML
    private PasswordField passwordInput1;
    @FXML
    private PasswordField passwordInput2;

    String gray = "#a3a3a3";
    String green = "#08aa51";

    final File isVisiblePNG_File = new File("src/main/resources/pngs/isVisible.png");
    final Image isVisible_PNG = new Image(isVisiblePNG_File.toURI().toString());
    final File isNotVisiblePNG_File = new File("src/main/resources/pngs/isNotVisible.png");
    final Image isNotVisible_PNG = new Image(isNotVisiblePNG_File.toURI().toString());

    public void initialize() {
        passwordVisibility_ImageView1.setImage(isNotVisible_PNG);
        passwordVisibility_ImageView2.setImage( isNotVisible_PNG );
        invalidUserInfo_Prompt.setVisible( false );
    }

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

    public void verifyUserInfo() throws IOException {
        if (isValidUserName(usernameInput.getText()) && isValidPassword(usernameInput.getText(),
                passwordInput1.getText()) && passwordInput1.getText().equals( passwordInput2.getText()))
        { launchDeliveryUI(); }
        else { displayInvalidUserInfo_Prompt(); }
    }

    private void displayInvalidUserInfo_Prompt()
    { displayPromptFor3secs( invalidUserInfo_Prompt ); }

    public boolean verifyUserName_Length()
    { return usernameInput.getText().length() >= 5; }

    public void verifyUserInput() {
       if ( verifyUserName_Length()) {
           username_Label.setTextFill(Color.web(green));
           usernameReq_Label.setTextFill(Color.web(green)); }
       else {
           username_Label.setTextFill(Color.web(gray));
           usernameReq_Label.setTextFill(Color.web(gray)); }
    }

    public boolean verifyPassword_NotContainUser()
    { return !passwordInput1.getText().contains( usernameInput.getText() ); }

    public boolean verifyPassword_FieldsMatch()
    { return passwordInput1.getText().equals( passwordInput2.getText() ); }

    public boolean verifyNonEmpty_PWField()
    { return passwordInput1.getText().length() != 0; }

    public boolean verifyPassword_MinLength()
    { return passwordInput1.getText().length() >= 8; }

    public void verifyPasswordInput(){
        if ( verifyPassword_NotContainUser() && verifyNonEmpty_PWField()) { notContainUserReq_Label.setTextFill(Color.web(green)); }
        else {notContainUserReq_Label.setTextFill(Color.web(gray));}

        if ( verifyPassword_MinLength() && verifyNonEmpty_PWField()) { hasMinReq_Label.setTextFill(Color.web(green)); }
        else { hasMinReq_Label.setTextFill(Color.web(gray));}

        if ( verifyPassword_FieldsMatch() && verifyNonEmpty_PWField()) { pwFieldsMustMatch_Label.setTextFill(Color.web(green)); }
        else {pwFieldsMustMatch_Label.setTextFill(Color.web(gray)); }

        if ( verifyPassword_NotContainUser() && verifyPassword_MinLength() && verifyPassword_FieldsMatch() && verifyNonEmpty_PWField()) {
            password_Label.setTextFill(Color.web(green)); }
        else { password_Label.setTextFill(Color.web(gray));}
    }
}