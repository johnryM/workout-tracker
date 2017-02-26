package com.example.skeeno.workouttracker.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by skeeno on 26/02/2017.
 */

public class WorkoutManager {

    private static WorkoutManager instance;
    private static ArrayList<Workout> workoutList;

    private WorkoutManager() {
        workoutList = new ArrayList<>();
        setUpTempData();
    }

    public static WorkoutManager getInstance () {
        if (instance == null ) {
            instance = new WorkoutManager();
        }
        return instance;
    }

    public ArrayList<Workout> getWorkoutList() {
        return workoutList;
    }

    public ArrayList<Workout> setUpTempData() {
        ArrayList<Workout> list = new ArrayList<>();
        workoutList.add(new Workout("Chest", new GregorianCalendar(2017, 0, 5), false, new ArrayList<Exercise>()));
        workoutList.add(new Workout("Shoulder", new GregorianCalendar(2017, 0, 7), false, new ArrayList<Exercise>()));
        workoutList.add(new Workout("Back", new GregorianCalendar(2017, 0, 9), false, new ArrayList<Exercise>()));
        workoutList.add(new Workout("Bicep", new GregorianCalendar(2017, 0, 11), false, new ArrayList<Exercise>()));
        workoutList.add(new Workout("Tricep", new GregorianCalendar(2017, 0, 13), false, new ArrayList<Exercise>()));
        workoutList.add(new Workout("Legs", new GregorianCalendar(2017, 0, 15), false, new ArrayList<Exercise>()));
        return list;
    }
}
