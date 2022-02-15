package it.unipv.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class StopTimesEntityPK implements Serializable {
    @Column(name = "trip_id", nullable = false)
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripId;
    @Column(name = "stop_id", nullable = false)
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stopId;

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopTimesEntityPK that = (StopTimesEntityPK) o;
        return Objects.equals(tripId, that.tripId) && Objects.equals(stopId, that.stopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, stopId);
    }
}
