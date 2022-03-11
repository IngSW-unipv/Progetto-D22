package it.unipv.po.aioobe.trenissimo.view;


import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ViaggioPreferitoControl extends VBox {

    @FXML private Label lblOrario;
    @FXML private Label lblPartenza;
    @FXML private Label lblArrivo;
    @FXML private Label lblCambi;
    @FXML private Label lblAdulti;
    @FXML private Label lblRagazzi;
    @FXML private Label lblBambini;
    @FXML private Label lblAnimali;

    @FXML private Button btnAcquista;

    @FXML private VBox boxChanges;
    @FXML private VBox boxChangesContainer;
    @FXML private FontIcon icoChanges;


    private ViaggiPreferitiEntity viaggio;


    public ViaggioPreferitoControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountSettingsView/viaggioPreferitoControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ViaggioPreferitoControl(ViaggiPreferitiEntity viaggiPreferiti) {
        this();
        setViaggio(viaggiPreferiti);
    }

    public ViaggiPreferitiEntity getViaggio() {
        return viaggio;
    }

    public void setViaggio(ViaggiPreferitiEntity viaggioPreferito) {
        this.viaggio = viaggioPreferito;

        lblOrario.textProperty().setValue(viaggio.getOra().toString());
        lblPartenza.textProperty().setValue(viaggio.getStazionePartenza());
        lblArrivo.textProperty().setValue(viaggio.getStazioneArrivo());
        lblCambi.textProperty().setValue(viaggio.getnCambi() + " cambi");
        lblAdulti.textProperty().setValue(viaggio.getnAdulti().toString() );
        lblRagazzi.textProperty().setValue(viaggio.getnRagazzi().toString() );
        lblBambini.textProperty().setValue(viaggio.getnBambini().toString() );
        lblAnimali.textProperty().setValue(viaggio.getnAnimali().toString() );

    }

    @FXML
    protected void onAcquista(){
        var lista = CachedStopsService.getInstance().findAll();
        var partenza = lista.stream().filter(x -> x.getStopName().equals(lblPartenza.getText())).toList().get(0);
        var arrivo = lista.stream().filter(x -> x.getStopName().equals(lblArrivo.getText())).toList().get(0);



        int partenzaId = partenza.getStopId();
        int destinazioneId = arrivo.getStopId();
        LocalDateTime data = LocalDateTime.now();

        Ricerca search = new Ricerca(partenzaId, destinazioneId, data);
        search.setNumAdulti(Integer.parseInt(lblAdulti.getText()));
        search.setNumRagazzi(Integer.parseInt(lblRagazzi.getText()));
        search.setNumBambini(Integer.parseInt(lblBambini.getText()));
        search.setNumAnimali(Integer.parseInt(lblAnimali.getText()));

       search.eseguiRicerca();
       var viaggi = search.getRisultatiAndata().stream().filter(x -> x.getOrarioPartenza()== Utils.timeToSeconds(lblOrario.getText())).toList();

       AcquistoView.openScene(lblArrivo.getScene().getWindow(), viaggi);



        // todo metodo per il tasto acquista in "tab" viaggi preferiti
    }


}
