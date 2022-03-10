package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.user.Account;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class ModificaPasswordController implements Initializable {

    @FXML private Button btnAnnulla;
    @FXML private Button btnSalva;

    @FXML private TextField txtVecchiaPsw;
    @FXML private TextField txtNuovaPsw;
    @FXML private TextField txtConfermaPsw;


    @FXML private Label lblErroreCorrispondenza;
    @FXML private Label lblErrorePasswordVecchia;

    @FXML private Label lblErroreVecchiaEmpty;
    @FXML private Label lblErroreNuovaEmpty;
    @FXML private Label lblErroreConfermaEmpty;

    @FXML private Label lblSalvataggioOK;

    private boolean isPswCheckOk;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            isPswCheckOk = true;
            onCheck();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @FXML
    protected void onCheck() throws IOException {

        txtVecchiaPsw.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtVecchiaPsw.getText())) {
                lblErrorePasswordVecchia.setVisible(false);
                lblErroreVecchiaEmpty.setVisible(false);
                btnSalva.setDisable(false);
                txtVecchiaPsw.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErrorePasswordVecchia.setVisible(false);
                lblErroreVecchiaEmpty.setVisible(true);
                txtVecchiaPsw.setStyle("-fx-border-color: #d70000");
            }
            try {
                if (!(Account.checkUserPassword(Account.getInstance().getUsername(), txtVecchiaPsw.getText()))) {
                    lblErrorePasswordVecchia.setVisible(true);
                    txtVecchiaPsw.setStyle("-fx-border-color: #d70000");
                }
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        txtNuovaPsw.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtNuovaPsw.getText())) {
                lblErroreCorrispondenza.setVisible(false);
                lblErroreNuovaEmpty.setVisible(false);
                btnSalva.setDisable(false);
                txtNuovaPsw.setStyle("-fx-border-color: #cccccc");
                txtConfermaPsw.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCorrispondenza.setVisible(false);
                lblErroreNuovaEmpty.setVisible(true);
                txtNuovaPsw.setStyle("-fx-border-color: #d70000");
            }

            if (!(txtNuovaPsw.getText().equals(txtConfermaPsw.getText()))){
                txtConfermaPsw.setStyle("-fx-border-color: #d70000");
                lblErroreCorrispondenza.setVisible(true);
            }

        });
        txtConfermaPsw.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtConfermaPsw.getText())) {
                lblErroreCorrispondenza.setVisible(false);
                lblErroreConfermaEmpty.setVisible(false);
                btnSalva.setDisable(false);
                txtConfermaPsw.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCorrispondenza.setVisible(false);
                lblErroreConfermaEmpty.setVisible(true);
                txtConfermaPsw.setStyle("-fx-border-color: #d70000");
            }

            if (!(txtNuovaPsw.getText().equals(txtConfermaPsw.getText()))){
                txtConfermaPsw.setStyle("-fx-border-color: #d70000");
                lblErroreCorrispondenza.setVisible(true);
            }
        });


        btnSalva.setOnMouseMoved(c -> {
        if ( lblErroreConfermaEmpty.isVisible() || lblErroreNuovaEmpty.isVisible() || lblErroreVecchiaEmpty.isVisible()
            || lblErroreCorrispondenza.isVisible() || lblErrorePasswordVecchia.isVisible())
        {
            btnSalva.setDisable(true);
            isPswCheckOk = false;
        }
        else
            isPswCheckOk = true;

    });

    }

    private void onCheckEmpty() {
        if (txtVecchiaPsw.getText().isEmpty()) {
            lblErroreVecchiaEmpty.setVisible(true);
            txtVecchiaPsw.setStyle("-fx-border-color: #d70000");
            isPswCheckOk = false;
        }
        if (txtNuovaPsw.getText().isEmpty()) {
            lblErroreNuovaEmpty.setVisible(true);
            txtNuovaPsw.setStyle("-fx-border-color: #d70000");
            isPswCheckOk = false;
        }
        if (txtConfermaPsw.getText().isEmpty()) {
            lblErroreConfermaEmpty.setVisible(true);
            txtConfermaPsw.setStyle("-fx-border-color: #d70000");
            isPswCheckOk = false;
        }
    }

    @FXML
    protected void onAnnulla(){
        ((Stage) btnAnnulla.getScene().getWindow()).close();
    }

    @FXML
    protected void onSalvaPassword() throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        onCheckEmpty();

        if (isPswCheckOk){
            lblSalvataggioOK.setVisible(true);

            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws InterruptedException {
                    Thread.sleep(2000);
                    return null;
                }
            };
            task.setOnSucceeded(e -> {
                lblSalvataggioOK.setVisible(false);
                onAnnulla();
            });
            new Thread(task).start();
        }


    }


}
