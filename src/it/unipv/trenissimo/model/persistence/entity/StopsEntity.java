package it.unipv.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "stops", schema = "trenissimo")
public class StopsEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "stop_id", nullable = false)
    private int stopId;
    @Basic
    @Column(name = "stop_code", nullable = true, length = 100)
    private String stopCode;
    @Basic
    @Column(name = "stop_name", nullable = true, length = 100)
    private String stopName;
    @Basic
    @Column(name = "stop_desc", nullable = true, length = 100)
    private String stopDesc;
    @Basic
    @Column(name = "stop_lat", nullable = true, precision = 0)
    private Double stopLat;
    @Basic
    @Column(name = "stop_lon", nullable = true, precision = 0)
    private Double stopLon;
    @Basic
    @Column(name = "stop_url", nullable = true, length = 100)
    private String stopUrl;
    @Basic
    @Column(name = "location_type", nullable = true)
    private Integer locationType;
    @Basic
    @Column(name = "parent_station", nullable = true, length = 100)
    private String parentStation;
    @OneToMany(mappedBy = "stopsByStopId")
    private Collection<StopTimesEntity> stopTimesByStopId;

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public String getStopCode() {
        return stopCode;
    }

    public void setStopCode(String stopCode) {
        this.stopCode = stopCode;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopDesc() {
        return stopDesc;
    }

    public void setStopDesc(String stopDesc) {
        this.stopDesc = stopDesc;
    }

    public Double getStopLat() {
        return stopLat;
    }

    public void setStopLat(Double stopLat) {
        this.stopLat = stopLat;
    }

    public Double getStopLon() {
        return stopLon;
    }

    public void setStopLon(Double stopLon) {
        this.stopLon = stopLon;
    }

    public String getStopUrl() {
        return stopUrl;
    }

    public void setStopUrl(String stopUrl) {
        this.stopUrl = stopUrl;
    }

    public Integer getLocationType() {
        return locationType;
    }

    public void setLocationType(Integer locationType) {
        this.locationType = locationType;
    }

    public String getParentStation() {
        return parentStation;
    }

    public void setParentStation(String parentStation) {
        this.parentStation = parentStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopsEntity that = (StopsEntity) o;
        return stopId == that.stopId && Objects.equals(stopCode, that.stopCode) && Objects.equals(stopName, that.stopName) && Objects.equals(stopDesc, that.stopDesc) && Objects.equals(stopLat, that.stopLat) && Objects.equals(stopLon, that.stopLon) && Objects.equals(stopUrl, that.stopUrl) && Objects.equals(locationType, that.locationType) && Objects.equals(parentStation, that.parentStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopId, stopCode, stopName, stopDesc, stopLat, stopLon, stopUrl, locationType, parentStation);
    }

    public Collection<StopTimesEntity> getStopTimesByStopId() {
        return stopTimesByStopId;
    }

    public void setStopTimesByStopId(Collection<StopTimesEntity> stopTimesByStopId) {
        this.stopTimesByStopId = stopTimesByStopId;
    }
}
