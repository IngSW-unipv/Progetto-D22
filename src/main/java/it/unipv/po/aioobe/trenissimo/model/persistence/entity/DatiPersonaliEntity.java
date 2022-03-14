package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Classe generata automaticamente mediante il tool OR mapping del framework Hibernate che modellizza le table contenute in database
 *
 * @author ArrayIndexOutOfBoundsException
 */
@Entity
@Table(name = "dati_personali", schema = "trenissimo")
public class DatiPersonaliEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;
    @Basic
    @Column(name = "cognome", nullable = false, length = 45)
    private String cognome;
    @Basic
    @Column(name = "data_nascita", nullable = false)
    private Date dataNascita;
    @Basic
    @Column(name = "mail", nullable = false, length = 45)
    private String mail;
    @Basic
    @Column(name = "via", nullable = true, length = 255)
    private String via;
    @Basic
    @Column(name = "civico", nullable = true, length = 45)
    private String civico;
    @Basic
    @Column(name = "citta", nullable = true, length = 255)
    private String citta;
    @Basic
    @Column(name = "cap", nullable = true)
    private Integer cap;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    private AccountEntity accountByUsername;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatiPersonaliEntity that = (DatiPersonaliEntity) o;
        return Objects.equals(username, that.username) && Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome) && Objects.equals(dataNascita, that.dataNascita) && Objects.equals(mail, that.mail) && Objects.equals(via, that.via) && Objects.equals(civico, that.civico) && Objects.equals(citta, that.citta) && Objects.equals(cap, that.cap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, nome, cognome, dataNascita, mail, via, civico, citta, cap);
    }

    public AccountEntity getAccountByUsername() {
        return accountByUsername;
    }

    public void setAccountByUsername(AccountEntity accountByUsername) {
        this.accountByUsername = accountByUsername;
    }

    @Override
    public String toString() {
        return "DatiPersonaliEntity{" +
                "username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", mail='" + mail + '\'' +
                ", via='" + via + '\'' +
                ", civico='" + civico + '\'' +
                ", citta='" + citta + '\'' +
                ", cap=" + cap +
                '}';
    }
}
