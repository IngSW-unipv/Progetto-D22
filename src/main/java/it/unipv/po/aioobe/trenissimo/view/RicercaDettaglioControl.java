package it.unipv.po.aioobe.trenissimo.view;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroOrario;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroOrdina;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroPrezzo;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.IFiltro;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.SegmentedButton;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RicercaDettaglioControl extends BorderPane {
    @FXML private VBox boxAndata;

    @FXML private RangeSlider rngPrezzo;
    @FXML private JFXTimePicker tmpPartenza;
    @FXML private JFXTimePicker tmpArrivo;
    @FXML private SegmentedButton sbtFiltro;

    private ObservableList<Viaggio> viaggi;
    private ObservableList<IFiltro> filtri;

    private Callback<Viaggio,Void> onAddToCart;


    private FiltroOrdina.Criterio criterioOrdine;

    public RicercaDettaglioControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RicercaDettaglioControl/ricercaDettaglioControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        viaggi = FXCollections.observableArrayList();

        filtri = FXCollections.observableArrayList();

        rngPrezzo.highValueChangingProperty()   .addListener((obs, old, newV) -> { if (!newV) { updateFiltri(); }});
        rngPrezzo.lowValueChangingProperty()    .addListener((obs, old, newV) -> { if (!newV) { updateFiltri(); }});

        tmpPartenza     .valueProperty().addListener((obs, old, newV) -> updateFiltri());
        tmpArrivo       .valueProperty().addListener((obs, old, newV) -> updateFiltri());

        tmpPartenza     .set24HourView(true);
        tmpArrivo       .set24HourView(true);

        tmpPartenza     .setValue(LocalTime.MIN);
        tmpArrivo       .setValue(LocalTime.MAX);

        viaggi.addListener((ListChangeListener<Viaggio>) c -> updateList());
        filtri.addListener((ListChangeListener<IFiltro>) c -> updateList());

        sbtFiltro.getButtons().setAll(Arrays.stream(FiltroOrdina.Criterio.values()).map(x -> {
            ToggleButton tb = new ToggleButton(x.nome);
            tb.setUserData(x);
            tb.setOnAction((e) -> onOrdina());
            return tb;
        }).toList());
    }


    private void onOrdina(){
        if (sbtFiltro.getButtons().stream().filter(ToggleButton::isSelected).findAny().isEmpty()){
            sbtFiltro.getButtons().stream().findFirst().get().setSelected(true);
        }
        criterioOrdine = (FiltroOrdina.Criterio) sbtFiltro.getButtons().stream().filter(ToggleButton::isSelected).findAny().get().getUserData();
        updateFiltri();
    }

    private List<Viaggio> filtra(List<Viaggio> viaggi){
        List<Viaggio> viaggiFiltrati = viaggi;
        for (IFiltro filtro : filtri) {
            viaggiFiltrati = filtro.esegui(viaggiFiltrati);
        }
        return viaggiFiltrati;
    }

    private void updateList(){
        boxAndata.getChildren().setAll(filtra(viaggi).stream().map(x -> new ViaggioControl(x, param -> {
            onAddToCart.call(x);
            return null;
        })).toList());
    }

    private void updateFiltri(){
        List<IFiltro> newFiltri = new ArrayList<>();
        newFiltri.add(new FiltroPrezzo(rngPrezzo.getLowValue(),rngPrezzo.getHighValue()));
        if (criterioOrdine != null) newFiltri.add(new FiltroOrdina(criterioOrdine));
        if (tmpPartenza.getValue() != null && tmpArrivo.getValue() != null) newFiltri.add(new FiltroOrario(tmpPartenza.getValue().toSecondOfDay(),tmpArrivo.getValue().toSecondOfDay()));
        filtri.setAll(newFiltri);
    }

    public void setViaggi(List<Viaggio> viaggi, LocalTime oraPartenza, Callback<Viaggio,Void> onAddToCart) {
        this.onAddToCart = onAddToCart;
        rngPrezzo.setMax(Utils.ceil     (viaggi.stream().max(Comparator.comparing(Viaggio::getPrezzoTot)).get().getPrezzoTot(),-1));
        rngPrezzo.setMin(Utils.floor    (viaggi.stream().min(Comparator.comparing(Viaggio::getPrezzoTot)).get().getPrezzoTot(),-1));
        rngPrezzo.setLowValue(rngPrezzo.getMin());
        rngPrezzo.setHighValue(rngPrezzo.getMax());
        tmpPartenza.setValue(oraPartenza);
        updateFiltri();
        this.viaggi.addAll(viaggi);
    }

}
