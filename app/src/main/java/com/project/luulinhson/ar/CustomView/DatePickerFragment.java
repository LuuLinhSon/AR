package com.project.luulinhson.ar.CustomView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.project.luulinhson.ar.R;

import java.util.Calendar;

/**
 * Created by Admin on 6/12/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int iNam = calendar.get(Calendar.YEAR);
        int iThang = calendar.get(Calendar.MONTH);
        int iNgay = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,iNgay,iThang,iNam);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView tvBirthDay = (TextView) getActivity().findViewById(R.id.tvBirthDay);
        String ngaySinh = dayOfMonth + "/" + (month + 1) + "/" + year;
        tvBirthDay.setText(ngaySinh);
    }
}
