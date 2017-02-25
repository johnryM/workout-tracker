package com.example.skeeno.workouttracker.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.skeeno.workouttracker.R;
import com.example.skeeno.workouttracker.fragments.WorkoutEditorFragment;

public class WorkoutEditor extends AppCompatActivity {

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, WorkoutEditor.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_editor);

        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            WorkoutEditorFragment weFragment = (WorkoutEditorFragment) fm.findFragmentById(R.id.fragment_workout_editor_container);
            if (weFragment == null) {
                weFragment =  WorkoutEditorFragment.newInstance("cake", "cake");
                fm.beginTransaction().add(R.id.activity_workout_exercise, weFragment).commit();
            }
        }
    }
}
