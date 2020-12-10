package edu.bsu.cs222.finalProject;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args)
    { launch( args ); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource( "/ui/loginUI.fxml" ));
        primaryStage.setTitle( "Cardinal Couriers" );
        primaryStage.setScene(new Scene(root,725,615));
        primaryStage.setResizable( false );
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {}

    public static void displayPromptFor3secs(Label prompt) {
        prompt.setVisible(true);
        PauseTransition visiblePause = new PauseTransition( Duration.seconds(3));
        visiblePause.setOnFinished( event -> prompt.setVisible(false) );
        visiblePause.play();
    }
}
