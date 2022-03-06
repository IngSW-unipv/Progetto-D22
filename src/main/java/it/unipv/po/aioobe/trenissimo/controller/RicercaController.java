package it.unipv.po.aioobe.trenissimo.controller;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroOrario;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroPrezzo;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.IFiltro;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.view.ViaggioControl;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.controlsfx.control.RangeSlider;

import java.net.URL;
import java.time.LocalTime;
import java.util.*;

public class RicercaController implements Initializable {
    @FXML private VBox layout;

    @FXML private RangeSlider rngPrezzo;
    @FXML private JFXTimePicker tmpPartenza;
    @FXML private JFXTimePicker tmpArrivo;

    private ObservableList<Viaggio> viaggi;
    private ObservableList<IFiltro> filtri;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viaggi = FXCollections.observableArrayList();
        filtri = FXCollections.observableArrayList();

        rngPrezzo.highValueChangingProperty()   .addListener((obs, old, newV) -> { if (!newV) { updateFiltri(); }});
        rngPrezzo.lowValueChangingProperty()    .addListener((obs, old, newV) -> { if (!newV) { updateFiltri(); }});

        tmpPartenza     .valueProperty().addListener((obs, old, newV) -> updateFiltri());
        tmpArrivo       .valueProperty().addListener((obs, old, newV) -> updateFiltri());

        tmpPartenza     .set24HourView(true);
        tmpArrivo       .set24HourView(true);

        tmpPartenza .setValue(LocalTime.MIN);
        tmpArrivo   .setValue(LocalTime.MAX);

        viaggi.addListener((ListChangeListener<Viaggio>) c -> updateList());
        filtri.addListener((ListChangeListener<IFiltro>) c -> updateList());
    }

    public void setViaggi(List<Viaggio> viaggi) {
        rngPrezzo.setMax(Utils.ceil     (viaggi.stream().max(Comparator.comparing(Viaggio::getPrezzo)).get().getPrezzo(),-1));
        rngPrezzo.setMin(Utils.floor    (viaggi.stream().min(Comparator.comparing(Viaggio::getPrezzo)).get().getPrezzo(),-1));
        rngPrezzo.setLowValue(rngPrezzo.getMin());
        rngPrezzo.setHighValue(rngPrezzo.getMax());
        this.viaggi.addAll(viaggi);
    }

    private List<Viaggio> filtra(List<Viaggio> viaggi){
        List<Viaggio> viaggiFiltrati = viaggi;
        for (IFiltro filtro : filtri) {
            viaggiFiltrati = filtro.esegui(viaggiFiltrati);
        }
        return viaggiFiltrati;
    }

    private void updateList(){
        layout.getChildren().setAll(filtra(viaggi).stream().map(ViaggioControl::new).toList());
    }

    private void updateFiltri(){
        List<IFiltro> newFiltri = new ArrayList<>();
        newFiltri.add(new FiltroPrezzo(rngPrezzo.getLowValue(),rngPrezzo.getHighValue()));
        if (tmpPartenza.getValue() != null && tmpArrivo.getValue() != null) newFiltri.add(new FiltroOrario(tmpPartenza.getValue().toSecondOfDay(),tmpArrivo.getValue().toSecondOfDay()));
        filtri.setAll(newFiltri);
    }

    @FXML
    protected void onGoToHomepage(){
        HomePage.openScene(layout.getScene().getWindow());
    }
}
