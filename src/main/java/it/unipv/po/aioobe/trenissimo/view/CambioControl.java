package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ViaggioAlt;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class CambioControl extends VBox {
    @FXML private GridPane boxStart;
    @FXML private GridPane boxSpacer;
    @FXML private GridPane boxEnd;
    @FXML private GridPane boxMiddle;

    @FXML private Label lblStartTime;
    @FXML private Label lblStartStation;
    @FXML private Label lblEndTime;
    @FXML private Label lblEndStation;
    @FXML private Label lblMiddleTime;
    @FXML private Label lblMiddleStation;


    public enum TipoCambio{
        START,
        SPACER,
        END,
        MIDDLE,
        END_LAST
    }


    public CambioControl(Connection cambio, Connection cambioPrev, TipoCambio tipo) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cambioControl/cambioControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boxStart.setVisible(false);
        boxStart.setPrefHeight(0);

        boxSpacer.setVisible(false);
        boxSpacer.setPrefHeight(0);

        boxEnd.setVisible(false);
        boxEnd.setPrefHeight(0);

        boxMiddle.setVisible(false);
        boxMiddle.setPrefHeight(0);

        switch (tipo){
            case START:
                boxStart.setVisible(true);
                boxStart.setPrefHeight(-1);
                lblStartTime.setText(Utils.secondsToTime(cambio.departure_timestamp, false));
                lblStartStation.setText(CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == cambio.departure_station).findFirst().get().getStopName());
                break;
            case SPACER:
                boxSpacer.setVisible(true);
                boxSpacer.setPrefHeight(-1);
                break;
            case END:
                boxEnd.setVisible(true);
                boxEnd.setPrefHeight(-1);
                lblEndTime.setText(Utils.secondsToTime(cambioPrev.arrival_timestamp, false));
                lblEndStation.setText(CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == cambio.departure_station).findFirst().get().getStopName());
                break;
            case MIDDLE:
                boxMiddle.setVisible(true);
                boxMiddle.setPrefHeight(-1);
                lblMiddleTime.setText(Utils.secondsToTime(cambioPrev.arrival_timestamp, false) + "\n" + Utils.secondsToTime(cambio.departure_timestamp, false));
                lblMiddleStation.setText(CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == cambio.departure_station).findFirst().get().getStopName());
                break;
            case END_LAST:
                boxEnd.setVisible(true);
                boxEnd.setPrefHeight(-1);
                lblEndTime.setText(Utils.secondsToTime(cambio.arrival_timestamp, false));
                lblEndStation.setText(CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == cambio.arrival_station).findFirst().get().getStopName());
                break;
        }
    }



}
