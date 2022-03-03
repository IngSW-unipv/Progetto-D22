package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.DatiPersonali;
import it.unipv.po.aioobe.trenissimo.model.user.utils.Indirizzo;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder.DEST;
import static it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder.copy;

public class AccountSettingsController implements Initializable {

    @FXML
    private VBox boxLoading;
    @FXML
    private VBox boxContent;
    @FXML
    private Button buttonModifica;
    @FXML
    private Button buttonAnnulla;
    @FXML
    private Button buttonSalva;
    @FXML
    private Label labelBenvenuto;
    @FXML
    private Label labelDatiPersonali;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCognome;
    @FXML
    private DatePicker datePickerDataNascita;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldVia;
    @FXML
    private TextField textFieldCivico;
    @FXML
    private TextField textFieldCitta;
    @FXML
    private TextField textFieldCAP;
    @FXML
    private Label labelErroreCAP;
    @FXML
    private Label labelErroreEmail;
    @FXML
    private Label labelErroreDataNascita;
@FXML
    private Label labelNumeroBiglietto;
@FXML
    private Label labelPrezzo;
@FXML
private Label labelDataAcquisto;
@FXML
private Label labelDownloadOK;

    private TicketBuilder titoloViaggio;



    private DatiPersonali dati = new DatiPersonali("Franco", "Rossi", LocalDate.parse("1999-05-10"),
            new Indirizzo("via aldo moro", "11", "Pavia", "12345"), "ffr@gmail.com");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onStart();
        //todo aggiungere thread per caricamento dati personali in label
    }

    @FXML
    protected void onStart(){

        //metodo per leggere i dati da db e caricarli nelle varie textfield

        labelBenvenuto.setText("Ciao, "+ "@inserireUsernameDaDB");
        labelDatiPersonali.setText("Dati Personali");
        textFieldNome.setText(dati.getNome());
        textFieldCognome.setText(dati.getCognome());
        datePickerDataNascita.setValue(dati.getDataNascita());
        textFieldEmail.setText(dati.getMail());
        textFieldVia.setText(dati.getIndirizzoResidenza().getVia());
        textFieldCivico.setText(dati.getIndirizzoResidenza().getCivico());
        textFieldCitta.setText(dati.getIndirizzoResidenza().getCitta());
        textFieldCAP.setText(dati.getIndirizzoResidenza().getCap());
        labelDownloadOK.setVisible(false);
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
            if(textFieldCAP.getText().length() == 5 && textFieldCAP.getText().matches("^[0-9]+$")) {
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
            if(textFieldEmail.getText().matches("[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,6}")) {
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
            if (datePickerDataNascita.getValue().isBefore(LocalDate.now()))
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


        buttonSalva.setOnMouseMoved(c -> {
        if (labelErroreCAP.isVisible() || labelErroreEmail.isVisible() || labelErroreDataNascita.isVisible())
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
        LocalDate dataNascita = LocalDate.parse(datePickerDataNascita.getValue().toString());;
        String mail = textFieldEmail.getText();

        Indirizzo ind = dati.getIndirizzoResidenza();
        ind.setVia(textFieldVia.getText());
        ind.setCivico(textFieldCivico.getText());
        ind.setCitta(textFieldCitta.getText());
        ind.setCap(textFieldCAP.getText());

        dati.setNome(nome);
        dati.setCognome(cognome);
        dati.setDataNascita(dataNascita);
        dati.setMail(mail);
        dati.setIndirizzoResidenza(ind);

        onStart(); //per ricaricare i dati all'interno delle textfield -- funziona anche senza mi sa

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

        Thread.sleep(1000);
        biglietto.delete();

    }
    @FXML
    protected void onScaricaBigliettoPDF() throws Exception {
        fillPDF();

        File biglietto = new File(TicketBuilder.DEST);
        File destin = new File(TicketBuilder.DW);
        TicketBuilder.copy(biglietto, destin);
        labelDownloadOK.setVisible(true);

    }
    private void fillPDF() throws Exception {
        titoloViaggio = new TicketBuilder("","",labelDataAcquisto.getText(),"","","","",
                "","",dati.getNome(),dati.getCognome(),"",dati.getDataNascita().toString(),
                labelNumeroBiglietto.getText(), labelPrezzo.getText());

        titoloViaggio.createPdf(TicketBuilder.SRC, TicketBuilder.DEST);
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
