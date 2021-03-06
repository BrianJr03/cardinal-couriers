package edu.bsu.cs222.finalProject.controllers;

import com.google.gson.JsonObject;
import edu.bsu.cs222.finalProject.DeliveryInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

import static edu.bsu.cs222.finalProject.DeliveryMap.*;
import static edu.bsu.cs222.finalProject.Main.displayPromptFor3secs;

public class DeliveryUIController {

    @FXML
    private TextField addressOne;
    @FXML
    private TextField zipCode;
    @FXML
    private TextField state;
    @FXML
    private TextField city;
    @FXML
    private Label outOfRange_Prompt;
    @FXML
    private Label invalidDeliveryInfo_Prompt;
    @FXML
    private AnchorPane rootPane;

    private DeliveryInfo deliveryInfo = new DeliveryInfo("","","","");
    public Button byPass, continueButton;
    public TextField addressTwo;

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    public void launchLoginUI() throws IOException
    { launchUI("/ui/loginUI.fxml"); }

    public void launchMainUI() throws IOException
    { launchUI("/ui/mainUI.fxml"); }

    public void displayInvalidDeliveryInfo_Prompt()
    { displayPromptFor3secs(invalidDeliveryInfo_Prompt); }

    public void displayOutOfRange_Prompt()
    { displayPromptFor3secs(outOfRange_Prompt); }

    public boolean deliveryInfoIsValid() {
        return  (deliveryInfo.isValidCity(deliveryInfo.getCity())
                && deliveryInfo.isValidStreet_Address(deliveryInfo.getStreetAddressLine1())
                && deliveryInfo.isValidState_Abbreviation(deliveryInfo.getState())
                && deliveryInfo.isValidZip(deliveryInfo.getZipCode()));
    }

    public void verifyDeliveryInput() throws IOException, NullPointerException {
        deliveryInfo = new DeliveryInfo(addressOne.getText(),zipCode.getText(),
                state.getText(), city.getText());
        if (deliveryInfoIsValid())
        { verifyDistance(); }
        else
        { displayInvalidDeliveryInfo_Prompt(); }
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