package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import it.unipv.po.aioobe.trenissimo.view.AcquistoView;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.view.RicercaDettaglioControl;
import it.unipv.po.aioobe.trenissimo.view.TicketControl;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Controller class per ricercaDettaglioControl.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @see it.unipv.po.aioobe.trenissimo.view.ricercaDettaglioControl
 * @see javafx.fxml.Initializable
 */
public class RicercaController implements Initializable {

    @FXML
    private HBox boxCart;
    @FXML
    private TabPane tabPane;
    private ObservableList<Viaggio> _carrello;

    /**
     * Metodo d'Inizializzazione
     *
     * @param location
     * @param resources
     * @see #updateCart()
     * @see Viaggio
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _carrello = FXCollections.observableArrayList();
        _carrello.addListener((ListChangeListener<Viaggio>) c -> updateCart());
    }

    /**
     * Permette di gestire la visualizzazione dei viaggi di andata e/o ritorno ricercati
     * @param ricerca
     */
    public void setRicerca(@NotNull Ricerca ricerca) {
        var andataTab = new Tab();
        andataTab.setText("Andata");
        var rdcAndata = new RicercaDettaglioControl();
        rdcAndata.setViaggi(ricerca.getRisultatiAndata(), ricerca.getDataAndata().toLocalTime(), v -> {
            _carrello.add(v);
            return null;
        });
        andataTab.setContent(rdcAndata);
        tabPane.getTabs().add(andataTab);
        if (ricerca.isAndataRitorno()) {
            var ritornoTab = new Tab();
            ritornoTab.setText("Ritorno");
            var rdcRitorno = new RicercaDettaglioControl();
            rdcRitorno.setViaggi(ricerca.getRisultatiRitorno(), ricerca.getDataRitorno().toLocalTime(), v -> {
                _carrello.add(v);
                return null;
            });
            ritornoTab.setContent(rdcRitorno);
            tabPane.getTabs().add(ritornoTab);
        }
    }


    /**
     * Aggiorna il carrello
     *
     * @see TicketControl
     */
    private void updateCart() {
        boxCart.getChildren().setAll(_carrello.stream().map(x -> new TicketControl(x, param -> {
            _carrello.remove(x);
            return null;
        })).toList());
    }

    /**
     * Permette di procedere con l'acquisto dei titoli di viaggio nel carrello, aprendo acquistoView.fxml
     *
     * @see AcquistoView
     * @see Alert
     * @see Stage
     * @see it.unipv.po.aioobe.trenissimo.view.acquistoView
     */
    @FXML
    protected void onAcquisto() {
        if (!_carrello.isEmpty()) {
            AcquistoView.openScene(boxCart.getScene().getWindow(), _carrello);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Trenissimo");
            alert.setHeaderText(null);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(HomePage.class.getResourceAsStream("HomePage/LogoIcona.png"))));
            alert.setContentText("Impossibile effettuare il checkout. Il carrello è vuoto!");
            alert.showAndWait();
        }
    }

    /**
     * Ritorna alla Home Page
     *
     * @see HomePage
     */
    @FXML
    protected void onGoToHomepage() {
        HomePage.openScene(boxCart.getScene().getWindow());
    }
}
