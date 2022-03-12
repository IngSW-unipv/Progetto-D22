package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.function.Predicate;

public class ViaggioControl extends VBox {
    @FXML private Label lblCompanyId;
    @FXML private Label lblDepartureTime;
    @FXML private Label lblDepartureStation;
    @FXML private Label lblArrivalTime;
    @FXML private Label lblArrivalStation;
    @FXML private Label lblTravelTime;
    @FXML private Label lblChanges;
    @FXML private Label lblPrice;

    @FXML private VBox boxChanges;
    @FXML private VBox boxChangesContainer;
    @FXML private FontIcon icoChanges;
    @FXML private Button bntAddToCart;

    private Viaggio viaggio;
    private Callback<Void,Void> onSelected;


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

    public ViaggioControl(Viaggio viaggio, Callback<Void,Void> onSelected) {
        this();
        this.onSelected = onSelected;
        if (onSelected == null){
            icoChanges.setManaged(false);
            icoChanges.setVisible(false);
            bntAddToCart.setManaged(false);
            bntAddToCart.setVisible(false);
        }
        setViaggio(viaggio);
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public void setViaggio(Viaggio viaggio) {
        this.viaggio = viaggio;

        lblCompanyId            .textProperty().setValue("Trenissimo");
        lblDepartureTime        .textProperty().setValue(Utils.secondsToTime(viaggio.getOrarioPartenza(), false));
        lblDepartureStation     .textProperty().setValue(viaggio.getStazionePartenza().getStopName());
        lblArrivalTime          .textProperty().setValue(Utils.secondsToTime(viaggio.getOrarioArrivo(), false));
        lblArrivalStation       .textProperty().setValue(viaggio.getStazioneArrivo().getStopName());
        lblTravelTime           .textProperty().setValue(((int) viaggio.getDurata() / 60) + " mins");
        lblChanges              .textProperty().setValue(viaggio.getNumeroCambi() + " cambi");
        lblPrice                .textProperty().setValue(viaggio.getPrezzoTotString()+"€");
    }

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

    @FXML
    protected void onAddToCard() {
        onSelected.call(null);
    }

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
