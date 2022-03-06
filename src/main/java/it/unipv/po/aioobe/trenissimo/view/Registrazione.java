package it.unipv.po.aioobe.trenissimo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Registrazione {

    public static void open(Window owner) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Registrazione.class.getResource("registrazione/registrazione-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 910, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setResizable(false);
        stage.setTitle("Registrazione");
        stage.setScene(scene);
        stage.show();
    }
}