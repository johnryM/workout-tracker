package com.example.skeeno.workouttracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.skeeno.workouttracker.utils.Helper;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Created by skeeno on 11/02/2017.
 */

public class Workout implements Parcelable {
    private String name;
    private GregorianCalendar date;
    private boolean isCompleted;
    private ArrayList<Exercise> exercises;
    private UUID uuid;

    public Workout(UUID id) {
        uuid = id;
    }

    public Workout(UUID id, String name, GregorianCalendar date, boolean isCompleted, ArrayList<Exercise> exercises) {
        uuid = id;
        this.name = name;
        this.date = date;
        this.isCompleted = isCompleted;
        this.exercises = exercises;
    }

    protected Workout(Parcel in) {
        name = in.readString();
        date = Helper.convertMilisToDate(in.readLong());
        isCompleted = in.readByte() != 0;
        in.readTypedList(exercises, Exercise.CREATOR);
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(Helper.convertDateToMilis(date));
        dest.writeByte((byte) (isCompleted ? 1 : 0));
        dest.writeTypedList(exercises);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        if (exercises == null) {
            exercises = new ArrayList<>();
        }
        exercises.add(exercise);
    }

    public UUID getUuid() {
        return uuid;
    }
}
