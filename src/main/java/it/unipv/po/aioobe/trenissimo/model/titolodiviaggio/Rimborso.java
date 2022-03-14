package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.TitoloViaggioService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy.IRimborsoStrategy;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy.RimborsoFactory;

import java.time.LocalDate;

/**
 * Classe che modellizza un rimborso.
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class Rimborso {

    private final StoricoAcquistiEntity storicoAcquisti;
    private final TitoloViaggioEntity titoloViaggioEntity;
    private LocalDate dataRichiesta;
    private final String id;
    private final IRimborsoStrategy rimborsoStrategy;

    /**
     * Costruttore che istanzia un service per lo storico acquisti ed un service per il titolo di viaggio assegnando l'istanza
     * agli attributi privati titoloViaggioEntity e storicoAcquisti. <br>
     * Inoltre assegna l'id del biglietto che è passato come parametro all'attributo privato relativo, ed assegna alla
     * data di richiesta, la data attuale al momento della chiamata del costruttore.
     *
     * @param id del biglietto
     */
    public Rimborso(String id) {
        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
        this.storicoAcquisti = storicoAcquistiService.findByTitoloViaggioId(id);
        this.titoloViaggioEntity = titoloViaggioService.findById(id);
        this.id = id;
        this.dataRichiesta = LocalDate.now();

        RimborsoFactory f = new RimborsoFactory();
        rimborsoStrategy = f.getRimborso();
    }

    /**
     * Metodo che, dato un ID del biglietto, controlla se esiste nel database.
     *
     * @param idBiglietto
     * @return "true" se il biglietto esiste nel database. "false" altrimenti.
     */
    public static boolean checkIdBiglietto(String idBiglietto) {
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
        TitoloViaggioEntity titolo = titoloViaggioService.findById(idBiglietto);
        return titolo != null;
    }

    public TitoloViaggioEntity getTitoloViaggioEntity() {
        return titoloViaggioEntity;
    }

    public StoricoAcquistiEntity getStoricoAcquisti() {
        return storicoAcquisti;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    /**
     * Metodo che richiama il metodo getPrezzoTot della RimborsoStrategy.
     *
     * @return "VoucherEntity" se RimborsoStrategy ritorna un voucher. "null" altrimenti.
     */
    public VoucherEntity getRimborso() {
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
        if (rimborsoStrategy.getPrezzoTot(this) == null)
            return null;
        else {
            titoloViaggioService.deleteById(id);
            return rimborsoStrategy.getPrezzoTot(this);
        }
    }
}
