package com.gamecodeschool.nr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;


public class user_main_activity extends AppCompatActivity  {


    BottomNavigationView bottomNavigationView;
    NavController navController;
    String CrisId;

    List<cities_java_class> citiesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_activity);
        CrisId= FirebaseAuth.getInstance().getCurrentUser().getUid();
         bottomNavigationView= findViewById(R.id.bottomNav);
         navController= Navigation.findNavController(this,R.id.nav_host_fragment);
         if(CrisId==NULL)
         {
             Toast.makeText(user_main_activity.this,"Invalid User Access",Toast.LENGTH_SHORT).show();
             Intent intent=new Intent(user_main_activity.this,user_login_register_main_activity.class);
             startActivity(intent);
         }

        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "You are exiting the USER profile", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(user_main_activity.this,MainActivity.class);
        startActivity(intent);
    }
}
