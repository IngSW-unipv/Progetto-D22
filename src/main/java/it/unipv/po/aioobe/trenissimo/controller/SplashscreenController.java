package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.persistence.util.exception.ConnectionDBException;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedRoutesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopTimesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedTripsService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class SplashscreenController implements Initializable{

    @FXML
    private BorderPane mainLayout;

    @FXML
    private Label lblCounter;

    @FXML
    private ProgressBar pgbSplashScreen;

    @FXML
    private Label lblStatus;

    private Integer counter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
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
            Image img = new Image(HomePage.class.getResourceAsStream("homePage/LogoIcona.png"));
            stage.getIcons().add(img);
            stage.show();
            ((Stage) mainLayout.getScene().getWindow()).close();
        });

        task.messageProperty().addListener((obs, oldVal, newVal) -> {
            lblStatus.setText(newVal);
        });

        new Thread(task).start();

    }

    @FXML
    protected void onCounterUp(){
        counter += 1;
        lblCounter.setText(counter.toString());
    }
}

