package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.AcquistoController;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;

public class AcquistoView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("acquistoView/acquistoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("AcquistoView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openWindow(Window owner){
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

    public static void openScene(Window owner, List<Viaggio> viaggi){
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
}
