package edu.bsu.cs222.finalProject;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
    public void display(String title, String message) {
        Stage window = new Stage();
        window.initModality( Modality.APPLICATION_MODAL );
        window.setMinWidth( 200 );
        window.setResizable( false );
        window.setTitle( title );

        Label label = new Label();
        label.setText( message );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment( Pos.CENTER );

        Scene scene = new Scene( layout );
        window.setScene( scene );
        window.showAndWait();
    }
}