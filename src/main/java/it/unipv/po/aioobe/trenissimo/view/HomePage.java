package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.AcquistoVoucherController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Main class che gestisce il render del file homePage-view.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @see Application
 * @see it.unipv.po.aioobe.trenissimo.view.homePage
 */

public class HomePage extends Application {

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
     * @see HomePage
     * @see AcquistoVoucherController
     */
    public static void openScene(Window owner) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("homePage/homePage-view.fxml"));

        Parent scene = null;
        try {
            scene = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("homePage/homePage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 800);
        stage.setResizable(false);
        stage.setTitle("Trenissimo");
        Image img = new Image(Objects.requireNonNull(HomePage.class.getResourceAsStream("homePage/LogoIcona.png")));
        stage.getIcons().add(img);
        stage.setScene(scene);
        stage.show();
    }
}
