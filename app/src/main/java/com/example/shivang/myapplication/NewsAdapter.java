package com.example.shivang.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivang on 14/10/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    private Context mContext;
    private List<articles> mMovies;
    private MovieClickListener mListener;
    private LayoutInflater mInflater;

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.MovieViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public NewsAdapter(Context context){
        mContext = context;
        /*mMovies =  movies;
        mListener = listener;*/
        this.mInflater = LayoutInflater.from(context);
        this.mMovies = new ArrayList<>();
    }

}
