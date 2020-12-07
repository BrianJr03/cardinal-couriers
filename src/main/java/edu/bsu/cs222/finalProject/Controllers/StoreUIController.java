package edu.bsu.cs222.finalProject.Controllers;

import edu.bsu.cs222.finalProject.Inventory;
import edu.bsu.cs222.finalProject.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static edu.bsu.cs222.finalProject.Inventory.collectItemsAsArrayList;

public class StoreUIController {
    @FXML
    public Label storeNameLBL;

    @FXML
    TableView<Item> inventoryTable;

    @FXML
    TableColumn < Item, String > nameColumn;

    @FXML
    TableColumn<Item, Double> priceColumn;

    @FXML
    TableColumn<String, String> quantityColumn;

    @FXML
    private AnchorPane rootPane;

    public void launchMainUI() throws IOException
    { launchUI( "/ui/mainUI.fxml" ); }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    public void initialize()
    { storeNameLBL.setVisible( true ); }

    public void showStoreName( String storeName )
    { storeNameLBL.setText( storeName ); }

    public void populateTableWithItems(String storeName) throws IOException {
        Inventory inventory = new Inventory(collectItemsAsArrayList(storeName));
        inventoryTable.setEditable(true);
        nameColumn.setCellValueFactory( new PropertyValueFactory <>( "name" ));
        priceColumn.setCellValueFactory( new PropertyValueFactory <>( "price" ));
        ObservableList<Item> items = FXCollections.observableArrayList(inventory.getItems());
        inventoryTable.setItems(items);
    }

    public void launchCartUI() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/cart.fxml"));
        Parent root = loader.load();
        CartUIController cart = loader.getController();

        //send data here
        sendDataToCart( cart );

        rootPane.getChildren().setAll( root );
    }

    public void sendDataToCart(CartUIController cart) {
        cart.setStoreName( storeNameLBL.getText() );
    }

    public void setStoreNameFromCart( String storeName)
    { storeNameLBL.setText( storeName ); }
}