package com.example.skeeno.workouttracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skeeno.workouttracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WorkoutEditorFragment extends Fragment {


    @BindView(R.id.edit_text_workout_name)
    EditText mEditTextWorkoutName;
    @BindView(R.id.button_workout_date)
    Button mButtonWorkoutDate;
    @BindView(R.id.checkbox_workout_completed)
    CheckBox mCheckboxWorkoutCompleted;

    Unbinder mUnbinder;

    public WorkoutEditorFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WorkoutEditorFragment newInstance(String param1, String param2) {
        WorkoutEditorFragment fragment = new WorkoutEditorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_editor, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        Toolbar workoutToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(workoutToolBar);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.workout_editor_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save_workout) {
            if (validateWorkout()) {
                Toast.makeText(getContext(), "validated", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateWorkout() {
        String text = mEditTextWorkoutName.getText().toString();
        if (text.length() == 0) {
            Toast.makeText(getContext(), "Its empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
