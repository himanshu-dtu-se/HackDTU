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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivang on 14/10/17.
 */

public class MyQueries extends Fragment{

    private RecyclerView mRecycler;

    private NewsAdapter newsAdapter1;

    private List<ArticleModel> articlesList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.all_news, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("My Queries");
        mRecycler = view.findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(llm);
        mRecycler.setAdapter(new AllNewsAdapter(getContext(), new ArrayList<ArticleModel>()));

        load_data();

    }

    private void load_data() {

        articlesList = new ArrayList<>();

        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setIndeterminate(false);
        dialog.setMessage("Getting data. Please Wait....");
        dialog.show();

        StringRequest request = new StringRequest(
                Request.Method.GET, "https://api.myjson.com/bins/133oo1"
                ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();

                        try {
                            JSONObject jsonObject= new JSONObject(response);

                            JSONObject obj = jsonObject.getJSONObject("Users");

                            JSONObject item = obj.getJSONObject("MF7YSJcX3gYfelQ2Djrq3ThhyRF3");

                            JSONObject item2 = item.getJSONObject("9ac63d26f8b61ac5a013e59410fb8df029d1f42c");
                            articlesList.add(
                                    new ArticleModel(
                                            item2.getString("author"),
                                            item2.getString("title"),
                                            item2.getString("description"),
                                            item2.getString("urlToImage"),
                                            item2.getString("publishedAt"),
                                            "9ac63d26f8b61ac5a013e59410fb8df029d1f42c"));


                            /*for (int i=0; i<obj.length(); i++){

                                try {
                                    articlesList.add(new ArticleModel(
                                            item.getString("author"),
                                            item.getString("title"),
                                            item.getString("description"),
                                            item.getString("url"),
                                            item.getString("urlToImage"),
                                            item.getString("publishedAt"),
                                            AeSimpleSHA1.SHA1(item.getString("title"))
                                    ));
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
*/
                            newsAdapter1 = new NewsAdapter(getContext() , articlesList);

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
