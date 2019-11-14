package com.gamecodeschool.nr;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user_home_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user_home_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "am1";
    private static final String ARG_PARAM2 = "am2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    adaptercity adaptercity;
    List<cities_java_class> citiesList;



    public user_home_fragment() {
        // Required empty public constructor
    }


    public static user_home_fragment newInstance(String param1, String param2) {
        user_home_fragment fragment = new user_home_fragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_user_home_fragment, container, false);
        citiesList=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        citiesList.add(                                         //ADD DATA FROM DATABASE HERE
                new cities_java_class(
                        "FEROZPUR",
                        R.drawable.ferozpur));
        citiesList.add(
                new cities_java_class(
                        "AMRITSAR",
                        R.drawable.amritsar));
        citiesList.add(
                new cities_java_class(
                        "LUDHIANA",
                        R.drawable.ludhiana));
        citiesList.add(
                new cities_java_class(
                        "PATHANKOT",
                        R.drawable.pathankot));
        citiesList.add(
                new cities_java_class(
                        "JAMMU",
                        R.drawable.jammu));
        citiesList.add(
                new cities_java_class(
                        "BAIJNATH",
                        R.drawable.baij));
        citiesList.add(
                new cities_java_class(
                        "KATRA",
                        R.drawable.katra));

        recyclerView.setLayoutManager(layoutManager);

        adaptercity=new adaptercity(getActivity(),citiesList);
        recyclerView.setAdapter(adaptercity);
        return view;
    }

}
