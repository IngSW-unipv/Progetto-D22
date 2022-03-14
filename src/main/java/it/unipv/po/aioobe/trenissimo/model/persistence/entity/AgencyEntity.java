package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Classe generata automaticamente mediante il tool OR mapping del framework Hibernate che modellizza le table contenute in database
 *
 * @author ArrayIndexOutOfBoundsException
 */
@Entity
@Table(name = "agency", schema = "trenissimo")
public class
AgencyEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "agency_id", nullable = false, length = 100)
    private String agencyId;
    @Basic
    @Column(name = "agency_name", nullable = true, length = 100)
    private String agencyName;
    @Basic
    @Column(name = "agency_url", nullable = true, length = 100)
    private String agencyUrl;
    @Basic
    @Column(name = "agency_timezone", nullable = true, length = 100)
    private String agencyTimezone;
    @Basic
    @Column(name = "agency_lang", nullable = true, length = 100)
    private String agencyLang;
    @Basic
    @Column(name = "agency_phone", nullable = true, length = 100)
    private String agencyPhone;
    @OneToMany(mappedBy = "agencyByAgencyId")
    private Collection<RoutesEntity> routesByAgencyId;

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyUrl() {
        return agencyUrl;
    }

    public void setAgencyUrl(String agencyUrl) {
        this.agencyUrl = agencyUrl;
    }

    public String getAgencyTimezone() {
        return agencyTimezone;
    }

    public void setAgencyTimezone(String agencyTimezone) {
        this.agencyTimezone = agencyTimezone;
    }

    public String getAgencyLang() {
        return agencyLang;
    }

    public void setAgencyLang(String agencyLang) {
        this.agencyLang = agencyLang;
    }

    public String getAgencyPhone() {
        return agencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        this.agencyPhone = agencyPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgencyEntity that = (AgencyEntity) o;
        return Objects.equals(agencyId, that.agencyId) && Objects.equals(agencyName, that.agencyName) && Objects.equals(agencyUrl, that.agencyUrl) && Objects.equals(agencyTimezone, that.agencyTimezone) && Objects.equals(agencyLang, that.agencyLang) && Objects.equals(agencyPhone, that.agencyPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencyId, agencyName, agencyUrl, agencyTimezone, agencyLang, agencyPhone);
    }

    public Collection<RoutesEntity> getRoutesByAgencyId() {
        return routesByAgencyId;
    }

    public void setRoutesByAgencyId(Collection<RoutesEntity> routesByAgencyId) {
        this.routesByAgencyId = routesByAgencyId;
    }

    @Override
    public String toString() {
        return "AgencyEntity{" +
                "agencyId='" + agencyId + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", agencyUrl='" + agencyUrl + '\'' +
                ", agencyTimezone='" + agencyTimezone + '\'' +
                ", agencyLang='" + agencyLang + '\'' +
                ", agencyPhone='" + agencyPhone + '\'' +
                '}';
    }
}
