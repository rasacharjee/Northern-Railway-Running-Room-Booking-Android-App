package com.gamecodeschool.nr;


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
import android.widget.Toast;

import java.util.ArrayList;



public class admin_home_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String CrisIdObj;//This value will be used to show only selected update menus
    RecyclerView input_recycler;
    ArrayList<String> list;
    String crisid;

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

        Bundle extras =getActivity().getIntent().getExtras();
        crisid=extras.getString("crisid");
      //  Toast.makeText(getActivity(),"CRISID"+crisid,Toast.LENGTH_SHORT).show();
        input_recycler=view.findViewById(R.id.input_recycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        input_recycler.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();


        //list.add("FEROZPUR");

             if (CrisIdObj=="123456"){
               list.add("FEROZPUR");
             }
             else if (CrisIdObj=="234567"){
                 list.add("AMRITSAR");
             }
             else if (CrisIdObj=="345678"){
                 list.add("PATHANKOT");
             }
             else if (CrisIdObj=="456789"){
                 list.add("JAMMU");
             }
             else if (CrisIdObj=="567890"){
                 list.add("JALANDHAR");
             }
             else if (CrisIdObj=="678901"){
                 list.add("BAIJNATH");
             }
             else {
                 list.add("KATRA");
             }


                CustomAdapter customAdapter=new CustomAdapter(getActivity(),list);
                input_recycler.setAdapter(customAdapter);


        return view;
    }
}
