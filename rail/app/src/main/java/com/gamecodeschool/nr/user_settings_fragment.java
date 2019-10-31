package com.gamecodeschool.nr;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;



public class user_settings_fragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    Button button_upload,button_save;
    ImageView image_view;
    StorageReference mStorageRef;
    TextView tvName,tvAdd;
    private StorageTask uploadTask;
    public Uri imguri;
    //  private   FirebaseAuth fAuth;
    Button btn_logout,btn_password;


    public user_settings_fragment() {
        // Required empty public constructor
    }

    public static user_settings_fragment newInstance(String param1, String param2) {
        user_settings_fragment fragment = new user_settings_fragment();
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
        View view= inflater.inflate(R.layout.fragment_user_settings_fragment, container, false);
        mStorageRef= FirebaseStorage.getInstance().getReference("Images");
        button_upload=view.findViewById(R.id.button_upload);
        button_save=view.findViewById(R.id.button_save);
        image_view=view.findViewById(R.id.image_view);
        btn_logout=view.findViewById(R.id.btn_logout);
        tvAdd=view.findViewById(R.id.tvAdd);
        //   fAuth=FirebaseAuth.getInstance();
        btn_password=view.findViewById(R.id.btn_password);

        // loadUserInfo();

        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filechooser();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uploadTask!=null && uploadTask.isInProgress()){
                    Toast.makeText(getActivity(),"Upload is in progress",Toast.LENGTH_SHORT).show();
                }
                else {
                    Fileuploader();

                }
            }
        });
        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.change_password_fragment);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    /** private void loadUserInfo() {
     FirebaseUser user=fAuth.getCurrentUser();
     if(user!=null) {
     if (user.getPhotoUrl() != null) {
     Uri photoUrl = user.getPhotoUrl();
     Glide.with(this).load(user.getPhotoUrl().toString()).into(image_view);
     }
     if (user.getDisplayName() != null) {
     String displayName = user.getDisplayName();
     tvName.setText(user.getDisplayName());
     }
     }
     }**/

    private String getExtension(Uri uri)
    {
        ContentResolver cr= getContext().getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void Fileuploader() {
        StorageReference Ref=mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));
        StorageReference imageFilePath= mStorageRef.child(imguri.getLastPathSegment());
        uploadTask=  Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(getActivity(),"Image Uploaded successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private void Filechooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data.getData()!=null )
        {
            imguri=data.getData();
            image_view.setImageURI(imguri);
        }
    }
}
