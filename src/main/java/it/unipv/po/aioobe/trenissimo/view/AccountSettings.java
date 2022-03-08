package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.AccountSettingsController;
import it.unipv.po.aioobe.trenissimo.controller.RicercaController;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountSettings extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AccountSettings.class.getResource("accountSettingsView/accountSettings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 800);
        stage.setResizable(false);
        stage.setTitle("Trenissimo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void open() {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("accountSettingsView/accountSettings-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1440, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void openScene(Window owner){
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

}
