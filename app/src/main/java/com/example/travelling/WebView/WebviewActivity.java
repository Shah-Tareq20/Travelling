package com.example.travelling.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.travelling.R;

public class WebviewActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.webview_id);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String value = bundle.getString("key");
            showDetails(value);
        }
    }
    private void showDetails(String value) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        /*if(value.equals("hotel")){
            webView.loadUrl("https://www.booking.com/index.html?aid=1784897&label=affnetawin-index_pub-318631_site-_pname-Shoplooks.com_plc-_ts-sl_stu8t3_clkid-18119_1668870535_34beca601a82111605514b57b818aa2a&utm_source=affnetawin&utm_medium=&utm_term=index&utm_content=318631");
        }
        if(value.equals("food")){
            webView.loadUrl("https://www.foodpanda.com.bd/");
        }*/
        if(value.equals("plane")){
            webView.loadUrl("https://www.flightexpert.com/");
        }
        if(value.equals("train")){
            webView.loadUrl("https://eticket.railway.gov.bd/en");
        }
        if(value.equals("bus")){
            webView.loadUrl("https://www.shohoz.com/bus-tickets");
        }
        if(value.equals("ship")){
            webView.loadUrl("https://launchbd.com/en_US/");
        }
        if(value.equals("car")){
            webView.loadUrl("https://www.uber.com/bd/en/");
        }


        webView.setWebViewClient(new WebViewClient());

    }
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}