package it.unipv.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "stop_times", schema = "trenissimo")
public class StopTimesEntity {
    @Id
    @GeneratedValue
    private UUID phantomId;
    @Basic
    @Column(name = "trip_id", nullable = true)
    private Integer tripId;
    @Basic
    @Column(name = "arrival_time", nullable = true, length = 100)
    private String arrivalTime;
    @Basic
    @Column(name = "departure_time", nullable = true, length = 100)
    private String departureTime;
    @Basic
    @Column(name = "stop_id", nullable = true)
    private Integer stopId;
    @Basic
    @Column(name = "stop_sequence", nullable = true)
    private Integer stopSequence;
    @Basic
    @Column(name = "stop_headsign", nullable = true, length = 100)
    private String stopHeadsign;
    @Basic
    @Column(name = "pickup_type", nullable = true, length = 100)
    private String pickupType;
    @Basic
    @Column(name = "drop_off_type", nullable = true, length = 100)
    private String dropOffType;
    @Basic
    @Column(name = "shape_dist_traveled", nullable = true, length = 100)
    private String shapeDistTraveled;
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", insertable = false, updatable = false)
    private TripsEntity tripsByTripId;
    @ManyToOne
    @JoinColumn(name = "stop_id", referencedColumnName = "stop_id", insertable = false, updatable = false)
    private StopsEntity stopsByStopId;

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    public Integer getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(Integer stopSequence) {
        this.stopSequence = stopSequence;
    }

    public String getStopHeadsign() {
        return stopHeadsign;
    }

    public void setStopHeadsign(String stopHeadsign) {
        this.stopHeadsign = stopHeadsign;
    }

    public String getPickupType() {
        return pickupType;
    }

    public void setPickupType(String pickupType) {
        this.pickupType = pickupType;
    }

    public String getDropOffType() {
        return dropOffType;
    }

    public void setDropOffType(String dropOffType) {
        this.dropOffType = dropOffType;
    }

    public String getShapeDistTraveled() {
        return shapeDistTraveled;
    }

    public void setShapeDistTraveled(String shapeDistTraveled) {
        this.shapeDistTraveled = shapeDistTraveled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopTimesEntity that = (StopTimesEntity) o;
        return Objects.equals(tripId, that.tripId) && Objects.equals(arrivalTime, that.arrivalTime) && Objects.equals(departureTime, that.departureTime) && Objects.equals(stopId, that.stopId) && Objects.equals(stopSequence, that.stopSequence) && Objects.equals(stopHeadsign, that.stopHeadsign) && Objects.equals(pickupType, that.pickupType) && Objects.equals(dropOffType, that.dropOffType) && Objects.equals(shapeDistTraveled, that.shapeDistTraveled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, arrivalTime, departureTime, stopId, stopSequence, stopHeadsign, pickupType, dropOffType, shapeDistTraveled);
    }

    public TripsEntity getTripsByTripId() {
        return tripsByTripId;
    }

    public void setTripsByTripId(TripsEntity tripsByTripId) {
        this.tripsByTripId = tripsByTripId;
    }

    public StopsEntity getStopsByStopId() {
        return stopsByStopId;
    }

    public void setStopsByStopId(StopsEntity stopsByStopId) {
        this.stopsByStopId = stopsByStopId;
    }
}
