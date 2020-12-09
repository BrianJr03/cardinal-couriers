package edu.bsu.cs222.finalProject.Controllers;

import edu.bsu.cs222.finalProject.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CartUIController {

    @FXML
    TableColumn< Button, Button> decrementColumn;
    @FXML
    TableColumn<Button, Button> incrementColumn;
    @FXML
    public TableView< Item > cartTable;
    @FXML
    TableColumn < Item, String > nameColumn;
    @FXML
    TableColumn<Item, Double> priceColumn;
    @FXML
    TableColumn<String, String> quantityColumn;
    @FXML
    private AnchorPane rootPane;

    String storeNameStored;
    String addressStored;
    String zipStored;
    String cityStored;
    String stateStored;

    ObservableList<Item> itemsInCart = FXCollections.observableArrayList();

    public void initialize() {
        cartTable.setEditable(true);
        nameColumn.setCellValueFactory( new PropertyValueFactory <>( "name" ));
        priceColumn.setCellValueFactory( new PropertyValueFactory<>( "price" ));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        decrementColumn.setCellValueFactory(new PropertyValueFactory<>("decButton"));
        incrementColumn.setCellValueFactory(new PropertyValueFactory<>("incButton"));
        cartTable.setItems( itemsInCart );
    }

    public void launchOrderConfirmUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/orderConfirm.fxml"));
        Parent root = loader.load();
        OrderConfirmController orderConfirmController = loader.getController();
        sendDataToOrderConfirm( orderConfirmController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToOrderConfirm(OrderConfirmController orderConfirmController) {
        orderConfirmController.setStoreName( storeNameStored );
        orderConfirmController.setZipText( zipStored );
        orderConfirmController.setCityText( cityStored );
        orderConfirmController.setStateText( stateStored );
        orderConfirmController.setAddressText( addressStored );
        orderConfirmController.stAddress.setText( addressStored );
        orderConfirmController. zip.setText( zipStored );
        orderConfirmController.city.setText( cityStored );
        orderConfirmController.state.setText( stateStored );
        orderConfirmController.storeName.setText( storeNameStored );
    }

    public void launchStoreUI() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreUIController store = loader.getController();
        sendDataToStore( store );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToStore(StoreUIController store) throws IOException {
        store.itemsStoredInCart.addAll( itemsInCart );
        store.setStoreNameFromCart( storeNameStored );
        store.populateTableWithItems( storeNameStored );
        store.setZipText( zipStored );
        store.setCityText( cityStored );
        store.setStateText( stateStored );
        store.setAddressText( addressStored );
    }

    public void setItemsInCart(ObservableList<Item> items)
    {this.itemsInCart = items;}

    public void setStoreName(String storeName)
    { this.storeNameStored = storeName; }

    public void setCityText( String cityStored )
    { this.cityStored = cityStored; }

    public void setZipText( String zipStored )
    { this.zipStored = zipStored; }

    public void setAddressText( String addressStored )
    { this.addressStored = addressStored; }

    public void setStateText( String stateStored )
    { this.stateStored = stateStored; }
}