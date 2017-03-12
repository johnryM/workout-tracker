package com.example.skeeno.workouttracker.database;

/**
 * Created by skeeno on 26/02/2017.
 */

public class WorkoutDbSchema {
    public static final class WorkoutTable {
        public static final String NAME = "workouts";

        public static final class Columns {
            public static final String WORKOUT_UUID = "uuid";
            public static final String WORKOUT_NAME = "workout_name";
            public static final String WORKOUT_DATE = "workout_date";
            public static final String WORKOUT_COMPLETED = "workout_completed";
        }

    }
}
