package edu.bsu.cs222.finalProject.controllers;

import edu.bsu.cs222.finalProject.Cart;
import edu.bsu.cs222.finalProject.Item;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class OrderConfirmController {
    @FXML
    public Label cartTotalLabel;
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
    @FXML
    public TableView<Item> orderConfirmTable;
    @FXML
    TableColumn< Item, String > nameColumn;
    @FXML
    TableColumn<Item, Double> priceColumn;
    @FXML
    TableColumn<String, String> quantityColumn;

    public final Cart cart = new Cart(FXCollections.observableArrayList());
    public String storeNameStored;

    public void initialize() {
        storeName.setVisible(true);
        costInDollars.setVisible(true);
        cartTotalLabel.setVisible(true);
        orderConfirmTable.setEditable(true);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    public void launchCartUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/cart.fxml"));
        Parent root = loader.load();
        CartUIController cartUIController = loader.getController();
        sendDataToCart(cartUIController);
        rootPane.getChildren().setAll(root);
    }

    public void sendDataToCart(CartUIController cartUIController) {
        cartUIController.cart.setCartItems(this.cart.getItems());
        cartUIController.storeNameStored = this.storeName.getText();
        cartUIController.costInDollars.setText(String.valueOf(this.cart.getTotalCost()));
        cartUIController.initialize();
        cartUIController.storeNameStored = storeNameStored; }

    public void launchPostPurchaseUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/postPurchaseUI.fxml"));
        Parent root = loader.load();
        PostPurchaseController postPurchaseController = loader.getController();
        postPurchaseController.cart.setCartItems(this.cart.getItems());
        rootPane.getChildren().setAll(root);
    }
}
