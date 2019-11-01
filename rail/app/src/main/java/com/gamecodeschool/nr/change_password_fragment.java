package com.gamecodeschool.nr;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class change_password_fragment extends Fragment {


    EditText etM;
    Button button_authenticate;
    FirebaseAuth mAuth;

    public change_password_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etM=view.findViewById(R.id.etM);
        button_authenticate=view.findViewById(R.id.button_authenticate);
        mAuth=FirebaseAuth.getInstance();
        button_authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usermail=etM.getText().toString().trim();

                if (usermail.equals(" ")){
                    Toast.makeText(getActivity(),"Please enter your email id",Toast.LENGTH_SHORT).show();

                }
                else{
                    mAuth.sendPasswordResetEmail(usermail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getActivity(),"Password Reset email sent!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(),MainActivity.class));
                                mAuth.signOut();
                                getActivity().finish();
                                Intent intent=new Intent(getActivity(),MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getActivity(),"Error in sending password reset email",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

}
