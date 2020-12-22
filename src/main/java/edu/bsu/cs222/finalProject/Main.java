package edu.bsu.cs222.finalProject;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.File;

public class Main extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args)
    { launch( args ); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        File groceriesPNG_File = new File("src/main/resources/pngs/groceries32x29.png.png");
        Parent root = FXMLLoader.load(getClass().getResource( "/ui/mainUI.fxml" ));
        Scene scene = new Scene( root );
        scene.setFill( Color.TRANSPARENT );
        primaryStage.initStyle( StageStyle.TRANSPARENT );
        primaryStage.getIcons().add( new Image( groceriesPNG_File.toURI().toString() ));
        primaryStage.setTitle( "Cardinal Couriers" );
        primaryStage.setScene(scene);
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
