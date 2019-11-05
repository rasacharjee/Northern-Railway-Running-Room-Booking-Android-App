package com.gamecodeschool.nr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin_login_main_activity extends AppCompatActivity {
    EditText metAemail,metApass,metCris;
    Button mbtnAlogin;
    FirebaseAuth mfAuth;
    final String cAID="123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_main_activity);
        metAemail=findViewById(R.id.etAemail);
        metApass=findViewById(R.id.etApassword);
        metCris=findViewById(R.id.etAcrisID);
        mbtnAlogin=findViewById(R.id.btnAlogin);
        mfAuth=FirebaseAuth.getInstance();

        mbtnAlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbtnAlogin.setEnabled(false);
                if (metAemail.getText().toString().isEmpty()||metApass.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else if (!(metCris.getText().toString().equals(cAID)))
                {
                    Toast.makeText(getApplicationContext(),"Invalid credentials and access denied",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String Aemail,Apassword;
                    Aemail=metAemail.getText().toString().trim();
                    Apassword=metApass.getText().toString().trim();
                    mfAuth.signInWithEmailAndPassword(Aemail,Apassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent= new Intent(admin_login_main_activity.this,admin.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });
    }
}
