package com.example.skeeno.workouttracker.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.skeeno.workouttracker.R;
import com.example.skeeno.workouttracker.fragments.WorkoutEditorFragment;
import com.example.skeeno.workouttracker.model.Workout;

public class WorkoutEditor extends AppCompatActivity {

    public static final String EXTRA_WORKOUT = "extra_workout";

    public static Intent newInstance(Context context, Workout workout) {
        Intent intent = new Intent(context, WorkoutEditor.class);
        intent.putExtra(EXTRA_WORKOUT, workout);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_editor);

        Workout workout = getIntent().getParcelableExtra(EXTRA_WORKOUT);

        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            WorkoutEditorFragment weFragment = (WorkoutEditorFragment) fm.findFragmentById(R.id.fragment_workout_editor_container);
            if (weFragment == null) {
                weFragment =  WorkoutEditorFragment.newInstance(workout);
                fm.beginTransaction().add(R.id.activity_workout_exercise, weFragment).commit();
            }
        }
    }
}
