package it.unipv.po.aioobe.trenissimo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;

public class Splashscreen extends Application {

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

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
