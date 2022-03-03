package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration;

public enum ValoreVoucher {
    _50€,
    _100€,
    _150€,
    _200€;

    @Override
    public String toString(){
        return name().substring(1);
    }
}
