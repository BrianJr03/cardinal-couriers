package edu.bsu.cs222.finalProject.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OrderConfirmController {

    public AnchorPane rootPane;
    public Label storeName;
    public Label orderTotal;
    public CheckBox checkBox;
    public Label deliveryAddressInfo;
    public Button confirmCheckout;
    public Label cartTotalLabel;

    public void initialize() {
        storeName.setVisible( false );
        orderTotal.setVisible( false );
        deliveryAddressInfo.setVisible( false );
        cartTotalLabel.setVisible( false );
    }

    public void launchStoreUI() throws IOException
    { launchUI( "/ui/storeUI.fxml" ); }

    public void launchCartUI() throws IOException
    { launchUI( "/ui/cart.fxml" ); }

    public void launchPostPurchaseUI() throws IOException
    { launchUI( "/ui/postPurchaseUI.fxml" ); }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }
}
