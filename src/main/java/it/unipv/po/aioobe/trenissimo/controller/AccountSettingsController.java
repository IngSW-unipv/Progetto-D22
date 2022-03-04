package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.DatiPersonaliService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

public class AccountSettingsController implements Initializable {

    @FXML
    private VBox boxLoading;
    @FXML
    private VBox boxContent;
    @FXML private Button buttonModifica;
    @FXML private Button buttonAnnulla;
    @FXML private Button buttonSalva;

    @FXML private Label labelBenvenuto;
    @FXML private Label labelDatiPersonali;
    @FXML private TextField textFieldNome;
    @FXML private TextField textFieldCognome;
    @FXML private DatePicker datePickerDataNascita;
    @FXML private TextField textFieldEmail;
    @FXML private TextField textFieldVia;
    @FXML private TextField textFieldCivico;
    @FXML private TextField textFieldCitta;
    @FXML private TextField textFieldCAP;

    @FXML private Label labelErroreCAP;
    @FXML private Label labelErroreEmail;
    @FXML private Label labelErroreDataNascita;

    @FXML private Label labelNumeroBiglietto;
    @FXML private Label labelPrezzo;
    @FXML private Label labelDataAcquisto;
    @FXML private Label labelDownloadOK;

    @FXML private Button buttonScarica;
    @FXML private Label labelErroreNome;
    @FXML private Label labelErroreCognome;
    @FXML private Label labelErroreVia;
    @FXML private Label labelErroreCivico;
    @FXML private Label labelErroreCitta;

    private TicketBuilder titoloViaggio;



    //private DatiPersonaliEntity dati = new DatiPersonaliEntity("Franco", "Rossi", LocalDate.parse("1999-05-10"),new Indirizzo("via aldo moro", "11", "Pavia", "12345"), "ffr@gmail.com");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onStart();
        //todo aggiungere thread per caricamento dati personali in label
    }

    @FXML
    protected void onStart(){

        //metodo per leggere i dati da db e caricarli nelle varie textfield

        labelBenvenuto.setText("Ciao, "+ Account.getInstance().getDatiPersonali("zambo").getNome());
        labelDatiPersonali.setText("Dati Personali");
/*
        textFieldNome.setText(Account.getInstance().getDatiPersonali(Account.getInstance().getUsername()).getNome());
        textFieldCognome.setText(Account.getInstance().getDatiPersonali(Account.getInstance().getUsername()).getCognome());
        datePickerDataNascita.setValue(Account.getInstance().getDatiPersonali(Account.getInstance().getUsername()).getDataNascita().toLocalDate());
        textFieldEmail.setText(Account.getInstance().getDatiPersonali(Account.getInstance().getUsername()).getMail());
        /*
        textFieldVia.setText(Account.getInstance().getDatiPersonali(Account.getInstance().getId()).getVia());
        textFieldCivico.setText(Account.getInstance().getDatiPersonali(Account.getInstance().getId()).getCivico());
        textFieldCitta.setText(Account.getInstance().getDatiPersonali(Account.getInstance().getId()).getCitta());
        textFieldCAP.setText(Account.getInstance().getDatiPersonali(Account.getInstance().getId()).getCap());
        */
    }

    @FXML
    protected void onModifica() throws IOException {

        //metodo per il click del tasto modifica

        labelDatiPersonali.setText("Modifica i dati personali:");

        abilita(textFieldNome);
        abilita(textFieldCognome);
        datePickerDataNascita.setMouseTransparent(false);
        datePickerDataNascita.setDisable(false);
        datePickerDataNascita.setFocusTraversable(false);
        abilita(textFieldEmail);
        abilita(textFieldVia);
        abilita(textFieldCivico);
        abilita(textFieldCitta);
        abilita(textFieldCAP);

        buttonSalva.setVisible(true);
        buttonAnnulla.setVisible(true);
        buttonModifica.setVisible(false);

        textFieldCAP.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkCAP(textFieldCAP.getText())) {
                labelErroreCAP.setVisible(false);
                buttonSalva.setDisable(false);
                textFieldCAP.setStyle("-fx-border-color: #cccccc");
            }
            else {
                labelErroreCAP.setVisible(true);
                textFieldCAP.setStyle("-fx-border-color: #d70000");
            }
        });

        textFieldEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkEmail(textFieldEmail.getText())) {
                labelErroreEmail.setVisible(false);
                buttonSalva.setDisable(false);
                textFieldEmail.setStyle("-fx-border-color: #cccccc");
            }
            else {
                labelErroreEmail.setVisible(true);
                textFieldEmail.setStyle("-fx-border-color: #d70000");

            }
        });

        datePickerDataNascita.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (Account.getInstance().checkDataNascita(datePickerDataNascita.getValue()))
            {
                labelErroreDataNascita.setVisible(false);
                buttonSalva.setDisable(false);
                datePickerDataNascita.setStyle("-fx-border-color: #cccccc");
            }
            else {
                labelErroreDataNascita.setVisible(true);
                datePickerDataNascita.setStyle("-fx-border-color: #d70000");
            }
        });

        textFieldNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(textFieldNome.getText())) {
                labelErroreNome.setVisible(false);
                buttonSalva.setDisable(false);
                textFieldNome.setStyle("-fx-border-color: #cccccc");
            }
            else {
                labelErroreNome.setVisible(true);
                textFieldNome.setStyle("-fx-border-color: #d70000");
            }
        });

        textFieldCognome.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(textFieldCognome.getText())) {
                labelErroreCognome.setVisible(false);
                buttonSalva.setDisable(false);
                textFieldCognome.setStyle("-fx-border-color: #cccccc");
            }
            else {
                labelErroreCognome.setVisible(true);
                textFieldCognome.setStyle("-fx-border-color: #d70000");
            }
        });

        textFieldVia.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(textFieldVia.getText())) {
                labelErroreVia.setVisible(false);
                buttonSalva.setDisable(false);
                textFieldVia.setStyle("-fx-border-color: #cccccc");
            }
            else {
                labelErroreVia.setVisible(true);
                textFieldVia.setStyle("-fx-border-color: #d70000");
            }
        });

        textFieldCivico.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(textFieldCivico.getText())) {
                labelErroreCivico.setVisible(false);
                buttonSalva.setDisable(false);
                textFieldCivico.setStyle("-fx-border-color: #cccccc");
            }
            else {
                labelErroreCivico.setVisible(true);
                textFieldCivico.setStyle("-fx-border-color: #d70000");
            }
        });
        textFieldCitta.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(textFieldCitta.getText())) {
                labelErroreCitta.setVisible(false);
                buttonSalva.setDisable(false);
                textFieldCognome.setStyle("-fx-border-color: #cccccc");
            }
            else {
                labelErroreCitta.setVisible(true);
                textFieldCitta.setStyle("-fx-border-color: #d70000");
            }
        });

        buttonSalva.setOnMouseMoved(c -> {
        if (labelErroreCAP.isVisible() || labelErroreEmail.isVisible() || labelErroreDataNascita.isVisible()
            ||labelErroreNome.isVisible() || labelErroreCognome.isVisible() || labelErroreVia.isVisible() ||
            labelErroreCivico.isVisible() || labelErroreCitta.isVisible())
            buttonSalva.setDisable(true);
    });

    }

    @FXML
    protected void onAnnulla(){

        //metodo per il tasto annulla, disabilita tutte le textField/button

        disabilita(textFieldNome);
        disabilita(textFieldCognome);
        datePickerDataNascita.setMouseTransparent(true);
        datePickerDataNascita.setDisable(true);
        datePickerDataNascita.setFocusTraversable(true);
        disabilita(textFieldEmail);
        disabilita(textFieldVia);
        disabilita(textFieldCivico);
        disabilita(textFieldCitta);
        disabilita(textFieldCAP);

        buttonSalva.setVisible(false);
        buttonAnnulla.setVisible(false);
        buttonModifica.setVisible(true);

        onStart();
    }
    @FXML
    protected void onSalva(){

        //metodo per il click del tasto salva

        String nome = textFieldNome.getText();;
        String cognome = textFieldCognome.getText();;
        LocalDate dataNascita = LocalDate.parse(datePickerDataNascita.getValue().toString());
        String mail = textFieldEmail.getText();
        String via = textFieldVia.getText();
        String civico = textFieldCivico.getText();
        String citta = textFieldCitta.getText();
        String cap = textFieldCAP.getText();

        Account.getInstance().salva(nome,cognome,dataNascita,mail, via, civico, citta, cap);

        onStart(); //per ricaricare i dati all'interno delle textfield

        onAnnulla(); //per disabilitare tutti i bottoni/textField, necessito delle stesse cose che fa annulla
                    // annulla a differenza di questo, non salva i cambiamenti

    }

    @FXML
    protected void onAcquista(){
        // todo metodo per il tasto acquista in "tab" viaggi preferiti
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

        File biglietto = new File(TicketBuilder.DEST);
        File destin = new File(TicketBuilder.DW + labelNumeroBiglietto.getText()+".pdf");

        TicketBuilder.copy(biglietto, destin);
        labelDownloadOK.setVisible(true);

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                Thread.sleep(4000);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            labelDownloadOK.setVisible(false);
        });
        new Thread(task).start();

    }
    private void fillPDF() throws Exception {
        /*
        titoloViaggio = new TicketBuilder("","",labelDataAcquisto.getText(),"","","","",
                "","",dati.getNome(),dati.getCognome(),"",dati.getDataNascita().toString(),
                labelNumeroBiglietto.getText(), labelPrezzo.getText());
*/
        titoloViaggio.createPdf();
    }

    //metodi per abilitare e disabilitare le textfield
    private void abilita(TextField a){
        a.setMouseTransparent(false);
        a.setDisable(false);
        a.setFocusTraversable(false);
    }
    private void disabilita(TextField a){
        a.setMouseTransparent(true);
        a.setDisable(true);
        a.setFocusTraversable(true);
    }

}
