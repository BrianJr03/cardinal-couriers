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
    public Label city;
    @FXML
    public Label zip;
    @FXML
    public Label state;
    @FXML
    public Label stAddress;
    @FXML
    public Label cartTotal;
    @FXML
    public Label costInDollars;
    @FXML
    private AnchorPane rootPane;
    @FXML
    public Label storeName;
    @FXML
    public CheckBox checkBox;
    @FXML
    public Button confirmCheckout;

    public String storeNameStored;

    public void initialize() {
        storeName.setVisible( true );
        costInDollars.setVisible( false );
        cartTotal.setVisible( false );
        stAddress.setVisible( true );
        zip.setVisible( true );
        city.setVisible( true );
        state.setVisible( true );
    }

    public void launchCartUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/cart.fxml"));
        Parent root = loader.load();
        CartUIController cartUIController = loader.getController();
        sendDataToCart( cartUIController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToCart(CartUIController cartUIController)
    { cartUIController.setStoreName( storeNameStored ); }

    public void launchPostPurchaseUI() throws IOException
    { launchUI( "/ui/postPurchaseUI.fxml" ); }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }
}
