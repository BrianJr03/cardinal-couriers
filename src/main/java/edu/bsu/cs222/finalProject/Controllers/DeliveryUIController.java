package edu.bsu.cs222.finalProject.Controllers;

import com.google.gson.JsonObject;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.bsu.cs222.finalProject.DeliveryMap.*;

public class DeliveryUIController {

    @FXML
    public TextField addressOne;
    @FXML
    public TextField zipCode;
    @FXML
    public TextField state;
    @FXML
    public TextField city;
    @FXML
    private Label inRange_Prompt;
    @FXML
    private Label outOfRange_Prompt;
    @FXML
    private Label invalidDeliveryInfo_Prompt;
    @FXML
    public Button continueButton;
    @FXML
    private AnchorPane rootPane;

    public void initialize() {
        inRange_Prompt.setVisible( false );
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

    public boolean isValidZip( String zipCode ) {
        Pattern pattern = Pattern.compile("\\d{5}");
        Matcher matcher = pattern.matcher(zipCode);
        return (matcher.find() && matcher.group().equals(zipCode));
    }

    public boolean isValidCity(String city) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
        Matcher matcher = pattern.matcher(city);
        if ( city.length() < 3 ) {return false;}
        return (matcher.find() && matcher.group().equals(city));
    }

    public boolean isValidState_Abbreviation( String state) {
        Pattern pattern = Pattern.compile("^(?:(A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|" +
                "N[CDEHJMVY]|O[HKR]|P[AR]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY]))$");
        Matcher matcher = pattern.matcher(state);
        return (matcher.find() && matcher.group().equals(state));
    }

    public void displayInvalidDeliveryInfo_Prompt()
    { displayPromptFor2secs(invalidDeliveryInfo_Prompt); }

    public void launchLoginUI() throws IOException
    { launchUI( "/ui/loginUI.fxml" ); }

    public void launchMainUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/mainUI.fxml"));
        Parent root = loader.load();
        MainUIController mainUIController = loader.getController();
        sendDataToMain( mainUIController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToMain( MainUIController mainUIController ) {
        mainUIController.setCityText(city.getText());
        mainUIController.setZipText(zipCode.getText());
        mainUIController.setAddressText(addressOne.getText());
        mainUIController.setStateText(state.getText());
    }

    public void displayOutOfRange_Prompt()
    { displayPromptFor2secs(outOfRange_Prompt); }

    @SuppressWarnings( "unused" )
    public void displayInRange_Prompt()
    { displayPromptFor2secs(inRange_Prompt); }

    public void verifyDeliveryInput() throws IOException, NullPointerException {
        if (            !isValidCity( city.getText() )
                        || addressOne.getText().length() == 0
                        || !isValidState_Abbreviation( state.getText() )
                        || !isValidZip( zipCode.getText() ) )
        { displayInvalidDeliveryInfo_Prompt(); }

        else{
            JsonObject mapsData = collectJsonObjectFromGoogle(addressOne.getText(),city.getText(),state.getText());
            if (findDistanceFromBSU(mapsData) == null) {
                displayInvalidDeliveryInfo_Prompt();
            } else if (findDistanceFromBSU(mapsData) > 10) {
                displayOutOfRange_Prompt();
            } else {
                launchMainUI();
            }}
    }
}