package com.gamecodeschool.nr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.gamecodeschool.nr.R.layout.admin_card_book_previous;


public class AdminAdapterBook extends RecyclerView.Adapter<AdminAdapterBook.BookViewHolder> {
    private Context mctx;
    private List<admin_book>AbookList;

    public AdminAdapterBook(Context mctx, List<admin_book> AbookList) {
        this.mctx = mctx;
        this.AbookList = AbookList;
    }

    @NonNull
    @Override
    public AdminAdapterBook.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mctx);
        View view=inflater.inflate(R.layout.admin_card_book_previous,null);
        BookViewHolder holder= new BookViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        admin_book abooked= AbookList.get(position);
        if(abooked.getStatus().equals("booked"))
        {
            holder.tvSt.setVisibility(View.VISIBLE);
            holder.tvStatus.setVisibility(View.VISIBLE);
        }
        else if(abooked.getStatus().equals("free"))
        {
            holder.tvSt.setVisibility(View.GONE);
            holder.tvStatus.setVisibility(View.GONE);
        }
        holder.username.setText(abooked.getName());
        holder.dateIn.setText(abooked.getCheckinDate());
        holder.dateOut.setText(abooked.getCheckoutDate());
        holder.timeIn.setText(abooked.getCheckinTime());

        holder.timeOut.setText(abooked.getCheckoutTime());


        holder.crisId.setText(abooked.getCrisId());
    }


    @Override
    public int getItemCount() {
        return AbookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView dateIn;
        TextView dateOut;
        TextView timeIn;
        TextView timeOut;
        TextView crisId;
        TextView tvSt;
        TextView tvStatus;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            username=itemView.findViewById(R.id.tvUserName);
            dateIn=itemView.findViewById(R.id.tvInDateRight);
            dateOut=itemView.findViewById(R.id.tvOutDateRight);
            timeIn=itemView.findViewById(R.id.tvInTimeRight);
            timeOut=itemView.findViewById(R.id.tvOutTimeRight);
            crisId=itemView.findViewById(R.id.tvCrisRight);
            tvSt=itemView.findViewById(R.id.tvSt);
            tvStatus=itemView.findViewById(R.id.tvStatus);
        }
    }
}
