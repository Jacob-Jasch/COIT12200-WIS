package coit12200.wis.view;

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
        sceneCoordinator.setScene(SceneCoordinator.SceneKey.LOGIN);
    }

    @FXML
    private void clearAction(ActionEvent event) {
    }

    @FXML
    private void btnExit(ActionEvent event) {
        System.exit(0);
    }
}
