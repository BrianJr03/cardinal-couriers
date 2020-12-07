package edu.bsu.cs222.finalProject.Controllers;

//Delete class before submission
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TestControllerTwo {

    public Label Label;
    public Pane rootPane;

    public void launchScene1() throws IOException
    { sendLabelToSceneOne(); }

    public void sendLabelToSceneOne() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/testUI/testUIOne.fxml" ));
        Parent root = loader.load();
        TestControllerOne test1 = loader.getController();
        String textToSend = "from scene two";
        test1.setLabelText( textToSend );
        rootPane.getChildren().setAll( root );
    }

    public void setLabelText(String labelTextStored) {
        Label.setText( labelTextStored );
    }
}
