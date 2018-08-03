package com.mycampusdock.dock;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExamFragment extends Fragment {
    private TextView loading;
    private ProgressBar progress;
    public static final String EXAM_URL = "http://cdc-test.herokuapp.com/";

    public static ExamFragment newInstance() {
        ExamFragment f = new ExamFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exam, container, false);
        WebView web = v.findViewById(R.id.webview);
        loading = v.findViewById(R.id.loading);
        progress = v.findViewById(R.id.progress);
        web.setWebViewClient(new MyBrowser());
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int p) {
                if (p > 99) {
                    progress.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                } else {
                    progress.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.VISIBLE);
                }
            }
        });
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.loadUrl(EXAM_URL);
        return v;
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}

