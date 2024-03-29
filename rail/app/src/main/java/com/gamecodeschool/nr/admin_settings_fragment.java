package com.gamecodeschool.nr;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static androidx.navigation.Navigation.findNavController;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link admin_settings_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class admin_settings_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button button_logout,button_password,button_about;
    TextView set,ab,con,link;
    ImageView pro;

    public admin_settings_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment admin_settings_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static admin_settings_fragment newInstance(String param1, String param2) {
        admin_settings_fragment fragment = new admin_settings_fragment();
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
        final View view= inflater.inflate(R.layout.fragment_user_settings_fragment, container, false);
        button_logout=view.findViewById(R.id.btn_logout);
        button_password=view.findViewById(R.id.btn_password);
        button_about=view.findViewById(R.id.btn_about);
        set=view.findViewById(R.id.tvset);
        ab=view.findViewById(R.id.tvab);
        con=view.findViewById(R.id.tvcon);
        link=view.findViewById(R.id.tvlink);
        pro=view.findViewById(R.id.ivpro);

        button_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Navigation.findNavController(view).navigate(R.id.change_password_fragment2);
            }
        });
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"LOGOUT SUCCESSFUL",Toast.LENGTH_SHORT).show();
            }
        });
        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set.setVisibility(View.INVISIBLE);
                pro.setVisibility(View.INVISIBLE);
                button_about.setVisibility(View.INVISIBLE);
                button_logout.setVisibility(View.INVISIBLE);
                button_password.setVisibility(View.INVISIBLE);
               ab.setVisibility(View.VISIBLE);
                con.setVisibility(View.VISIBLE);
                link.setVisibility(View.VISIBLE);

                link.setText(Html.fromHtml("<a href=\"mailto:nr.bookings1@gmail.com\">Email: nr.bookings1@gmail.com</a>"));
                link.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
        return view;
    }

}
