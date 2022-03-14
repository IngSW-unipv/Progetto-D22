package it.unipv.po.aioobe.trenissimo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Main class che gestisce il render del file splashscreen.fxml
 *
 * @see Application
 * @see it.unipv.po.aioobe.trenissimo.view.splashScreen
 */

public class Splashscreen extends Application {

    /**
     * Lancia il render della parte grafica
     *
     * @param args argomenti da linea di comando
     * @see #launch(String...)
     * @see #start(Stage)
     */
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        launch(args);
    }

    /**
     * Risponde alla chiamata del main
     *
     * @param stage stage che contiene l'elemento che ha chiamato il metodo
     * @throws IOException
     * @see #launch(String...)
     * @see Application
     * @see Splashscreen
     */
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        FXMLLoader fxmlLoader = new FXMLLoader(Splashscreen.class.getResource("splashScreen/splashscreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 380);
        stage.setTitle("Trenissimo");
        stage.centerOnScreen();
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
