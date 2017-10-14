package com.example.shivang.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {

    private String author="", title="", desc="", publish="", imgURL="", unique_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Bundle bundle = getIntent().getExtras();

        for(String key : bundle.keySet()){
            author = bundle.getString("author");
            title = bundle.getString("title");
            desc = bundle.getString("description");
            imgURL = bundle.getString("urlToImage");
            publish = bundle.getString("publishedAt");
            unique_id = bundle.getString("unique_id");

        }

        ImageView img = (ImageView)findViewById(R.id.details_image);
        Picasso.with(getApplicationContext())
                .load(imgURL)
                .into(img);

        TextView tv_title = (TextView)findViewById(R.id.details_title);
        tv_title.setText(title);

        TextView tv_desc = (TextView)findViewById(R.id.details_desc);
        tv_desc.setText(desc);

        TextView tv_publish = (TextView)findViewById(R.id.details_publishdate);
        tv_publish.setText(publish.substring(0,10));



        Button button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                handle_fake_reg();

            }
        });
    }

    private void handle_fake_reg() {

        final ProgressDialog dialog = new ProgressDialog(NewsDetails.this);
        dialog.setMessage("Please Wait... While we take care everything");
        dialog.setIndeterminate(false);
        dialog.show();

        FirebaseDatabase mDatabase;
        final DatabaseReference mRef, mRef2;

        FirebaseAuth mAuth;
        FirebaseUser mUser;

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();

        mRef = mDatabase.getReference()
                .child("Users")
                .child(mUser.getUid())
                .child(unique_id);

        mRef2 = mDatabase.getReference()
                .child("Common")
                .child(unique_id);

        mRef.setValue(new
                        ArticleModel(
                                author,title,desc,imgURL,publish
                )).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            dialog.dismiss();

                            mRef2.setValue(new
                                    ArticleModel(
                                    author,title,desc,imgURL,publish
                            ));

                            new AlertDialog.Builder(NewsDetails.this)
                                    .setMessage("We have queued up this news for verification. You can check \'My queries\' section for more detail")
                                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialog.dismiss();
                                        }
                                    }).create().show();
                        }else{
                            dialog.dismiss();
                            Snackbar.make(getCurrentFocus(),"Could not add this query to queue", BaseTransientBottomBar.LENGTH_LONG)
                                    .setAction("RETRY", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            handle_fake_reg();
                                        }
                                    }).show();
                        }
                    }
                });


    }
}
