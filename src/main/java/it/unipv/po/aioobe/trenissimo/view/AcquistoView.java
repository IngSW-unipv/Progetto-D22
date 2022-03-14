package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.AccountSettingsController;
import it.unipv.po.aioobe.trenissimo.controller.AcquistoController;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

/**
 * Main class che gestisce il render del file acquistoView.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @see Application
 * @see it.unipv.po.aioobe.trenissimo.view.acquistoView
 */
public class AcquistoView extends Application {
    /**
     * Lancia il render della parte grafica
     *
     * @param args argomenti da linea di comando
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Risponde alle chiamate esterne di altri componenti
     *
     * @param owner finestra che contiene l'elemento che ha chiamato il metodo
     * @see Login
     * @see AccountSettingsController
     */
    public static void openWindow(Window owner) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("acquistoView/acquistoView.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("AcquistoView");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Risponde alle chiamate esterne di altri componenti
     *
     * @param owner  finestra che contiene l'elemento che ha chiamato il metodo
     * @param viaggi lista di viaggi da visualizzare
     * @see Viaggio
     * @see HomePage
     * @see AcquistoController
     */
    public static void openScene(Window owner, List<Viaggio> viaggi) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("acquistoView/acquistoView.fxml"));
        Parent scene = null;
        try {
            scene = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((AcquistoController) fxmlLoader.getController()).set_viaggi(viaggi);
        owner.getScene().setRoot(scene);
    }

    /**
     * Risponde alla chiamata del main
     *
     * @param stage stage che contiene l'elemento che ha chiamato il metodo
     * @throws IOException
     * @see #launch(String...)
     * @see AccountSettings
     * @see Application
     */
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("acquistoView/acquistoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("AcquistoView");
        stage.setScene(scene);
        stage.show();
    }
}
