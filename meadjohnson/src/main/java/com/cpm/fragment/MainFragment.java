package com.cpm.fragment;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;
import com.cpm.download.CompleteDownloadActivity;
import com.cpm.gskgtsupervisor.MainMenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    WebView webView;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        imageView = (ImageView) view.findViewById(R.id.img_main);

        webView = (WebView) view.findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());

        String url = CommonString.URL_Notice_Board;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Activity activity = getActivity();
        if(activity instanceof CompleteDownloadActivity){
            CompleteDownloadActivity myactivity = (CompleteDownloadActivity) activity;
            myactivity.getSupportActionBar().setTitle("Main Menu");
        }
        else{
            ((MainMenuActivity) getActivity()).getSupportActionBar().setTitle("Main Menu");
        }

    }

   /* private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }*/

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
           /* progress.setVisibility(View.GONE);
            WebViewActivity.this.progress.setProgress(100);*/
            imageView.setVisibility(View.INVISIBLE);
            webView.setVisibility(View.VISIBLE);
            super.onPageFinished(view, url);
            view.clearCache(true);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
           /* progress.setVisibility(View.VISIBLE);
            WebViewActivity.this.progress.setProgress(0);*/
            super.onPageStarted(view, url, favicon);
        }


    }
}