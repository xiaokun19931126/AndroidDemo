package com.xiaokun.xiusou.demo6.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaokun.xiusou.demo6.R;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class WebActivity extends AppCompatActivity {
    @Bind(R.id.mWeb)
    WebView webView;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        Log.d("url", "url:" + url);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                webView.loadUrl("http://www.baidu.com");
//                view.loadUrl(url);
                return false;
            }
        });
        webView.loadUrl(url);
    }
}
