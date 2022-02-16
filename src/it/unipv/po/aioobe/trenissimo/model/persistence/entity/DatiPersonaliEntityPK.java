package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class DatiPersonaliEntityPK implements Serializable {
    @Column(name = "nome")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nome;
    @Column(name = "cognome")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cognome;
    @Column(name = "data_nascita")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Date dataNascita;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatiPersonaliEntityPK that = (DatiPersonaliEntityPK) o;
        return Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome) && Objects.equals(dataNascita, that.dataNascita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, dataNascita);
    }
}
