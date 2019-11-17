package com.gamecodeschool.nr;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> list;
    private String value;
    //private int mvalue;
    DatabaseReference databaseReference;

    public CustomAdapter(Context context,ArrayList<String> list)
    {
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.city,parent,false);
        MyViewHolder vh=new MyViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.MyViewHolder holder , final int position) {
        holder.cityid.setText(list.get(position));

            holder.btncity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    value=holder.enterval.getText().toString().trim();
                    if (value!=null){
                        AlertDialog.Builder dialog =new AlertDialog.Builder(context);
                        dialog.setTitle("Do you want to update?").setPositiveButton("Confirm" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog , int which) {
                                updateRooms(value,list.get(position));//updates rooms to specific station in database
                                Toast.makeText(context,"The number of rooms has been updated",Toast.LENGTH_SHORT).show();
                                holder.enterval.setText("");
                            }
                        }).setNegativeButton("Cancel" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog , int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert=dialog.create();
                        alert.show();
                    }
                    else {
                        Toast.makeText(context,"Enter valid number of rooms",Toast.LENGTH_SHORT).show();
                    }

                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cityid;
        EditText enterval;
        Button btncity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            enterval=itemView.findViewById(R.id.enterval);
            btncity=itemView.findViewById(R.id.btncity);
            cityid=itemView.findViewById(R.id.cityid);
        }
    }
    private void updateRooms(String value,String city){
        String dvalue=city+"rooms";
        databaseReference=FirebaseDatabase.getInstance().getReference("Rooms").child(dvalue);
        databaseReference.setValue(value);
    }
}
