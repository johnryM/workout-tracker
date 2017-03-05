package com.example.skeeno.workouttracker.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.skeeno.workouttracker.database.WorkoutBaseHelper;
import com.example.skeeno.workouttracker.database.WorkoutCursorWrapper;
import com.example.skeeno.workouttracker.database.WorkoutDbSchema.WorkoutTable;
import com.example.skeeno.workouttracker.utils.Helper;
import com.example.skeeno.workouttracker.utils.RxUtils;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Callable;

import rx.Observable;


/**
 * Created by skeeno on 26/02/2017.
 */

public class WorkoutManager {

    public static final String TAG = WorkoutManager.class.getSimpleName();

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


    public Observable<ArrayList<Workout>> getObservableWorkoutData() {
        return RxUtils.makeObservable(getCallableData(getWorkoutList()));
    }

    private ArrayList<Workout> getWorkoutList() {
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

    private Callable<ArrayList<Workout>> getCallableData(final ArrayList<Workout> workouts) {
        return  new Callable<ArrayList<Workout>>() {
            @Override
            public ArrayList<Workout> call() throws Exception {
                return workouts;
            }
        };
    }
}
