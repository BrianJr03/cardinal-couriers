package edu.bsu.cs222.finalProject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OrderConfirmController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    public Label storeName;
    @FXML
    public Label orderTotal;
    @FXML
    public CheckBox checkBox;
    @FXML
    public Label deliveryAddressInfo;
    @FXML
    public Button confirmCheckout;
    @FXML
    public Label cartUIController;

    String storeNameStored;
    String addressStored;
    String zipStored;
    String cityStored;
    String stateStored;

    public void initialize() {
        storeName.setVisible( false );
        orderTotal.setVisible( false );
        deliveryAddressInfo.setVisible( false );
        cartUIController.setVisible( false );
    }

    public void launchCartUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/cart.fxml"));
        Parent root = loader.load();
        CartUIController cartUIController = loader.getController();
        sendDataToCart( cartUIController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToCart(CartUIController cartUIController) {
        cartUIController.setStoreName( storeNameStored );
        cartUIController.setZipText( zipStored );
        cartUIController.setCityText( cityStored );
        cartUIController.setStateText( stateStored );
        cartUIController.setAddressText( addressStored );

    }

    public void launchPostPurchaseUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/postPurchaseUI.fxml"));
        Parent root = loader.load();
        PostPurchaseController postPurchaseController = loader.getController();
        sendDataToPostPurchase( postPurchaseController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToPostPurchase(PostPurchaseController postPurchaseController) {
        postPurchaseController.setZipText( zipStored );
        postPurchaseController.setZipText( zipStored );
        postPurchaseController.setCityText( cityStored );
        postPurchaseController.setStateText( stateStored );
        postPurchaseController.setAddressText( addressStored );
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
