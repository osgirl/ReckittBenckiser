package com.cpm.dailyentry;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.cpm.capitalfoods.R;

@SuppressWarnings("deprecation")
public class DeepFreezerActivity extends TabActivity{

	TabHost tabHost;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.deepfreezer_tab_layout);

		Resources ressources = getResources(); 
		tabHost = getTabHost(); 

		// Android tab
		Intent intentattendance = new Intent().setClass(this, MccainType.class);
		TabSpec tabAttendance = tabHost
				.newTabSpec("Android")
				.setIndicator("Mccain", ressources.getDrawable(R.drawable.tab_background))
				.setContent(intentattendance);

		// Android tab
		Intent intentattendanceMtd = new Intent().setClass(this, StoreType.class);
		TabSpec tabAttendanceMtd = tabHost
				.newTabSpec("Android")
				.setIndicator("Store", ressources.getDrawable(R.drawable.tab_background))
				.setContent(intentattendanceMtd);



		// add all tabs 
		tabHost.addTab(tabAttendance);
		tabHost.addTab(tabAttendanceMtd);

		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);

		setTabColor(tabHost);
		setTabTextsize(tabHost);

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				setTabColor(tabHost);
				//Toast.makeText(getApplicationContext(), "Tab clicked", Toast.LENGTH_SHORT).show();
			}
		});


	}




	public void setTabColor(TabHost tabhost) {
		for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {

			tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#9E9E9E")); //unselected
		}
		tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#F44336")); // selected
	}

	public void setTabTextsize(TabHost tabhost){
		for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
			final TextView tv = (TextView)tabhost.getTabWidget().getChildTabViewAt(i).findViewById(android.R.id.title);
			tabhost.getTabWidget().getChildTabViewAt(i).getLayoutParams().height *=0.66;
			tv.setGravity(Gravity.TOP);
			tv.setTextSize(18);
		}
	}

	@Override
	public void onBackPressed() {
		/*Intent i = new Intent(this, StoreEntry.class);
		startActivity(i);*/
		
		AlertDialog.Builder builder = new AlertDialog.Builder(
				DeepFreezerActivity.this);
		builder.setMessage("Want to go back, all unsaved data will be lost")
		.setCancelable(false)
		.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
			public void onClick(
					DialogInterface dialog, int id) {
			
				finish();
				
				overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
			}
		})
		.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(
					DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	

}
