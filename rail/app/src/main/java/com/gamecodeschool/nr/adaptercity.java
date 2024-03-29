package com.gamecodeschool.nr;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    DatabaseReference databaseFerozpurRooms,databaseLudhianaRooms;
    int room,lroom;

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
                public void onClick(final View v) {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialogbox);
                    dialog.setCancelable(false);
                    dialog.setTitle("Room Availability Status");

                    final Button btn_Can = dialog.findViewById(R.id.btn_Can);
                    final Button btn_Book = dialog.findViewById(R.id.btn_Book);
                    final TextView tvRoomsAvailable=dialog.findViewById(R.id.uRoomsAvailable);

              if (compare.equals("FEROZPUR")){
                  databaseFerozpurRooms= FirebaseDatabase.getInstance().getReference("Rooms").child("FEROZPURrooms");
                  databaseFerozpurRooms.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String vroot=dataSnapshot.getValue().toString();//directly getting the value of rooms,no further class required
                            tvRoomsAvailable.setText(vroot);
                            room=Integer.parseInt(vroot);
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) {
                          Toast.makeText(context, "Try again later", Toast.LENGTH_SHORT).show();
                      }
                  });

                     //tvRoomsAvailable.setText("65");
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
                                Bundle bundle=new Bundle();
                                bundle.putString("cities",compare);
                                room.setArguments(bundle);
                              ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.ReplaceLayout, room).commit();

                              dialog.dismiss();
                          }
                      });


                    /*else {
                        Toast.makeText(context, "No rooms available thus book button disabled", Toast.LENGTH_SHORT).show();
                    }*/
                    dialog.show();
                }

                else if (compare.equals("LUDHIANA")){
                  databaseLudhianaRooms= FirebaseDatabase.getInstance().getReference("Rooms").child("LUDHIANArooms");
                  databaseLudhianaRooms.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                          String lroot=dataSnapshot.getValue().toString();//directly getting the value of rooms,no further class required
                          tvRoomsAvailable.setText(lroot);
                          room=Integer.parseInt(lroot);
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) {
                          Toast.makeText(context, "Try again later", Toast.LENGTH_SHORT).show();
                      }
                  });

                  //tvRoomsAvailable.setText("65");
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
                          Bundle bundle=new Bundle();
                          bundle.putString("cities",compare);
                          room.setArguments(bundle);
                          ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.ReplaceLayout, room).commit();

                          dialog.dismiss();
                      }
                  });


                    /*else {
                        Toast.makeText(context, "No rooms available thus book button disabled", Toast.LENGTH_SHORT).show();
                    }*/
                  dialog.show();
              }
                    else{
                        tvRoomsAvailable.setText("0");
                        Toast.makeText(context,"No Rooms available",Toast.LENGTH_LONG).show();
                        //migth require to disable the booking button
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
