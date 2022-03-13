package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedRoutesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopTimesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedTripsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.exception.ConnectionDBException;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Controller class per splashscreen.fxml
 *
 * @author ArrayIndexOutOfBoundsException
 * @version %I%, %G%
 * @see it.unipv.po.aioobe.trenissimo.view.splashScreen
 * @see javafx.fxml.Initializable
 */
public class SplashscreenController implements Initializable{

    @FXML
    private BorderPane mainLayout;

    @FXML
    private Label lblStatus;

    /**
     * Metodo d'Inizializzazione
     *
     * @param location
     * @param resources
     * @see HomePage
     * @see Scene
     * @see Stage
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Task<Void> task = new Task<Void>() {
            /**
             *
             * @return sempre null
             * @throws InterruptedException necessario per TimeUnit
             * @throws ConnectionDBException utilizzato per verificare la connessione ad database
             * @see CachedRoutesService
             * @see CachedStopsService
             * @see CachedTripsService
             * @see CachedStopTimesService
             * @see TimeUnit
             */
            @Override
            public Void call() throws InterruptedException, ConnectionDBException {
                try {
                updateMessage("Loading routes...");
                CachedRoutesService.getInstance().findAll();

                updateMessage("Loading stops...");
                CachedStopsService.getInstance().findAll();

                updateMessage("Loading trips...");
                CachedTripsService.getInstance().findAll();

                updateMessage("Loading stop times...");
                CachedStopTimesService.getInstance().findAll();

                }catch (ConnectionDBException e){
                    lblStatus.setStyle("-fx-text-fill: #d70000");
                    for(int i = 5; i>0; i--) {
                        updateMessage("Error loading database. Trenissimo closing in " + i + "s");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    System.exit(1);
                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("homePage/homePage-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1440, 800);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Trenissimo");
            stage.setScene(scene);
            Image img = new Image(Objects.requireNonNull(HomePage.class.getResourceAsStream("homePage/LogoIcona.png")));
            stage.getIcons().add(img);
            stage.show();
            ((Stage) mainLayout.getScene().getWindow()).close();
        });

        task.messageProperty().addListener((obs, oldVal, newVal) -> {
            lblStatus.setText(newVal);
        });

        new Thread(task).start();

    }
}

