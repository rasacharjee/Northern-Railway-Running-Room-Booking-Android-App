package com.gamecodeschool.nr;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class adaptercity extends RecyclerView.Adapter<adaptercity.cityViewHolder> {

    private Context context;
    private List<cities_java_class> citiesList;

    private DatabaseReference databaseReference;

    public adaptercity(Context mCt, List<cities_java_class> citiesList) {
        this.context = mCt;
        this.citiesList = citiesList;
    }

    @NonNull
    @Override
    public cityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.card,parent,false);
        cityViewHolder holder= new cityViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final cityViewHolder holder, int position) {
        cities_java_class cities= citiesList.get(position);

        holder.tv.setText(cities.getCity());
        final String compare=cities.getCity();
        holder.iv.setImageDrawable(context.getResources().getDrawable(cities.getImage()));

            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
              /*  if(compare=="FEROZPUR"){
                   room_booking_fragment moor=new room_booking_fragment();
                   // ((FragmentActivity)v.getContext()).getSupportFragmentManager().
                    ((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.ReplaceLayout,moor).commit();

                           // beginTransaction().replace(R.id.ReplaceLayout,moor).commit();

                    FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                      manager.beginTransaction().replace(R.id.ReplaceLayout,moor).commit();
                }
                else {
                    Toast.makeText(context,"No Rooms available",Toast.LENGTH_LONG).show();
                } */

                databaseReference= FirebaseDatabase.getInstance().getReference("Rooms");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot rooms:dataSnapshot.getChildren())
                        {
                            room_available_java_class newclass= rooms.getValue(room_available_java_class.class);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                if (compare=="FEROZPUR"){
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialogbox);
                    dialog.setCancelable(false);
                    dialog.setTitle("Room Availability Status");

                    Button btn_Can = dialog.findViewById(R.id.btn_Can);
                    Button btn_Book = dialog.findViewById(R.id.btn_Book);
                    btn_Can.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "Booking Cancelled", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    btn_Book.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            room_booking_fragment room = new room_booking_fragment();

                            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.ReplaceLayout, room).commit();

                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
                    else{
                        Toast.makeText(context,"No Rooms available",Toast.LENGTH_LONG).show();
                    }

                }

            });
    }


    @Override
    public int getItemCount()
    {
        return citiesList.size();
    }

    class cityViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;

        public cityViewHolder(@NonNull View itemView) {
            super(itemView);

            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);

        }
    }
}
