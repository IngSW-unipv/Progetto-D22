package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.VoucherService;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "voucher", schema = "trenissimo")
public class VoucherEntity extends Acquisto {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "voucher_id", nullable = false, length = 100)
    private String voucherId;
    @Basic
    @Column(name = "valore", nullable = false, precision = 0)
    private double valore;

    public VoucherEntity() {
        super();
        this.voucherId = "VO" + System.nanoTime();
    }

    @Override
    public String getId() {
        return voucherId;
    }

    @Override
    public double getPrezzo() {
        return valore;
    }

    @Override
    public void setPrezzo(double valore) {
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
    public void pagare() {
        VoucherService voucherService = new VoucherService();
        voucherService.persist(this);
    }

    @Override
    public String toString() {
        return "VoucherEntity{" +
                "voucherId='" + voucherId + '\'' +
                ", valore=" + valore +
                '}';
    }

}
