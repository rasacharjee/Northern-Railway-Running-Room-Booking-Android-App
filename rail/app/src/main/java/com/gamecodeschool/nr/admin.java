package com.gamecodeschool.nr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin extends AppCompatActivity {
    BottomNavigationView admin_bottomNavigation;
    NavController admin_navController;
    String CRISvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        admin_bottomNavigation=findViewById(R.id.admin_btmNav);
        admin_navController= Navigation.findNavController(this,R.id.admin_nav_host);
        NavigationUI.setupWithNavController(admin_bottomNavigation,admin_navController);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CRISvalue = extras.getString("key");

            //Toast.makeText(getApplicationContext(),CRISvalue,Toast.LENGTH_LONG).show();
        }

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
