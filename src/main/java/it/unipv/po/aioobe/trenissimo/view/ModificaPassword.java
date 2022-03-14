package it.unipv.po.aioobe.trenissimo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Main class che gestisce il render del file modificaPassword-view.fxml
 *
 * @see it.unipv.po.aioobe.trenissimo.view.acquistoVoucher
 */
public class ModificaPassword {

    /**
     * Risponde alle chiamate esterne di altri componenti
     *
     * @param owner finestra che contiene l'elemento che ha chiamato il metodo
     * @throws IOException
     * @see ModificaPassword
     */
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