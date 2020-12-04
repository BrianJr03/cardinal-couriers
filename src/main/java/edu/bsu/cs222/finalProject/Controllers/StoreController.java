package edu.bsu.cs222.finalProject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StoreController {

    @FXML
    public Label storeNameLBL;

    @FXML
    TableView<String> inventoryTable;

    @FXML
    private AnchorPane rootPane;

    public void launchMainUI() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource( "/ui/mainUI.fxml" ));
        rootPane.getChildren().setAll( pane );
    }

    public void initialize()
    { storeNameLBL.setVisible( true ); }

    public void showStoreName( String storeName )
    { storeNameLBL.setText( storeName ); }

    public void launchCartUI() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource( "/ui/cart.fxml" ));
        rootPane.getChildren().setAll( pane );
    }
}