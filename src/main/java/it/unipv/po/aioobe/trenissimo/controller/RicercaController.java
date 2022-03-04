package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.viaggio.ViaggioAlt;
import it.unipv.po.aioobe.trenissimo.view.ViaggioControl;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class RicercaController implements Initializable {
    @FXML
    private VBox layout;

    private ObservableList<ViaggioAlt> viaggi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viaggi = FXCollections.observableArrayList();
        viaggi.addListener((ListChangeListener<ViaggioAlt>) c -> {
            layout.getChildren().setAll(c.getList().stream().map(ViaggioControl::new).toList());
        });
    }

    public void setViaggi(List<ViaggioAlt> viaggi) {
        System.out.println("setViaggi");
        this.viaggi.addAll(viaggi);
    }
}
