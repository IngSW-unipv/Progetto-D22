package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "viaggi_preferiti", schema = "trenissimo")
public class ViaggiPreferitiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "viaggio_preferito_id", nullable = false)
    private Integer viaggioPreferitoId;
    @Basic
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "stazione_partenza", nullable = true, length = 45)
    private String stazionePartenza;
    @Basic
    @Column(name = "stazione_arrivo", nullable = true, length = 45)
    private String stazioneArrivo;
    @Basic
    @Column(name = "ora", nullable = true)
    private Time ora;
    @Basic
    @Column(name = "n_adulti", nullable = true)
    private Integer nAdulti;
    @Basic
    @Column(name = "n_ragazzi", nullable = true)
    private Integer nRagazzi;
    @Basic
    @Column(name = "n_bambini", nullable = true)
    private Integer nBambini;
    @Basic
    @Column(name = "n_animali", nullable = true)
    private Integer nAnimali;
    @Basic
    @Column(name = "n_max_cambi", nullable = true)
    private Integer nMaxCambi;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    private AccountEntity accountByUsername;

    public Integer getViaggioPreferitoId() {
        return viaggioPreferitoId;
    }

    public void setViaggioPreferitoId(Integer viaggioPreferitoId) {
        this.viaggioPreferitoId = viaggioPreferitoId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return Objects.equals(viaggioPreferitoId, that.viaggioPreferitoId) && Objects.equals(username, that.username) && Objects.equals(stazionePartenza, that.stazionePartenza) && Objects.equals(stazioneArrivo, that.stazioneArrivo) && Objects.equals(ora, that.ora) && Objects.equals(nAdulti, that.nAdulti) && Objects.equals(nRagazzi, that.nRagazzi) && Objects.equals(nBambini, that.nBambini) && Objects.equals(nAnimali, that.nAnimali) && Objects.equals(nMaxCambi, that.nMaxCambi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(viaggioPreferitoId, username, stazionePartenza, stazioneArrivo, ora, nAdulti, nRagazzi, nBambini, nAnimali, nMaxCambi);
    }

    public AccountEntity getAccountByUsername() {
        return accountByUsername;
    }

    public void setAccountByUsername(AccountEntity accountByUsername) {
        this.accountByUsername = accountByUsername;
    }

    public ViaggiPreferitiEntity toViaggiPreferitiEntity(Viaggio v){
        ViaggiPreferitiEntity viaggiPreferitiEntity = new ViaggiPreferitiEntity();
        viaggiPreferitiEntity.setStazionePartenza(v.getStazionePartenza().getStopName());
        viaggiPreferitiEntity.setStazioneArrivo(v.getStazioneArrivo().getStopName());
        viaggiPreferitiEntity.setOra(Time.valueOf(Utils.secondsToTime(v.getOrarioPartenza()))); // TODO: 04/03/2022 MAGHEGGIO TEMPORANEO
        viaggiPreferitiEntity.setnAdulti(v.getNumAdulti());
        viaggiPreferitiEntity.setnRagazzi(v.getNumRagazzi());
        viaggiPreferitiEntity.setnBambini(v.getNumBambini());
        viaggiPreferitiEntity.setnAnimali(v.getNumAnimali());

        return viaggiPreferitiEntity;
    }

    @Override
    public String toString() {
        return "ViaggiPreferitiEntity{" +
                "viaggioPreferitoId=" + viaggioPreferitoId +
                ", username='" + username + '\'' +
                ", stazionePartenza='" + stazionePartenza + '\'' +
                ", stazioneArrivo='" + stazioneArrivo + '\'' +
                ", ora=" + ora +
                ", nAdulti=" + nAdulti +
                ", nRagazzi=" + nRagazzi +
                ", nBambini=" + nBambini +
                ", nAnimali=" + nAnimali +
                ", nMaxCambi=" + nMaxCambi +
                '}';
    }

}
