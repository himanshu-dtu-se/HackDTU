package com.example.shivang.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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
    }
}
