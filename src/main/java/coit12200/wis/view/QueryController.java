/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coit12200.wis.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 12273526
 */
public class QueryController implements Initializable {

    @FXML
    private Button btnClear;
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtDistillery;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtRegion;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrevious;
    @FXML
    private Button btnAllMalts;
    @FXML
    private Button btnMaltsFromRegion;
    @FXML
    private Button btnMaltsAgeRange;
    @FXML
    private TextArea txaMessages;
    @FXML
    private TextField txtMaltsRegion;
    @FXML
    private TextField txtMaltLowAge;
    @FXML
    private TextField txtMaltHighAge;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clearAction(ActionEvent event) {
        txtAge.clear();
        txtDistillery.clear();
        txtPrice.clear();
        txtMaltsRegion.clear();
        txtRegion.clear();
        txtMaltHighAge.clear();
        txtMaltLowAge.clear();
        txaMessages.clear();
    }

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void nextAction(ActionEvent event) {
        txaMessages.setText("Next: under development");
    }

    @FXML
    private void previousAction(ActionEvent event) {
        txaMessages.setText("Previous: under development");
    }

    @FXML
    private void displayAllMaltsAction(ActionEvent event) {
        txaMessages.setText("All malts: under development");
    }

    @FXML
    private void displayMaltsFromRegionAction(ActionEvent event) {
        txaMessages.setText("Malts in Region: under development");
    }

    @FXML
    private void displayMaltsInAgeRangeAction(ActionEvent event) {
        txaMessages.setText("Malts in age range: under development");
    }
    
}
