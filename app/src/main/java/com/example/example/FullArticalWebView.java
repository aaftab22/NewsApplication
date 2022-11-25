package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FullArticalWebView extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_artical_web_view);
        String url;
        webView = findViewById(R.id.webview_ID);

        url = getIntent().getStringExtra("urlforAtrical");

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);


    }
}