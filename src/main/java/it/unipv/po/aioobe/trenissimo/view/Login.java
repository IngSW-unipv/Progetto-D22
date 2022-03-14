package it.unipv.po.aioobe.trenissimo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

/**
 * Main class che gestisce il render del file login-view.fxml
 *
 * @see it.unipv.po.aioobe.trenissimo.view.acquistoVoucher
 */
public class Login {

    /**
     * Risponde alle chiamate esterne di altri componenti
     *
     * @param owner finestra che contiene l'elemento che ha chiamato il metodo
     * @throws IOException
     * @see Login
     * @see HomePage
     */
    public static void open(Window owner) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setResizable(false);
        stage.setTitle("Login");
        Image img = new Image(Objects.requireNonNull(HomePage.class.getResourceAsStream("homePage/LogoIcona.png")));
        stage.getIcons().add(img);
        stage.setScene(scene);
        stage.show();
    }
}