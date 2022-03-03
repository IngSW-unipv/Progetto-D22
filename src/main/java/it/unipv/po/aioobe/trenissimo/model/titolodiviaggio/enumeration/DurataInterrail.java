package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration;

public enum DurataInterrail {
    _5_GIORNI_ENTRO_1_MESE,
    _10_GIORNI_ENTRO_2_MESE,
    _15_GIORNI_ENTRO_2_MESI,
    _15_GIORNI,
    _22_GIORNI,
    _1_MESE,
    _2_MESI,
    _3_MESI;

    @Override
    public String toString(){
        return name().substring(1).replace("_", " ").toLowerCase();
    }
}
