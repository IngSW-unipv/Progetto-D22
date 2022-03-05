package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.RicercaController;
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
import java.util.ArrayList;
import java.util.List;

public class RicercaView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("ricercaView/ricercaView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);


        List<Connection> cambi = new ArrayList<>();

        // Per prototipi più veloci
        cambi.add(new Connection(964, 2518, 22020, 22260, 14156298, 14156298));
        cambi.add(new Connection(2518, 746, 22320, 22620, 14156298, 14156298));
        cambi.add(new Connection(746, 588, 22800, 23100, 14158830, 14158830));
        cambi.add(new Connection(588, 2143, 23160, 23340, 14158830, 14158830));
        cambi.add(new Connection(2143, 736, 23400, 23580, 14158830, 14158830));
        cambi.add(new Connection(736, 737, 23640, 23940, 14158830, 14158830));
        cambi.add(new Connection(737, 1039, 24000, 24240, 14158830, 14158830));
        cambi.add(new Connection(1039, 2046, 24300, 24600, 14158830, 14158830));
        cambi.add(new Connection(2046, 1720, 24660, 26100, 14158830, 14158830));
        cambi.add(new Connection(1720, 1718, 26820, 27180, 14157526, 14157526));
        cambi.add(new Connection(1718, 3468, 27240, 27390, 14157526, 14157526));
        cambi.add(new Connection(3468, 1723, 27420, 27570, 14157526, 14157526));
        cambi.add(new Connection(1723, 1719, 27600, 27690, 14157526, 14157526));
        cambi.add(new Connection(1719, 1714, 27720, 27840, 14157526, 14157526));
        cambi.add(new Connection(1714, 1713, 27900, 28020, 14157526, 14157526));
        cambi.add(new Connection(1713, 1707, 28080, 28440, 14157526, 14157526));
        cambi.add(new Connection(1707, 5048, 28470, 29190, 14155665, 14155665));
        cambi.add(new Connection(5048, 5314, 29610, 30420, 14158093, 14158093));
        cambi.add(new Connection(5314, 5318, 30510, 31140, 14158093, 14158093));
        cambi.add(new Connection(5318, 5319, 31200, 31500, 14158093, 14158093));


        Viaggio viaggio = new Viaggio();
        viaggio.setCambi(cambi);
        List<Viaggio> viaggi = new ArrayList<>();
        viaggi.add(viaggio);
        viaggi.add(viaggio);



        ((RicercaController) fxmlLoader.getController()).setViaggi(viaggi);
        stage.setTitle("RicercaView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openWindow(List<Viaggio> viaggi, Window owner){
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
        ((RicercaController) fxmlLoader.getController()).setViaggi(viaggi);
        stage.setTitle("RicercaView");
        stage.setScene(scene);
        stage.show();
    }

    public static void openScene(List<Viaggio> viaggi, Window owner){
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("ricercaView/ricercaView.fxml"));

        Parent scene = null;
        try {
            scene = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ((RicercaController) fxmlLoader.getController()).setViaggi(viaggi);
        owner.getScene().setRoot(scene);
    }
}
