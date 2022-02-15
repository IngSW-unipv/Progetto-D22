package it.unipv.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "calendar_dates", schema = "trenissimo")
public class CalendarDatesEntity {
    @Basic
    @Column(name = "service_id", nullable = true)
    private Long serviceId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "date", nullable = false)
    private int date;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
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
        return date == that.date && Objects.equals(serviceId, that.serviceId) && Objects.equals(exceptionType, that.exceptionType);
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
