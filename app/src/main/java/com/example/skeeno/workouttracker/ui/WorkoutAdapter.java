package com.example.skeeno.workouttracker.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.skeeno.workouttracker.R;
import com.example.skeeno.workouttracker.model.Workout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by skeeno on 12/02/2017.
 */

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {

    ArrayList mWorkoutList = new ArrayList();

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
        workoutHolder.bindWorkoutDataToHolder((Workout) mWorkoutList.get(position));
    }

    @Override
    public int getItemCount() {
        return mWorkoutList.size();
    }

    public void updateData(ArrayList<Workout> list) {
        if (mWorkoutList != null) {
            mWorkoutList.clear();
            mWorkoutList.addAll(list);
        } else {
            mWorkoutList = list;
        }
        notifyDataSetChanged();
    }

    public class WorkoutHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.workout_text)
        TextView mWorkoutTitle;
        @BindView(R.id.date_text)
        TextView mDateText;
        @BindView(R.id.completed_checkbox)
        CheckBox mCompletedCheckbox;


        public WorkoutHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindWorkoutDataToHolder(Workout workout) {
            mWorkoutTitle.setText(workout.getName());
            mDateText.setText(workout.getDate().getTime().toString());
            mCompletedCheckbox.setChecked(workout.isCompleted());
        }
    }
}
