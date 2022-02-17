package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "dati_personali", schema = "trenissimo")
public class DatiPersonaliEntity {
    @Id
    @Column(name = "account_id")
    private Integer accountId;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "data_nascita")
    private Date dataNascita;
    @Basic
    @Column(name = "indirizzo")
    private String indirizzo;
    @Basic
    @Column(name = "mail")
    private String mail;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatiPersonaliEntity that = (DatiPersonaliEntity) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome) && Objects.equals(dataNascita, that.dataNascita) && Objects.equals(indirizzo, that.indirizzo) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, nome, cognome, dataNascita, indirizzo, mail);
    }

    @Override
    public String toString() {
        return "DatiPersonaliEntity{" +
                "accountId=" + accountId +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", indirizzo='" + indirizzo + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
