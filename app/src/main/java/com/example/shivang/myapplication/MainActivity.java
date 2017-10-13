package com.example.shivang.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private RecyclerView mRecycler;

    private NewsAdapter1 newsAdapter1;

    private List<articles> articlesList;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mRecycler = (RecyclerView)findViewById(R.id.recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecycler.setHasFixedSize(true);

        load_data();

    }

    private void load_data() {

        articlesList = new ArrayList<>();

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setIndeterminate(false);
        dialog.setMessage("Getting data. Please Wait....");
        dialog.show();

        StringRequest request = new StringRequest(
                Request.Method.GET, "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=3e3cef2db4ed40b681d3e968392f4b49"
                ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();

                        try {
                            JSONObject jsonObject= new JSONObject(response);

                            JSONArray array = jsonObject.getJSONArray("articles");

                            for (int i=0; i<array.length(); i++){
                                JSONObject item = array.getJSONObject(i);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);

    }

}
