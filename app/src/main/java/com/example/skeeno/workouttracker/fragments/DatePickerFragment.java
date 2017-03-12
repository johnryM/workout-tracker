package com.example.skeeno.workouttracker.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;


import com.example.skeeno.workouttracker.R;

import java.util.GregorianCalendar;


public class DatePickerFragment extends DialogFragment {
    static final String EXTRA_DATE = "extra_date" ;


    public DatePickerFragment() {
        // Required empty public constructor
    }

    public static DatePickerFragment newInstance() {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        final DatePicker mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);

        return new AlertDialog.Builder(getActivity()).
                setView(v).
                setTitle("Pick release date").
                setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    int year = mDatePicker.getYear();
                    int month = mDatePicker.getMonth();
                    int day = mDatePicker.getDayOfMonth();
                    GregorianCalendar calendar = new GregorianCalendar(year, month, day);
                    sendResult(Activity.RESULT_OK, calendar);
                }).
                create();
    }

    private void sendResult(int resultCode, GregorianCalendar calendar) {
        if (getTargetFragment() == null) { return; }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, calendar);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
