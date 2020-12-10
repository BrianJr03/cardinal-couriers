package edu.bsu.cs222.finalProject.controllers;

import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import static edu.bsu.cs222.finalProject.Inventory.collectItemsAsArrayList;

public class StoreUIController {

    @FXML
    public Label storeNameLBL;
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
    TableColumn<String, String> quantityColumn;
    @FXML
    private AnchorPane rootPane;

    final ObservableList<Item> itemsToCart = FXCollections.observableArrayList();

    public void addItemToCart() {
        ObservableList<Item> items = FXCollections.observableArrayList(inventoryTable.getItems());
        for (Item item : items) {
            if (item.getQuantity() != 0) {
                Item newItem = new Item(new Item(item.getName(), item.getPrice()), item.getQuantity());
                if (!itemsToCart.isEmpty())
                { verifyNoDuplicateCartItems(newItem); }
                else { itemsToCart.add(newItem); }
            }
        }
    }

    public void verifyNoDuplicateCartItems(Item item) {
        for ( int i = 0; i < itemsToCart.size(); i++ ) {
            Item cartItem = itemsToCart.get(i);
            if (cartItem.getName().equals(item.getName())) {
                cartItem.setQuantity(item.getQuantity() + cartItem.getQuantity());
                break;
            } else if (i == itemsToCart.size() - 1) {
                itemsToCart.add(item);
                break;
            }
        }
    }

    public void launchMainUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/mainUI.fxml"));
        Parent root = loader.load();
        MainUIController mainUIController = loader.getController();
        sendDataToMain(mainUIController);
        rootPane.getChildren().setAll(root);
    }

    public void sendDataToMain(MainUIController mainUIController)
    { mainUIController.displayPromptFor2secs(mainUIController.cartResetPrompt); }

    public void initialize() {
        storeNameLBL.setVisible(true);
        inventoryTable.setEditable(true);
    }

    public void showStoreName(String storeName)
    { storeNameLBL.setText(storeName); }

    public void populateTableWithItems(String storeName) throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList(storeName));
        inventoryTable.setEditable(true);
        nameColumn.setCellValueFactory( new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory( new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        decrementColumn.setCellValueFactory(new PropertyValueFactory<>("decButton"));
        incrementColumn.setCellValueFactory(new PropertyValueFactory<>("incButton"));
        ObservableList<Item> items = FXCollections.observableArrayList(inventory.getItems());
        setMouseClickEvents(items);
        inventoryTable.setItems(items);
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
        cartUIController.storeNameStored = storeNameLBL.getText();
    }

    public void setStoreNameFromCart(String storeName)
    { storeNameLBL.setText(storeName); }

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