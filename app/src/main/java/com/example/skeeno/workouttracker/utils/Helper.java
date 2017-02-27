package com.example.skeeno.workouttracker.utils;

import java.util.GregorianCalendar;

/**
 * Created by skeeno on 26/02/2017.
 */

public class Helper {

    public static long convertDateToMilis(GregorianCalendar date) {
        return date.getTimeInMillis();
    }

    public static GregorianCalendar convertMilisToDate(long time) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(time);
        return cal;
    }

}
