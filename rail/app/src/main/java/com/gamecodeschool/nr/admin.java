package com.gamecodeschool.nr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin extends AppCompatActivity {
    BottomNavigationView admin_bottomNavigation;
    NavController admin_navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        admin_bottomNavigation=findViewById(R.id.admin_btmNav);
        admin_navController= Navigation.findNavController(this,R.id.admin_nav_host);
        NavigationUI.setupWithNavController(admin_bottomNavigation,admin_navController);
    }
}
