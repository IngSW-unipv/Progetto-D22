package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Gestisce la parte di visualizzazione dei cambi
 * e anche il controller di se stesso
 *
 * @author ArrayIndexOutOfBoundsException
 * @see VBox
 */
public class CambioControl extends VBox {
    @FXML
    private GridPane boxStart;
    @FXML
    private GridPane boxSpacer;
    @FXML
    private GridPane boxEnd;
    @FXML
    private GridPane boxMiddle;

    @FXML
    private Label lblStartTime;
    @FXML
    private Label lblStartStation;
    @FXML
    private Label lblEndTime;
    @FXML
    private Label lblEndStation;
    @FXML
    private Label lblMiddleTime;
    @FXML
    private Label lblMiddleStation;


    /**
     * @param cambio     rappresenta la connection di provenienza (stazione 1)
     * @param cambioPrev rappresenta la connection di arrivo (stazione 2)
     * @param tipo       tipo di connessione
     * @see TipoCambio
     * @see Connection
     * @see Utils
     */
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

        switch (tipo) {
            case START -> {
                boxStart.setVisible(true);
                boxStart.setPrefHeight(-1);
                lblStartTime.setText(Utils.secondsToTime(cambio.getDepartureTimestamp(), false));
                lblStartStation.setText(CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == cambio.getDepartureStation()).findFirst().get().getStopName());
            }
            case SPACER -> {
                boxSpacer.setVisible(true);
                boxSpacer.setPrefHeight(-1);
            }
            case END -> {
                boxEnd.setVisible(true);
                boxEnd.setPrefHeight(-1);
                lblEndTime.setText(Utils.secondsToTime(cambioPrev.getArrivalTimestamp(), false));
                lblEndStation.setText(CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == cambio.getDepartureStation()).findFirst().get().getStopName());
            }
            case MIDDLE -> {
                boxMiddle.setVisible(true);
                boxMiddle.setPrefHeight(-1);
                lblMiddleTime.setText(Utils.secondsToTime(cambioPrev.getArrivalTimestamp(), false) + "\n" + Utils.secondsToTime(cambio.getDepartureTimestamp(), false));
                lblMiddleStation.setText(CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == cambio.getDepartureStation()).findFirst().get().getStopName());
            }
            case END_LAST -> {
                boxEnd.setVisible(true);
                boxEnd.setPrefHeight(-1);
                lblEndTime.setText(Utils.secondsToTime(cambio.getArrivalTimestamp(), false));
                lblEndStation.setText(CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == cambio.getArrivalStation()).findFirst().get().getStopName());
            }
        }
    }


    /**
     * Tipo di nodo
     */
    public enum TipoCambio {
        START, SPACER, END, MIDDLE, END_LAST
    }


}
