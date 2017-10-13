package com.example.shivang.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shivang on 14/10/17.
 */

public class NewsAdapter1 extends RecyclerView.Adapter<NewsAdapter1.ViewHolder>{

    private Context mContext;
    private List<articles> mArticles;


    public NewsAdapter1(Context mContext, List<articles> mArticles) {
        this.mContext = mContext;
        this.mArticles = mArticles;
    }

    @Override
    public NewsAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter1.ViewHolder holder, int position) {
        holder.heading.setText(mArticles.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView heading;
        private LinearLayout mNewsCard;

        public ViewHolder(View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.thumbnail);
            heading = itemView.findViewById(R.id.title);
            mNewsCard = itemView.findViewById(R.id.news_card);

        }
    }
}
