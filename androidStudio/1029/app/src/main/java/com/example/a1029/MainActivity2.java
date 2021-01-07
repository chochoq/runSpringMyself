package com.example.a1029;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    String url;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //로딩창띄우기
        progress = new ProgressDialog(this);
        progress.setMessage("페이지불러오는중");
        progress.show();

        Intent intent = getIntent();
        String url=intent.getStringExtra("url");


        //타이틀,뒤로가기버튼
        getSupportActionBar().setTitle(url);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title=findViewById(R.id.title);
        title.setText(Html.fromHtml(intent.getStringExtra("title")));

        WebView web = findViewById(R.id.web);
        web.setWebViewClient(new MyWebView());
        WebSettings settings=web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    class  MyWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        //페이지가 불러왔을때 로딩창사라지게
        @Override
        public void onPageFinished(WebView view, String url) {
            progress.dismiss();
            super.onPageFinished(view, url);
        }
    }
}