package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration;

public enum DurataInterrail {
    _5_GIORNI_ENTRO_1_MESE("5 giorni entro 1 mese"),
    _10_GIORNI_ENTRO_2_MESE("10 giorni entro 2 mese"),
    _15_GIORNI_ENTRO_2_MESI("15 giorni entro 2 mesi "),
    _15_GIORNI("15 giorni"),
    _22_GIORNI("22 giorni"),
    _1_MESE("1 mese"),
    _2_MESI("2 mesi"),
    _3_MESI("3 mesi");

    public final String label;

    private DurataInterrail(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}
