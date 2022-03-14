package it.unipv.po.aioobe.trenissimo.model.persistence.entity;

import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.VoucherService;

import javax.persistence.*;
import java.util.Objects;

/**
 * Classe generata automaticamente mediante il tool OR mapping del framework Hibernate che modellizza le table contenute in database
 *
 * @author ArrayIndexOutOfBoundsException
 */
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

    /**
     * Genera in maniera univoca l'id del voucher
     */
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

    /**
     * Implementa il metodo astratto ereditato dalla superclasse che effettua il pagamento e salva in database il voucher
     *
     * @return true se il pagamento è andato a buon fine, ritorna false altrimenti
     */
    @Override
    public boolean pagare() {
        try {
            VoucherService voucherService = new VoucherService();
            voucherService.persist(this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "VoucherEntity{" +
                "voucherId='" + voucherId + '\'' +
                ", valore=" + valore +
                '}';
    }

}
