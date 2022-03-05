package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.RicercaController;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;

public class HomePage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("homePage/homePage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 800);
        stage.setResizable(false);
        stage.setTitle("Trenissimo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openScene(Window owner){
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("homePage/homePage-view.fxml"));

        Parent scene = null;
        try {
            scene = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        owner.getScene().setRoot(scene);
    }
}
