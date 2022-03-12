

package it.unipv.po.aioobe.trenissimo.controller;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.CryptographyUtils;
import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.TitoloViaggioService;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.ToggleSwitch;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML private DatePicker dtpBigliettoPartenza;
    @FXML private ToggleSwitch tgsBigliettoAR;
    @FXML private VBox boxLoading;
    @FXML private VBox boxContent;
    @FXML private DatePicker dtpBigliettoRitorno;
    @FXML private JFXTimePicker tmpBigliettoRitorno;
    @FXML private JFXTimePicker tmpBigliettoAndata;

    @FXML private ComboBox<ValoreVoucher> cmbVoucherValore;

    @FXML private Label lblNumAdulti;
    @FXML private Label lblNumRagazzi;
    @FXML private Label lblNumBambini;
    @FXML private Label lblNumAnimali;

    @FXML private Spinner spnBigliettoAdulti;
    @FXML private Spinner spnBigliettoRagazzi;
    @FXML private Spinner spnBigliettoBambini;
    @FXML private Spinner spnBigliettoAnimali;

    @FXML private Group grpLoggedIn;
    @FXML private Group grpLoggedOut;

    @FXML private SearchableComboBox<StopsEntity> scbBigliettoPartenza;
    @FXML private SearchableComboBox<StopsEntity> scbBigliettoDestinazione;


    @FXML private TabPane tabPaneRicerca;
    @FXML private TabPane tabPaneRimborso;
    @FXML private TextField txtRimborsoTitoloID;
    @FXML private Label lblErroreRimborso;
    @FXML private Label lblErroreRimborsoEmpty;
    @FXML private Label lblRimborsoOK;
    @FXML private Button btnRichiestaRimborso;
// todo continuare gestione errori

    private TicketBuilder titoloViaggio;
    private boolean isIdBigliettoOK;


    protected SearchableComboBox initScb(SearchableComboBox scb){

        var result = CachedStopsService.getInstance().findAll().stream().sorted(Comparator.comparing(stopsEntity -> stopsEntity.getStopName())).toList();

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

        return scb;
    }

    private void setAlert(String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Trenissimo");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(HomePage.class.getResourceAsStream("HomePage/LogoIcona.png")));
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        spnBigliettoAdulti      .valueProperty().addListener((obs,oldV,newV) -> { lblNumAdulti.setText(newV.toString());  });
        spnBigliettoRagazzi     .valueProperty().addListener((obs,oldV,newV) -> { lblNumRagazzi.setText(newV.toString()); });
        spnBigliettoBambini     .valueProperty().addListener((obs,oldV,newV) -> { lblNumBambini.setText(newV.toString()); });
        spnBigliettoAnimali     .valueProperty().addListener((obs,oldV,newV) -> { lblNumAnimali.setText(newV.toString()); });

        tmpBigliettoAndata     .set24HourView(true);
        tmpBigliettoRitorno     .set24HourView(true);

        dtpBigliettoPartenza.setValue(LocalDate.now());
        tmpBigliettoAndata.setValue(LocalTime.now());

        initScb(scbBigliettoPartenza);
        initScb(scbBigliettoDestinazione);

        cmbVoucherValore.setItems(FXCollections.observableArrayList(ValoreVoucher.values()));


        tgsBigliettoAR.selectedProperty().addListener((obs, oldVal, newVal) -> {
            dtpBigliettoRitorno.setDisable(!newVal);
            tmpBigliettoRitorno.setDisable(!newVal);
        });

        Account.loggedInProperty.addListener((obs,old,newV) -> onAccountChange());
        onAccountChange();


        onRicercaSelected();
        checkIdRealTime();


    }

    private void onAccountChange() {;
        grpLoggedIn            .setManaged(Account.getLoggedIn());
        grpLoggedIn            .setVisible(Account.getLoggedIn());

        grpLoggedOut           .setManaged(!Account.getLoggedIn());
        grpLoggedOut           .setVisible(!Account.getLoggedIn());
    }


    @FXML protected void onSignup() throws IOException              { Registrazione.open(boxContent.getScene().getWindow()); }
    @FXML protected void onLogout()                                 { Account.getInstance().logout(); }
    @FXML protected void onAccountSettings()                        { AccountSettings.openScene(boxContent.getScene().getWindow()); }

    @FXML
    protected void onRicerca() {

        if (scbBigliettoPartenza.getValue() == null || scbBigliettoDestinazione.getValue() == null || scbBigliettoDestinazione.getValue() == scbBigliettoPartenza.getValue()){
            setAlert("Selezionare stazione di partenza e/o destinazione validi!");
            return;
        }

        if(dtpBigliettoPartenza.getValue() == null || tmpBigliettoAndata.getValue() == null || (tgsBigliettoAR.isSelected() &&
                dtpBigliettoRitorno.getValue() == null) || (tgsBigliettoAR.isSelected() && tmpBigliettoRitorno.getValue() == null)
            || (tmpBigliettoAndata.getValue().isBefore(LocalTime.now()) && dtpBigliettoPartenza.getValue().isBefore(LocalDate.now()))
                || (tmpBigliettoAndata.getValue().isBefore(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)) && dtpBigliettoPartenza.getValue().equals(LocalDate.now()))
                || (tgsBigliettoAR.isSelected() && tmpBigliettoRitorno.getValue().isBefore(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)) && dtpBigliettoRitorno.getValue().isBefore(LocalDate.now()))
                || (tgsBigliettoAR.isSelected() && tmpBigliettoRitorno.getValue().isBefore(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)) && dtpBigliettoRitorno.getValue().equals(LocalDate.now()))){
            setAlert("Inserire data e/o orario di partenza e/o ritorno");
            return;
        }

        boxLoading.setVisible(true);
        boxContent.setDisable(true);
        Task<Ricerca> task = new Task<>() {
            @Override
            public Ricerca call() {
                int partenzaId = (scbBigliettoPartenza.getValue()).getStopId();
                int destinazioneId = (scbBigliettoDestinazione.getValue()).getStopId();
                LocalDateTime data = LocalDateTime.of(dtpBigliettoPartenza.getValue(), tmpBigliettoAndata.getValue());

                Ricerca search = new Ricerca(partenzaId, destinazioneId, data);
                if (tgsBigliettoAR.isSelected()){
                    search.setAndataRitorno(true);
                    search.setDataRitorno(LocalDateTime.of(dtpBigliettoRitorno.getValue(), tmpBigliettoRitorno.getValue()));
                }

                search.setNumAdulti((int)spnBigliettoAdulti.getValue());
                search.setNumRagazzi((int)spnBigliettoRagazzi.getValue());
                search.setNumBambini((int)spnBigliettoBambini.getValue());
                search.setNumAnimali((int)spnBigliettoAnimali.getValue());

                search.eseguiRicerca();
                return search;
            }
        };

        task.setOnSucceeded(e -> {
            boxLoading.setVisible(false);
            boxContent.setDisable(false);
            try {
                RicercaView.openScene((Ricerca) e.getSource().getValue(), (Stage) boxContent.getScene().getWindow());
            }catch (NoSuchElementException event){
                setAlert("Viaggio inesistente!");
            }
        });
        new Thread(task).start();
    }

    @FXML
    protected void onVoucherCheckout(){
        if(cmbVoucherValore.getValue() == null){
            setAlert("Impossibile effettuare il checkout. Scegliere il valore del voucher!");
            return;
        }
    }

    @FXML
    protected void onLogin() throws IOException {
        Login.open((boxContent).getScene().getWindow());
    }

    @FXML
    protected void onRicercaSelected(){
        tabPaneRicerca.setVisible(true);
        tabPaneRimborso.setVisible(false);
        txtRimborsoTitoloID.setText("");
        lblRimborsoOK.setVisible(false);
        lblErroreRimborsoEmpty.setVisible(false);
        lblErroreRimborso.setVisible(false);
        txtRimborsoTitoloID.setStyle("-fx-border-color: #cccccc");

    }
    @FXML
    protected void onRimborsoSelected(){
        tabPaneRicerca.setVisible(false);
        tabPaneRimborso.setVisible(true);
    }

    @FXML
    protected void onRimborso() throws Exception {
        // todo aggiungere controlli dopo parte grafica

        if (txtRimborsoTitoloID.getText().isEmpty())
        {
            lblErroreRimborsoEmpty.setVisible(true);
            txtRimborsoTitoloID.setStyle("-fx-border-color: #d70000");
            isIdBigliettoOK=false;
        }
        if (!(Rimborso.checkIdBiglietto(txtRimborsoTitoloID.getText()))) {
            lblErroreRimborso.setVisible(true);
            txtRimborsoTitoloID.setStyle("-fx-border-color: #d70000");
            isIdBigliettoOK=false;
        }

        if(isIdBigliettoOK) {
            Rimborso r = new Rimborso(txtRimborsoTitoloID.getText());
            VoucherService voucherService = new VoucherService();
            VoucherEntity v = r.getRimborso();
            if (v==null){
                setAlert("Impossibile richiedere un rimborso perché la data di partenza\nè incompatibile con le modalità di rimborso!");
                return;
            }
                else
            voucherService.persist(v);

            onScaricaBigliettoPDF(v);
            isIdBigliettoOK=false;

        }

    }

    private void checkIdRealTime(){

        txtRimborsoTitoloID.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Utils.checkDatiGenerico(txtRimborsoTitoloID.getText())) {
                lblErroreRimborso.setVisible(false);
                lblErroreRimborsoEmpty.setVisible(false);
                btnRichiestaRimborso.setDisable(false);
                txtRimborsoTitoloID.setStyle("-fx-border-color: #cccccc");
            }
            else {
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
            if ( (lblErroreRimborso.isVisible() || lblErroreRimborsoEmpty.isVisible()))
            {
                btnRichiestaRimborso.setDisable(true);
                isIdBigliettoOK = false;
            }
            else
                isIdBigliettoOK = true;

        });
    }

    @FXML
    protected void onScaricaBigliettoPDF(VoucherEntity voucherEntity) throws Exception {
        fillPDF(voucherEntity);

        File biglietto = new File(TicketBuilder.DEST); //biglietto in folder temporanea

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Scegli dove salvare il titolo di viaggio");
        fileChooser.setInitialFileName(voucherEntity.getId());

        File destin = new File(fileChooser.showSaveDialog(new Stage()).getAbsolutePath().concat(".pdf"));
        TicketBuilder.copy(biglietto, destin);

        // todo aggiungere controllo su getAbsPath

        lblRimborsoOK.setVisible(true);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
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

    private void fillPDF(VoucherEntity voucherEntity) throws Exception {
        titoloViaggio = new TicketBuilder(voucherEntity.getId(), String.valueOf(voucherEntity.getPrezzo()));

        titoloViaggio.createPdf(voucherEntity.getId());
    }



}


