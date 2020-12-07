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
    public Label cartTotalLabel;
    String storeNameStored;

    public void initialize() {
        storeName.setVisible( false );
        orderTotal.setVisible( false );
        deliveryAddressInfo.setVisible( false );
        cartTotalLabel.setVisible( false );
    }

    public void launchStoreUI() throws IOException
    { launchUI( "/ui/storeUI.fxml" ); }

    public void launchCartUI() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/cart.fxml"));
        Parent root = loader.load();
        CartUIController cart = loader.getController();

        //send data here
        cart.setStoreName( storeNameStored );

        rootPane.getChildren().setAll( root ); }

    public void launchPostPurchaseUI() throws IOException
    { launchUI( "/ui/postPurchaseUI.fxml" ); }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    public void setStoreName( String storeNameStored )
    { this.storeNameStored = storeNameStored; }
}
