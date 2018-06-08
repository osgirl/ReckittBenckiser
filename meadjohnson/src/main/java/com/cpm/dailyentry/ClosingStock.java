package com.cpm.dailyentry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.cpm.Constants.CommonString;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.ClosingStockInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.ColdroomClosingGetterSetter;
import com.cpm.xmlGetterSetter.DeepFreezerTypeGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;

public class ClosingStock extends AppCompatActivity implements OnClickListener{

	boolean checkflag=true;
	boolean validate=true;
	boolean flagcoldroom=false;
	boolean flagmccain=false;
	boolean flagstoredf=false;
	int valHeadCount;
	int valChildCount;
	
	List<Integer> checkHeaderArray = new ArrayList<Integer>();
	List<Integer> checkValidHeaderArray = new ArrayList<Integer>();
	List<Integer> checkValidChildArray = new ArrayList<Integer>();
	
	static int currentapiVersion = 1;
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	Button btnSave;
	TextView tvheader;
	int saveBtnFlag=0;
	int arrayEditText[]={R.id.etclosing_cold_room,R.id.etclosing_stock_mccaindf,R.id.etclosing_stock_storedf};
	//	List<String> listDataHeader;
	//<String, List<String>> listDataChild;

	List<StockNewGetterSetter> listDataHeader;
	HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild;

	private ArrayList<StockNewGetterSetter> stockData = new ArrayList<StockNewGetterSetter>();

	private SharedPreferences preferences;

	ArrayList<StockNewGetterSetter> brandData;
	ArrayList<StockNewGetterSetter> skuData;
	
	ArrayList<ColdroomClosingGetterSetter> closingColdData;

	ClosingStockInsertDataGetterSetter insertData=new ClosingStockInsertDataGetterSetter();

	GSKDatabase db;

	EditText et=null;

	String arrayData[]=new String[arrayEditText.length+1];

	String sku_cd;

	CustomKeyboardView mKeyboardView;
	Keyboard mKeyboard;

	String store_cd;
	String visit_date,username,intime;

	boolean dataExists=false;
	
	boolean ischangedflag=false;
	
	ArrayList<DeepFreezerTypeGetterSetter> deepFreezlist=new ArrayList<DeepFreezerTypeGetterSetter>();
	
	boolean mccainFlag=false;
	boolean storeFlag=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.closing_stock);

		currentapiVersion = android.os.Build.VERSION.SDK_INT;

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		btnSave=(Button) findViewById(R.id.save_btn);

		tvheader=(TextView) findViewById(R.id.txt_idealFor);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


		db=new GSKDatabase(getApplicationContext());
		db.open();

		mKeyboard = new Keyboard(this, R.xml.keyboard);

		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView
		.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
				this));

		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);

		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		username= preferences.getString(CommonString.KEY_USERNAME, null);
		intime=getCurrentTime();
		
	/*	deepFreezlist = db.getDFTypeData("McCain",store_cd);
		if(deepFreezlist.get(0).getStatus().equals("YES"))
		{
			mccainFlag=true;
		}
		
		deepFreezlist = db.getDFTypeData("Store",store_cd);
		if(deepFreezlist.get(0).getStatus().equals("YES"))
		{
			storeFlag=true;
		}*/

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

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_LONG).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {

				/*for(int i=0;i<listDataHeader.size();i++){
	                if(i==groupPosition){
	                    //do nothing
	                	}
	                    else{
	                    	expListView.collapseGroup(i);
	                    }
	                }*/

				/*Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition).getBrand() + " Expanded",
						Toast.LENGTH_LONG).show();*/
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				/*Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition).getBrand() + " Collapsed",
						Toast.LENGTH_LONG).show();*/

				if (mKeyboardView.getVisibility() == View.VISIBLE) {
					mKeyboardView.setVisibility(View.INVISIBLE);
					/*mKeyboardView.requestFocusFromTouch();*/
				}

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition).getBrand()
						+ " : "
						+ listDataChild.get(
								listDataHeader.get(groupPosition)).get(
										childPosition).getSku(), Toast.LENGTH_LONG)
										.show();


				findViewById(R.id.lvExp).setVisibility(View.INVISIBLE);
				findViewById(R.id.entry_data).setVisibility(View.VISIBLE);
				tvheader.setText(listDataChild.get(
						listDataHeader.get(groupPosition)).get(
								childPosition).getSku());
				sku_cd=listDataChild.get(
						listDataHeader.get(groupPosition)).get(
								childPosition).getSku_cd();

				saveBtnFlag=1;

				return false;
			}
		});

		/*for (int i = 0; i < arrayEditText.length; i++) {

			((EditText) findViewById(arrayEditText[i])).setOnFocusChangeListener(this);

		}*/

	}

	/*
	 * Preparing the list data
	 */

	private void prepareListData() {
		listDataHeader = new ArrayList<StockNewGetterSetter>();
		listDataChild = new HashMap<StockNewGetterSetter, List<StockNewGetterSetter>>();


		brandData=db.getStockAvailabilityData(store_cd);

		if(brandData.size()>0){

			// Adding child data

			for(int i=0;i<brandData.size();i++){
				listDataHeader.add(brandData.get(i));

				skuData=db.getClosingStockDataFromDatabase(store_cd, brandData.get(i).getBrand_cd());
				if(!(skuData.size()>0) || ((skuData.get(0).getClosing_stock()==null) || (skuData.get(0).getClosing_stock().equals("")))){
					skuData=db.getStockSkuClosingData(brandData.get(i).getBrand_cd(),store_cd);
				}
				else{
					btnSave.setText("Update");
				}

				List<StockNewGetterSetter> skulist = new ArrayList<StockNewGetterSetter>();
				for(int j=0;j<skuData.size();j++){
					
					//closingColdData=db.getClosingColdDataFromDatabase(store_cd, skuData.get(j).getSku_cd());
					/*if(closingColdData.size()>0){
						skuData.get(j).setClos_stock_cold_room(closingColdData.get(0).getClosing_cold().get(0));
					}*/
					
					skulist.add(skuData.get(j));
				}

				listDataChild.put(listDataHeader.get(i), skulist); // Header, Child data
			}

		}


	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		int id=v.getId();

		if(id==R.id.save_btn){

			expListView.clearFocus();

			flagcoldroom=flagmccain=flagstoredf=false;

			/*if(saveBtnFlag==0){
				finish();
			}
			else{

				findViewById(R.id.lvExp).setVisibility(View.VISIBLE);
				findViewById(R.id.entry_data).setVisibility(View.INVISIBLE);
				saveBtnFlag=0;

				saveData();
				tvheader.setText("Stock Availibility");
				hideSoftKeyboard(et);
			}*/

			if(validateData(listDataChild, listDataHeader)){

				if(validateStockData(listDataChild, listDataHeader)){
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage("Are you sure you want to save")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int id) {

							db.open();

							dataExists=db.checkStock(store_cd);

							//setBlank(listDataChild,listDataHeader);
							
							if(dataExists){
								db.UpdateClosingStocklistData(store_cd, listDataChild, listDataHeader);
							}
							else{
								db.InsertClosingStocklistData(
										store_cd, listDataChild,
										listDataHeader);
							}

							//db.deleteClosingStockData(store_cd);

							Snackbar.make(expListView,"Data has been saved",Snackbar.LENGTH_LONG).show();

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
					Toast.makeText(getApplicationContext(), "Data filled is not valid", Toast.LENGTH_LONG).show();
				}

			}
			else{
				listAdapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
			}
		}
	}

	public void hideSoftKeyboard(View view){
		InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/*@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if(hasFocus==true){
			et=(EditText) v;
		}
	}*/

	/*public void saveData(){

		for (int i = 0; i < arrayEditText.length; i++) {
			View myLayout = findViewById(R.id.entry_data);
			arrayData[i]=((EditText) myLayout.findViewById(arrayEditText[i])).getText().toString();
			((EditText) myLayout.findViewById(arrayEditText[i])).setText("");
		}

		insertData.setSku_cd(sku_cd);
		insertData.setClos_stock_cold_room(arrayData[0]);
		insertData.setMeccaindf(arrayData[1]);
		insertData.setStoredf(arrayData[2]);


		db.insertClosingStockData(insertData, sku_cd);

	}*/


	public class ExpandableListAdapter extends BaseExpandableListAdapter {

		private Context _context;
		private List<StockNewGetterSetter> _listDataHeader; // header titles
		// child data in format of header title, child title
		private HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> _listDataChild;

		public ExpandableListAdapter(Context context, List<StockNewGetterSetter> listDataHeader,
				HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listChildData) {
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

			final StockNewGetterSetter childText = (StockNewGetterSetter) getChild(groupPosition, childPosition);

			ViewHolder holder=null;

			if (convertView == null) {

				

				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.closing_stock_entry, null);

				holder=new ViewHolder();

				holder.cardView=(CardView) convertView.findViewById(R.id.card_view);
				holder.etclstkcold=(EditText) convertView.findViewById(R.id.etclosing_cold_room);
			/*	holder.etclstkmcndf=(EditText) convertView.findViewById(R.id.etclosing_stock_mccaindf);
				holder.etclstkstrdf=(EditText) convertView.findViewById(R.id.etclosing_stock_storedf);*/
				
			

				convertView.setTag(holder);

			}

			holder = (ViewHolder) convertView.getTag();

			if (currentapiVersion >= 11) {
				holder.etclstkcold.setTextIsSelectable(true);
				/*holder.etclstkmcndf.setTextIsSelectable(true);
				holder.etclstkstrdf.setTextIsSelectable(true);*/

				holder.etclstkcold.setRawInputType(InputType.TYPE_CLASS_TEXT);
				/*holder.etclstkmcndf.setRawInputType(InputType.TYPE_CLASS_TEXT);
				holder.etclstkstrdf.setRawInputType(InputType.TYPE_CLASS_TEXT);*/

			} else {
				holder.etclstkcold.setInputType(0);
				/*holder.etclstkmcndf.setInputType(0);
				holder.etclstkstrdf.setInputType(0);*/


			}

			holder.etclstkcold.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {

					if (hasFocus) {
						showKeyboardWithAnimation();
					}

					if (!hasFocus) {

						hide();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();
						if (value1.equals("")) {

							_listDataChild
									.get(listDataHeader.get(groupPosition))
									.get(childPosition).setClosing_stock("");

						} else {

							ischangedflag = true;

							_listDataChild
									.get(listDataHeader.get(groupPosition))
									.get(childPosition).setClosing_stock(value1);

						}

					}

				}
			});


			holder.etclstkcold.setText(childText.getClosing_stock());
			/*holder.etclstkmcndf.setText(childText.getClos_stock_meccaindf());
			holder.etclstkstrdf.setText(childText.getClos_stock_storedf());*/

			TextView txtListChild = (TextView) convertView
					.findViewById(R.id.lblListItem);

			txtListChild.setText(childText.getSku());
			
			if(!checkflag){
				
				boolean tempflag=false;
				
				if(holder.etclstkcold.getText().toString().equals("")){
					//holder.etclstkcold.setBackgroundColor(getResources().getColor(R.color.red));
					holder.etclstkcold.setHintTextColor(getResources().getColor(R.color.red));
					holder.etclstkcold.setHint("Empty");
					tempflag=true;
				}
				
				/*if(mccainFlag){
					
					if(holder.etclstkmcndf.getText().toString().equals("")){
						holder.etclstkmcndf.setHintTextColor(getResources().getColor(R.color.red));
						holder.etclstkmcndf.setHint("Empty");
						tempflag=true;
					}
					
				}

				if(storeFlag){
					
					if(holder.etclstkstrdf.getText().toString().equals("")){
						//holder.etclstkstrdf.setBackgroundColor(getResources().getColor(R.color.red));
						holder.etclstkstrdf.setHintTextColor(getResources().getColor(R.color.red));
						holder.etclstkstrdf.setHint("Empty");
						tempflag=true;
					}
					
				}*/
				
				
				if(tempflag){
					
					holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
				}
				else{
					
					holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
				}
				

			}
			
	if(!validate){
				
		if(checkValidHeaderArray.contains(groupPosition)){
			
			if(checkValidChildArray.contains(childPosition)){
				boolean tempflag=false;
				
				/*if(flagcoldroom){
					holder.etclstkcold.setTextColor(getResources().getColor(R.color.red));
					tempflag=true;
				}
				else{
					holder.etclstkcold.setTextColor(getResources().getColor(R.color.teal_dark));
				}
				
				if(flagmccain){
					holder.etclstkmcndf.setTextColor(getResources().getColor(R.color.red));
					tempflag=true;
				}
				else{
					holder.etclstkmcndf.setTextColor(getResources().getColor(R.color.teal_dark));
				}
				
				if(flagstoredf){
					holder.etclstkstrdf.setTextColor(getResources().getColor(R.color.red));
					tempflag=true;
				}
				else{
					holder.etclstkstrdf.setTextColor(getResources().getColor(R.color.teal_dark));
				}*/
				
				holder.etclstkcold.setTextColor(getResources().getColor(R.color.teal_dark));
				/*holder.etclstkmcndf.setTextColor(getResources().getColor(R.color.teal_dark));
				holder.etclstkstrdf.setTextColor(getResources().getColor(R.color.teal_dark));*/
				holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
				
			/*	if(!flagcoldroom && !flagmccain && !flagstoredf){
					holder.etclstkcold.setTextColor(getResources().getColor(R.color.teal_dark));
					holder.etclstkmcndf.setTextColor(getResources().getColor(R.color.teal_dark));
					holder.etclstkstrdf.setTextColor(getResources().getColor(R.color.teal_dark));
					holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
					tempflag=false;
				} 
				else{
					
					holder.etclstkcold.setTextColor(getResources().getColor(R.color.red));
					holder.etclstkmcndf.setTextColor(getResources().getColor(R.color.red));
					holder.etclstkstrdf.setTextColor(getResources().getColor(R.color.red));
					holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
					tempflag=true;
				}*/
				
				
				/*if(tempflag){
					
					holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
				}
				else{
					
					holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
				}*/
			}
			else{
				holder.etclstkcold.setTextColor(getResources().getColor(R.color.teal_dark));
			/*	holder.etclstkmcndf.setTextColor(getResources().getColor(R.color.teal_dark));
				holder.etclstkstrdf.setTextColor(getResources().getColor(R.color.teal_dark));*/
				holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
			}
			
		}
		else{
			holder.etclstkcold.setTextColor(getResources().getColor(R.color.teal_dark));
		/*	holder.etclstkmcndf.setTextColor(getResources().getColor(R.color.teal_dark));
			holder.etclstkstrdf.setTextColor(getResources().getColor(R.color.teal_dark));*/
			holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
		}
		
			
			
			}
			
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
			StockNewGetterSetter headerTitle = (StockNewGetterSetter) getGroup(groupPosition);
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
			
			if(!validate){
				if(checkValidHeaderArray.contains(groupPosition)){
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


	public void hide() {
		mKeyboardView.setVisibility(View.INVISIBLE);
	/*	// mKeyboardView.clearFocus();
		mKeyboardView.requestFocusFromTouch();*/

	}



	public class ViewHolder{

		EditText etclstkcold;
		CardView cardView;
	}

	boolean validateData(
			HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild2,
			List<StockNewGetterSetter> listDataHeader2) {
		//boolean flag = true;
		
		checkHeaderArray.clear();
		
		for (int i = 0; i < listDataHeader2.size(); i++) {
			for (int j = 0; j < listDataChild2.get(listDataHeader.get(i))
					.size(); j++) {
				String coldroom = listDataChild.get(listDataHeader.get(i)).get(j).getClosing_stock();
			/*	String mccndf = listDataChild.get(listDataHeader.get(i)).get(j).getClos_stock_meccaindf();
				String strdf = listDataChild.get(listDataHeader.get(i)).get(j).getClos_stock_storedf();
*/


				if (coldroom.equalsIgnoreCase(""))

						 {
					
					if(!checkHeaderArray.contains(i)){
						checkHeaderArray.add(i);
					}
					
					checkflag=false;
					
					//flag = false;
					break;

				} else{

					//flag = true;
					checkflag=true;
				}
			}

			if(checkflag == false){
				break;
			}

		}
		
		//expListView
		
		
		return checkflag;

	}



	boolean validateStockData(
			HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild2,
			List<StockNewGetterSetter> listDataHeader2) {
		//boolean flag = true;

		if(brandData.size()>0){

			// Adding child data

			checkValidHeaderArray.clear();
			checkValidChildArray.clear();
			
			
			for(int i=0;i<brandData.size();i++){

				stockData=db.getOpeningNMiddayStockDataFromDatabase(store_cd, brandData.get(i).getBrand_cd());

				for(int j=0;j<stockData.size();j++){
					
					String opening_stock = stockData.get(j).getOpenning_total_stock();
					String mid_stock = stockData.get(j).getTotal_mid_stock_received();
					String closing_stock = listDataChild.get(listDataHeader.get(i)).get(j).getClosing_stock();

					int open_stk, midStock, cl_stk;

					open_stk =Integer.parseInt(opening_stock);
					midStock=Integer.parseInt(mid_stock);
					cl_stk=Integer.parseInt(closing_stock);

						
						int total_stock = open_stk + midStock;

						if ((total_stock>=cl_stk)) {
							
							validate = true;
							//flag = true;

						} else {
							validate = false;
							valHeadCount=i;
							if(!checkValidChildArray.contains(j)){
								checkValidChildArray.add(j);
							}
							if(!checkValidHeaderArray.contains(i)){
								checkValidHeaderArray.add(i);
							}

							//flag = false;
							break;
						}
					
				}

				if(validate==false){
					break;
				}
				
			}

		}

		return validate;
	}



	/***
	 * Display the screen keyboard with an animated slide from bottom
	 */
	private void showKeyboardWithAnimation() {
		if (mKeyboardView.getVisibility() == View.GONE) {
			Animation animation = AnimationUtils
					.loadAnimation(ClosingStock.this,
							R.anim.slide_in_bottom);
			mKeyboardView.showWithAnimation(animation);
		}
		else if(mKeyboardView.getVisibility() == View.INVISIBLE){
			mKeyboardView.setVisibility(View.VISIBLE);
		}
	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (mKeyboardView.getVisibility() == View.VISIBLE) {
			mKeyboardView.setVisibility(View.INVISIBLE);
			/*mKeyboardView.requestFocusFromTouch();*/
		} else {

			if(ischangedflag){
				
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ClosingStock.this);
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

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mKeyboardView.setVisibility(View.GONE);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView.setVisibility(View.INVISIBLE);
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
	
	/*void setBlank(
			HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild2,
			List<StockNewGetterSetter> listDataHeader2){
	
		for (int i = 0; i < listDataHeader2.size(); i++) {
			for (int j = 0; j < listDataChild2.get(listDataHeader2.get(i))
					.size(); j++) {
				
				if(!mccainFlag){
					listDataChild.get(listDataHeader2.get(i)).get(j).setClos_stock_meccaindf("");

				}
				
				if(!storeFlag){
					listDataChild.get(listDataHeader2.get(i)).get(j).setClos_stock_storedf("");
					
				}
				
			}
		}
		
	}
*/
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
