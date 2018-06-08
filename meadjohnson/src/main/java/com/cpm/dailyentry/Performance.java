package com.cpm.dailyentry;

import java.util.ArrayList;

import com.cpm.Constants.CommonString;
import com.cpm.database.GSKDatabase;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.PerformanceGetterSetter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Performance extends Activity{
	
	TextView tvtarget,tvachievement,tvsales;
	Button btnok;
	
	GSKDatabase db;
	
	String store_cd;
	
	ArrayList<PerformanceGetterSetter> list=new ArrayList<>();
	
	private SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.performance_layout);
		
		tvtarget=(TextView) findViewById(R.id.tvtarget);
		tvsales=(TextView) findViewById(R.id.tvmtdsales);
		tvachievement=(TextView) findViewById(R.id.tvachievement);
		btnok=(Button) findViewById(R.id.btnok);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);
	
		db=new GSKDatabase(getApplicationContext());
		
		db.open();
		
		list=db.getPerformrmance(store_cd);
		
		tvtarget.setText(list.get(0).getMonthly_target().get(0));
		tvsales.setText(list.get(0).getMtd_sales().get(0));
		tvachievement.setText(list.get(0).getAchievement().get(0));
		
		btnok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finish();
				
				overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
				
			}
		});
		
	}

}
