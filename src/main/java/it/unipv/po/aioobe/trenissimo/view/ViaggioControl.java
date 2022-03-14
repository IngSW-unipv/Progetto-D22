package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

/**
 * Main class che gestisce il render del file viaggioControl.fxml
 * è anche il controller di se stesso
 *
 * @author ArrayIndexOutOfBoundsException
 * @see VBox
 * @see it.unipv.po.aioobe.trenissimo.view.viaggioControl
 */

public class ViaggioControl extends VBox {
    @FXML
    private Label lblCompanyId;
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
    private Label lblChanges;
    @FXML
    private Label lblPrice;
    @FXML
    private FontIcon icoPreferiti;

    @FXML
    private VBox boxChanges;
    @FXML
    private VBox boxChangesContainer;
    @FXML
    private FontIcon icoChanges;
    @FXML
    private Button bntAddToCart;

    @FXML
    private Button btnAddPreferiti;

    private Viaggio viaggio;
    private Callback<Void, Void> onSelected;


    /**
     * Costruttore per la visualizzazione di viaggioControl.fxml
     */
    public ViaggioControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viaggioControl/viaggioControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Costruttore per la visualizzazione di viaggioControl.fxml
     *
     * @param viaggio    Viaggio da cui estrarre informazioni
     * @param onSelected callback visualizzazione bottone
     */
    public ViaggioControl(Viaggio viaggio, Callback<Void, Void> onSelected) {
        this();
        this.onSelected = onSelected;
        if (onSelected == null) {
            icoChanges.setManaged(false);
            icoChanges.setVisible(false);
            bntAddToCart.setManaged(false);
            bntAddToCart.setVisible(false);
            btnAddPreferiti.setVisible(false);
            btnAddPreferiti.setManaged(false);
        }
        if (!Account.getLoggedIn()) {
            btnAddPreferiti.setVisible(false);
            btnAddPreferiti.setManaged(false);
        }
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
     * Raccoglie i dati di uno Viaggio
     *
     * @param viaggio
     */
    public void setViaggio(@NotNull Viaggio viaggio) {
        this.viaggio = viaggio;

        lblCompanyId.textProperty().setValue("Trenissimo");
        lblDepartureTime.textProperty().setValue(Utils.secondsToTime(viaggio.getOrarioPartenza(), false));
        lblDepartureStation.textProperty().setValue(viaggio.getStazionePartenza().getStopName());
        lblArrivalTime.textProperty().setValue(Utils.secondsToTime(viaggio.getOrarioArrivo(), false));
        lblArrivalStation.textProperty().setValue(viaggio.getStazioneArrivo().getStopName());
        lblTravelTime.textProperty().setValue((viaggio.getDurata() / 60) + " mins");
        lblChanges.textProperty().setValue(viaggio.getNumeroCambi() + " cambi");
        lblPrice.textProperty().setValue(viaggio.getPrezzoTotString() + "€");
    }

    /**
     * Renderizza i cambiamenti avvenuti
     *
     * @see CambioControl
     * @see it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection
     */
    private void renderChanges() {
        var cambi = viaggio.getCambi();

        for (int i = 0; i < cambi.size(); i++) {
            if (i == 0 || cambi.get(i - 1).getArrivalStationTrip() != cambi.get(i).getDepartureStationTrip()) {
                if (i != 0 && (cambi.get(i - 1).getArrivalStationTrip() != cambi.get(i).getDepartureStationTrip())) {
                    boxChanges.getChildren().add(new CambioControl(cambi.get(i), cambi.get(i - 1), CambioControl.TipoCambio.END));
                }
                boxChanges.getChildren().add(new CambioControl(cambi.get(i), null, CambioControl.TipoCambio.START));
                if (i == cambi.size() - 1) {
                    boxChanges.getChildren().add(new CambioControl(cambi.get(i), null, CambioControl.TipoCambio.END_LAST));
                }
                continue;
            }

            if (cambi.get(i).getDepartureStationTrip() == cambi.get(i).getArrivalStationTrip()) {
                boxChanges.getChildren().add(new CambioControl(cambi.get(i), cambi.get(i - 1), CambioControl.TipoCambio.MIDDLE));
                if (i == cambi.size() - 1) {
                    boxChanges.getChildren().add(new CambioControl(cambi.get(i), null, CambioControl.TipoCambio.END_LAST));
                }
                continue;
            }
        }
    }

    /**
     * Aggiunge al carrello
     */
    @FXML
    protected void onAddToCard() {
        onSelected.call(null);
    }

    /**
     * Aggiunge ai Preferiti
     *
     * @see Account
     */
    @FXML
    protected void onAddPreferiti() {
        Account.getInstance().addViaggioPreferito(viaggio);
        icoPreferiti.setIconLiteral("fas-heart");
        btnAddPreferiti.setDisable(true);
    }

    /**
     * Renderizza i cambi, espande e chiude la visualizzazione dei cambi
     */
    @FXML
    protected void onToggleChangeVisibility() {
        if (boxChangesContainer.isVisible()) {
            boxChanges.getChildren().clear();
            boxChangesContainer.setVisible(false);
            boxChangesContainer.setPrefHeight(0);
            icoChanges.setIconLiteral("fas-angle-down");
        } else {
            renderChanges();
            boxChangesContainer.setVisible(true);
            boxChangesContainer.setPrefHeight(-1);
            icoChanges.setIconLiteral("fas-angle-up");
        }

    }

}
