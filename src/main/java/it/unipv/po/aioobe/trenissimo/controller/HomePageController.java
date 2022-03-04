package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedRoutesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopTimesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedTripsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ViaggioAlt;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.CSASearch;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.view.RicercaView;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dtpBigliettoPartenza.setValue(LocalDate.now());

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
        });

    }

    @FXML
    protected void onRicerca() {
        boxLoading.setVisible(true);
        boxContent.setDisable(true);
        Task<List<Viaggio>> task = new Task<>() {
            @Override
            public List<Viaggio> call() {
                int partenzaId = ((StopsEntity) scbBigliettoPartenza.getValue()).getStopId();
                int destinazioneId = ((StopsEntity) scbBigliettoDestinazione.getValue()).getStopId();

                CSASearch search = new CSASearch();

                return search.eseguiRicerca(partenzaId, destinazioneId);
            }
        };

        task.setOnSucceeded(e -> {
            boxLoading.setVisible(false);
            boxContent.setDisable(false);
            RicercaView.open((List<Viaggio>) e.getSource().getValue(), (Stage) boxContent.getScene().getWindow());
        });
        new Thread(task).start();
    }
}
