package it.unipv.po.aioobe.trenissimo.model.viaggio;


import java.time.LocalTime;
import java.util.UUID;

public class ViaggioPreferito {

    private String viaggioPreferitoId;
    private String trenoId;
    private String stazionePartenza;
    private String stazioneArrivo;
    private LocalTime ora;
    private Integer nAdulti;
    private Integer nRagazzi;
    private Integer nBambini;
    private Integer nAnimali;
    private Integer nMaxCambi;

    public ViaggioPreferito(Viaggio v) {

        this.viaggioPreferitoId = UUID.randomUUID().toString();
        this.trenoId = v.getIdTreno();
        this.stazionePartenza = String.valueOf(v.getStazionePartenza());
        this.stazioneArrivo = String.valueOf(v.getStazioneArrivo());
        this.ora = v.getOra();
        this.nAdulti = v.getNumAdulti();
        this.nRagazzi = v.getNumRagazzi();
        this.nBambini = v.getNumBambini();
        this.nAnimali = v.getNumAnimali();
        this.nMaxCambi = v.getNumMaxCambi();
    }

    public String getViaggioPreferitoId(){
        return viaggioPreferitoId;
    }

    public String getTrenoId() {
        return trenoId;
    }

    public void setTrenoId(String trenoId) {
        this.trenoId = trenoId;
    }

    public String getStazionePartenza() {
        return stazionePartenza;
    }

    public void setStazionePartenza(String stazionePartenza) {
        this.stazionePartenza = stazionePartenza;
    }

    public String getStazioneArrivo() {
        return stazioneArrivo;
    }

    public void setStazioneArrivo(String stazioneArrivo) {
        this.stazioneArrivo = stazioneArrivo;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public Integer getnAdulti() {
        return nAdulti;
    }

    public void setnAdulti(Integer nAdulti) {
        this.nAdulti = nAdulti;
    }

    public Integer getnRagazzi() {
        return nRagazzi;
    }

    public void setnRagazzi(Integer nRagazzi) {
        this.nRagazzi = nRagazzi;
    }

    public Integer getnBambini() {
        return nBambini;
    }

    public void setnBambini(Integer nBambini) {
        this.nBambini = nBambini;
    }

    public Integer getnAnimali() {
        return nAnimali;
    }

    public void setnAnimali(Integer nAnimali) {
        this.nAnimali = nAnimali;
    }

    public Integer getnMaxCambi() {
        return nMaxCambi;
    }

    public void setnMaxCambi(Integer nMaxCambi) {
        this.nMaxCambi = nMaxCambi;
    }
}
