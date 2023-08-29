package com.premiumdeveloper.dailydose.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.premiumdeveloper.dailydose.NewsDetail;
import com.premiumdeveloper.dailydose.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>{

    List<Article> articleList;
    public NewsRecyclerAdapter(List<Article> articleList){
        this.articleList = articleList;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.titleTxt.setText(article.getTitle());
        holder.sourceTxt.setText(article.getSource().getName());
        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.ic_baseline_broken_image_24)
                .placeholder(R.drawable.ic_baseline_broken_image_24)
                .into(holder.articleImg);

        holder.itemView.setOnClickListener((v -> {
            Intent intent = new Intent(v.getContext(), NewsDetail.class);
            intent.putExtra("url", article.getUrl());
            v.getContext().startActivity(intent);
        }));

    }

    public void updateData(List<Article> data){
        articleList.clear();
        articleList.addAll(data);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView titleTxt, sourceTxt;
        ImageView articleImg;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.article_title);
            sourceTxt = itemView.findViewById(R.id.article_source);
            articleImg = itemView.findViewById(R.id.article_img);
        }
    }
}
