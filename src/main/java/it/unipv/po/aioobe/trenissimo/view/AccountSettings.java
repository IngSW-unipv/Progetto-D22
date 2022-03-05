package it.unipv.po.aioobe.trenissimo.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

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

    public static void open() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("accountSettingsView/accountSettings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 800);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

}
