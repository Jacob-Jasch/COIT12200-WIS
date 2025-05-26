package coit12200.wis.view;

import coit12200.wis.data.UserData;
import coit12200.wis.roles.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnChangePassword;
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

    @FXML
    private void changePasswordAction(ActionEvent event) {
        sceneCoordinator.setScene(SceneCoordinator.SceneKey.PASSWORD);
    }

    @FXML
    private void clearAction(ActionEvent event) {
        txtUserName.clear();
        txaMessages.clear();
        txtPassword.clear();
    }

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }
}
