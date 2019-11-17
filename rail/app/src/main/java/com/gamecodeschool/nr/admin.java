package com.gamecodeschool.nr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class admin extends AppCompatActivity {
    BottomNavigationView admin_bottomNavigation;
    NavController admin_navController;
    public String CRISvalue;
    public String email;
    String Key;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;

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
        if(!(email.equals("nr.bookings1@gmail.com")||email.equals("sanjeev.1jan1975@gmail.com")||email.equals("opvij1969@gmail.com")))
        {
            Toast.makeText(admin.this,"YOU DO NOT HAVE ADMIN ACCESS",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(admin.this,MainActivity.class);
            startActivity(intent);
        }
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                }
                            });
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
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
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "You are exiting the Admin Profile", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(admin.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                   // Toast.makeText(this,"PERMISSION GRANTED",Toast.LENGTH_SHORT).show();
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}
