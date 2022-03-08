package it.unipv.po.aioobe.trenissimo.view;


import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.swing.text.html.ImageView;
import java.io.IOException;

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

}
