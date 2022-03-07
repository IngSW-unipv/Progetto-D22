package it.unipv.po.aioobe.trenissimo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ModificaPassword {

    public static void open(Window owner) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ModificaPassword.class.getResource("modificaPassword/modificaPassword-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 705, 370);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setResizable(false);
        stage.setTitle("Modifica Password");
        stage.setScene(scene);
        stage.show();
    }

}