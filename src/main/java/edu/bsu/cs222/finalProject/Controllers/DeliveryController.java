package edu.bsu.cs222.finalProject.Controllers;

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

@SuppressWarnings( "unused" )
public class DeliveryController {
    public TextField zipTextField;
    public Label inRange_Prompt;
    public Label outOfRange_Prompt;
    public Button continueButton;
    public AnchorPane rootPane;

    public void initialize()
    {
        inRange_Prompt.setVisible( false );
        outOfRange_Prompt.setVisible( false );
    }

    public void displayPrompt_2secs( Label range_Prompt) {
        range_Prompt.setVisible( true );
        PauseTransition visiblePause = new PauseTransition( Duration.seconds(2));
        visiblePause.setOnFinished( event -> range_Prompt.setVisible(false) );
        visiblePause.play();
    }

    @FXML
    public void launchLoginUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/loginUI.fxml"));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    @FXML
    public void launchMainUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/mainUI.fxml"));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    public void displayOutOfRange_Prompt() {
        displayPrompt_2secs(outOfRange_Prompt);
    }

    public void displayInRange_Prompt() {
        displayPrompt_2secs(inRange_Prompt);
    }
}
