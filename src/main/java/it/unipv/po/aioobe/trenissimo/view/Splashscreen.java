package it.unipv.po.aioobe.trenissimo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Splashscreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
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
