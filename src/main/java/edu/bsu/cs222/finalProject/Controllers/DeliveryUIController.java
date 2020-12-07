package edu.bsu.cs222.finalProject.Controllers;

import com.google.gson.JsonObject;
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

import static edu.bsu.cs222.finalProject.DeliveryMap.collectJsonObjectFromGoogle;
import static edu.bsu.cs222.finalProject.DeliveryMap.findDistanceFromBSU;

public class DeliveryUIController
{

    public TextField addressOne;
    public TextField zipCode;
    public Label inRange_Prompt;
    public Label outOfRange_Prompt;
    public Label invalidDeliveryInfo_Prompt;
    public Button continueButton;
    public AnchorPane rootPane;
    public String storedZipCode;
    public String storedAddress;

    public void initialize() {
        inRange_Prompt.setVisible( false );
        outOfRange_Prompt.setVisible( false );
        invalidDeliveryInfo_Prompt.setVisible( false );
    }

    public void displayPromptFor10secs(Label prompt) {
        prompt.setVisible( true );
        PauseTransition visiblePause = new PauseTransition( Duration.seconds(10));
        visiblePause.setOnFinished( event -> prompt.setVisible(false) );
        visiblePause.play();
    }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    public boolean isValidZip( String zipCode ) {
        Pattern pattern = Pattern.compile("\\d{5}");
        Matcher matcher = pattern.matcher(zipCode);
        return (matcher.find() && matcher.group().equals(zipCode));
    }

    public void displayInvalidDeliveryInfo_Prompt()
    { displayPromptFor10secs(invalidDeliveryInfo_Prompt); }

    public void launchLoginUI() throws IOException
    { storeDeliveryInfo(); launchUI( "/ui/loginUI.fxml" ); }

    public void launchMainUI() throws IOException
    { storeDeliveryInfo(); launchUI( "/ui/mainUI.fxml" ); }

    @SuppressWarnings( "unused" )
    public void displayOutOfRange_Prompt()
    { displayPromptFor10secs(outOfRange_Prompt); }

    @SuppressWarnings( "unused" )
    public void displayInRange_Prompt()
    { displayPromptFor10secs(inRange_Prompt); }

    public void verifyDeliveryInput() throws IOException {
        JsonObject mapsData = collectJsonObjectFromGoogle(addressOne.getText());
        if (findDistanceFromBSU(mapsData) == null) {
            displayInvalidDeliveryInfo_Prompt();
        } else if (findDistanceFromBSU(mapsData) > 10) {
            displayOutOfRange_Prompt();
        } else {
            launchMainUI();
        }
    }

    public void storeDeliveryInfo()
    {
       storedZipCode =  this.zipCode.getText() ;
       storedAddress = this.addressOne.getText();
    }

    public void setAddress( String address )
    {
        addressOne.setText( address );
    }

    public void setZip( String zip )
    {
        zipCode.setText( zip );
    }




}