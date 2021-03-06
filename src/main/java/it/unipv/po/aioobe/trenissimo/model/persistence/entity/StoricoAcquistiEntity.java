package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe generata automaticamente mediante il tool OR mapping del framework Hibernate che modellizza le table contenute in database
 *
 * @author ArrayIndexOutOfBoundsException
 */
@Entity
@Table(name = "storico_acquisti", schema = "trenissimo")
public class StoricoAcquistiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "storico_acquisti_id", nullable = false)
    private Integer storicoAcquistiId;
    @Basic
    @Column(name = "username", nullable = true, length = 45)
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
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private AccountEntity accountByUsername;
    @ManyToOne
    @JoinColumn(name = "titolo_viaggio_id", referencedColumnName = "titolo_viaggio_id", insertable = false, updatable = false)
    private TitoloViaggioEntity titoloViaggioByTitoloViaggioId;

    public TitoloViaggioEntity getTitoloViaggioEntity() {
        return titoloViaggioByTitoloViaggioId;
    }

    public void setTitoloViaggioEntity(TitoloViaggioEntity titoloViaggioEntity) {
        this.titoloViaggioByTitoloViaggioId = titoloViaggioEntity;
    }

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

    /**
     * Converte l'acquisto passato per parametro in una StoricoAcquistiEntity per permettere il salvataggio delle informazioni in database
     *
     * @param acquisto istanza della classe Acquisto
     * @return una StoricoAcquistiEntity
     */
    public StoricoAcquistiEntity toStoricoAcquistiEntity(@NotNull Acquisto acquisto) {
        StoricoAcquistiEntity storicoAcquisti = new StoricoAcquistiEntity();
        storicoAcquisti.setTitoloViaggioId(acquisto.getId());
        storicoAcquisti.setPrezzo(acquisto.getPrezzo());
        storicoAcquisti.setDataAcquisto(Timestamp.valueOf(LocalDateTime.now()));
        return storicoAcquisti;
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
