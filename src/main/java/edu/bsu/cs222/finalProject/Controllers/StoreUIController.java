package edu.bsu.cs222.finalProject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StoreUIController
{

    @FXML
    public Label storeNameLBL;

    @FXML
    TableView<String> inventoryTable;

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

    public void launchCartUI() throws IOException
    { launchUI( "/ui/cart.fxml" ); }
}