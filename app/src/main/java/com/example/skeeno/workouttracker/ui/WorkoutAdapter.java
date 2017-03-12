package com.example.skeeno.workouttracker.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.skeeno.workouttracker.R;
import com.example.skeeno.workouttracker.activities.WorkoutEditor;
import com.example.skeeno.workouttracker.model.Workout;
import com.example.skeeno.workouttracker.utils.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by skeeno on 12/02/2017.
 */

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {

    private ArrayList <Workout>mWorkoutList = new ArrayList<>();

    public WorkoutAdapter(ArrayList<Workout> list) {
        mWorkoutList = list;
    }

    @Override
    public WorkoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new WorkoutHolder(view) ;
    }

    @Override
    public void onBindViewHolder(WorkoutHolder holder, int position) {
        WorkoutHolder workoutHolder = holder;
        workoutHolder.bindWorkoutDataToHolder(mWorkoutList.get(position));
    }

    @Override
    public int getItemCount() {
        return mWorkoutList.size();
    }

    public void updateWorkouts(ArrayList<Workout> wlist) {
        if (wlist != null && wlist.size() != 0) {
            mWorkoutList.clear();
            mWorkoutList.addAll(wlist);
        }
    }

    public class WorkoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.workout_text)
        TextView mWorkoutTitle;
        @BindView(R.id.date_text)
        TextView mDateText;
        @BindView(R.id.completed_checkbox)
        CheckBox mCompletedCheckbox;

        Workout workout;


        public WorkoutHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindWorkoutDataToHolder(Workout aworkout) {
            workout = aworkout;
            mWorkoutTitle.setText(workout.getName());
            mDateText.setText(Helper.formatDate(workout.getDate().getTime()));
            mCompletedCheckbox.setChecked(workout.isCompleted());
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(WorkoutEditor.newInstance(v.getContext(), workout));
        }
    }
}
