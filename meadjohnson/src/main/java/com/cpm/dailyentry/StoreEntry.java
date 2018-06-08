package com.cpm.dailyentry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.GetterSetter.NavMenuItemGetterSetter;
import com.cpm.database.GSKDatabase;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.MiddayStockInsertData;
import com.cpm.xmlGetterSetter.OpeningStockInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.StockGetterSetter;

public class StoreEntry extends AppCompatActivity implements OnClickListener{

	Button btnDeepfreez,btnOpeningStock,btnClosingStock,btnMiddayStock,btnPromotion,btnAsset,btnfoodstr,btnfacingcomp,btncalls;
   Button performance;
	GSKDatabase db;
	private SharedPreferences preferences;
	String store_cd;
	
	boolean food_flag,user_flag=false;
	
	String user_type="";
	
	private ArrayList<StockGetterSetter> stockData = new ArrayList<StockGetterSetter>();
	
	HashMap<OpeningStockInsertDataGetterSetter, List<MiddayStockInsertData>> listDataChild;
	
	List<OpeningStockInsertDataGetterSetter> listDataHeader;
	ArrayList<MiddayStockInsertData> skuData;
	
	LinearLayout layout_mid_close,gap_layout,gap_calls,food_layout;

	ValueAdapter adapter;

	RecyclerView recyclerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.store_entry_new_layout);

		//setContentView(R.layout.store_entry_layout);
		//setContentView(R.layout.store_entry_all_layout);

		setContentView(R.layout.menu_item_recycle_layout);

		/*btnDeepfreez=(Button) findViewById(R.id.deepfreezer);
		btnOpeningStock=(Button) findViewById(R.id.openingstock);
		btnClosingStock=(Button) findViewById(R.id.closingstock);
		btnMiddayStock=(Button) findViewById(R.id.midstock);
		btnPromotion=(Button) findViewById(R.id.prommotion);
		btnAsset=(Button) findViewById(R.id.assets);
		btnfoodstr=(Button) findViewById(R.id.foodstore);
		btnfacingcomp=(Button) findViewById(R.id.facingcompetitor);
		btncalls=(Button) findViewById(R.id.calls);
		
		layout_mid_close=(LinearLayout) findViewById(R.id.midclose_layout);
		food_layout=(LinearLayout) findViewById(R.id.food_layout);
		gap_layout=(LinearLayout) findViewById(R.id.gap);
		gap_calls=(LinearLayout) findViewById(R.id.gap_calls);
		performance =(Button)findViewById(R.id.performance);*/

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
				
	/*	performance.setOnClickListener(this);
		
		btnMiddayStock.setOnClickListener(this);
		btnDeepfreez.setOnClickListener(this);
		btnClosingStock.setOnClickListener(this);
		btnOpeningStock.setOnClickListener(this);
		btnPromotion.setOnClickListener(this);
		btnAsset.setOnClickListener(this);
		btnfoodstr.setOnClickListener(this);
		btnfacingcomp.setOnClickListener(this);
		btncalls.setOnClickListener(this);*/
		
		
		db=new GSKDatabase(getApplicationContext());
		db.open();
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		store_cd = preferences.getString(CommonString.KEY_STORE_CD, null);
		
		food_flag=preferences.getBoolean(CommonString.KEY_FOOD_STORE, false);
		
		user_type = preferences.getString(CommonString.KEY_USER_TYPE, null);
		
		if(user_type!=null){/*
			if(user_type.equals("Merchandiser")){
				layout_mid_close.setVisibility(View.GONE);
				gap_layout.setVisibility(View.GONE);
				
				//layout_calls.setVisibility(View.GONE);
				btnMiddayStock.setVisibility(View.GONE);
				//gap_calls.setVisibility(View.GONE);
				
				performance.setVisibility(View.GONE);
				
				user_flag=true;
			}
		*/}
		
		/*if(food_flag){
			food_layout.setVisibility(View.VISIBLE);
			gap_calls.setVisibility(View.VISIBLE);
		}
		else{
			gap_calls.setVisibility(View.GONE);
		}*/


	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		recyclerView=(RecyclerView) findViewById(R.id.drawer_layout_recycle);

		adapter=new ValueAdapter(getApplicationContext(),getdata());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
		
		//validate();
		
	}
	
	public void validate(){
		
		db.open();
		
		if(db.isClosingDataFilled(store_cd)){
			btnClosingStock.setBackgroundResource(R.drawable.closing_stock_done);
		}
		else{
			btnClosingStock.setBackgroundResource(R.drawable.closing_stock);
		}
		
		if(db.isCompetitionDataFilled(store_cd)){
			btnfacingcomp.setBackgroundResource(R.drawable.competition_done);
		}

		//btnDeepfreez.setBackgroundResource(R.drawable.deep_freezer_done);
		
		/*if(db.isDeepfreezerDataFilled(store_cd, "McCain") && db.isDeepfreezerDataFilled(store_cd, "Store")){
			btnDeepfreez.setBackgroundResource(R.drawable.deep_freezer_done);
		}*/
		if(db.isMiddayDataFilled(store_cd)){
			btnMiddayStock.setBackgroundResource(R.drawable.mid_stock_done);
		}
		else{
			btnMiddayStock.setBackgroundResource(R.drawable.mid_stock);
		}
		
		if(db.isOpeningDataFilled(store_cd)){
			btnOpeningStock.setBackgroundResource(R.drawable.opening_stock_done);
		}
		else{
			btnOpeningStock.setBackgroundResource(R.drawable.opening_stock);
		}
		
		if(db.isAssetDataFilled(store_cd)){
			btnAsset.setBackgroundResource(R.drawable.asset_done);
		}
		if(db.isPromotionDataFilled(store_cd)){
			btnPromotion.setBackgroundResource(R.drawable.promotion_done);
		}


		
		/*if(db.isFoodDataFilled(store_cd)){
			btnfoodstr.setBackgroundResource(R.drawable.food_done);
		}
		
		if(db.isCallsDataFilled(store_cd)){
			btncalls.setBackgroundResource(R.drawable.calls_done);
		}*/
		
	}


	@Override
	public void onBackPressed() {
		/*Intent i = new Intent(this, DailyEntryScreen.class);
		startActivity(i);*/
		
		finish();
		
		
		overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
	}


	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		int id=view.getId();

		switch (id) {
		/*case R.id.deepfreezer:
			
			if(!db.isClosingDataFilled(store_cd)){
				Intent in=new Intent(getApplicationContext(),DeepFreezerActivity.class);

				startActivity(in);

				overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
			}
			else{
				Toast.makeText(getApplicationContext(), "Data cannot be changed", Toast.LENGTH_SHORT).show();
			}

			

			break;*/

		case R.id.openingstock:
			
			if(!db.isClosingDataFilled(store_cd)){
			
				//if(db.getDFTypeUploadData(store_cd).size()>0){
					
					Intent in1=new Intent(getApplicationContext(),OpeningStock.class);

					startActivity(in1);

					overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
					
				/*}
				else{
					Toast.makeText(getApplicationContext(),
							"First Fill Deep Freezer Data", Toast.LENGTH_SHORT).show();
				}*/

			}
			else{
				Toast.makeText(getApplicationContext(), "Data cannot be changed", Toast.LENGTH_SHORT).show();
			}
			
			
			break;

		case R.id.closingstock:
			
			stockData=db.getOpeningStock(store_cd);
			if((stockData.size()<=0) || (stockData.get(0).getOpen_stock_cold_room()==null) || (stockData.get(0).getOpen_stock_cold_room().equals(""))){
			
				Toast.makeText(getApplicationContext(),
						"First Fill Opening Stock and Midday Stock Data", Toast.LENGTH_SHORT).show();
			
		}
		else{
			stockData=db.getMiddayStock(store_cd);
			
			if((stockData.size()<=0) || (stockData.get(0).getMidday_stock()==null) || (stockData.get(0).getMidday_stock().equals(""))){
				
				Toast.makeText(getApplicationContext(),
						"First Fill Opening Stock and Midday Stock Data", Toast.LENGTH_SHORT).show();
				
				}
			else{
	
				Intent in2=new Intent(getApplicationContext(),ClosingStock.class);

				startActivity(in2);

				overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

			}
		}

			
			break;


		case R.id.midstock:

			if(!db.isClosingDataFilled(store_cd)){
				
				Intent in3=new Intent(getApplicationContext(),MidDayStock.class);

				startActivity(in3);

				overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
			}
			else{
				Toast.makeText(getApplicationContext(), "Data cannot be changed", Toast.LENGTH_SHORT).show();
			}
			
			

			break;
			
		case R.id.prommotion:

			Intent in4=new Intent(getApplicationContext(),PromotionActivity.class);

			startActivity(in4);

			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

			break;
			
		case R.id.assets:

			Intent in5=new Intent(getApplicationContext(),AssetActivity.class);

			startActivity(in5);

			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

			break;
			
		case R.id.foodstore:

			Intent in6=new Intent(getApplicationContext(),FoodStore.class);

			startActivity(in6);

			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

			break;
			
		case R.id.facingcompetitor:

			Intent in7=new Intent(getApplicationContext(),FacingCompetitor.class);

			startActivity(in7);

			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

			break;
			
		case R.id.calls:

			Intent in8=new Intent(getApplicationContext(),CallsActivity.class);

			startActivity(in8);

			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

			break;
			
			
		case R.id.performance:
			
			Intent startPerformance = 	new Intent(StoreEntry.this,Performance.class);
			startActivity(startPerformance);
			 overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.save_database:

			AlertDialog.Builder builder1 = new AlertDialog.Builder(
					StoreEntry.this);
			builder1.setMessage(
					"Are you sure you want to take the backup of your data")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
						@SuppressWarnings("resource")
						public void onClick(DialogInterface dialog,
								int id) {
							try {

								File file = new File(Environment
										.getExternalStorageDirectory(),
										"capital_backup");
								if (!file.isDirectory()) {
									file.mkdir();
								}

								File sd = Environment
										.getExternalStorageDirectory();
								File data = Environment
										.getDataDirectory();

								if (sd.canWrite()) {
									long date = System.currentTimeMillis(); 

									SimpleDateFormat sdf = new SimpleDateFormat("MMM/MM/dd");
									String dateString = sdf.format(date);   
									
									String currentDBPath = "//data//com.cpm.capitalfoods//databases//Capital_DATABASE";
									String backupDBPath = "capital_backup"
											+ dateString.replace('/', '-');

									String path=Environment.getExternalStorageDirectory().getPath();

									File currentDB = new File(data,
											currentDBPath);
									File backupDB = new File(path,
											backupDBPath);

									if (currentDB.exists()) {
										@SuppressWarnings("resource")
										FileChannel src = new FileInputStream(
												currentDB).getChannel();
										FileChannel dst = new FileOutputStream(
												backupDB).getChannel();
										dst.transferFrom(src, 0,
												src.size());
										src.close();
										dst.close();
									}
								}
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

						}
					})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int id) {
							dialog.cancel();
						}
					});
			AlertDialog alert1 = builder1.create();
			alert1.show();


			break;

			case android.R.id.home:

				// NavUtils.navigateUpFromSameTask(this);
				finish();

				overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

			break;

		}
		return super.onOptionsItemSelected(item);
	}



	public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.MyViewHolder>{

		private LayoutInflater inflator;

		List<NavMenuItemGetterSetter> data= Collections.emptyList();

		public ValueAdapter(Context context, List<NavMenuItemGetterSetter> data){

			inflator = LayoutInflater.from(context);
			this.data=data;

		}

		@Override
		public ValueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

			View view=inflator.inflate(R.layout.custom_row,parent,false);

			MyViewHolder holder=new MyViewHolder(view);

			return holder;
		}

		@Override
		public void onBindViewHolder(final ValueAdapter.MyViewHolder viewHolder, final int position) {

			final NavMenuItemGetterSetter current=data.get(position);

			//viewHolder.txt.setText(current.txt);

			viewHolder.icon.setImageResource(current.getIconImg());
			viewHolder.icon.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					if(current.getIconImg()==R.drawable.opening_stock || current.getIconImg()==R.drawable.opening_stock_done){

						if(!db.isClosingDataFilled(store_cd)){

							//if(db.getDFTypeUploadData(store_cd).size()>0){

							Intent in1=new Intent(getApplicationContext(),OpeningStock.class);

							startActivity(in1);

							overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

				/*}
				else{
					Toast.makeText(getApplicationContext(),
							"First Fill Deep Freezer Data", Toast.LENGTH_SHORT).show();
				}*/

						}
						else{

							Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();

						}


					}
					if(current.getIconImg()==R.drawable.mid_stock || current.getIconImg()==R.drawable.mid_stock_done){

						if(!db.isClosingDataFilled(store_cd)){

							Intent in3=new Intent(getApplicationContext(),MidDayStock.class);

							startActivity(in3);

							overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
						}
						else{

							Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();

						}


					}
					if(current.getIconImg()==R.drawable.promotion || current.getIconImg()==R.drawable.promotion_done){
						Intent in4=new Intent(getApplicationContext(),PromotionActivity.class);

						startActivity(in4);

						overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

					}
					if(current.getIconImg()==R.drawable.asset || current.getIconImg()==R.drawable.asset_done){
						Intent in5=new Intent(getApplicationContext(),AssetActivity.class);

						startActivity(in5);

						overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
					}

					if(current.getIconImg()==R.drawable.closing_stock || current.getIconImg()==R.drawable.closing_stock_done){

						if(db.isOpeningDataFilled(store_cd)){

							if(db.isMiddayDataFilled(store_cd)){

								Intent in2=new Intent(getApplicationContext(),ClosingStock.class);

								startActivity(in2);

								overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

							}
							else{

								Snackbar.make(recyclerView,"First fill Midday Stock Data",Snackbar.LENGTH_SHORT).show();
							}

						}else{

							Snackbar.make(recyclerView,"First fill Opening Stock data",Snackbar.LENGTH_SHORT).show();

						}

					}
				/*	if(current.getIconImg()==R.drawable.c_add_display || current.getIconImg()==R.drawable.c_add_display_done){


						Intent in5=new Intent(getApplicationContext(),AdditionalPOIActivity.class);

						startActivity(in5);


						overridePendingTransition(R.anim.activity_in, R.anim.activity_out);


						*//*stockData=db.getOpeningStock(store_cd);
						if((stockData.size()<=0) || (stockData.get(0).getOpen_stock_cold_room()==null) || (stockData.get(0).getOpen_stock_cold_room().equals(""))){

							Toast.makeText(getApplicationContext(),
									"First Fill Opening Stock and Midday Stock Data", Toast.LENGTH_SHORT).show();

						}
						else{
							stockData=db.getMiddayStock(store_cd);

							if((stockData.size()<=0) || (stockData.get(0).getMidday_stock()==null) || (stockData.get(0).getMidday_stock().equals(""))){

								Toast.makeText(getApplicationContext(),
										"First Fill Opening Stock and Midday Stock Data", Toast.LENGTH_SHORT).show();

							}
							else{

								Intent in2=new Intent(getApplicationContext(),ClosingStock.class);

								startActivity(in2);

								overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

							}
						}*//*

					}*/

					if(current.getIconImg()==R.drawable.competition || current.getIconImg()==R.drawable.competition_done){

						Intent in7=new Intent(getApplicationContext(),CompetionMenuActivity.class);
						//Intent in7=new Intent(getApplicationContext(),CompetitionFaceupActivity.class);

						startActivity(in7);

						overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
					}


					if(current.getIconImg()==R.drawable.calls || current.getIconImg()==R.drawable.calls_done){

						//Intent in7=new Intent(getApplicationContext(),CompetionMenuActivity.class);
						Intent in7=new Intent(getApplicationContext(),CallsActivity.class);

						startActivity(in7);

						overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
					}

					if(current.getIconImg()==R.drawable.c_add_display || current.getIconImg()==R.drawable.c_add_display_done){

						Intent in=new Intent(getApplicationContext(),SamplingDetailActivity.class);

						startActivity(in);

						overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
					}
				}
			});

		}

		@Override
		public int getItemCount() {
			return data.size();
		}


		class MyViewHolder extends RecyclerView.ViewHolder{

			//TextView txt;
			ImageView icon;

			public MyViewHolder(View itemView) {
				super(itemView);
				//txt=(TextView) itemView.findViewById(R.id.list_txt);
				icon=(ImageView) itemView.findViewById(R.id.list_icon);
			}
		}

	}

	public List<NavMenuItemGetterSetter> getdata(){
		List<NavMenuItemGetterSetter> data=new ArrayList<>();

		int openingImg, middayImg, closingImg, promotionImg, assetImg, additionalImg, competitionImg, callsImg, samplingImg;

		//callsImg = R.drawable.calls;

		if(db.isClosingDataFilled(store_cd)){
			closingImg = R.drawable.closing_stock_done;
		}
		else{
			closingImg = R.drawable.closing_stock;
		}

		if(db.isMiddayDataFilled(store_cd)){
			middayImg = R.drawable.mid_stock_done;
		}
		else{
			middayImg = R.drawable.mid_stock;
		}

		if(db.isOpeningDataFilled(store_cd)){
			openingImg = R.drawable.opening_stock_done;
		}
		else{
			openingImg = R.drawable.opening_stock;
		}

		if(db.isAssetDataFilled(store_cd)){
			assetImg = R.drawable.asset_done;
		}
		else{
			assetImg = R.drawable.asset;
		}

		if(db.isPromotionDataFilled(store_cd)){
			promotionImg = R.drawable.promotion_done;
		}
		else{
			promotionImg = R.drawable.promotion;
		}

		if(db.getFacingCompetitorData(store_cd).size()>0){
			competitionImg = R.drawable.competition_done;
		}
		else{
			competitionImg = R.drawable.competition;
		}

		if(db.getPOIData(store_cd).size()>0){
			additionalImg = R.drawable.c_add_display_done;
		}
		else{
			additionalImg = R.drawable.c_add_display;
		}

		if(db.getSampleData(store_cd).size()>0){
			samplingImg = R.drawable.c_add_display_done;
		}
		else{
			samplingImg = R.drawable.c_add_display;
		}

		if(db.isCallsDataFilled(store_cd)){
			callsImg = R.drawable.calls_done;
		}
		else{
			callsImg = R.drawable.calls;
		}

	/*	if(user_type.equals("Promoter")){
			int img[]={openingImg, middayImg, promotionImg, assetImg, closingImg, additionalImg, competitionImg};
			for(int i=0;i<img.length;i++){

				NavMenuItemGetterSetter recData=new NavMenuItemGetterSetter();
				recData.setIconImg(img[i]);
				//recData.setIconName(text[i]);

				data.add(recData);
			}
		}
		else if(user_type.equals("Merchandiser")){
			int img[]={openingImg, promotionImg, assetImg, additionalImg, competitionImg};
			for(int i=0;i<img.length;i++){

				NavMenuItemGetterSetter recData=new NavMenuItemGetterSetter();
				recData.setIconImg(img[i]);
				//recData.setIconName(text[i]);

				data.add(recData);
			}
		}
*/
		//String text[]={"My Cart", "Profile", "About","Logout"};

		if(user_type.equals("Promoter")){
			int img[]={openingImg, middayImg, promotionImg, assetImg, closingImg, competitionImg, callsImg};
			for(int i=0;i<img.length;i++){

				NavMenuItemGetterSetter recData=new NavMenuItemGetterSetter();
				recData.setIconImg(img[i]);
				//recData.setIconName(text[i]);

				data.add(recData);
			}
		}
		else if(user_type.equals("Merchandiser")){
			int img[]={openingImg, promotionImg, assetImg, competitionImg};
			for(int i=0;i<img.length;i++){

				NavMenuItemGetterSetter recData=new NavMenuItemGetterSetter();
				recData.setIconImg(img[i]);
				//recData.setIconName(text[i]);

				data.add(recData);
			}
		}


		return  data;
	}

}
