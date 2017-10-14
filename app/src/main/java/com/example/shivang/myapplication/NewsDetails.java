package com.example.shivang.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        String author="", title="", desc="", publish="", imgURL="";


        Bundle bundle = getIntent().getExtras();

        for(String key : bundle.keySet()){
            author = bundle.getString("author");
            title = bundle.getString("title");
            desc = bundle.getString("description");
            imgURL = bundle.getString("urlToImage");
            publish = bundle.getString("publishedAt");
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

        FirebaseDatabase mDatabase;
        DatabaseReference mRef;

        FirebaseAuth mAuth;
        FirebaseUser mUser;

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();

    }
}
