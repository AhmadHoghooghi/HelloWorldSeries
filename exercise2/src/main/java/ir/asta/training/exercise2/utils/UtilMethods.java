package ir.asta.training.exercise2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class UtilMethods {
    public static String extractParamFromMap(String name, Map<String, String[]> parameterMap) {
        return parameterMap.get(name) == null ? null : parameterMap.get(name)[0];
    }

    public static Date parseToDate(String utcDateString) throws ParseException {
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return dateFormatLocal.parse(utcDateString);
    }
}
