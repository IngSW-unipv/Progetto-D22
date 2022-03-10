package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.RicercaController;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RicercaView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("ricercaView/ricercaView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);


        List<Connection> cambi = new ArrayList<>();

        // Per prototipi più veloci

        cambi.add(new Connection(367, 3053,25500, 25980, 14158963, 14158963));
        cambi.add(new Connection(3053, 2036,26040, 26340, 14158963, 14158963));
        cambi.add(new Connection(2036, 1858,26400, 26820, 14158963, 14158963));
        cambi.add(new Connection(1858, 406, 26880, 27120, 14156467, 14156467));





        Viaggio viaggio1 = new Viaggio();
        viaggio1.setCambi(cambi);
        viaggio1.setNumAdulti(1);

        Viaggio viaggio2 = new Viaggio();
        viaggio2.setCambi(cambi);
        viaggio2.setNumAdulti(2);

        List<Viaggio> viaggi = new ArrayList<>();

        viaggi.add(viaggio2);
        viaggi.add(viaggio1);

        Ricerca ricerca = new Ricerca(0,0, LocalDateTime.now());
        ricerca.setRisultatiAndata(viaggi);

        ((RicercaController) fxmlLoader.getController()).setRicerca(ricerca);
        stage.setTitle("RicercaView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openWindow(Ricerca ricerca, Window owner){
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

    public static void openScene(Ricerca ricerca, Window owner){
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
