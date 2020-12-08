package edu.bsu.cs222.finalProject.Controllers;

import edu.bsu.cs222.finalProject.StoreInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainUIController {
    @FXML
    private AnchorPane rootPane;

    String addressStored;
    String zipStored;
    String cityStored;
    String stateStored;

    StoreInfo storeInfo = new StoreInfo();

    @FXML
    public void launchDeliveryUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/deliveryUI.fxml"));
        Parent root = loader.load();
        DeliveryUIController deliveryUIController = loader.getController();
        sendDataToDelivery( deliveryUIController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToDelivery(DeliveryUIController deliveryUIController) {
        deliveryUIController.zipCode.setText( zipStored );
        deliveryUIController.city.setText( cityStored );
        deliveryUIController.state.setText( stateStored );
        deliveryUIController.addressOne.setText( addressStored );
    }

    @FXML
    private void launchStoreUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/storeUI.fxml"));
        Parent root = loader.load();
        StoreUIController storeUIController = loader.getController();
        sendDataToStore( storeUIController );
        rootPane.getChildren().setAll( root );
    }

    public void sendDataToStore(StoreUIController storeUIController) throws IOException {
        storeUIController.showStoreName(storeInfo.getStoreName());
        storeUIController.populateTableWithItems(storeInfo.getStoreName());
        storeUIController.setCityText( cityStored );
        storeUIController.setAddressText( addressStored );
        storeUIController.setZipText( zipStored );
        storeUIController.setStateText( stateStored );
    }

    @FXML
    private void sendData_Walmart() throws IOException {
        storeInfo.setStoreName( "Walmart" );
        launchStoreUI();
    }

    @FXML
    private void sendData_Kroger() throws IOException {
        storeInfo.setStoreName( "Kroger" );
        launchStoreUI();
    }

    @FXML
    private void sendData_ALDI() throws IOException {
        storeInfo.setStoreName( "ALDI" );
        launchStoreUI();
    }

    public void setCityText( String cityStored )
    { this.cityStored = cityStored; }

    public void setZipText( String zipStored )
    { this.zipStored = zipStored; }

    public void setAddressText( String addressStored )
    { this.addressStored = addressStored; }

    public void setStateText( String stateStored )
    { this.stateStored = stateStored; }
}