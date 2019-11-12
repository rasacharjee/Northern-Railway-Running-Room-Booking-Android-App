package com.gamecodeschool.nr;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class user_bookings_fragment extends Fragment {


    private RecyclerView recyclerViewbook;
    private adapterbook  booking;
    private List<book> bookList;
    private int crisid;
    Query dbbooksferoz;
    Query dbbooksamrit;
    Query dbbooksbaij;
    Query dbbooksjal;
    Query dbbooksjammu;
    Query dbbookskatra;
    Query dbbookspathan;


    public user_bookings_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_user_bookings_fragment, container, false);

        //String crisId=this.getArguments().getString("CrisID");
        dbbooksferoz= FirebaseDatabase.getInstance().getReference("FEROZPUR").orderByChild("id").equalTo(crisid);
        dbbooksbaij= FirebaseDatabase.getInstance().getReference("BAIJNATH").orderByChild("id").equalTo(crisid);
        dbbooksamrit= FirebaseDatabase.getInstance().getReference("AMRITSAR").orderByChild("id").equalTo(crisid);
        dbbooksjal= FirebaseDatabase.getInstance().getReference("JALANDAR").orderByChild("id").equalTo(crisid);
        dbbooksjammu= FirebaseDatabase.getInstance().getReference("JAMMU").orderByChild("id").equalTo(crisid);
        dbbookskatra= FirebaseDatabase.getInstance().getReference("KATRA").orderByChild("id").equalTo(crisid);
        dbbookspathan= FirebaseDatabase.getInstance().getReference("PATHANKOT").orderByChild("id").equalTo(crisid);

        bookList=new ArrayList<>();
        recyclerViewbook=view.findViewById(R.id.recyclerViewbook);
        recyclerViewbook.setHasFixedSize(false);
        recyclerViewbook.setLayoutManager(new LinearLayoutManager(getActivity()));

        bookList.clear();
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                   for(DataSnapshot snapshot:dataSnapshot.getChildren())
                   {
                       book obj=snapshot.getValue(book.class);
                       bookList.add(obj);
                   }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        dbbookspathan.addListenerForSingleValueEvent(valueEventListener);
        dbbooksamrit.addListenerForSingleValueEvent(valueEventListener);
        dbbooksbaij.addListenerForSingleValueEvent(valueEventListener);
        dbbooksferoz.addListenerForSingleValueEvent(valueEventListener);
        dbbooksjal.addListenerForSingleValueEvent(valueEventListener);
        dbbooksjammu.addListenerForSingleValueEvent(valueEventListener);
        dbbookskatra.addListenerForSingleValueEvent(valueEventListener);

        bookList.add(
                new book(
                        "Ferozpur",
                        "2 November",
                        "1 pm",
                        "12 pm",
                        "3 november" ));

        booking=new adapterbook(getActivity(),bookList);
        recyclerViewbook.setAdapter(booking);
        return view;

    }

}
