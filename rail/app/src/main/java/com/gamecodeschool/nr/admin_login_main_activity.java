package com.gamecodeschool.nr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
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
    final String cf="0077";
    final String cl="0006";
    String CRISvalue;
    ProgressDialog progressDialog1 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_main_activity);
        metAemail=findViewById(R.id.etAemail);
        metApass=findViewById(R.id.etApassword);
        metCris=findViewById(R.id.etAcrisID);
        mbtnAlogin=findViewById(R.id.btnAlogin);
        mfAuth=FirebaseAuth.getInstance();
        progressDialog1=new ProgressDialog(this);




        mbtnAlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog1.setTitle("WELCOME");
                progressDialog1.setMessage("Please wait while logging in");
                progressDialog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog1.setProgress(0);
                progressDialog1.setCanceledOnTouchOutside(true);
                //progressDialog1.show();

              //  mbtnAlogin.setEnabled(false);
                if (metAemail.getText().toString().isEmpty()||metApass.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please enter all fields",Toast.LENGTH_SHORT).show();
                    mbtnAlogin.setEnabled(true);
                }
                else if (!(metCris.getText().toString().equals(cAID)||metCris.getText().toString().equals(cf)||metCris.getText().toString().equals(cl)))
                {
                    progressDialog1.show();
                    Toast.makeText(getApplicationContext(),"Invalid credentials and access denied",Toast.LENGTH_LONG).show();
                    progressDialog1.dismiss();
                    mbtnAlogin.setEnabled(true);
                }
                else
                {
                    progressDialog1.show();
                    final String Aemail,Apassword;
                    Aemail=metAemail.getText().toString().trim();
                    Apassword=metApass.getText().toString().trim();
                    mfAuth.signInWithEmailAndPassword(Aemail,Apassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                CRISvalue=metCris.getText().toString();

                               /* FragmentManager fragmentManager=getSupportFragmentManager();
                                FragmentTransaction transaction= fragmentManager.beginTransaction();
                                admin_home_fragment homeobj= new admin_home_fragment();
                                if (CRISvalue!=null){

                                    Bundle b= new Bundle();
                                    b.putString("id",CRISvalue);
                                    homeobj.setArguments(b);
                                    transaction.add(R.id.admin_home_fragment,homeobj);
                                    transaction.commit();}*/
                                Intent intent= new Intent(getApplicationContext(),admin.class);
                                intent.putExtra("key",cAID);
                                intent.putExtra("email",Aemail);
                                intent.putExtra("crisid",CRISvalue);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_LONG).show();
                                progressDialog1.dismiss();

                        }
                    });
                }
            }
        });
    }
}
