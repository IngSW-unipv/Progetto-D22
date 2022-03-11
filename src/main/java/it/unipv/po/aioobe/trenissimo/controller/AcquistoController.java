package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.CorsaSingola;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.TipoTitoloViaggio;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.view.ViaggioControl;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AcquistoController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private VBox boxViaggi;

    private ObservableList<Viaggio> _viaggi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _viaggi = FXCollections.observableArrayList();
        _viaggi.addListener((ListChangeListener<Viaggio>) c -> {
            boxViaggi.getChildren().setAll(_viaggi.stream().map(x -> new ViaggioControl(x, null)).toList());
        });
    }

    public void set_viaggi(List<Viaggio> viaggi) {
        _viaggi.setAll(viaggi);
    }

    @FXML
    protected void onPaga(){
        List<Acquisto> biglietti = new ArrayList<>();
        for (Viaggio v: _viaggi) {
            biglietti.add(new CorsaSingola(TipoTitoloViaggio.BIGLIETTOCORSASINGOLA, v));
        }
        biglietti.forEach(x -> x.pagare());
        if (Account.getLoggedIn()) {
            biglietti.forEach(x -> x.puntiFedelta(biglietti));
            //metodi per aggiornare i punti fedeltà dal db all'istanza di Account
            String username = Account.getInstance().getUsername();
            Account.getInstance().setAccount(username);
        }

    }

    @FXML
    protected void onGoToHomepage() {
        HomePage.openScene(root.getScene().getWindow());
    }
}
