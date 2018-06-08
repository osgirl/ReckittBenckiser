package com.cpm.upload;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.GetterSetter.StoreBean;
import com.cpm.dailyentry.DailyEntryScreen;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;

import com.cpm.message.AlertMessage;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;

public class UploadOptionActivity extends AppCompatActivity implements View.OnClickListener{

	private Intent intent;
	private String date;
	private SharedPreferences preferences;
	private static GSKDatabase database;
	ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();

	JourneyPlanGetterSetter storestatus = new JourneyPlanGetterSetter();
	Button btn_upload_data,btn_upload_image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_option);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		btn_upload_data = (Button) findViewById(R.id.btn_upload_data);
		btn_upload_image = (Button) findViewById(R.id.btn_upload_image);

		btn_upload_data.setOnClickListener(this);
		btn_upload_image.setOnClickListener(this);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		date = preferences.getString(CommonString.KEY_DATE, null);

		database = new GSKDatabase(this);
		database.open();

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	}

/*	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.upload_data:

			//cdata = database.getCoverageData(date);

			if (cdata.size() == 0) {

				Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA,
						Toast.LENGTH_LONG).show();

			} else {

				if ((validate_data())) {

					Intent i = new Intent(UploadOptionActivity.this,
							UploadDataActivity.class);
					startActivity(i);
					UploadOptionActivity.this.finish();
				}

				else {
					Toast.makeText(getBaseContext(),
							AlertMessage.MESSAGE_NO_DATA, Toast.LENGTH_LONG)
							.show();
				}

			}

			break;
		case R.id.upload_image:

//			cdata = database.getCoverageData(date, null);
//
//			if (cdata.size() == 0) {
//
//				Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_IMAGE,
//						Toast.LENGTH_LONG).show();
//
//			}
//
//			else {
//
//				if (validate()) {
//
//					intent = new Intent(this, UploadImageActivity.class);
//					startActivity(intent);
//					finish();
//				} else {
//					Toast.makeText(getBaseContext(),
//							AlertMessage.MESSAGE_DATA_FIRST, Toast.LENGTH_LONG)
//							.show();
//				}
//			}
//			break;
			
			
			
		//	database.getCoverageData(visitdate)

		}
	}*/


	public boolean validate_data() {
		boolean result = false;

		database.open();
		cdata = database.getCoverageData(date);

		for (int i = 0; i < cdata.size(); i++) {

			storestatus = database.getStoreStatus(cdata.get(i).getStoreId());

			if (!storestatus.getUploadStatus().get(0).equalsIgnoreCase(CommonString.KEY_D)) {
				if ((storestatus.getCheckOutStatus().get(0).equalsIgnoreCase(
						CommonString.KEY_C)
						|| storestatus.getUploadStatus().get(0).equalsIgnoreCase(
						CommonString.KEY_P) || storestatus.getUploadStatus().get(0)
						.equalsIgnoreCase(CommonString.STORE_STATUS_LEAVE))) {
					result = true;
					break;

				}
			}
		}

		return result;
	}


	public boolean validate() {
		boolean result = false;

	database.open();
		cdata = database.getCoverageData(date);

		for (int i = 0; i < cdata.size(); i++) {

			if (cdata.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_D)) {
				result = true;
				break;

			}
		}

		return result;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, DailyEntryScreen.class);
		startActivity(i);
		UploadOptionActivity.this.finish();
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();

		if(id == R.id.btn_upload_data){

			cdata = database.getCoverageData(date);

			if (cdata.size() == 0) {

				Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA,
						Toast.LENGTH_LONG).show();

			} else {


				if ((validate_data())) {

					Intent i = new Intent(getBaseContext(),
							UploadDataActivity.class);
					i.putExtra("UploadAll", false);
					startActivity(i);

					finish();

				}
				else {
					Toast.makeText(getBaseContext(),
							AlertMessage.MESSAGE_NO_DATA, Toast.LENGTH_LONG)
							.show();
				}

			}

		}else if(id == R.id.btn_upload_image){


			cdata = database.getCoverageData(date);

			if (cdata.size() == 0) {

				Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_IMAGE,
						Toast.LENGTH_LONG).show();

			}

			else {

			if (validate()) {


				Intent i = new Intent(getBaseContext(),
						UploadAllImageActivity.class);
				//i.putExtra("UploadAll", false);
				startActivity(i);

				finish();

			} else {
					Toast.makeText(getBaseContext(),
							AlertMessage.MESSAGE_DATA_FIRST, Toast.LENGTH_LONG)
							.show();
				}
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.empty_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if(id==android.R.id.home){

			// NavUtils.navigateUpFromSameTask(this);
			finish();

			overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

		}

		return super.onOptionsItemSelected(item);
	}
}
