package com.gamecodeschool.nr;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user_bookings_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user_bookings_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerViewbook;
    adapterbook  booking;
    List<book>bookList;


    public user_bookings_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment user_bookings_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static user_bookings_fragment newInstance(String param1, String param2) {
        user_bookings_fragment fragment = new user_bookings_fragment();
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
        final View view= inflater.inflate(R.layout.fragment_user_bookings_fragment, container, false);

        //String crisId=this.getArguments().getString("CrisID");

        bookList=new ArrayList<>();
        recyclerViewbook=view.findViewById(R.id.recyclerViewbook);
        recyclerViewbook.setHasFixedSize(false);
        recyclerViewbook.setLayoutManager(new LinearLayoutManager(getActivity()));

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
