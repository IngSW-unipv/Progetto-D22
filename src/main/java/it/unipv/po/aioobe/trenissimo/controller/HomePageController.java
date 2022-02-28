package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedTripsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ViaggioAlt;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.CSASearch;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.ToggleSwitch;

import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
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

        int partenzaId = ((StopsEntity)scbBigliettoPartenza.getValue()).getStopId();
        int destinazioneId = ((StopsEntity)scbBigliettoDestinazione.getValue()).getStopId();

        CSASearch search = new CSASearch();

        var viaggi = search.eseguiRicerca(partenzaId, destinazioneId, 12*3600);

        for(ViaggioAlt v : viaggi) {

            var result = v.getCambi();

            System.out.println("Partenza: " + Utils.secondsToTime(v.getStazionePartenza()) + " - Durata: " + Utils.secondsToTime(v.getDurata()));
            System.out.println("Cambi: " + v.getNumeroCambi());
            for (Connection x : result) {
                var routeFrom = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() == x.departure_station_trip).findFirst().get().getRouteId();
                var routeTo = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() == x.arrival_station_trip).findFirst().get().getRouteId();
                System.out.println(
                        "[" + routeFrom + "] " + CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.departure_station).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.departure_timestamp)
                                + ") -> [" + routeTo + "] " + CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.arrival_station).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.arrival_timestamp) + ")");
            }

            System.out.println("\n\n");

        }
        
        if(tgsBigliettoAR.isSelected()){

            var viaggiR = search.eseguiRicerca(destinazioneId, partenzaId, 15*3600);

            for(ViaggioAlt v : viaggiR) {
                var resultR = v.getCambi();

                System.out.println("Partenza: " + Utils.secondsToTime(v.getStazionePartenza()) + " - Durata: " + Utils.secondsToTime(v.getDurata()));
                System.out.println("Cambi: " + v.getNumeroCambi());
                for (Connection x : resultR) {
                    var routeFrom = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() == x.departure_station_trip).findFirst().get().getRouteId();
                    var routeTo = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() == x.arrival_station_trip).findFirst().get().getRouteId();
                    System.out.println(
                            "[" + routeFrom + "] " + CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.departure_station).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.departure_timestamp)
                                    + ") -> [" + routeTo + "] " + CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.arrival_station).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.arrival_timestamp) + ")");
                }

                System.out.println("\n\n");
            }
        }
    }
}
