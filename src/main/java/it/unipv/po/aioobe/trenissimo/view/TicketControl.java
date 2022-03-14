package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Main class che gestisce il render del file ticketControl.fxml
 * è anche il controller di se stesso
 *
 * @author ArrayIndexOutOfBoundsException
 * @see AnchorPane
 * @see it.unipv.po.aioobe.trenissimo.view.ticketControl
 */

public class TicketControl extends AnchorPane {
    @FXML
    private Label lblDepartureTime;
    @FXML
    private Label lblDepartureStation;
    @FXML
    private Label lblArrivalTime;
    @FXML
    private Label lblArrivalStation;
    @FXML
    private Label lblTravelTime;
    @FXML
    private Label lblPrice;

    @FXML
    private Label lblNumAdulti;
    @FXML
    private Label lblNumRagazzi;
    @FXML
    private Label lblNumBambini;
    @FXML
    private Label lblNumAnimali;

    private Viaggio viaggio;
    private Callback<Void, Void> onRemove;


    /**
     * Costruttore per la visualizzazione di ticketControl.fxml
     */
    public TicketControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticketControl/ticketControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Costruttore per la visualizzazione di ticketControl.fxml
     *
     * @param viaggio  Viaggio da cui estrarre informazioni
     * @param onRemove callback di rimozione biglietto
     */

    public TicketControl(Viaggio viaggio, Callback<Void, Void> onRemove) {
        this();
        this.onRemove = onRemove;
        setViaggio(viaggio);
    }

    /**
     * Ritorna un Viaggio
     *
     * @return viaggio
     */
    public Viaggio getViaggio() {
        return viaggio;
    }

    /**
     * Raccoglie i dati di un Viaggio
     *
     * @param viaggio
     */
    public void setViaggio(@NotNull Viaggio viaggio) {
        this.viaggio = viaggio;

        lblDepartureTime.setText(Utils.secondsToTime(viaggio.getOrarioPartenza(), false));
        lblDepartureStation.setText(viaggio.getStazionePartenza().getStopName());
        lblArrivalTime.setText(Utils.secondsToTime(viaggio.getOrarioArrivo(), false));
        lblArrivalStation.setText(viaggio.getStazioneArrivo().getStopName());
        lblTravelTime.setText((viaggio.getDurata() / 60) + " mins");
        lblPrice.setText(viaggio.getPrezzoTotString() + "€");

        lblNumAdulti.setText(String.valueOf(viaggio.getNumAdulti()));
        lblNumRagazzi.setText(String.valueOf(viaggio.getNumRagazzi()));
        lblNumBambini.setText(String.valueOf(viaggio.getNumBambini()));
        lblNumAnimali.setText(String.valueOf(viaggio.getNumAnimali()));

        if (viaggio.getNumAdulti() == 0) {
            lblNumAdulti.setManaged(false);
            lblNumAdulti.setVisible(false);
        }
        if (viaggio.getNumRagazzi() == 0) {
            lblNumRagazzi.setManaged(false);
            lblNumRagazzi.setVisible(false);
        }
        if (viaggio.getNumBambini() == 0) {
            lblNumBambini.setManaged(false);
            lblNumBambini.setVisible(false);
        }
        if (viaggio.getNumAnimali() == 0) {
            lblNumAnimali.setManaged(false);
            lblNumAnimali.setVisible(false);
        }

    }

    /**
     * Metodo per eliminare un biglietto di richiamo callback
     */
    @FXML
    protected void onTicketRemove() {
        onRemove.call(null);
    }
}
