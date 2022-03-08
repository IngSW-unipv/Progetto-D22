package it.unipv.po.aioobe.trenissimo.controller;


import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StoricoAcquistoController implements Initializable {

    @FXML
    private Label lblNumeroBiglietto;
    @FXML
    private Label lblPrezzo;
    @FXML
    private Label lblDataAcquisto;
    @FXML
    private Label lblDownloadOK;

    @FXML
    private VBox layout;
    @FXML
    private VBox layoutStorico;


    private TicketBuilder titoloViaggio;



    @FXML
    protected void onVisualizzaBigliettoPDF() throws Exception {
        fillPDF();

        File biglietto = new File(TicketBuilder.DEST);
        Desktop.getDesktop().open(biglietto);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                Thread.sleep(3000);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            biglietto.delete();
        });
        new Thread(task).start();

    }
    @FXML
    protected void onScaricaBigliettoPDF() throws Exception {
        fillPDF();

        File biglietto = new File(TicketBuilder.DEST); //biglietto in folder temporanea

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Scegli dove salvare il titolo di viaggio");
        fileChooser.setInitialFileName(lblNumeroBiglietto.getText());

        File destin = new File(fileChooser.showSaveDialog(new Stage()).getAbsolutePath().concat(".pdf"));
        TicketBuilder.copy(biglietto, destin);

        lblDownloadOK.setVisible(true);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                Thread.sleep(4000);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            lblDownloadOK.setVisible(false);
            biglietto.delete();
        });
        new Thread(task).start();

    }

    private void fillPDF() throws Exception {

        titoloViaggio = new TicketBuilder("","", lblDataAcquisto.getText(),"","","","",
                "","", Account.getInstance().getDatiPersonali().getNome(),Account.getInstance().getDatiPersonali().getCognome(),
                "",Account.getInstance().getDatiPersonali().getDataNascita().toString(), lblNumeroBiglietto.getText(), lblPrezzo.getText());

        titoloViaggio.createPdf();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}