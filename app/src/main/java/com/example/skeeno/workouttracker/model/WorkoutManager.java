package com.example.skeeno.workouttracker.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.skeeno.workouttracker.database.WorkoutBaseHelper;
import com.example.skeeno.workouttracker.database.WorkoutCursorWrapper;
import com.example.skeeno.workouttracker.database.WorkoutDbSchema.WorkoutTable;
import com.example.skeeno.workouttracker.utils.Helper;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.UUID;


/**
 * Created by skeeno on 26/02/2017.
 */

public class WorkoutManager {

    private static WorkoutManager instance;
    private static ArrayList<Workout> mWorkoutList;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    private WorkoutManager(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new WorkoutBaseHelper(mContext).getWritableDatabase();
        mWorkoutList = new ArrayList<>();

        //setUpTempData();
    }

    public static WorkoutManager getInstance(Context context) {
        if (instance == null ) {
            instance = new WorkoutManager(context);
        }
        return instance;
    }

    public ArrayList<Workout> getWorkoutList() {
        WorkoutCursorWrapper cursor = queryWorkouts(null, null);
        mWorkoutList.clear();

        if (cursor == null ) { return null; };

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                mWorkoutList.add(cursor.getWorkout());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return mWorkoutList;
    }

    public void addWorkout(Workout workout) {
        ContentValues values = getContentValues(workout);
        mDatabase.insert(WorkoutTable.NAME, null, values);
    }

    public Workout getWorkout(UUID id) {
        WorkoutCursorWrapper cursor = queryWorkouts(
                WorkoutTable.Columns.WORKOUT_UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getColumnCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return  cursor.getWorkout();
        } finally {
            cursor.close();
        }
    }

    public void updateWorkout(Workout workout) {
        String uuidString = workout.getUuid().toString();
        ContentValues values = getContentValues(workout);

        mDatabase.update(WorkoutTable.NAME, values,
                WorkoutTable.Columns.WORKOUT_UUID + " = ?",
                new String[] { uuidString });

    }

    private static ContentValues getContentValues(Workout workout) {
        ContentValues values = new ContentValues();
        values.put(WorkoutTable.Columns.WORKOUT_UUID, workout.getUuid().toString());
        values.put(WorkoutTable.Columns.WORKOUT_NAME, workout.getName());
        values.put(WorkoutTable.Columns.WORKOUT_DATE, Helper.convertDateToMilis(workout.getDate()));
        values.put(WorkoutTable.Columns.WORKOUT_COMPLETED, workout.isCompleted() ? 1 : 0);
        //TODO add exercise implementation
        return values;
    }

    public ArrayList<Workout> setUpTempData() {
        ArrayList<Workout> list = new ArrayList<>();
        mWorkoutList.add(new Workout(UUID.randomUUID(), "Chest", new GregorianCalendar(2017, 0, 5), false, new ArrayList<Exercise>()));
        mWorkoutList.add(new Workout(UUID.randomUUID(), "Shoulder", new GregorianCalendar(2017, 0, 7), false, new ArrayList<Exercise>()));
        mWorkoutList.add(new Workout(UUID.randomUUID(), "Back", new GregorianCalendar(2017, 0, 9), false, new ArrayList<Exercise>()));
        mWorkoutList.add(new Workout(UUID.randomUUID(), "Bicep", new GregorianCalendar(2017, 0, 11), false, new ArrayList<Exercise>()));
        mWorkoutList.add(new Workout(UUID.randomUUID(), "Tricep", new GregorianCalendar(2017, 0, 13), false, new ArrayList<Exercise>()));
        mWorkoutList.add(new Workout(UUID.randomUUID(), "Legs", new GregorianCalendar(2017, 0, 15), false, new ArrayList<Exercise>()));
        return list;
    }

    public WorkoutCursorWrapper queryWorkouts(String whereClause, String [] whereArgs) {
        Cursor cursor = mDatabase.query(
                WorkoutTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null
        );

        return new WorkoutCursorWrapper(cursor);
    }
}
