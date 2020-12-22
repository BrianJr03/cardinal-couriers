package edu.bsu.cs222.finalProject.controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {

    @FXML
    private VBox vBox;
    @FXML
    private Parent fxml;

    @Override
    public void initialize( URL location , ResourceBundle resources ) {
        TranslateTransition transition = new TranslateTransition( Duration.seconds( 1 ), vBox);
        transition.setToX( vBox.getLayoutX() * 20 );
        transition.play();
        transition.setOnFinished( (e) -> {
            try {
                fxml = FXMLLoader.load( getClass().getResource( "/ui/loginUI.fxml" ));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll( fxml );
            }
            catch ( IOException ignored ) {}
        } );
    }

    @FXML
    private void launchSignIn() {
        TranslateTransition transition = new TranslateTransition( Duration.seconds( 1 ), vBox);
        transition.setToX( vBox.getLayoutX() * 20 );
        transition.play();
        transition.setOnFinished( (e) -> {
            try {
                fxml = FXMLLoader.load( getClass().getResource( "/ui/loginUI.fxml" ));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll( fxml );
            }
            catch ( IOException ignored ) {}
        } );
    }

    @FXML
    private void launchSignUp() {
        TranslateTransition transition = new TranslateTransition( Duration.seconds( 1 ), vBox);
        transition.setToX(0);
        transition.play();
        transition.setOnFinished( (e) -> {
            try {
                fxml = FXMLLoader.load( getClass().getResource( "/ui/signupUI.fxml" ));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll( fxml );
            }
            catch ( IOException ignored ) {}
        } );
    }

    public void closeProgram()
    { System.exit(0); }
}
