package edu.bsu.cs222.finalProject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class CartUIController
{
    public AnchorPane rootPane;

    @FXML
    String storeName;

    public void launchStoreUI() throws IOException
    { sendStoreNameToStoreUI(); launchUI( "/ui/storeUI.fxml" ); }

    public void launchOrderConfirmUI() throws IOException
    { launchUI( "/ui/orderConfirm.fxml" ); }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    public void sendStoreNameToStoreUI() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreUIController store = loader.getController();
        store.setStoreNameFromCart(storeName);
        rootPane.getChildren().setAll( root );
    }

    public void setAddressInfoFromDelivery(String storeName)
    { this.storeName = storeName; }
}