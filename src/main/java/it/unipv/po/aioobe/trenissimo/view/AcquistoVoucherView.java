package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.controller.AcquistoVoucherController;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.ValoreVoucher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class AcquistoVoucherView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("acquistoVoucher/acquistoVoucher.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("AcquistoVoucher");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openWindow(Window owner){
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

    public static void openScene(Window owner, ValoreVoucher importo){
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
}
