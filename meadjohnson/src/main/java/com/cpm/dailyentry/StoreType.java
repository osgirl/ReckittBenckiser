package com.cpm.dailyentry;

import java.util.ArrayList;
import java.util.Calendar;


import com.cpm.Constants.CommonString;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.DeepFreezerTypeGetterSetter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class StoreType extends Activity implements OnItemSelectedListener, OnClickListener{

	/*TextView tv;
	Spinner spin;*/
	private ArrayAdapter<CharSequence> dfAdapter;

	ArrayList<DeepFreezerTypeGetterSetter> deepFreezlist=new ArrayList<DeepFreezerTypeGetterSetter>();
	ArrayList<String> dflist=new ArrayList<String>();
	ArrayList<String> statuslist=new ArrayList<String>();

	GSKDatabase db;
	ListView lv;
	//EditText etremarkone;
	Button btnsave;
	//TextView tvdfType;
	//LinearLayout lv_layout;

	int listsize=0;

	String store_cd,visit_date,username,intime;

	private SharedPreferences preferences;
	
	boolean storeflag=false;
	
	String statusstr="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.deepfreezer_type_layout);

		//spin=(Spinner) findViewById(R.id.spin);

		lv=(ListView) findViewById(R.id.dfavallist);

		//etremarkone=(EditText) findViewById(R.id.etremarkone);

		btnsave=(Button) findViewById(R.id.btnsavedeepfz);

		//tvdfType=(TextView) findViewById(R.id.tvdftype);

		//tvdfType.setText("Store DF Available");

		//lv_layout=(LinearLayout) findViewById(R.id.lv_layout);

		dfAdapter= new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);



		statuslist.add("Status");
		statuslist.add("Yes");
		statuslist.add("No");

		for(int i=0;i<statuslist.size();i++){
			dfAdapter.add(statuslist.get(i));
		}


		/*spin.setAdapter(dfAdapter);
		spin.setOnItemSelectedListener(this);*/

		db=new GSKDatabase(getApplicationContext());
		db.open();

		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);

		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		username= preferences.getString(CommonString.KEY_USERNAME, null);
		intime=getCurrentTime();

		setDataToListView();


		lv.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}

			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				lv.invalidateViews();
			}

		});
		
		btnsave.setOnClickListener(this);

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		lv.invalidateViews();
	}


	public void setDataToListView(){

		try{
			deepFreezlist = db.getDFTypeData("Store",store_cd);

			if(deepFreezlist.size()<=0){
				deepFreezlist = db.getDFMasterData("Store");
			}
			else{
				
				statusstr=deepFreezlist.get(0).getStatus();
				
				storeflag=true;
				btnsave.setText("Update");
			}
		}
		catch(Exception e){
			Log.d("Exception when fetching Deepfreezer!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
		}

		if(deepFreezlist.size()>0){
			/*etremarkone.setText(deepFreezlist.get(0).getRemark());
			spin.setSelection(statuslist.indexOf(deepFreezlist.get(0).getStatus()));*/
			listsize=deepFreezlist.size();
			lv.setAdapter(new MyAdaptor());
		}

	}



	private class MyAdaptor extends BaseAdapter{

		@Override
		public int getCount() {

			return listsize;
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			String status;

			//spinflag=false;
			
			//final String status="";
			
			if (convertView == null) {
				holder = new ViewHolder();

				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.df_list_item_store, null);

				holder.dfavail= (TextView) convertView
						.findViewById(R.id.tvdfav);
				//holder.spinstat=(Spinner) convertView.findViewById(R.id.spinstatus);
				holder.tbpresent=(ToggleButton) convertView.findViewById(R.id.tbpresent);
				holder.etremark=(EditText) convertView.findViewById(R.id.etremark);
				
				holder.stauslay=(LinearLayout) convertView.findViewById(R.id.status_layout);
				
				if(position==0 && deepFreezlist.get(position).getStatus().equals("") || position==0 && deepFreezlist.get(position).getStatus().equals("NO") ){
					listsize=1;
					notifyDataSetChanged();
				}

				
				/*holder.spinstat.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View v,
							int pos, long arg3) {
						// TODO Auto-generated method stub

						if(pos!=0){
							status=statuslist.get(pos);

							final int position = arg0.getId();

							
							if(position==0 && status.equals("No")){
								listsize=1;
								 //notifyDataSetChanged();
								//lv.invalidateViews();
								
								
								SharedPreferences.Editor editor = preferences.edit();
								editor.putBoolean("opnestkmccaindf", false);

								editor.commit();
							}
							else if(position==0 && status.equals("Yes")){
								listsize=deepFreezlist.size();
								
								SharedPreferences.Editor editor = preferences.edit();
								editor.putBoolean("opnestkmccaindf", true);

								editor.commit();
							}
							

							if(status.equals("No")){
								//lv.setClickable(false);
								spinflag=false;
								deepFreezlist.get(position).setStatus("No");
								
								//lv.getChildAt(position).findViewById(R.id.etremark).setVisibility(View.VISIBLE);
								
							}
							else{
								deepFreezlist.get(position).setStatus("Yes");
								spinflag=true;
								
								//lv.getChildAt(position).findViewById(R.id.etremark).setVisibility(View.INVISIBLE);
							}

							
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});*/
				
				holder.tbpresent.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						
						
						String val = ((ToggleButton) v).getText().toString();
						
					/*	status=statuslist.get(pos);

						final int position = arg0.getId();
*/
						final int position = v.getId();
						if(position==0 && val.equals("NO")){
							listsize=1;
							 //notifyDataSetChanged();
							//lv.invalidateViews();
							
							
							SharedPreferences.Editor editor = preferences.edit();
							editor.putBoolean("opnestkmccaindf", false);

							editor.commit();
						}
						else if(position==0 && val.equals("YES")){
							listsize=deepFreezlist.size();
							
							SharedPreferences.Editor editor = preferences.edit();
							editor.putBoolean("opnestkmccaindf", true);

							editor.commit();
						}
						
						
						
						deepFreezlist.get(position).setStatus(val);
						
						lv.invalidateViews();
					}
				});
				
			
			convertView.setTag(holder);


			} else {
				holder = (ViewHolder) convertView.getTag();
			}
				
			//holder.spinstat.setAdapter(dfAdapter);

			holder.dfavail.setId(position);
			holder.etremark.setId(position);
			holder.tbpresent.setId(position);
			//holder.spinstat.setId(position);
			
			if(listsize==1){
				
				holder.etremark.addTextChangedListener(new TextWatcher(){
					 
				    @Override
				    public void afterTextChanged(Editable arg0) {
				        // TODO Auto-generated method stub
				 
				    }
				 
				    @Override
				    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				            int arg3) {
				        // TODO Auto-generated method stub
				 
				    }
				 
				    @Override
				    public void onTextChanged(CharSequence s, int a, int b, int c) {
				        // TODO Auto-generated method stub
				 
				    	deepFreezlist.get(position).setRemark(s.toString());
				    }});
				
			}
			else{
				
				holder.etremark
				.setOnFocusChangeListener(new OnFocusChangeListener() {
					public void onFocusChange(View v, boolean hasFocus) {

						if (!hasFocus) {
							final int position = v.getId();
							final EditText Caption = (EditText) v;
							String value1 = Caption.getText().toString();

							if (value1.equals("")) {

								deepFreezlist.get(position).setRemark("");

							} else {

								deepFreezlist.get(position).setRemark(value1);

							}

						}
					}
				});
			}

			if(deepFreezlist.get(position).getDeep_freezer().contains("Deep Freezer cooling")){
				holder.stauslay.setVisibility(View.INVISIBLE);
			}
			else{
				holder.stauslay.setVisibility(View.VISIBLE);
			}
			
			/*if(deepFreezlist.get(position).getDeep_freezer().contains("Material Wellness")){
				holder.etremark.setVisibility(View.INVISIBLE);
			}
			else{
				holder.etremark.setVisibility(View.VISIBLE);
			}*/
			
			holder.dfavail.setText(deepFreezlist.get(position).getDeep_freezer());
			holder.etremark.setText(deepFreezlist.get(position).getRemark());
			status=deepFreezlist.get(position).getStatus();
			if(status.equals("NO")){
				holder.etremark.setVisibility(View.VISIBLE);
				//dfAdapter.notifyDataSetChanged();
				// notifyDataSetChanged();
				lv.invalidateViews();
			}
			else if(status.equals("YES")){
				
				holder.etremark.setVisibility(View.INVISIBLE);
				//dfAdapter.notifyDataSetChanged();
				// notifyDataSetChanged();
				//lv.invalidateViews();
				/*if(position!=0){
					
					//dfAdapter.notifyDataSetChanged();
					lv.invalidateViews();
				}*/
				lv.invalidateViews();
				// notifyDataSetChanged();
			}/*else{
				holder.etremark.setVisibility(View.VISIBLE);
				//dfAdapter.notifyDataSetChanged();
				//lv.invalidateViews();
				 notifyDataSetChanged();
			}*/
			
			/*if(spinflag){
				if(position!=0){
					holder.etremark.setVisibility(View.INVISIBLE);
					dfAdapter.notifyDataSetChanged();
				}
			}
			else{
				holder.etremark.setVisibility(View.VISIBLE);
				dfAdapter.notifyDataSetChanged();
			}*/
		//	int indexspin=statuslist.indexOf(status);
			holder.tbpresent.setChecked(deepFreezlist.get(position).getStatus().equals("YES"));
			
			
			//holder.spinstat.setSelection(indexspin);
			
			return convertView;
		}

	}


	private class ViewHolder {
		TextView dfavail;
		//Spinner spinstat;
		
		ToggleButton tbpresent;
		EditText etremark;
		LinearLayout stauslay;

	}



	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.spin:

			if(position!=0){
				String status=statuslist.get(position);
				if(status.equals("No")){
					//lv.setClickable(false);
					lv.setVisibility(View.INVISIBLE);
					deepFreezlist.get(0).setStatus("No");
				}
				else{
					lv.setVisibility(View.VISIBLE);
					deepFreezlist.get(0).setStatus("Yes");
				}
			}


			break;

		default:
			break;
		}
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnsavedeepfz){

			if(validateData(deepFreezlist)){
				
				if(storeflag && db.isOpeningDataFilled(store_cd) && !statusstr.equals(deepFreezlist.get(0).getStatus())){
					
					AlertDialog.Builder builder = new AlertDialog.Builder(StoreType.this);
					builder.setTitle("Want To Update Data");
					builder.setMessage("If you update, Stock data will be deleted and needed fresh entries")
					.setCancelable(false)
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

							dialog.cancel();
							
							getMid();
							
							db.deleteStockData();
							db.deleteStockHeaderData();
							
							db.insertDeepFreezerTypeData(deepFreezlist,"Store",store_cd);
							
							finish();
							
							overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
							
						}
					})

					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
							/*Intent i = new Intent(getApplicationContext(),
													MainActivity.class);
											startActivity(i);

											finish();*/
						}
					});

					AlertDialog alert = builder.create();
					alert.show();
					
				}
				else{
					
					AlertDialog.Builder builder = new AlertDialog.Builder(StoreType.this);
					builder.setTitle("Parinaam");
					builder.setMessage("Want To Save Data")
					.setCancelable(false)
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

							dialog.cancel();
							
							getMid();
							
							db.insertDeepFreezerTypeData(deepFreezlist,"Store",store_cd);
							
							finish();
							
							overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
							
						}
					})

					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
							/*Intent i = new Intent(getApplicationContext(),
													MainActivity.class);
											startActivity(i);

											finish();*/
						}
					});

					AlertDialog alert = builder.create();
					alert.show();
					
				}
				

			}
			else{
				Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
			}
			
		}
	}

	public long checkMid() {
		return db.CheckMid(visit_date, store_cd);
	}

	public long getMid() {

		long mid = 0;

		mid = checkMid();

		if (mid == 0) {
			CoverageBean cdata = new CoverageBean();
			cdata.setStoreId(store_cd);
			cdata.setVisitDate(visit_date);
			cdata.setUserId(username);
			cdata.setInTime(intime);
			cdata.setOutTime(getCurrentTime());
			cdata.setReason("");
			cdata.setReasonid("0");
			cdata.setLatitude("0.0");
			cdata.setLongitude("0.0");
			mid = db.InsertCoverageData(cdata);

		}

		return mid;
	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}
	
	boolean validateData(ArrayList<DeepFreezerTypeGetterSetter> deeplist) {
		boolean flag = true;
		
			for (int j = 0; j < deeplist.size(); j++) {
				/*String aspermccain = listDataChild2.get(listDataHeader2.get(i)).get(j).getAs_per_meccain();*/
				//String openstockcoldrm = deeplist.get(j).getRemark();
				
				/*if(deeplist.get(j).getStatus().equals("Status")){
					flag = false;
					break;
				}else if(deeplist.get(j).getStatus().equals("No")){
					String openstockcoldrm = deeplist.get(j).getRemark();
					if ( openstockcoldrm.equalsIgnoreCase("")) {
						flag = false;
						break;
						
					}
					
					
				}*/
				
				/*String status=deeplist.get(j).getStatus();
				if(!status.equals("No") && !status.equals("Yes")){
					flag = false;
					break;
				}else if(deeplist.get(0).getStatus().equals("No")){
					flag = true;
					break;
				}else if(deeplist.get(j).getStatus().equals("No")){
					String openstockcoldrm = deeplist.get(j).getRemark();
					if ( openstockcoldrm.equalsIgnoreCase("")) {
						flag = false;
						break;
					}
				}*/
				
				
				String status=deeplist.get(j).getStatus();
				if(!status.equals("NO") && !status.equals("YES")){
					flag = false;
					break;
				}else if(deeplist.get(j).getStatus().equals("NO")){
					if(j==0){
						String openstockcoldrm = deeplist.get(j).getRemark();
						if ( openstockcoldrm.equalsIgnoreCase("")) {
							flag = false;
							break;
						}
						else{
							
							for(int i=1;i<deeplist.size();i++){
								
								deeplist.get(i).setStatus("NO");
								deeplist.get(i).setRemark("");
							}
							
							flag = true;
							break;
						}
					}
					else{
						String openstockcoldrm = deeplist.get(j).getRemark();
						if ( openstockcoldrm.equalsIgnoreCase("")) {
							flag = false;
							break;
						}
					}
					
				}else if(deeplist.get(j).getStatus().equals("YES")){
					if(j==0){
						deeplist.get(j).setRemark("");
					}
				}
				
				
				
				/*if(deeplist.get(j).getStatus().equals("No")){
					
					if(deeplist.get(0).getStatus().equals("Yes")){
						String openstockcoldrm = deeplist.get(j).getRemark();
						if ( openstockcoldrm.equalsIgnoreCase("")) {
							flag = false;
							break;
						}
					}
					
				}*/
				
				/*if ( openstockcoldrm.equalsIgnoreCase("")) {
					flag = false;
					break;
					
				} else{
					flag = true;
					if(deeplist.get(0).getStatus().equals("No")){
						break;
					}
			}*/
			}
			
		return flag;
		
	}
	
	@Override
	public void onBackPressed() {
		/*Intent i = new Intent(this, StoreEntry.class);
		startActivity(i);*/
		finish();

		overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
	}
	
}
