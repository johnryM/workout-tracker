package com.example.skeeno.workouttracker.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.skeeno.workouttracker.model.Workout;
import com.example.skeeno.workouttracker.utils.Helper;

import java.util.UUID;

import static com.example.skeeno.workouttracker.database.WorkoutDbSchema.*;

/**
 * Created by skeeno on 26/02/2017.
 */

public class WorkoutCursorWrapper extends CursorWrapper {

    public WorkoutCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Workout getWorkout() {
        String uuid = getString(getColumnIndex(WorkoutTable.Columns.WORKOUT_UUID));
        String name = getString(getColumnIndex(WorkoutTable.Columns.WORKOUT_NAME));
        long date = getLong(getColumnIndex(WorkoutTable.Columns.WORKOUT_DATE));
        int completed = getInt(getColumnIndex(WorkoutTable.Columns.WORKOUT_COMPLETED));

        return new Workout(UUID.fromString(uuid), name, Helper.convertMilisToDate(date), completed != 0, null);
    }
}
