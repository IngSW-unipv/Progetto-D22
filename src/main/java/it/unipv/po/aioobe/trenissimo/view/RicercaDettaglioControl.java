package it.unipv.po.aioobe.trenissimo.view;

import com.jfoenix.controls.JFXTimePicker;
import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroOrario;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroOrdina;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroPrezzo;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.IFiltro;
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
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Main class che gestisce il render del file ricercaDettaglioControl.fxml
 * è anche il controller di se stesso
 *
 * @author ArrayIndexOutOfBoundsException
 * @see BorderPane
 * @see Viaggio
 * @see IFiltro
 * @see FiltroOrdina
 * @see it.unipv.po.aioobe.trenissimo.view.ricercaDettaglioControl
 */
public class RicercaDettaglioControl extends BorderPane {
    private final ObservableList<Viaggio> viaggi;
    private final ObservableList<IFiltro> filtri;
    @FXML
    private VBox boxAndata;
    @FXML
    private RangeSlider rngPrezzo;
    @FXML
    private JFXTimePicker tmpPartenza;
    @FXML
    private JFXTimePicker tmpArrivo;
    @FXML
    private SegmentedButton sbtFiltro;
    private Callback<Viaggio, Void> onAddToCart;


    private FiltroOrdina.Criterio criterioOrdine;

    /**
     * Controller per la visualizzazione di ricercaDettaglioControl.fxml
     *
     * @see FiltroOrdina
     * @see Viaggio
     * @see IFiltro
     */
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

        rngPrezzo.highValueChangingProperty().addListener((obs, old, newV) -> {
            if (!newV) {
                updateFiltri();
            }
        });
        rngPrezzo.lowValueChangingProperty().addListener((obs, old, newV) -> {
            if (!newV) {
                updateFiltri();
            }
        });

        tmpPartenza.valueProperty().addListener((obs, old, newV) -> updateFiltri());
        tmpArrivo.valueProperty().addListener((obs, old, newV) -> updateFiltri());

        tmpPartenza.set24HourView(true);
        tmpArrivo.set24HourView(true);

        tmpPartenza.setValue(LocalTime.MIN);
        tmpArrivo.setValue(LocalTime.MAX);

        viaggi.addListener((ListChangeListener<Viaggio>) c -> updateList());
        filtri.addListener((ListChangeListener<IFiltro>) c -> updateList());

        sbtFiltro.getButtons().setAll(Arrays.stream(FiltroOrdina.Criterio.values()).map(x -> {
            ToggleButton tb = new ToggleButton(x.nome);
            tb.setUserData(x);
            tb.setOnAction((e) -> onOrdina());
            return tb;
        }).toList());
    }


    /**
     * Ordina i viaggi visualizzati
     *
     * @see FiltroOrdina
     */
    private void onOrdina() {
        if (sbtFiltro.getButtons().stream().filter(ToggleButton::isSelected).findAny().isEmpty()) {
            sbtFiltro.getButtons().stream().findFirst().get().setSelected(true);
        }
        criterioOrdine = (FiltroOrdina.Criterio) sbtFiltro.getButtons().stream().filter(ToggleButton::isSelected).findAny().get().getUserData();
        updateFiltri();
    }

    /**
     * Applica alla lista di viaggi i filtri all'interno della lista filtri
     *
     * @param viaggi lista di viaggi da ordinare
     * @return lista di viaggi filtrati
     * @see #filtri
     * @see Viaggio
     * @see IFiltro
     */
    private List<Viaggio> filtra(List<Viaggio> viaggi) {
        List<Viaggio> viaggiFiltrati = viaggi;
        for (IFiltro filtro : filtri) {
            viaggiFiltrati = filtro.esegui(viaggiFiltrati);
        }
        return viaggiFiltrati;
    }

    /**
     * Aggiorna la lista dei viaggi mostrati
     */
    private void updateList() {
        boxAndata.getChildren().setAll(filtra(viaggi).stream().map(x -> new ViaggioControl(x, param -> {
            onAddToCart.call(x);
            return null;
        })).toList());
    }

    /**
     * Aggiorna la lista dei filtri nel caso presenti altrimenti applica quelli di default
     * (per data di partenza crescente)
     *
     * @see IFiltro
     * @see FiltroPrezzo
     * @see FiltroOrdina
     * @see FiltroOrario
     */
    private void updateFiltri() {
        List<IFiltro> newFiltri = new ArrayList<>();
        newFiltri.add(new FiltroPrezzo(rngPrezzo.getLowValue(), rngPrezzo.getHighValue()));
        if (criterioOrdine != null) newFiltri.add(new FiltroOrdina(criterioOrdine));
        if (tmpPartenza.getValue() != null && tmpArrivo.getValue() != null)
            newFiltri.add(new FiltroOrario(tmpPartenza.getValue().toSecondOfDay(), tmpArrivo.getValue().toSecondOfDay()));
        filtri.setAll(newFiltri);
    }

    /**
     * Setta i viaggi visualizzabili e i limiti dello slider prezzo
     *
     * @param viaggi      lista di viaggi da mostrare
     * @param oraPartenza orario dal quale si vogliono iniziare a vedere i viaggi
     * @param onAddToCart callback di acquisto viaggio
     * @see #rngPrezzo
     * @see #updateFiltri()
     */
    public void setViaggi(@NotNull List<Viaggio> viaggi, LocalTime oraPartenza, Callback<Viaggio, Void> onAddToCart) {
        this.onAddToCart = onAddToCart;
        rngPrezzo.setMax(Utils.ceil(viaggi.stream().max(Comparator.comparing(Viaggio::getPrezzoTot)).get().getPrezzoTot(), -1));
        rngPrezzo.setMin(Utils.floor(viaggi.stream().min(Comparator.comparing(Viaggio::getPrezzoTot)).get().getPrezzoTot(), -1));
        rngPrezzo.setLowValue(rngPrezzo.getMin());
        rngPrezzo.setHighValue(rngPrezzo.getMax());
        tmpPartenza.setValue(oraPartenza);
        updateFiltri();
        this.viaggi.addAll(viaggi);
    }

}
