package it.unipv.trenissimo.model.persistence.entity;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "calendar_dates", schema = "trenissimo")
public class CalendarDatesEntity {
    @Id
    @GeneratedValue
    private UUID phantomId;
    @Basic
    @Column(name = "service_id", nullable = true)
    private Long serviceId;
    @Basic
    @Column(name = "date", nullable = true)
    private Integer date;
    @Basic
    @Column(name = "exception_type", nullable = true)
    private Integer exceptionType;
    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", insertable = false, updatable = false)
    private CalendarEntity calendarByServiceId;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(Integer exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarDatesEntity that = (CalendarDatesEntity) o;
        return Objects.equals(serviceId, that.serviceId) && Objects.equals(date, that.date) && Objects.equals(exceptionType, that.exceptionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, date, exceptionType);
    }

    public CalendarEntity getCalendarByServiceId() {
        return calendarByServiceId;
    }

    public void setCalendarByServiceId(CalendarEntity calendarByServiceId) {
        this.calendarByServiceId = calendarByServiceId;
    }
}
