package it.unipv.po.aioobe.trenissimo;

import it.unipv.po.aioobe.trenissimo.view.Splashscreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Trenissimo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new Splashscreen().start(stage);
    }
}
