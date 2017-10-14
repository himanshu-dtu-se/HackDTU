package com.example.shivang.myapplication;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * Created by shivang on 14/10/17.
 */

public class AllNewsFragment extends Fragment {
    private RecyclerView mRecycler;
    private NewsAdapter newsAdapter1;

    private List<ArticleModel> articlesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.all_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecycler = view.findViewById(R.id.recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecycler.setHasFixedSize(true);

        load_data();

    }

    private void load_data() {

        articlesList = new ArrayList<>();

        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setIndeterminate(false);
        dialog.setMessage("Getting data. Please Wait....");
        dialog.show();

        StringRequest request = new StringRequest(
                Request.Method.GET, "https://newsapi.org/v1/ArticleModel?source=the-next-web&sortBy=latest&apiKey=3e3cef2db4ed40b681d3e968392f4b49"
                ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();

                        try {
                            JSONObject jsonObject= new JSONObject(response);

                            JSONArray array = jsonObject.getJSONArray("ArticleModel");

                            for (int i=0; i<array.length(); i++){
                                JSONObject item = array.getJSONObject(i);
                                articlesList.add(new ArticleModel(
                                        item.getString("author"),
                                        item.getString("title"),
                                        item.getString("description"),
                                        item.getString("url"),
                                        item.getString("urlToImage"),
                                        item.getString("publishedAt")
                                ));
                            }

                            newsAdapter1 = new NewsAdapter(getActivity().getApplicationContext(), articlesList);
                            mRecycler.setAdapter(newsAdapter1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        Snackbar.make(getActivity().getCurrentFocus(), "Could not fetch news.",Snackbar.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);

    }
}
