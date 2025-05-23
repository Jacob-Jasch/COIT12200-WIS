/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coit12200.wis.view;

import java.net.URL;
import java.util.ResourceBundle;

import coit12200.wis.roles.WhiskeyDataValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import coit12200.wis.roles.WhiskeyDataManager;
import coit12200.wis.data.WhiskeyData;

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
    private WhiskeyDataManager dataManager;
    private WhiskeyData wd;
    private WhiskeyDataValidator validator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wd = new WhiskeyData();
        wd.connect();

        dataManager = new WhiskeyDataManager(wd);
        validator = new WhiskeyDataValidator();
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
        WhiskeyData.WhiskeyDetails nextRecord = dataManager.next();
        if (nextRecord != null) {
            txtDistillery.setText(nextRecord.distillery());
            txtAge.setText("" + nextRecord.age());
            txtRegion.setText(nextRecord.region());
            txtPrice.setText("" + nextRecord.price());
            txaMessages.setText("Next record displayed.");
        } else {
            txaMessages.setText("End of list.");
        }
    }

    @FXML
    private void previousAction(ActionEvent event) {
        WhiskeyData.WhiskeyDetails previousRecord = dataManager.previous();
        if (previousRecord != null) {
            txtDistillery.setText(previousRecord.distillery());
            txtAge.setText("" + previousRecord.age());
            txtRegion.setText(previousRecord.region());
            txtPrice.setText("" + previousRecord.price());
            txaMessages.setText("Previous record displayed.");
        } else {
            txaMessages.setText("End of list.");
        }
    }

    @FXML
    private void displayAllMaltsAction(ActionEvent event) {
        int count = dataManager.findAllMalts();

        if (count > 0) {
            WhiskeyData.WhiskeyDetails record = dataManager.first();

            txtDistillery.setText(record.distillery());
            txtAge.setText("" + record.age());
            txtRegion.setText(record.region());
            txtPrice.setText("" + record.price());

            txaMessages.setText("Found " + count + " malt whiskey records.");
        } else {
            txaMessages.setText("No malt whiskey records found.");
        }
    }

    @FXML
    private void displayMaltsFromRegionAction(ActionEvent event) {
        String region = txtMaltsRegion.getText();
        var result = validator.checkRegion(region);

        if (!result.result()) {
            txaMessages.setText("Invalid region: " + result.message());
            return;
        }

        int count = dataManager.findMaltsFromRegion(region);
        if (count > 0) {
            WhiskeyData.WhiskeyDetails record = dataManager.first();

            txtDistillery.setText(record.distillery());
            txtAge.setText("" + record.age());
            txtRegion.setText(record.region());
            txtPrice.setText("" + record.price());

            txaMessages.setText("Found " + count + " malts in region: " + region);
        } else {
            txaMessages.setText("No malts found in region: " + region);
        }    }

    @FXML
    private void displayMaltsInAgeRangeAction(ActionEvent event) {
        String lowStr = txtMaltLowAge.getText();
        String highStr = txtMaltHighAge.getText();

        var result = validator.checkAgeRange(lowStr, highStr);

        if (!result.result()) {
            txaMessages.setText("Invalid age range: " + result.message());
            return;
        }

        int count = dataManager.findMaltsInAgeRange(result.r().lower(), result.r().upper());
        if (count > 0) {
            WhiskeyData.WhiskeyDetails record = dataManager.first();

            txtDistillery.setText(record.distillery());
            txtAge.setText("" + record.age());
            txtRegion.setText(record.region());
            txtPrice.setText("" + record.price());

            txaMessages.setText("Found " + count + " malts in age range.");
        } else {
            txaMessages.setText("No malts found in that age range.");
        }    }
    
}
