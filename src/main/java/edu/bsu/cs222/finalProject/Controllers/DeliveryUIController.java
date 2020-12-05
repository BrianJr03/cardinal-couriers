package edu.bsu.cs222.finalProject.Controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeliveryUIController
{

    public TextField addressOne;
    public TextField zipCode;
    public Label inRange_Prompt;
    public Label outOfRange_Prompt;
    public Label invalidDeliveryInfo_Prompt;
    public Button continueButton;
    public AnchorPane rootPane;

    public void initialize() {
        inRange_Prompt.setVisible( false );
        outOfRange_Prompt.setVisible( false );
        invalidDeliveryInfo_Prompt.setVisible( false );
    }

    public void sendAddressInfoToMain() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/mainUI.fxml"));
        Parent root = loader.load();
        MainUIController mainUI = loader.getController();
        mainUI.setAddressInfoFromDelivery( zipCode.getText(), addressOne.getText() );
        rootPane.getChildren().setAll( root );
    }

    public void setAddressInfoFromMain(String zipCodeFromMain, String addressOneFromMain)
    { zipCode.setText( zipCodeFromMain ); addressOne.setText( addressOneFromMain ); }

    public void displayPromptFor2secs(Label prompt) {
        prompt.setVisible( true );
        PauseTransition visiblePause = new PauseTransition( Duration.seconds(2));
        visiblePause.setOnFinished( event -> prompt.setVisible(false) );
        visiblePause.play();
    }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
        sendAddressInfoToMain();
    }

    public boolean isValidZip( String zipCode ) {
        Pattern pattern = Pattern.compile("\\d{5}");
        Matcher matcher = pattern.matcher(zipCode);
        return (matcher.find() && matcher.group().equals(zipCode));
    }

    public void displayInvalidDeliveryInfo_Prompt()
    { displayPromptFor2secs(invalidDeliveryInfo_Prompt); }

    public void launchLoginUI() throws IOException
    { launchUI( "/ui/loginUI.fxml" ); }

    public void launchMainUI() throws IOException
    { launchUI( "/ui/mainUI.fxml" ); }

    @SuppressWarnings( "unused" )
    public void displayOutOfRange_Prompt()
    { displayPromptFor2secs(outOfRange_Prompt); }

    @SuppressWarnings( "unused" )
    public void displayInRange_Prompt()
    { displayPromptFor2secs(inRange_Prompt); }

    public void verifyDeliveryInput() throws IOException {
        if (    zipCode.getText().length() == 0
                || addressOne.getText().length() == 0
                || !isValidZip(zipCode.getText()))
        { displayInvalidDeliveryInfo_Prompt(); }
        else { launchMainUI(); }
    }
}