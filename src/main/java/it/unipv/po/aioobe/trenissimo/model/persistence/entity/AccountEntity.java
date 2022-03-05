package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
