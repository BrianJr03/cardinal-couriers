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
    private Label alreadyHaveCC_Label;
    @FXML
    private Label bsu_Student_Label;
    @FXML
    private Label signUpToday_Label;
    @FXML
    private VBox vBox;
    @FXML
    private Parent fxml;

    @Override
    public void initialize( URL location , ResourceBundle resources )  {
        alreadyHaveCC_Label.setVisible( false );
        bsu_Student_Label.setVisible( false );
        try { loadUI_IntoVBox(); } catch ( IOException ignored ){}
        TranslateTransition transition = new TranslateTransition( Duration.seconds( 1 ), vBox);
        transition.setToX( vBox.getLayoutX() * 20 );
        transition.play();
        transition.setOnFinished( (e) -> {
            try { loadUI_IntoVBox(); }
            catch ( IOException ignored ) {}
        } );
    }

    public void loadUI_IntoVBox() throws IOException {
        fxml = FXMLLoader.load( getClass().getResource( "/ui/loginUI.fxml" ));
        vBox.getChildren().removeAll();
        vBox.getChildren().setAll( fxml );
    }

    @FXML
    private void launchSignIn() {
        TranslateTransition transition = new TranslateTransition( Duration.seconds( 1 ), vBox);
        transition.setToX( vBox.getLayoutX() * 20 );
        transition.play();
        alreadyHaveCC_Label.setVisible( false );
        transition.setOnFinished( (e) -> {
            try {
                fxml = FXMLLoader.load( getClass().getResource( "/ui/loginUI.fxml" ));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll( fxml );
                alreadyHaveCC_Label.setVisible( false );
                bsu_Student_Label.setVisible( false );
                signUpToday_Label.setVisible( true );
            }
            catch ( IOException ignored ) {}
        } );
    }

    @FXML
    private void launchSignUp() {
        alreadyHaveCC_Label.setVisible( false );
        bsu_Student_Label.setVisible( true );
        TranslateTransition transition = new TranslateTransition( Duration.seconds( 1 ), vBox);
        transition.setToX(0);
        transition.play();
        signUpToday_Label.setVisible( false );
        transition.setOnFinished( (e) -> {
            try {
                fxml = FXMLLoader.load( getClass().getResource( "/ui/signupUI.fxml" ));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll( fxml );
                alreadyHaveCC_Label.setVisible( true );
            }
            catch ( IOException ignored ) {}
        } );
    }

    public void closeProgram()
    { System.exit(0); }
}
