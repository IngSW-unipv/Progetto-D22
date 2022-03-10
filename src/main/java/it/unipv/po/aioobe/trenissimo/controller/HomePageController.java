package it.unipv.po.aioobe.trenissimo.controller;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.VoucherService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.ValoreVoucher;
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
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;
import java.net.URL;
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
// todo continuare gestione errori

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

        cmbVoucherValore.setItems(FXCollections.observableArrayList(ValoreVoucher.values()));


        tgsBigliettoAR.selectedProperty().addListener((obs, oldVal, newVal) -> {
            dtpBigliettoRitorno.setDisable(!newVal);
            tmpBigliettoRitorno.setDisable(!newVal);
        });

        Account.loggedInProperty.addListener((obs,old,newV) -> onAccountChange());
        onAccountChange();

        tabPaneRicerca.setVisible(true);
        tabPaneRimborso.setVisible(false);

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
    }
   @FXML
    protected void onRimborsoSelected(){
       tabPaneRicerca.setVisible(false);
       tabPaneRimborso.setVisible(true);
    }

    @FXML
    protected void onRimborso(){
        // todo aggiungere controlli dopo parte grafica
        /*
        if (txtRimborsoTitoloID.getText().isEmpty())
        {
            //lblErroreRimborso.setVisible(true);
            txtRimborsoTitoloID.setStyle("-fx-border-color: #d70000");
        }*/

        Rimborso r = new Rimborso(txtRimborsoTitoloID.getText());
        VoucherService voucherService = new VoucherService();
        voucherService.persist(r.getRimborso());
    }

}
