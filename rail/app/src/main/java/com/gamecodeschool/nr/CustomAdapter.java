package com.gamecodeschool.nr;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> list;
    private String value;

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
    public void onBindViewHolder(@NonNull final CustomAdapter.MyViewHolder holder , int position) {
        holder.cityid.setText(list.get(position));
        holder.btncity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=holder.enterval.getText().toString().trim();
                Toast.makeText(context , "SUCCESSFULLY UPDATED" , Toast.LENGTH_SHORT).show();

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
}
