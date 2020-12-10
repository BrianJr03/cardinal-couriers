package edu.bsu.cs222.finalProject.controllers;

import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import static edu.bsu.cs222.finalProject.Inventory.collectItemsAsArrayList;
import static edu.bsu.cs222.finalProject.controllers.CartUIController.setTableProperties;

public class StoreUIController {

    @FXML
    public Label storeNameLabel;
    @FXML
    TableColumn<Button, Button> decrementColumn;
    @FXML
    TableColumn<Button, Button> incrementColumn;
    @FXML
    public TableView<Item> inventoryTable;
    @FXML
    TableColumn < Item, String > nameColumn;
    @FXML
    TableColumn<Item, Double> priceColumn;
    @FXML
    TableColumn<Item, String> quantityColumn;
    @FXML
    private AnchorPane rootPane;

    final ObservableList<Item> itemsToCart = FXCollections.observableArrayList();

    public void addItemToCart() {
        ObservableList<Item> items = inventoryTable.getItems();
        for (Item item : items) {
            if (item.getQuantity() != 0) {
                Item newItem = new Item(new Item(item.getName(), item.getPrice()), item.getQuantity());
                if (!itemsToCart.isEmpty())
                { verifyNoDuplicateCartItems(newItem); }
                else { itemsToCart.add(newItem); }}}
    }

    public void verifyNoDuplicateCartItems(Item item) {
        for ( int i = 0; i < itemsToCart.size(); i++ ) {
            Item cartItem = itemsToCart.get(i);
            if (cartItem.getName().equals(item.getName())) {
                cartItem.setQuantity(item.getQuantity() + cartItem.getQuantity());
                break;
            } else if (i == itemsToCart.size() - 1) {
                itemsToCart.add(item);
                break; }}
    }

    public void populateTableWithItems(String storeName) throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList(storeName));
        setTableProperties(nameColumn, priceColumn, quantityColumn, decrementColumn, incrementColumn);
        ObservableList<Item> items = FXCollections.observableArrayList(inventory.getItems());
        setMouseClickEvents(items);
        inventoryTable.setItems(items);
    }

    public void launchMainUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/mainUI.fxml"));
        Parent root = loader.load();
        MainUIController mainUIController = loader.getController();
        rootPane.getChildren().setAll(root);
        mainUIController.displayCartClearPrompt();
    }

    public void launchCartUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/cart.fxml"));
        Parent root = loader.load();
        CartUIController cartUIController = loader.getController();
        sendDataToCart(cartUIController);
        rootPane.getChildren().setAll(root);
    }

    public void sendDataToCart(CartUIController cartUIController) {
        for (Item item : itemsToCart) {
            cartUIController.cart.add(item);
        }
        cartUIController.costInDollars.setText(String.valueOf(cartUIController.cart.getTotalCost()));
        cartUIController.setMouseClickEvents(cartUIController.cart.getItems());
        cartUIController.storeName = storeNameLabel.getText();
    }

    private void setMouseClickEvents(ObservableList<Item> itemsList) {
        for (Item item : itemsList) {
            item.getDecButton().setOnMouseClicked(event -> {
                item.decreaseQuantity();
                inventoryTable.refresh();
            });
            item.getIncButton().setOnMouseClicked(event -> {
                item.increaseQuantity();
                inventoryTable.refresh();
            });
        }
    }
}