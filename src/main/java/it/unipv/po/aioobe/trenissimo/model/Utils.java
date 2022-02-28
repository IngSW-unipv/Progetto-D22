package it.unipv.po.aioobe.trenissimo.model;

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
}
