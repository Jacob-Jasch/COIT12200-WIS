package coit12200.wis.view;

import coit12200.wis.data.UserData.UserDetails;
import coit12200.wis.roles.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * PasswordController handles the user password change process.
 * It validates the old password and updates it to a new password if valid.
 * @author Jacob Duckworth
 */
public class PasswordController {

    /** TextField for user input of username.*/
    @FXML
    private TextField txtUserName;
    /** TextField for user input of old password.*/
    @FXML
    private PasswordField txtOldPassword;
    /** TextField for user input of new password.*/
    @FXML
    private PasswordField txtNewPassword;
    /** Button to trigger the password change action.*/
    @FXML
    private Button btnSubmit;
    /** Button to clear the input fields and messages.*/
    @FXML
    private Button btnClear;
    /** Button to exit the application.*/
    @FXML
    private Button btnExit;
    /** TextArea to display messages to the user, such as validation errors or status updates.*/
    @FXML
    private TextArea txaMessages;
    private SceneCoordinator sceneCoordinator;
    private UserDataManager dataManager;
    private UserDataValidator validator;

    /**
     * Injects dependencies into the PasswordController.
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
     * Handles the password change action when the user clicks the submit button.
     * It validates the input fields, checks the old password, and updates it to the new password if valid.
     * @param event the ActionEvent triggered by the submit button click
     */
    @FXML
    private void submitAction(ActionEvent event) {
        String username = txtUserName.getText();
        String oldPassword = txtOldPassword.getText();
        String newPassword = txtNewPassword.getText();

        UserDetails userDetails = dataManager.findUser(username);
        ValidationResponse response = validator.checkNewDetails(userDetails, username, oldPassword, newPassword);

        if (response.result()) {
            String encryptedPassword = UserDataValidator.generateSHA1(newPassword);
            int rowsAffected = dataManager.updatePassword(username, encryptedPassword);

            if (rowsAffected > 0) {
                txaMessages.setText("Password updated successfully. Please log in.");
                sceneCoordinator.setScene(SceneCoordinator.SceneKey.LOGIN);
            } else {
                txaMessages.setText("Failed to update password.");
            }
        } else {
            txaMessages.setText(response.message());
        }
    }

    /**
     * Clears the input fields and messages in the TextArea.
     * @param event the ActionEvent triggered by the clear button click
     */
    @FXML
    private void clearAction(ActionEvent event) {
        txaMessages.clear();
        txtNewPassword.clear();
        txtOldPassword.clear();
        txtUserName.clear();
    }

    /**
     * Exits the application when the exit button is clicked.
     * @param event the ActionEvent triggered by the exit button click
     */
    @FXML
    private void btnExit(ActionEvent event) {
        System.exit(0);
    }
}
