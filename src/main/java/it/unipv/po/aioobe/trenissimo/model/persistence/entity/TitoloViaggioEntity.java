package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.CorsaSingola;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

/**
 * Classe generata automaticamente mediante il tool OR mapping del framework Hibernate che modellizza le table contenute in database
 *
 * @author ArrayIndexOutOfBoundsException
 */
@Entity
@Table(name = "titolo_viaggio", schema = "trenissimo")
public class TitoloViaggioEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "titolo_viaggio_id", nullable = false, length = 100)
    private String titoloViaggioId;
    @Basic
    @Column(name = "stazione_partenza", nullable = true, length = 45)
    private String stazionePartenza;
    @Basic
    @Column(name = "stazione_arrivo", nullable = true, length = 45)
    private String stazioneArrivo;
    @Basic
    @Column(name = "data_partenza", nullable = true)
    private Date dataPartenza;
    @Basic
    @Column(name = "data_arrivo", nullable = true)
    private Date dataArrivo;
    @Basic
    @Column(name = "ora_partenza", nullable = true)
    private Time oraPartenza;
    @Basic
    @Column(name = "ora_arrivo", nullable = true)
    private Time oraArrivo;
    @OneToMany(mappedBy = "titoloViaggioByTitoloViaggioId")
    private Collection<StoricoAcquistiEntity> storicoAcquistisByTitoloViaggioId;
    @Basic
    @Column(name = "num_adulti")
    private Integer numAdulti;
    @Basic
    @Column(name = "num_ragazzi")
    private Integer numRagazzi;
    @Basic
    @Column(name = "num_bambini")
    private Integer numBambini;
    @Basic
    @Column(name = "num_animali")
    private Integer numAnimali;

    public String getTitoloViaggioId() {
        return titoloViaggioId;
    }

    public void setTitoloViaggioId(String titoloViaggioId) {
        this.titoloViaggioId = titoloViaggioId;
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

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public Date getDataArrivo() {
        return dataArrivo;
    }

    public void setDataArrivo(Date dataArrivo) {
        this.dataArrivo = dataArrivo;
    }

    public Time getOraPartenza() {
        return oraPartenza;
    }

    public void setOraPartenza(Time oraPartenza) {
        this.oraPartenza = oraPartenza;
    }

    public Time getOraArrivo() {
        return oraArrivo;
    }

    public void setOraArrivo(Time oraArrivo) {
        this.oraArrivo = oraArrivo;
    }

    public Integer getNumAdulti() {
        return numAdulti;
    }

    public void setNumAdulti(Integer numAdulti) {
        this.numAdulti = numAdulti;
    }

    public Integer getNumRagazzi() {
        return numRagazzi;
    }

    public void setNumRagazzi(Integer numRagazzi) {
        this.numRagazzi = numRagazzi;
    }

    public Integer getNumBambini() {
        return numBambini;
    }

    public void setNumBambini(Integer numBambini) {
        this.numBambini = numBambini;
    }

    public Integer getNumAnimali() {
        return numAnimali;
    }

    public void setNumAnimali(Integer numAnimali) {
        this.numAnimali = numAnimali;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitoloViaggioEntity that = (TitoloViaggioEntity) o;
        return Objects.equals(titoloViaggioId, that.titoloViaggioId) && Objects.equals(stazionePartenza, that.stazionePartenza) && Objects.equals(stazioneArrivo, that.stazioneArrivo) && Objects.equals(dataPartenza, that.dataPartenza) && Objects.equals(dataArrivo, that.dataArrivo) && Objects.equals(oraPartenza, that.oraPartenza) && Objects.equals(oraArrivo, that.oraArrivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titoloViaggioId, stazionePartenza, stazioneArrivo, dataPartenza, dataArrivo, oraPartenza, oraArrivo);
    }

    public Collection<StoricoAcquistiEntity> getStoricoAcquistisByTitoloViaggioId() {
        return storicoAcquistisByTitoloViaggioId;
    }

    public void setStoricoAcquistisByTitoloViaggioId(Collection<StoricoAcquistiEntity> storicoAcquistisByTitoloViaggioId) {
        this.storicoAcquistisByTitoloViaggioId = storicoAcquistisByTitoloViaggioId;
    }

    /**
     * Converte il biglietto passato per parametro in una TitoloViaggioEntity per permettere il salvataggio delle informazioni in database
     *
     * @param biglietto istanza della classe CorsaSingola
     * @return
     */
    public TitoloViaggioEntity toTitoloViaggioEntity(@NotNull CorsaSingola biglietto) {
        TitoloViaggioEntity titoloViaggioEntity = new TitoloViaggioEntity();
        titoloViaggioEntity.setTitoloViaggioId(biglietto.getId());
        titoloViaggioEntity.setStazionePartenza(biglietto.getViaggio().getStazionePartenza().getStopName());
        titoloViaggioEntity.setStazioneArrivo(biglietto.getViaggio().getStazioneArrivo().getStopName());
        titoloViaggioEntity.setDataPartenza(Date.valueOf(biglietto.getViaggio().getDataPartenza()));
        titoloViaggioEntity.setDataArrivo(Date.valueOf(biglietto.getViaggio().getDataPartenza()));
        titoloViaggioEntity.setOraPartenza(Time.valueOf(Utils.secondsToTime(biglietto.getViaggio().getOrarioPartenza())));
        titoloViaggioEntity.setOraArrivo(Time.valueOf(Utils.secondsToTime(biglietto.getViaggio().getOrarioArrivo())));
        titoloViaggioEntity.setNumAdulti(biglietto.getViaggio().getNumAdulti());
        titoloViaggioEntity.setNumRagazzi(biglietto.getViaggio().getNumRagazzi());
        titoloViaggioEntity.setNumBambini(biglietto.getViaggio().getNumBambini());
        titoloViaggioEntity.setNumAnimali(biglietto.getViaggio().getNumAnimali());
        return titoloViaggioEntity;
    }

    @Override
    public String toString() {
        return "TitoloViaggioEntity{" +
                "titoloViaggioId='" + titoloViaggioId + '\'' +
                ", stazionePartenza='" + stazionePartenza + '\'' +
                ", stazioneArrivo='" + stazioneArrivo + '\'' +
                ", dataPartenza=" + dataPartenza +
                ", dataArrivo=" + dataArrivo +
                ", oraPartenza=" + oraPartenza +
                ", oraArrivo=" + oraArrivo +
                ", numAdulti=" + numAdulti +
                ", numRagazzi=" + numRagazzi +
                ", numBambini=" + numBambini +
                ", numAnimali=" + numAnimali +
                '}';
    }
}
