package com.example.shivang.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }

}
