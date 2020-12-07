package edu.bsu.cs222.finalProject.Controllers;


//Delete class before submission
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TestControllerOne {

    public Label Label;
    public Pane rootPane;

    public void launchScene2() throws IOException
    { sendLabelToSceneTwo(); }

    public void sendLabelToSceneTwo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/testUI/testUITwo.fxml" ));
        Parent root = loader.load();
        TestControllerTwo test2 = loader.getController();
        String textToSend = "from scene one";
        test2.setLabelText( textToSend );
        rootPane.getChildren().setAll( root );
    }

    public void setLabelText(String labelTextStored ) {
        Label.setText( labelTextStored );
    }
}
