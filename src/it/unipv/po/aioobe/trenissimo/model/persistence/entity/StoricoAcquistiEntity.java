package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "storico_acquisti", schema = "trenissimo", catalog = "")
public class StoricoAcquistiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "storico_acquisti_id")
    private String storicoAcquistiId;
    @Basic
    @Column(name = "account_id")
    private Integer accountId;
    @Basic
    @Column(name = "titolo_viaggio_id")
    private String titoloViaggioId;
    @Basic
    @Column(name = "prezzo")
    private Double prezzo;
    @Basic
    @Column(name = "data_acquisto")
    private Timestamp dataAcquisto;

    public String getStoricoAcquistiId() {
        return storicoAcquistiId;
    }

    public void setStoricoAcquistiId(String storicoAcquistiId) {
        this.storicoAcquistiId = storicoAcquistiId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getTitoloViaggioId() {
        return titoloViaggioId;
    }

    public void setTitoloViaggioId(String titoloViaggioId) {
        this.titoloViaggioId = titoloViaggioId;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Timestamp getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Timestamp dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoricoAcquistiEntity that = (StoricoAcquistiEntity) o;
        return Objects.equals(storicoAcquistiId, that.storicoAcquistiId) && Objects.equals(accountId, that.accountId) && Objects.equals(titoloViaggioId, that.titoloViaggioId) && Objects.equals(prezzo, that.prezzo) && Objects.equals(dataAcquisto, that.dataAcquisto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storicoAcquistiId, accountId, titoloViaggioId, prezzo, dataAcquisto);
    }
}
