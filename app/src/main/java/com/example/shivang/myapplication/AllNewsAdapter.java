package com.example.shivang.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shivang on 14/10/17.
 */

public class AllNewsAdapter extends RecyclerView.Adapter<AllNewsAdapter.ViewHolder> {

    private Context context;
    private List<ArticleModel> articleModels;

    public AllNewsAdapter(Context context, List<ArticleModel> articleModels) {
        this.context = context;
        this.articleModels = articleModels;
    }

    @Override
    public AllNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.verification_news_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AllNewsAdapter.ViewHolder holder, int position) {

        Picasso.with(context)
                .load(articleModels.get(position).getUrlToImage())
                .into(holder.poster);

        holder.heading.setText(articleModels.get(position).getTitle());
        holder.desc.setText(articleModels.get(position).getDescription());
        holder.positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Positive feedback added",Toast.LENGTH_SHORT).show();

                SharedPreferences pref = context.getSharedPreferences("USED",Context.MODE_PRIVATE);
                if(pref.getBoolean("title",true)){
                    pref.edit().putBoolean("title",false).commit();
                }else{
                    holder.layout.setVisibility(View.INVISIBLE);
                }
                holder.layout.setVisibility(View.INVISIBLE);
            }
        });

        holder.negetive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.layout.setVisibility(View.INVISIBLE);
                Toast.makeText(context,"Negetive feedback added",Toast.LENGTH_SHORT).show();
                SharedPreferences pref = context.getSharedPreferences("USED",Context.MODE_PRIVATE);
                if(pref.getBoolean("title",true)){
                    pref.edit().putBoolean("title",false).commit();
                }else{
                    holder.layout.setVisibility(View.INVISIBLE);
                }
                holder.layout.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView heading, desc;
        private LinearLayout layout;
        private Button positive, negetive;

        public ViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.thumbnail);
            heading = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.description);
            layout = itemView.findViewById(R.id.feedback);
            positive = itemView.findViewById(R.id.ac_positive);
            negetive = itemView.findViewById(R.id.ic_negetive);

        }
    }
}
