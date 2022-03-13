package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.ValoreVoucher;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller class per acquistoVoucher.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @version %I%, %G%
 * @see it.unipv.po.aioobe.trenissimo.view.acquistoVoucher
 * @see javafx.fxml.Initializable
 */

public class AcquistoVoucherController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private Label lblVoucherId;
    @FXML
    private TextArea txtMessaggio;
    @FXML
    private Label lblPrice;

    @FXML
    private Button btnAcquisto;
    @FXML
    private TextField txtNumCarta;
    @FXML
    private TextField txtDataScadenza;
    @FXML
    private TextField txtCVV;

    @FXML
    private Label lblTotale;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;
    @FXML
    private DatePicker dtpDataNascita;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtVia;
    @FXML
    private TextField txtCivico;
    @FXML
    private TextField txtCitta;
    @FXML
    private TextField txtCAP;

    @FXML
    private Button btnAggiungiPagamento;
    @FXML
    private Label lblErroreNumCarta;
    @FXML
    private Label lblErroreData;
    @FXML
    private Label lblErroreCVV;

    @FXML
    private Label lblErroreCAP;
    @FXML
    private Label lblErroreEmail;
    @FXML
    private Label lblErroreDataNascita;
    @FXML
    private Label lblErroreNome;
    @FXML
    private Label lblErroreCognome;
    @FXML
    private Label lblErroreVia;
    @FXML
    private Label lblErroreCivico;
    @FXML
    private Label lblErroreCitta;

    @FXML
    private Label lblCartaOK;
    @FXML
    private Button btnConferma;
    @FXML
    private Label lblDatiOK;

    @FXML
    private Label lblErroreMaxCaratteri;

    @FXML
    private VBox vboxDragMouse;

    private TicketBuilder titoloViaggio;
    private VoucherEntity voucher;

    /**
     * Metodo d'Inizializzazione
     *
     * @param location
     * @param resources
     * @see #checkPagamentoRealTime()
     * @see #checkDatiRealTime()
     * @see Account
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        checkPagamentoRealTime();
        checkDatiRealTime();

        vboxDragMouse.setOnMouseMoved(c -> {
            if ((lblDatiOK.isVisible() && lblCartaOK.isVisible())) btnAcquisto.setDisable(false);
        });

        txtMessaggio.textProperty().addListener(c -> {
            if (txtMessaggio.getText().length() >= 100) {
                txtMessaggio.setStyle("-fx-border-color: #d70000; -fx-border-radius: 8 8 8 8");
                lblErroreMaxCaratteri.setVisible(true);
                txtMessaggio.setDisable(true);
            } else {
                txtMessaggio.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 8 8 8 8");
                lblErroreMaxCaratteri.setVisible(false);
            }
        });

        if (Account.getLoggedIn()) {
            txtNome.setText(Account.getInstance().getDatiPersonali().getNome());
            txtCognome.setText(Account.getInstance().getDatiPersonali().getCognome());
            dtpDataNascita.setValue(Account.getInstance().getDatiPersonali().getDataNascita().toLocalDate());
            txtEmail.setText(Account.getInstance().getDatiPersonali().getMail());
            txtVia.setText(Account.getInstance().getDatiPersonali().getVia());
            txtCivico.setText(Account.getInstance().getDatiPersonali().getCivico());
            txtCitta.setText(Account.getInstance().getDatiPersonali().getCitta());
            txtCAP.setText(Account.getInstance().getDatiPersonali().getCap().toString());
        }
    }

    /**
     * Genera un voucher dato un importo
     *
     * @param importo valore in euro (enum)
     * @see VoucherEntity
     */

    public void setVoucher(@NotNull ValoreVoucher importo) {

        String[] valore = importo.toString().split("(?=€)");

        voucher = new VoucherEntity();
        voucher.setPrezzo(Double.parseDouble(valore[0]));
        lblVoucherId.setText(voucher.getId());
        lblPrice.setText(importo.toString());
        lblTotale.setText("€ " + voucher.getPrezzo());

    }

    /**
     * Abilita le modifiche al text-field messaggio
     */
    @FXML
    protected void onModificaTesto() {
        txtMessaggio.setDisable(false);
    }

    /**
     * @throws Exception
     * @see #onAlert(String)
     * @see #onScaricaBigliettoPDF()
     * @see #onGoToHomepage()
     * @see Acquisto
     * @see Account
     */
    @FXML
    protected void onPaga() throws Exception {

        onAlert("Acquisto avvenuto con successo!");

        List<Acquisto> carrello = new ArrayList<>();
        carrello.add(this.voucher);
        carrello.get(0).pagare();
        if (Account.getLoggedIn()) {
            this.voucher.puntiFedelta(carrello);
            //metodi per aggiornare i punti fedeltà dal db all'istanza di Account
            String username = Account.getInstance().getUsername();
            Account.getInstance().setAccount(username);
        }
        onScaricaBigliettoPDF();

        onGoToHomepage();

    }

    /**
     * Apre il file chooser e permette di scaricare il voucher in formato PDF
     *
     * @throws Exception
     * @see #fillPDF()
     * @see File
     * @see FileChooser
     * @see TicketBuilder
     */
    @FXML
    protected void onScaricaBigliettoPDF() throws Exception {
        fillPDF();

        File voucher = new File(TicketBuilder.DEST);

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Scegli dove salvare il voucher");
        fileChooser.setInitialFileName(lblVoucherId.getText());

        File destin = new File(fileChooser.showSaveDialog(new Stage()).getAbsolutePath().concat(".pdf"));
        TicketBuilder.copy(voucher, destin);

        Task<Void> task = new Task<>() {
            /**
             * Aspetta 4.0 secondi
             * @return sempre null
             * @throws InterruptedException necessaria per Thread.sleep()
             */
            @Override
            public @Nullable Void call() throws InterruptedException {
                Thread.sleep(4000);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            voucher.delete();
        });
        new Thread(task).start();

    }


    /**
     * Compila i campi del voucher PDF
     *
     * @throws Exception
     * @see TicketBuilder
     */
    private void fillPDF() throws Exception {

        titoloViaggio = new TicketBuilder(lblVoucherId.getText(), lblPrice.getText(), txtMessaggio.getText());
        titoloViaggio.createVORegalo();
    }

    /**
     * Conferma i dati personali
     */
    @FXML
    protected void onConfermaDati() {
        lblDatiOK.setVisible(true);
        txtNome.setDisable(true);
        txtCognome.setDisable(true);
        dtpDataNascita.setDisable(true);
        txtVia.setDisable(true);
        txtCivico.setDisable(true);
        txtCitta.setDisable(true);
        txtCAP.setDisable(true);
        txtEmail.setDisable(true);
    }

    /**
     * Gestisce il controllo e la scelta della carta di credito
     *
     * @see Utils
     */
    @FXML
    protected void onAggiungiCarta() {
        if (Utils.checkNumCarta(txtNumCarta.getText()) && Utils.checkDataScadenza(txtDataScadenza.getText()) && Utils.checkCVV(txtCVV.getText())) {
            lblCartaOK.setVisible(true);
            txtNumCarta.setDisable(true);
            txtDataScadenza.setDisable(true);
            txtCVV.setDisable(true);
        }
    }

    /**
     * Controlla la validità della carta di credito
     *
     * @see Utils
     */
    private void checkPagamentoRealTime() {

        txtNumCarta.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkNumCarta(txtNumCarta.getText())) {
                lblErroreNumCarta.setVisible(false);
                btnAggiungiPagamento.setDisable(false);
                txtNumCarta.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreNumCarta.setVisible(true);
                txtNumCarta.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCVV.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkCVV(txtCVV.getText())) {
                lblErroreCVV.setVisible(false);
                btnAggiungiPagamento.setDisable(false);
                txtCVV.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCVV.setVisible(true);
                txtCVV.setStyle("-fx-border-color: #d70000");
            }
        });

        txtDataScadenza.textProperty().addListener((observable, oldValue, newValue) -> {
            if (txtDataScadenza.getText().length() == 7) {
                if (Utils.checkDataScadenza(txtDataScadenza.getText())) {
                    lblErroreData.setVisible(false);
                    btnAggiungiPagamento.setDisable(false);
                    txtDataScadenza.setStyle("-fx-border-color: #cccccc");
                } else {
                    lblErroreData.setVisible(true);
                    txtDataScadenza.setStyle("-fx-border-color: #d70000");
                }
            }
        });

        btnAggiungiPagamento.setOnMouseMoved(c -> {
            if (lblErroreNumCarta.isVisible() || lblErroreCVV.isVisible() || lblErroreData.isVisible() || txtNumCarta.getText().length() == 0 || txtCVV.getText().length() == 0 || txtDataScadenza.getText().length() != 7)
                btnAggiungiPagamento.setDisable(true);
        });

    }

    /**
     * Controlla la validità dei dati personali
     *
     * @see Account
     * @see Utils
     */
    private void checkDatiRealTime() {

        lblErroreCAP.setVisible(true);
        lblErroreEmail.setVisible(true);
        lblErroreDataNascita.setVisible(true);
        lblErroreNome.setVisible(true);
        lblErroreCognome.setVisible(true);
        lblErroreVia.setVisible(true);
        lblErroreCivico.setVisible(true);
        lblErroreCitta.setVisible(true);

        btnConferma.setDisable(true);

        txtCAP.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Account.getInstance().checkCAP(txtCAP.getText())) {
                lblErroreCAP.setVisible(false);
                btnConferma.setDisable(false);
                txtCAP.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCAP.setVisible(true);
                txtCAP.setStyle("-fx-border-color: #d70000");
            }
        });

        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Account.getInstance().checkEmail(txtEmail.getText())) {
                lblErroreEmail.setVisible(false);
                btnConferma.setDisable(false);
                txtEmail.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreEmail.setVisible(true);
                txtEmail.setStyle("-fx-border-color: #d70000");

            }
        });

        dtpDataNascita.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (Account.getInstance().checkDataNascita(dtpDataNascita.getValue())) {
                lblErroreDataNascita.setVisible(false);
                btnConferma.setDisable(false);
                dtpDataNascita.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreDataNascita.setVisible(true);
                dtpDataNascita.setStyle("-fx-border-color: #d70000");
            }
        });

        txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtNome.getText())) {
                lblErroreNome.setVisible(false);
                btnConferma.setDisable(false);
                txtNome.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreNome.setVisible(true);
                txtNome.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCognome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtCognome.getText())) {
                lblErroreCognome.setVisible(false);
                btnConferma.setDisable(false);
                txtCognome.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCognome.setVisible(true);
                txtCognome.setStyle("-fx-border-color: #d70000");
            }
        });

        txtVia.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtVia.getText())) {
                lblErroreVia.setVisible(false);
                btnConferma.setDisable(false);
                txtVia.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreVia.setVisible(true);
                txtVia.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCivico.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtCivico.getText())) {
                lblErroreCivico.setVisible(false);
                btnConferma.setDisable(false);
                txtCivico.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCivico.setVisible(true);
                txtCivico.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCitta.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtCitta.getText())) {
                lblErroreCitta.setVisible(false);
                btnConferma.setDisable(false);
                txtCitta.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCitta.setVisible(true);
                txtCitta.setStyle("-fx-border-color: #d70000");
            }
        });

        btnConferma.setOnMouseMoved(c -> {
            if (lblErroreNome.isVisible() || lblErroreCognome.isVisible() || lblErroreDataNascita.isVisible() || lblErroreEmail.isVisible() || lblErroreVia.isVisible() || lblErroreCivico.isVisible() || lblErroreCitta.isVisible() || lblErroreCAP.isVisible()) {
                btnConferma.setDisable(true);
            }
        });
    }

    /**
     * Mostra una finestra di dialogo contenente informazioni personalizzate
     *
     * @param messaggio messaggio da stampare nell'alert
     * @see Alert
     * @see Stage
     */
    private void onAlert(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Trenissimo");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(HomePage.class.getResourceAsStream("HomePage/LogoIcona.png"))));
        alert.showAndWait();
    }


    /**
     * Ritorna alla Home Page
     *
     * @see HomePage
     */
    @FXML
    protected void onGoToHomepage() {
        HomePage.openScene(root.getScene().getWindow());
    }
}
