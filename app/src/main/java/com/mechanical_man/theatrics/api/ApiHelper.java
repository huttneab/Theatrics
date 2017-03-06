package com.mechanical_man.theatrics.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mechanical Man, LLC on 3/6/17. Theatrics
 */

public class ApiHelper {

    public static String getReleaseYear(String releaseDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD", Locale.US);

        String year = "";
        try {
            Date date = dateFormat.parse(releaseDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = String.valueOf(calendar.get(Calendar.YEAR));
        } catch (ParseException e) {
            // do nothing, we will just end up returning an empty string
        }

        return year;
    }
}
