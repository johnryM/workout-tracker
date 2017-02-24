package com.example.skeeno.workouttracker.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skeeno.workouttracker.R;
import com.example.skeeno.workouttracker.model.Exercise;
import com.example.skeeno.workouttracker.model.Workout;
import com.example.skeeno.workouttracker.ui.WorkoutAdapter;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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

    public RecyclerviewFragment() {
        // Required empty public constructor
    }

    public static RecyclerviewFragment newInstance() {
        return new RecyclerviewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new WorkoutAdapter(setUpTempData()));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public ArrayList<Workout> setUpTempData() {
        ArrayList<Workout> list = new ArrayList<>();
        list.add(new Workout("Chest", new GregorianCalendar(2017, 0, 5), true, new ArrayList<Exercise>()));
        list.add(new Workout("Shoulder", new GregorianCalendar(2017, 0, 7), true, new ArrayList<Exercise>()));
        list.add(new Workout("Back", new GregorianCalendar(2017, 0, 9), true, new ArrayList<Exercise>()));
        list.add(new Workout("Bicep", new GregorianCalendar(2017, 0, 11), true, new ArrayList<Exercise>()));
        list.add(new Workout("Tricep", new GregorianCalendar(2017, 0, 13), true, new ArrayList<Exercise>()));
        list.add(new Workout("Legs", new GregorianCalendar(2017, 0, 15), true, new ArrayList<Exercise>()));
        return list;
    }
}
