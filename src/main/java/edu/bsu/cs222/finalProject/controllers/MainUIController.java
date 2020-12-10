package edu.bsu.cs222.finalProject.controllers;

import edu.bsu.cs222.finalProject.StoreInfo;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class MainUIController {
    @FXML
    public Label cartResetPrompt;
    @FXML
    private AnchorPane rootPane;

    final StoreInfo storeInfo = new StoreInfo();

    public void initialize()
    { cartResetPrompt.setVisible( false ); }

    public void launchDeliveryUI() throws IOException
    { launchUI( "/ui/deliveryUI.fxml" ); }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll(root);
    }

    public void displayPromptFor2secs(Label prompt) {
        prompt.setVisible(true);
        PauseTransition visiblePause1 = new PauseTransition(Duration.seconds(2));
        visiblePause1.setOnFinished(event -> prompt.setVisible(false));
        visiblePause1.play();
    }

    @FXML
    private void launchStoreUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreUIController storeUIController = loader.getController();
        sendDataToStore(storeUIController);
        rootPane.getChildren().setAll(root);
    }

    public void sendDataToStore(StoreUIController storeUIController) throws IOException {
        storeUIController.showStoreName(storeInfo.getStoreName());
        storeUIController.populateTableWithItems(storeInfo.getStoreName());
    }

    @FXML
    private void sendData_Walmart() throws IOException {
        storeInfo.setStoreName("Walmart");
        launchStoreUI();
    }

    @FXML
    private void sendData_Kroger() throws IOException {
        storeInfo.setStoreName("Kroger");
        launchStoreUI();
    }

    @FXML
    private void sendData_ALDI() throws IOException {
        storeInfo.setStoreName("ALDI");
        launchStoreUI();
    }
}