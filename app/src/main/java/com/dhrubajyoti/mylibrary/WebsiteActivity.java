package com.dhrubajyoti.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        //opt Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // hide title bar
        getSupportActionBar().hide();

        Intent intent = getIntent();
        if(intent != null) {
            webView = findViewById(R.id.webView);
            webView.loadUrl(intent.getStringExtra("url"));
//            webView.setWebViewClient(new WebViewClient());
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (webView.canGoBack()) {
//            webView.goBack();
//        }else{
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}