package com.gamecodeschool.nr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class room_booking_fragment extends Fragment {

    private String date, checkout, inTime, outTime;
    private int room;
    private EditText checkinval;
    private EditText checkoutval;
    private EditText checkintimeval;
    private EditText checkouttimeval;
    private EditText crisidval;
    private EditText trainnoval;
    private Button submitval;

    DatabaseReference databaseFerozpurbookings,databaseAmritsarbookings,databasePathankotbookings,databaseJalandharbookings,databaseJammubookings,databaseKatrabookings,databaseBaijnathbookings;
    DatabaseReference databaseFerozpurRooms,databaseAmritsarRooms,databasePathankotRooms,databaseJalandharRooms,databaseJammuRooms,databaseKatraRooms,databaseBaijnathRooms;

    public room_booking_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_booking, container, false);
        checkinval = view.findViewById(R.id.checkinval);
        checkoutval = view.findViewById(R.id.checkoutval);
        checkintimeval = view.findViewById(R.id.checkintimeval);
        checkouttimeval = view.findViewById(R.id.checkouttimeval);
        crisidval = view.findViewById(R.id.crisidval);
        trainnoval = view.findViewById(R.id.trainnoval);
        submitval = view.findViewById(R.id.submitval);

        databaseFerozpurbookings=FirebaseDatabase.getInstance().getReference("FEROZPUR");
        databaseAmritsarbookings=FirebaseDatabase.getInstance().getReference("AMRITSAR");
        databaseBaijnathbookings=FirebaseDatabase.getInstance().getReference("BAIJNATH");
        databaseJalandharbookings=FirebaseDatabase.getInstance().getReference("JALANDHAR");
        databaseJammubookings=FirebaseDatabase.getInstance().getReference("JAMMU");
        databaseKatrabookings=FirebaseDatabase.getInstance().getReference("KATRA");
        databasePathankotbookings=FirebaseDatabase.getInstance().getReference("PATHANKOT");

        databaseFerozpurRooms = FirebaseDatabase.getInstance().getReference("FEROZPURrooms");
        databaseAmritsarRooms=FirebaseDatabase.getInstance().getReference("AMRITSARrooms");
        databaseBaijnathRooms=FirebaseDatabase.getInstance().getReference("BAIJNATHrooms");
        databaseJalandharbookings=FirebaseDatabase.getInstance().getReference("JALANDHARrooms");
        databaseJammuRooms=FirebaseDatabase.getInstance().getReference("JAMMUrooms");
        databaseKatraRooms=FirebaseDatabase.getInstance().getReference("KATRArooms");
        databasePathankotRooms=FirebaseDatabase.getInstance().getReference("PATHANKOTrooms");


        Calendar c = Calendar.getInstance();
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int month = c.get(Calendar.MONTH);
        final int year = c.get(Calendar.YEAR);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int min = c.get(Calendar.MINUTE);

        date = day+"-"+(month + 1)+"-"+year;
        checkinval.setText(date);

        checkinval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        date = dayOfMonth + "-" + month + "-" + year;
                        checkinval.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        checkoutval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        checkout = dayOfMonth + "-" + month + "-" + year;
                        checkoutval.setText(checkout);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        checkintimeval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time;
                        if(hourOfDay>12)
                        {
                            time=(hourOfDay-12)+":"+minute+" PM";
                        }
                        else {
                            time = hourOfDay + ":" + minute + " AM";
                        }
                        checkintimeval.setText(time);
                        inTime=time;
                    }
                }, hour, min, false);
                timePickerDialog.show();
            }
        });
        checkouttimeval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time;
                        if(hourOfDay>12)
                        {
                            time=(hourOfDay-12)+":"+minute+" PM";
                        }
                        else {
                            time = hourOfDay + ":" + minute + " AM";
                        }
                        checkouttimeval.setText(time);
                        outTime=time;
                    }
                }, hour, min, false);
                timePickerDialog.show();
            }
        });

        submitval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                funbookdetails();
                //DO OPERATIONS
            }
        });

        return view;
    }

  /*  @Override
    public void onStart() {
        super.onStart();
        databaseRoomsAvailable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                room_available_java_class room_av= new room_available_java_class(dataSnapshot.getChildren().toString());
                room=Integer.parseInt(room_av.getRoom_available());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Bookings cannot be completed", Toast.LENGTH_SHORT).show();
            }
        });

    }*/

    private void funbookdetails() {
        String crisId =crisidval.getText().toString().trim();
        Integer trainNo = Integer.parseInt(trainnoval.getText().toString().trim());//Might cause null pointer expectation


        if ( crisId!=null||trainNo != null || date != null || checkout != null || inTime!=null ||outTime!=null) {
            String bId = databaseCompletebookings.push().getKey();
            database_book_java_class book = new database_book_java_class(bId, crisId, trainNo, inTime, outTime, date, checkout);
            databaseCompletebookings.child(bId).setValue(book);
            room= room-1;
            databaseRoomsAvailable.setValue("");
            //Add the database thing for booking rooms available node
        } else if (crisId==null){
            Toast.makeText(getActivity(),"Please enter CRIS ID",Toast.LENGTH_SHORT).show();
        }else if (trainNo==null){
            Toast.makeText(getActivity(),"Please enter Train Number",Toast.LENGTH_SHORT).show();
        }else if (date==null){
            Toast.makeText(getActivity(),"Please enter Check in date",Toast.LENGTH_SHORT).show();
        }else if (checkout==null){
            Toast.makeText(getActivity(),"Please enter Check out date",Toast.LENGTH_SHORT).show();
        }else if (inTime==null){
            Toast.makeText(getActivity(),"Please enter Check in time",Toast.LENGTH_SHORT).show();
        }else if (outTime==null){
            Toast.makeText(getActivity(),"Please enter Check out time",Toast.LENGTH_SHORT).show();
        }

    }
}