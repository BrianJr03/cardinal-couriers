package edu.bsu.cs222.finalProject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static edu.bsu.cs222.finalProject.Main.*;

public class ChooseStoreUIController
{

    @FXML
    private Label cartResetPrompt;
    @FXML
    private AnchorPane rootPane;

    private String storeName;

    public void initialize()
    { cartResetPrompt.setVisible( false ); }

    public void launchDeliveryUI() throws IOException
    { launchUI( "/ui/deliveryUI.fxml" ); }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll(root);
    }

    public void displayCartClearPrompt()
    { displayPromptFor3secs(cartResetPrompt); }

    public void launchStoreUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreUIController storeUIController = loader.getController();
        sendDataToStore(storeUIController);
        rootPane.getChildren().setAll(root);
    }

    public void sendDataToStore(StoreUIController storeUIController) throws IOException {
        storeUIController.storeNameLabel.setText(storeName);
        storeUIController.populateTableWithItems(storeName);
    }

    public void setWalmartLaunch() throws IOException {
        storeName = "Walmart";
        launchStoreUI();
    }

    public void setKrogerLaunch() throws IOException {
        storeName = "Kroger";
        launchStoreUI();
    }

    public void setALDILaunch() throws IOException {
        storeName = "ALDI";
        launchStoreUI();
    }
}