package edu.bsu.cs222.finalProject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainUIController
{
    @FXML
    private AnchorPane rootPane;

    @FXML
    public void launchLoginUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/loginUI.fxml"));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    @FXML //Create object instead of String
    private void launchStoreUI(String storeName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreController store = loader.getController();
        store.showStoreName(storeName);
        rootPane.getChildren().setAll( root );
    }

    @FXML
    private void sendData_Walmart() throws IOException {
        String storeName = "Walmart";
        launchStoreUI( storeName );
    }

    @FXML
    private void sendData_Kroger() throws IOException {
        String storeName = "Kroger";
        launchStoreUI( storeName );
    }

    @FXML
    private void sendData_ALDI() throws IOException {
        String storeName = "ALDI";
        launchStoreUI( storeName );
    }
}