package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.user.Account;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrazioneController implements Initializable {

    @FXML private Button btnAnnulla;
    @FXML private Button btnRegistrati;

    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    @FXML private TextField txtNome;
    @FXML private TextField txtCognome;
    @FXML private DatePicker dtpDataNascita;
    @FXML private TextField txtEmail;
    @FXML private TextField txtVia;
    @FXML private TextField txtCivico;
    @FXML private TextField txtCitta;
    @FXML private TextField txtCAP;

    @FXML private Label lblRegistrazione;
    @FXML private Label lblErroreUsername;
    @FXML private Label lblErrorePassword;
    @FXML private Label lblErroreNome;
    @FXML private Label lblErroreCognome;
    @FXML private Label lblErroreDataNascita;
    @FXML private Label lblErroreDataNascitaEmpty;
    @FXML private Label lblErroreEmail;
    @FXML private Label lblErroreEmailEmpty;
    @FXML private Label lblErroreVia;
    @FXML private Label lblErroreCivico;
    @FXML private Label lblErroreCitta;
    @FXML private Label lblErroreCAP;
    @FXML private Label lblErroreCAPEmpty;

    @FXML private Label lblRegistrazioneOK;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            onCheck();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //todo aggiungere thread per caricamento dati personali in label
    }

    @FXML
    protected void onCheck() throws IOException {
        txtUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtUsername.getText())) {
                lblErroreUsername.setVisible(false);
                btnRegistrati.setDisable(false);
                txtUsername.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreUsername.setVisible(true);
                txtUsername.setStyle("-fx-border-color: #d70000");
            }
        });
        txtPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtPassword.getText())) {
                lblErrorePassword.setVisible(false);
                btnRegistrati.setDisable(false);
                txtPassword.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErrorePassword.setVisible(true);
                txtPassword.setStyle("-fx-border-color: #d70000");
            }
        });
        txtCAP.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkCAP(txtCAP.getText())) {
                lblErroreCAPEmpty.setVisible(false);
                lblErroreCAP.setVisible(false);
                btnRegistrati.setDisable(false);
                txtCAP.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCAPEmpty.setVisible(false);
                lblErroreCAP.setVisible(true);
                txtCAP.setStyle("-fx-border-color: #d70000");
            }
        });

        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkEmail(txtEmail.getText())) {
                lblErroreEmailEmpty.setVisible(false);
                lblErroreEmail.setVisible(false);
                btnRegistrati.setDisable(false);
                txtEmail.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreEmailEmpty.setVisible(false);
                lblErroreEmail.setVisible(true);
                txtEmail.setStyle("-fx-border-color: #d70000");
            }
        });

        dtpDataNascita.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (Account.getInstance().checkDataNascita(dtpDataNascita.getValue()))
            {
                lblErroreDataNascitaEmpty.setVisible(false);
                lblErroreDataNascita.setVisible(false);
                btnRegistrati.setDisable(false);
                dtpDataNascita.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreDataNascitaEmpty.setVisible(false);
                lblErroreDataNascita.setVisible(true);
                dtpDataNascita.setStyle("-fx-border-color: #d70000");
            }
        });

        txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtNome.getText())) {
                lblErroreNome.setVisible(false);
                btnRegistrati.setDisable(false);
                txtNome.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreNome.setVisible(true);
                txtNome.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCognome.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtCognome.getText())) {
                lblErroreCognome.setVisible(false);
                btnRegistrati.setDisable(false);
                txtCognome.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCognome.setVisible(true);
                txtCognome.setStyle("-fx-border-color: #d70000");
            }
        });

        txtVia.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtVia.getText())) {
                lblErroreVia.setVisible(false);
                btnRegistrati.setDisable(false);
                txtVia.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreVia.setVisible(true);
                txtVia.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCivico.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtCivico.getText())) {
                lblErroreCivico.setVisible(false);
                btnRegistrati.setDisable(false);
                txtCivico.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCivico.setVisible(true);
                txtCivico.setStyle("-fx-border-color: #d70000");
            }
        });
        txtCitta.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtCitta.getText())) {
                lblErroreCitta.setVisible(false);
                btnRegistrati.setDisable(false);
                txtCognome.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCitta.setVisible(true);
                txtCitta.setStyle("-fx-border-color: #d70000");
            }
        });

        btnRegistrati.setOnMouseMoved(c -> {
        if (lblErroreCAP.isVisible() || lblErroreEmail.isVisible() || lblErroreDataNascita.isVisible()
            || lblErroreNome.isVisible() || lblErroreCognome.isVisible() || lblErroreVia.isVisible() ||
            lblErroreCivico.isVisible() || lblErroreCitta.isVisible() || lblErrorePassword.isVisible()
            || lblErroreUsername.isVisible())
            btnRegistrati.setDisable(true);
    });

    }

    private void onCheckEmpty(){
        if (txtUsername.getText().isEmpty())
        {
            lblErroreUsername.setVisible(true);
            txtUsername.setStyle("-fx-border-color: #d70000");
        }
    if (txtPassword.getText().isEmpty())
        {
            lblErrorePassword.setVisible(true);
            txtPassword.setStyle("-fx-border-color: #d70000");
        }
    if (txtNome.getText().isEmpty())
        {
            lblErroreNome.setVisible(true);
            txtNome.setStyle("-fx-border-color: #d70000");
        }
    if (txtCognome.getText().isEmpty())
        {
            lblErroreCognome.setVisible(true);
            txtCognome.setStyle("-fx-border-color: #d70000");
        }
    if (dtpDataNascita.getEditor().getText().isEmpty())
        {
            lblErroreDataNascitaEmpty.setVisible(true);
            dtpDataNascita.setStyle("-fx-border-color: #d70000");
        }
    if (txtEmail.getText().isEmpty())
    {
        lblErroreEmailEmpty.setVisible(true);
        txtEmail.setStyle("-fx-border-color: #d70000");
    }
    if (txtVia.getText().isEmpty())
        {
            lblErroreVia.setVisible(true);
            txtVia.setStyle("-fx-border-color: #d70000");
        }
    if (txtCivico.getText().isEmpty())
        {
            lblErroreCivico.setVisible(true);
            txtCivico.setStyle("-fx-border-color: #d70000");
        }
    if (txtCitta.getText().isEmpty())
        {
            lblErroreCitta.setVisible(true);
            txtCitta.setStyle("-fx-border-color: #d70000");
        }

    if (txtCAP.getText().isEmpty())
        {
            lblErroreCAPEmpty.setVisible(true);
            txtCAP.setStyle("-fx-border-color: #d70000");
        }
    }

    @FXML
    protected void onAnnulla(){
        ((Stage) btnAnnulla.getScene().getWindow()).close();
    }

    @FXML
    protected void onRegistrati() throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        onCheckEmpty();

        //metodo per il click del tasto salva
        String username = txtUsername.getText();
        String psw = txtPassword.getText();
        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        LocalDate dataNascita = LocalDate.parse(dtpDataNascita.getValue().toString());
        String mail = txtEmail.getText();
        String via = txtVia.getText();
        String civico = txtCivico.getText();
        String citta = txtCitta.getText();
        String cap = txtCAP.getText();

        Account.signUp(username,psw,nome,cognome,dataNascita,mail, via, civico, citta, cap);

        lblRegistrazioneOK.setVisible(true);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                Thread.sleep(2000);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            lblRegistrazioneOK.setVisible(false);
            onAnnulla();
        });
        new Thread(task).start();

    }


}
