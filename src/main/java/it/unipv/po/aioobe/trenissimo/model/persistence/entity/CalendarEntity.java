package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "calendar", schema = "trenissimo")
public class CalendarEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "service_id", nullable = false)
    private long serviceId;
    @Basic
    @Column(name = "monday", nullable = true)
    private Integer monday;
    @Basic
    @Column(name = "tuesday", nullable = true)
    private Integer tuesday;
    @Basic
    @Column(name = "wednesday", nullable = true)
    private Integer wednesday;
    @Basic
    @Column(name = "thursday", nullable = true)
    private Integer thursday;
    @Basic
    @Column(name = "friday", nullable = true)
    private Integer friday;
    @Basic
    @Column(name = "saturday", nullable = true)
    private Integer saturday;
    @Basic
    @Column(name = "sunday", nullable = true)
    private Integer sunday;
    @Basic
    @Column(name = "start_date", nullable = true)
    private Integer startDate;
    @Basic
    @Column(name = "end_date", nullable = true)
    private Integer endDate;
    @OneToMany(mappedBy = "calendarByServiceId")
    private Collection<CalendarDatesEntity> calendarDatesByServiceId;

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getMonday() {
        return monday;
    }

    public void setMonday(Integer monday) {
        this.monday = monday;
    }

    public Integer getTuesday() {
        return tuesday;
    }

    public void setTuesday(Integer tuesday) {
        this.tuesday = tuesday;
    }

    public Integer getWednesday() {
        return wednesday;
    }

    public void setWednesday(Integer wednesday) {
        this.wednesday = wednesday;
    }

    public Integer getThursday() {
        return thursday;
    }

    public void setThursday(Integer thursday) {
        this.thursday = thursday;
    }

    public Integer getFriday() {
        return friday;
    }

    public void setFriday(Integer friday) {
        this.friday = friday;
    }

    public Integer getSaturday() {
        return saturday;
    }

    public void setSaturday(Integer saturday) {
        this.saturday = saturday;
    }

    public Integer getSunday() {
        return sunday;
    }

    public void setSunday(Integer sunday) {
        this.sunday = sunday;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarEntity that = (CalendarEntity) o;
        return serviceId == that.serviceId && Objects.equals(monday, that.monday) && Objects.equals(tuesday, that.tuesday) && Objects.equals(wednesday, that.wednesday) && Objects.equals(thursday, that.thursday) && Objects.equals(friday, that.friday) && Objects.equals(saturday, that.saturday) && Objects.equals(sunday, that.sunday) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, monday, tuesday, wednesday, thursday, friday, saturday, sunday, startDate, endDate);
    }

    public Collection<CalendarDatesEntity> getCalendarDatesByServiceId() {
        return calendarDatesByServiceId;
    }

    public void setCalendarDatesByServiceId(Collection<CalendarDatesEntity> calendarDatesByServiceId) {
        this.calendarDatesByServiceId = calendarDatesByServiceId;
    }

    @Override
    public String toString() {
        return "CalendarEntity{" +
                "serviceId=" + serviceId +
                ", monday=" + monday +
                ", tuesday=" + tuesday +
                ", wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                ", saturday=" + saturday +
                ", sunday=" + sunday +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
