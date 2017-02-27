package com.example.skeeno.workouttracker.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skeeno.workouttracker.R;
import com.example.skeeno.workouttracker.model.Workout;
import com.example.skeeno.workouttracker.model.WorkoutManager;
import com.example.skeeno.workouttracker.ui.WorkoutAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerviewFragment extends Fragment {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;

    private WorkoutAdapter mWorkoutAdapter;


    public RecyclerviewFragment() {
        // Required empty public constructor
    }

    public static RecyclerviewFragment newInstance() {
        return new RecyclerviewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void updateUI() {
        WorkoutManager workoutManager = WorkoutManager.getInstance(getActivity());
        ArrayList<Workout> workouts = workoutManager.getWorkoutList();

        mWorkoutAdapter = new WorkoutAdapter(workouts);
        mRecyclerView.setAdapter(mWorkoutAdapter);
    }

}
