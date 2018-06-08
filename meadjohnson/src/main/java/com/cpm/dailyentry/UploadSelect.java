package com.cpm.dailyentry;

import com.cpm.capitalfoods.R;
import com.cpm.upload.UploadAllImageActivity;
import com.cpm.upload.UploadDataActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UploadSelect extends Activity implements OnClickListener{
	
	Button btndata,btnimg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.upload_select);
		
		btndata=(Button) findViewById(R.id.btnupdata);
		btnimg=(Button) findViewById(R.id.btnupimg);
		
		btndata.setOnClickListener(this);
		btnimg.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		int id = v.getId();
		
		if(id==R.id.btnupdata){
			
			Intent in=new Intent(getApplicationContext(),UploadDataActivity.class);
			in.putExtra("UploadAll", false);
			startActivity(in);
			
		}else if(id==R.id.btnupimg){
			
			Intent in=new Intent(getApplicationContext(),UploadAllImageActivity.class);
			startActivity(in);
			
		}
		
	}
	
	

}
