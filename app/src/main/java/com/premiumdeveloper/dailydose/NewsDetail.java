package com.premiumdeveloper.dailydose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsDetail extends AppCompatActivity {
    WebView datail_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        String url = getIntent().getStringExtra("url");
        datail_webview = findViewById(R.id.details_webview);
        WebSettings webSettings = datail_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        datail_webview.setWebViewClient(new WebViewClient());
        datail_webview.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if(datail_webview.canGoBack()){
            datail_webview.goBack();
        }else{
            super.onBackPressed();
        }
    }
}