package com.gamecodeschool.nr;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admin_booking_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String valueReceived;
    String CrisIdObj;
    RecyclerView ArecyclerViewbook;
    AdminAdapterBook  Abooking;
    List<admin_book> AbookList;

    DatabaseReference databaseFerozpur,databaseAmritsar,databasePathankot,databaseJammu,databaseKatra,databaseJalandhar,databaseBaijnath;


    public admin_booking_fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static admin_booking_fragment newInstance(String param1, String param2) {
        admin_booking_fragment fragment = new admin_booking_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            //valueReceived=getArguments().getString("Value");
            //CRIS id string,Will be used to place a query in the database to fetch room booking data in a particular station

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_admin_booking_fragment, container, false);

        databaseAmritsar= FirebaseDatabase.getInstance().getReference("AMRITSAR");
        databaseBaijnath= FirebaseDatabase.getInstance().getReference("BAIJNATH");
        databaseFerozpur= FirebaseDatabase.getInstance().getReference("FEROZPUR");
        databaseJalandhar= FirebaseDatabase.getInstance().getReference("JALANDHAR");
        databaseJammu= FirebaseDatabase.getInstance().getReference("JAMMU");
        databaseKatra= FirebaseDatabase.getInstance().getReference("KATRA");

        AbookList=new ArrayList<>();
        ArecyclerViewbook=view.findViewById(R.id.AdminrecyclerViewbook);
        ArecyclerViewbook.setHasFixedSize(false);
        ArecyclerViewbook.setLayoutManager(new LinearLayoutManager(getActivity()));

     /*   AbookList.add(new admin_book("Ferozpur",
                "2nd November",
                "3rd November",
                "12 pm",
                "3 pm",
                "123456"));*/
        //Abooking=new AdminAdapterBook(getActivity(),AbookList);
        //ArecyclerViewbook.setAdapter(Abooking);
        //Toast.makeText(getActivity(),CrisIdObj,Toast.LENGTH_LONG).show();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (CrisIdObj=="123456"){
            databaseFerozpur.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   for (DataSnapshot bookSnapshot: dataSnapshot.getChildren()){
                       AbookList.clear();
                      admin_book admin_book= bookSnapshot.getValue(com.gamecodeschool.nr.admin_book.class);
                      AbookList.add(admin_book);
                   }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),"Try again later",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (CrisIdObj=="234567"){
            databaseAmritsar.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot bookSnapshot: dataSnapshot.getChildren()){
                        AbookList.clear();
                        admin_book admin_book= bookSnapshot.getValue(com.gamecodeschool.nr.admin_book.class);
                        AbookList.add(admin_book);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),"Try again later",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (CrisIdObj=="345678"){
            databasePathankot.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    AbookList.clear();
                    for (DataSnapshot bookSnapshot: dataSnapshot.getChildren()){
                        admin_book admin_book= bookSnapshot.getValue(com.gamecodeschool.nr.admin_book.class);
                        AbookList.add(admin_book);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),"Try again later",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (CrisIdObj=="456789"){
            databaseJammu.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    AbookList.clear();
                    for (DataSnapshot bookSnapshot: dataSnapshot.getChildren()){
                        admin_book admin_book= bookSnapshot.getValue(com.gamecodeschool.nr.admin_book.class);
                        AbookList.add(admin_book);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),"Try again later",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (CrisIdObj=="567890"){
            databaseJalandhar.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    AbookList.clear();
                    for (DataSnapshot bookSnapshot: dataSnapshot.getChildren()){
                        admin_book admin_book= bookSnapshot.getValue(com.gamecodeschool.nr.admin_book.class);
                        AbookList.add(admin_book);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),"Try again later",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (CrisIdObj=="678901"){
            databaseBaijnath.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    AbookList.clear();
                    for (DataSnapshot bookSnapshot: dataSnapshot.getChildren()){
                        admin_book admin_book= bookSnapshot.getValue(com.gamecodeschool.nr.admin_book.class);
                        AbookList.add(admin_book);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),"Try again later",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (CrisIdObj=="789012"){
            databaseKatra.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    AbookList.clear();
                    for (DataSnapshot bookSnapshot: dataSnapshot.getChildren()){
                        admin_book admin_book= bookSnapshot.getValue(com.gamecodeschool.nr.admin_book.class);
                        AbookList.add(admin_book);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),"Try again later",Toast.LENGTH_SHORT).show();
                }
            });
        }

        Abooking=new AdminAdapterBook(getActivity(),AbookList);
        ArecyclerViewbook.setAdapter(Abooking);

    }
}
