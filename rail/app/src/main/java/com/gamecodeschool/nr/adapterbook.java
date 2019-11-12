package com.gamecodeschool.nr;

import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.net.Uri;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.gamecodeschool.nr.R.layout.card_book_previous;

public class adapterbook extends RecyclerView.Adapter<adapterbook.BookViewHolder> {

    private Context mctx;
    private List<book>bookList;
    int room;
    int val;
    DatabaseReference databaseFerozpurRooms,databaseAmritsarRooms,databasePathankotRooms,databaseJalandharRooms,databaseJammuRooms,databaseKatraRooms,databaseBaijnathRooms;



    public adapterbook(Context mctx, List<book> bookList) {
        this.mctx = mctx;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mctx).inflate(card_book_previous,parent,false);
        BookViewHolder holder=new BookViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder holder, int position) {
         final book booked= bookList.get(position);

         holder.cityidBook.setText(booked.getCityName());
         holder.entervalin.setText(booked.getDate());
         holder.entervalout.setText(booked.getOutdate());
         holder.tvtime1.setText(booked.getCheckInTime());
         holder.tvtime2.setText(booked.getCheckOutTime());

         holder.btncomplaint.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                  final Dialog comdialog=new Dialog(mctx);
                comdialog.setContentView(R.layout.complaint_dialog);
                comdialog.setCancelable(false);
                comdialog.setTitle("LODGE COMPLAINT");

                final TextView tvmail=comdialog.findViewById(R.id.tvmail);
                final EditText etSub=comdialog.findViewById(R.id.etSub);
                final EditText etmess=comdialog.findViewById(R.id.etmess);
                final Button button_snd=comdialog.findViewById(R.id.button_snd);
                final String[] MAIL={"nr.bookings1@gmail.com"};
                button_snd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.setData(Uri.parse("mail to: "));
                        intent.putExtra(Intent.EXTRA_EMAIL,MAIL);
                        intent.putExtra(Intent.EXTRA_SUBJECT,etSub.getText().toString());
                        intent.putExtra(Intent.EXTRA_TEXT,etmess.getText().toString());
                        intent.setType("message/rfc822");
                        mctx.startActivity(Intent.createChooser(intent,"Choose an email client"));
                        comdialog.dismiss();
                    }
                 });
                 comdialog.show();
             }
         });


        databaseFerozpurRooms = FirebaseDatabase.getInstance().getReference("Rooms").child("FEROZPURrooms");
        databaseAmritsarRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("AMRITSARrooms");
        databaseBaijnathRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("BAIJNATHrooms");
        databaseJalandharRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("JALANDHARrooms");
        databaseJammuRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("JAMMUrooms");
        databaseKatraRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("KATRArooms");
        databasePathankotRooms=FirebaseDatabase.getInstance().getReference("Rooms").child("PATHANKOTrooms");

         holder.btnCheckout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(booked.getCityName().equals("FEROZPUR")) {
                     val = 1;
                     databaseFerozpurRooms.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             while (val > 0) {
                                 String vroot = dataSnapshot.getValue().toString();
                                 room = Integer.parseInt(vroot);
                                 Log.d("LOG" , "ROOM:-" + room);
                                 room = room + 1;//if rooms can be accessed here
                                 Log.d("LOGR" , "Room updated:-" + room);
                                 databaseFerozpurRooms.setValue(room);
                                 Toast.makeText(mctx , "Checkout successful" , Toast.LENGTH_LONG).show();
                                 holder.btnCheckout.setEnabled(false);
                                 val--;
                             }
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {
                         }
                     });
                 }


             }
         });
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

        Button btnCheckout;
        Button btncomplaint;


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            cityidBook=itemView.findViewById(R.id.cityidBook);
            entervalin=itemView.findViewById(R.id.entervalin);
            entervalout=itemView.findViewById(R.id.entervalout);
            tvtime1=itemView.findViewById(R.id.tvtime1);
            tvtime2=itemView.findViewById(R.id.tvtime2);
            btnCheckout=itemView.findViewById(R.id.btnCheckout);
            btncomplaint=itemView.findViewById(R.id.btncomplaint);
        }
    }

}
