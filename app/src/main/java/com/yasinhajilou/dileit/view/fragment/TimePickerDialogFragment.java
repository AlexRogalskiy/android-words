package com.yasinhajilou.dileit.view.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.widget.TimePicker;

import com.yasinhajilou.dileit.viewmodel.TimeSharedViewModel;

import java.util.Calendar;


public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private TimeSharedViewModel mTimeSharedViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        mTimeSharedViewModel = new ViewModelProvider(getActivity()).get(TimeSharedViewModel.class);
        return new TimePickerDialog(getContext(), this, hour, min, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        mTimeSharedViewModel.setTime(new int[]{i, i1});
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);

        //first dialog will popup and if user cancel the time picker
        // we should turn of the switch
        mTimeSharedViewModel.setCancelListener(true);
    }
}