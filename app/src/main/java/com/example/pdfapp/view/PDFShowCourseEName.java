package com.example.pdfapp.view;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pdfapp.PDFuploaderActivity;
import com.example.pdfapp.R;
import com.example.pdfapp.adapter.List_Adapter;
import com.example.pdfapp.viewmodel.PDFViewmodel;

import java.util.ArrayList;

public class PDFShowCourseEName extends AppCompatActivity implements  ClickListener{


    private  ArrayList<String> allrelatedcourses=new ArrayList<>();

    private  PDFViewmodel viewmodel;
    private static String COURSE_NAME;

    private RecyclerView recyclerView;
    private List_Adapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfshow_course_ename);


         viewmodel = new ViewModelProvider(this).get(PDFViewmodel.class);
         adapter=new List_Adapter(allrelatedcourses,this,1);
         recyclerView=findViewById(R.id.showrecyclercourse);


        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
         recyclerView.setLayoutManager(layoutManager);
         recyclerView.setAdapter(adapter);
         adapter.setListener(this);

        viewmodel.getallrelatedcourses().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                allrelatedcourses.clear();
                allrelatedcourses.addAll(strings);
                adapter.notifyDataSetChanged();
            }
        });



    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PDFShowCourseEName.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("EXIT !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            super.onBackPressed();
            finish();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }





    public void Addpdf(View view) {
        startActivity(new Intent(PDFShowCourseEName.this, PDFuploaderActivity.class));
    }


    @Override
    public void itemclick(int position) {
        COURSE_NAME=allrelatedcourses.get(position);
        Intent intent=new Intent(PDFShowCourseEName.this, PDFListActivity.class);
        intent.putExtra("course_name",COURSE_NAME);
        startActivity(intent);

    }

    public void exitme(View view) {
        onBackPressed();
    }
}