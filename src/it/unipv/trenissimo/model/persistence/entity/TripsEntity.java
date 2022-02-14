package it.unipv.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "trips", schema = "trenissimo")
public class TripsEntity {
    @Basic
    @Column(name = "route_id", nullable = true, length = 100)
    private String routeId;
    @Basic
    @Column(name = "service_id", nullable = true)
    private Long serviceId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "trip_id", nullable = false)
    private int tripId;
    @Basic
    @Column(name = "trip_headsign", nullable = true, length = 100)
    private String tripHeadsign;
    @Basic
    @Column(name = "trip_short_name", nullable = true)
    private Integer tripShortName;
    @Basic
    @Column(name = "direction_id", nullable = true)
    private Integer directionId;
    @Basic
    @Column(name = "block_id", nullable = true, length = 100)
    private String blockId;
    @Basic
    @Column(name = "shape_id", nullable = true, length = 100)
    private String shapeId;
    @OneToMany(mappedBy = "tripsByTripId")
    private Collection<StopTimesEntity> stopTimesByTripId;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTripHeadsign() {
        return tripHeadsign;
    }

    public void setTripHeadsign(String tripHeadsign) {
        this.tripHeadsign = tripHeadsign;
    }

    public Integer getTripShortName() {
        return tripShortName;
    }

    public void setTripShortName(Integer tripShortName) {
        this.tripShortName = tripShortName;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripsEntity that = (TripsEntity) o;
        return tripId == that.tripId && Objects.equals(routeId, that.routeId) && Objects.equals(serviceId, that.serviceId) && Objects.equals(tripHeadsign, that.tripHeadsign) && Objects.equals(tripShortName, that.tripShortName) && Objects.equals(directionId, that.directionId) && Objects.equals(blockId, that.blockId) && Objects.equals(shapeId, that.shapeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, serviceId, tripId, tripHeadsign, tripShortName, directionId, blockId, shapeId);
    }

    public Collection<StopTimesEntity> getStopTimesByTripId() {
        return stopTimesByTripId;
    }

    public void setStopTimesByTripId(Collection<StopTimesEntity> stopTimesByTripId) {
        this.stopTimesByTripId = stopTimesByTripId;
    }
}
