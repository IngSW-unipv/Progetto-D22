package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.user.Login;
import it.unipv.po.aioobe.trenissimo.view.AccountSettings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;

import java.io.IOException;


public class LoginController{

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnAccedi;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private Label lblLoginError;


    @FXML
    protected void onLogin() throws IOException {

        if(Login.getInstance().checkUserPassword(txtUsername.getText(), pwfPassword.getText())) {
            btnAccedi.setBackground(Background.EMPTY);
            Login.getInstance().login(txtUsername.getText());
            AccountSettings.open();
        }
        else
            lblLoginError.setVisible(true);
    }

}
