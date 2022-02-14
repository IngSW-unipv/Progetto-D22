package it.unipv.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "routes", schema = "trenissimo")
public class RoutesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "route_id", nullable = false, length = 100)
    private String routeId;
    @Basic
    @Column(name = "agency_id", nullable = true, length = 100)
    private String agencyId;
    @Basic
    @Column(name = "route_short_name", nullable = true, length = 100)
    private String routeShortName;
    @Basic
    @Column(name = "route_long_name", nullable = true, length = 100)
    private String routeLongName;
    @Basic
    @Column(name = "route_desc", nullable = true, length = 100)
    private String routeDesc;
    @Basic
    @Column(name = "route_type", nullable = true)
    private Integer routeType;
    @Basic
    @Column(name = "route_url", nullable = true, length = 100)
    private String routeUrl;
    @Basic
    @Column(name = "route_color", nullable = true, length = 100)
    private String routeColor;
    @Basic
    @Column(name = "route_text_color", nullable = true, length = 100)
    private String routeTextColor;
    @ManyToOne
    @JoinColumn(name = "agency_id", referencedColumnName = "agency_id", insertable = false, updatable = false)
    private AgencyEntity agencyByAgencyId;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getRouteShortName() {
        return routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }

    public String getRouteLongName() {
        return routeLongName;
    }

    public void setRouteLongName(String routeLongName) {
        this.routeLongName = routeLongName;
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc;
    }

    public Integer getRouteType() {
        return routeType;
    }

    public void setRouteType(Integer routeType) {
        this.routeType = routeType;
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }

    public String getRouteColor() {
        return routeColor;
    }

    public void setRouteColor(String routeColor) {
        this.routeColor = routeColor;
    }

    public String getRouteTextColor() {
        return routeTextColor;
    }

    public void setRouteTextColor(String routeTextColor) {
        this.routeTextColor = routeTextColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutesEntity that = (RoutesEntity) o;
        return Objects.equals(routeId, that.routeId) && Objects.equals(agencyId, that.agencyId) && Objects.equals(routeShortName, that.routeShortName) && Objects.equals(routeLongName, that.routeLongName) && Objects.equals(routeDesc, that.routeDesc) && Objects.equals(routeType, that.routeType) && Objects.equals(routeUrl, that.routeUrl) && Objects.equals(routeColor, that.routeColor) && Objects.equals(routeTextColor, that.routeTextColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, agencyId, routeShortName, routeLongName, routeDesc, routeType, routeUrl, routeColor, routeTextColor);
    }

    public AgencyEntity getAgencyByAgencyId() {
        return agencyByAgencyId;
    }

    public void setAgencyByAgencyId(AgencyEntity agencyByAgencyId) {
        this.agencyByAgencyId = agencyByAgencyId;
    }
}
