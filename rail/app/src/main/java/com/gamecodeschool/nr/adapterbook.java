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

import static com.gamecodeschool.nr.R.layout.card_book_previous;

public class adapterbook extends RecyclerView.Adapter<adapterbook.BookViewHolder> {

    private Context mctx;
    private List<book>bookList;

    public adapterbook(Context mctx, List<book> bookList) {
        this.mctx = mctx;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mctx);
        View view=inflater.inflate(card_book_previous,null);
        BookViewHolder holder=new BookViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
         book booked= bookList.get(position);

         holder.cityidBook.setText(booked.getCityName());
         holder.entervalin.setText(booked.getDate());
         holder.entervalout.setText(booked.getOutdate());
         holder.tvtime1.setText(booked.getCheckInTime());
         holder.tvtime2.setText(booked.getCheckOutTime());

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        TextView cityidBook;
        TextView entervalin;
        TextView entervalout;
        TextView tvtime1;
        TextView tvtime2;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            cityidBook=itemView.findViewById(R.id.cityidBook);
            entervalin=itemView.findViewById(R.id.entervalin);
            entervalout=itemView.findViewById(R.id.entervalout);
            tvtime1=itemView.findViewById(R.id.tvtime1);
            tvtime2=itemView.findViewById(R.id.tvtime2);
        }
    }

}
