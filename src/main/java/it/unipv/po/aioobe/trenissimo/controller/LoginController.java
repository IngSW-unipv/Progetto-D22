package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;
import it.unipv.po.aioobe.trenissimo.model.user.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;

import java.net.URL;
import java.util.ResourceBundle;


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
    protected void onLogin() {

        if(Login.checkUserPassword(txtUsername.getText(), pwfPassword.getText()))
                btnAccedi.setBackground(Background.EMPTY);
            else
                lblLoginError.setVisible(true);
    }

}
