package it.unipv.po.aioobe.trenissimo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login{

    public static void open() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}