package com.example.skeeno.workouttracker.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.example.skeeno.workouttracker.model.Workout;
import com.example.skeeno.workouttracker.model.WorkoutManager;
import com.example.skeeno.workouttracker.utils.Helper;

import java.util.GregorianCalendar;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WorkoutEditorFragment extends Fragment {

    private static final String EXTRA_WORKOUT = "extra_workout";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;


    @BindView(R.id.edit_text_workout_name)
    EditText mEditTextWorkoutName;
    @BindView(R.id.button_workout_date)
    Button mButtonWorkoutDate;
    @BindView(R.id.checkbox_workout_completed)
    CheckBox mCheckboxWorkoutCompleted;

    Unbinder mUnbinder;

    private GregorianCalendar mCalendar;
    private Workout mWorkout;

    public WorkoutEditorFragment() {
        // Required empty public constructor
    }

    public static WorkoutEditorFragment newInstance(Workout workout) {
        WorkoutEditorFragment fragment = new WorkoutEditorFragment();
        Bundle args = new Bundle();

        args.putParcelable(EXTRA_WORKOUT, workout);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_editor, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        setUpToolBar(view);

        mWorkout = getArguments().getParcelable(EXTRA_WORKOUT);
        if (mWorkout != null) {
            fillInExistingWorkout();
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            mCalendar = (GregorianCalendar) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            if (mCalendar != null) {
                mButtonWorkoutDate.setText(Helper.formatDate(mCalendar.getTime()));
            }
        }
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
                WorkoutManager workoutManager = WorkoutManager.getInstance(getActivity());

                if(mWorkout != null) {
                    mWorkout.setName(mEditTextWorkoutName.getText().toString());
                    mWorkout.setDate(mCalendar);
                    mWorkout.setCompleted(mCheckboxWorkoutCompleted.isChecked());
                    workoutManager.addWorkout(mWorkout);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateWorkout() {
        String text = mEditTextWorkoutName.getText().toString();
        if (text.length() == 0) {
            Toast.makeText(getContext(), "Workout name not entered", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mCalendar == null) {
            Toast.makeText(getContext(), "Date not entered", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @OnClick(R.id.button_workout_date)
    public void openCalendar() {
        FragmentManager fm = getFragmentManager();
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.setTargetFragment(WorkoutEditorFragment.this, REQUEST_DATE);
        dialog.show(fm, DIALOG_DATE);
    }

    private void fillInExistingWorkout() {
        mCalendar = mWorkout.getDate();

        mEditTextWorkoutName.setText(mWorkout.getName());
        mButtonWorkoutDate.setText(Helper.formatDate(mWorkout.getDate().getTime()));
        mCheckboxWorkoutCompleted.setChecked(mWorkout.isCompleted());
    }

    private void setUpToolBar(View view) {
        Toolbar workoutToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(workoutToolBar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
