package edu.bsu.cs222.finalProject.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class CartUIController
{
    public AnchorPane rootPane;

    public void launchStoreUI() throws IOException
    { launchUI( "/ui/storeUI.fxml" ); }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }
}