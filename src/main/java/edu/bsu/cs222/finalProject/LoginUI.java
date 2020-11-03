package edu.bsu.cs222.finalProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginUI extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args)
    { launch( args ); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource( "/ui/login.fxml" ));

        primaryStage.setTitle("Grocery Shop");
        primaryStage.setScene(new Scene(root,725,615));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {}
}