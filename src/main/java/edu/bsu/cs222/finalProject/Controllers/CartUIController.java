package edu.bsu.cs222.finalProject.Controllers;

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
    public Label cartTotal;
    @FXML
    public Label costInDollars;
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

    ObservableList<Item> itemsInCart = FXCollections.observableArrayList();
    private String storeNameStored;

    public void initialize() {
        cartTotal.setVisible(true);
        costInDollars.setVisible(true);
        cartTable.setEditable(true);
        nameColumn.setCellValueFactory( new PropertyValueFactory <>( "name" ));
        priceColumn.setCellValueFactory( new PropertyValueFactory<>( "price" ));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        decrementColumn.setCellValueFactory(new PropertyValueFactory<>("decButton"));
        incrementColumn.setCellValueFactory(new PropertyValueFactory<>("incButton"));
        cartTable.setItems( itemsInCart );
        System.out.println( this.itemsInCart );
    }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }

    public void launchOrderConfirmUI() throws IOException
    { launchUI( "/ui/orderConfirm.fxml" ); }

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
    }

    public void setStoreName(String storeName)
    { this.storeNameStored = storeName; }
}