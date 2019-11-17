package com.gamecodeschool.nr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class room_booking_fragment extends Fragment {

    private String date, checkout, inTime, outTime,compare;
    private int room,roomL;
    private EditText checkinval;
    private EditText checkoutval;
    private EditText checkintimeval;
    private EditText checkouttimeval;
    private EditText crisidval;
    private EditText trainnoval;
    private Button submitval;
    private String CrisIdObj;
    private String name;

    DatabaseReference databaseFerozpurbookings,databaseAmritsarbookings,databasePathankotbookings,databaseLudhianabookings,databaseJammubookings,databaseKatrabookings,databaseBaijnathbookings;
    DatabaseReference databaseFerozpurRooms,databaseAmritsarRooms,databasePathankotRooms,databaseLudhianaRooms,databaseJammuRooms,databaseKatraRooms,databaseBaijnathRooms;

    DatabaseReference databasedetails;
    Query getname;
    String UID;

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


        Bundle bundle=getArguments();
        compare=bundle.getString("cities");
        Toast.makeText(getActivity(),compare,Toast.LENGTH_SHORT).show();

        databaseFerozpurbookings=FirebaseDatabase.getInstance().getReference("FEROZPUR");
        databaseAmritsarbookings=FirebaseDatabase.getInstance().getReference("AMRITSAR");
        databaseBaijnathbookings=FirebaseDatabase.getInstance().getReference("BAIJNATH");
        databaseLudhianabookings=FirebaseDatabase.getInstance().getReference("LUDHIANA");
        databaseJammubookings=FirebaseDatabase.getInstance().getReference("JAMMU");
        databaseKatrabookings=FirebaseDatabase.getInstance().getReference("KATRA");
        databasePathankotbookings=FirebaseDatabase.getInstance().getReference("PATHANKOT");

        databaseFerozpurRooms = FirebaseDatabase.getInstance().getReference("Rooms").child("FEROZPURrooms");
        databaseAmritsarRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("AMRITSARrooms");
        databaseBaijnathRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("BAIJNATHrooms");
        databaseLudhianaRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("LUDHIANArooms");
        databaseJammuRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("JAMMUrooms");
        databaseKatraRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("KATRArooms");
        databasePathankotRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("PATHANKOTrooms");

        UID=FirebaseAuth.getInstance().getCurrentUser().getUid();
        getname=FirebaseDatabase.getInstance().getReference("Driver_Credentials").orderByChild("duid").equalTo(UID);
        getname.addListenerForSingleValueEvent(valueEventListener);
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
                        int present=Integer.valueOf(String.valueOf(hour)+String.valueOf(min));
                        int checkout=Integer.valueOf(String.valueOf(hourOfDay)+String.valueOf(minute));
                        if(hourOfDay-hour<=0 && checkout-present>=30)
                        {
                            Toast.makeText(getActivity(),"BOOKING NOT ALLOWED",Toast.LENGTH_SHORT).show();
                            submitval.setEnabled(false);
                        }
                        else if (hourOfDay-hour==1 && checkout-present>=70)
                        {
                            Toast.makeText(getActivity(),"BOOKING NOT ALLOWED",Toast.LENGTH_SHORT).show();
                            submitval.setEnabled(false);
                        }
                        else if(checkout-present<=0){
                            Toast.makeText(getActivity(),"BOOKING NOT ALLOWED",Toast.LENGTH_SHORT).show();
                            submitval.setEnabled(false);
                        }
                        else if(hourOfDay-hour>1){
                            Toast.makeText(getActivity(),"BOOKING NOT ALLOWED",Toast.LENGTH_SHORT).show();
                            submitval.setEnabled(false);
                        }
                        else {
                            if (hourOfDay > 12) {
                                time = (hourOfDay - 12) + ":" + minute + " PM";
                            } else {
                                time = hourOfDay + ":" + minute + " AM";
                            }
                            checkintimeval.setText(time);
                            inTime = time;
                            submitval.setEnabled(true);
                        }
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
                checkoutval.getText().clear();
                checkouttimeval.getText().clear();
                checkinval.getText().clear();
                checkintimeval.getText().clear();
                crisidval.getText().clear();
                trainnoval.getText().clear();


            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseFerozpurRooms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String vroot=dataSnapshot.getValue().toString();
                room=Integer.parseInt(vroot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Bookings cannot be completed", Toast.LENGTH_SHORT).show();
            }
        });
        databaseLudhianaRooms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lroot=dataSnapshot.getValue().toString();
                roomL=Integer.parseInt(lroot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               Toast.makeText(getActivity(),"Bookings cannot be completed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void funbookdetails() {
        String crisId =crisidval.getText().toString().trim();
        int trainNo = Integer.parseInt(trainnoval.getText().toString().trim());//Might cause null pointer expectation

        if ( crisId!=null||trainNo!= 0 || date != null || checkout != null || inTime!=null ||outTime!=null) {
            if (compare.equals("FEROZPUR")){
                String bId = databaseFerozpurbookings.push().getKey();
                String status="booked";
               // String UID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                database_book_java_class book = new database_book_java_class(bId, crisId, trainNo, inTime, outTime, date, checkout,status,UID,compare,name);
                databaseFerozpurbookings.child(bId).setValue(book);
                room= room-1;//if rooms can be accessed here
                databaseFerozpurRooms.setValue(room);
                Toast.makeText(getActivity(),"Booking successful",Toast.LENGTH_LONG).show();
            }
            else if (compare.equals("AMRITSAR")){
                String bId = databaseAmritsarbookings.push().getKey();
                String status="booked";
                database_book_java_class book = new database_book_java_class(bId, crisId, trainNo, inTime, outTime, date, checkout,status,UID,compare,name);
                databaseAmritsarbookings.child(bId).setValue(book);
                room= room-1;//if rooms can be accessed here
                databaseAmritsarRooms.setValue(room);
                Toast.makeText(getActivity(),"Booking successful",Toast.LENGTH_LONG).show();
            }
            else if (compare.equals("LUDHIANA")){
                String bId = databaseLudhianabookings.push().getKey();
                String status="booked";
                //String UID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                database_book_java_class book = new database_book_java_class(bId, crisId, trainNo, inTime, outTime, date, checkout,status,UID,compare,name);
                databaseLudhianabookings.child(bId).setValue(book);
                roomL= roomL-1;//if rooms can be accessed here
                databaseLudhianaRooms.setValue(roomL);
                Toast.makeText(getActivity(),"Booking successful",Toast.LENGTH_LONG).show();
            }
            else if (compare.equals("JAMMU")){
                String bId = databaseJammubookings.push().getKey();
                String status="booked";
                //String UID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                database_book_java_class book = new database_book_java_class(bId, crisId, trainNo, inTime, outTime, date, checkout,status,UID,compare,name);
                databaseJammubookings.child(bId).setValue(book);
                room= room-1;//if rooms can be accessed here
                databaseJammuRooms.setValue(room);
                Toast.makeText(getActivity(),"Booking successful",Toast.LENGTH_LONG).show();
            }
            else if (compare.equals("PATHANKOT")){
                String bId = databasePathankotbookings.push().getKey();
                String status="booked";
               // String UID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                database_book_java_class book = new database_book_java_class(bId, crisId, trainNo, inTime, outTime, date, checkout,status,UID,compare,name);
                databasePathankotbookings.child(bId).setValue(book);
                room= room-1;//if rooms can be accessed here
                databasePathankotRooms.setValue(room);
                Toast.makeText(getActivity(),"Booking successful",Toast.LENGTH_LONG).show();
            }
            else if (compare.equals("KATRA")){
                String bId = databaseKatrabookings.push().getKey();
                String status="booked";
              //  String UID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                database_book_java_class book = new database_book_java_class(bId, crisId, trainNo, inTime, outTime, date, checkout,status,UID,compare,name);
                databaseKatrabookings.child(bId).setValue(book);
                room= room-1;//if rooms can be accessed here
                databaseKatraRooms.setValue(room);
                Toast.makeText(getActivity(),"Booking successful",Toast.LENGTH_LONG).show();
            }
            else if (compare.equals("BAIJNATH")){
                String bId = databaseBaijnathbookings.push().getKey();
                String status="booked";
               // String UID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                database_book_java_class book = new database_book_java_class(bId, crisId, trainNo, inTime, outTime, date, checkout,status,UID,compare,name);
                databaseBaijnathbookings.child(bId).setValue(book);
                room= room-1;//if rooms can be accessed here
                databaseBaijnathRooms.setValue(room);
                Toast.makeText(getActivity(),"Booking successful",Toast.LENGTH_LONG).show();

            }
            //future provision

        } else if (crisId==null){
            Toast.makeText(getActivity(),"Please enter CRIS ID",Toast.LENGTH_SHORT).show();
        }else if (trainNo==0){
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
    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    driver_credentials obj = data.getValue(driver_credentials.class);
                    name = obj.getdName();
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}