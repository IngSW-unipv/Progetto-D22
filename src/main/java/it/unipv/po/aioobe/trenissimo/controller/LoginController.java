package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedAccountEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    protected void onLogin() {

        var account = CachedAccountEntity.getInstance().findAll();

        if (account.stream().anyMatch(x -> x.getUsername().equals(txtUsername.getText()))
            && account.stream().filter(x -> x.getUsername().equals(txtUsername.getText())).findFirst().get().getPassword().equals(pwfPassword.getText()))
                btnAccedi.setBackground(Background.EMPTY);
            else
                lblLoginError.setVisible(true);

    }

}
