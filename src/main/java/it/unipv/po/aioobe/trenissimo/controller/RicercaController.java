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
import it.unipv.po.aioobe.trenissimo.view.RicercaDettaglioControl;
import it.unipv.po.aioobe.trenissimo.view.TicketControl;
import it.unipv.po.aioobe.trenissimo.view.ViaggioControl;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.SegmentedButton;

import java.net.URL;
import java.time.LocalTime;
import java.util.*;

public class RicercaController implements Initializable {

    @FXML private HBox boxCart;
    @FXML private TabPane tabPane;
    private ObservableList<Viaggio> _carrello;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _carrello = FXCollections.observableArrayList();
        _carrello.addListener((ListChangeListener<Viaggio>) c -> updateCart());



    }

    public void setRicerca(Ricerca ricerca) {
        var andataTab = new Tab();
        andataTab.setText("Andata");
        var rdcAndata = new RicercaDettaglioControl();
        rdcAndata.setViaggi(ricerca.getRisultatiAndata(), ricerca.getDataAndata().toLocalTime(), v-> {
            _carrello.add(v);
            return null;
        });
        andataTab.setContent(rdcAndata);
        tabPane.getTabs().add(andataTab);
        if (ricerca.isAndataRitorno()){
            var ritornoTab = new Tab();
            ritornoTab.setText("Ritorno");
            var rdcRitorno = new RicercaDettaglioControl();
            rdcRitorno.setViaggi(ricerca.getRisultatiRitorno(), ricerca.getDataRitorno().toLocalTime(), v-> {
                _carrello.add(v);
                return null;
            });
            ritornoTab.setContent(rdcRitorno);
            tabPane.getTabs().add(ritornoTab);
        }
    }



    private void updateCart(){
        boxCart.getChildren().setAll(_carrello.stream().map(x -> new TicketControl(x,param -> {
            _carrello.remove(x);
            return null;
        })).toList());
    }



    @FXML
    protected void onGoToHomepage(){
        HomePage.openScene(boxCart.getScene().getWindow());
    }
}
