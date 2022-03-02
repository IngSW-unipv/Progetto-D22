package it.unipv.po.aioobe.trenissimo.controller;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ViaggioAlt;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.CSASearch;
import it.unipv.po.aioobe.trenissimo.view.Login;
import it.unipv.po.aioobe.trenissimo.view.RicercaView;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private DatePicker dtpBigliettoPartenza;

    @FXML
    private SearchableComboBox scbBigliettoPartenza;

    @FXML
    private SearchableComboBox scbBigliettoDestinazione;

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
    private Label lblBigliettoRitorno;


    @FXML private Label lblNumAdulti;
    @FXML private Label lblNumRagazzi;
    @FXML private Label lblNumBambini;
    @FXML private Label lblNumAnimali;

    @FXML private Spinner spnBigliettoAdulti;
    @FXML private Spinner spnBigliettoRagazzi;
    @FXML private Spinner spnBigliettoBambini;
    @FXML private Spinner spnBigliettoAnimali;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        spnBigliettoAdulti.valueProperty().addListener((obs,oldV,newV) -> {lblNumAdulti.setText(newV.toString());});
        spnBigliettoRagazzi.valueProperty().addListener((obs,oldV,newV) -> {lblNumRagazzi.setText(newV.toString());});
        spnBigliettoBambini.valueProperty().addListener((obs,oldV,newV) -> {lblNumBambini.setText(newV.toString());});
        spnBigliettoAnimali.valueProperty().addListener((obs,oldV,newV) -> {lblNumAnimali.setText(newV.toString());});

        dtpBigliettoPartenza.setValue(LocalDate.now());
        tmpBigliettoAndata.setValue(LocalTime.now());

        var result = CachedStopsService.getInstance().findAll().stream().sorted(Comparator.comparing(StopsEntity::getStopName)).toList();

        scbBigliettoPartenza.setItems(FXCollections.observableArrayList(result));
        scbBigliettoPartenza.setConverter(new StringConverter<StopsEntity>() {
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

        scbBigliettoDestinazione.setItems(FXCollections.observableArrayList(result));
        scbBigliettoDestinazione.setConverter(new StringConverter<StopsEntity>() {
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

        tgsBigliettoAR.selectedProperty().addListener((obs, oldVal, newVal) -> {
            dtpBigliettoRitorno.setDisable(!newVal);
            tmpBigliettoRitorno.setDisable(!newVal);
            lblBigliettoRitorno.setDisable(!newVal);
        });

    }

    @FXML
    protected void onRicerca() {
        boxLoading.setVisible(true);
        boxContent.setDisable(true);
        Task<List<ViaggioAlt>> task = new Task<>() {
            @Override
            public List<ViaggioAlt> call() {
                int partenzaId = ((StopsEntity) scbBigliettoPartenza.getValue()).getStopId();
                int destinazioneId = ((StopsEntity) scbBigliettoDestinazione.getValue()).getStopId();

                CSASearch search = new CSASearch();

                return search.eseguiRicerca(partenzaId, destinazioneId, tmpBigliettoAndata.getValue().toSecondOfDay());
            }
        };

        task.setOnSucceeded(e -> {
            boxLoading.setVisible(false);
            boxContent.setDisable(false);
            RicercaView.openScene((List<ViaggioAlt>) e.getSource().getValue(), (Stage) boxContent.getScene().getWindow());

//            ((Stage)boxLoading.getScene().getWindow()).setScene()

        });
        new Thread(task).start();
    }

    @FXML
    protected void onLogin() throws IOException {
        Login.open();
    }
}
