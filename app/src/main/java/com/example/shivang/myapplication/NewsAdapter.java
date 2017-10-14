package com.example.shivang.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shivang on 14/10/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private Context mContext;
    private List<ArticleModel> mArticles;


    public NewsAdapter(Context mContext, List<ArticleModel> mArticles) {
        this.mContext = mContext;
        this.mArticles = mArticles;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, final int position) {
        holder.heading.setText(mArticles.get(position).getTitle());
        holder.desc.setText(mArticles.get(position).getDescription().substring(0,100).concat(" ......"));
        Picasso
                .with(mContext)
                .load(mArticles.get(0).getUrlToImage())
                .into(holder.poster);
        holder.mNewsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, NewsDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putSerializable("author",mArticles.get(position).getAuthor());
                bundle.putSerializable("title",mArticles.get(position).getTitle());
                bundle.putSerializable("description",mArticles.get(position).getDescription());
                bundle.putSerializable("urlToImage",mArticles.get(position).getUrlToImage());
                bundle.putSerializable("publishedAt",mArticles.get(position).getPublishedAt());

                intent.putExtras(bundle);

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView heading, desc;
        private LinearLayout mNewsCard;

        public ViewHolder(View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.thumbnail);
            heading = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.description);
            desc.setMaxLines(2);
            mNewsCard = itemView.findViewById(R.id.news_card);

        }
    }
}
