package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.TicketBuilder;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.view.ModificaPassword;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AccountSettingsController implements Initializable {


    @FXML private Button btnModifica;
    @FXML private Button btnAnnulla;
    @FXML private Button btnSalva;
    @FXML private Button btnModificaPassword;

    @FXML private Label lblBenvenuto;
    @FXML private Label lblDatiPersonali;
    @FXML private TextField txtNome;
    @FXML private TextField txtCognome;
    @FXML private DatePicker dtpDataNascita;
    @FXML private TextField txtEmail;
    @FXML private TextField txtVia;
    @FXML private TextField txtCivico;
    @FXML private TextField txtCitta;
    @FXML private TextField txtCAP;

    @FXML private Label lblErroreCAP;
    @FXML private Label lblErroreEmail;
    @FXML private Label lblErroreDataNascita;

    @FXML private Label lblNumeroBiglietto;
    @FXML private Label lblPrezzo;
    @FXML private Label lblDataAcquisto;
    @FXML private Label lblDownloadOK;

    @FXML private Label lblErroreNome;
    @FXML private Label lblErroreCognome;
    @FXML private Label lblErroreVia;
    @FXML private Label lblErroreCivico;
    @FXML private Label lblErroreCitta;

    private TicketBuilder titoloViaggio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onStart();
        //todo aggiungere thread per caricamento dati personali in label
    }

    @FXML
    protected void onStart(){

        //metodo per leggere i dati da db e caricarli nelle varie textfield

        //Account.getInstance().setDatiPersonali("zambo"); // todo da aggiunere dati del login

        lblBenvenuto.setText("Ciao, "+ Account.getInstance().getDatiPersonali().getNome());
        lblDatiPersonali.setText("Dati Personali");
        txtNome.setText(Account.getInstance().getDatiPersonali().getNome());
        txtCognome.setText(Account.getInstance().getDatiPersonali().getCognome());
        dtpDataNascita.setValue(Account.getInstance().getDatiPersonali().getDataNascita().toLocalDate());
        txtEmail.setText(Account.getInstance().getDatiPersonali().getMail());
        txtVia.setText(Account.getInstance().getDatiPersonali().getVia());
        txtCivico.setText(Account.getInstance().getDatiPersonali().getCivico().toString());
        txtCitta.setText(Account.getInstance().getDatiPersonali().getCitta());
        txtCAP.setText(Account.getInstance().getDatiPersonali().getCap().toString());

    }

    @FXML
    protected void onGoToHomepage(){
        HomePage.openScene(lblBenvenuto.getScene().getWindow());
    }

    @FXML
    protected void onModifica() throws IOException {

        //metodo per il click del tasto modifica

        lblDatiPersonali.setText("Modifica i dati personali:");

        abilita(txtNome);
        abilita(txtCognome);
        dtpDataNascita.setMouseTransparent(false);
        dtpDataNascita.setDisable(false);
        dtpDataNascita.setFocusTraversable(false);
        abilita(txtEmail);
        abilita(txtVia);
        abilita(txtCivico);
        abilita(txtCitta);
        abilita(txtCAP);


        btnSalva.setVisible(true);
        btnAnnulla.setVisible(true);
        btnModifica.setVisible(false);
        btnModificaPassword.setDisable(false);

        txtCAP.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkCAP(txtCAP.getText())) {
                lblErroreCAP.setVisible(false);
                btnSalva.setDisable(false);
                txtCAP.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCAP.setVisible(true);
                txtCAP.setStyle("-fx-border-color: #d70000");
            }
        });

        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkEmail(txtEmail.getText())) {
                lblErroreEmail.setVisible(false);
                btnSalva.setDisable(false);
                txtEmail.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreEmail.setVisible(true);
                txtEmail.setStyle("-fx-border-color: #d70000");

            }
        });

        dtpDataNascita.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (Account.getInstance().checkDataNascita(dtpDataNascita.getValue()))
            {
                lblErroreDataNascita.setVisible(false);
                btnSalva.setDisable(false);
                dtpDataNascita.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreDataNascita.setVisible(true);
                dtpDataNascita.setStyle("-fx-border-color: #d70000");
            }
        });

        txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtNome.getText())) {
                lblErroreNome.setVisible(false);
                btnSalva.setDisable(false);
                txtNome.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreNome.setVisible(true);
                txtNome.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCognome.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtCognome.getText())) {
                lblErroreCognome.setVisible(false);
                btnSalva.setDisable(false);
                txtCognome.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCognome.setVisible(true);
                txtCognome.setStyle("-fx-border-color: #d70000");
            }
        });

        txtVia.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtVia.getText())) {
                lblErroreVia.setVisible(false);
                btnSalva.setDisable(false);
                txtVia.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreVia.setVisible(true);
                txtVia.setStyle("-fx-border-color: #d70000");
            }
        });

        txtCivico.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtCivico.getText())) {
                lblErroreCivico.setVisible(false);
                btnSalva.setDisable(false);
                txtCivico.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCivico.setVisible(true);
                txtCivico.setStyle("-fx-border-color: #d70000");
            }
        });
        txtCitta.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Account.getInstance().checkDatiGenerico(txtCitta.getText())) {
                lblErroreCitta.setVisible(false);
                btnSalva.setDisable(false);
                txtCognome.setStyle("-fx-border-color: #cccccc");
            }
            else {
                lblErroreCitta.setVisible(true);
                txtCitta.setStyle("-fx-border-color: #d70000");
            }
        });

        btnSalva.setOnMouseMoved(c -> {
        if (lblErroreCAP.isVisible() || lblErroreEmail.isVisible() || lblErroreDataNascita.isVisible()
            || lblErroreNome.isVisible() || lblErroreCognome.isVisible() || lblErroreVia.isVisible() ||
            lblErroreCivico.isVisible() || lblErroreCitta.isVisible())
            btnSalva.setDisable(true);
    });

    }

    @FXML
    protected void onModificaPassword() throws IOException {
        ModificaPassword.open(lblBenvenuto.getScene().getWindow());

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                Thread.sleep(1500);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            onAnnulla();
        });
        new Thread(task).start();
    }


    @FXML
    protected void onAnnulla(){

        //metodo per il tasto annulla, disabilita tutte le textField/button

        disabilita(txtNome);
        disabilita(txtCognome);
        dtpDataNascita.setMouseTransparent(true);
        dtpDataNascita.setDisable(true);
        dtpDataNascita.setFocusTraversable(true);
        disabilita(txtEmail);
        disabilita(txtVia);
        disabilita(txtCivico);
        disabilita(txtCitta);
        disabilita(txtCAP);

        btnSalva.setVisible(false);
        btnAnnulla.setVisible(false);
        btnModifica.setVisible(true);
        btnModificaPassword.setDisable(true);

        onStart();
    }
    @FXML
    protected void onSalva(){

        //metodo per il click del tasto salva

        String nome = txtNome.getText();;
        String cognome = txtCognome.getText();;
        LocalDate dataNascita = LocalDate.parse(dtpDataNascita.getValue().toString());
        String mail = txtEmail.getText();
        String via = txtVia.getText();
        String civico = txtCivico.getText();
        String citta = txtCitta.getText();
        String cap = txtCAP.getText();

        Account.getInstance().salvaModificaDati(nome,cognome,dataNascita,mail, via, civico, citta, cap);

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
                "","",Account.getInstance().getDatiPersonali().getNome(),Account.getInstance().getDatiPersonali().getCognome(),
                "",Account.getInstance().getDatiPersonali().getDataNascita().toString(), lblNumeroBiglietto.getText(), lblPrezzo.getText());

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
