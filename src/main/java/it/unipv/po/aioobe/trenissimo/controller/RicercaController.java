package it.unipv.po.aioobe.trenissimo.controller;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroOrario;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroOrdina;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroPrezzo;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.IFiltro;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.view.TicketControl;
import it.unipv.po.aioobe.trenissimo.view.ViaggioControl;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.SegmentedButton;

import java.net.URL;
import java.time.LocalTime;
import java.util.*;

public class RicercaController implements Initializable {
    @FXML private VBox boxAndata;
    @FXML private VBox boxRitorno;
    @FXML private HBox boxCart;


    @FXML private RangeSlider rngPrezzo;
    @FXML private JFXTimePicker tmpPartenza;
    @FXML private JFXTimePicker tmpArrivo;
    @FXML private SegmentedButton sbtFiltro;

    private ObservableList<Viaggio> _viaggiAndata;
    private ObservableList<Viaggio> _viaggiRitorno;
    private ObservableList<Viaggio> _carrello;
    private ObservableList<IFiltro> filtri;

    private FiltroOrdina.Criterio criterioOrdine;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _viaggiAndata = FXCollections.observableArrayList();
        _viaggiRitorno = FXCollections.observableArrayList();
        _carrello = FXCollections.observableArrayList();
        filtri = FXCollections.observableArrayList();

        rngPrezzo.highValueChangingProperty()   .addListener((obs, old, newV) -> { if (!newV) { updateFiltri(); }});
        rngPrezzo.lowValueChangingProperty()    .addListener((obs, old, newV) -> { if (!newV) { updateFiltri(); }});

        tmpPartenza     .valueProperty().addListener((obs, old, newV) -> updateFiltri());
        tmpArrivo       .valueProperty().addListener((obs, old, newV) -> updateFiltri());

        tmpPartenza     .set24HourView(true);
        tmpArrivo       .set24HourView(true);

        tmpPartenza     .setValue(LocalTime.MIN);
        tmpArrivo       .setValue(LocalTime.MAX);

        _viaggiAndata.addListener((ListChangeListener<Viaggio>) c -> updateList());
        _viaggiRitorno.addListener((ListChangeListener<Viaggio>) c -> updateList());
        _carrello.addListener((ListChangeListener<Viaggio>) c -> updateCart());
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

    public void setRicerca(Ricerca ricerca) {
        rngPrezzo.setMax(Utils.ceil     (ricerca.getRisultatiAndata().stream().max(Comparator.comparing(Viaggio::getPrezzoTot)).get().getPrezzoTot(),-1));
        rngPrezzo.setMin(Utils.floor    (ricerca.getRisultatiAndata().stream().min(Comparator.comparing(Viaggio::getPrezzoTot)).get().getPrezzoTot(),-1));
        rngPrezzo.setLowValue(rngPrezzo.getMin());
        rngPrezzo.setHighValue(rngPrezzo.getMax());
        tmpPartenza.setValue(ricerca.getDataAndata().toLocalTime());
        updateFiltri();
        this._viaggiAndata.addAll(ricerca.getRisultatiAndata());
        if (ricerca.isAndataRitorno()) this._viaggiRitorno.addAll(ricerca.getRisultatiRitorno());
    }


    private List<Viaggio> filtra(List<Viaggio> viaggi){
        List<Viaggio> viaggiFiltrati = viaggi;
        for (IFiltro filtro : filtri) {
            viaggiFiltrati = filtro.esegui(viaggiFiltrati);
        }
        return viaggiFiltrati;
    }

    private void updateList(){
        boxAndata.getChildren().setAll(filtra(_viaggiAndata).stream().map(x -> new ViaggioControl(x, param -> {
            _carrello.add(x);
            return null;
        })).toList());
        boxRitorno.getChildren().setAll(filtra(_viaggiRitorno).stream().map(x -> new ViaggioControl(x, param -> {
            _carrello.add(x);
            return null;
        })).toList());
    }

    private void updateCart(){
        boxCart.getChildren().setAll(filtra(_carrello).stream().map(x -> new TicketControl(x,param -> {
            _carrello.remove(x);
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

    @FXML
    protected void onGoToHomepage(){
        HomePage.openScene(boxAndata.getScene().getWindow());
    }
}
