package com.gamecodeschool.nr;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.gamecodeschool.nr.R.layout.admin_card_book_previous;


public class AdminAdapterBook extends RecyclerView.Adapter<AdminAdapterBook.BookViewHolder> {
    private Context mctx;
    private List<admin_book>AbookList;
    private File pdffile;
    String filename;
    String name;
    String dateinpdf;
    String dateoutpdf;
    String timeinpdf;
    String timeoutpdf;
    String idpdf;
    String citynamepdf;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

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
    public void onBindViewHolder(@NonNull BookViewHolder holder, final int position) {
        final admin_book abooked= AbookList.get(position);
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

        holder.pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    filename=abooked.getName()+abooked.getCityname()+abooked.getCrisId()+position;
                    name="NAME:   "+abooked.getName();
                    dateinpdf="CHECK-IN DATE:   "+abooked.getCheckinDate();
                    dateoutpdf="CHECK-OUT DATE:   "+abooked.getCheckoutDate();
                    timeinpdf="CHECK-IN TIME:   "+abooked.getCheckinTime();
                    timeoutpdf="CHECK-OUT TIME:   "+abooked.getCheckoutTime();
                    idpdf="CRIS ID:   "+abooked.getCrisId();
                    citynamepdf="CITY NAME:   "+abooked.getCityname();
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
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
        Button pdf;

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
            pdf=itemView.findViewById(R.id.btnpdf);
        }
    }

    private void createPdfWrapper() throws FileNotFoundException,DocumentException{
        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }

        pdffile = new File(docsFolder.getAbsolutePath(),randomAlphaNumeric(7)+".pdf");
        OutputStream output = new FileOutputStream(pdffile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        document.add(new Paragraph(citynamepdf));
        document.add(new Paragraph(name));
        document.add(new Paragraph(idpdf));
        document.add(new Paragraph(dateinpdf));
        document.add(new Paragraph(dateoutpdf));
        document.add(new Paragraph(timeinpdf));
        document.add(new Paragraph(timeoutpdf));

        document.close();
        Toast.makeText(mctx,"PDF Saved in Documents",Toast.LENGTH_SHORT).show();
       // previewPdf();
    }

    private void previewPdf() {

        PackageManager packageManager = mctx.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdffile);
            intent.setDataAndType(uri, "application/pdf");

            mctx.startActivity(intent);
        }else{
            Toast.makeText(mctx,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }
    public static String randomAlphaNumeric(int count) {

        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {

            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());

            builder.append(ALPHA_NUMERIC_STRING.charAt(character));

        }

        return builder.toString();

    }
}
