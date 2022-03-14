package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.ViaggiPreferitiService;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.view.ModificaPassword;
import it.unipv.po.aioobe.trenissimo.view.StoricoAcquistoControl;
import it.unipv.po.aioobe.trenissimo.view.ViaggioPreferitoControl;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controller class per accountSettings-view.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @see it.unipv.po.aioobe.trenissimo.view.accountSettingsView
 * @see javafx.fxml.Initializable
 */
public class AccountSettingsController implements Initializable {


    @FXML
    private Button btnModifica;
    @FXML
    private Button btnAnnulla;
    @FXML
    private Button btnSalva;
    @FXML
    private Button btnModificaPassword;

    @FXML
    private Label lblBenvenuto;
    @FXML
    private Label lblPunti;
    @FXML
    private Label lblDatiPersonali;
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
    private VBox layout;
    @FXML
    private VBox layoutStorico;

    @FXML
    private TabPane tabPane;

    private ObservableList<ViaggiPreferitiEntity> viaggiPreferiti;
    private ObservableList<StoricoAcquistiEntity> acquisti;


    /**
     * Metodo d'Inizializzazione
     *
     * @param url
     * @param resourceBundle
     * @see #onStart()
     * @see #updateListViaggiPreferiti()
     * @see #updateListStoricoAcquisti()
     * @see ViaggiPreferitiEntity
     * @see StoricoAcquistiEntity
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onStart();

        viaggiPreferiti = FXCollections.observableArrayList();
        viaggiPreferiti.addListener((ListChangeListener<ViaggiPreferitiEntity>) c -> updateListViaggiPreferiti());

        acquisti = FXCollections.observableArrayList();
        acquisti.addListener((ListChangeListener<StoricoAcquistiEntity>) c -> updateListStoricoAcquisti());

    }

    /**
     * Prende dati dal database e riempie i text-fields della view
     *
     * @see ViaggioPreferitoControl
     * @see Account
     */
    @FXML
    protected void onStart() {
        if (ViaggioPreferitoControl.fromViaggioControl) tabPane.getSelectionModel().select(1);
        else tabPane.getSelectionModel().select(0);

        lblBenvenuto.setText("Ciao, " + Account.getInstance().getDatiPersonali().getNome());
        lblPunti.setText(Account.getInstance().getPuntiFedelta());
        lblDatiPersonali.setText("Dati Personali");
        txtNome.setText(Account.getInstance().getDatiPersonali().getNome());
        txtCognome.setText(Account.getInstance().getDatiPersonali().getCognome());
        dtpDataNascita.setValue(Account.getInstance().getDatiPersonali().getDataNascita().toLocalDate());
        txtEmail.setText(Account.getInstance().getDatiPersonali().getMail());
        txtVia.setText(Account.getInstance().getDatiPersonali().getVia());
        txtCivico.setText(Account.getInstance().getDatiPersonali().getCivico());
        txtCitta.setText(Account.getInstance().getDatiPersonali().getCitta());
        txtCAP.setText(Account.getInstance().getDatiPersonali().getCap().toString());

    }

    /**
     * Ritorna alla Home Page
     *
     * @see HomePage
     */
    @FXML
    protected void onGoToHomepage() {
        HomePage.openScene(lblBenvenuto.getScene().getWindow());
    }

    /**
     * Abilita la possibilità di modificare i dati con controlli sul formato
     *
     * @see #abilita(TextField)
     * @see Utils
     */
    @FXML
    protected void onModifica() {

        lblDatiPersonali.setText("Modifica i dati personali:");

        abilita(txtNome);
        abilita(txtCognome);
        dtpDataNascita.setMouseTransparent(false);
        dtpDataNascita.setDisable(false);
        dtpDataNascita.setFocusTraversable(false);
        abilita(txtEmail);
        abilita(txtVia);
        abilita(txtCivico);
        abilita(txtCitta);
        abilita(txtCAP);


        btnSalva.setVisible(true);
        btnAnnulla.setVisible(true);
        btnModifica.setVisible(false);
        btnModificaPassword.setDisable(false);

        txtCAP.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Account.getInstance().checkCAP(txtCAP.getText())) {
                lblErroreCAP.setVisible(false);
                btnSalva.setDisable(false);
                txtCAP.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCAP.setVisible(true);
                txtCAP.setStyle("-fx-border-color: #d70000");
            }
        });

        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Account.getInstance().checkEmail(txtEmail.getText())) {
                lblErroreEmail.setVisible(false);
                btnSalva.setDisable(false);
                txtEmail.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreEmail.setVisible(true);
                txtEmail.setStyle("-fx-border-color: #d70000");

            }
        });

        dtpDataNascita.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (Account.getInstance().checkDataNascita(dtpDataNascita.getValue())) {
                lblErroreDataNascita.setVisible(false);
                btnSalva.setDisable(false);
                dtpDataNascita.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreDataNascita.setVisible(true);
                dtpDataNascita.setStyle("-fx-border-color: #d70000");
            }
        });

        txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtNome.getText())) {
                lblErroreNome.setVisible(false);
                btnSalva.setDisable(false);
                txtNome.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreNome.setVisible(true);
                txtNome.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCognome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtCognome.getText())) {
                lblErroreCognome.setVisible(false);
                btnSalva.setDisable(false);
                txtCognome.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCognome.setVisible(true);
                txtCognome.setStyle("-fx-border-color: #d70000");
            }
        });

        txtVia.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtVia.getText())) {
                lblErroreVia.setVisible(false);
                btnSalva.setDisable(false);
                txtVia.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreVia.setVisible(true);
                txtVia.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCivico.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtCivico.getText())) {
                lblErroreCivico.setVisible(false);
                btnSalva.setDisable(false);
                txtCivico.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCivico.setVisible(true);
                txtCivico.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCitta.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtCitta.getText())) {
                lblErroreCitta.setVisible(false);
                btnSalva.setDisable(false);
                txtCitta.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreCitta.setVisible(true);
                txtCitta.setStyle("-fx-border-color: #d70000");
            }
        });

        btnSalva.setOnMouseMoved(c -> {
            if (lblErroreCAP.isVisible() || lblErroreEmail.isVisible() || lblErroreDataNascita.isVisible() || lblErroreNome.isVisible() || lblErroreCognome.isVisible() || lblErroreVia.isVisible() || lblErroreCivico.isVisible() || lblErroreCitta.isVisible())
                btnSalva.setDisable(true);
        });

    }

    /**
     * Abilita la possibilità di modificare la password aprendo modificaPassword-view
     *
     * @throws IOException
     * @see #onAnnulla()
     * @see ModificaPassword
     * @see it.unipv.po.aioobe.trenissimo.view.modificaPassword
     */
    @FXML
    protected void onModificaPassword() throws IOException {
        ModificaPassword.open(lblBenvenuto.getScene().getWindow());

        Task<Void> task = new Task<>() {

            /**
             * Aspetta 1.5 secondi
             * @return null
             * @throws InterruptedException necessaria per Thread.sleep()
             * @see Thread
             */
            @Override
            public @Nullable Void call() throws InterruptedException {
                Thread.sleep(1500);
                return null;
            }
        };

        task.setOnSucceeded(e -> onAnnulla());

        new Thread(task).start();
    }

    /**
     * Disabilita text-fields e buttons
     *
     * @see #disabilita(TextField)
     * @see #onStart()
     */
    @FXML
    protected void onAnnulla() {

        disabilita(txtNome);
        disabilita(txtCognome);
        dtpDataNascita.setMouseTransparent(true);
        dtpDataNascita.setDisable(true);
        dtpDataNascita.setFocusTraversable(true);
        disabilita(txtEmail);
        disabilita(txtVia);
        disabilita(txtCivico);
        disabilita(txtCitta);
        disabilita(txtCAP);

        btnSalva.setVisible(false);
        btnAnnulla.setVisible(false);
        btnModifica.setVisible(true);
        btnModificaPassword.setDisable(true);

        onStart();
    }

    /**
     * Prende i dati dai text-field dei dati personali e li salva nel database
     *
     * @see #onStart()
     * @see #onAnnulla()
     * @see Account
     */
    @FXML
    protected void onSalva() {

        String nome = txtNome.getText();
        String cognome = txtCognome.getText();
        LocalDate dataNascita = LocalDate.parse(dtpDataNascita.getValue().toString());
        String mail = txtEmail.getText();
        String via = txtVia.getText();
        String civico = txtCivico.getText();
        String citta = txtCitta.getText();
        String cap = txtCAP.getText();

        Account.getInstance().salvaModificaDati(nome, cognome, dataNascita, mail, via, civico, citta, cap);

        onStart();

        onAnnulla();

    }

    /**
     * Carica dal database i viaggi preferiti
     *
     * @see ViaggiPreferitiService
     * @see ViaggiPreferitiEntity
     */
    public void setViaggiPreferiti() {
        ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
        this.viaggiPreferiti.addAll(viaggiPreferitiService.findByUsername(Account.getInstance().getUsername()));

    }

    /**
     * Aggiorna i viaggi preferiti all'interno della view
     *
     * @see ViaggioPreferitoControl
     * @see ViaggiPreferitiEntity
     */
    private void updateListViaggiPreferiti() {
        layout.getChildren().setAll(viaggiPreferiti.stream().map(ViaggioPreferitoControl::new).toList());
    }

    /**
     * Carica dal database gli storico acquisto
     *
     * @see StoricoAcquistiService
     * @see StoricoAcquistiEntity
     */
    public void setStoricoAcquisti() {
        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
        this.acquisti.addAll(storicoAcquistiService.findByUsername(Account.getInstance().getUsername()));
    }

    /**
     * Aggiorna gli storico acquisto all'interno della view
     *
     * @see StoricoAcquistoControl
     * @see StoricoAcquistiEntity
     */
    private void updateListStoricoAcquisti() {
        layoutStorico.getChildren().setAll(acquisti.stream().map(StoricoAcquistoControl::new).toList());
    }


    /**
     * Abilita singolo text-field
     *
     * @param a Text-field da abilitare
     * @see #onModifica()
     * @see #onModificaPassword()
     */
    private void abilita(@NotNull TextField a) {
        a.setMouseTransparent(false);
        a.setDisable(false);
        a.setFocusTraversable(false);
    }

    /**
     * Disabilita singolo text-field
     *
     * @param a Text-field da disabilitare
     * @see #onModifica()
     * @see #onModificaPassword()
     */
    private void disabilita(@NotNull TextField a) {
        a.setMouseTransparent(true);
        a.setDisable(true);
        a.setFocusTraversable(true);
    }

}
