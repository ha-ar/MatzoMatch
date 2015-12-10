package com.algorepublic.matzomatch.fragments;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.algorepublic.matzomatch.BaseClass;
import com.androidquery.AQuery;
import com.algorepublic.matzomatch.R;

public class ActivityWebLink extends AppCompatActivity {

    AQuery aq;
    String link;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_webview);
        aq = new AQuery(this);
        aq.id(R.id.progress_bar).visibility(View.VISIBLE);
        final WebView wv_linker = (WebView) findViewById(R.id.weblinker);
        Bundle bundle = getIntent().getExtras();
        link = bundle.getString("Key");
        if ( link.equals("privacy")){
            wv_linker.loadUrl(BaseClass.privacy);
        } else
        if (link.equals("terms")){
            wv_linker.loadUrl(BaseClass.terms);
        }else {
            wv_linker.loadUrl(link);
        }
        aq.id(R.id.layout_arrow).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        wv_linker.getSettings().setJavaScriptEnabled(true);
        wv_linker.getSettings().setBuiltInZoomControls(true);
        wv_linker.getSettings().setSupportZoom(true);
        wv_linker.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv_linker.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(ActivityWebLink.this, "Oh no! webpage not found.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                return super.shouldOverrideUrlLoading(view, url);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                aq.id(R.id.progress_bar).visibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub

        super.onDestroy();
    }

}
