package it.unipv.po.aioobe.trenissimo.view;


import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;

public class StoricoAcquistoControl extends VBox {

    @FXML private Label lblIDAcquisto;
    @FXML private Label lblNumeroBiglietto;
    @FXML private Label lblDataAcquisto;
    @FXML private Label lblPrezzo;
    @FXML private Label lblDownloadOK;

    @FXML private Button btnBigliettoPDF;
    @FXML private Button btnScarica;


    @FXML private VBox boxChanges;
    @FXML private VBox boxChangesContainer;
    @FXML private FontIcon icoChanges;

    private StoricoAcquistiEntity acquisto;



    public StoricoAcquistoControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountSettingsView/storicoAcquistoControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public StoricoAcquistoControl(StoricoAcquistiEntity acquisto) {
        this();
        setAcquisto(acquisto);
    }

    public StoricoAcquistiEntity getAcquisto() {
        return acquisto;
    }

    public void setAcquisto(StoricoAcquistiEntity acquistiEntity) {
        this.acquisto = acquistiEntity;

        lblIDAcquisto.textProperty().setValue(acquisto.getStoricoAcquistiId().toString());
        lblDataAcquisto.textProperty().setValue(acquisto.getDataAcquisto().toLocalDateTime().toLocalDate().toString());
        lblNumeroBiglietto.textProperty().setValue(acquisto.getTitoloViaggioId());
        lblPrezzo.textProperty().setValue(acquisto.getPrezzo().toString() + "€");

    }


}
