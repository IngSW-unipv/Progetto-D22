package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class TicketControl extends AnchorPane {
    @FXML private Label lblDepartureTime;
    @FXML private Label lblDepartureStation;
    @FXML private Label lblArrivalTime;
    @FXML private Label lblArrivalStation;
    @FXML private Label lblTravelTime;
    @FXML private Label lblPrice;

    private Viaggio viaggio;
    private Callback<Void, Void> onRemove;


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

    public TicketControl(Viaggio viaggio, Callback<Void,Void> onRemove) {
        this();
        this.onRemove = onRemove;
        setViaggio(viaggio);
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public void setViaggio(Viaggio viaggio) {
        this.viaggio = viaggio;

        lblDepartureTime        .textProperty().setValue(Utils.secondsToTime(viaggio.getOrarioPartenza(), false));
        lblDepartureStation     .textProperty().setValue(viaggio.getStazionePartenza().getStopName());
        lblArrivalTime          .textProperty().setValue(Utils.secondsToTime(viaggio.getOrarioArrivo(), false));
        lblArrivalStation       .textProperty().setValue(viaggio.getStazioneArrivo().getStopName());
        lblTravelTime           .textProperty().setValue(((int) viaggio.getDurata() / 60) + " mins");
        lblPrice                .textProperty().setValue(String.valueOf(viaggio.getPrezzoTot()));
    }

    @FXML
    protected void onTicketRemove(){
        onRemove.call(null);
    }
}
