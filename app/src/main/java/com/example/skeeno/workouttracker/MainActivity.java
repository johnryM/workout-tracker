package com.example.skeeno.workouttracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.skeeno.workouttracker.activities.WorkoutEditor;
import com.example.skeeno.workouttracker.fragments.RecyclerviewFragment;
import com.example.skeeno.workouttracker.model.WorkoutManager;

public class MainActivity extends AppCompatActivity {

    private WorkoutManager workoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(WorkoutEditor.newInstance(MainActivity.this));
            }
        });

        workoutManager = WorkoutManager.getInstance();

        if (savedInstanceState == null) {

            FragmentManager fm = getSupportFragmentManager();
            RecyclerviewFragment rcFragment = (RecyclerviewFragment) fm.findFragmentById(R.id.fragment_recycler_view_container);

            if (rcFragment == null) {
                rcFragment = RecyclerviewFragment.newInstance(workoutManager.getWorkoutList());
                fm.beginTransaction().add(R.id.content_main, rcFragment).commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
