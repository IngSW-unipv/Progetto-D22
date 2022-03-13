package it.unipv.po.aioobe.trenissimo.model;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.VoucherService;
import org.jetbrains.annotations.NotNull;

import java.time.YearMonth;

//TODO: non in UML
public class Utils {
    public static Integer timeToSeconds(String time) {
        var hh = time.substring(0,2);
        var mm = time.substring(3,5);
        var ss = time.substring(6,8);
        return Integer.parseInt(hh) * 3600 + Integer.parseInt(mm) * 60 + Integer.parseInt(ss);
    }

    public static String secondsToTime(Integer seconds){
        return secondsToTime(seconds, true);
    }

    public static String secondsToTime(Integer seconds, boolean showSeconds){
        long HH = seconds / 3600;
        long MM = (seconds % 3600) / 60;
        long SS = seconds % 60;
        if (showSeconds){
            return String.format("%02d:%02d:%02d", HH, MM, SS);
        } else {
            return String.format("%02d:%02d", HH, MM);
        }
    }

    public static double ceil(double value, int places) {
        double factor =  Math.pow(10, places);
        value = value * factor;
        long tmp = (long)Math.ceil(value);
        return (double) tmp / factor;
    }

    public static double floor(double value, int places) {
        double factor =  Math.pow(10, places);
        value = value * factor;
        long tmp = (long)Math.floor(value);
        return (double) tmp / factor;
    }

    public static boolean checkDatiGenerico(@NotNull String dato){
        return dato.length() > 0 ;
    }

    public static boolean checkIdVoucher(String idVoucher){
        VoucherService voucherService = new VoucherService();
        VoucherEntity voucher =  voucherService.findById(idVoucher);
        return  voucher != null;

    }

    public static boolean checkCVV(@NotNull String cvv){
        return cvv.length() == 3 && cvv.matches("^[0-9]+$");
    }

    public static boolean checkNumCarta(@NotNull String numero){
        return numero.length() == 16 && numero.matches("^[0-9]+$");
    }

    public static boolean checkDataScadenza(String data){
        var date = YearMonth.parse(data);
        if (date.isAfter(YearMonth.now()))
            return true;
        else
            return false;
    }

}
