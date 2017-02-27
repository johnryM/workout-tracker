package com.example.skeeno.workouttracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.skeeno.workouttracker.database.WorkoutDbSchema.WorkoutTable;

/**
 * Created by skeeno on 26/02/2017.
 */


public class WorkoutBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "workoutBase.db";

    public WorkoutBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + WorkoutTable.NAME + "(" +
        " _id integer primary key autoincrement, " +
                WorkoutTable.Columns.WORKOUT_UUID + ", " +
                WorkoutTable.Columns.WORKOUT_NAME + ", " +
                WorkoutTable.Columns.WORKOUT_DATE + ", " +
                WorkoutTable.Columns.WORKOUT_COMPLETED +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
