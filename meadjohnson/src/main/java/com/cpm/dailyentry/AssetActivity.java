package com.cpm.dailyentry;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.cpm.Constants.CommonString;
import com.cpm.dailyentry.PromotionActivity.ExpandableListAdapter;
import com.cpm.dailyentry.PromotionActivity.ViewHolder;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.AssetInsertdataGetterSetter;
import com.cpm.xmlGetterSetter.OpeningStockInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.PromotionInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.StockGetterSetter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AbsListView.OnScrollListener;

public class AssetActivity extends AppCompatActivity implements OnClickListener{

	boolean checkflag=true;
	List<Integer> checkHeaderArray = new ArrayList<Integer>();

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	Button btnSave;

	List<AssetInsertdataGetterSetter> listDataHeader;
	HashMap<AssetInsertdataGetterSetter, List<AssetInsertdataGetterSetter>> listDataChild;

	ArrayList<AssetInsertdataGetterSetter> brandData;
	ArrayList<AssetInsertdataGetterSetter> skuData;

	AssetInsertdataGetterSetter insertData=new AssetInsertdataGetterSetter();

	GSKDatabase db;

	private SharedPreferences preferences;

	String store_cd,visit_date,username,intime;

	ImageView img;
	
	boolean ischangedflag=false;

	String _pathforcheck,_path,str;

	private String image1 = "";

	String img1 = "";
	static int grp_position = -1;
	static int child_position = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.asset_layout);

		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		btnSave=(Button) findViewById(R.id.save_btn);

		img=(ImageView) findViewById(R.id.imgnodata);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


		db=new GSKDatabase(getApplicationContext());
		db.open();

		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);

		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		username= preferences.getString(CommonString.KEY_USERNAME, null);
		intime=getCurrentTime();

		str = Environment.getExternalStorageDirectory() + "/Meadjohnson_Images/";
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		btnSave.setOnClickListener(this);

		expListView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}

			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				expListView.invalidateViews();
			}

		});

	}

	private void prepareListData() {
		listDataHeader = new ArrayList<AssetInsertdataGetterSetter>();
		listDataChild = new HashMap<AssetInsertdataGetterSetter, List<AssetInsertdataGetterSetter>>();

		/*brandData=db.getAssetUpload(store_cd);

		if(brandData.size()<0){

		}*/



		brandData=db.getAssetBrandData(store_cd);

		if(brandData.size()>0){

			// Adding child data

			for(int i=0;i<brandData.size();i++){
				listDataHeader.add(brandData.get(i));

				skuData=db.getAssetDataFromdatabase(store_cd, brandData.get(i).getBrand_cd());
				if(!(skuData.size()>0) || (skuData.get(0).getAsset()==null) || (skuData.get(0).getAsset().equals(""))){
					skuData=db.getAssetData(brandData.get(i).getBrand_cd(),store_cd);
				}
				else{
					btnSave.setText("Update");
				}

				List<AssetInsertdataGetterSetter> skulist = new ArrayList<AssetInsertdataGetterSetter>();
				for(int j=0;j<skuData.size();j++){
					skulist.add(skuData.get(j));
				}

				listDataChild.put(listDataHeader.get(i), skulist); // Header, Child data
			}

		}
		else{
			expListView.setVisibility(View.GONE);
			btnSave.setVisibility(View.INVISIBLE);
			img.setVisibility(View.VISIBLE);
		}




	}



	public class ExpandableListAdapter extends BaseExpandableListAdapter {

		private Context _context;
		private List<AssetInsertdataGetterSetter> _listDataHeader; // header titles
		// child data in format of header title, child title
		private HashMap<AssetInsertdataGetterSetter, List<AssetInsertdataGetterSetter>> _listDataChild;

		public ExpandableListAdapter(Context context, List<AssetInsertdataGetterSetter> listDataHeader,
				HashMap<AssetInsertdataGetterSetter, List<AssetInsertdataGetterSetter>> listChildData) {
			this._context = context;
			this._listDataHeader = listDataHeader;
			this._listDataChild = listChildData;

		}

		@Override
		public Object getChild(int groupPosition, int childPosititon) {
			return this._listDataChild.get(this._listDataHeader.get(groupPosition))
					.get(childPosititon);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@SuppressLint("NewApi")
		@Override
		public View getChildView(final int groupPosition, final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			final AssetInsertdataGetterSetter childText = (AssetInsertdataGetterSetter) getChild(groupPosition, childPosition);

			ViewHolder holder=null;

			if (convertView == null) {



				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.asset_entry, null);

				holder=new ViewHolder();

				holder.cardView=(CardView) convertView.findViewById(R.id.card_view);
				holder.etremark=(EditText) convertView.findViewById(R.id.etremarks);
				holder.tbpresent=(ToggleButton) convertView.findViewById(R.id.tbpresent);
				holder.btn_cam = (Button) convertView.findViewById(R.id.btncam);
				holder.cam_layout = (LinearLayout) convertView.findViewById(R.id.cam_layout);
				holder.remark_layout = (LinearLayout) convertView.findViewById(R.id.remark_layout);


				convertView.setTag(holder);

			}

			holder = (ViewHolder) convertView.getTag();

			holder.tbpresent.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					ischangedflag=true;
					String val = ((ToggleButton) v).getText().toString();
					_listDataChild.get(listDataHeader.get(groupPosition))
					.get(childPosition).setPresent(val);

					expListView.invalidateViews();
				}
			});

			holder.btn_cam.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					grp_position = groupPosition;
					child_position = childPosition;

					_pathforcheck = store_cd + "Asset"
							+ "Image" + visit_date.replace("/","") + getCurrentTime().replace(":","")+".jpg";

					_path = Environment.getExternalStorageDirectory() +  "/Meadjohnson_Images/"
							+ _pathforcheck;

					startCameraActivity();

				}
			});

			holder.etremark.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {



					if (!hasFocus) {
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString().trim();
						value1 = value1.replaceAll("[&^<>{}'$]", "");
						if (value1.equals("") || value1.substring(0).equals(".") || value1.substring(0).equals(",")) {

							_listDataChild
							.get(listDataHeader.get(groupPosition))
							.get(childPosition).setRemark("");

						} else {
							ischangedflag=true;
							_listDataChild
							.get(listDataHeader.get(groupPosition))
							.get(childPosition).setRemark(value1);

						}

					}

				}
			});



			holder.etremark.setText(childText.getRemark());

			holder.tbpresent.setChecked(_listDataChild
					.get(listDataHeader.get(groupPosition))
					.get(childPosition).getPresent().equals("YES"));

			if (!img1.equalsIgnoreCase("")) {
				if (childPosition == child_position && groupPosition == grp_position) {
					//childText.get(childPosition).setCamera("YES");
					_listDataChild
							.get(listDataHeader.get(groupPosition))
							.get(childPosition).setImg(img1);
					//childText.setImg(img1);
					img1 = "";

				}
			}

			if(_listDataChild
					.get(listDataHeader.get(groupPosition))
					.get(childPosition).getPresent().equals("YES")){
				//holder.etremark.setVisibility(View.INVISIBLE);

				holder.etremark.setVisibility(View.GONE);
				//holder.cam_layout.setVisibility(View.VISIBLE);

				/*if (_listDataChild
						.get(listDataHeader.get(groupPosition))
						.get(childPosition).getImg()!=null && !_listDataChild
						.get(listDataHeader.get(groupPosition))
						.get(childPosition).getImg().equals("")) {
					holder.btn_cam.setBackgroundResource(R.drawable.camera_done);
				} else {
					holder.btn_cam.setBackgroundResource(R.drawable.camera);
				}
*/
			}else if(_listDataChild
					.get(listDataHeader.get(groupPosition))
					.get(childPosition).getPresent().equals("NO")){
				holder.etremark.setVisibility(View.VISIBLE);

				/*holder.remark_layout.setVisibility(View.VISIBLE);
				holder.cam_layout.setVisibility(View.GONE);*/

				holder.etremark.setText(childText.getRemark());
			}

			TextView txtListChild = (TextView) convertView
					.findViewById(R.id.lblListItem);

			txtListChild.setText(childText.getAsset());

			if(!checkflag){
				boolean tempflag=false;
				if(_listDataChild
						.get(listDataHeader.get(groupPosition))
						.get(childPosition).getPresent().equals("NO")){
					if(holder.etremark.getText().toString().equals("")){
						//holder.etmidstock.setBackgroundColor(getResources().getColor(R.color.red));
						holder.etremark.setHintTextColor(getResources().getColor(R.color.green));
						holder.etremark.setHint("Empty");
						tempflag=true;
					}
				}
				else{
					if(_listDataChild
							.get(listDataHeader.get(groupPosition))
							.get(childPosition).getImg().equals("")){
						holder.btn_cam.setBackgroundResource(R.drawable.camera_not_done);

					}
					else{
						holder.btn_cam.setBackgroundResource(R.drawable.camera_done);
					}
				}


				if(tempflag){

					holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
				}
				else{

					holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
				}

			}

			//holder.tvpromo.setText(childText.getAsset());
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return this._listDataChild.get(this._listDataHeader.get(groupPosition))
					.size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return this._listDataHeader.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return this._listDataHeader.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			AssetInsertdataGetterSetter headerTitle = (AssetInsertdataGetterSetter) getGroup(groupPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_group, null);
			}

			TextView lblListHeader = (TextView) convertView
					.findViewById(R.id.lblListHeader);
			lblListHeader.setTypeface(null, Typeface.BOLD);
			lblListHeader.setText(headerTitle.getBrand());

			if(!checkflag){
				if(checkHeaderArray.contains(groupPosition)){
					lblListHeader.setBackgroundColor(getResources().getColor(R.color.red));
				}
				else{
					lblListHeader.setBackgroundColor(getResources().getColor(R.color.teal_dark));
				}
			}

			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

	}


	public class ViewHolder{

		EditText etremark;
		ToggleButton tbpresent;
		CardView cardView;
		LinearLayout cam_layout,remark_layout;
		Button btn_cam;
		//TextView tvpromo;
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();

		if(id==R.id.save_btn){

			expListView.clearFocus();

			if(validateData(listDataChild, listDataHeader)){

				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Are you sure you want to save")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {

						db.open();

						getMid();

						db.deleteAssetData(store_cd);
						db.InsertAssetData(
								store_cd, listDataChild,
								listDataHeader);

						Toast.makeText(getApplicationContext(),
								"Data has been saved", Toast.LENGTH_SHORT).show();

						/*Intent DailyEntryMenu = new Intent(
								AssetActivity.this,
								StoreEntry.class);
						startActivity(DailyEntryMenu);*/

						finish();
					}
				})
				.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {
						dialog.cancel();
					}
				});
				AlertDialog alert = builder.create();

				alert.show();

			}
			else{
				listAdapter.notifyDataSetChanged();
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
			cdata.setStatus(CommonString.KEY_CHECK_IN);
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

	boolean validateData(
			HashMap<AssetInsertdataGetterSetter, List<AssetInsertdataGetterSetter>> listDataChild2,
			List<AssetInsertdataGetterSetter> listDataHeader2) {
		//boolean flag = true;
		checkHeaderArray.clear();

		for (int i = 0; i < listDataHeader2.size(); i++) {
			for (int j = 0; j < listDataChild2.get(listDataHeader2.get(i))
					.size(); j++) {
				/*String aspermccain = listDataChild2.get(listDataHeader2.get(i)).get(j).getAs_per_meccain();*/
				String present= listDataChild2.get(listDataHeader2.get(i)).get(j).getPresent();
				String remark = listDataChild2.get(listDataHeader2.get(i)).get(j).getRemark();
				String img = listDataChild2.get(listDataHeader2.get(i)).get(j).getImg();

				if(present.equalsIgnoreCase("NO")){
					if (remark.equalsIgnoreCase("")) {

						if(!checkHeaderArray.contains(i)){
							checkHeaderArray.add(i);
						}

						checkflag=false;

						//flag = false;
						break;

					} else{
						checkflag=true;
						//flag = true;
					}
				}
				else if(present.equalsIgnoreCase("YES")){
					/*if(img.equals("")){
						if(!checkHeaderArray.contains(i)){
							checkHeaderArray.add(i);
						}

						checkflag=false;

						//flag = false;
						break;

					} else{

						checkflag=true;
						//flag = true;
					}*/
					checkflag=true;
				}


			}

			if(checkflag == false){
				break;
			}

		}

		//expListView.invalidate();

		return checkflag;

	}

	@Override
	public void onBackPressed() {
		/*Intent i = new Intent(this, StoreEntry.class);
		startActivity(i);*/
		
		if(ischangedflag){
			AlertDialog.Builder builder = new AlertDialog.Builder(
					AssetActivity.this);
			builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
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
		else{
			finish();

			overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
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


	protected void startCameraActivity() {

		try {
			Log.i("MakeMachine", "startCameraActivity()");
			File file = new File(_path);
			Uri outputFileUri = Uri.fromFile(file);

			Intent intent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

			startActivityForResult(intent, 0);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Log.i("MakeMachine", "resultCode: " + resultCode);
		switch (resultCode) {
			case 0:
				Log.i("MakeMachine", "User cancelled");
				break;

			case -1:

				if (_pathforcheck != null && !_pathforcheck.equals("")) {
					if (new File(str + _pathforcheck).exists()) {

						//cam.setBackgroundResource(R.drawable.camera_list_tick);
						image1 = _pathforcheck;
						img1 = _pathforcheck;
						expListView.invalidateViews();
						_pathforcheck = "";
						//Toast.makeText(getApplicationContext(), ""+image1, Toast.LENGTH_LONG).show();

					}
				}

				break;
		}


		super.onActivityResult(requestCode, resultCode, data);
	}

}
