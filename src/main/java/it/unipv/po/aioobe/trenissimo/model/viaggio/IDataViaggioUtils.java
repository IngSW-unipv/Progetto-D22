package it.unipv.po.aioobe.trenissimo.model.viaggio;

import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.ModalitaViaggio;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IDataViaggioUtils {
    public void setNumMaxCambi(int cambi);
    public void setModalitaViaggio(ModalitaViaggio modalitaViaggio);
    public void setNumAnimali(int animali);
    public void setNumBambini(int bambini);
    public void setNumRagazzi(int ragazzi);
    public void setNumAdulti(int adulti);
    public void setOra(LocalTime ora);
    public void setData(LocalDate data);

    public int getNumMaxCambi();
    public ModalitaViaggio getModalitaViaggio();
    public int getNumAnimali();
    public int getNumBambini();
    public int getNumRagazzi();
    public int getNumAdulti();
    public LocalTime getOra();
    public LocalDate getData();
}
