package com.cpm.dailyentry;

import java.util.ArrayList;

import com.cpm.Constants.CommonString;

import com.cpm.database.GSKDatabase;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.CallsGetterSetter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CallsActivity extends AppCompatActivity implements OnFocusChangeListener{
	
	int listsize=0;
	
	EditText et_total_calls, et_productive_calls;
	Button btnsave;
	
	//ListView lv;
	
	CallsGetterSetter callsGetterSetter;
	
	
	String totalcalls,productivecalls;
	
	GSKDatabase db;
	
	private SharedPreferences preferences;
	String store_cd;
	
	ArrayList<CallsGetterSetter> listcalls=new ArrayList<>();
	
	MyAdaptor myAdaptor;
	
	static int currentapiVersion = 1;
	
	CustomKeyboardView mKeyboardView;
	
	Keyboard mKeyboard;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.calls_layout);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		et_total_calls=(EditText) findViewById(R.id.et_totalcalls);
		et_productive_calls=(EditText) findViewById(R.id.et_productivecalls);
		
		btnsave=(Button) findViewById(R.id.btnsave);
		
		currentapiVersion = android.os.Build.VERSION.SDK_INT;
		
		mKeyboard = new Keyboard(this, R.xml.keyboard);

		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView
		.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
				this));
		
		if (currentapiVersion >= 11) {
			
			et_total_calls.setTextIsSelectable(true);
			et_productive_calls.setTextIsSelectable(true);
			
			et_total_calls.setRawInputType(InputType.TYPE_CLASS_TEXT);
			et_productive_calls.setRawInputType(InputType.TYPE_CLASS_TEXT);
		} else {
			
			et_total_calls.setInputType(0);
			et_productive_calls.setInputType(0);

		}
		
		
		et_total_calls.setOnFocusChangeListener(this);
		et_productive_calls.setOnFocusChangeListener(this);
		
		//lv=(ListView) findViewById(R.id.lvcalls);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);
		
		myAdaptor=new MyAdaptor();
		
		db=new GSKDatabase(getApplicationContext());
		
		db.open();
		
		listcalls=db.getCallsData(store_cd);
		
		if(listcalls.size()>0){
			et_total_calls.setText(listcalls.get(0).getTotal_calls());
			et_productive_calls.setText(listcalls.get(0).getProductive_calls());
			btnsave.setText("Update");
		}
		
		/*if(listcalls.size()>0){
			listsize=listcalls.size();
			
			lv.setAdapter(myAdaptor);
		}*/
		
		btnsave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				try{
					
					totalcalls=et_total_calls.getText().toString();
					productivecalls=et_productive_calls.getText().toString();
					
					if(totalcalls.equals("") || productivecalls.equals("")){
						Toast.makeText(getApplicationContext(), CommonString.PLS_FILL_DATA, Toast.LENGTH_SHORT).show();
					}
					else if(Integer.parseInt(totalcalls)<Integer.parseInt(productivecalls)){
						Toast.makeText(getApplicationContext(), CommonString.CALLS_INVALID_DATA, Toast.LENGTH_SHORT).show();
					
						if(mKeyboardView.getVisibility() == View.INVISIBLE){
							mKeyboardView.setVisibility(View.VISIBLE);
						}
					
					}else{
					/*	et_total_calls.setText("");
						et_productive_calls.setText("");*/
						
						
						
						AlertDialog.Builder builder = new AlertDialog.Builder(CallsActivity.this);
						builder.setMessage("Are you sure you want to save")
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								
								db.insertCallsData(store_cd, totalcalls, productivecalls);
								Toast.makeText(getApplicationContext(),
										"Data has been saved", Toast.LENGTH_SHORT).show();
							finish();
								
							}
						})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								
								if(mKeyboardView.getVisibility() == View.INVISIBLE){
									mKeyboardView.setVisibility(View.VISIBLE);
								}
								
								dialog.cancel();
							}
						});
						AlertDialog alert = builder.create();

						alert.show();
						
					
						
						/*listcalls=db.getCallsData(store_cd);
						
						if(listcalls.size()>0){
							listsize=listcalls.size();
							
							lv.setAdapter(myAdaptor);
						}*/
						
					}
					
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
				
		
				
			}
		});
		
	}
	
	
	private class MyAdaptor extends BaseAdapter{

		@Override
		public int getCount() {

			return listcalls.size();
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@SuppressLint("NewApi")
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;

			if (convertView == null) {
				holder = new ViewHolder();
				
			

				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.calls_item, null);

				
				
				holder.totalcall= (TextView) convertView
						.findViewById(R.id.tvtotalcalls);
				holder.productivecall=(TextView) convertView.findViewById(R.id.tvproductivecalls);
				holder.imgdel=(ImageView) convertView.findViewById(R.id.imgdelete);
				
				holder.imgdel.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int key_id=Integer.parseInt(listcalls.get(position).getKey_id());
						db.deleteStockRow(key_id, store_cd);
						
						listcalls.remove(position);
						notifyDataSetChanged();
					}
				});

				convertView.setTag(holder);


			} else {
				holder = (ViewHolder) convertView.getTag();
				
			}
			

			holder.totalcall.setId(position);
			holder.productivecall.setId(position);
			holder.imgdel.setId(position);

			holder.totalcall.setText(listcalls.get(position).getTotal_calls());
			holder.productivecall.setText(listcalls.get(position).getProductive_calls());
			
			return convertView;
		}

	}


	private class ViewHolder {
		TextView totalcall;
		TextView productivecall;
		ImageView imgdel;
	}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if(mKeyboardView.getVisibility() == View.VISIBLE){
			mKeyboardView.setVisibility(View.INVISIBLE);
		}
		else{
			finish();
			
			overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
		}
			
	}
	
	/***
	 * Display the screen keyboard with an animated slide from bottom
	 */
	private void showKeyboardWithAnimation() {
		if (mKeyboardView.getVisibility() == View.GONE) {
			Animation animation = AnimationUtils
					.loadAnimation(CallsActivity.this,
							R.anim.slide_in_bottom);
			mKeyboardView.showWithAnimation(animation);
		}
		else if(mKeyboardView.getVisibility() == View.INVISIBLE){
			mKeyboardView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onFocusChange(View view, boolean hasFocus) {
		// TODO Auto-generated method stub
		
		int id = view.getId();
		
		if(id==R.id.et_totalcalls){
			if(hasFocus){
				showKeyboardWithAnimation();
			}
			else{
				hide();
			}
		}
		
		if(id==R.id.et_productivecalls){
			if(hasFocus){
				showKeyboardWithAnimation();
			}
			else{
				hide();
			}
		}
			
		
	}

	public void hide() {
		mKeyboardView.setVisibility(View.INVISIBLE);
		/*	// mKeyboardView.clearFocus();
		mKeyboardView.requestFocusFromTouch();*/

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
