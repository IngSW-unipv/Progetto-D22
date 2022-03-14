package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.RicercaController;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class che gestisce il render del file ricercaView.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @see Application
 * @see it.unipv.po.aioobe.trenissimo.view.ricercaView
 */

public class RicercaView {
    /**
     * Risponde alle chiamate esterne di altri componenti
     *
     * @param ricerca ricerca che si vuole mostrare a video
     * @param owner   finestra che contiene l'elemento che ha chiamato il metodo
     * @see HomePage
     * @see RicercaController
     */
    public static void openWindow(Ricerca ricerca, Window owner) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("ricercaView/ricercaView.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((RicercaController) fxmlLoader.getController()).setRicerca(ricerca);
        stage.setTitle("RicercaView");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Risponde alle chiamate esterne di altri componenti
     *
     * @param ricerca
     * @param owner   finestra che contiene l'elemento che ha chiamato il metodo
     * @see HomePage
     * @see RicercaController
     */
    public static void openScene(Ricerca ricerca, Window owner) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("ricercaView/ricercaView.fxml"));

        Parent scene = null;
        try {
            scene = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ((RicercaController) fxmlLoader.getController()).setRicerca(ricerca);
        owner.getScene().setRoot(scene);
    }
}
