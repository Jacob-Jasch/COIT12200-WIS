/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coit12200.wis.view;

import java.net.URL;
import java.util.ResourceBundle;

import coit12200.wis.roles.SceneCoordinator;
import coit12200.wis.roles.WhiskeyDataValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import coit12200.wis.roles.WhiskeyDataManager;
import coit12200.wis.data.WhiskeyData;
import coit12200.wis.data.WhiskeyData.WhiskeyDetails;

/**
 * QueryController handles the user interface for querying whiskey data.
 * It provides methods to clear input fields, exit the application, navigate
 * through records,
 * and display whiskey records based on various criteria such as region and age
 * range.
 * 
 * @author Jacob Duckworth
 */
public class QueryController implements Initializable {

    /** Button to clear the input fields and messages. */
    @FXML
    private Button btnClear;
    /** Button to exit the application. */
    @FXML
    private Button btnExit;
    /** TextField for user input of distillery name. */
    @FXML
    private TextField txtDistillery;
    /** TextField for user input of whiskey age. */
    @FXML
    private TextField txtAge;
    /** TextField for user input of whiskey region. */
    @FXML
    private TextField txtRegion;
    /** TextField for user input of whiskey price. */
    @FXML
    private TextField txtPrice;
    /** Button to navigate to the next whiskey record. */
    @FXML
    private Button btnNext;
    /** Button to navigate to the previous whiskey record. */
    @FXML
    private Button btnPrevious;
    /** Button to display all malt whiskey records. */
    @FXML
    private Button btnAllMalts;
    /** Button to display malt whiskey records from a specific region. */
    @FXML
    private Button btnMaltsFromRegion;
    /** Button to display malt whiskey records within a specific age range. */
    @FXML
    private Button btnMaltsAgeRange;
    /**
     * TextArea to display messages to the user, such as validation errors or status
     * updates.
     */
    @FXML
    private TextArea txaMessages;
    /** TextField for user input of region to filter malt whiskeys. */
    @FXML
    private TextField txtMaltsRegion;
    /** TextField for user input of lower age limit for malt whiskeys. */
    @FXML
    private TextField txtMaltLowAge;
    /** TextField for user input of upper age limit for malt whiskeys. */
    @FXML
    private TextField txtMaltHighAge;
    private WhiskeyDataManager dataManager;
    private WhiskeyData wd;
    private WhiskeyDataValidator validator;
    private SceneCoordinator sceneCoordinator;

    /**
     * Injects dependencies into the QueryController.
     * @param sc  the SceneCoordinator to manage scene transitions
     * @param wdm the WhiskeyDataManager to handle whiskey data operations
     * @param wdv the WhiskeyDataValidator to validate whiskey data
     */
    public void inject(SceneCoordinator sc, WhiskeyDataManager wdm, WhiskeyDataValidator wdv) {
        this.sceneCoordinator = sc;
        this.dataManager = wdm;
        this.validator = wdv;
    }

    /**
     * Initializes the controller by connecting to the whiskey data source.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wd = new WhiskeyData();
        wd.connect();

        dataManager = new WhiskeyDataManager(wd);
        validator = new WhiskeyDataValidator();
    }

    /**
     * Clears the input fields and messages displayed in the TextArea.
     * This method is called when the user clicks the clear button.
     * 
     * @param event the ActionEvent triggered by the clear button click
     */
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

    /**
     * Exits the application when the user clicks the exit button.
     * This method is called when the user clicks the exit button.
     * 
     * @param event the ActionEvent triggered by the exit button click
     */
    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Navigates to the next whiskey record in the list.
     * If there are no more records, it displays a message indicating the end of the
     * list.
     * 
     * @param event the ActionEvent triggered by the next button click
     */
    @FXML
    private void nextAction(ActionEvent event) {
        WhiskeyDetails nextRecord = dataManager.next();
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

    /**
     * Navigates to the previous whiskey record in the list.
     * If there are no previous records, it displays a message indicating the end of
     * the list.
     * 
     * @param event the ActionEvent triggered by the previous button click
     */
    @FXML
    private void previousAction(ActionEvent event) {
        WhiskeyDetails previousRecord = dataManager.previous();
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

    /**
     * Displays all malt whiskey records.
     * It retrieves all malt records from the data manager and displays the first
     * record's details.
     * 
     * @param event the ActionEvent triggered by the display all malts button click
     */
    @FXML
    private void displayAllMaltsAction(ActionEvent event) {
        int count = dataManager.findAllMalts();

        if (count > 0) {
            WhiskeyDetails record = dataManager.first();

            txtDistillery.setText(record.distillery());
            txtAge.setText("" + record.age());
            txtRegion.setText(record.region());
            txtPrice.setText("" + record.price());

            txaMessages.setText("Found " + count + " malt whiskey records.");
        } else {
            txaMessages.setText("No malt whiskey records found.");
        }
    }

    /**
     * Displays malt whiskey records from a specific region.
     * It validates the region input and retrieves records from that region.
     * 
     * @param event the ActionEvent triggered by the display malts from region button click
     */
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
            WhiskeyDetails record = dataManager.first();

            txtDistillery.setText(record.distillery());
            txtAge.setText("" + record.age());
            txtRegion.setText(record.region());
            txtPrice.setText("" + record.price());

            txaMessages.setText("Found " + count + " malts in region: " + region);
        } else {
            txaMessages.setText("No malts found in region: " + region);
        }
    }

    /**
     * Displays malt whiskey records within a specified age range.
     * It validates the age range input and retrieves records within that range.
     * 
     * @param event the ActionEvent triggered by the display malts in age range button click
     */
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
            WhiskeyDetails record = dataManager.first();

            txtDistillery.setText(record.distillery());
            txtAge.setText("" + record.age());
            txtRegion.setText(record.region());
            txtPrice.setText("" + record.price());

            txaMessages.setText("Found " + count + " malts in age range.");
        } else {
            txaMessages.setText("No malts found in that age range.");
        }
    }

}
