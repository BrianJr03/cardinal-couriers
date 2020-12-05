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
    String zipCodeFromDelivery;

    @FXML
    String addressOneFromDelivery;

    StoreInfo storeInfo = new StoreInfo();

    public void sendAddressInfoToDelivery() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/deliveryUI.fxml"));
        Parent root = loader.load();
        DeliveryUIController delivery = loader.getController();
        delivery.setAddressInfoFromMain( zipCodeFromDelivery , addressOneFromDelivery );
        rootPane.getChildren().setAll( root );
    }

    public void setAddressInfoFromDelivery(String zipCode, String addressOne)
    { this.zipCodeFromDelivery = zipCode; this.addressOneFromDelivery = addressOne; }

    @FXML
    public void launchDeliveryUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/deliveryUI.fxml"));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
        sendAddressInfoToDelivery();
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