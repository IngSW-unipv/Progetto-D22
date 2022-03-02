package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "viaggi_preferiti", schema = "trenissimo")
public class ViaggiPreferitiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "viaggio_preferito_id")
    private String viaggioPreferitoId;
    @Basic
    @Column(name = "account_id")
    private Integer accountId;
    @Basic
    @Column(name = "stazione_partenza")
    private String stazionePartenza;
    @Basic
    @Column(name = "stazione_arrivo")
    private String stazioneArrivo;
    @Basic
    @Column(name = "data")
    private Date data;
    @Basic
    @Column(name = "ora")
    private Time ora;
    @Basic
    @Column(name = "n_adulti")
    private Integer nAdulti;
    @Basic
    @Column(name = "n_ragazzi")
    private Integer nRagazzi;
    @Basic
    @Column(name = "n_bambini")
    private Integer nBambini;
    @Basic
    @Column(name = "n_animali")
    private Integer nAnimali;
    @Basic
    @Column(name = "modalita_viaggio")
    private String modalitaViaggio;
    @Basic
    @Column(name = "n_max_cambi")
    private Integer nMaxCambi;

    public String getViaggioPreferitoId() {
        return viaggioPreferitoId;
    }

    public void setViaggioPreferitoId(String viaggioPreferitoId) {
        this.viaggioPreferitoId = viaggioPreferitoId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
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

    public String getModalitaViaggio() {
        return modalitaViaggio;
    }

    public void setModalitaViaggio(String modalitaViaggio) {
        this.modalitaViaggio = modalitaViaggio;
    }

    public Integer getnMaxCambi() {
        return nMaxCambi;
    }

    public void setnMaxCambi(Integer nMaxCambi) {
        this.nMaxCambi = nMaxCambi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViaggiPreferitiEntity that = (ViaggiPreferitiEntity) o;
        return Objects.equals(viaggioPreferitoId, that.viaggioPreferitoId) && Objects.equals(accountId, that.accountId) && Objects.equals(stazionePartenza, that.stazionePartenza) && Objects.equals(stazioneArrivo, that.stazioneArrivo) && Objects.equals(data, that.data) && Objects.equals(ora, that.ora) && Objects.equals(nAdulti, that.nAdulti) && Objects.equals(nRagazzi, that.nRagazzi) && Objects.equals(nBambini, that.nBambini) && Objects.equals(nAnimali, that.nAnimali) && Objects.equals(modalitaViaggio, that.modalitaViaggio) && Objects.equals(nMaxCambi, that.nMaxCambi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(viaggioPreferitoId, accountId, stazionePartenza, stazioneArrivo, data, ora, nAdulti, nRagazzi, nBambini, nAnimali, modalitaViaggio, nMaxCambi);
    }

    @Override
    public String toString() {
        return "ViaggiPreferitiEntity{" +
                "viaggioPreferitoId='" + viaggioPreferitoId + '\'' +
                ", accountId=" + accountId +
                ", stazionePartenza='" + stazionePartenza + '\'' +
                ", stazioneArrivo='" + stazioneArrivo + '\'' +
                ", data=" + data +
                ", ora=" + ora +
                ", nAdulti=" + nAdulti +
                ", nRagazzi=" + nRagazzi +
                ", nBambini=" + nBambini +
                ", nAnimali=" + nAnimali +
                ", modalitaViaggio='" + modalitaViaggio + '\'' +
                ", nMaxCambi=" + nMaxCambi +
                '}';
    }
}
