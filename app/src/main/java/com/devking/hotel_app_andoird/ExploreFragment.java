package com.devking.hotel_app_andoird;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ExploreFragment extends Fragment {

    Calendar calendar;
    TextView check_in,check_out;
    ImageButton checkin_img,checkout_img;
    DatePickerDialog datePickerDialog;
    AutoCompleteTextView drop_down_menu;

    public ExploreFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_explore, container, false);
        check_in = rootview.findViewById(R.id.ckeck_in);
        check_out = rootview.findViewById(R.id.check_out);
        checkin_img = rootview.findViewById(R.id.checkin_img);
        checkout_img = rootview.findViewById(R.id.checkout_img);
        calendar =Calendar.getInstance();

        checkin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        check_in.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,dayOfMonth);
                datePickerDialog.show();
            }
        });


        checkout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                datePickerDialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        check_out.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,dayOfMonth);
                datePickerDialog.show();
            }
        });

        return rootview;
    }
}