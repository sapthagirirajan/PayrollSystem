package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDateTime() {
        return dateTimeFormat.format(new Date());
    }
}
