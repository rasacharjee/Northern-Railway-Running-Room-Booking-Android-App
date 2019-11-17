package com.gamecodeschool.nr;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class admin_home_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String CrisIdObj;
    //This value will be used to show only selected update menus
    RecyclerView input_recycler;
    ArrayList<String> list;
    String crisid;
    TextView roomval;
    TextView cityid2;
    DatabaseReference databaseRooms;
    ProgressDialog progressDialog;
    Query qFerozpur,qLudhiana;
    List<admin_book> qList;


    public admin_home_fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static admin_home_fragment newInstance(String param1, String param2) {
        admin_home_fragment fragment = new admin_home_fragment();
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
            //Bundle b=getArguments();
          // CrisIdObj=b.getString("id");


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_admin_home_fragment, container, false);
        roomval=view.findViewById(R.id.roomval);
        cityid2=view.findViewById(R.id.cityid2);
        progressDialog=new ProgressDialog(getActivity());

        Bundle extras =getActivity().getIntent().getExtras();
        crisid=extras.getString("crisid");
      //  Toast.makeText(getActivity(),"CRISID"+crisid,Toast.LENGTH_SHORT).show();
        input_recycler=view.findViewById(R.id.input_recycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        input_recycler.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();


         //list.add("FEROZPUR");
         CrisIdObj=FirebaseAuth.getInstance().getCurrentUser().getUid();
         Log.d("LOG","OnResponse"+CrisIdObj);
         //Toast.makeText(getActivity(),CrisIdObj,Toast.LENGTH_SHORT).show();

        progressDialog.setTitle("WAIT");
        progressDialog.setMessage("Please wait while we are getting current room available");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.setCancelable(false);


            if (CrisIdObj.equals("Lm8FOLC8qjVkrg3Po78VIozLU7F3")){
                list.clear();
                 list.add("FEROZPUR");
                 progressDialog.show();
                databaseRooms= FirebaseDatabase.getInstance().getReference("Rooms").child("FEROZPURrooms");
                databaseRooms.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String vroot=dataSnapshot.getValue().toString();//directly getting the value of rooms,no further class required
                        roomval.setText(vroot);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Try again later", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
                 cityid2.setText("FEROZPUR");
             }
             else if (CrisIdObj.equals("M3xqhpKnHUges7XZxoASr6x3VMw2")){
                 list.clear();
                 list.add("AMRITSAR");
                progressDialog.show();
                databaseRooms= FirebaseDatabase.getInstance().getReference("Rooms").child("AMRITSARrooms");
                databaseRooms.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String vroot=dataSnapshot.getValue().toString();//directly getting the value of rooms,no further class required
                        roomval.setText(vroot);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Try again later", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
                cityid2.setText("AMRITSAR");
             }
             else if (CrisIdObj=="345678"){
                 list.add("PATHANKOT");
                cityid2.setText("PATHANKOT");
             }
             else if (CrisIdObj=="456789"){
                 list.add("JAMMU");
                cityid2.setText("JAMMU");
             }
             else if (CrisIdObj.equals("EUqtFjPHxGaFcpAvLyMMtHB2Zpz2")){
                 list.clear();
                 list.add("LUDHIANA");
                 progressDialog.show();
                databaseRooms= FirebaseDatabase.getInstance().getReference("Rooms").child("LUDHIANArooms");
                databaseRooms.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String vroot=dataSnapshot.getValue().toString();//directly getting the value of rooms,no further class required
                        roomval.setText(vroot);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Try again later", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
                progressDialog.dismiss();
                cityid2.setText("LUDHIANA");
             }
             else if (CrisIdObj=="678901"){
                 list.add("BAIJNATH");
                cityid2.setText("BAIJNATH");
             }
             else {
                 list.add("KATRA");
                cityid2.setText("KATRA");
             }


                CustomAdapter customAdapter=new CustomAdapter(getActivity(),list);
                input_recycler.setAdapter(customAdapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (id.equals("Lm8FOLC8qjVkrg3Po78VIozLU7F3")){
            qFerozpur=FirebaseDatabase.getInstance().getReference("FEROZPUR").orderByChild("status").equalTo("booked");
            qFerozpur.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //qList.clear();
                    for (DataSnapshot qdata:dataSnapshot.getChildren()){
                        admin_book qbook=qdata.getValue(admin_book.class);
                        String chkDate=qbook.getCheckoutDate();
                       // String chkTime=qbook.getCheckoutTime();
                       /* char[] cTime=new char[chkTime.length()];
                        for (int i=0;i<chkTime.length();i++){
                            cTime[i]=chkTime.charAt(i);
                        }
                        Calendar right=Calendar.getInstance();
                        int hour=right.get(Calendar.HOUR_OF_DAY);
                        int min=right.get(Calendar.MINUTE);*/
                        //qList.add(qbook);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else if (id.equals("EUqtFjPHxGaFcpAvLyMMtHB2Zpz2")){
            qLudhiana=FirebaseDatabase.getInstance().getReference("LUDHIANA").orderByChild("status").equalTo("booked");
        }else {
            Toast.makeText(getActivity(),"Auto-check out error due to invalid admin credentials",Toast.LENGTH_SHORT).show();
        }



    }
}
