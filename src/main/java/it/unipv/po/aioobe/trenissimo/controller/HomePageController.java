package it.unipv.po.aioobe.trenissimo.controller;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.VoucherService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.ValoreVoucher;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import it.unipv.po.aioobe.trenissimo.view.*;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.ToggleSwitch;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller class per homePage-view.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @see it.unipv.po.aioobe.trenissimo.view.homePage
 * @see javafx.fxml.Initializable
 */

public class HomePageController implements Initializable {

    @FXML
    private DatePicker dtpBigliettoPartenza;
    @FXML
    private ToggleSwitch tgsBigliettoAR;
    @FXML
    private VBox boxLoading;
    @FXML
    private VBox boxContent;
    @FXML
    private DatePicker dtpBigliettoRitorno;
    @FXML
    private JFXTimePicker tmpBigliettoRitorno;
    @FXML
    private JFXTimePicker tmpBigliettoAndata;

    @FXML
    private ComboBox<ValoreVoucher> cmbVoucherValore;

    @FXML
    private Label lblNumAdulti;
    @FXML
    private Label lblNumRagazzi;
    @FXML
    private Label lblNumBambini;
    @FXML
    private Label lblNumAnimali;

    @FXML
    private Spinner spnBigliettoAdulti;
    @FXML
    private Spinner spnBigliettoRagazzi;
    @FXML
    private Spinner spnBigliettoBambini;
    @FXML
    private Spinner spnBigliettoAnimali;

    @FXML
    private Group grpLoggedIn;
    @FXML
    private Group grpLoggedOut;

    @FXML
    private SearchableComboBox<StopsEntity> scbBigliettoPartenza;
    @FXML
    private SearchableComboBox<StopsEntity> scbBigliettoDestinazione;


    @FXML
    private TabPane tabPaneRicerca;
    @FXML
    private TabPane tabPaneRimborso;
    @FXML
    private TextField txtRimborsoTitoloID;
    @FXML
    private Label lblErroreRimborso;
    @FXML
    private Label lblErroreRimborsoEmpty;
    @FXML
    private Label lblRimborsoOK;
    @FXML
    private Button btnRichiestaRimborso;

    private TicketBuilder titoloViaggio;
    private boolean isIdBigliettoOK;


    /**
     * Permette di ricercare, in tempo reale, una stazione tra quelle esistenti
     *
     * @param scb componente searchable combo box
     * @see SearchableComboBox
     * @see StopsEntity
     * @see CachedStopsService
     * @see StringConverter
     */
    protected void initScb(@NotNull SearchableComboBox scb) {

        var result = CachedStopsService.getInstance().findAll().stream().sorted(Comparator.comparing(StopsEntity::getStopName)).toList();

        scb.setItems(FXCollections.observableArrayList(result));
        scb.setConverter(new StringConverter<StopsEntity>() {
            @Override
            public String toString(StopsEntity user) {

                if (user == null) {
                    return null;
                } else {
                    return user.getStopName();
                }
            }

            @Override
            public StopsEntity fromString(String id) {
                return null;
            }
        });
    }

    /**
     * Mostra una finestra di dialogo contenente informazioni personalizzate
     *
     * @param contentText messaggio da stampare nell'alert
     * @see Alert
     * @see Stage
     */
    private void setAlert(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Trenissimo");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(HomePage.class.getResourceAsStream("HomePage/LogoIcona.png"))));
        alert.showAndWait();
    }

    /**
     * Metodo d'Inizializzazione
     *
     * @param url
     * @param resourceBundle
     * @see #onRicercaSelected()
     * @see #onAccountChange()
     * @see #checkIdRealTime()
     * @see Account
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        spnBigliettoAdulti.valueProperty().addListener((obs, oldV, newV) -> {
            lblNumAdulti.setText(newV.toString());
        });
        spnBigliettoRagazzi.valueProperty().addListener((obs, oldV, newV) -> {
            lblNumRagazzi.setText(newV.toString());
        });
        spnBigliettoBambini.valueProperty().addListener((obs, oldV, newV) -> {
            lblNumBambini.setText(newV.toString());
        });
        spnBigliettoAnimali.valueProperty().addListener((obs, oldV, newV) -> {
            lblNumAnimali.setText(newV.toString());
        });

        tmpBigliettoAndata.set24HourView(true);
        tmpBigliettoRitorno.set24HourView(true);

        dtpBigliettoPartenza.setValue(LocalDate.now());
        tmpBigliettoAndata.setValue(LocalTime.now());

        initScb(scbBigliettoPartenza);
        initScb(scbBigliettoDestinazione);

        cmbVoucherValore.setItems(FXCollections.observableArrayList(ValoreVoucher.values()));


        tgsBigliettoAR.selectedProperty().addListener((obs, oldVal, newVal) -> {
            dtpBigliettoRitorno.setDisable(!newVal);
            tmpBigliettoRitorno.setDisable(!newVal);
        });

        Account.loggedInProperty.addListener((obs, old, newV) -> onAccountChange());
        onAccountChange();


        onRicercaSelected();
        checkIdRealTime();


    }

    /**
     * Cambio gli elementi visibili in base al tipo di cliente, ospite o utente loggato
     */
    private void onAccountChange() {
        grpLoggedIn.setManaged(Account.getLoggedIn());
        grpLoggedIn.setVisible(Account.getLoggedIn());

        grpLoggedOut.setManaged(!Account.getLoggedIn());
        grpLoggedOut.setVisible(!Account.getLoggedIn());
    }

    /**
     * Permette l'aperture della finestra di registrazione, registrazione-view.fxml
     *
     * @throws IOException
     * @see Registrazione
     * @see it.unipv.po.aioobe.trenissimo.view.registrazione
     */
    @FXML
    protected void onSignup() throws IOException {
        Registrazione.open(boxContent.getScene().getWindow());
    }

    /**
     * Permette di effettuare il logout
     *
     * @see Account
     */
    @FXML
    protected void onLogout() {
        Account.getInstance().logout();
    }

    /**
     * Permette l'apertura della finestra delle impostazioni, accountSettings-view.fxml
     *
     * @see AccountSettingsController
     * @see it.unipv.po.aioobe.trenissimo.view.accountSettingsView
     */
    @FXML
    protected void onAccountSettings() {
        AccountSettings.openScene(boxContent.getScene().getWindow());
    }

    /**
     * Permette di ricercare i viaggi disponibili e di mostrarli aprendo ricercaView.fxml
     *
     * @see #setAlert(String)
     * @see Ricerca
     * @see RicercaView
     * @see it.unipv.po.aioobe.trenissimo.view.ricercaView
     */
    @FXML
    protected void onRicerca() {

        if (scbBigliettoPartenza.getValue() == null || scbBigliettoDestinazione.getValue() == null || scbBigliettoDestinazione.getValue() == scbBigliettoPartenza.getValue()) {
            setAlert("Selezionare stazione di partenza e/o destinazione validi!");
            return;
        } else if (dtpBigliettoPartenza.getValue() == null
                || tmpBigliettoAndata.getValue() == null
                || (tgsBigliettoAR.isSelected() && dtpBigliettoRitorno.getValue() == null)
                || (tgsBigliettoAR.isSelected() && tmpBigliettoRitorno.getValue() == null)
                || (dtpBigliettoPartenza.getValue().isBefore(LocalDate.now()))
                || (tmpBigliettoAndata.getValue().isBefore(LocalTime.now().truncatedTo(ChronoUnit.HOURS)) && dtpBigliettoPartenza.getValue().equals(LocalDate.now()))
                || (tgsBigliettoAR.isSelected() && dtpBigliettoRitorno.getValue().isBefore(LocalDate.now()))
                || (tgsBigliettoAR.isSelected() && tmpBigliettoRitorno.getValue().isBefore(LocalTime.now().truncatedTo(ChronoUnit.HOURS)) && dtpBigliettoRitorno.getValue().equals(LocalDate.now()))
                || (tgsBigliettoAR.isSelected() && dtpBigliettoRitorno.getValue().isBefore(dtpBigliettoPartenza.getValue())) || (tgsBigliettoAR.isSelected() && tmpBigliettoRitorno.getValue().isBefore(tmpBigliettoAndata.getValue()) && dtpBigliettoRitorno.getValue().equals(dtpBigliettoPartenza.getValue()))){
            setAlert("Inserire data e/o orario di partenza e/o ritorno validi!");
            return;
        } else if ((int)spnBigliettoAdulti.getValue() == 0 && (int)spnBigliettoRagazzi.getValue() == 0 && (int)spnBigliettoBambini.getValue() == 0 && (int)spnBigliettoAnimali.getValue() == 0) {
            setAlert("Inserire almeno un passeggero!");
            return;
        }

        boxLoading.setVisible(true);
        boxContent.setDisable(true);
        Task<Ricerca> task = new Task<>() {

            /**
             * Permette di effettuare la ricerca
             * @return Ritorna un oggetto Ricerca contenente i risultati della ricerca
             * @see Ricerca
             */
            @Override
            public @NotNull Ricerca call() {
                int partenzaId = (scbBigliettoPartenza.getValue()).getStopId();
                int destinazioneId = (scbBigliettoDestinazione.getValue()).getStopId();
                LocalDateTime data = LocalDateTime.of(dtpBigliettoPartenza.getValue(), tmpBigliettoAndata.getValue());

                Ricerca search = new Ricerca(partenzaId, destinazioneId, data);
                if (tgsBigliettoAR.isSelected()) {
                    search.setAndataRitorno(true);
                    search.setDataRitorno(LocalDateTime.of(dtpBigliettoRitorno.getValue(), tmpBigliettoRitorno.getValue()));
                }

                search.setNumAdulti((int) spnBigliettoAdulti.getValue());
                search.setNumRagazzi((int) spnBigliettoRagazzi.getValue());
                search.setNumBambini((int) spnBigliettoBambini.getValue());
                search.setNumAnimali((int) spnBigliettoAnimali.getValue());

                search.eseguiRicerca();
                return search;
            }
        };

        task.setOnSucceeded(e -> {
            boxLoading.setVisible(false);
            boxContent.setDisable(false);
            try {
                RicercaView.openScene((Ricerca) e.getSource().getValue(), boxContent.getScene().getWindow());
            } catch (NoSuchElementException event) {
                setAlert("Viaggio inesistente!");
            }
        });
        new Thread(task).start();
    }

    /**
     * Permette l'apertura della finestra d'acquisto del voucher, acquistoVoucher.fxml
     *
     * @see AcquistoVoucherView
     * @see it.unipv.po.aioobe.trenissimo.view.acquistoView
     */
    @FXML
    protected void onVoucherCheckout() {
        if (cmbVoucherValore.getValue() == null) {
            setAlert("Impossibile effettuare il checkout. Scegliere il valore del voucher!");
            return;
        } else AcquistoVoucherView.openScene(boxContent.getScene().getWindow(), cmbVoucherValore.getValue());
    }

    /**
     * Permette l'apertura della finestra di login, login-view.fxml
     *
     * @throws IOException
     * @see Login
     * @see it.unipv.po.aioobe.trenissimo.view.login
     */
    @FXML
    protected void onLogin() throws IOException {
        Login.open((boxContent).getScene().getWindow());
    }

    /**
     * Permette di spostarsi nella sezione ricerca
     */
    @FXML
    protected void onRicercaSelected() {
        tabPaneRicerca.setVisible(true);
        tabPaneRimborso.setVisible(false);
        txtRimborsoTitoloID.setText("");
        lblRimborsoOK.setVisible(false);
        lblErroreRimborsoEmpty.setVisible(false);
        lblErroreRimborso.setVisible(false);
        txtRimborsoTitoloID.setStyle("-fx-border-color: #cccccc");

    }

    /**
     * Permette di spostarsi nella sezione ricerca
     */
    @FXML
    protected void onRimborsoSelected() {
        tabPaneRicerca.setVisible(false);
        tabPaneRimborso.setVisible(true);
    }

    /**
     * Gestisce il rimborso di un titolo di viaggio in base ai criteri Rimborso presenti i properties
     *
     * @throws Exception
     * @see #setAlert(String)
     * @see #onScaricaBigliettoPDF(VoucherEntity)
     * @see Rimborso
     * @see VoucherService
     * @see VoucherEntity
     * @see it.unipv.po.aioobe.trenissimo.properties
     */
    @FXML
    protected void onRimborso() throws Exception {

        if (txtRimborsoTitoloID.getText().isEmpty()) {
            lblErroreRimborsoEmpty.setVisible(true);
            txtRimborsoTitoloID.setStyle("-fx-border-color: #d70000");
            isIdBigliettoOK = false;
        }
        if (!(Rimborso.checkIdBiglietto(txtRimborsoTitoloID.getText()))) {
            lblErroreRimborso.setVisible(true);
            txtRimborsoTitoloID.setStyle("-fx-border-color: #d70000");
            isIdBigliettoOK = false;
        }

        if (isIdBigliettoOK) {
            Rimborso r = new Rimborso(txtRimborsoTitoloID.getText());
            VoucherService voucherService = new VoucherService();
            VoucherEntity v = r.getRimborso();
            if (v == null) {
                setAlert("Impossibile richiedere un rimborso perché la data di partenza\nè incompatibile con le modalità di rimborso!");
                return;
            } else voucherService.persist(v);

            onScaricaBigliettoPDF(v);
            isIdBigliettoOK = false;

        }

    }

    /**
     * Controllo sull'ID del titolo del viaggio al fine di effettuare il rimborso
     *
     * @see Utils
     * @see Rimborso
     */
    private void checkIdRealTime() {

        txtRimborsoTitoloID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtRimborsoTitoloID.getText())) {
                lblErroreRimborso.setVisible(false);
                lblErroreRimborsoEmpty.setVisible(false);
                btnRichiestaRimborso.setDisable(false);
                txtRimborsoTitoloID.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreRimborso.setVisible(false);
                lblErroreRimborsoEmpty.setVisible(true);
                txtRimborsoTitoloID.setStyle("-fx-border-color: #d70000");
            }
            try {
                if (!(Rimborso.checkIdBiglietto(txtRimborsoTitoloID.getText()))) {
                    lblErroreRimborso.setVisible(true);
                    txtRimborsoTitoloID.setStyle("-fx-border-color: #d70000");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        btnRichiestaRimborso.setOnMouseMoved(c -> {
            if ((lblErroreRimborso.isVisible() || lblErroreRimborsoEmpty.isVisible())) {
                btnRichiestaRimborso.setDisable(true);
                isIdBigliettoOK = false;
            } else isIdBigliettoOK = true;

        });
    }

    /**
     * Apre il file chooser e permette di scaricare il voucher (rimborso) in formato PDF
     *
     * @throws Exception
     * @see #fillPDF(VoucherEntity)
     * @see File
     * @see FileChooser
     * @see TicketBuilder
     */
    @FXML
    protected void onScaricaBigliettoPDF(VoucherEntity voucherEntity) throws Exception {
        fillPDF(voucherEntity);

        File biglietto = new File(TicketBuilder.DEST);

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Scegli dove salvare il titolo di viaggio");
        fileChooser.setInitialFileName(voucherEntity.getId());

        File destin = new File(fileChooser.showSaveDialog(new Stage()).getAbsolutePath().concat(".pdf"));
        TicketBuilder.copy(biglietto, destin);

        lblRimborsoOK.setVisible(true);

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
            lblRimborsoOK.setVisible(false);
            biglietto.delete();
        });
        new Thread(task).start();
    }

    /**
     * Compila i campi del voucher PDF
     *
     * @param voucherEntity
     * @throws Exception
     * @see TicketBuilder
     * @see VoucherEntity
     */
    private void fillPDF(@NotNull VoucherEntity voucherEntity) throws Exception {
        titoloViaggio = new TicketBuilder(voucherEntity.getId(), String.valueOf(voucherEntity.getPrezzo()));

        titoloViaggio.createPdf(voucherEntity.getId());
    }


    /**
     * Apre la finestra nascosta fatta con Java Swing
     * <a href="https://www.youtube.com/watch?v=pXPXMxsXT28">See anything unusual?</a>
     *
     * @param event
     */
    @FXML
    protected void onSandraBullock(MouseEvent event) {
        if (event.isShiftDown() && event.isControlDown()) {
            (new HiddenJFrame()).setVisible(true);
        }
    }

}


