package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.AccountSettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Main class che gestisce il render del file accountSettings-view.fxml
 *
 * @see Application
 * @see it.unipv.po.aioobe.trenissimo.view.accountSettingsView
 */
public class AccountSettings extends Application {
    /**
     * Lancia il render della parte grafica
     *
     * @param args argomenti da linea di comando
     * @see #launch(String...)
     * @see #start(Stage)
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
    public static void openScene(Window owner) {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("accountSettingsView/accountSettings-view.fxml"));

        Parent scene = null;
        try {
            scene = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((AccountSettingsController) fxmlLoader.getController()).setViaggiPreferiti();
        ((AccountSettingsController) fxmlLoader.getController()).setStoricoAcquisti();

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
        FXMLLoader fxmlLoader = new FXMLLoader(AccountSettings.class.getResource("accountSettingsView/accountSettings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 800);
        stage.setResizable(false);
        stage.setTitle("Trenissimo");
        stage.setScene(scene);
        stage.show();
    }

}
