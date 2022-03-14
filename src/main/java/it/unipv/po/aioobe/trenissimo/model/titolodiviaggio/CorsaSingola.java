package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.TitoloViaggioService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.TipoTitoloViaggio;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import org.jetbrains.annotations.NotNull;

/**
 * Classe che modellizza un biglietto corsa singola.
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class CorsaSingola extends Acquisto {
    private final TipoTitoloViaggio tipo;
    private double prezzo;
    private final String id;
    private final Viaggio viaggio;

    /**
     * Costruttore utilizzato per creare un biglietto corsa singola.
     *
     * @param tipo
     * @param viaggio
     */
    public CorsaSingola(TipoTitoloViaggio tipo, @NotNull Viaggio viaggio) {
        super();
        this.tipo = tipo;
        this.id = "CS" + System.nanoTime();
        this.viaggio = viaggio;
        this.prezzo = viaggio.getPrezzoTot();
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public TipoTitoloViaggio getTipo() {
        return this.tipo;
    }

    @Override
    public double getPrezzo() {
        return this.prezzo;
    }

    @Override
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Metodo che implementa il metodo astratto della superclasse, Viene richiamato quando si vuole fare un pagamento
     * di un biglietto corsa singola. <br>
     * Il metodo salva nel database il titolo di viaggio, e, se si è loggati, verrà salvato anche nello storico acquisti.
     *
     * @return "true" se il pagamento è andato a buon fine. "false" altrimenti.
     */
    @Override
    public boolean pagare() {
        try {

            StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
            StoricoAcquistiEntity storicoAcquistiEntity = new StoricoAcquistiEntity();
            TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
            TitoloViaggioEntity titoloViaggioEntity = new TitoloViaggioEntity();
            titoloViaggioService.persist(titoloViaggioEntity.toTitoloViaggioEntity(this));
            storicoAcquistiEntity = storicoAcquistiEntity.toStoricoAcquistiEntity(this);
            if (Account.getLoggedIn())
                storicoAcquistiEntity.setUsername(Account.getInstance().getUsername());
            else
                storicoAcquistiEntity.setUsername(null);
            storicoAcquistiService.persist(storicoAcquistiEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

