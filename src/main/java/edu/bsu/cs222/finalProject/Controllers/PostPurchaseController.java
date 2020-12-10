package edu.bsu.cs222.finalProject.Controllers;

import edu.bsu.cs222.finalProject.Cart;
import edu.bsu.cs222.finalProject.SendReceipt;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.mail.MessagingException;
import java.io.IOException;

public class PostPurchaseController {

    @FXML
    public ComboBox<String> carrierComboBox;
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

    public Cart cart = new Cart( FXCollections.observableArrayList() );
    public ObservableList<String> carrierOptions = FXCollections.observableArrayList();

    public void initialize() {
        receiptSent.setVisible( false );
        carrierOptions.add( "AT&T" );
        carrierOptions.add( "Sprint" );
        carrierOptions.add( "T-Mobile" );
        carrierOptions.add( "Verizon" );
        carrierComboBox.setItems( carrierOptions );
    }

    public void sendReceipt() throws MessagingException, IOException {
        SendReceipt sendReceipt = new SendReceipt();
        if (emailCheckBox.isSelected())
        { sendReceipt.sendReceiptAsEmail( SendReceipt.isValidEmail(email.getText()), cart );}
        if(textCheckBox.isSelected()) {
            if (carrierComboBox.getSelectionModel().isSelected( 0 ))
            { sendReceipt.sendReceiptAsTextMSG( SendReceipt.isValidPhoneNumber(txtMSG.getText()), cart, "AT&T"); }

            if (carrierComboBox.getSelectionModel().isSelected( 1 ))
            { sendReceipt.sendReceiptAsTextMSG( SendReceipt.isValidPhoneNumber(txtMSG.getText()), cart, "Sprint"); }

            if (carrierComboBox.getSelectionModel().isSelected( 2 ))
            { sendReceipt.sendReceiptAsTextMSG( SendReceipt.isValidPhoneNumber(txtMSG.getText()), cart, "T-Mobile"); }

            if (carrierComboBox.getSelectionModel().isSelected( 3 ))
            { sendReceipt.sendReceiptAsTextMSG( SendReceipt.isValidPhoneNumber(txtMSG.getText()), cart, "Verizon"); }
        }
        displayPromptFor4secs( receiptSent );
    }

    public void displayPromptFor4secs( Label prompt) {
        prompt.setVisible( true );
        PauseTransition visiblePause1 = new PauseTransition( Duration.seconds(4));
        visiblePause1.setOnFinished( event -> prompt.setVisible(false) );
        visiblePause1.play();
    }

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
