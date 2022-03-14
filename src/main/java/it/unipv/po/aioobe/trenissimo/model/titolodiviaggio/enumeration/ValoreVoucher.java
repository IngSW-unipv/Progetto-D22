package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration;

/**
 * Enumeration che definisce il valore numerico del voucher regalo (o Gift Card).
 *
 * @author ArrayIndexOutOfBoundsException
 */
public enum ValoreVoucher {
    _50€("50€"),
    _100€("100€"),
    _150€("150€"),
    _200€("200€");

    public final String label;

    /**
     * Metodo che associa un'etichetta (label) all'elemento della enum.
     *
     * @param label
     */
    ValoreVoucher(String label) {
        this.label = label;
    }

    /**
     * Metodo che stampa l'etichetta associata alla enumeration (non il valore della enum).
     *
     * @return
     */
    @Override
    public String toString() {
        return label;
    }

}
