package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration;

public enum ValoreVoucher {
    _50€("50€"),
    _100€("100€"),
    _150€("150€"),
    _200€("200€");

    public final String label;

    private ValoreVoucher(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }

}
