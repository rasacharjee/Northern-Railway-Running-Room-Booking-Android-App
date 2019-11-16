package com.gamecodeschool.nr;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user_register_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user_register_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "arg1";
    private static final String ARG_PARAM2 = "arg2";
    EditText etName,etEmail,etPhn,etPassword,etcPassword;
    Button btnRegister;


    public FirebaseAuth mAuth;
    public DatabaseReference databaseDrivers;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public user_register_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment user_register_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static user_register_fragment newInstance(String param1, String param2) {
        user_register_fragment fragment = new user_register_fragment();
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
        View view= inflater.inflate(R.layout.fragment_user_register_fragment, container, false);
        etName=view.findViewById(R.id.etUname);
        etEmail=view.findViewById(R.id.etULemail);
        etPhn=view.findViewById(R.id.etUphn);
        etPassword=view.findViewById(R.id.etUpass);
        etcPassword=view.findViewById(R.id.etUcpassword);
        btnRegister=view.findViewById(R.id.btnUregister);
        databaseDrivers= FirebaseDatabase.getInstance().getReference("Driver_Credentials");
        mAuth=FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String Name,Email,Phn,Password,cPassword;
                Name=etName.getText().toString().trim();//TODO also pass name and phone details to database after viewing costing
                Email=etEmail.getText().toString().trim();
                Phn=etPhn.getText().toString().trim();
                Password=etPassword.getText().toString().trim();
                cPassword=etcPassword.getText().toString().trim();

                if (Name.isEmpty()||Email.isEmpty()||Phn.isEmpty()||(Phn.length()<10)||Password.isEmpty()||cPassword.isEmpty()||(!Password.equals(cPassword)))
                    Toast.makeText(getActivity(),"Enter all fields and make sure password and confirm password are same",Toast.LENGTH_LONG).show();
                else
                {
                    mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful())
                            {

                                Toast.makeText(getActivity(),"User registered successfully",Toast.LENGTH_SHORT).show();
                                addCredentials();
                                Navigation.findNavController(view).navigate(R.id.action_user_register_fragment_to_user_login_fragment);
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }


            }
        });




    }
    public void addCredentials()
    {
        String dvid= databaseDrivers.push().getKey();
        String dvEmail,dvPhn,dvName,dUid = new String();
        dvEmail=etEmail.getText().toString().trim();
        dvPhn=etPhn.getText().toString().trim();
        dvName=etName.getText().toString().trim();
        driver_credentials dCredentials= new driver_credentials(dvid,dvEmail,dvPhn,dvName);
        databaseDrivers.child(dvid).setValue(dCredentials);
    }
}
