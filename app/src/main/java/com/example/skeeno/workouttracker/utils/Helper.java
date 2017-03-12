package com.example.skeeno.workouttracker.utils;

import com.example.skeeno.workouttracker.model.Workout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

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

    public static Workout getDefaultWorkout() {
        return new Workout(UUID.randomUUID(),
                "",
                Helper.convertMilisToDate(System.currentTimeMillis()),
                false,
                new ArrayList<>());
    }

    public static String formatDate(Date date) {
        String pattern = "MMMM dd, yyyy";
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        return sd.format(date);
    }

}
