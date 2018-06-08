package com.cpm.gskgtsupervisor;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.cpm.Constants.CommonString;

import com.cpm.dailyentry.DailyEntryScreen;
import com.cpm.dailyentry.Performance;
import com.cpm.dailyentry.UploadSelect;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;


import com.cpm.download.CompleteDownloadActivity;
import com.cpm.upload.UploadDataActivity;

import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.message.AlertMessage;

public class MainActivity extends Activity implements OnClickListener {

	Button downloadBtn,dailyEntry,exit,download,upload,performance;
	
	GSKDatabase database;
	ArrayList<JourneyPlanGetterSetter> jcplist;
	private SharedPreferences preferences;
	private String date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);
		
		upload = (Button)findViewById(R.id.upload);
		/*gatemeeting  = (Button)findViewById(R.id.gatemeeting);
     	myperformance = (Button)findViewById(R.id.myperformance);*/
		dailyEntry = (Button)findViewById(R.id.dash_dailyentry);
		download = (Button)findViewById(R.id.download);
		/*teamattendence = (Button)findViewById(R.id.teamattendence);
     	myattendence = (Button)findViewById(R.id.myattendence);*/
		exit = (Button)findViewById(R.id.exit);
		performance = (Button)findViewById(R.id.performance);
		
		database = new GSKDatabase(this);
		database.open();

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		date = preferences.getString(CommonString.KEY_DATE, null);

	}

	@Override

	protected void onStart(){
		super.onStart();
		upload.setOnClickListener(this);
		/*gatemeeting.setOnClickListener(this);
		myperformance.setOnClickListener(this);*/
		dailyEntry.setOnClickListener(this);
		download.setOnClickListener(this);
		/*teamattendence.setOnClickListener(this);
		myattendence.setOnClickListener(this);
		 */		exit.setOnClickListener(this);
		 performance.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		if(v.getId()==R.id.download){
			
			if(checkNetIsAvailable()){
				
				Intent startDownload = 	new Intent(MainActivity.this,CompleteDownloadActivity.class);
				startActivity(startDownload);
				MainActivity.this.finish();
			}
			else{
				Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_SHORT).show();
			}
			
		}else if(v.getId()==R.id.dash_dailyentry){
			Intent startDownload = 	new Intent(MainActivity.this,DailyEntryScreen.class);
			startActivity(startDownload);
			
			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
			
			//finish();
		}
		else if(v.getId()==R.id.exit){
			Intent startDownload = 	new Intent(MainActivity.this,LoginActivity.class);
			startActivity(startDownload);
			MainActivity.this.finish();
			
		}
		else if(v.getId()==R.id.upload){
			if (checkNetIsAvailable()) {

				jcplist=database.getJCPData(date);

				if (jcplist.size() == 0) {
					Toast.makeText(getBaseContext(), "Please Download Data First",
							Toast.LENGTH_LONG).show();
				} else {

					if(preferences.getString(CommonString.KEY_STOREVISITED_STATUS, "").equals("Yes")){
						Toast.makeText(getApplicationContext(), "First checkout of store", Toast.LENGTH_SHORT).show();
					}
					else{
						
						ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();
						//ArrayList<GeotaggingBeans> gdata = new ArrayList<GeotaggingBeans>();

						cdata = database.getCoverageData(date);
						//gdata = database.getGeotaggingData("Y");

						if (cdata.size() == 0) {

							Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA,
									Toast.LENGTH_LONG).show();

						} else {
							Intent i = new Intent(getBaseContext(),
									UploadDataActivity.class);
							i.putExtra("UploadAll", false);
							startActivity(i);

					finish();

						}
						
					}
					
					
				
					/*
					intent = new Intent(getBaseContext(),
							UploadOptionActivity.class);
					startActivity(intent);

					MainMenuActivity.this.finish();*/

				}

			} else {
				Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_SHORT).show();
			}
		}else if(v.getId()==R.id.performance){
			Intent startPerformance = 	new Intent(MainActivity.this,Performance.class);
			startActivity(startPerformance);
			 overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
		}
		/*else if(v.getId()==R.id.teamattendence){
//			Intent startDownload = 	new Intent(MainActivity.this,NonWorkingActivity.class);
//			startActivity(startDownload);
		}*/
	}

	
	
	@Override
	public void onBackPressed() {
		/*Intent i = new Intent(this, MainActivity.class);
		startActivity(i);*/
		
		/*android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);*/
		
		//finish();
		

		//overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
	}
	
	public boolean checkNetIsAvailable(){
		ConnectivityManager cm =
		        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		 
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
		                      activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}
	
	/*public boolean CheckNetAvailability() {

		boolean connected = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState() == NetworkInfo.State.CONNECTED
				|| connectivityManager.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
			// we are connected to a network
			connected = true;
		}
		return connected;
	}*/
	

	
}
