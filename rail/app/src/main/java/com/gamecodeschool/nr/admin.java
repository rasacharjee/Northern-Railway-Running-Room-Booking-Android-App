package com.gamecodeschool.nr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin extends AppCompatActivity {
    BottomNavigationView admin_bottomNavigation;
    NavController admin_navController;
    public String CRISvalue;
    public String email;
    String Key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        admin_bottomNavigation=findViewById(R.id.admin_btmNav);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Key = extras.getString("key");
            CRISvalue= extras.getString("crisid");
            email=extras.getString("email");

            //Toast.makeText(getApplicationContext(),CRISvalue,Toast.LENGTH_LONG).show();
        }
        if(!(email.equals("nr.bookings1@gmail.com")))
        {
            Toast.makeText(admin.this,"YOU DO NOT HAVE ADMIN ACCESS",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(admin.this,MainActivity.class);
            startActivity(intent);
        }
        admin_navController= Navigation.findNavController(this,R.id.admin_nav_host);
        NavigationUI.setupWithNavController(admin_bottomNavigation,admin_navController);




      /*  if (CRISvalue!=null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction= fragmentManager.beginTransaction();
            admin_booking_fragment homeobj= new admin_booking_fragment();
            Bundle b= new Bundle();
            b.putString("id",CRISvalue);
            homeobj.setArguments(b);
            transaction.add(R.id.admin_booking_fragment,homeobj);
            transaction.commit();
        }*/

      /*  FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        admin_home_fragment homeobj= new admin_home_fragment();
        if (extras !=null){
            Bundle b= new Bundle();
            b.putString("id",CRISvalue);
            homeobj.setArguments(b);
            transaction.add(R.id.admin_home_fragment,homeobj);
            transaction.commit();
        }*/
            /*if (CRISvalue != null){
                Bundle bundle=new Bundle();
                bundle.putString("Cid",CRISvalue);
                admin_home_fragment adminobj= new admin_home_fragment();
                adminobj.setArguments(bundle);

            }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(getApplicationContext(),"You are leaving ADMIN profile",Toast.LENGTH_SHORT).show();
        finishAffinity();
        System.exit(0);
    }
}
