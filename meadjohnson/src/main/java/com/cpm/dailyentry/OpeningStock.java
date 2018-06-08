package com.cpm.dailyentry;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString;

import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.DeepFreezerTypeGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;

@SuppressLint("ClickableViewAccessibility")
public class OpeningStock extends AppCompatActivity implements OnClickListener{

	boolean validate=true;
	boolean flagcoldroom=false;
	boolean flagmccain=false;
	boolean flagstoredf=false;
	int valHeadCount;
	int valChildCount;
	
	List<Integer> checkValidHeaderArray = new ArrayList<Integer>();
	List<Integer> checkValidChildArray = new ArrayList<Integer>();

	String _pathforcheck,_path,str;
	private String image1 = "";
	String img1 = "";
	static int grp_position = -1;
	
	boolean checkflag=true;
	static int currentapiVersion = 1;

	List<Integer> checkHeaderArray = new ArrayList<Integer>();

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	Button btnSave;
	TextView tvheader;
	int saveBtnFlag=0;
	int arrayEditText[]={R.id.etAs_Per_Mccain,R.id.etactual_listed,R.id.etOpening_Stock_Cold_Room,R.id.etOpening_Stock_Mccain_Df,R.id.etTotal_Facing_McCain_DF,R.id.etOpening_Stock_Store_DF,R.id.etTotal_Facing_Store_DF,R.id.etmaterial_wellness_thawed_quantity};
	//	List<String> listDataHeader;
	//<String, List<String>> listDataChild;

	List<StockNewGetterSetter> listDataHeader;
	HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild;
	ArrayList<StockNewGetterSetter> openingStockData;

	private SharedPreferences preferences;
	String store_cd;

	ArrayList<StockNewGetterSetter> brandData;
	ArrayList<StockNewGetterSetter> skuData;


	StockNewGetterSetter insertData=new StockNewGetterSetter();

	GSKDatabase db;

	EditText et=null;

	String arrayData[]=new String[arrayEditText.length+1];

	String sku_cd;

	CustomKeyboardView mKeyboardView;
	Keyboard mKeyboard;

	String visit_date,username,intime;

	private ArrayList<StockNewGetterSetter> stockData = new ArrayList<StockNewGetterSetter>();
	boolean dataExists=false;

	boolean openmccaindfFlag=false;
	
	String Error_Message;
	
	boolean ischangedflag=false;
	
	ArrayList<DeepFreezerTypeGetterSetter> deepFreezlist=new ArrayList<DeepFreezerTypeGetterSetter>();

	boolean mccainFlag=false;
	boolean storeFlag=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opening_stock);

		currentapiVersion = android.os.Build.VERSION.SDK_INT;

		// get the list view
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		btnSave=(Button) findViewById(R.id.save_btn);

		tvheader=(TextView) findViewById(R.id.txt_idealFor);

		mKeyboard = new Keyboard(this, R.xml.keyboard);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		str = Environment.getExternalStorageDirectory() + "/Meadjohnson_Images/";

		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView
		.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
				this));

		db=new GSKDatabase(getApplicationContext());
		db.open();

		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);

		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		username= preferences.getString(CommonString.KEY_USERNAME, null);
		intime=getCurrentTime();

		/*deepFreezlist = db.getDFTypeData("McCain",store_cd);
		if(deepFreezlist.get(0).getStatus().equals("YES"))
		{
			mccainFlag=true;
		}
		
		deepFreezlist = db.getDFTypeData("Store",store_cd);
		if(deepFreezlist.get(0).getStatus().equals("YES"))
		{
			storeFlag=true;
		}
		*/
		// preparing list data
		prepareListData();

		openmccaindfFlag=preferences.getBoolean("opnestkmccaindf", false);

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

		/*	for (int i = 0; i < arrayEditText.length; i++) {

			((EditText) findViewById(arrayEditText[i])).setOnFocusChangeListener(this);

		}*/

	}

	/*
	 * Preparing the list data
	 */

	private void prepareListData() {
		listDataHeader = new ArrayList<StockNewGetterSetter>();
		listDataChild = new HashMap<StockNewGetterSetter, List<StockNewGetterSetter>>();

		brandData = db.getHeaderStockImageData(store_cd, visit_date);
		if(!(brandData.size()>0)){
			brandData=db.getStockAvailabilityData(store_cd);
		}


		if(brandData.size()>0){

			// Adding child data

			for(int i=0;i<brandData.size();i++){
				listDataHeader.add(brandData.get(i));

				skuData=db.getOpeningStockDataFromDatabase(store_cd, brandData.get(i).getBrand_cd());
				if(!(skuData.size()>0) || (skuData.get(0).getOpenning_total_stock()==null) || (skuData.get(0).getOpenning_total_stock().equals(""))){
					skuData=db.getStockSkuData(brandData.get(i).getBrand_cd(),store_cd);
				}
				else{
					btnSave.setText("Update");
				}

				List<StockNewGetterSetter> skulist = new ArrayList<StockNewGetterSetter>();
				for(int j=0;j<skuData.size();j++){
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

		expListView.clearFocus();

		if(id==R.id.save_btn){

			/*	if(saveBtnFlag==0){
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
				
				/*if(db.isClosingDataFilled(store_cd)){
					validateStockData(listDataChild, listDataHeader);
				}*/

				if(validateImg()){

					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage("Are you sure you want to save")
							.setCancelable(false)
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
															int id) {

											db.open();

											getMid();

											//setBlank(listDataChild,listDataHeader);

											dataExists=db.checkStock(store_cd);
											if(dataExists){
												db.UpdateHeaderOpeningStocklistData(store_cd,visit_date,listDataHeader);
												db.UpdateOpeningStocklistData(store_cd, listDataChild, listDataHeader);
											}
											else{

												db.InsertHeaderOpeningStocklistData(store_cd,visit_date,listDataHeader);
												db.InsertOpeningStocklistData(
														store_cd, listDataChild,
														listDataHeader);
											}
											//db.deleteOpeningStockData(store_cd);


											Toast.makeText(getApplicationContext(),
													"Data has been saved", Toast.LENGTH_LONG).show();

						/*Intent DailyEntryMenu = new Intent(
								OpeningStock.this,
								StoreEntry.class);
						startActivity(DailyEntryMenu);*/
											finish();

											overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
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
					Toast.makeText(getApplicationContext(), Error_Message, Toast.LENGTH_LONG).show();
				}



			}
			else{
				Toast.makeText(getApplicationContext(), Error_Message, Toast.LENGTH_LONG).show();
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
		insertData.setAs_per_meccain(arrayData[0]);
		insertData.setActual_listed(arrayData[1]);
		insertData.setOpen_stock_cold_room(arrayData[2]);
		insertData.setOpen_stock_mccaindf(arrayData[3]);
		insertData.setTotalfacing_mccaindf(arrayData[4]);
		insertData.setOpen_stock_store_df(arrayData[5]);
		insertData.setTotal_facing_storedf(arrayData[6]);
		insertData.setMaterial_wellness(arrayData[7]);

		db.insertOpeningStockData(insertData, sku_cd);

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
				convertView = infalInflater.inflate(R.layout.list_item_openingstk, null);

				holder=new ViewHolder();

				holder.cardView=(CardView) convertView.findViewById(R.id.card_view);
				//holder.etaspermcn=(TextView) convertView.findViewById(R.id.etAs_Per_Mccain);
				holder.ettotalstck=(EditText) convertView.findViewById(R.id.etOpening_Stock_total_stock);
				holder.etfacing=(EditText) convertView.findViewById(R.id.etOpening_Stock_Facing);
				holder.etstckunder=(EditText) convertView.findViewById(R.id.etStock_Under_Days);
			/*	holder.etopnstkstrdf=(EditText) convertView.findViewById(R.id.etOpening_Stock_Store_DF);
				holder.ettotalfacstrdf=(EditText) convertView.findViewById(R.id.etTotal_Facing_Store_DF);
				holder.etmatwell=(EditText) convertView.findViewById(R.id.etmaterial_wellness_thawed_quantity);*/

				holder.openmccaindf_layout=(LinearLayout) convertView.findViewById(R.id.openmccaindf_layaout);
				
				
			
				
				/*//------------------
				holder.mWatcher.setActive(false);
			    holder.ettotalstck.setText(childText.getOpen_stock_cold_room(),BufferType.EDITABLE);
			    //holder.ettotalstck.setText(childText.getOpen_stock_cold_room());
			    holder.mWatcher.setPosition(childPosition);
			    holder.mWatcher.setActive(true);
*/

				/*if(openmccaindfFlag){
					holder.openmccaindf_layout.setVisibility(View.VISIBLE);
				}*/

				/*holder.tbactual = (ToggleButton) convertView
						.findViewById(R.id.tbactual_listed);*/

				convertView.setTag(holder);

			}
			else{
				holder = (ViewHolder) convertView.getTag();
			}


		
			
			if (currentapiVersion >= 11) {
				//holder.etaspermcn.setTextIsSelectable(true);
				holder.ettotalstck.setTextIsSelectable(true);
				holder.etfacing.setTextIsSelectable(true);
				holder.etstckunder.setTextIsSelectable(true);
			/*	holder.etopnstkstrdf.setTextIsSelectable(true);
				holder.ettotalfacstrdf.setTextIsSelectable(true);
				holder.etmatwell.setTextIsSelectable(true);*/
				//holder.etaspermcn.setRawInputType(InputType.TYPE_CLASS_TEXT);
				holder.ettotalstck.setRawInputType(InputType.TYPE_CLASS_TEXT);
				holder.etfacing.setRawInputType(InputType.TYPE_CLASS_TEXT);
				holder.etstckunder.setRawInputType(InputType.TYPE_CLASS_TEXT);
			/*	holder.etopnstkstrdf.setRawInputType(InputType.TYPE_CLASS_TEXT);
				holder.ettotalfacstrdf.setRawInputType(InputType.TYPE_CLASS_TEXT);
				holder.etmatwell.setRawInputType(InputType.TYPE_CLASS_TEXT);*/
			} else {
				//holder.etaspermcn.setInputType(0);
				holder.ettotalstck.setInputType(0);
				holder.etfacing.setInputType(0);
				holder.etstckunder.setInputType(0);
			/*	holder.etopnstkstrdf.setInputType(0);
				holder.ettotalfacstrdf.setInputType(0);
				holder.etmatwell.setInputType(0);*/

			}


//----------------------------------
			holder.ettotalstck.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {

					if(hasFocus){
						showKeyboardWithAnimation();
					}
					//hideSoftKeyboard();


					if (!hasFocus) {

						hide();
						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();

						final String facing=_listDataChild
								.get(listDataHeader.get(groupPosition))
								.get(position).getOpening_facing();

						final String stk_under=_listDataChild
								.get(listDataHeader.get(groupPosition))
								.get(position).getStock_under45days();


						if (value1.equals("")) {

							_listDataChild
									.get(listDataHeader.get(groupPosition))
									.get(position).setOpenning_total_stock("");

						} else if (facing.equals("") && stk_under.equals("")) {

							ischangedflag = true;

							_listDataChild
									.get(listDataHeader.get(groupPosition))
									.get(position).setOpenning_total_stock(value1);

						} else{

							if(!facing.equals("")){
								_listDataChild
										.get(listDataHeader.get(groupPosition))
										.get(position).setOpening_facing("");
							}
							if (!stk_under.equals("")) {

								_listDataChild
										.get(listDataHeader.get(groupPosition))
										.get(position).setStock_under45days("");
							}

							ischangedflag = true;

							_listDataChild
									.get(listDataHeader.get(groupPosition))
									.get(position).setOpenning_total_stock(value1);

						}
					}
				}
			});

			holder.etfacing.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {

					if(hasFocus){
						showKeyboardWithAnimation();
					}

					if (!hasFocus) {

						hide();
						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();

						final String stock=_listDataChild
								.get(listDataHeader.get(groupPosition))
								.get(position).getOpenning_total_stock();

						if (value1.equals("")) {

							_listDataChild
							.get(listDataHeader.get(groupPosition))
							.get(position).setOpening_facing("");

						} else if(stock.equals("")) {

							Snackbar.make(expListView,"First fill Total Stock data",Snackbar.LENGTH_LONG).show();
							_listDataChild
									.get(listDataHeader.get(groupPosition))
									.get(position).setOpening_facing("");

						}else{

							int totalstk=0;

							totalstk=Integer.parseInt(stock);

							int facing=Integer.parseInt(value1);
							
							if(facing>totalstk){
								/*Toast.makeText(getApplicationContext(),
										"Facing cannot be greater than Total Stock", Toast.LENGTH_LONG).show();*/

								Snackbar.make(expListView, "Facing cannot be greater than Total Stock", Snackbar.LENGTH_LONG).show();
							}
							else{
								
								ischangedflag=true;
								
								_listDataChild
								.get(listDataHeader.get(groupPosition))
								.get(position).setOpening_facing(value1);
							}

						/*	ischangedflag=true;

							_listDataChild
									.get(listDataHeader.get(groupPosition))
									.get(position).setOpening_facing(value1);*/


						}

					}

				}
			});

			holder.etstckunder.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {

					if(hasFocus){
						showKeyboardWithAnimation();
					}

					if (!hasFocus) {

						hide();
						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();

						final String stock=_listDataChild
								.get(listDataHeader.get(groupPosition))
								.get(position).getOpenning_total_stock();

						if (value1.equals("")) {

							_listDataChild
							.get(listDataHeader.get(groupPosition))
							.get(position).setStock_under45days("");

						} else  if(stock.equals("")) {

							Snackbar.make(expListView,"First fill Total Stock data",Snackbar.LENGTH_LONG).show();
							_listDataChild
									.get(listDataHeader.get(groupPosition))
									.get(position).setStock_under45days("");

						}else {

							int totalstk = 0;

							totalstk = Integer.parseInt(stock);

							int stkunder = Integer.parseInt(value1);

							if (stkunder > totalstk) {
								/*Toast.makeText(getApplicationContext(),
										"Facing cannot be greater than Total Stock", Toast.LENGTH_LONG).show();*/

								Snackbar.make(expListView, "Stock Under Expiry cannot be greater than Total Stock", Snackbar.LENGTH_LONG).show();
							} else {
								ischangedflag = true;

								_listDataChild
										.get(listDataHeader.get(groupPosition))
										.get(position).setStock_under45days(value1);

							}
						}

					}

				}
			});



			holder.ettotalstck.setId(childPosition);
			holder.etfacing.setId(childPosition);
			holder.etstckunder.setId(childPosition);
		/*	holder.etopnstkstrdf.setId(childPosition);
			holder.ettotalfacstrdf.setId(childPosition);
			holder.etmatwell.setId(childPosition);*/

			//holder.etaspermcn.setText(childText.getAs_per_meccain());
			holder.ettotalstck.setText(childText.getOpenning_total_stock());
			if(childText.getOpenning_total_stock().equals("0")){
				holder.etfacing.setText("0");
				_listDataChild
						.get(listDataHeader.get(groupPosition))
						.get(childPosition).setOpening_facing("0");

				holder.etfacing.setEnabled(false);
			}
			else{
				holder.etfacing.setText(childText.getOpening_facing());
				holder.etfacing.setEnabled(true);
			}

			if(childText.getOpenning_total_stock().equals("0")){
				holder.etstckunder.setText("0");
				_listDataChild
						.get(listDataHeader.get(groupPosition))
						.get(childPosition).setStock_under45days("0");
				holder.etstckunder.setEnabled(false);
			}
			else{
				holder.etstckunder.setText(childText.getStock_under45days());
				holder.etstckunder.setEnabled(true);
			}


			/*holder.etopnstkstrdf.setText(childText.getOpen_stock_store_df());
			holder.ettotalfacstrdf.setText(childText.getTotal_facing_storedf());
			holder.etmatwell.setText(childText.getMaterial_wellness());


			holder.tbactual.setChecked(_listDataChild
					.get(listDataHeader.get(groupPosition))
					.get(childPosition).getActual_listed().equals("YES"));*/


			_listDataChild
			.get(listDataHeader.get(groupPosition))
			.get(childPosition).setSku_cd(childText.getSku_cd());

			TextView txtListChild = (TextView) convertView
					.findViewById(R.id.lblListItem);




			txtListChild.setText(childText.getSku());

			if(!checkflag){
				
				boolean tempflag=false;
				
				if(holder.ettotalstck.getText().toString().equals("")){
					//holder.ettotalstck.setBackgroundColor(getResources().getColor(R.color.red));
					holder.ettotalstck.setHintTextColor(getResources().getColor(R.color.red));
					holder.ettotalstck.setHint("Empty");
					tempflag=true;
				}
				else{
					//holder.ettotalstck.setBackgroundColor(getResources().getColor(R.color.white));
				}

					if(holder.etfacing.getText().toString().equals("")){
						//holder.etfacing.setBackgroundColor(getResources().getColor(R.color.red));
						holder.etfacing.setHintTextColor(getResources().getColor(R.color.red));
						holder.etfacing.setHint("Empty");
						tempflag=true;
					}
					else{
						//holder.etfacing.setBackgroundColor(getResources().getColor(R.color.white));
					}

					/*if(holder.etstckunder.getText().toString().equals("")){
						//holder.etstckunder.setBackgroundColor(getResources().getColor(R.color.red));
						holder.etstckunder.setHintTextColor(getResources().getColor(R.color.red));
						holder.etstckunder.setHint("Empty");
						tempflag=true;
					}
					else{
						//holder.etstckunder.setBackgroundColor(getResources().getColor(R.color.white));
					}*/
				
			/*	if(storeFlag){
					if(holder.etopnstkstrdf.getText().toString().equals("")){
						//holder.etopnstkstrdf.setBackgroundColor(getResources().getColor(R.color.red));
						holder.etopnstkstrdf.setHintTextColor(getResources().getColor(R.color.red));
						holder.etopnstkstrdf.setHint("Empty");
						tempflag=true;
					}
					else{
						//holder.etopnstkstrdf.setBackgroundColor(getResources().getColor(R.color.white));
					}
					
					if(holder.ettotalfacstrdf.getText().toString().equals("")){
						//holder.ettotalfacstrdf.setBackgroundColor(getResources().getColor(R.color.red));
						holder.ettotalfacstrdf.setHintTextColor(getResources().getColor(R.color.red));
						holder.ettotalfacstrdf.setHint("Empty");
						tempflag=true;
					}
					else{
						//holder.ettotalfacstrdf.setBackgroundColor(getResources().getColor(R.color.white));
					}
				}
				
				

				if(holder.etmatwell.getText().toString().equals("")){
					//holder.etmatwell.setBackgroundColor(getResources().getColor(R.color.red));
					holder.etmatwell.setHintTextColor(getResources().getColor(R.color.red));
					holder.etmatwell.setHint("Empty");
					tempflag=true;
				}
				else{
					//holder.etmatwell.setBackgroundColor(getResources().getColor(R.color.white));
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
						
						if(flagcoldroom){
							holder.ettotalstck.setTextColor(getResources().getColor(R.color.red));
							tempflag=true;
						}
						else{
							holder.ettotalstck.setTextColor(getResources().getColor(R.color.teal_dark));
						}
						
						if(flagmccain){
							holder.etfacing.setTextColor(getResources().getColor(R.color.red));
							tempflag=true;
						}
						else{
							holder.etfacing.setTextColor(getResources().getColor(R.color.teal_dark));
						}
						
						/*if(flagstoredf){
							holder.etopnstkstrdf.setTextColor(getResources().getColor(R.color.red));
							tempflag=true;
						}
						else{
							holder.etopnstkstrdf.setTextColor(getResources().getColor(R.color.teal_dark));
						}*/
						
						if(!flagcoldroom && !flagmccain && !flagstoredf){
							holder.ettotalstck.setTextColor(getResources().getColor(R.color.teal_dark));
							holder.etfacing.setTextColor(getResources().getColor(R.color.teal_dark));
							//holder.etopnstkstrdf.setTextColor(getResources().getColor(R.color.teal_dark));
							holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
							tempflag=false;
						} 
						else{
							
							/*holder.etclstkcold.setTextColor(getResources().getColor(R.color.red));
							holder.etclstkmcndf.setTextColor(getResources().getColor(R.color.red));
							holder.etclstkstrdf.setTextColor(getResources().getColor(R.color.red));*/
							holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
							tempflag=true;
						}
						
						
						/*if(tempflag){
							
							holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
						}
						else{
							
							holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
						}*/
					}
					else{
						holder.ettotalstck.setTextColor(getResources().getColor(R.color.teal_dark));
						holder.etfacing.setTextColor(getResources().getColor(R.color.teal_dark));
						//holder.etopnstkstrdf.setTextColor(getResources().getColor(R.color.teal_dark));
						holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
					}
					
				}
				else{
					holder.ettotalstck.setTextColor(getResources().getColor(R.color.teal_dark));
					holder.etfacing.setTextColor(getResources().getColor(R.color.teal_dark));
					//holder.etopnstkstrdf.setTextColor(getResources().getColor(R.color.teal_dark));
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
		public View getGroupView(final int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			final StockNewGetterSetter headerTitle = (StockNewGetterSetter) getGroup(groupPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_group_opening, null);
			}

			//final int position = convertView.getId();
			TextView lblListHeader = (TextView) convertView
					.findViewById(R.id.lblListHeader);
			ImageView imgcam = (ImageView) convertView.findViewById(R.id.imgcamstk);

			imgcam.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					grp_position = groupPosition;

					_pathforcheck = store_cd +headerTitle.getBrand_cd()+ "OpeningStock"
							+ "Image" + visit_date.replace("/","")+getCurrentTime().replace(":","")+".jpg";

					_path = Environment.getExternalStorageDirectory() +  "/Meadjohnson_Images/"
							+ _pathforcheck;

					startCameraActivity();
				}
			});

			lblListHeader.setTypeface(null, Typeface.BOLD);
			lblListHeader.setText(headerTitle.getBrand());

			if (!img1.equalsIgnoreCase("")) {
				if (groupPosition == grp_position) {
					//childText.get(childPosition).setCamera("YES");
					headerTitle.setImg_cam(img1);
					//childText.setImg(img1);
					img1 = "";

				}
			}


			if (headerTitle.getImg_cam()!=null && !headerTitle.getImg_cam().equals("")) {
				imgcam.setBackgroundResource(R.drawable.camera_done);
			} else {
				imgcam.setBackgroundResource(R.drawable.camera);
			}

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

			//convertView.setId(groupPosition);

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

		EditText ettotalstck,etfacing,etstckunder;
		//ToggleButton tbactual;
		//TextView etaspermcn;
		LinearLayout openmccaindf_layout;
		CardView cardView;

		 public MutableWatcher mWatcher;
	}


	boolean validateData(
			HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild2,
			List<StockNewGetterSetter> listDataHeader2) {
		boolean flag = true;

		checkHeaderArray.clear();

		for (int i = 0; i < listDataHeader2.size(); i++) {
			for (int j = 0; j < listDataChild2.get(listDataHeader2.get(i))
					.size(); j++) {
				/*String aspermccain = listDataChild2.get(listDataHeader2.get(i)).get(j).getAs_per_meccain();*/
				String openstocktotal = listDataChild2.get(listDataHeader2.get(i)).get(j).getOpenning_total_stock();
				String openfacing = listDataChild2.get(listDataHeader2.get(i)).get(j).getOpening_facing();
				String stockunder = listDataChild2.get(listDataHeader2.get(i)).get(j).getStock_under45days();

				int openstkmccndfint;
				int totalmccndfint;
				int openstockstrdfint;
				int totalstrdfint;
				

				if (openstocktotal.equalsIgnoreCase("") || (openfacing.equalsIgnoreCase(""))) {

					if(!checkHeaderArray.contains(i)){
						checkHeaderArray.add(i);
					}

					checkflag=false;

					flag = false;
					Error_Message="Please fill all the data";
					break;

				} 
				else {
					
					/*if(mccainFlag){
						openstkmccndfint=Integer.parseInt(openstkmccndf);
						totalmccndfint=Integer.parseInt(totalmccndf);

						if(totalmccndfint>openstkmccndfint){
							if(!checkHeaderArray.contains(i)){
								checkHeaderArray.add(i);
							}

							checkflag=false;

							flag = false;
							Error_Message="Facing data should not be greater than Mccain opening Stock";
							break;
						}
						else{
							checkflag=true;
							flag = true;
						}
					}
					else{
						checkflag=true;
						flag = true;
					}*/

					checkflag=true;
					flag = true;
					
					/*if(storeFlag){
						
						openstockstrdfint=Integer.parseInt(openstockstrdf);
						totalstrdfint=Integer.parseInt(totalstrdf);
						
						
						if(totalstrdfint>openstockstrdfint){
							if(!checkHeaderArray.contains(i)){
								checkHeaderArray.add(i);
							}

							checkflag=false;

							flag = false;
							Error_Message="Facing data should not be greater than Store opening Stock";
							break;
						}
						else{
							checkflag=true;
							flag = true;
						}
						
					}
					else{
						checkflag=true;
						flag = true;
					}*/
					
					
					
					
				}
			
				/*
				int opnstkmcndfint=Integer.parseInt(openstkmccndf);
				int totalmccndfint=Integer.parseInt(totalmccndf);
				int openstockstrdfint=Integer.parseInt(openstockstrdf);
				int totalstrdfint=Integer.parseInt(totalstrdf);
				
				if(totalmccndfint>opnstkmcndfint){
					Error_Message="Facing cannot be greater than Mccain Opening Stock";
					break;
				}
				
				
				if(totalstrdfint>openstockstrdfint){
					Error_Message="Facing cannot be greater than Store Opening Stock";
					break;
				}*/
			}

			if(checkflag == false){
				break;
			}

		}

		//expListView.invalidate();
		listAdapter.notifyDataSetChanged();

		return checkflag;

	}


	public boolean validateImg(){

		/*int count=0;

		for(int i=0;i<listDataHeader.size();i++){

			if(!(listDataHeader.get(i).getImg_cam()==null) && !listDataHeader.get(i).getImg_cam().equals("")){
				count++;
			}
		}

		if(count>0){
			return true;
		}
		else{

			Error_Message="Please click at least one image";
			return false;
		}*/

		return true;

	}
	
	
/*	void setBlank(
			HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild2,
			List<StockNewGetterSetter> listDataHeader2){
	
		for (int i = 0; i < listDataHeader2.size(); i++) {
			for (int j = 0; j < listDataChild2.get(listDataHeader2.get(i))
					.size(); j++) {
				
				if(!mccainFlag){
					listDataChild.get(listDataHeader2.get(i)).get(j).setOpenning_total_stock("");
					listDataChild.get(listDataHeader2.get(i)).get(j).setTotalfacing_mccaindf("");
				}
				
				if(!storeFlag){
					listDataChild.get(listDataHeader2.get(i)).get(j).setOpen_stock_store_df("");
					listDataChild.get(listDataHeader2.get(i)).get(j).setTotal_facing_storedf("");
				}
				
			}
		}
		
	}*/

	/***
	 * Display the screen keyboard with an animated slide from bottom
	 */
	private void showKeyboardWithAnimation() {
		if (mKeyboardView.getVisibility() == View.GONE) {
			Animation animation = AnimationUtils
					.loadAnimation(OpeningStock.this,
							R.anim.slide_in_bottom);
			mKeyboardView.showWithAnimation(animation);
		}
		else if(mKeyboardView.getVisibility() == View.INVISIBLE){
			mKeyboardView.setVisibility(View.VISIBLE);
		}
	}


	public void hideSoftKeyboard() {
		try {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void hide() {
		mKeyboardView.setVisibility(View.INVISIBLE);
		/*	// mKeyboardView.clearFocus();
		mKeyboardView.requestFocusFromTouch();*/

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
						OpeningStock.this);
				builder.setMessage(CommonString.ONBACK_ALERT_MESSAGE)
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
					public void onClick(
							DialogInterface dialog, int id) {
					
						OpeningStock.this.finish();
						
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
				OpeningStock.this.finish();
				
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
	
	/*boolean validateStockData(
			HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> listDataChild2,
			List<StockNewGetterSetter> listDataHeader2) {
		//boolean flag = true;

		if(brandData.size()>0){

			// Adding child data

			checkValidHeaderArray.clear();
			checkValidChildArray.clear();
			
			
			for(int i=0;i<brandData.size();i++){

				stockData=db.getClosingNMiddayStockDataFromDatabase(store_cd, brandData.get(i).getBrand_cd());

				for(int j=0;j<stockData.size();j++){
					
					String closing_coldroom = stockData.get(j).getClos_stock_cold_room();
					String mid_stock = stockData.get(j).getMidday_stock();
					String closing_mccain_df = stockData.get(j).getClos_stock_meccaindf();	
					String closing_store_df = stockData.get(j).getClos_stock_storedf();	

					String cold_room = listDataChild.get(listDataHeader.get(i)).get(j).getOpen_stock_cold_room();
					String mccain_df = listDataChild.get(listDataHeader.get(i)).get(j).getOpen_stock_mccaindf();
					String store_df = listDataChild.get(listDataHeader.get(i)).get(j).getOpen_stock_store_df();
					
					int midStock=Integer.parseInt(mid_stock);
					
					int opncold=Integer.parseInt(cold_room);
					int opnmccn=Integer.parseInt(mccain_df);
					int opnstore=+Integer.parseInt(store_df);
					
					int closecold=Integer.parseInt(closing_coldroom);
					int closemccn=Integer.parseInt(closing_mccain_df);
					int closestore=Integer.parseInt(closing_store_df);
					
					if(midStock==0){
						if(closecold>opncold){
							flagcoldroom=true;
							if(!checkValidChildArray.contains(j)){
								checkValidChildArray.add(j);
							}
							if(!checkValidHeaderArray.contains(i)){
								checkValidHeaderArray.add(i);
							}
						} 
						if(closemccn>opnmccn){
							flagmccain=true;
							if(!checkValidChildArray.contains(j)){
								checkValidChildArray.add(j);
							}
							if(!checkValidHeaderArray.contains(i)){
								checkValidHeaderArray.add(i);
							}
						}
						if(closestore>opnstore){
							flagstoredf=true;
							if(!checkValidChildArray.contains(j)){
								checkValidChildArray.add(j);
							}
							if(!checkValidHeaderArray.contains(i)){
								checkValidHeaderArray.add(i);
							}
						}
						
						if(flagcoldroom == true || flagmccain == true || flagstoredf == true){
							validate=false;
							
							if(!checkValidChildArray.contains(j)){
								checkValidChildArray.add(j);
							}
							if(!checkValidHeaderArray.contains(i)){
								checkValidHeaderArray.add(i);
							}
							
							break;
						}
						else{
							validate=true;
						}
						
					}
					else{
						
						int total_stock = opncold + midStock + opnmccn + opnstore;
						int total_closing = closecold + closemccn + closestore;
						
						if ((total_stock>=total_closing)) {
							
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
					
				}

				if(validate==false){
					break;
				}
				
			}

		}

		return validate;
	}
	*/
	
	class MutableWatcher implements TextWatcher {

	    private int mPosition;
	    private boolean mActive;

	    void setPosition(int position) {
	        mPosition = position;
	    }

	    void setActive(boolean active) {
	        mActive = active;
	    }

	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) { }

	    @Override
	    public void afterTextChanged(Editable s) {
	        if (mActive) {
	          //  mUserDetails.set(mPosition, s.toString());
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
				_pathforcheck="";
				break;

			case -1:

				if (_pathforcheck != null && !_pathforcheck.equals("")) {
					if (new File(str + _pathforcheck).exists()) {

						image1 = _pathforcheck;
						img1 = _pathforcheck;
						expListView.invalidateViews();
						_pathforcheck = "";

					}
				}

				break;
		}


		super.onActivityResult(requestCode, resultCode, data);
	}

}
