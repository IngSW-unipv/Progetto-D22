package it.unipv.po.aioobe.trenissimo.controller;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.DurataAbbonamento;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.DurataInterrail;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.NumeroViaggiCarnet;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.ValoreVoucher;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import it.unipv.po.aioobe.trenissimo.view.AccountSettings;
import it.unipv.po.aioobe.trenissimo.view.Login;
import it.unipv.po.aioobe.trenissimo.view.Registrazione;
import it.unipv.po.aioobe.trenissimo.view.RicercaView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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

    protected SearchableComboBox initScb(SearchableComboBox scb){

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

    }

    private void onAccountChange() {;
        grpLoggedIn            .setManaged(Account.getLoggedIn());
        grpLoggedIn            .setVisible(Account.getLoggedIn());

        grpLoggedOut           .setManaged(!Account.getLoggedIn());
        grpLoggedOut           .setVisible(!Account.getLoggedIn());
    }


    @FXML protected void onSignup() throws IOException              { Registrazione.open((boxContent).getScene().getWindow()); }
    @FXML protected void onLogout()                                 { Account.getInstance().logout(); }
    @FXML protected void onAccountSettings()                        { AccountSettings.openScene(boxContent.getScene().getWindow()); }

    @FXML
    protected void onRicerca() {
        boxLoading.setVisible(true);
        boxContent.setDisable(true);
        Task<List<Viaggio>> task = new Task<>() {
            @Override
            public List<Viaggio> call() {
                int partenzaId = (scbBigliettoPartenza.getValue()).getStopId();
                int destinazioneId = (scbBigliettoDestinazione.getValue()).getStopId();
                LocalDateTime data = LocalDateTime.of(dtpBigliettoPartenza.getValue(), tmpBigliettoAndata.getValue());

                Ricerca search = new Ricerca(partenzaId, destinazioneId, data);

                search.setNumAdulti((int)spnBigliettoAdulti.getValue());
                search.setNumRagazzi((int)spnBigliettoRagazzi.getValue());
                search.setNumBambini((int)spnBigliettoBambini.getValue());
                search.setNumBambini((int)spnBigliettoAnimali.getValue());

                System.out.println(search);

                return search.eseguiRicerca();
            }
        };

        task.setOnSucceeded(e -> {
            boxLoading.setVisible(false);
            boxContent.setDisable(false);
            RicercaView.openScene((List<Viaggio>) e.getSource().getValue(), (Stage) boxContent.getScene().getWindow());
        });
        new Thread(task).start();
    }

    @FXML
    protected void onLogin() throws IOException {
        Login.open((boxContent).getScene().getWindow());
    }
}
