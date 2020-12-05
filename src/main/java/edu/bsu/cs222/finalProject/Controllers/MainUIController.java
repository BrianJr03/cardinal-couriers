package edu.bsu.cs222.finalProject.Controllers;

import edu.bsu.cs222.finalProject.StoreInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainUIController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    String zipCode;

    @FXML
    String addressOne;

    StoreInfo storeInfo = new StoreInfo();

    public void setAddressInfoFromDelivery(String zipCode, String addressOne)
    { this.zipCode = zipCode; this.addressOne = addressOne; }

    public void sendAddressInfoToMain() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/deliveryUI.fxml"));
        Parent root = loader.load();
        DeliveryUIController delivery = loader.getController();
        delivery.setAddressInfoFromMain( zipCode, addressOne );
        rootPane.getChildren().setAll( root );
    }

    @FXML
    public void launchDeliveryUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/deliveryUI.fxml"));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
        sendAddressInfoToMain();
    }

    @FXML
    private void launchStoreUI(StoreInfo storeInfo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreUIController store = loader.getController();
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