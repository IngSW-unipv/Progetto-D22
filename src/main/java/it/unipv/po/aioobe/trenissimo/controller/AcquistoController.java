package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.dao.StoricoAcquistiDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.TitoloViaggioService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.VoucherService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.CorsaSingola;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.TipoTitoloViaggio;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.view.ViaggioControl;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AcquistoController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private VBox boxViaggi;

    @FXML private Label lblRiscattoOK;
    @FXML private Label lblErroreRiscatto;
    @FXML private Button btnRiscatta;
    @FXML private TextField txtVoucher;

    @FXML private Label lblSubtotale;
    @FXML private Label lblIVA;
    @FXML private Label lblSconto;
    @FXML private Label lblTotale;

    private ObservableList<Viaggio> _viaggi;
    private TicketBuilder titoloViaggio;

    private boolean isIdVoucherOK;
    private Double subtotale = 0.0;
    private Double iva = 0.0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _viaggi = FXCollections.observableArrayList();
        _viaggi.addListener((ListChangeListener<Viaggio>) c -> {
            boxViaggi.getChildren().setAll(_viaggi.stream().map(x -> new ViaggioControl(x, null)).toList());
        });

        checkIdRealTime();
    }

    public void set_viaggi(List<Viaggio> viaggi) {

        for (Viaggio v: viaggi) {
            subtotale = subtotale + v.getPrezzoTot();
            iva = iva + v.getPrezzoIva();
        }

        lblSubtotale.setText("€ "+String.format(Locale.US,"%.2f", subtotale));
        lblIVA.setText("€ "+String.format(Locale.US,"%.2f", iva));
        lblTotale.setText("€ "+String.format(Locale.US,"%.2f", subtotale));

        _viaggi.setAll(viaggi);
    }

    @FXML
    protected void onPaga() throws Exception {
        List<Acquisto> biglietti = new ArrayList<>();
        for (Viaggio v: _viaggi) {
            biglietti.add(new CorsaSingola(TipoTitoloViaggio.BIGLIETTOCORSASINGOLA, v));
        }
        biglietti.forEach(x -> x.pagare());
        if (Account.getLoggedIn()) {
            biglietti.forEach(x -> x.puntiFedelta(biglietti));
            //metodi per aggiornare i punti fedeltà dal db all'istanza di Account
            String username = Account.getInstance().getUsername();
            Account.getInstance().setAccount(username);
        }
        for (Acquisto a:biglietti){
            onScaricaBigliettoPDF(a);
        }

    }

    @FXML
    protected void onScaricaBigliettoPDF(Acquisto a) throws Exception {
        fillPDF(a);

        File biglietto = new File(TicketBuilder.DEST); //biglietto in folder temporanea

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Scegli dove salvare il titolo di viaggio");
        fileChooser.setInitialFileName(a.getId());

        File destin = new File(fileChooser.showSaveDialog(new Stage()).getAbsolutePath().concat(".pdf"));
        TicketBuilder.copy(biglietto, destin);

        // todo aggiungere controllo su getAbsPath

        //lblDownloadOK.setVisible(true);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                Thread.sleep(4000);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            //lblDownloadOK.setVisible(false);
            biglietto.delete();
        });
        new Thread(task).start();

    }

    private void fillPDF(Acquisto a) throws Exception {
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
        TitoloViaggioEntity titoloViaggioEntity;
        titoloViaggioEntity=titoloViaggioService.findById(a.getId());

        if(a.getId().substring(0,2).equals("CS"))
            /*titoloViaggio = new TicketBuilder(titoloViaggioEntity.getStazionePartenza(),titoloViaggioEntity.getStazioneArrivo(),
                    titoloViaggioEntity.getDataPartenza().toString(), titoloViaggioEntity.getDataArrivo().toString(),
                    titoloViaggioEntity.getOraPartenza().toString(),titoloViaggioEntity.getOraArrivo().toString(),
                    Account.getInstance().getDatiPersonali().getNome(), Account.getInstance().getDatiPersonali().getCognome(),
                    Account.getInstance().getDatiPersonali().getDataNascita().toString(), a.getId(), String.valueOf(a.getPrezzo()));
        */
titoloViaggio = new TicketBuilder(titoloViaggioEntity.getStazionePartenza(),titoloViaggioEntity.getStazioneArrivo(),
                    titoloViaggioEntity.getDataPartenza().toString(), titoloViaggioEntity.getDataArrivo().toString(),
                    titoloViaggioEntity.getOraPartenza().toString(),titoloViaggioEntity.getOraArrivo().toString(),
                    "","","", a.getId(), String.valueOf(a.getPrezzo()));

        titoloViaggio.createPdf(a.getId());
    }

    @FXML
    protected void onRiscatta() throws Exception {
        // todo aggiungere controlli dopo parte grafica
        VoucherService voucherService = new VoucherService();

        if (!(Utils.checkIdVoucher(txtVoucher.getText()))) {
            lblErroreRiscatto.setVisible(true);
            txtVoucher.setStyle("-fx-border-color: #d70000");
            isIdVoucherOK=false;
        }

        if(isIdVoucherOK) {
            lblSconto.setText("-€"+voucherService.findById(txtVoucher.getText()).getPrezzo());
            String[] totaleSplit = lblTotale.getText().split("(?<=€)");
            String[] scontoSplit = lblSconto.getText().split("(?<=€)");

            Double calcoloTot = (Double.valueOf(totaleSplit[1]) - Double.valueOf(scontoSplit[1]));
            if (calcoloTot<0.0)
                lblTotale.setText("€ "+ 0.0);
            else
                lblTotale.setText("€"+ calcoloTot);

            voucherService.deleteById(txtVoucher.getText());
            lblRiscattoOK.setVisible(true);

            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws InterruptedException {
                    Thread.sleep(2000);
                    return null;
                }
            };
            task.setOnSucceeded(e -> {
                lblRiscattoOK.setVisible(false);
                btnRiscatta.setDisable(true);
            });
            new Thread(task).start();

            }


        else
            isIdVoucherOK=false;



    }


    private void checkIdRealTime() {

        txtVoucher.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Utils.checkDatiGenerico(txtVoucher.getText())) {
                lblErroreRiscatto.setVisible(false);
                btnRiscatta.setDisable(false);
                txtVoucher.setStyle("-fx-border-color: #cccccc");
            } else {
                lblErroreRiscatto.setVisible(false);
                txtVoucher.setStyle("-fx-border-color: #d70000");
            }
            try {
                if (!(Utils.checkIdVoucher(txtVoucher.getText()))) {
                    lblErroreRiscatto.setVisible(true);
                    txtVoucher.setStyle("-fx-border-color: #d70000");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        btnRiscatta.setOnMouseMoved(c -> {
            if (lblErroreRiscatto.isVisible()) {
                btnRiscatta.setDisable(true);
                isIdVoucherOK = false;
            }
            else
            isIdVoucherOK = true;

        });
    }

    @FXML
    protected void onGoToHomepage() {
        HomePage.openScene(root.getScene().getWindow());
    }
}
