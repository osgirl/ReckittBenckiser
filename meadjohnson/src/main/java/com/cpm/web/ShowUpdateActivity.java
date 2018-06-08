package com.cpm.web;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.cpm.capitalfoods.R;
import com.cpm.gskgtsupervisor.MainActivity;
import com.cpm.gskgtsupervisor.MainMenuActivity;
import com.cpm.gskgtsupervisor.MenuActivity;

public class ShowUpdateActivity extends Activity {

	//WebView mWebView;

	Button mcontinuebtn;
	String status, service_status;
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		
		mcontinuebtn = (Button) findViewById(R.id.header);
		/*mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://blogs.ubc.ca/rsharma/files/2014/10/mccain-foods.png");

		mWebView.setWebViewClient(new HelloWebViewClient());
*/
		mcontinuebtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

			/*	Intent i = new Intent(ShowUpdateActivity.this,
						MainActivity.class);
				startActivity(i);*/

				Intent i = new Intent(ShowUpdateActivity.this,
						MainMenuActivity.class);
				startActivity(i);

				ShowUpdateActivity.this.finish();

			}
		});

	}

	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(ShowUpdateActivity.this, com.cpm.gskgtsupervisor.LoginActivity.class);
		startActivity(i);
		ShowUpdateActivity.this.finish();

	}

}
