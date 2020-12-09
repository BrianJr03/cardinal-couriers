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

    String addressStored;
    String zipStored;
    String cityStored;
    String stateStored;

    public void launchMainUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/mainUI.fxml"));
        Parent root = loader.load();
        MainUIController mainUIController = loader.getController();
        sendDataToMain( mainUIController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToMain(MainUIController mainUIController) {
        mainUIController.setZipText( zipStored );
        mainUIController.setCityText( cityStored );
        mainUIController.setStateText( stateStored );
        mainUIController.setAddressText( addressStored );
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
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ObservableList<Item> items = FXCollections.observableArrayList(inventory.getItems());
        inventoryTable.setItems(items);
    }

    public void launchCartUI() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/cart.fxml"));
        Parent root = loader.load();
        CartUIController cartUIController = loader.getController();
        sendDataToCart( cartUIController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToCart(CartUIController cartUIController) {
        cartUIController.setStoreName( storeNameLBL.getText() );
        cartUIController.setZipText( zipStored );
        cartUIController.setCityText( cityStored );
        cartUIController.setAddressText( addressStored );
        cartUIController.setStateText( stateStored );
    }

    public void setStoreNameFromCart( String storeName )
    { storeNameLBL.setText( storeName ); }

    public void setCityText( String cityStored )
    { this.cityStored = cityStored; }

    public void setZipText( String zipStored )
    { this.zipStored = zipStored; }

    public void setAddressText( String addressStored )
    { this.addressStored = addressStored; }

    public void setStateText( String stateStored )
    { this.stateStored = stateStored; }
}