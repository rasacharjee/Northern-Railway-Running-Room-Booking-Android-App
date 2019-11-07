package com.gamecodeschool.nr;


import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user_login_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user_login_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText etEmail,etPassword;
    Button btnLogin,btnRegister;
    TextView textView;
    FirebaseAuth fAuth;
     ProgressDialog progressDialog ;



    public user_login_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment user_login_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static user_login_fragment newInstance(String param1, String param2) {
        user_login_fragment fragment = new user_login_fragment();
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
        progressDialog=new ProgressDialog(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_login_fragment, container, false);
        etEmail=view.findViewById(R.id.etULemail);
        etPassword=view.findViewById(R.id.etULpassword);
        btnLogin=view.findViewById(R.id.btnUlogin);
        btnRegister=view.findViewById(R.id.btnRegister);

        fAuth=FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (fAuth.getCurrentUser()!=null)
        {
            Intent intent= new Intent(getActivity(),user_main_activity.class);
            startActivity(intent);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email,password;
                email=etEmail.getText().toString().trim();
                password=etPassword.getText().toString().trim();

                progressDialog.setTitle("WELCOME");
                progressDialog.setMessage("Please wait while logging in");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setProgress(0);
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.show();
                btnLogin.setEnabled(false);

                if(email.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(getActivity(),"Valid email and password required",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                else
                {
                    fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent= new Intent(getActivity(),user_main_activity.class);
                                startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Valid credentials required and error retrieved is"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                    });

                }

            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.setEnabled(false);
                Navigation.findNavController(view).navigate(R.id.user_register_fragment);
            }
        });
    }
}
