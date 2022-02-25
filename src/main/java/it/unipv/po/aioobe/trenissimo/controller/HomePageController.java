package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.ToggleSwitch;

import java.net.URL;
import java.time.LocalDate;
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

        var result = CachedStopsService.getInstance().findAll();

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

    }
}
