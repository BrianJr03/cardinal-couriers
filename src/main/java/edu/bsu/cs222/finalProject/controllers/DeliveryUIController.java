package edu.bsu.cs222.finalProject.controllers;

import com.google.gson.JsonObject;
import edu.bsu.cs222.finalProject.DeliveryInfo;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Objects;

import static edu.bsu.cs222.finalProject.DeliveryMap.*;

public class DeliveryUIController {

    @FXML
    public TextField addressOne;
    @FXML
    public TextField addressTwo;
    @FXML
    public TextField zipCode;
    @FXML
    public TextField state;
    @FXML
    public TextField city;
    @FXML
    private Label outOfRange_Prompt;
    @FXML
    private Label invalidDeliveryInfo_Prompt;
    @FXML
    public Button continueButton;
    @FXML
    private AnchorPane rootPane;

    public DeliveryInfo deliveryInfo;

    public void initialize() {
        outOfRange_Prompt.setVisible( false );
        invalidDeliveryInfo_Prompt.setVisible( false );
    }

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
    }

    public void displayInvalidDeliveryInfo_Prompt()
    { displayPromptFor2secs(invalidDeliveryInfo_Prompt); }

    public void launchLoginUI() throws IOException
    { launchUI( "/ui/loginUI.fxml" ); }

    public void launchMainUI() throws IOException
    { launchUI( "/ui/mainUI.fxml" ); }

    public void displayOutOfRange_Prompt()
    { displayPromptFor2secs(outOfRange_Prompt); }

    public boolean verifyDeliveryInfo() {
        return  ( !deliveryInfo.isValidCity( deliveryInfo.getCity() )
                || !deliveryInfo.isValidStreet_Address( deliveryInfo.getStreetAddressLine1() )
                || !deliveryInfo.isValidState_Abbreviation( deliveryInfo.getState() )
                || !deliveryInfo.isValidZip( deliveryInfo.getZipCode() ) );
    }

    public void verifyDeliveryInput() throws IOException, NullPointerException {
        deliveryInfo = new DeliveryInfo( addressOne.getText(),zipCode.getText(),
                state.getText(), city.getText() );
        if (verifyDeliveryInfo())
            { displayInvalidDeliveryInfo_Prompt(); }
        else
            { verifyDistance();}
    }

    public void verifyDistance() throws IOException {
        JsonObject mapsData = collectJsonObjectFromGoogle(deliveryInfo.getStreetAddressLine1(),deliveryInfo.getCity(),
                deliveryInfo.getState());
        if (findDistanceFromBSU(mapsData) == null) {
            displayInvalidDeliveryInfo_Prompt();
        } else if (Objects.requireNonNull(findDistanceFromBSU(mapsData)).floatValue() > 10) {
            displayOutOfRange_Prompt();
        } else {
            launchMainUI();
        }
    }
}