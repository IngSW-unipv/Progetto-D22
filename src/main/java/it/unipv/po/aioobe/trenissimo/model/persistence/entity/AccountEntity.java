package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Classe generata automaticamente mediante il tool OR mapping del framework Hibernate che modellizza le table contenute in database
 * @author ArrayIndexOutOfBoundsException
 */
@Entity
@Table(name = "account", schema = "trenissimo")
public class AccountEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "password", nullable = true, length = 45)
    private String password;
    @Basic
    @Column(name = "punti_fedelta", nullable = true)
    private Integer puntiFedelta;
    @OneToOne(mappedBy = "accountByUsername")
    private DatiPersonaliEntity datiPersonaliByUsername;
    @OneToMany(mappedBy = "accountByUsername")
    private Collection<StoricoAcquistiEntity> storicoAcquistisByUsername;
    @OneToMany(mappedBy = "accountByUsername")
    private Collection<ViaggiPreferitiEntity> viaggiPreferitisByUsername;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPuntiFedelta() {
        return puntiFedelta;
    }

    public void setPuntiFedelta(Integer puntiFedelta) {
        this.puntiFedelta = puntiFedelta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(puntiFedelta, that.puntiFedelta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, puntiFedelta);
    }

    public DatiPersonaliEntity getDatiPersonaliByUsername() {
        return datiPersonaliByUsername;
    }

    public void setDatiPersonaliByUsername(DatiPersonaliEntity datiPersonaliByUsername) {
        this.datiPersonaliByUsername = datiPersonaliByUsername;
    }

    public Collection<StoricoAcquistiEntity> getStoricoAcquistisByUsername() {
        return storicoAcquistisByUsername;
    }

    public void setStoricoAcquistisByUsername(Collection<StoricoAcquistiEntity> storicoAcquistisByUsername) {
        this.storicoAcquistisByUsername = storicoAcquistisByUsername;
    }

    public Collection<ViaggiPreferitiEntity> getViaggiPreferitisByUsername() {
        return viaggiPreferitisByUsername;
    }

    public void setViaggiPreferitisByUsername(Collection<ViaggiPreferitiEntity> viaggiPreferitisByUsername) {
        this.viaggiPreferitisByUsername = viaggiPreferitisByUsername;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", puntiFedelta=" + puntiFedelta +
                '}';
    }
}
