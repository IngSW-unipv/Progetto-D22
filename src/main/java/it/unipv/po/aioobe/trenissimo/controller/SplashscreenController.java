package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedRoutesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopTimesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedTripsService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashscreenController implements Initializable{




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call()  {
                updateMessage("Loading routes...");
                CachedRoutesService.getInstance().findAll();

                updateMessage("Loading stops...");
                CachedStopsService.getInstance().findAll();

                updateMessage("Loading trips...");
                CachedTripsService.getInstance().findAll();

                updateMessage("Loading stop times...");
                CachedStopTimesService.getInstance().findAll();

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
            stage.show();
            ((Stage) mainLayout.getScene().getWindow()).close();
        });

        task.messageProperty().addListener((obs, oldVal, newVal) -> {
            lblStatus.setText(newVal);
        });
        new Thread(task).start();

    }

    @FXML
    private BorderPane mainLayout;



    @FXML
    private Label lblCounter;

    @FXML
    private Label lblStatus;

    private Integer counter = 0;

    @FXML
    protected void onCounterUp(){
        counter += 1;
        lblCounter.setText(counter.toString());
    }
}

