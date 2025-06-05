package coit12200.wis.view;

import coit12200.wis.data.UserData;
import coit12200.wis.roles.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * LoginController handles the user login process.
 * It validates user credentials and manages the transition to the main application scene.
 * @author Jacob Duckworth
 */
public class LoginController {

    /** TextField for user input of username.*/
    @FXML
    private TextField txtUserName;
    /** TextField for user input of password.*/
    @FXML
    private PasswordField txtPassword;
    /** Button to trigger the login action.*/
    @FXML
    private Button btnLogin;
    /** Button to trigger the change password action.*/
    @FXML
    private Button btnChangePassword;
    /** Button to clear the input fields and messages.*/
    @FXML
    private Button btnClear;
    /** Button to exit the application.*/
    @FXML
    private Button btnExit;
    /** TextArea to display messages to the user, such as validation errors or login status.*/
    @FXML
    private TextArea txaMessages;
    private SceneCoordinator sceneCoordinator;
    private UserDataManager dataManager;
    private UserDataValidator validator;

    /**
     * Injects dependencies into the LoginController.
     * @param sc the SceneCoordinator to manage scene transitions
     * @param udm the UserDataManager to handle user data operations
     * @param udv the UserDataValidator to validate user credentials
     */
    public void inject(SceneCoordinator sc, UserDataManager udm, UserDataValidator udv) {
        this.sceneCoordinator = sc;
        this.dataManager = udm;
        this.validator = udv;
    }

    /**
     * Handles the login action when the user clicks the login button. 
     * It validates the input fields, checks the user credentials, and transitions to the main application scene if successful.
     * If validation fails, it displays an error message in the TextArea.
     * @param event the ActionEvent triggered by the login button click
     */
    @FXML
    private void loginAction(ActionEvent event) {
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        ValidationResponse fieldCheck = validator.checkForFieldsPresent(username, password);
        if (!fieldCheck.result()) {
            txaMessages.setText(fieldCheck.message());
            return;
        }
        UserData.UserDetails userDetails = dataManager.findUser(username);
        ValidationResponse loginCheck = validator.checkCurrentDetails(userDetails, username, password);
        if (!loginCheck.result()) {
            txaMessages.setText(loginCheck.message());
            if (loginCheck.message().contains("default password")) {
                sceneCoordinator.setScene(SceneCoordinator.SceneKey.PASSWORD);
            }
            return;
        }
        sceneCoordinator.setScene(SceneCoordinator.SceneKey.QUERY);
    }

    /**
     * Handles the action to change the user's password.
     * It transitions the user to the password change scene.
     * @param event the ActionEvent triggered by the change password button click
     */
    @FXML
    private void changePasswordAction(ActionEvent event) {
        sceneCoordinator.setScene(SceneCoordinator.SceneKey.PASSWORD);
    }

    /**
     * Clears the input fields and the messages TextArea.
     * It resets the username, password fields, and the messages area to empty.
     * @param event the ActionEvent triggered by the clear button click
     */
    @FXML
    private void clearAction(ActionEvent event) {
        txtUserName.clear();
        txaMessages.clear();
        txtPassword.clear();
    }

    /**
     * Exits the application when the exit button is clicked.
     * @param event the ActionEvent triggered by the exit button click
     */
    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }
}
