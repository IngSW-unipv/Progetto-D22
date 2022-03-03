package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "dati_personali", schema = "trenissimo", catalog = "")
public class DatiPersonaliEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_id")
    private Integer accountId;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "cognome")
    private String cognome;
    @Basic
    @Column(name = "data_nascita")
    private Date dataNascita;
    @Basic
    @Column(name = "via")
    private String via;
    @Basic
    @Column(name = "civico")
    private Integer civico;
    @Basic
    @Column(name = "citta")
    private String citta;
    @Basic
    @Column(name = "cap")
    private Integer cap;
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

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public Integer getCivico() {
        return civico;
    }

    public void setCivico(Integer civico) {
        this.civico = civico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
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
        return Objects.equals(accountId, that.accountId) && Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome) && Objects.equals(dataNascita, that.dataNascita) && Objects.equals(via, that.via) && Objects.equals(civico, that.civico) && Objects.equals(citta, that.citta) && Objects.equals(cap, that.cap) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, nome, cognome, dataNascita, via, civico, citta, cap, mail);
    }

    @Override
    public String toString() {
        return "DatiPersonaliEntity{" +
                "accountId=" + accountId +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", via='" + via + '\'' +
                ", civico=" + civico +
                ", citta='" + citta + '\'' +
                ", cap=" + cap +
                ", mail='" + mail + '\'' +
                '}';
    }
}
