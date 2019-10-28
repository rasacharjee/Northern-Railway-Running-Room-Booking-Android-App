package com.gamecodeschool.nr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class adaptercity extends RecyclerView.Adapter<adaptercity.cityViewHolder> {

    private Context context;
    private List<cities_java_class> citiesList;

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
    public void onBindViewHolder(@NonNull cityViewHolder holder, int position) {
        cities_java_class cities= citiesList.get(position);

        holder.tv.setText(cities.getCity());
        holder.iv.setImageDrawable(context.getResources().getDrawable(cities.getImage()));    }

    @Override
    public int getItemCount() {
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
