package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.user.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnAccedi;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private Label lblLoginError;


    @FXML
    protected void onLogin()  {

        try {
            if(Account.checkUserPassword(txtUsername.getText(), pwfPassword.getText())) {
                btnAccedi.setBackground(Background.EMPTY);
                Account.login(txtUsername.getText());
                ((Stage) btnAccedi.getScene().getWindow()).close();
            }
            else
                lblLoginError.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pwfPassword.setOnKeyPressed( event -> {
            if(event.getCode() == KeyCode.ENTER) {
                onLogin();
            }
        } );
    }
}
