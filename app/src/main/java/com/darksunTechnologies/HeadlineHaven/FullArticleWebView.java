package com.darksunTechnologies.HeadlineHaven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FullArticleWebView extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_artical_web_view);
        String url;
        webView = findViewById(R.id.webview_ID);

        url = getIntent().getStringExtra("urlForArticle");

        webView.setWebViewClient(new WebViewClient());
        assert url != null;
        webView.loadUrl(url);
    }
}