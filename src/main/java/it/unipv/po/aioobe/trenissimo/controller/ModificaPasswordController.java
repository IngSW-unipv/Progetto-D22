package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.Nullable;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

/**
 * Controller class per modificaPassword-view.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @see it.unipv.po.aioobe.trenissimo.view.login
 * @see javafx.fxml.Initializable
 */
public class ModificaPasswordController implements Initializable {

    @FXML
    private Button btnAnnulla;
    @FXML
    private Button btnSalva;

    @FXML
    private TextField txtVecchiaPsw;
    @FXML
    private TextField txtNuovaPsw;
    @FXML
    private TextField txtConfermaPsw;


    @FXML
    private Label lblErroreCorrispondenza;
    @FXML
    private Label lblErrorePasswordVecchia;

    @FXML
    private Label lblErroreVecchiaEmpty;
    @FXML
    private Label lblErroreNuovaEmpty;
    @FXML
    private Label lblErroreConfermaEmpty;

    @FXML
    private Label lblSalvataggioOK;

    private boolean isPswCheckOk;


    /**
     * Metodo d'Inizializzazione
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            isPswCheckOk = true;
            onCheck();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Controllo sulla vecchia e nuova password
     *
     * @throws IOException
     * @see Account
     * @see Utils
     */
    @FXML
    protected void onCheck() throws IOException {

        txtVecchiaPsw.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtVecchiaPsw.getText())) {
                lblErrorePasswordVecchia.setVisible(false);
                lblErroreVecchiaEmpty.setVisible(false);
                btnSalva.setDisable(false);
                txtVecchiaPsw.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErrorePasswordVecchia.setVisible(false);
                lblErroreVecchiaEmpty.setVisible(true);
                txtVecchiaPsw.setStyle("-fx-border-color: #d70000");
            }
            try {
                if (!(Account.checkUserPassword(Account.getInstance().getUsername(), txtVecchiaPsw.getText()))) {
                    lblErrorePasswordVecchia.setVisible(true);
                    txtVecchiaPsw.setStyle("-fx-border-color: #d70000");
                }
            } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        txtNuovaPsw.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtNuovaPsw.getText())) {
                lblErroreCorrispondenza.setVisible(false);
                lblErroreNuovaEmpty.setVisible(false);
                btnSalva.setDisable(false);
                txtNuovaPsw.setStyle("-fx-border-color: #cccccc");
                txtConfermaPsw.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCorrispondenza.setVisible(false);
                lblErroreNuovaEmpty.setVisible(true);
                txtNuovaPsw.setStyle("-fx-border-color: #d70000");
            }

            if (!(txtNuovaPsw.getText().equals(txtConfermaPsw.getText()))) {
                txtConfermaPsw.setStyle("-fx-border-color: #d70000");
                lblErroreCorrispondenza.setVisible(true);
            }

        });
        txtConfermaPsw.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtConfermaPsw.getText())) {
                lblErroreCorrispondenza.setVisible(false);
                lblErroreConfermaEmpty.setVisible(false);
                btnSalva.setDisable(false);
                txtConfermaPsw.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCorrispondenza.setVisible(false);
                lblErroreConfermaEmpty.setVisible(true);
                txtConfermaPsw.setStyle("-fx-border-color: #d70000");
            }

            if (!(txtNuovaPsw.getText().equals(txtConfermaPsw.getText()))) {
                txtConfermaPsw.setStyle("-fx-border-color: #d70000");
                lblErroreCorrispondenza.setVisible(true);
            }
        });


        btnSalva.setOnMouseMoved(c -> {
            if (lblErroreConfermaEmpty.isVisible() || lblErroreNuovaEmpty.isVisible() || lblErroreVecchiaEmpty.isVisible() || lblErroreCorrispondenza.isVisible() || lblErrorePasswordVecchia.isVisible()) {
                btnSalva.setDisable(true);
                isPswCheckOk = false;
            } else isPswCheckOk = true;

        });

    }

    /**
     * Controllo sui text-field vuoti
     */
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

    /**
     * Annulla operazione di cambio password
     */
    @FXML
    protected void onAnnulla() {
        ((Stage) btnAnnulla.getScene().getWindow()).close();
    }

    /**
     * Salva la nuova password
     *
     * @throws IOException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @see #onCheckEmpty()
     * @see #onAnnulla()
     * @see Account
     */

    @FXML
    protected void onSalvaPassword() throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        onCheckEmpty();

        if (isPswCheckOk) {
            lblSalvataggioOK.setVisible(true);
            Account.getInstance().setPassword(txtNuovaPsw.getText());

            Task<Void> task = new Task<Void>() {
                /**
                 * Aspetta 2.0 secondi
                 * @return sempre null
                 * @throws InterruptedException necessaria per Thread.sleep()
                 */
                @Override
                public @Nullable Void call() throws InterruptedException {
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
