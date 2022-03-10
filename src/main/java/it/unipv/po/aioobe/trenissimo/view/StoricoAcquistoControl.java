package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.TitoloViaggioService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StoricoAcquistoControl extends VBox {

    @FXML private Label lblIDAcquisto;
    @FXML private Label lblNumeroBiglietto;
    @FXML private Label lblDataAcquisto;
    @FXML private Label lblPrezzo;
    @FXML private Label lblDownloadOK;

    @FXML private Button btnBigliettoPDF;
    @FXML private Button btnScarica;

    @FXML private VBox boxChanges;
    @FXML private VBox boxChangesContainer;
    @FXML private FontIcon icoChanges;

    private StoricoAcquistiEntity acquisto;

    private TicketBuilder titoloViaggio;


    public StoricoAcquistoControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountSettingsView/storicoAcquistoControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public StoricoAcquistoControl(StoricoAcquistiEntity acquisto) {
        this();
        setAcquisto(acquisto);
    }

    public StoricoAcquistiEntity getAcquisto() {
        return acquisto;
    }

    public void setAcquisto(StoricoAcquistiEntity acquistiEntity) {
        this.acquisto = acquistiEntity;

        lblIDAcquisto.textProperty().setValue(acquisto.getStoricoAcquistiId().toString());
        lblDataAcquisto.textProperty().setValue(acquisto.getDataAcquisto().toLocalDateTime().toLocalDate().toString());
        lblNumeroBiglietto.textProperty().setValue(acquisto.getTitoloViaggioId());
        lblPrezzo.textProperty().setValue(acquisto.getPrezzo().toString() + "€");

    }


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

        // todo aggiungere controllo su getAbsPath

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
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
        TitoloViaggioEntity titoloViaggioEntity;
        titoloViaggioEntity=titoloViaggioService.findById(lblNumeroBiglietto.getText());

       if(lblNumeroBiglietto.getText().substring(0,2).equals("CS"))
            titoloViaggio = new TicketBuilder(titoloViaggioEntity.getStazionePartenza(),titoloViaggioEntity.getStazioneArrivo(),
                    titoloViaggioEntity.getDataPartenza().toString(), titoloViaggioEntity.getDataArrivo().toString(),
                    titoloViaggioEntity.getOraPartenza().toString(),titoloViaggioEntity.getOraArrivo().toString(),
                    Account.getInstance().getDatiPersonali().getNome(), Account.getInstance().getDatiPersonali().getCognome(),
                    Account.getInstance().getDatiPersonali().getDataNascita().toString(), lblNumeroBiglietto.getText(), lblPrezzo.getText());
       else
           titoloViaggio = new TicketBuilder(lblNumeroBiglietto.getText(), lblPrezzo.getText());

        titoloViaggio.createPdf(lblNumeroBiglietto.getText());
    }

}
