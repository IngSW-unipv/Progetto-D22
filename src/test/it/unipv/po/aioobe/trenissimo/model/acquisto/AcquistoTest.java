package it.unipv.po.aioobe.trenissimo.model.acquisto;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class AcquistoTest {

    @Test
    public void pagare() {
        boolean result = true;
        List<Acquisto> carrello = new ArrayList<>();
        VoucherEntity v1 = new VoucherEntity();
        v1.setPrezzo(10.0);
        VoucherEntity v2 = new VoucherEntity();
        v2.setPrezzo(100.0);
        VoucherEntity v3 = new VoucherEntity();
        v3.setPrezzo(10.0);
        carrello.add(v1);
        carrello.add(v2);
        carrello.add(v3);
        for(Acquisto a:carrello){
            result = result && a.pagare();
        }
        assertTrue(result);
    }
}