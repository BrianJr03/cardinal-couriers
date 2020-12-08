package edu.bsu.cs222.finalProject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PostPurchaseController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    public CheckBox emailCheckBox;
    @FXML
    public CheckBox textCheckBox;
    @FXML
    public TextField email;
    @FXML
    public TextField txtMSG;
    @FXML
    public Label receiptSent;

    String addressStored;
    String zipStored;
    String cityStored;
    String stateStored;

    public void initialize()
    {
        receiptSent.setVisible( false );
    }

    public void sendReceipt()
    {}

    public void closeProgram()
    { System.exit( 0 ); }

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

    public void setCityText( String cityStored )
    { this.cityStored = cityStored; }

    public void setZipText( String zipStored )
    { this.zipStored = zipStored; }

    public void setAddressText( String addressStored )
    { this.addressStored = addressStored; }

    public void setStateText( String stateStored )
    { this.stateStored = stateStored; }
}
