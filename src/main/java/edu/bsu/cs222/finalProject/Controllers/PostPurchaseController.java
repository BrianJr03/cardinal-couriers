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

    public void initialize()
    {
        receiptSent.setVisible( false );
    }

    public void sendReceipt()
    {}

    public void closeProgram()
    { System.exit( 0 ); }

    public void launchMainUI() throws IOException
    { launchUI( "/ui/mainUI.fxml" ); }

    public void launchUI(String uiPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uiPath));
        Parent root = loader.load();
        rootPane.getChildren().setAll( root );
    }
}
