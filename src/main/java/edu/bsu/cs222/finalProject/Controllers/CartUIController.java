package edu.bsu.cs222.finalProject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CartUIController {

    @FXML
    private AnchorPane rootPane;

    String storeNameStored;
    String addressStored;
    String zipStored;
    String cityStored;
    String stateStored;

    public void launchOrderConfirmUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/orderConfirm.fxml"));
        Parent root = loader.load();
        OrderConfirmController orderConfirmController = loader.getController();
        sendDataToOrderConfirm( orderConfirmController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToOrderConfirm(OrderConfirmController orderConfirmController) {
        orderConfirmController.setStoreName(storeNameStored);
        orderConfirmController.setZipText( zipStored );
        orderConfirmController.setCityText( cityStored );
        orderConfirmController.setStateText( stateStored );
        orderConfirmController.setAddressText( addressStored );
    }

    public void launchStoreUI() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreUIController store = loader.getController();
        sendDataToStore( store );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToStore(StoreUIController store) throws IOException {
        store.setStoreNameFromCart( storeNameStored );
        store.populateTableWithItems( storeNameStored );
        store.setZipText( zipStored );
        store.setCityText( cityStored );
        store.setStateText( stateStored );
        store.setAddressText( addressStored );
    }

    public void setStoreName(String storeName)
    { this.storeNameStored = storeName; }

    public void setCityText( String cityStored )
    { this.cityStored = cityStored; }

    public void setZipText( String zipStored )
    { this.zipStored = zipStored; }

    public void setAddressText( String addressStored )
    { this.addressStored = addressStored; }

    public void setStateText( String stateStored )
    { this.stateStored = stateStored; }
}