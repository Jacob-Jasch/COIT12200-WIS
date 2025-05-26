package coit12200.wis.view;

import coit12200.wis.data.UserData.UserDetails;
import coit12200.wis.data.UserData;
import coit12200.wis.roles.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PasswordController {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtOldPassword;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnExit;
    @FXML
    private TextArea txaMessages;

    private SceneCoordinator sceneCoordinator;
    private UserDataManager dataManager;
    private UserDataValidator validator;

    public void inject(SceneCoordinator sc, UserDataManager udm, UserDataValidator udv) {
        this.sceneCoordinator = sc;
        this.dataManager = udm;
        this.validator = udv;
    }

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

    @FXML
    private void clearAction(ActionEvent event) {
        txaMessages.clear();
        txtNewPassword.clear();
        txtOldPassword.clear();
        txtUserName.clear();
    }

    @FXML
    private void btnExit(ActionEvent event) {
        System.exit(0);
    }
}
