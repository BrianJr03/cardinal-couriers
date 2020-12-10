package edu.bsu.cs222.finalProject.controllers;

import edu.bsu.cs222.finalProject.Cart;
import edu.bsu.cs222.finalProject.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CartUIController {

    @FXML
    private TableColumn<Button, Button> decrementColumn;
    @FXML
    private TableColumn<Button, Button> incrementColumn;
    @FXML
    private TableView<Item> cartTable;
    @FXML
    private TableColumn <Item, String> nameColumn;
    @FXML
    private TableColumn<Item, Double> priceColumn;
    @FXML
    private TableColumn<Item, String> quantityColumn;
    @FXML
    private AnchorPane rootPane;

    public Label costInDollars;
    public final Cart cart = new Cart(FXCollections.observableArrayList());
    public String storeNameStored;

    public void initialize() {
        costInDollars.setVisible(true);
        cartTable.setEditable(true);
        setTableProperties(cartTable, nameColumn, priceColumn, quantityColumn, decrementColumn, incrementColumn);
        cartTable.setItems( cart.getItems() );
        setMouseClickEvents( cart.getItems() );
    }

    public static void setTableProperties(TableView<Item> tableView, TableColumn<Item, String> nameColumn, TableColumn<Item, Double> priceColumn,
                                     TableColumn<Item, String> quantityColumn, TableColumn<Button, Button> decrementColumn, TableColumn<Button, Button> incrementColumn) {
        nameColumn.setCellValueFactory( new PropertyValueFactory <>( "name" ));
        priceColumn.setCellValueFactory( new PropertyValueFactory<>( "price" ));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        decrementColumn.setCellValueFactory(new PropertyValueFactory<>("decButton"));
        incrementColumn.setCellValueFactory(new PropertyValueFactory<>("incButton"));
    }

    public void launchOrderConfirmUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/orderConfirm.fxml"));
        Parent root = loader.load();
        OrderConfirmController orderConfirm = loader.getController();
        sendDataToOrderConfirm(orderConfirm);
        rootPane.getChildren().setAll(root);
    }

    private void sendDataToOrderConfirm(OrderConfirmController orderConfirm) {
        for (Item item : cart.getItems()) {
            orderConfirm.cart.add(item);
        }
        orderConfirm.orderConfirmTable.setItems( cart.getItems() );
        orderConfirm.costInDollars.setText(String.valueOf(orderConfirm.cart.getTotalCost()));
        orderConfirm.storeName.setText(storeNameStored);
        orderConfirm.storeNameStored = storeNameStored;
    }

    public void launchStoreUI() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreUIController store = loader.getController();
        sendDataToStore( store );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToStore(StoreUIController store) throws IOException {
        store.itemsToCart.addAll( cart.getItems() );
        store.setStoreNameFromCart( storeNameStored );
        store.populateTableWithItems( storeNameStored );
    }

    public void setMouseClickEvents(ObservableList<Item> itemsList) {
        for (Item item : itemsList) {
            item.getDecButton().setOnMouseClicked(event -> {
                item.decreaseQuantity();
                if (item.getQuantity() == 0) {
                    cartTable.getItems().remove(item);
                }
                cartTable.refresh();
                costInDollars.setText(String.valueOf(cart.getTotalCost()));
            });
            item.getIncButton().setOnMouseClicked(event -> {
                item.increaseQuantity();
                cartTable.refresh();
                costInDollars.setText(String.valueOf(cart.getTotalCost()));
            });
        }
    }

    public void setStoreName(String storeName)
    { this.storeNameStored = storeName; }
}