package it.unipv.po.aioobe.trenissimo.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
        textFieldNome.setText("nome da impostare" /* todo */);
        textFieldCognome.setText("cognome da impostare" /* todo */);
        datePickerDataNascita.setValue(LocalDate.now());
        textFieldEmail.setText("email da impostare" /* todo */);
        textFieldVia.setText("via da impostare" /* todo */);
        textFieldCivico.setText("civico da impostare" /* todo */);
        textFieldCitta.setText("citta da impostare" /* todo */);
        textFieldCAP.setText("CAP da impostare" /* todo */);

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

        //metodo per il tasto annulla

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

        String nome= textFieldNome.getText();;
        String cognome = textFieldCognome.getText();;
        LocalDate dataNascita = LocalDate.parse(datePickerDataNascita.getValue().toString());;
        String mail = textFieldEmail.getText();
        String CAP = textFieldCAP.getText();
        String indirizzo = textFieldVia.getText() + " , " + textFieldCivico.getText() + " , "
                + textFieldCitta.getText() + " , " + CAP;


        // qua vanno salvati nel db i nuovi dati, poi richiamato l'onStart per ricaricare i dati nelle textfield

        //onStart();

    }

    @FXML
    protected void onAcquista(){
        // todo metodo per il tasto acquista in "tab" viaggi preferiti
    }

    @FXML
    protected void onVisualizzaBigliettoPDF(){
        // todo metodo per il tasto acquista in "tab" storico viaggi
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
