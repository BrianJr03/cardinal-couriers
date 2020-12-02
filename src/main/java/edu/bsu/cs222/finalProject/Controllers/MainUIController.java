package edu.bsu.cs222.finalProject.Controllers;

import edu.bsu.cs222.finalProject.StoreInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainUIController
{
    @FXML
    private AnchorPane rootPane;

    StoreInfo storeInfo = new StoreInfo();

    @FXML
    public void launchLoginUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/loginUI.fxml"));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    @FXML
    private void launchStoreUI(StoreInfo storeInfo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreController store = loader.getController();
        store.showStoreName(storeInfo.getStoreName());
        rootPane.getChildren().setAll( root );
    }

    @FXML
    private void sendData_Walmart() throws IOException {
        storeInfo.setStoreName( "Walmart" );
        launchStoreUI( storeInfo );
    }

    @FXML
    private void sendData_Kroger() throws IOException {
        storeInfo.setStoreName( "Kroger" );
        launchStoreUI( storeInfo );
    }

    @FXML
    private void sendData_ALDI() throws IOException {
        storeInfo.setStoreName( "ALDI" );
        launchStoreUI( storeInfo );
    }
}