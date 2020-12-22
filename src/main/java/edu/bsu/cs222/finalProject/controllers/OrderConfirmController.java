package edu.bsu.cs222.finalProject.controllers;

import edu.bsu.cs222.finalProject.Cart;
import edu.bsu.cs222.finalProject.Item;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import static edu.bsu.cs222.finalProject.controllers.CartUIController.setTableProperties;

public class OrderConfirmController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn< Item, String > nameColumn;
    @FXML
    private TableColumn<Item, Double> priceColumn;
    @FXML
    private TableColumn<Item, String> quantityColumn;

    public Label storeName;
    public Label costInDollars;
    public TableView<Item> orderConfirmTable;
    public final Cart cart = new Cart(FXCollections.observableArrayList());
    public String storeNameStored;
    public Button confirmCheckout;
    public CheckBox checkBox;
    public Label cartTotalLabel;

    public void initialize()
    { setTableProperties(nameColumn, priceColumn, quantityColumn, new TableColumn<>(), new TableColumn<>()); }

    public void launchCartUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/cart.fxml"));
        Parent root = loader.load();
        CartUIController cartUIController = loader.getController();
        sendDataToCart(cartUIController);
        rootPane.getChildren().setAll(root);
    }

    public void sendDataToCart(CartUIController cartUIController) {
        cartUIController.cart.setCartItems(this.cart.getItems());
        cartUIController.storeName = this.storeName.getText();
        cartUIController.costInDollars.setText(String.valueOf(this.cart.getTotalCost()));
        cartUIController.initialize();
        cartUIController.storeName = storeNameStored; }

    public void launchPostPurchaseUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/postPurchaseUI.fxml"));
        Parent root = loader.load();
        PostPurchaseController postPurchaseController = loader.getController();
        postPurchaseController.cart.setCartItems(this.cart.getItems());
        rootPane.getChildren().setAll(root);
    }
}
