package it.unipv.po.aioobe.trenissimo;

import it.unipv.po.aioobe.trenissimo.view.Splashscreen;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entrypoint
 */
public class Trenissimo extends Application {

    /**
     * Main entrypoint
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Lancia lo Splashscreen
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Splashscreen().start(stage);
    }

}
