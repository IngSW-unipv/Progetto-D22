package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "voucher", schema = "trenissimo")
public class VoucherEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "voucher_id", nullable = false, length = 100)
    private String voucherId;
    @Basic
    @Column(name = "valore", nullable = false, precision = 0)
    private double valore;

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId() {
        this.voucherId = "VO" + System.currentTimeMillis();
    }

    public double getValore() {
        return valore;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoucherEntity that = (VoucherEntity) o;
        return Double.compare(that.valore, valore) == 0 && Objects.equals(voucherId, that.voucherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voucherId, valore);
    }

    @Override
    public String toString() {
        return "VoucherEntity{" +
                "voucherId='" + voucherId + '\'' +
                ", valore=" + valore +
                '}';
    }
}
