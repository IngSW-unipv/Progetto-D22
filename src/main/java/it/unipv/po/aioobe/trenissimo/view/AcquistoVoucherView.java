package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.AccountSettingsController;
import it.unipv.po.aioobe.trenissimo.controller.AcquistoVoucherController;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.ValoreVoucher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Main class che gestisce il render del file acquistoVoucher.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @see Application
 * @see it.unipv.po.aioobe.trenissimo.view.acquistoVoucher
 */
public class AcquistoVoucherView extends Application {

    /**
     * Lancia il render della parte grafica
     *
     * @param args argomenti da linea di comando
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Risponde alle chiamate esterne di altri componenti
     *
     * @param owner finestra che contiene l'elemento che ha chiamato il metodo
     * @see Login
     * @see AccountSettingsController
     */
    public static void openWindow(Window owner) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("acquistoVoucher/acquistoVoucher.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("AcquistoVoucher");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Risponde alle chiamate esterne di altri componenti
     *
     * @param owner   finestra che contiene l'elemento che ha chiamato il metodo
     * @param importo lista di viaggi da visualizzare
     * @see ValoreVoucher
     * @see HomePage
     * @see AcquistoVoucherController
     */
    public static void openScene(Window owner, ValoreVoucher importo) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("acquistoVoucher/acquistoVoucher.fxml"));
        Parent scene = null;
        try {
            scene = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((AcquistoVoucherController) fxmlLoader.getController()).setVoucher(importo);
        owner.getScene().setRoot(scene);
    }

    /**
     * Risponde alla chiamata del main
     *
     * @param stage stage che contiene l'elemento che ha chiamato il metodo
     * @throws IOException
     * @see #launch(String...)
     * @see AccountSettings
     * @see Application
     */
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("acquistoVoucher/acquistoVoucher.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("AcquistoVoucher");
        stage.setScene(scene);
        stage.show();
    }
}
