package it.unipv.po.aioobe.trenissimo.view;


import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.Nullable;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Main class che gestisce il render del file viaggioPreferitoControl.fxml
 * è anche il controller di se stesso
 *
 * @author ArrayIndexOutOfBoundsException
 * @see VBox
 * @see it.unipv.po.aioobe.trenissimo.view.accountSettingsView
 */

public class ViaggioPreferitoControl extends VBox {

    public static Boolean fromViaggioControl = false;
    @FXML
    private Label lblOrario;
    @FXML
    private Label lblPartenza;
    @FXML
    private Label lblArrivo;
    @FXML
    private Label lblCambi;
    @FXML
    private Label lblAdulti;
    @FXML
    private Label lblRagazzi;
    @FXML
    private Label lblBambini;
    @FXML
    private Label lblAnimali;
    @FXML
    private Button btnAcquista;
    @FXML
    private VBox boxChanges;
    @FXML
    private VBox boxChangesContainer;
    @FXML
    private FontIcon icoChanges;
    @FXML
    private Button btnDeletePreferito;
    @FXML
    private Label lblDeleteOK;
    private ViaggiPreferitiEntity viaggio;

    /**
     * Costruttore per la visualizzazione di viaggioPreferitoControl.fxml
     */
    public ViaggioPreferitoControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountSettingsView/viaggioPreferitoControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Costruttore per la visualizzazione di viaggioControl.fxml
     *
     * @param viaggiPreferiti ViaggiPreferitiEntity da cui estrarre informazioni
     */
    public ViaggioPreferitoControl(ViaggiPreferitiEntity viaggiPreferiti) {
        this();
        setViaggio(viaggiPreferiti);
    }

    /**
     * Ritorna un ViaggioPreferito
     *
     * @return viaggio preferito
     */
    public ViaggiPreferitiEntity getViaggio() {
        return viaggio;
    }


    /**
     * Raccoglie i dati di uno ViaggioPreferitoEntity
     *
     * @param viaggioPreferito
     */
    public void setViaggio(ViaggiPreferitiEntity viaggioPreferito) {
        this.viaggio = viaggioPreferito;

        lblOrario.textProperty().setValue(viaggio.getOra().toString());
        lblPartenza.textProperty().setValue(viaggio.getStazionePartenza());
        lblArrivo.textProperty().setValue(viaggio.getStazioneArrivo());
        lblCambi.textProperty().setValue(viaggio.getnCambi() + " cambi");
        lblAdulti.textProperty().setValue(viaggio.getnAdulti().toString());
        lblRagazzi.textProperty().setValue(viaggio.getnRagazzi().toString());
        lblBambini.textProperty().setValue(viaggio.getnBambini().toString());
        lblAnimali.textProperty().setValue(viaggio.getnAnimali().toString());

    }

    /**
     * Permette di eseguire una ricerca che porterà direttamente all'acquisto del biglietto
     *
     * @see CachedStopsService
     * @see Ricerca
     * @see AcquistoView
     * @see it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity
     * @see it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio
     */
    @FXML
    protected void onAcquista() {
        var lista = CachedStopsService.getInstance().findAll();
        var partenza = lista.stream().filter(x -> x.getStopName().equals(lblPartenza.getText())).toList().get(0);
        var arrivo = lista.stream().filter(x -> x.getStopName().equals(lblArrivo.getText())).toList().get(0);


        int partenzaId = partenza.getStopId();
        int destinazioneId = arrivo.getStopId();
        LocalDateTime data = LocalDateTime.now();

        Ricerca search = new Ricerca(partenzaId, destinazioneId, data);
        search.setNumAdulti(Integer.parseInt(lblAdulti.getText()));
        search.setNumRagazzi(Integer.parseInt(lblRagazzi.getText()));
        search.setNumBambini(Integer.parseInt(lblBambini.getText()));
        search.setNumAnimali(Integer.parseInt(lblAnimali.getText()));

        search.eseguiRicerca();
        var viaggi = search.getRisultatiAndata().stream().filter(x -> x.getOrarioPartenza() == Utils.timeToSeconds(lblOrario.getText())).toList();

        AcquistoView.openScene(lblArrivo.getScene().getWindow(), viaggi);

    }

    /**
     * Permette di cancellare un viaggio preferito
     *
     * @see Account
     * @see AccountSettings
     */
    @FXML
    protected void onDeletePreferito() {
        Account.getInstance().deleteViaggioPreferito(viaggio);
        btnDeletePreferito.setDisable(true);
        lblDeleteOK.setVisible(true);
        fromViaggioControl = true;

        Task<Void> task = new Task<Void>() {
            /**
             * Aspetta 1.0 secondi
             * @return sempre null
             * @throws InterruptedException necessaria per Thread.sleep()
             */
            @Override
            public @Nullable Void call() throws InterruptedException {
                Thread.sleep(1000);
                return null;
            }
        };
        task.setOnSucceeded(e -> {
            AccountSettings.openScene(lblArrivo.getScene().getWindow());
        });
        new Thread(task).start();
    }


}
