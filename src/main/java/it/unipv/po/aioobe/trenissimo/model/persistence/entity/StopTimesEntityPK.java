package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Classe generata automaticamente dal framework Hibernate che modellizza la chiave primaria composta della table StopTimes in database
 * @author ArrayIndexOutOfBoundsException
 */
public class StopTimesEntityPK implements Serializable {
    @Column(name = "trip_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;
    @Column(name = "stop_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stopId;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopTimesEntityPK that = (StopTimesEntityPK) o;
        return tripId == that.tripId && stopId == that.stopId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, stopId);
    }
}
