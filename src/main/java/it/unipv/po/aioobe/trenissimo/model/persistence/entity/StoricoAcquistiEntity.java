package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "storico_acquisti", schema = "trenissimo")
public class StoricoAcquistiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "storico_acquisti_id", nullable = false)
    private Integer storicoAcquistiId;
    @Basic
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "titolo_viaggio_id", nullable = true, length = 100)
    private String titoloViaggioId;
    @Basic
    @Column(name = "prezzo", nullable = true, precision = 0)
    private Double prezzo;
    @Basic
    @Column(name = "data_acquisto", nullable = true)
    private Timestamp dataAcquisto;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    private AccountEntity accountByUsername;

    public Integer getStoricoAcquistiId() {
        return storicoAcquistiId;
    }

    public void setStoricoAcquistiId(Integer storicoAcquistiId) {
        this.storicoAcquistiId = storicoAcquistiId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return Objects.equals(storicoAcquistiId, that.storicoAcquistiId) && Objects.equals(username, that.username) && Objects.equals(titoloViaggioId, that.titoloViaggioId) && Objects.equals(prezzo, that.prezzo) && Objects.equals(dataAcquisto, that.dataAcquisto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storicoAcquistiId, username, titoloViaggioId, prezzo, dataAcquisto);
    }

    public AccountEntity getAccountByUsername() {
        return accountByUsername;
    }

    public void setAccountByUsername(AccountEntity accountByUsername) {
        this.accountByUsername = accountByUsername;
    }

    @Override
    public String toString() {
        return "StoricoAcquistiEntity{" +
                "storicoAcquistiId=" + storicoAcquistiId +
                ", username='" + username + '\'' +
                ", titoloViaggioId='" + titoloViaggioId + '\'' +
                ", prezzo=" + prezzo +
                ", dataAcquisto=" + dataAcquisto +
                '}';
    }
}
