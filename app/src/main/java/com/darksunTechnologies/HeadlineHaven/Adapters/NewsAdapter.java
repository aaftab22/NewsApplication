package com.example.HeadlineHaven.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.HeadlineHaven.Models.Articles;
import com.darksunTechnologies.HeadlineHaven.NewsDetailActivity;
import com.darksunTechnologies.HeadlineHaven.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder> {
    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public NewsAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_news,parent,false);
        return new NewsAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NewsAdapter.viewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.headingTV.setText(articles.getTitle());
        holder.subHeadingTV.setText(articles.getDescription());
        Picasso.get().load(articles.getUrlToImage()).into(holder.bannerIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sending data to new activity to show full content with banner
                Intent newsDataIntent = new Intent(context, NewsDetailActivity.class);
                newsDataIntent.putExtra("title",articles.getTitle());
                newsDataIntent.putExtra("desc",articles.getDescription());
                newsDataIntent.putExtra("content",articles.getContent());
                newsDataIntent.putExtra("imageUrl",articles.getUrlToImage());
                newsDataIntent.putExtra("url",articles.getUrl());
                context.startActivity(newsDataIntent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView headingTV,subHeadingTV;
        private ImageView bannerIV;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            headingTV = itemView.findViewById(R.id.newsHeading_ID);
            subHeadingTV = itemView.findViewById(R.id.newsSummary_ID);
            bannerIV = itemView.findViewById(R.id.NewsImage_ID);

        }
    }
}
