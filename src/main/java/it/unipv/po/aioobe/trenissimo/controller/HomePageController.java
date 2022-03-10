package it.unipv.po.aioobe.trenissimo.controller;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.CryptographyUtils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.TitoloViaggioService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.VoucherService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.DurataAbbonamento;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.DurataInterrail;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.NumeroViaggiCarnet;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.ToggleSwitch;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML private DatePicker dtpBigliettoPartenza;
    @FXML private ToggleSwitch tgsBigliettoAR;
    @FXML private VBox boxLoading;
    @FXML private VBox boxContent;
    @FXML private DatePicker dtpBigliettoRitorno;
    @FXML private JFXTimePicker tmpBigliettoRitorno;
    @FXML private JFXTimePicker tmpBigliettoAndata;

    @FXML private ComboBox<DurataAbbonamento> cmbAbbonamentoDurata;
    @FXML private ComboBox<ValoreVoucher> cmbVoucherValore;
    @FXML private ComboBox<NumeroViaggiCarnet> cmbCarnetNumeroViaggi;
    @FXML private ComboBox<DurataInterrail> cmbDurataInterrail;

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
    @FXML private SearchableComboBox scbAbbonamentoPartenza;
    @FXML private SearchableComboBox scbAbbonamentoDestinazione;
    @FXML private SearchableComboBox scbCarnetPartenza;
    @FXML private SearchableComboBox scbCarnetDestinazione;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        spnBigliettoAdulti      .valueProperty().addListener((obs,oldV,newV) -> { lblNumAdulti.setText(newV.toString());  });
        spnBigliettoRagazzi     .valueProperty().addListener((obs,oldV,newV) -> { lblNumRagazzi.setText(newV.toString()); });
        spnBigliettoBambini     .valueProperty().addListener((obs,oldV,newV) -> { lblNumBambini.setText(newV.toString()); });
        spnBigliettoAnimali     .valueProperty().addListener((obs,oldV,newV) -> { lblNumAnimali.setText(newV.toString()); });

        dtpBigliettoPartenza.setValue(LocalDate.now());
        tmpBigliettoAndata.setValue(LocalTime.now());

        initScb(scbBigliettoPartenza);
        initScb(scbBigliettoDestinazione);
        initScb(scbAbbonamentoPartenza);
        initScb(scbAbbonamentoDestinazione);
        initScb(scbCarnetPartenza);
        initScb(scbCarnetDestinazione);

        cmbAbbonamentoDurata.setItems(FXCollections.observableArrayList(DurataAbbonamento.values()));
        cmbVoucherValore.setItems(FXCollections.observableArrayList(ValoreVoucher.values()));
        cmbCarnetNumeroViaggi.setItems(FXCollections.observableArrayList(NumeroViaggiCarnet.values()));
        cmbDurataInterrail.setItems(FXCollections.observableArrayList(DurataInterrail.values()));


        tgsBigliettoAR.selectedProperty().addListener((obs, oldVal, newVal) -> {
            dtpBigliettoRitorno.setDisable(!newVal);
            tmpBigliettoRitorno.setDisable(!newVal);
        });

        Account.loggedInProperty.addListener((obs,old,newV) -> onAccountChange());
        onAccountChange();


        onRicercaSelected();

        txtRimborsoTitoloID.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtRimborsoTitoloID.getText())) {
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
            if ( lblErroreRimborso.isVisible() || lblErroreRimborsoEmpty.isVisible())
            {
                btnRichiestaRimborso.setDisable(true);
                isIdBigliettoOK = false;
            }
            else
                isIdBigliettoOK = true;

        });

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Trenissimo");
            alert.setHeaderText(null);
            alert.setContentText("Selezionare stazione di partenza e/o destinazione validi!");

            alert.showAndWait();
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
                search.setNumBambini((int)spnBigliettoAnimali.getValue());

                search.eseguiRicerca();
                return search;
            }
        };

        task.setOnSucceeded(e -> {
            boxLoading.setVisible(false);
            boxContent.setDisable(false);
            RicercaView.openScene((Ricerca) e.getSource().getValue(), (Stage) boxContent.getScene().getWindow());
        });
        new Thread(task).start();
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

        if(isIdBigliettoOK) {
            Rimborso r = new Rimborso(txtRimborsoTitoloID.getText());
            VoucherService voucherService = new VoucherService();
            VoucherEntity v = r.getRimborso();
            voucherService.persist(v);

            onScaricaBigliettoPDF(v);
        }

    }


    @FXML
    protected void onScaricaBigliettoPDF(VoucherEntity voucherEntity) throws Exception {
        fillPDF(voucherEntity);

        File biglietto = new File(TicketBuilder.DEST); //biglietto in folder temporanea

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Scegli dove salvare il titolo di viaggio");
        fileChooser.setInitialFileName(voucherEntity.getVoucherId());

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
            txtRimborsoTitoloID.setText("");
        });
        new Thread(task).start();

    }

    private void fillPDF(VoucherEntity voucherEntity) throws Exception {
            titoloViaggio = new TicketBuilder(voucherEntity.getVoucherId(), String.valueOf(voucherEntity.getValore()));

        titoloViaggio.createPdf(voucherEntity.getVoucherId());
    }



}
