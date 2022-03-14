package it.unipv.po.aioobe.trenissimo.model;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.VoucherService;
import org.jetbrains.annotations.NotNull;

import java.time.YearMonth;

/**
 * Classe contenente metodi di utilità generica.
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class Utils {

    /**
     * Metodo che converte una stringa contente un tempo, in secondi.
     *
     * @param time in formato "ore:minuti:secondi"
     * @return Integer, i secondi
     */
    public static Integer timeToSeconds(String time) {
        var hh = time.substring(0, 2);
        var mm = time.substring(3, 5);
        var ss = time.substring(6, 8);
        return Integer.parseInt(hh) * 3600 + Integer.parseInt(mm) * 60 + Integer.parseInt(ss);
    }

    /**
     * Metodo che converte i "seconds" passati come parametro, in una stringa che rappresenta un tempo.
     *
     * @param seconds
     * @return stringa nel formato "ore:minuti:secondi"
     */
    public static String secondsToTime(Integer seconds) {
        return secondsToTime(seconds, true);
    }

    /**
     * Metodo che restituisce i "seconds" passati come parametro, in una stringa che rappresenta un tempo. <br>
     * Se showSeconds è true, restituisce "ore:minuti:secondi".<br>
     * se showSeconds è false, restituice "ore:minuti".
     *
     * @param seconds
     * @param showSeconds
     * @return "ore:minuti:secondi" se showSeconds è true. <br>
     * "ore:minuti" se showSeconds è false.
     */
    public static String secondsToTime(Integer seconds, boolean showSeconds) {
        long HH = seconds / 3600;
        long MM = (seconds % 3600) / 60;
        long SS = seconds % 60;
        if (showSeconds) {
            return String.format("%02d:%02d:%02d", HH, MM, SS);
        } else {
            return String.format("%02d:%02d", HH, MM);
        }
    }


    /**
     * Metodo estensione di Math.ceil, restituisce il primo intero superiore del valore di partenza che sia multiplo di 10^(places)
     *
     * @param value  valore di partenza
     * @param places precisione dell'approssimazione
     * @return
     */
    public static double ceil(double value, int places) {
        double factor = Math.pow(10, places);
        value = value * factor;
        long tmp = (long) Math.ceil(value);
        return (double) tmp / factor;
    }

    /**
     * Metodo estensione di Math.floor, restituisce il ultimo intero inferiore del valore di partenza che sia multiplo di 10^(places)
     *
     * @param value  valore di partenza
     * @param places precisione dell'approssimazione
     * @return
     */
    public static double floor(double value, int places) {
        double factor = Math.pow(10, places);
        value = value * factor;
        long tmp = (long) Math.floor(value);
        return (double) tmp / factor;
    }

    /**
     * Metodo generico per controllare che il "dato" fornito come paramtro, abbia un numero di caratteri maggiori di 0.
     * È consigliato l'uso se si vuole verificare che il testo di una TextField non sia vuoto.
     *
     * @param dato
     * @return "true" se la lunghezza del dato fornito è maggiore di 0. <br>
     * "false" altrimenti.
     */
    public static boolean checkDatiGenerico(@NotNull String dato) {
        return dato.length() > 0;
    }

    /**
     * Metodo utilizzato per controllare che l'idVoucher fornito come parametro, esista nel database.
     *
     * @param idVoucher identificativo del voucher.
     * @return "true" se nel database è stato trovato un voucher con l'id fornito come parametro. <br>
     * "false" altrimenti.
     */
    public static boolean checkIdVoucher(String idVoucher) {
        VoucherService voucherService = new VoucherService();
        VoucherEntity voucher = voucherService.findById(idVoucher);
        return voucher != null;

    }

    /**
     * Metodo per controllare il codice CVV della crta di credito inserita.
     *
     * @param cvv Codice di sicurezza della carta.
     * @return "true" se il codice passato come parametro è composto da 3 cifre e contiene soltanto numeri.<br>
     * "false" altrimenti.
     */
    public static boolean checkCVV(@NotNull String cvv) {
        return cvv.length() == 3 && cvv.matches("^[0-9]+$");
    }

    /**
     * Metodo per controllare che il numero di cifre della carta inserita, siano coerenti.
     *
     * @param numero Codice numerico della carta.
     * @return "true" se il numero passato come parametro, ha 16 cifre e contiene solo numeri.<br>
     * "false" altrimenti.
     */
    public static boolean checkNumCarta(@NotNull String numero) {
        return numero.length() == 16 && numero.matches("^[0-9]+$");
    }

    /**
     * Metodo per controllare che la data di scadenza della carta di credito inserita.
     *
     * @param data data della carta.
     * @return "true" la data passata come parametro è successiva a quella odierna (il controllo viene fatto soltanto sul mese) <br>
     * "false" altrimenti.
     */
    public static boolean checkDataScadenza(String data) {
        var date = YearMonth.parse(data);
        return date.isAfter(YearMonth.now());
    }

}
