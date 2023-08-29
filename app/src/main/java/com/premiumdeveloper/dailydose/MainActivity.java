package com.premiumdeveloper.dailydose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.premiumdeveloper.dailydose.Adapters.NewsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Article> articleList = new ArrayList<>();
    NewsRecyclerAdapter adapter;
    LinearProgressIndicator progressIndicator;
    Button general_category, sports_category, entertainment_category, science_category, technology_category, business_category, health_category;
    SearchView search_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        api-key=96e2d9c31cb94cfa83b5980f6f513433

        recyclerView = findViewById(R.id.news_recyclerview);
        progressIndicator = findViewById(R.id.progress_bar);
        general_category = findViewById(R.id.general_category);
        health_category = findViewById(R.id.health_category);
        sports_category = findViewById(R.id.sports_category);
        technology_category = findViewById(R.id.technology_category);
        business_category = findViewById(R.id.business_category);
        science_category = findViewById(R.id.science_category);
        entertainment_category = findViewById(R.id.entertainment_category);
        search_news = findViewById(R.id.search_news);
        setRecyclerView();
        getNews("GENERAL", null);
        loadCategory(general_category, general_category, health_category, sports_category, technology_category, business_category, science_category, entertainment_category);
        search_news.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getNews("GENERAL", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    void setRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsRecyclerAdapter(articleList);
        recyclerView.setAdapter(adapter);
    }

    void changeInProgress(boolean show){
        if (show){
            progressIndicator.setVisibility(View.VISIBLE);
        }else{
            progressIndicator.setVisibility(View.INVISIBLE);
        }
    }

    void loadCategory(Button general_category, Button health_category, Button sports_category, Button technology_category, Button business_category, Button science_category, Button entertainment_category, Button entertainmentCategory){
        general_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = general_category.getText().toString().toUpperCase();
                getNews(category, null);
            }
        });

        health_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = health_category.getText().toString().toUpperCase();
                getNews(category, null);
            }
        });

        sports_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = sports_category.getText().toString().toUpperCase();
                getNews(category, null);
            }
        });

        technology_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = technology_category.getText().toString().toUpperCase();
                getNews(category, null);
            }
        });

        business_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = business_category.getText().toString().toUpperCase();
                getNews(category, null);
            }
        });

        science_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = science_category.getText().toString().toUpperCase();
                getNews(category, null);
            }
        });

        entertainment_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String category = entertainment_category.getText().toString().toUpperCase();
                getNews(category, null);
            }
        });
    }



    void getNews(String category, String query){
        changeInProgress(true);
        NewsApiClient newsApiClient = new NewsApiClient("96e2d9c31cb94cfa83b5980f6f513433");

// /v2/top-headlines
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .language("en")
                        .category(category)
                        .q(query)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        runOnUiThread(()-> {
                            changeInProgress(false);
                            articleList = response.getArticles();
                            adapter.updateData(articleList);
                            adapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
//                        System.out.println(throwable.getMessage());
                        Log.i("APi works", "this api call failed!");
                    }
                }
        );

    }
}