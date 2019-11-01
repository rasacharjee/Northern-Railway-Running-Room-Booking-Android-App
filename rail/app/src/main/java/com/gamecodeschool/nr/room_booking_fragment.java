package com.gamecodeschool.nr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;


public class room_booking_fragment extends Fragment {

    private String date;
    private EditText checkinval;
    private EditText checkoutval;
    private EditText checkintimeval;
    private EditText crisidval;
    private EditText trainnoval;
    private Button submitval;


    public room_booking_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_room_booking , container , false);
        checkinval=view.findViewById(R.id.checkinval);
        checkoutval=view.findViewById(R.id.checkoutval);
        checkintimeval=view.findViewById(R.id.checkintimeval);
        crisidval=view.findViewById(R.id.crisidval);
        trainnoval=view.findViewById(R.id.trainnoval);
        submitval=view.findViewById(R.id.submitval);


        Calendar c= Calendar.getInstance();
        final int day=c.get(Calendar.DAY_OF_MONTH);
        final int month=c.get(Calendar.MONTH);
        final int year=c.get(Calendar.YEAR);
        final int hour=c.get(Calendar.HOUR_OF_DAY);
        final int min=c.get(Calendar.MINUTE);

        date=day+"-"+(month+1)+"-"+year;
        checkinval.setText(date);

        checkinval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity() , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view , int year , int month , int dayOfMonth) {
                        month=month+1;
                        date=dayOfMonth+"-"+month+"-"+year;
                        checkinval.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        checkoutval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity() , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view , int year , int month , int dayOfMonth) {
                        month=month+1;
                        String checkout=dayOfMonth+"-"+month+"-"+year;
                        checkoutval.setText(checkout);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        checkintimeval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity() , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view , int hourOfDay , int minute) {
                        String time=hourOfDay+":"+minute;
                        checkintimeval.setText(time);
                    }
                },hour,min,false);
                timePickerDialog.show();
            }
        });

        submitval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DO OPERATIONS
            }
        });

        return view;
    }
}
