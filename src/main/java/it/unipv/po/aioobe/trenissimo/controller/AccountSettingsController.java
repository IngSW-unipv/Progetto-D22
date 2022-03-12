package it.unipv.po.aioobe.trenissimo.controller;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.ViaggiPreferitiService;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.view.HomePage;
import it.unipv.po.aioobe.trenissimo.view.ModificaPassword;
import it.unipv.po.aioobe.trenissimo.view.StoricoAcquistoControl;
import it.unipv.po.aioobe.trenissimo.view.ViaggioPreferitoControl;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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


    @FXML private Button btnModifica;
    @FXML private Button btnAnnulla;
    @FXML private Button btnSalva;
    @FXML private Button btnModificaPassword;

    @FXML private Label lblBenvenuto;
    @FXML private Label lblPunti;
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

    @FXML private Label lblErroreNome;
    @FXML private Label lblErroreCognome;
    @FXML private Label lblErroreVia;
    @FXML private Label lblErroreCivico;
    @FXML private Label lblErroreCitta;

    @FXML private VBox layout;
    @FXML private VBox layoutStorico;

    private ObservableList<ViaggiPreferitiEntity> viaggiPreferiti;
    private ObservableList<StoricoAcquistiEntity> acquisti;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onStart();

        viaggiPreferiti = FXCollections.observableArrayList();
        viaggiPreferiti.addListener((ListChangeListener<ViaggiPreferitiEntity>) c -> updateListViaggiPreferiti());

        acquisti = FXCollections.observableArrayList();
        acquisti.addListener((ListChangeListener<StoricoAcquistiEntity>) c -> updateListStoricoAcquisti());

    }

    @FXML
    protected void onStart(){

        //metodo per leggere i dati da db e caricarli nelle varie textfield

        lblBenvenuto.setText("Ciao, "+ Account.getInstance().getDatiPersonali().getNome());
        lblPunti.setText(Account.getInstance().getPuntiFedelta());
        lblDatiPersonali.setText("Dati Personali");
        txtNome.setText(Account.getInstance().getDatiPersonali().getNome());
        txtCognome.setText(Account.getInstance().getDatiPersonali().getCognome());
        dtpDataNascita.setValue(Account.getInstance().getDatiPersonali().getDataNascita().toLocalDate());
        txtEmail.setText(Account.getInstance().getDatiPersonali().getMail());
        txtVia.setText(Account.getInstance().getDatiPersonali().getVia());
        txtCivico.setText(Account.getInstance().getDatiPersonali().getCivico());
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
            if(Utils.checkDatiGenerico(txtNome.getText())) {
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
            if(Utils.checkDatiGenerico(txtCognome.getText())) {
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
            if(Utils.checkDatiGenerico(txtVia.getText())) {
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
            if(Utils.checkDatiGenerico(txtCivico.getText())) {
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
            if(Utils.checkDatiGenerico(txtCitta.getText())) {
                lblErroreCitta.setVisible(false);
                btnSalva.setDisable(false);
                txtCitta.setStyle("-fx-border-color: #cccccc");
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

    public void setViaggiPreferiti() {
        ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
        this.viaggiPreferiti.addAll(viaggiPreferitiService.findByUsername(Account.getInstance().getUsername()));

    }

    private void updateListViaggiPreferiti(){
        layout.getChildren().setAll(viaggiPreferiti.stream().map(ViaggioPreferitoControl::new).toList());
    }

    public void setStoricoAcquisti() {
        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
        this.acquisti.addAll(storicoAcquistiService.findByUsername(Account.getInstance().getUsername()));
    }

    private void updateListStoricoAcquisti(){
        layoutStorico.getChildren().setAll(acquisti.stream().map(StoricoAcquistoControl::new).toList());
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
