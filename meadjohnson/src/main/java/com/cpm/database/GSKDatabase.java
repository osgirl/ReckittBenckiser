package com.cpm.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cpm.Constants.CommonString;
import com.cpm.GetterSetter.SampleDataGetterSetter;
import com.cpm.GetterSetter.StoreBean;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.TableBean;
import com.cpm.xmlGetterSetter.AssetInsertdataGetterSetter;
import com.cpm.xmlGetterSetter.AssetMasterGetterSetter;
import com.cpm.xmlGetterSetter.CallsGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.ClosingStockInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.ColdroomClosingGetterSetter;
import com.cpm.xmlGetterSetter.CompanyGetterSetter;
import com.cpm.xmlGetterSetter.CompetitionPromotionGetterSetter;
import com.cpm.xmlGetterSetter.DeepFreezerGetterSetter;
import com.cpm.xmlGetterSetter.DeepFreezerTypeGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetionCompanyGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetitorGetterSetter;
import com.cpm.xmlGetterSetter.FoodStoreInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.HeaderGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.MappingAssetGetterSetter;
import com.cpm.xmlGetterSetter.MappingAvailabilityGetterSetter;
import com.cpm.xmlGetterSetter.MappingPromotionGetterSetter;
import com.cpm.xmlGetterSetter.MiddayStockInsertData;
import com.cpm.xmlGetterSetter.NonWorkingReasonGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;
import com.cpm.xmlGetterSetter.POIGetterSetter;
import com.cpm.xmlGetterSetter.PerformanceGetterSetter;
import com.cpm.xmlGetterSetter.PromotionInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;
import com.cpm.xmlGetterSetter.StockGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;


public class GSKDatabase extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "Capital_DATABASESS";
	public static final int DATABASE_VERSION = 2;
	private SQLiteDatabase db;

	// ********************************************Extract
	// Database*********************************************

	// ***********************************END COPY
	// DATABASE************************************************************

	public GSKDatabase(Context completeDownloadActivity) {
		super(completeDownloadActivity, DATABASE_NAME, null, DATABASE_VERSION);
	}// TODO Auto-generated constructor stub }

	public void open() {
		try {
			db = this.getWritableDatabase();
		} catch (Exception e) {
		}
	}

	public void close() {
		db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// CREATING TABLE FOR GTGSK
	
		
		db.execSQL(TableBean.getjcptable());
		db.execSQL(TableBean.getSkumastertable());
		db.execSQL(TableBean.getMappingavailtable());
		db.execSQL(TableBean.getMappingpromotable());
		db.execSQL(TableBean.getMappingassettable());
		//db.execSQL(TableBean.getDeepfreezertable());
		db.execSQL(TableBean.getAssetmastertable());
		db.execSQL(TableBean.getCompanytable());
		//db.execSQL(TableBean.getPerformancetable());
		//db.execSQL(TableBean.getClosingcoldtable());
		db.execSQL(TableBean.getNonworkingtable());
		//db.execSQL(TableBean.getCategorymastertable());
		db.execSQL(CommonString.CREATE_TABLE_DEEPFREEZER_DATA);
		db.execSQL(CommonString.CREATE_TABLE_OPENING_STOCK_DATA);
		db.execSQL(CommonString.CREATE_TABLE_CLOSING_STOCK_DATA);
		db.execSQL(CommonString.CREATE_TABLE_MIDDAY_STOCK_DATA);
		db.execSQL(CommonString.CREATE_TABLE_insert_OPENINGHEADER_DATA);
		db.execSQL(CommonString.CREATE_TABLE_insert_OPENINGHEADER_CLOSING_DATA);
		db.execSQL(CommonString.CREATE_TABLE_insert_HEADER_MIDDAY_DATA);
		db.execSQL(CommonString.CREATE_TABLE_insert_HEADER_PROMOTION_DATA);
		db.execSQL(CommonString.CREATE_TABLE_PROMOTION_DATA);
		db.execSQL(CommonString.CREATE_TABLE_insert_HEADER_ASSET_DATA);
		db.execSQL(CommonString.CREATE_TABLE_ASSET_DATA);
		db.execSQL(CommonString.CREATE_TABLE_FACING_COMPETITOR_DATA);
		db.execSQL(CommonString.CREATE_TABLE_FOOD_STORE_DATA);
		db.execSQL(CommonString.CREATE_TABLE_insert_HEADER_FOOD_STORE_DATA);
		db.execSQL(CommonString.CREATE_TABLE_COVERAGE_DATA);
		db.execSQL(CommonString.CREATE_TABLE_STOCK_DATA);
		db.execSQL(CommonString.CREATE_TABLE_CALLS_DATA);
		db.execSQL(CommonString.CREATE_TABLE_COMPETITION_POI);
		db.execSQL(CommonString.CREATE_TABLE_POI);
		db.execSQL(CommonString.CREATE_TABLE_COMPETITION_PROMOTION);
		db.execSQL(CommonString.CREATE_TABLE_STOCK_IMAGE);
		db.execSQL(CommonString.CREATE_TABLE_SAMPLE_DATA);

	/*	System.out.println("table create "+TableBean.getMerchandisetable());
		
		db.execSQL(TableBean.getMerchandisetable());
		db.execSQL(TableBean.getStoreTable());
		
		
		db.execSQL(TableBean.getNonWorkingTable());
		db.execSQL(CommonString.CREATE_TABLE_COVERAGE_DATA1);
		db.execSQL(CommonString.CREATE_TABLE_GATE_COVERAGE_DATA);
		db.execSQL(CommonString.CREATE_TABLE_GATE_MEETING_MER);
		db.execSQL(TableBean.getDistributorTable());
		db.execSQL(TableBean.getDesignation());
		db.execSQL(TableBean.getJourneyplan());
		db.execSQL(TableBean.getTableDeviation());
		db.execSQL(CommonString.CREATE_TABLE_STORE);
		db.execSQL(CommonString.CREATE_TABLE_ATTENDENCE_DATA);
		db.execSQL(TableBean.getSecWindow());
		db.execSQL(TableBean.getSupPerform());
		db.execSQL(TableBean.getSupMerchandiser());
		db.execSQL(CommonString.CREATE_TABLE_LIST_HEADER);
		db.execSQL(CommonString.CREATE_TABLE_LIST_CHILD);*/
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_COVERAGE_DATA);

		onCreate(db);
	}

	
	public void deleteSpecificStoreData(String storeid) {
		
		db.delete( CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
		db.delete("DEEPFREEZER_DATA", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		db.delete("FACING_COMPETITOR_DATA", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		db.delete("openingHeader_Asset_data", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		db.delete("ASSET_DATA", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		db.delete("openingHeader_Promotion_data", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		db.delete("PROMOTION_DATA", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		db.delete("openingHeader_FOOD_STORE_data", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		db.delete("FOOD_STORE_DATA", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		db.delete("openingHeader_data", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
	/*	db.delete("OPENING_STOCK_DATA", null, null);
		db.delete("MIDDAY_STOCK_DATA", null, null);
		db.delete("CLOSING_STOCK_DATA", null, null);*/
		db.delete("openingHeader_Closing_data", CommonString.KEY_STORE_CD + "='" + storeid+ "'", null);
		db.delete("openingHeader_Midday_data", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		db.delete("STOCK_DATA", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		
		db.delete("CALLS_DATA", CommonString.KEY_STORE_CD + "='" + storeid + "'", null);
		
	}
	
	
	public void deleteAllTables() {

		// DELETING TABLES GTGSK

		
		db.delete( CommonString.TABLE_COVERAGE_DATA, null, null);
		db.delete("DEEPFREEZER_DATA", null, null);
		db.delete("FACING_COMPETITOR_DATA", null, null);
		db.delete("openingHeader_Asset_data", null, null);
		db.delete("ASSET_DATA", null, null);
		db.delete("openingHeader_Promotion_data", null, null);
		db.delete("PROMOTION_DATA", null, null);
		db.delete("openingHeader_FOOD_STORE_data", null, null);
		db.delete("FOOD_STORE_DATA", null, null);
		db.delete("openingHeader_data", null, null);
	/*	db.delete("OPENING_STOCK_DATA", null, null);
		db.delete("MIDDAY_STOCK_DATA", null, null);
		db.delete("CLOSING_STOCK_DATA", null, null);*/
		db.delete("openingHeader_Closing_data", null, null);
		db.delete("openingHeader_Midday_data", null, null);
		db.delete("STOCK_DATA", null, null);
		db.delete("CALLS_DATA", null, null);
		db.delete(CommonString.TABLE_FACING_COMPETITOR_DATA,null,null);
		db.delete(CommonString.TABLE_COMPETITION_POI,null,null);
		db.delete(CommonString.TABLE_COMPETITION_PROMOTION,null,null);
		db.delete(CommonString.TABLE_POI,null,null);
		db.delete(CommonString.TABLE_STOCK_IMAGE,null,null);
		
	}
	
	
	public void deleteStockData(){
		db.delete("STOCK_DATA", null, null);
	}
	
	public void deleteStockHeaderData(){
		db.delete("openingHeader_data", null, null);
	}


	public void updateGeoTagStatus(String id, String status, double lat,
			double longtitude) {

		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_STATUS, status);
			values.put(CommonString.KEY_LATITUDE, Double.toString(lat));
			values.put(CommonString.KEY_LONGITUDE, Double.toString(longtitude));

			db.update(CommonString.TABLE_GEO_TAG_MAPPING, values,
					CommonString.KEY_STORE_ID + "='" + id + "'", null);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Earned Toggle Data ",
					ex.toString());
		}
	}


	public void InsertGeoTagData(JCPGetterSetter data) {

		db.delete(CommonString.TABLE_GEO_TAG_MAPPING, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getStoreid().size(); i++) {

				values.put(CommonString.KEY_STORE_ID, data.getStoreid().get(i));
				values.put(CommonString.KEY_STORE_NAME, data.getStorename()
						.get(i));
				values.put(CommonString.storetype_id, data.getStore_typeid()
						.get(i));
				values.put(CommonString.KEY_CATEGORY_ID, data.getCATEGORY_ID()
						.get(i));
				values.put(CommonString.KEY_LATITUDE, "");
				values.put(CommonString.KEY_LONGITUDE, "");

				values.put(CommonString.KEY_STATUS, data.getStatus().get(i));

				db.insert(CommonString.TABLE_GEO_TAG_MAPPING, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.toString());
		}

	}
	
	
	
	//Store data
	
		public void insertSkuMasterData(SkuMasterGetterSetter data) {

			db.delete("SKU_MASTER", null, null);
			ContentValues values = new ContentValues();

			try {

				
				for (int i = 0; i < data.getSku_cd().size(); i++) {
					
					
						
						values.put("SKU_CD", Integer.parseInt(data.getSku_cd().get(i)));
						values.put("SKU", data.getSku().get(i));
						values.put("BRAND_CD", Integer.parseInt(data.getBrand_cd().get(i)));
						values.put("BRAND", data.getBrand().get(i));
						
						values.put("CATEGORY_CD", Integer.parseInt(data.getCategory_cd().get(i)));
						values.put("CATEGORY", data.getCategory().get(i));
						//values.put("MRP", data.getMrp().get(i));
						values.put("SKU_SEQUENCE", data.getSku_sequence().get(i));
						values.put("BRAND_SEQUENCE", data.getBrand_sequence().get(i));
						values.put("CATEGORY_SEQUENCE", data.getCategory_sequence().get(i));
						
						//values.put("CATEGORY_TYPE", data.getCategory_type().get(i));
						
						//values.put("PACKING", data.getPacking_size().get(i));
						
						db.insert("SKU_MASTER", null, values);
						

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}
	
	
		//JCP data
		
			public void insertJCPData(JourneyPlanGetterSetter data) {

				db.delete("JOURNEY_PLAN", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getStore_cd().size(); i++) {

						values.put("STORE_CD", Integer.parseInt(data.getStore_cd().get(i)));
						values.put("EMP_CD",Integer.parseInt(data.getEmp_cd().get(i)));

//-------------------------------
//Temp 		values.put("VISIT_DATE", data.getVISIT_DATE().get(i));

						values.put("VISIT_DATE", data.getVISIT_DATE().get(i));
						values.put("KEYACCOUNT", data.getKey_account().get(i));
						
						values.put("STORENAME", data.getStore_name().get(i));
						values.put("CITY", data.getCity().get(i));
						values.put("STORETYPE", data.getStore_type().get(i));
						//values.put("CATEGORY_TYPE", data.getCategory_type().get(i));
						
						values.put("UPLOAD_STATUS", data.getUploadStatus().get(i));
						values.put("CHECKOUT_STATUS", data.getCheckOutStatus().get(i));
						
						/*values.put("UPLOAD_STATUS", "N");
						values.put("CHECKOUT_STATUS","N");
*/
						db.insert("JOURNEY_PLAN", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert JCP Data ",
							ex.toString());
				}

			}
			
			
			//mapping available data
			
			public void insertMappingAvailData(MappingAvailabilityGetterSetter data) {

				db.delete("MAPPING_AVAILABILITY", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getStore_cd().size(); i++) {

						values.put("STORE_CD", Integer.parseInt(data.getStore_cd().get(i)));
						values.put("SKU_CD",Integer.parseInt(data.getSku_cd().get(i)));
						/*values.put("CATEGORY_SEQUENCE", Integer.parseInt(data.getCategory_sequence().get(i)));
						values.put("BRAND_SEQUENCE", Integer.parseInt(data.getBrand_sequence().get(i)));
						values.put("SKU_SEQUENCE", Integer.parseInt(data.getSku_sequence().get(i)));*/
						
						
						
						db.insert("MAPPING_AVAILABILITY", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Mapping Data ",
							ex.toString());
				}

			}
			
			
			// Performance Data
			
			
			public void insertPerformanceData(PerformanceGetterSetter data) {

				db.delete("MY_PERFORMANCE", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getStore_cd().size(); i++) {

						values.put("STORE_CD",data.getStore_cd().get(i));
						values.put("TARGET",data.getMonthly_target().get(i));
						values.put("SALE", data.getMtd_sales().get(i));
						values.put("ACH", data.getAchievement().get(i));
						
						db.insert("MY_PERFORMANCE", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Performance Data ",
							ex.toString());
				}

			}
			
			
			
	// closing cold Data
			
			
			public void insertClosingColdData(ColdroomClosingGetterSetter data) {

				db.delete("PREVIOUS_COLD_STOCK", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getStore_cd().size(); i++) {

						values.put("STORE_CD",data.getStore_cd().get(i));
						values.put("SKU_CD",data.getSku_cd().get(i));
						values.put("CLOSING_STOCK_COLD", data.getClosing_cold().get(i));
					
						db.insert("PREVIOUS_COLD_STOCK", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closing Cold Data ",
							ex.toString());
				}

			}
			
			
			
//mapping promotion data
			
			public void insertMappingPromotionData(MappingPromotionGetterSetter data) {

				db.delete("MAPPING_PROMOTION", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getStore_cd().size(); i++) {

						values.put("STORE_CD", Integer.parseInt(data.getStore_cd().get(i)));
						values.put("PROMOTION", data.getPromotion().get(i));
						values.put("PID", data.getPid().get(i));
						values.put("BRAND_CD", data.getBrand_cd().get(i));

						/*values.put("SKU_CD",Integer.parseInt(data.getSku_cd().get(i)));
						values.put("BRAND_SEQUENCE", Integer.parseInt(data.getBrand_sequence().get(i)));
						values.put("SKU_SEQUENCE", Integer.parseInt(data.getSku_sequence().get(i)));
						values.put("CATEGORY_TYPE", data.getCategory_type().get(i));*/
						
						db.insert("MAPPING_PROMOTION", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Mapping promotion Data ",
							ex.toString());
				}

			}
		
			
//mapping asset data
			
			public void insertMappingAssetData(MappingAssetGetterSetter data) {

				db.delete("MAPPING_ASSET", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getStore_cd().size(); i++) {

						values.put("STORE_CD", Integer.parseInt(data.getStore_cd().get(i)));
						values.put("BRAND_ID", Integer.parseInt(data.getBrand_id().get(i)));
						values.put("ASSET_CD", data.getAsset_cd().get(i));
						
						db.insert("MAPPING_ASSET", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Mapping asset Data",
							ex.toString());
				}

			}
			
			
//deep freezer data
			
			public void insertDeepFreezerData(DeepFreezerGetterSetter data) {

				db.delete("DEEPFREEZER_MASTER", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getFid().size(); i++) {

						values.put("FID", Integer.parseInt(data.getFid().get(i)));
						values.put("DEEP_FREEZER", data.getDeep_frrezer().get(i));
						values.put("FREEZER_TYPE", data.getType().get(i));
						values.put("SEQUENCE", Integer.parseInt(data.getSequence().get(i)));
						
						db.insert("DEEPFREEZER_MASTER", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert DeepFreezer Data ",
							ex.toString());
				}

			}
		
			
//Asset master data
			
			public void insertAssetMasterData(AssetMasterGetterSetter data) {

				db.delete("ASSET_MASTER", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getAsset_cd().size(); i++) {

						values.put("ASSET_CD", Integer.parseInt(data.getAsset_cd().get(i)));
						values.put("ASSET", data.getAsset().get(i));
						
						db.insert("ASSET_MASTER", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Asset Master Data ",
							ex.toString());
				}

			}

	//Asset master data

	public void insertCompanyMasterData(CompanyGetterSetter data) {

		db.delete("COMPANY_MASTER", null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getCompany_cd().size(); i++) {

				values.put("COMPANY_CD", Integer.parseInt(data.getCompany_cd().get(i)));
				values.put("COMPANY", data.getCompany().get(i));

				db.insert("COMPANY_MASTER", null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Comapny Master Data ",
					ex.toString());
		}

	}

			
//Non Working data
			
			public void insertNonWorkingReasonData(NonWorkingReasonGetterSetter data) {

				db.delete("NON_WORKING_REASON", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getReason_cd().size(); i++) {

						values.put("REASON_CD", Integer.parseInt(data.getReason_cd().get(i)));
						values.put("REASON", data.getReason().get(i));
						values.put("ENTRY_ALLOW", data.getEntry_allow().get(i));
						
						db.insert("NON_WORKING_REASON", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Non Working Data ",
							ex.toString());
				}

			}
			
			
//Category Master data
			
			public void insertCategoryMasterData(CategoryMasterGetterSetter data) {

				db.delete("CATEGORY_MASTER", null, null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.getCategory_cd().size(); i++) {

						values.put("CATEGORY_CD", Integer.parseInt(data.getCategory_cd().get(i)));
						values.put("CATEGORY", data.getCategory().get(i));
						
						db.insert("CATEGORY_MASTER", null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Category master Data ",
							ex.toString());
				}

			}
			
			
			//get JCP Data
			
			public ArrayList<JourneyPlanGetterSetter> getJCPData(String date) {

				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<JourneyPlanGetterSetter> list = new ArrayList<JourneyPlanGetterSetter>();
				Cursor dbcursor = null;

				try {
					dbcursor = db.rawQuery("SELECT * from JOURNEY_PLAN where VISIT_DATE = '" + date + "'" 
							, null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							JourneyPlanGetterSetter sb = new JourneyPlanGetterSetter();

							sb.setStore_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STORE_CD")));
							
							sb.setKey_account(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("KEYACCOUNT")));

							sb.setStore_name((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STORENAME"))));


							sb.setCity((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CITY"))));
							
						/*	sb.setCategory_type((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE"))));*/
							
							sb.setUploadStatus((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("UPLOAD_STATUS"))));
							sb.setCheckOutStatus((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CHECKOUT_STATUS"))));
							sb.setVISIT_DATE((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("VISIT_DATE"))));

							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingJCP data---------------------->Stop<-----------",
						"-------------------");
				return list;

			}

	//get JCP Data

	public ArrayList<JourneyPlanGetterSetter> getAllJCPData() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<JourneyPlanGetterSetter> list = new ArrayList<JourneyPlanGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * from JOURNEY_PLAN "
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					JourneyPlanGetterSetter sb = new JourneyPlanGetterSetter();

					sb.setStore_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STORE_CD")));

					sb.setKey_account(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("KEYACCOUNT")));

					sb.setStore_name((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STORENAME"))));


					sb.setCity((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CITY"))));


					sb.setUploadStatus((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("UPLOAD_STATUS"))));
					sb.setCheckOutStatus((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CHECKOUT_STATUS"))));
					sb.setVISIT_DATE((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("VISIT_DATE"))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingJCP data---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
		
//get DeepFreezerType Data from Master
			
			public ArrayList<DeepFreezerTypeGetterSetter> getDFMasterData(String dftype) {

				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<DeepFreezerTypeGetterSetter> list = new ArrayList<DeepFreezerTypeGetterSetter>();
				Cursor dbcursor = null;

				try {
					dbcursor = db.rawQuery("SELECT * from DEEPFREEZER_MASTER where FREEZER_TYPE = '" + dftype + "'" 
							, null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							DeepFreezerTypeGetterSetter df = new DeepFreezerTypeGetterSetter();


							df.setDeep_freezer(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("DEEP_FREEZER")));
							df.setFid(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("FID")));
							df.setFreezer_type(dftype);
							df.setRemark("");
							df.setStatus("NO");

							list.add(df);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Deepfreezer!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching Deep Freezer data---------------------->Stop<-----------",
						"-------------------");
				return list;

			}
			
			
			//Insert Deepfreezer Type Data
			
			public void insertDeepFreezerTypeData(ArrayList<DeepFreezerTypeGetterSetter> data,String dfType, String store_cd) {

				db.delete(CommonString.TABLE_DEEPFREEZER_DATA, "FREEZER_TYPE" + "='" + dfType + "'", null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.size(); i++) {

						values.put("FID", Integer.parseInt(data.get(i).getFid()));
						values.put("STORE_CD", store_cd);
						values.put("DEEP_FREEZER", data.get(i).getDeep_freezer());
						values.put("FREEZER_TYPE", data.get(i).getFreezer_type());
						values.put("STATUS", data.get(i).getStatus());
						values.put("REMARK", data.get(i).getRemark());
						
						db.insert(CommonString.TABLE_DEEPFREEZER_DATA, null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert DeepFreezer Data ",
							ex.toString());
				}

			}
			
			
	//Insert Facing Competitor Data
			
			public void insertFscingCompetitorData(ArrayList<FacingCompetionCompanyGetterSetter> data,String store_cd) {

				db.delete(CommonString.TABLE_FACING_COMPETITOR_DATA, "STORE_CD" + "='" + store_cd + "'", null);
				ContentValues values = new ContentValues();

				try {

					for (int i = 0; i < data.size(); i++) {

						values.put("COMPANY_CD", Integer.parseInt(data.get(i).getCompany_cd()));
						values.put("COMPANY", data.get(i).getCompany());
						values.put("FACING", data.get(i).getFacing());
						//values.put("STORE_DF", data.get(i).getStoredf());
						values.put("STORE_CD", store_cd);
						
						db.insert(CommonString.TABLE_FACING_COMPETITOR_DATA, null, values);

					}

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Facing Competition Data ",
							ex.toString());
				}

			}
			
//get DeepFreezerType Data
			
			public ArrayList<DeepFreezerTypeGetterSetter> getDFTypeData(String dftype, String storecd) {

				Log.d("FetchingDeepFreezerType--------------->Start<------------",
						"------------------");
				ArrayList<DeepFreezerTypeGetterSetter> list = new ArrayList<DeepFreezerTypeGetterSetter>();
				Cursor dbcursor = null;

				try {
					dbcursor = db.rawQuery("SELECT * from DEEPFREEZER_DATA where FREEZER_TYPE = '" + dftype + "'  AND STORE_CD = '" + storecd + "'" 
							, null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							DeepFreezerTypeGetterSetter df = new DeepFreezerTypeGetterSetter();


							df.setDeep_freezer(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("DEEP_FREEZER")));
							df.setFid(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("FID")));
							df.setFreezer_type(dftype);
							df.setRemark(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("REMARK")));
							df.setStatus(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STATUS")));

							list.add(df);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Deepfreezer!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingJCP data---------------------->Stop<-----------",
						"-------------------");
				return list;

			}
			
			
			//check if table is empty
			public boolean isDeepfreezerDataFilled(String storeId,String dftype){
				boolean filled=false;
				
				Cursor dbcursor = null;
				
				try {

					dbcursor = db
							.rawQuery(
									"SELECT * FROM DEEPFREEZER_DATA WHERE FREEZER_TYPE = '" + dftype + "'  AND STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						int icount = dbcursor.getInt(0);
						dbcursor.close();
						if(icount>0){
							filled=true;
						}
						else{
							filled=false;
						}
						
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return filled;
				}
				
				return filled;
			}
			
			
//get DeepFreezerType Data
			
			public ArrayList<DeepFreezerTypeGetterSetter> getDFTypeUploadData(String store_cd) {

				Log.d("FetchingDeepFreezerType--------------->Start<------------",
						"------------------");
				ArrayList<DeepFreezerTypeGetterSetter> list = new ArrayList<DeepFreezerTypeGetterSetter>();
				Cursor dbcursor = null;

				try {
					dbcursor = db.rawQuery("SELECT * from DEEPFREEZER_DATA where STORE_CD = '" + store_cd + "'" 
							, null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							DeepFreezerTypeGetterSetter df = new DeepFreezerTypeGetterSetter();


							df.setDeep_freezer(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("DEEP_FREEZER")));
							df.setFid(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("FID")));
							df.setFreezer_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("FREEZER_TYPE")));
							df.setRemark(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("REMARK")));
							df.setStatus(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STATUS")));

							list.add(df);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Deepfreezer!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingJCP data---------------------->Stop<-----------",
						"-------------------");
				return list;

			}
			
			
//get Facing Competitor Data
			
			public ArrayList<FacingCompetionCompanyGetterSetter> getFacingCompetitorData(String store_cd) {

				Log.d("Fetching facing competitor--------------->Start<------------",
						"------------------");
				ArrayList<FacingCompetionCompanyGetterSetter> list = new ArrayList<FacingCompetionCompanyGetterSetter>();
				Cursor dbcursor = null;

				try {
					dbcursor = db.rawQuery("SELECT * from FACING_COMPETITOR_DATA where STORE_CD = '" + store_cd + "'" 
							, null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							FacingCompetionCompanyGetterSetter fc = new FacingCompetionCompanyGetterSetter();


							fc.setCompany(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("COMPANY")));
							fc.setCompany_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("COMPANY_CD")));
							fc.setFacing(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("FACING")));
						/*	fc.setStoredf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STORE_DF")));*/
							

							list.add(fc);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Facing Competitor!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching facing Competitor---------------------->Stop<-----------",
						"-------------------");
				return list;

			}	
			
			
			
//get Facing Competitor Data
			
			public ArrayList<PerformanceGetterSetter> getPerformrmance(String store_cd) {

				Log.d("Fetching facing competitor--------------->Start<------------",
						"------------------");
				ArrayList<PerformanceGetterSetter> list = new ArrayList<PerformanceGetterSetter>();
				Cursor dbcursor = null;

				try {
					dbcursor = db.rawQuery("SELECT * from MY_PERFORMANCE where STORE_CD = '" + store_cd + "'" 
							, null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							PerformanceGetterSetter fc = new PerformanceGetterSetter();


							fc.setMonthly_target(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("TARGET")));
							fc.setMtd_sales(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SALE")));
							fc.setAchievement(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("ACH")));
							
							list.add(fc);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Facing Competitor!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching facing Competitor---------------------->Stop<-----------",
						"-------------------");
				return list;

			}	
			
			
			//check if table is empty
			public boolean isCompetitionDataFilled(String storeId){
				boolean filled=false;
				
				Cursor dbcursor = null;
				
				try {

					dbcursor = db
							.rawQuery(
									"SELECT * FROM FACING_COMPETITOR_DATA WHERE STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						int icount = dbcursor.getInt(0);
						dbcursor.close();
						if(icount>0){
							filled=true;
						}
						else{
							filled=false;
						}
						
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return filled;
				}
				
				return filled;
			}
			
			
			//Stock data
			
			public ArrayList<StockNewGetterSetter> getStockAvailabilityData(String store_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.BRAND_CD, SD.BRAND FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE CD.STORE_CD ='"+store_cd+"' ORDER BY SD.BRAND_SEQUENCE"
											, null);
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();

							/*sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));*/
						/*	sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));*/

							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));

							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));
							
							/*sb.setCategory_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_CD")));

							sb.setCategory(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY")));
							
							sb.setMrp(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MRP")));
							
							sb.setSku_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_SEQUENCE")));
							
							sb.setCategory_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_SEQUENCE")));
							
							sb.setBrand_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_SEQUENCE")));*/
							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching opening stock---------------------->Stop<-----------",
						"-------------------");
				return list;
			}


	//Header with cam data

	public ArrayList<StockNewGetterSetter> getHeaderStockImageData(String store_cd, String visit_date) {
		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
		Cursor dbcursor = null;

		try {

			dbcursor = db
					.rawQuery(
							"SELECT DISTINCT BRAND_CD, BRAND, IMAGE_STK FROM STOCK_IMAGE WHERE STORE_CD ='"+store_cd+"'AND VISIT_DATE  ='"+ visit_date +"'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					StockNewGetterSetter sb = new StockNewGetterSetter();

							/*sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));*/
						/*	sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));*/

					sb.setBrand_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND_CD")));

					sb.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND")));

					sb.setImg_cam(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("IMAGE_STK")));

							/*sb.setCategory_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_CD")));

							sb.setCategory(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY")));

							sb.setMrp(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MRP")));

							sb.setSku_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_SEQUENCE")));

							sb.setCategory_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_SEQUENCE")));

							sb.setBrand_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_SEQUENCE")));*/

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Header!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("Fetching Header stock---------------------->Stop<-----------",
				"-------------------");
		return list;
	}

			
	//Stock data
			
			public ArrayList<StockNewGetterSetter> getFoodStoreAvailabilityData(String store_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					String food="Food";
					
					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.BRAND_CD, SD.BRAND FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE CD.STORE_CD ='"+store_cd+"' AND SD.CATEGORY_TYPE ='"+food+"' ORDER BY CD.BRAND_SEQUENCE"
											, null);
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();

							/*sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));*/
						/*	sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));*/

							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));

							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));
							
							/*sb.setCategory_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_CD")));

							sb.setCategory(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY")));
							
							sb.setMrp(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MRP")));
							
							sb.setSku_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_SEQUENCE")));
							
							sb.setCategory_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_SEQUENCE")));
							
							sb.setBrand_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_SEQUENCE")));*/
							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching opening stock---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			
	//Stock Sku data
			
			public ArrayList<StockNewGetterSetter> getStockSkuData(String brand_cd,String store_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE CD.STORE_CD= '" +  store_cd + "' AND SD.BRAND_CD ='"+brand_cd+"' ORDER BY SD.SKU_SEQUENCE"
											, null);
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();

							
							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							
							/*sb.setCategory_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE")));*/

							sb.setOpenning_total_stock("");
							sb.setOpening_facing("");
							sb.setStock_under45days("");
							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching opening stock---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			
			
			//Food Stock data
					
					public ArrayList<FoodStoreInsertDataGetterSetter> getFoodSkuData(String brand_cd,String store_cd) {
						Log.d("FetchingStoredata--------------->Start<------------",
								"------------------");
						ArrayList<FoodStoreInsertDataGetterSetter> list = new ArrayList<FoodStoreInsertDataGetterSetter>();
						Cursor dbcursor = null;

						try {

							String food="Food";
							dbcursor = db
									.rawQuery(
											"SELECT SD.SKU_CD, SD.SKU, SD.PACKING FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD ='"+brand_cd+"' AND CD.STORE_CD ='"+store_cd+"' AND SD.CATEGORY_TYPE ='"+food+"' ORDER BY CD.SKU_SEQUENCE"
													, null);
							
						//	"SELECT  SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD =2 AND SD.CATEGORY_TYPE ='Food' AND CD.STORE_CD = 392 ORDER BY CD.SKU_SEQUENCE"

							//WHERE SD.BRAND_CD ='"+brand_cd+"' AND SD.CATEGORY_TYPE ='"+"Food"+"' AND CD.STORE_CD ='"+store_cd+"'
							
							if (dbcursor != null) {
								dbcursor.moveToFirst();
								while (!dbcursor.isAfterLast()) {
									FoodStoreInsertDataGetterSetter sb = new FoodStoreInsertDataGetterSetter();

									
									sb.setSku_cd(dbcursor.getString(dbcursor
											.getColumnIndexOrThrow("SKU_CD")));

									sb.setSku(dbcursor.getString(dbcursor
											.getColumnIndexOrThrow("SKU")));
									
									sb.setActual_listed("NO");
									sb.setAs_per_meccain("");
									sb.setPacking_size(dbcursor.getString(dbcursor
											.getColumnIndexOrThrow("PACKING")));
									sb.setMccain_df("");
									sb.setStore_df("");
									sb.setMtd_sales("");
									
									list.add(sb);
									dbcursor.moveToNext();
								}
								dbcursor.close();
								return list;
							}

						} catch (Exception e) {
							Log.d("Exception when fetching opening stock!!!!!!!!!!!",
									e.toString());
							return list;
						}

						Log.d("Fetching opening stock---------------------->Stop<-----------",
								"-------------------");
						return list;
					}
			
			
//Stock Sku Closing data
			
			public ArrayList<StockNewGetterSetter> getStockSkuClosingData(String brand_cd, String store_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {
					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE CD.STORE_CD= '" +  store_cd + "' AND SD.BRAND_CD ='"+brand_cd+"' ORDER BY SD.SKU_SEQUENCE"
											, null);
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();

							
							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							
							sb.setClosing_stock("");

							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching opening stock---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			
			
			
			
//Stock Midday Sku data
			
			public ArrayList<StockNewGetterSetter> getStockSkuMiddayData(String brand_cd, String store_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE CD.STORE_CD= '" +  store_cd + "' AND SD.BRAND_CD ='"+brand_cd+"' ORDER BY SD.SKU_SEQUENCE"
											, null);
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();

							
							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							
							sb.setTotal_mid_stock_received("");
							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching opening stock---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			public ArrayList<MiddayStockInsertData> getStockSkuMiddayZeroData(String brand_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<MiddayStockInsertData> list = new ArrayList<MiddayStockInsertData>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD ='"+brand_cd+"' ORDER BY CD.SKU_SEQUENCE"
											, null);
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							MiddayStockInsertData sb = new MiddayStockInsertData();

							
							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							
							sb.setMidday_stock("0");
							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching opening stock---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
//Promotion brand data
			
			
			public ArrayList<StockNewGetterSetter> getPromotionBrandData(String store_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.BRAND_CD, SD.BRAND FROM MAPPING_PROMOTION CD INNER JOIN SKU_MASTER SD ON CD.BRAND_CD = SD.BRAND_CD WHERE CD.STORE_CD ='"+store_cd+"' ORDER BY SD.BRAND_SEQUENCE"
											, null);
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();


							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));

							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));
							
							
							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching opening stock---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
//Promotion brand data
			
			
			public ArrayList<AssetInsertdataGetterSetter> getAssetBrandData(String store_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT BRAND_CD, BRAND FROM SKU_MASTER WHERE BRAND_CD IN( SELECT DISTINCT BRAND_ID FROM MAPPING_ASSET WHERE STORE_CD ='"+store_cd+"' )"
											, null);
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();


							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));

							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));
							
							
							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching Asset brand---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
// get promotion Sku data
			public ArrayList<PromotionInsertDataGetterSetter> getPromotionSkuData(String brand_cd, String store_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<PromotionInsertDataGetterSetter> list = new ArrayList<PromotionInsertDataGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT CD.PROMOTION, CD.PID FROM MAPPING_PROMOTION CD INNER JOIN SKU_MASTER SD ON CD.BRAND_CD = SD.BRAND_CD WHERE SD.BRAND_CD ='"+brand_cd+"' AND CD.STORE_CD ='"+store_cd+"'"
											, null);
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							PromotionInsertDataGetterSetter sb = new PromotionInsertDataGetterSetter();

							
							/*sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));*/
							
							sb.setPromotion_txt(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PROMOTION")));
							sb.setPresent("NO");
							sb.setRemark("");
							sb.setPid(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PID")));
							sb.setImg("");

							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching opening stock---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
		
			/*// get Asset data
						public ArrayList<PromotionInsertDataGetterSetter> getAssetSkuData(String brand_cd) {
							Log.d("FetchingStoredata--------------->Start<------------",
									"------------------");
							ArrayList<PromotionInsertDataGetterSetter> list = new ArrayList<PromotionInsertDataGetterSetter>();
							Cursor dbcursor = null;

							try {

								dbcursor = db
										.rawQuery(
												"SELECT DISTINCT SD.SKU_CD, SD.SKU, CD.PROMOTION,CD.CATEGORY_TYPE FROM MAPPING_PROMOTION CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD ='"+brand_cd+"' ORDER BY CD.SKU_SEQUENCE"
														, null);
								

								if (dbcursor != null) {
									dbcursor.moveToFirst();
									while (!dbcursor.isAfterLast()) {
										PromotionInsertDataGetterSetter sb = new PromotionInsertDataGetterSetter();

										
										sb.setSku_cd(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("SKU_CD")));

										sb.setSku(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("SKU")));
										
										sb.setPromotion_txt(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("PROMOTION")));
										sb.setPresent("NO");
										sb.setRemark("remark");
										sb.setCategory_type(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("CATEGORY_TYPE")));
										
										list.add(sb);
										dbcursor.moveToNext();
									}
									dbcursor.close();
									return list;
								}

							} catch (Exception e) {
								Log.d("Exception when fetching opening stock!!!!!!!!!!!",
										e.toString());
								return list;
							}

							Log.d("Fetching opening stock---------------------->Stop<-----------",
									"-------------------");
							return list;
						}
*/
			// get Asset data
						public ArrayList<AssetInsertdataGetterSetter> getAssetData(String brand_cd,String store_cd) {
							Log.d("FetchingAssetdata--------------->Start<------------",
									"------------------");
							ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
							Cursor dbcursor = null;

							try {

								dbcursor = db
										.rawQuery(
												"SELECT AM.ASSET_CD, AM.ASSET, M.BRAND_ID FROM MAPPING_ASSET M INNER JOIN ASSET_MASTER AM ON M.ASSET_CD = AM.ASSET_CD WHERE M.STORE_CD ='" + store_cd + "' AND M.BRAND_ID ='" + brand_cd + "'"
												, null);
							
								if (dbcursor != null) {
									dbcursor.moveToFirst();
									while (!dbcursor.isAfterLast()) {
										AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();

										
										sb.setAsset(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("ASSET")));

										sb.setAsset_cd(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("ASSET_CD")));
										
									
										sb.setPresent("NO");
										sb.setRemark("");
										sb.setImg("");
										
										list.add(sb);
										dbcursor.moveToNext();
									}
									dbcursor.close();
									return list;
								}

							} catch (Exception e) {
								Log.d("Exception when fetching Asset!!!!!!!!!!!",
										e.toString());
								return list;
							}

							Log.d("Fetching asset data---------------------->Stop<-----------",
									"-------------------");
							return list;
						}


	// get Asset data
	public ArrayList<AssetInsertdataGetterSetter> getAllAssetData() {
		Log.d("FetchingAssetdata--------------->Start<------------",
				"------------------");
		ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
		Cursor dbcursor = null;

		try {

			dbcursor = db
					.rawQuery(
							"SELECT * FROM ASSET_MASTER"
							, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();


					sb.setAsset(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET")));

					sb.setAsset_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET_CD")));


					sb.setPresent("NO");
					sb.setRemark("");

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Asset!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("Fetching asset data---------------------->Stop<-----------",
				"-------------------");
		return list;
	}
					
						
						// get Asset data
						public ArrayList<NonWorkingReasonGetterSetter> getNonWorkingData() {
							Log.d("FetchingAssetdata--------------->Start<------------",
									"------------------");
							ArrayList<NonWorkingReasonGetterSetter> list = new ArrayList<NonWorkingReasonGetterSetter>();
							Cursor dbcursor = null;

							try {

								dbcursor = db
										.rawQuery(
												"SELECT * FROM NON_WORKING_REASON"
														, null);
							
								if (dbcursor != null) {
									dbcursor.moveToFirst();
									while (!dbcursor.isAfterLast()) {
										NonWorkingReasonGetterSetter sb = new NonWorkingReasonGetterSetter();

										
										sb.setReason_cd(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("REASON_CD")));

										sb.setReason(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("REASON")));

										sb.setEntry_allow(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("ENTRY_ALLOW")));


										list.add(sb);
										dbcursor.moveToNext();
									}
									dbcursor.close();
									return list;
								}

							} catch (Exception e) {
								Log.d("Exception when fetching Non working!!!!!!!!!!!",
										e.toString());
								return list;
							}

							Log.d("Fetching non working data---------------------->Stop<-----------",
									"-------------------");
							return list;
						}
					
			
			
	//Insert Opening Stock Data
			
		/*	public void insertOpeningStockData(StockNewGetterSetter data,String sku_cd) {

				db.delete(CommonString.TABLE_OPENING_STOCK_DATA, "SKU_CD" + "='" + sku_cd + "'", null);
				ContentValues values = new ContentValues();

				try {

					
						values.put("SKU_CD", Integer.parseInt(data.getSku_cd()));
						values.put("AS_PER_MECCAN", data.getAs_per_meccain());
						values.put("ACTUAL_LISTED", data.getActual_listed());
						values.put("OPENING_STOCK_COLD_ROOM", data.getOpen_stock_cold_room());
						values.put("OPENING_STOCK_MCCAIN_DF", data.getOpen_stock_mccaindf());
						values.put("TOTAL_FACING_MCCAIN_DF", data.getTotalfacing_mccaindf());
						values.put("OPENING_STOCK_STORE_DF", data.getOpen_stock_store_df());
						values.put("TOTAL_FACING_STORE_DF", data.getTotal_facing_storedf());
						values.put("MATERIAL_WELLNESS", data.getMaterial_wellness());
						
						db.insert(CommonString.TABLE_OPENING_STOCK_DATA, null, values);

					

				} catch (Exception ex) {
					Log.d("Database Exception while Insert OPENING STOCK Data ",
							ex.toString());
				}

			}*/
			
			
//Insert Opening Data with Brand
			
			public void InsertOpeningStocklistData( String storeid,
					HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data,List<StockNewGetterSetter> save_listDataHeader) {
				
				ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {

					db.beginTransaction();
					for (int i = 0; i < save_listDataHeader.size(); i++) {

						values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_OPENINGHEADER_DATA,
								null, values);
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							values1.put("Common_Id", (int) l );
							
							values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_cd());
							values1.put("BRAND", save_listDataHeader.get(i).getBrand());
							
							values1.put("STORE_CD", storeid);
							values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j).getSku());
							values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getSku_cd()));
							values1.put("OPENING_TOTAL_STOCK", data.get(save_listDataHeader.get(i)).get(j).getOpenning_total_stock());
							values1.put("FACING", data.get(save_listDataHeader.get(i)).get(j).getOpening_facing());
							values1.put("STOCK_UNDER_DAYS", data.get(save_listDataHeader.get(i)).get(j).getStock_under45days());

							db.insert(CommonString.TABLE_STOCK_DATA, null, values1);

						}
					}
					db.setTransactionSuccessful();
					db.endTransaction();
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}

	//Insert Header Data with img

	public void InsertHeaderOpeningStocklistData( String storeid,String visit_date,List<StockNewGetterSetter> save_listDataHeader) {

		ContentValues values = new ContentValues();
//		ContentValues values1 = new ContentValues();

		try {

			db.beginTransaction();
			for (int i = 0; i < save_listDataHeader.size(); i++) {

				values.put("STORE_CD", storeid);

				values.put("BRAND_CD", save_listDataHeader.get(i)
						.getBrand_cd());
				values.put("BRAND", save_listDataHeader
						.get(i).getBrand());
				values.put("IMAGE_STK", save_listDataHeader
						.get(i).getImg_cam());
				values.put("VISIT_DATE", visit_date);

				long l = db.insert(CommonString.TABLE_STOCK_IMAGE,
						null, values);

			}
			db.setTransactionSuccessful();
			db.endTransaction();
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Header Data ",
					ex.toString());
		}

	}
			
//Update Opening Data with Brand
			
			public void UpdateOpeningStocklistData( String storeid,
					HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data,List<StockNewGetterSetter> save_listDataHeader) {
				
			//	ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {
					
					ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();
					
					list=getHeaderStock(storeid);
					
					db.beginTransaction();
					
					for (int i = 0; i < list.size(); i++) {

						/*values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_OPENINGHEADER_DATA,
								null, values);*/
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							//values1.put("Common_Id", Integer.parseInt(list.get(i).getKeyId()));
							//values1.put("STORE_CD", storeid);
							//values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
							//		.getSku_cd()));

							values1.put("OPENING_TOTAL_STOCK", data.get(save_listDataHeader.get(i)).get(j).getOpenning_total_stock());
							values1.put("FACING", data.get(save_listDataHeader.get(i)).get(j).getOpening_facing());
							values1.put("STOCK_UNDER_DAYS", data.get(save_listDataHeader.get(i)).get(j).getStock_under45days());

							//db.insert(CommonString.TABLE_STOCK_DATA, null, values1);
							db.update(CommonString.TABLE_STOCK_DATA, values1, "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD " + "='" + Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd()) + "'", null);

						}
					}
					db.setTransactionSuccessful();
					db.endTransaction();
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}

	//Update Opening Data with Brand

	public void UpdateHeaderOpeningStocklistData( String storeid, String visit_date,List<StockNewGetterSetter> save_listDataHeader) {

		ContentValues values = new ContentValues();
		//ContentValues values1 = new ContentValues();

		try {


			db.beginTransaction();

			for (int i = 0; i < save_listDataHeader.size(); i++) {

						/*values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());*/

				values.put("IMAGE_STK", save_listDataHeader
						.get(i).getImg_cam());

				db.update(CommonString.TABLE_STOCK_IMAGE, values, "STORE_CD" + "='" + storeid + "' AND BRAND_CD " + "='" + Integer.parseInt(save_listDataHeader.get(i).getBrand_cd()) + "' AND VISIT_DATE  ='"+ visit_date +"'", null);

			}
			db.setTransactionSuccessful();
			db.endTransaction();
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.toString());
		}

	}
			
			
//Insert Closing Data with Brand
			
			public void InsertClosingStocklistData( String storeid,
					HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data,List<StockNewGetterSetter> save_listDataHeader) {
				
				ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {

					db.beginTransaction();
					for (int i = 0; i < save_listDataHeader.size(); i++) {

						values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_OPENINGHEADER_DATA,
								null, values);
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							values1.put("Common_Id", (int)l);
							
							values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_cd());
							values1.put("BRAND", save_listDataHeader.get(i).getBrand());
							
							values1.put("STORE_CD", storeid);
							values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j).getSku());
							values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getSku_cd()));
							values1.put("CLOSING_STOCK", data.get(save_listDataHeader.get(i)).get(j).getClosing_stock());
							
							db.insert(CommonString.TABLE_STOCK_DATA, null, values1);

						}
					}
					db.setTransactionSuccessful();
					db.endTransaction();
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}
			
//Update Closing Data with Brand
			
			public void UpdateClosingStocklistData( String storeid,
					HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data,List<StockNewGetterSetter> save_listDataHeader) {
				
				//ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {

					ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();
					
					list=getHeaderStock(storeid);
					
					db.beginTransaction();
					
					for (int i = 0; i < list.size(); i++) {

					/*	values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_OPENINGHEADER_CLOSING_DATA,
								null, values);*/
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

						/*	values1.put("Common_Id", (int)l);
							values1.put("STORE_CD", storeid);
							values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getSku_cd()));*/
							values1.put("CLOSING_STOCK", data.get(save_listDataHeader.get(i)).get(j).getClosing_stock());

							
							
							//db.insert(CommonString.TABLE_STOCK_DATA, null, values1);
							db.update(CommonString.TABLE_STOCK_DATA, values1, "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD " + "='" + Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd()) + "'", null);
						}
					}
					db.setTransactionSuccessful();
					db.endTransaction();
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}
			

//Insert Midday Data with Brand
			
			public void InsertMiddayStocklistData( String storeid,
					HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data,List<StockNewGetterSetter> save_listDataHeader) {
				
				ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {

					db.beginTransaction();
					for (int i = 0; i < save_listDataHeader.size(); i++) {

						values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_OPENINGHEADER_DATA,
								null, values);
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							values1.put("Common_Id", (int)l);
							
							values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_cd());
							values1.put("BRAND", save_listDataHeader.get(i).getBrand());
							
							values1.put("STORE_CD", storeid);
							values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j).getSku());
							values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getSku_cd()));
							values1.put("MIDDAY_TOTAL_STOCK", data.get(save_listDataHeader.get(i)).get(j).getTotal_mid_stock_received());
							
							db.insert(CommonString.TABLE_STOCK_DATA, null, values1);

						}
					}
					db.setTransactionSuccessful();
					db.endTransaction();
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}
			
//Update Midday Data with Brand
			
			public void UpdateMiddayStocklistData( String storeid,
					HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data,List<StockNewGetterSetter> save_listDataHeader) {
				
				//ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {

					ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();
					
					list=getHeaderStock(storeid);
					
					//db.beginTransaction();
					for (int i = 0; i < list.size(); i++) {

						/*values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_HEADER_MIDDAY_DATA,
								null, values);*/
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							/*values1.put("Common_Id", (int)l);
							values1.put("STORE_CD", storeid);
							values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getSku_cd()));*/
							values1.put("MIDDAY_TOTAL_STOCK", data.get(save_listDataHeader.get(i)).get(j).getTotal_mid_stock_received());
							
							//db.insert(CommonString.TABLE_STOCK_DATA, null, values1);
							db.update(CommonString.TABLE_STOCK_DATA, values1, "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD "+"='" + Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd())+ "'", null);

						}
					}
					/*db.setTransactionSuccessful();
					db.endTransaction();*/
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}
			
			
			
//Insert Food Store List data
			
			public void InsertFoodStorelistData( String storeid,
					HashMap<StockNewGetterSetter, List<FoodStoreInsertDataGetterSetter>> data,List<StockNewGetterSetter> save_listDataHeader) {
				
				ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {

					db.beginTransaction();
					for (int i = 0; i < save_listDataHeader.size(); i++) {

						values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_HEADER_FOOD_STORE_DATA,
								null, values);
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							values1.put("Common_Id", (int)l);
							values1.put("STORE_CD", storeid);
							values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getSku_cd()));
							values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j)
									.getSku());
							values1.put("ACTUAL_LISTED", data.get(save_listDataHeader.get(i)).get(j).getActual_listed());
							values1.put("AS_PER_MCCAIN", data.get(save_listDataHeader.get(i)).get(j).getAs_per_meccain());
							values1.put("MCCAIN_DF", data.get(save_listDataHeader.get(i)).get(j).getMccain_df());
							values1.put("STORE_DF", data.get(save_listDataHeader.get(i)).get(j).getStore_df());
							values1.put("PACKING_SIZE", data.get(save_listDataHeader.get(i)).get(j).getPacking_size());
							values1.put("MTD_SALES", data.get(save_listDataHeader.get(i)).get(j).getMtd_sales());
							
							db.insert(CommonString.TABLE_FOOD_STORE_DATA, null, values1);

						}
					}
					db.setTransactionSuccessful();
					db.endTransaction();
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}
			
//Insert Promotion Data with Brand
			
			public void InsertPromotionData( String storeid,
					HashMap<StockNewGetterSetter, List<PromotionInsertDataGetterSetter>> data,List<StockNewGetterSetter> save_listDataHeader) {
				
				ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {

					db.beginTransaction();
					for (int i = 0; i < save_listDataHeader.size(); i++) {

						values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_PROMOTION_HEADER_DATA,
								null, values);
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							values1.put("Common_Id", (int)l);
							values1.put("STORE_CD", storeid);
							values1.put("PID", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getPid()));
							values1.put("IMAGE", data.get(save_listDataHeader.get(i)).get(j).getImg());
							/*values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j)
									.getSku());*/
							values1.put("PROMOTION", data.get(save_listDataHeader.get(i)).get(j).getPromotion_txt());
							//values1.put("CATEGORY_TYPE", data.get(save_listDataHeader.get(i)).get(j).getCategory_type());
							values1.put("REMARK", data.get(save_listDataHeader.get(i)).get(j).getRemark());
							values1.put("PRESENT", data.get(save_listDataHeader.get(i)).get(j).getPresent());
							
							db.insert(CommonString.TABLE_PROMOTION_DATA, null, values1);

						}
					}
					db.setTransactionSuccessful();
					db.endTransaction();
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}
			
	
//Insert Asset Data with Brand
			
			public void InsertAssetData( String storeid,
					HashMap<AssetInsertdataGetterSetter, List<AssetInsertdataGetterSetter>> data,List<AssetInsertdataGetterSetter> save_listDataHeader) {
				
				ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {

					db.beginTransaction();
					for (int i = 0; i < save_listDataHeader.size(); i++) {

						values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_ASSET_HEADER_DATA,
								null, values);
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							values1.put("Common_Id", (int)l);
							values1.put("STORE_CD", storeid);
							values1.put("ASSET_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getAsset_cd()));
							values1.put("ASSET", data.get(save_listDataHeader.get(i)).get(j).getAsset());
							values1.put("REMARK", data.get(save_listDataHeader.get(i)).get(j).getRemark());
							values1.put("PRESENT", data.get(save_listDataHeader.get(i)).get(j).getPresent());
							values1.put("IMAGE", data.get(save_listDataHeader.get(i)).get(j).getImg());
							
							db.insert(CommonString.TABLE_ASSET_DATA, null, values1);

						}
					}
					db.setTransactionSuccessful();
					db.endTransaction();
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}
			
			
			//Get Asset Upload Data 
			
			public ArrayList<AssetInsertdataGetterSetter> getAssetDataFromdatabase(String storeId, String brand_cd ) {
				Log.d("FetchingAssetuploaddata--------------->Start<------------",
						"------------------");
				ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.ASSET_CD, SD.ASSET, SD.PRESENT, SD.REMARK, SD.IMAGE, CD.BRAND_CD, CD.BRAND " +
									"FROM openingHeader_Asset_data CD INNER JOIN ASSET_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "' AND CD.BRAND_CD = '"+ brand_cd +"'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();

							sb.setAsset_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("ASSET_CD")));
							
							sb.setAsset(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("ASSET")));

							sb.setPresent(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PRESENT")));
							sb.setRemark(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("REMARK")));
							sb.setImg(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("IMAGE")));
							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));


							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingStoredat---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			//check if table is empty
			public boolean isAssetDataFilled(String storeId){
				boolean filled=false;
				
				Cursor dbcursor = null;
				
				try {

					dbcursor = db
							.rawQuery(
									"SELECT * FROM ASSET_DATA WHERE STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						int icount = dbcursor.getInt(0);
						dbcursor.close();
						if(icount>0){
							filled=true;
						}
						else{
							filled=false;
						}
						
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return filled;
				}
				
				return filled;
			}
			
			
//Get Asset Upload Data 
			
			public ArrayList<AssetInsertdataGetterSetter> getAssetUpload(String storeId) {
				Log.d("FetchingAssetuploaddata--------------->Start<------------",
						"------------------");
				ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.ASSET_CD,SD.ASSET,SD.PRESENT,SD.REMARK, SD.IMAGE,CD.BRAND_CD,CD.BRAND " +
									"FROM openingHeader_Asset_data CD INNER JOIN ASSET_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();

							sb.setAsset_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("ASSET_CD")));
							
							sb.setAsset(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("ASSET")));
							sb.setImg(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("IMAGE")));

							sb.setPresent(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PRESENT")));
							sb.setRemark(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("REMARK")));
							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));


							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingStoredat---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			//Get Promotion Upload Data 
			
			public ArrayList<PromotionInsertDataGetterSetter> getPromotionUpload(String storeId) {
				Log.d("FetchingPromotionuploaddata--------------->Start<------------",
						"------------------");
				ArrayList<PromotionInsertDataGetterSetter> list = new ArrayList<PromotionInsertDataGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.PROMOTION, SD.PID, SD.PRESENT, SD.REMARK, SD.IMAGE, CD.BRAND_CD, CD.BRAND " +
									"FROM openingHeader_Promotion_data CD INNER JOIN PROMOTION_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							PromotionInsertDataGetterSetter sb = new PromotionInsertDataGetterSetter();

							/*sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));*/
							
							sb.setPromotion_txt(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PROMOTION")));
							sb.setPid(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PID")));

							/*sb.setCategory_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE")));*/
							sb.setPresent(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PRESENT")));
							sb.setRemark(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("REMARK")));
							sb.setImg(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("IMAGE")));
							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));
							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));


							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingStoredat---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
	//Get Promotion Upload Data 
			
			public ArrayList<PromotionInsertDataGetterSetter> getPromotionDataFromDatabase(String storeId, String brand_cd) {
				Log.d("FetchingPromotionuploaddata--------------->Start<------------",
						"------------------");
				ArrayList<PromotionInsertDataGetterSetter> list = new ArrayList<PromotionInsertDataGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.PID, SD.IMAGE, SD.PROMOTION,SD.PRESENT,SD.REMARK, CD.BRAND_CD,CD.BRAND " +
									"FROM openingHeader_Promotion_data CD INNER JOIN PROMOTION_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "' AND CD.BRAND_CD = '"+ brand_cd +"'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							PromotionInsertDataGetterSetter sb = new PromotionInsertDataGetterSetter();

						/*	sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));
							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							*/
							sb.setPid(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PID")));
							sb.setPromotion_txt(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PROMOTION")));
							sb.setImg(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("IMAGE")));

							/*sb.setCategory_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE")));*/
							sb.setPresent(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PRESENT")));
							sb.setRemark(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("REMARK")));
							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));
							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));


							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingStoredat---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			//check if table is empty
			public boolean isPromotionDataFilled(String storeId){
				boolean filled=false;
				
				Cursor dbcursor = null;
				
				try {

					dbcursor = db
							.rawQuery(
									"SELECT * FROM PROMOTION_DATA WHERE STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						int icount = dbcursor.getInt(0);
						dbcursor.close();
						if(icount>0){
							filled=true;
						}
						else{
							filled=false;
						}
						
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return filled;
				}
				
				return filled;
			}
			
//Get Promotion Upload Data 
			
			public ArrayList<FoodStoreInsertDataGetterSetter> getFoodStoreUpload(String storeId) {
				Log.d("FetchingPromotionuploaddata--------------->Start<------------",
						"------------------");
				ArrayList<FoodStoreInsertDataGetterSetter> list = new ArrayList<FoodStoreInsertDataGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.SKU_CD,SD.AS_PER_MCCAIN,SD.ACTUAL_LISTED,SD.MCCAIN_DF,SD.STORE_DF, SD.MTD_SALES, SD.PACKING_SIZE, CD.BRAND_CD,CD.BRAND " +
									"FROM openingHeader_FOOD_STORE_data CD INNER JOIN FOOD_STORE_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							FoodStoreInsertDataGetterSetter sb = new FoodStoreInsertDataGetterSetter();

							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));
							
							/*sb.setAs_per_meccain(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("AS_PER_MCCAIN")));*/

							sb.setActual_listed(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("ACTUAL_LISTED")));
							sb.setMccain_df(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MCCAIN_DF")));
							sb.setStore_df(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STORE_DF")));
							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));
							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));
							/*sb.setMtd_sales(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MTD_SALES")));*/
							
							if(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MTD_SALES"))==null|| dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MTD_SALES")).equals("")){
								
								sb.setMtd_sales("0");
								
							}else{
								sb.setMtd_sales(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MTD_SALES")));
								
							}
							
							sb.setPacking_size(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PACKING_SIZE")));


							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingFoodStoredat---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
//Get Promotion Database Data 
			
			public ArrayList<FoodStoreInsertDataGetterSetter> getFoodStoreDataFromDatabase(String storeId, String brand_cd ) {
				Log.d("FetchingPromotionuploaddata--------------->Start<------------",
						"------------------");
				ArrayList<FoodStoreInsertDataGetterSetter> list = new ArrayList<FoodStoreInsertDataGetterSetter>();
				Cursor dbcursor = null;
				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.SKU_CD, SD.SKU,SD.AS_PER_MCCAIN,SD.ACTUAL_LISTED,SD.MCCAIN_DF,SD.STORE_DF, SD.MTD_SALES, SD.PACKING_SIZE, CD.BRAND_CD,CD.BRAND " +
									"FROM openingHeader_FOOD_STORE_data CD INNER JOIN FOOD_STORE_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "' AND CD.BRAND_CD = '"+ brand_cd +"'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							FoodStoreInsertDataGetterSetter sb = new FoodStoreInsertDataGetterSetter();

							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));
							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							
							sb.setAs_per_meccain(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("AS_PER_MCCAIN")));

							sb.setActual_listed(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("ACTUAL_LISTED")));
							sb.setMccain_df(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MCCAIN_DF")));
							sb.setStore_df(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STORE_DF")));
							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));
							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));
							sb.setMtd_sales(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MTD_SALES")));
							sb.setPacking_size(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PACKING_SIZE")));

							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingFoodStoredat---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			//check if table is empty
			public boolean isFoodDataFilled(String storeId){
				boolean filled=false;
				
				Cursor dbcursor = null;
				
				try {

					dbcursor = db
							.rawQuery(
									"SELECT * FROM FOOD_STORE_DATA WHERE STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						int icount = dbcursor.getInt(0);
						dbcursor.close();
						if(icount>0){
							filled=true;
						}
						else{
							filled=false;
						}
						
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return filled;
				}
				
				return filled;
			}
			
			
//Get Stock Upload Data 
			
			public ArrayList<StockNewGetterSetter> getStockUpload(String storeId) {
				Log.d("FetchingPromotionuploaddata--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SKU_CD, OPENING_TOTAL_STOCK, FACING , STOCK_UNDER_DAYS,CLOSING_STOCK, MIDDAY_TOTAL_STOCK " +
									"FROM STOCK_DATA WHERE STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();

							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setOpenning_total_stock(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("OPENING_TOTAL_STOCK")));

							sb.setOpening_facing(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("FACING")));

							sb.setStock_under45days(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STOCK_UNDER_DAYS")));
							/*sb.setOpen_stock_mccaindf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("OPENING_STOCK_MCCAIN_DF")));*/

							String closing_stock = dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CLOSING_STOCK"));

							if(closing_stock == null || closing_stock.equals("")){
								sb.setClosing_stock("0");
							}
							else {
								sb.setClosing_stock(closing_stock);
							}


							/*sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));
							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));*/


							String midday_stock = dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MIDDAY_TOTAL_STOCK"));

							if(midday_stock == null || midday_stock.equals("")){
								sb.setTotal_mid_stock_received("0");
							}
							else {
								sb.setTotal_mid_stock_received(midday_stock);
							}
							
							/*sb.setClos_stock_cold_room(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("COLD_ROOM")));*/
							/*sb.setClos_stock_meccaindf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MCCAIN_DF")));*/
							/*sb.setClos_stock_storedf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STORE_DF")));*/
							/*sb.setMidday_stock(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MIDDAY_STOCK")));*/
						

							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingFoodStoredat---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			//Get Opening and Midday Stock for validation
			
			public ArrayList<StockGetterSetter> getOpeningNMiddayStock(String storeId) {
				Log.d("FetchingOpening Midday Stock data--------------->Start<------------",
						"------------------");
				ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.SKU_CD,SD.OPENING_STOCK_COLD_ROOM, SD.OPENING_STOCK_MCCAIN_DF, SD.OPENING_STOCK_STORE_DF, SD.MIDDAY_STOCK " +
									"FROM openingHeader_data CD INNER JOIN OPENING_STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id JOIN MIDDAY_STOCK_DATA MD ON CD.KEY_ID=MD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'", null);
					
					/*dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.SKU_CD FROM openingHeader_Midday_data CD INNER JOIN MIDDAY_STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'  AS A INNER JOIN SELECT DISTINCT SD.SKU_CD FROM openingHeader_data CD INNER JOIN OPENING_STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'  AS B ON A.SKU_CD=B.SKU_CD AND A.BRAND_CD=B.BRAND_CD", null);*/
					

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockGetterSetter sb = new StockGetterSetter();

							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));
							sb.setOpen_stock_mccaindf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("OPENING_STOCK_MCCAIN_DF")));
							sb.setOpen_stock_cold_room(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("OPENING_STOCK_COLD_ROOM")));
							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));
							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));
							sb.setOpen_stock_store_df(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("OPENING_STOCK_STORE_DF")));
							sb.setMidday_stock(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MIDDAY_STOCK")));
						

							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			//opening stock
			
			public boolean checkStock(String storeId) {
				Log.d("FetchingOpening Stock data--------------->Start<------------",
						"------------------");
				ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.SKU_CD FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockGetterSetter sb = new StockGetterSetter();

							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));
						
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						
						if(list.size()>0){
							return true;
						}
						else{
							return false;
						}
						
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return false;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return false;
			}

			
			
			//opening stock
			
			public ArrayList<StockGetterSetter> getOpeningStock(String storeId) {
				Log.d("FetchingOpening Stock data--------------->Start<------------",
						"------------------");
				ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.OPENING_STOCK_COLD_ROOM FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockGetterSetter sb = new StockGetterSetter();

							sb.setOpen_stock_cold_room(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("OPENING_STOCK_COLD_ROOM")));
						
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
//Header Key data
			
			public ArrayList<HeaderGetterSetter> getHeaderStock(String storeId) {
				Log.d("FetchingOpening Stock data--------------->Start<------------",
						"------------------");
				ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT KEY_ID FROM openingHeader_data WHERE STORE_CD= '"
											+ storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							HeaderGetterSetter sb = new HeaderGetterSetter();

							sb.setKeyId(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("KEY_ID")));
						
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
//get opening stock data from database
			
			public ArrayList<StockNewGetterSetter> getOpeningStockDataFromDatabase(String storeId, String brand_cd) {
				Log.d("FetchingOpening Stock data--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.SKU_CD, SD.SKU, SD.OPENING_TOTAL_STOCK, SD.FACING ,SD.STOCK_UNDER_DAYS FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "' AND CD.BRAND_CD = '"+ brand_cd +"'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();
							
							
							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							
							sb.setOpenning_total_stock(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("OPENING_TOTAL_STOCK")));
							
							sb.setOpening_facing(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("FACING")));
							sb.setStock_under45days(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STOCK_UNDER_DAYS")));

							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			//check if table is empty
			public boolean isOpeningDataFilled(String storeId){
				boolean filled=false;
				
				Cursor dbcursor = null;
				
				try {

					dbcursor = db
							.rawQuery(
									"SELECT OPENING_TOTAL_STOCK, FACING FROM STOCK_DATA WHERE STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							
							if(dbcursor.getString(dbcursor.getColumnIndexOrThrow("OPENING_TOTAL_STOCK")).equals("") || dbcursor.getString(dbcursor.getColumnIndexOrThrow("FACING")).equals("")){
								filled=false;
								break;
							}
							else{
								filled=true;
							}
						
							dbcursor.moveToNext();
						}
						dbcursor.close();
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return filled;
				}
				
				return filled;
			}
			
			
//get Opening and Midday stock data for comparison from database
			
			public ArrayList<StockNewGetterSetter> getOpeningNMiddayStockDataFromDatabase(String storeId, String brand_cd) {
				Log.d("FetchingOpening Stock data--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.SKU_CD, SD.SKU, SD.OPENING_TOTAL_STOCK, SD.MIDDAY_TOTAL_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "' AND CD.BRAND_CD = '"+ brand_cd +"'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();
							
							
							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));

							sb.setOpenning_total_stock(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("OPENING_TOTAL_STOCK")));

							sb.setTotal_mid_stock_received(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MIDDAY_TOTAL_STOCK")));

							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
//get Closing and Midday stock data for comparison from database
			
			public ArrayList<StockGetterSetter> getClosingNMiddayStockDataFromDatabase(String storeId, String brand_cd) {
				Log.d("FetchingClosingnMid Stock data--------------->Start<------------",
						"------------------");
				ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
				Cursor dbcursor = null;

				try {
					
					dbcursor = db
							.rawQuery(
									"SELECT SD.SKU_CD, SD.SKU, SD.COLD_ROOM, SD.MCCAIN_DF, SD.STORE_DF, SD.MIDDAY_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "' AND CD.BRAND_CD = '"+ brand_cd +"'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockGetterSetter sb = new StockGetterSetter();
							
							
							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							
						/*	sb.setCategory_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE")));*/
							
							/*sb.setActual_listed(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("ACTUAL_LISTED")));
							sb.setAs_per_meccain("N");*/
							sb.setClos_stock_cold_room(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("COLD_ROOM")));
							sb.setClos_stock_meccaindf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MCCAIN_DF")));
							sb.setClos_stock_storedf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STORE_DF")));
							sb.setMidday_stock(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MIDDAY_STOCK")));
							/*sb.setTotal_facing_storedf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("TOTAL_FACING_STORE_DF")));
							sb.setTotalfacing_mccaindf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("TOTAL_FACING_MCCAIN_DF")));
							sb.setMaterial_wellness(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MATERIAL_WELLNESS")));*/
						
						
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingClosing midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
//get Closing stock data from database
			
			public ArrayList<StockNewGetterSetter> getClosingStockDataFromDatabase(String storeId, String brand_cd) {
				Log.d("FetchingOpening Stock data--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.SKU_CD, SD.SKU, SD.CLOSING_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "' AND CD.BRAND_CD = '"+ brand_cd +"'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();
							
							

							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));
							
							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));

							sb.setClosing_stock(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CLOSING_STOCK")));
						
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
//get Closing stock data from database
		
			public ArrayList<ColdroomClosingGetterSetter> getClosingColdDataFromDatabase(String storeId, String sku_cd) {
				Log.d("FetchingOpening Stock data--------------->Start<------------",
						"------------------");
				
				//temp code
				//storeId=451+"";
				
				ArrayList<ColdroomClosingGetterSetter> list = new ArrayList<ColdroomClosingGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT CLOSING_STOCK_COLD FROM PREVIOUS_COLD_STOCK WHERE STORE_CD= '"
											+ storeId + "' AND SKU_CD = '"+ sku_cd +"'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							ColdroomClosingGetterSetter sb = new ColdroomClosingGetterSetter();
							
							

							sb.setClosing_cold(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CLOSING_STOCK_COLD")));
								
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			
			
			//check if table is empty
			public boolean isClosingDataFilled(String storeId){
				boolean filled=false;
				
				Cursor dbcursor = null;
				
				try {

					dbcursor = db
							.rawQuery(
									"SELECT CLOSING_STOCK FROM STOCK_DATA WHERE STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							
							if(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLOSING_STOCK")).equals("") ){
								filled=false;
								break;
							}
							else{
								filled=true;
							}
						
							dbcursor.moveToNext();
						}
						dbcursor.close();
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return filled;
				}
				
				return filled;
				
				
			}
			
			
//get Midday stock data from database
			
			public ArrayList<StockNewGetterSetter> getMiddayStockDataFromDatabase(String storeId, String brand_cd) {
				Log.d("FetchingOpening Stock data--------------->Start<------------",
						"------------------");
				ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.SKU_CD, SD.SKU, SD.MIDDAY_TOTAL_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "' AND CD.BRAND_CD = '"+ brand_cd +"'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockNewGetterSetter sb = new StockNewGetterSetter();

							

							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));
							
							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							
						/*	sb.setCategory_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE")));*/
							
							sb.setTotal_mid_stock_received(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MIDDAY_TOTAL_STOCK")));
							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			//check if table is empty
			public boolean isMiddayDataFilled(String storeId){
				boolean filled=false;
				
				Cursor dbcursor = null;
				
				try {

					dbcursor = db
							.rawQuery(
									"SELECT MIDDAY_TOTAL_STOCK FROM STOCK_DATA WHERE STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							
							if(dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIDDAY_TOTAL_STOCK")).equals("")){
								filled=false;
								break;
							}
							else{
								filled=true;
							}
						
							dbcursor.moveToNext();
						}
						dbcursor.close();
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return filled;
				}
				
				return filled;
			}
			
			
//Middaystock stock
			
			public ArrayList<StockGetterSetter> getMiddayStock(String storeId) {
				Log.d("FetchingOpening Stock data--------------->Start<------------",
						"------------------");
				ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT SD.MIDDAY_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							StockGetterSetter sb = new StockGetterSetter();

							sb.setMidday_stock(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MIDDAY_STOCK")));
						
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingOPening midday---------------------->Stop<-----------",
						"-------------------");
				return list;
			}
			
			
			
//deletecross Stock Data
			
			public void deleteOpeningStockData(String storeid) {

				try {

					db.delete(CommonString.TABLE_OPENING_STOCK_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

					db.delete(CommonString.TABLE_INSERT_OPENINGHEADER_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());

				}
			}
			
//deletecross Stock Data closing
			
			public void deleteClosingStockData(String storeid) {

				try {

					db.delete(CommonString.TABLE_CLOSING_STOCK_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

					db.delete(CommonString.TABLE_INSERT_OPENINGHEADER_CLOSING_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());

				}
			}
			
			
//deletecross Stock Data Midday
			
			public void deleteMiddayStockData(String storeid) {

				try {

					db.delete(CommonString.TABLE_MIDDAY_STOCK_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

					db.delete(CommonString.TABLE_INSERT_HEADER_MIDDAY_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());

				}
			}
			
			
			
//deletecross Stock Data Midday
			
			public void updateMiddayStockData( String storeid,
					HashMap<StockNewGetterSetter, List<MiddayStockInsertData>> data,List<StockNewGetterSetter> save_listDataHeader) {
				
				ContentValues values = new ContentValues();
				ContentValues values1 = new ContentValues();

				try {

					
					
					db.beginTransaction();
					for (int i = 1; i <= save_listDataHeader.size(); i++) {

						values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString.TABLE_INSERT_HEADER_MIDDAY_DATA,
								null, values);
						
						//db.update(CommonString.TABLE_MIDDAY_STOCK_DATA, values1, "KEY_ID" + "='" + i + "'", null);
						
						for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							values1.put("Common_Id", (int)l);
							values1.put("STORE_CD", storeid);
							values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getSku_cd()));
							values1.put("MIDDAY_STOCK", data.get(save_listDataHeader.get(i)).get(j).getMidday_stock());
							
							db.insert(CommonString.TABLE_MIDDAY_STOCK_DATA, null, values1);
							
						}
					}
					db.setTransactionSuccessful();
					db.endTransaction();
				} catch (Exception ex) {
					Log.d("Database Exception while Insert Posm Master Data ",
							ex.toString());
				}

			}
			
			
//deletecross Food Store data
			
			public void deleteFoodStoreData(String storeid) {

				try {

					db.delete(CommonString.TABLE_FOOD_STORE_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

					db.delete(CommonString.TABLE_INSERT_HEADER_FOOD_STORE_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());

				}
			}
			
			
//deletecross Promotion Data
			
			public void deletePromotionData(String storeid) {

				try {

					db.delete(CommonString.TABLE_PROMOTION_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

					db.delete(CommonString.TABLE_INSERT_PROMOTION_HEADER_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());

				}
			}
			
		
//deletecross Asset Data
			
			public void deleteAssetData(String storeid) {

				try {

					db.delete(CommonString.TABLE_ASSET_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

					db.delete(CommonString.TABLE_INSERT_ASSET_HEADER_DATA,
							CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());

				}
			}
			
			
//Insert Closing Stock Data
			
			public void insertClosingStockData(ClosingStockInsertDataGetterSetter data,String sku_cd) {

				db.delete(CommonString.TABLE_CLOSING_STOCK_DATA, "SKU_CD" + "='" + sku_cd + "'", null);
				ContentValues values = new ContentValues();

				try {

					
						values.put("SKU_CD", Integer.parseInt(data.getSku_cd()));
						values.put("COLD_ROOM", data.getClos_stock_cold_room());
						values.put("MCCAIN_DF", data.getMeccaindf());
						values.put("STORE_DF", data.getStoredf());
						
						db.insert(CommonString.TABLE_CLOSING_STOCK_DATA, null, values);

					

				} catch (Exception ex) {
					Log.d("Database Exception while Insert CLOSING STOCK Data ",
							ex.toString());
				}

			}
			
			
//Insert Closing Stock Data
			
			public void insertMiddayStockData(String midday_stock,String sku_cd) {

				db.delete(CommonString.TABLE_MIDDAY_STOCK_DATA, "SKU_CD" + "='" + sku_cd + "'", null);
				ContentValues values = new ContentValues();

				try {

					
						values.put("SKU_CD", Integer.parseInt(sku_cd));
						values.put("MIDDAY_STOCK", midday_stock);
						
						
						db.insert(CommonString.TABLE_MIDDAY_STOCK_DATA, null, values);

					

				} catch (Exception ex) {
					Log.d("Database Exception while Insert OPENING STOCK Data ",
							ex.toString());
				}

			}
			
			
//get if category type is food 
			
			public boolean isCategoryTypeFood(String brand_cd) {
				Log.d("FetchingStoredata--------------->Start<------------",
						"------------------");
				ArrayList<MiddayStockInsertData> list = new ArrayList<MiddayStockInsertData>();
				Cursor dbcursor = null;

				try {

					dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD ='" + brand_cd + "'AND SD.CATEGORY_TYPE ='" + "Food" + "'"
									, null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							MiddayStockInsertData sb = new MiddayStockInsertData();

							
							sb.setSku_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));
							
							sb.setMidday_stock("15");
							
							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						
						
					}

				} catch (Exception e) {
					Log.d("Exception when fetching opening stock!!!!!!!!!!!",
							e.toString());
					return false;
				}

				Log.d("Fetching opening stock---------------------->Stop<-----------",
						"-------------------");
				if(list.size()>0){
					return true;
				}
				else{
					return false;
				}
			}

			
			
//get DeepFreezerType Data
			
			public ArrayList<FacingCompetitorGetterSetter> getCategoryData() {

				Log.d("FetchingCategoryType--------------->Start<------------",
						"------------------");
				ArrayList<FacingCompetitorGetterSetter> list = new ArrayList<FacingCompetitorGetterSetter>();
				Cursor dbcursor = null;

				try {
					dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_CD, CATEGORY from SKU_MASTER"
							, null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							FacingCompetitorGetterSetter df = new FacingCompetitorGetterSetter();


							df.setCategory_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_CD")));
							df.setCategory(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY")));
							df.setMccaindf("");
							//df.setStoredf("");
							
							list.add(df);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Category!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("FetchingCategory data---------------------->Stop<-----------",
						"-------------------");
				return list;

			}



//get Brand Data

	public ArrayList<StockNewGetterSetter> getBrandData(String category_id) {

		Log.d("FetchingBrand--------------->Start<------------",
				"------------------");
		ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT DISTINCT BRAND_CD, BRAND from SKU_MASTER  WHERE CATEGORY_CD"+ " = '" + category_id +
					"' "
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					StockNewGetterSetter df = new StockNewGetterSetter();


					df.setBrand_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND_CD")));
					df.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND")));

					list.add(df);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Brand!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("Brand data---------------------->Stop<-----------",
				"-------------------");
		return list;

	}


//get Company Data

	public ArrayList<CompanyGetterSetter> getCompanyData() {

		Log.d("FetchingCategoryType--------------->Start<------------",
				"------------------");
		ArrayList<CompanyGetterSetter> list = new ArrayList<CompanyGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT DISTINCT COMPANY_CD, COMPANY from COMPANY_MASTER"
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					CompanyGetterSetter df = new CompanyGetterSetter();


					df.setCompany_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("COMPANY_CD")));
					df.setCompany(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("COMPANY")));

					list.add(df);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Company!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingCategory data---------------------->Stop<-----------",
				"-------------------");
		return list;

	}


	//get Competition Company Data

	public ArrayList<FacingCompetionCompanyGetterSetter> getCompetitionCompanyData() {

		Log.d("FetchingCategoryType--------------->Start<------------",
				"------------------");
		ArrayList<FacingCompetionCompanyGetterSetter> list = new ArrayList<FacingCompetionCompanyGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT DISTINCT COMPANY_CD, COMPANY from COMPANY_MASTER"
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					FacingCompetionCompanyGetterSetter df = new FacingCompetionCompanyGetterSetter();


					df.setCompany_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("COMPANY_CD")));
					df.setCompany(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("COMPANY")));

					df.setFacing("");

					list.add(df);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Company!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingCategory data---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

			public int CheckMid(String currdate, String storeid) {

				Cursor dbcursor = null;
				int mid = 0;
				try {
					dbcursor = db.rawQuery("SELECT  * from "
							+ CommonString.TABLE_COVERAGE_DATA + "  WHERE "
							+ CommonString.KEY_VISIT_DATE + " = '" + currdate
							+ "' AND " + CommonString.KEY_STORE_ID + " ='" + storeid
							+ "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();

						mid = dbcursor.getInt(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_ID));

						dbcursor.close();

					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
				}

				return mid;
			}



	public String getVisiteDateFromCoverage(String storeid) {

		Cursor dbcursor = null;
		String visite_date= "";
		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_COVERAGE_DATA + "  WHERE "
					 + CommonString.KEY_STORE_ID + " ='" + storeid
					+ "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				visite_date = dbcursor.getString(dbcursor
						.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE));

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
		}

		return visite_date;
	}


	public long InsertCoverageData(CoverageBean data) {

				ContentValues values = new ContentValues();

				try {

					values.put(CommonString.KEY_STORE_ID, data.getStoreId());
					values.put(CommonString.KEY_USER_ID, data.getUserId());
					values.put(CommonString.KEY_IN_TIME, data.getInTime());
					values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
					values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
					values.put(CommonString.KEY_LATITUDE, data.getLatitude());
					values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
					values.put(CommonString.KEY_REASON_ID, data.getReasonid());
					values.put(CommonString.KEY_REASON, data.getReason());
					values.put(CommonString.KEY_COVERAGE_STATUS, data.getStatus());
					values.put(CommonString.KEY_IMAGE, data.getImage());
					values.put(CommonString.KEY_COVERAGE_REMARK, data.getRemark());
					values.put(CommonString.KEY_REASON_ID, data.getReasonid());
					values.put(CommonString.KEY_REASON, data.getReason());
					//values.put(CommonString.KEY_other, data.getOtherreson());

					return db.insert(CommonString.TABLE_COVERAGE_DATA, null, values);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
				return 0;
			}
			
			
			// getCoverageData
			public ArrayList<CoverageBean> getCoverageData(String visitdate) {

				ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
				Cursor dbcursor = null;

				try {

						dbcursor = db.rawQuery("SELECT  * from "
								+ CommonString.TABLE_COVERAGE_DATA + " where "
								+ CommonString.KEY_VISIT_DATE + "='" + visitdate + "'",
								null);
			
						
					if (dbcursor != null) {

						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
	                      CoverageBean sb = new CoverageBean();
							
							sb.setStoreId(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
							sb.setUserId((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
							sb.setInTime(((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
							sb.setOutTime(((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
							sb.setVisitDate((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
							sb.setLatitude(((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
							sb.setLongitude(((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
							sb.setStatus((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_STATUS))))));
							sb.setImage((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_IMAGE))))));
							
							sb.setReason((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_REASON))))));
							sb.setReasonid((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_REASON_ID))))));
							sb.setMID(Integer.parseInt(((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_ID))))));

							if(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK))==null){
								sb.setRemark("");
							}
							else{
								sb.setRemark((((dbcursor.getString(dbcursor
										.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK))))));
							}
							
							
							/*sb.setCoveargestatus((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_STATUS))))));*/
						
					
						/*	sb.setSelfieImage((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_IMAGE_SELFIE))))));

							sb.setCoveargestatus((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_STATUS))))));
							
							sb.setMerchanId((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_MERCHANDISER_ID))))));
							
							
							sb.setCoverageReamark((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK))))));

							sb.setDataSource((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_DATA_SOURCE))))));
							
							sb.setDavId((((dbcursor.getString(dbcursor
									.getColumnIndexOrThrow(CommonString.KEY_DEV_REASON))))));
									*/

							list.add(sb);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
							e.toString());

				}

				return list;

			}

	// getCoverageData
	public ArrayList<CoverageBean> getCoverageSpecificData(String store_id) {

		ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
		Cursor dbcursor = null;

		try {

			dbcursor = db.rawQuery("SELECT  * from "
							+ CommonString.TABLE_COVERAGE_DATA + " where "
							+ CommonString.KEY_STORE_ID + "='" + store_id + "'",
					null);


			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					CoverageBean sb = new CoverageBean();

					sb.setUserId((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
					sb.setInTime(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
					sb.setOutTime(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
					sb.setVisitDate((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
					sb.setLatitude(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
					sb.setLongitude(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
					sb.setStatus((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_STATUS))))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
					e.toString());

		}

		return list;

	}

	//check if table is empty
	public boolean isCoverageDataFilled(String visit_date){
		boolean filled=false;

		Cursor dbcursor = null;

		try {

			dbcursor = db
					.rawQuery(
							"SELECT * FROM COVERAGE_DATA " + "where "
									+ CommonString.KEY_VISIT_DATE + "<>'" + visit_date + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				int icount = dbcursor.getInt(0);
				dbcursor.close();
				if(icount>0){
					filled=true;
				}
				else{
					filled=false;
				}

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return filled;
		}

		return filled;
	}

			
			public void updateCoverageStatus(int mid, String status) {

				try {
					ContentValues values = new ContentValues();
					values.put(CommonString.KEY_COVERAGE_STATUS, status);

					db.update(CommonString.TABLE_COVERAGE_DATA, values,
							CommonString.KEY_ID + "=" + mid, null);
				} catch (Exception e) {

				}
			}



	public void updateCoverageStoreOutTime(String StoreId, String VisitDate,String outtime,String status) {

		try {
			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_OUT_TIME, outtime);
			values.put(CommonString.KEY_COVERAGE_STATUS, status);

			db.update(CommonString.TABLE_COVERAGE_DATA, values,CommonString.KEY_STORE_ID + "='" + StoreId + "' AND "
					+ CommonString.KEY_VISIT_DATE + "='" + VisitDate
					+ "'" , null);
		} catch (Exception e) {

		}
	}
			
			
			public void updateStoreStatusOnLeave(String storeid, String visitdate,
					String status) {

				try {
					ContentValues values = new ContentValues();
					values.put("UPLOAD_STATUS", status);

					db.update("JOURNEY_PLAN", values,
							CommonString.KEY_STORE_CD + "='" + storeid + "' AND "
									+ CommonString.KEY_VISIT_DATE + "='" + visitdate
									+ "'", null);
				} catch (Exception e) {

				}
			}
			
			
			public void updateStoreStatusOnCheckout(String storeid, String visitdate,
					String status) {

				try {
					ContentValues values = new ContentValues();
					values.put(CommonString.KEY_CHECKOUT_STATUS, status);

					db.update("JOURNEY_PLAN", values,
							CommonString.KEY_STORE_CD + "='" + storeid + "' AND "
									+ CommonString.KEY_VISIT_DATE + "='" + visitdate
									+ "'", null);
				} catch (Exception e) {

				}
			}
	
			
//Insert Calls Data
			
			public void insertCallsData(String store_cd,String total_calls, String productive_calls) {

				db.delete("CALLS_DATA", "STORE_CD" + "='" + store_cd + "'", null);
				ContentValues values = new ContentValues();

				try {
						values.put("STORE_CD",store_cd);
						values.put("TOTAL_CALLS", total_calls);
						values.put("PRODUCTIVE_CALLS", productive_calls);
						
						db.insert(CommonString.TABLE_CALLS_DATA, null, values);

					

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Calls Data ",
							ex.toString());
				}

			}

	//Insert Sample Data

	public void insertSampleData(String store_cd,String customer_name, String phone_number, String quantity_sample, String quantity_sale) {

		//db.delete("SAMPLE_DATA", "STORE_CD" + "='" + store_cd + "'", null);
		ContentValues values = new ContentValues();

		try {
			values.put("STORE_CD",store_cd);
			values.put("CUSTOMER_NAME", customer_name);
			values.put("PHONE_NUMBER", phone_number);
			values.put("QUANTITY_SAMPLE", quantity_sample);
			values.put("QUANTITY_SALE", quantity_sale);

			db.insert(CommonString.TABLE_SAMPLE_DATA, null, values);



		} catch (Exception ex) {
			Log.d("Database Exception while Insert Calls Data ",
					ex.toString());
		}

	}
			
			
//get Calls Data
			
			public ArrayList<CallsGetterSetter> getCallsData(String store_cd) {

				Log.d("Fetching calls--------------->Start<------------",
						"------------------");
				ArrayList<CallsGetterSetter> list = new ArrayList<CallsGetterSetter>();
				Cursor dbcursor = null;

				try {
					dbcursor = db.rawQuery("SELECT * from CALLS_DATA where STORE_CD = '" + store_cd + "'"
							, null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						while (!dbcursor.isAfterLast()) {
							CallsGetterSetter fc = new CallsGetterSetter();


							fc.setStore_id(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("STORE_CD")));
							fc.setTotal_calls(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("TOTAL_CALLS")));
							fc.setProductive_calls(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PRODUCTIVE_CALLS")));
							fc.setKey_id(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("Key_Id")));
							
							list.add(fc);
							dbcursor.moveToNext();
						}
						dbcursor.close();
						return list;
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Calls data!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return list;
				}

				Log.d("Fetching facing Calls---------------------->Stop<-----------",
						"-------------------");
				return list;

			}


	//get Sample Data

	public ArrayList<SampleDataGetterSetter> getSampleData(String store_cd) {

		Log.d("Fetching calls--------------->Start<------------",
				"------------------");
		ArrayList<SampleDataGetterSetter> list = new ArrayList<SampleDataGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * from SAMPLE_DATA where STORE_CD = '" + store_cd + "'"
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SampleDataGetterSetter fc = new SampleDataGetterSetter();


					fc.setStore_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STORE_CD")));
					fc.setCustomer_name(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CUSTOMER_NAME")));
					fc.setPhone_no(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("PHONE_NUMBER")));
					fc.setQuality_sample(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("QUANTITY_SAMPLE")));
					fc.setQuality_sale(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("QUANTITY_SALE")));

					list.add(fc);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Sample data!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("Fetching facing Calls---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	//check if table is empty
			public boolean isCallsDataFilled(String storeId){
				boolean filled=false;
				
				Cursor dbcursor = null;
				
				try {

					dbcursor = db
							.rawQuery(
									"SELECT * FROM CALLS_DATA WHERE STORE_CD= '" + storeId + "'", null);

					if (dbcursor != null) {
						dbcursor.moveToFirst();
						int icount = dbcursor.getInt(0);
						dbcursor.close();
						if(icount>0){
							filled=true;
						}
						else{
							filled=false;
						}
						
					}

				} catch (Exception e) {
					Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
							e.toString());
					return filled;
				}
				
				return filled;
			}
			

			public void deleteStockRow(int id,String store_cd){

				try{
					db.delete(CommonString.TABLE_CALLS_DATA, CommonString.KEY_STORE_CD + "='" + store_cd + "' AND "
							+ "Key_Id" + "='" + id + "'"
							, null);
				}
				catch(Exception e){

				}

			}



	//Insert Calls Data

	public void insertCompetitionPOIData(String store_cd, POIGetterSetter poiGetterSetter) {

		//db.delete("CALLS_DATA", "STORE_CD" + "='" + store_cd + "'", null);
		ContentValues values = new ContentValues();

		try {

			values.put("STORE_CD", store_cd);
			values.put("CATEGORY_CD", poiGetterSetter.getCategory_cd());
			values.put("CATEGORY",poiGetterSetter.getCategory());
			values.put("ASSET_CD", poiGetterSetter.getAsset_cd());
			values.put("ASSET", poiGetterSetter.getAsset());
			values.put("COMPANY_CD", poiGetterSetter.getCompany_cd());
			values.put("COMPANY",poiGetterSetter.getCompany());
			values.put("REMARK", poiGetterSetter.getRemark());

			db.insert(CommonString.TABLE_COMPETITION_POI, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Calls Data ",
					ex.toString());
		}

	}


	//get Calls Data

	public ArrayList<POIGetterSetter> getCompetitionPOIData(String store_cd) {

		Log.d("Fetching calls--------------->Start<------------",
				"------------------");
		ArrayList<POIGetterSetter> list = new ArrayList<POIGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * from COMPETITION_POI WHERE STORE_CD= '" + store_cd + "'"
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					POIGetterSetter fc = new POIGetterSetter();


					fc.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("Key_Id")));
					fc.setCategory_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY_CD")));
					fc.setCategory(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY")));
					fc.setAsset_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET_CD")));
					fc.setAsset(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET")));
					fc.setRemark(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("REMARK")));
					fc.setCompany_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("COMPANY_CD")));
					fc.setCompany(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("COMPANY")));

					list.add(fc);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("Fetching facing Calls---------------------->Stop<-----------",
				"-------------------");
		return list;

	}




	public void deleteCompetitionPOIRow(int id){

		try{
			db.delete(CommonString.TABLE_COMPETITION_POI, "Key_Id " + "='" + id + "'"
					, null);
		}
		catch(Exception e){

		}

	}


	//Insert Calls Data

	public void insertCompetitionPromotionData(CompetitionPromotionGetterSetter poiGetterSetter, String store_cd) {

		//db.delete("CALLS_DATA", "STORE_CD" + "='" + store_cd + "'", null);
		ContentValues values = new ContentValues();

		try {

			values.put("STORE_CD", store_cd);
			values.put("CATEGORY_CD", poiGetterSetter.getCategory_cd());
			values.put("CATEGORY",poiGetterSetter.getCategory());
			values.put("COMPANY_CD", poiGetterSetter.getCompany_cd());
			values.put("COMPANY",poiGetterSetter.getCompany());
			values.put("REMARK", poiGetterSetter.getRemark());
			values.put("PROMOTION", poiGetterSetter.getPromotion());
			values.put("SKU", poiGetterSetter.getSku());

			db.insert(CommonString.TABLE_COMPETITION_PROMOTION, null, values);


		} catch (Exception ex) {
			Log.d("Database Exception while Insert Calls Data ",
					ex.toString());
		}

	}

	//get Competition Promotion Data

	public ArrayList<CompetitionPromotionGetterSetter> getCompetitionPromotionData(String store_cd) {

		Log.d("Fetching calls--------------->Start<------------",
				"------------------");
		ArrayList<CompetitionPromotionGetterSetter> list = new ArrayList<CompetitionPromotionGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * from COMPETITION_PROMOTION WHERE STORE_CD= '" + store_cd + "'"
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					CompetitionPromotionGetterSetter fc = new CompetitionPromotionGetterSetter();

					fc.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("Key_Id")));
					fc.setCategory_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY_CD")));
					fc.setCategory(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY")));
					fc.setRemark(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("REMARK")));
					fc.setPromotion(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("PROMOTION")));
					fc.setSku(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("SKU")));
					fc.setCompany_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("COMPANY_CD")));
					fc.setCompany(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("COMPANY")));

					list.add(fc);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("Fetching facing Calls---------------------->Stop<-----------",
				"-------------------");
		return list;

	}


	public void deleteCompetitionPromotionRow(int id){

		try{
			db.delete(CommonString.TABLE_COMPETITION_PROMOTION, "Key_Id " + "='" + id + "'"
					, null);
		}
		catch(Exception e){

		}

	}


//Insert Calls Data

	public void insertPOIData(POIGetterSetter poiGetterSetter,String store_cd) {

		//db.delete("CALLS_DATA", "STORE_CD" + "='" + store_cd + "'", null);
		ContentValues values = new ContentValues();

		try {

			values.put("STORE_CD", store_cd);
			values.put("CATEGORY_CD", poiGetterSetter.getCategory_cd());
			values.put("CATEGORY",poiGetterSetter.getCategory());
			values.put("ASSET_CD", poiGetterSetter.getAsset_cd());
			values.put("ASSET", poiGetterSetter.getAsset());
			values.put("BRAND_CD", poiGetterSetter.getBrand_cd());
			values.put("BRAND", poiGetterSetter.getBrand());
			values.put("REMARK", poiGetterSetter.getRemark());
			values.put("IMAGE_POI", poiGetterSetter.getImage());
			db.insert(CommonString.TABLE_POI, null, values);


		} catch (Exception ex) {
			Log.d("Database Exception while Insert Calls Data ",
					ex.toString());
		}

	}


	//get Calls Data

	public ArrayList<POIGetterSetter> getPOIData(String store_cd) {

		Log.d("Fetching calls--------------->Start<------------",
				"------------------");
		ArrayList<POIGetterSetter> list = new ArrayList<POIGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT * from POI WHERE STORE_CD= '" + store_cd + "'"
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					POIGetterSetter fc = new POIGetterSetter();

					fc.setId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("Key_Id")));
					fc.setCategory_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY_CD")));
					fc.setCategory(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY")));
					fc.setAsset_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET_CD")));
					fc.setAsset(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET")));
					fc.setBrand_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND_CD")));
					fc.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND")));
					fc.setRemark(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("REMARK")));
					fc.setImage(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("IMAGE_POI")));
					list.add(fc);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("Fetching facing Calls---------------------->Stop<-----------",
				"-------------------");
		return list;

	}


	public void deletePOIRow(int id,String store_cd){

		try{
			db.delete(CommonString.TABLE_POI, CommonString.KEY_STORE_CD + "='" + store_cd + "' AND "
					+ "Key_Id " + "='" + id + "'"
					, null);
		}
		catch(Exception e){

		}

	}



	/// get store Status
	public JourneyPlanGetterSetter getStoreStatus(String id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		JourneyPlanGetterSetter sb = new JourneyPlanGetterSetter();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from  JOURNEY_PLAN"
					+ "  WHERE STORE_CD = '"
					+ id + "'", null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 0; i < numrows; i++) {

					sb.setStore_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));

					sb.setCheckOutStatus((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CHECKOUT_STATUS"))));

					sb.setUploadStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("UPLOAD_STATUS")));

					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return sb;

	}


	//----------------------------------------------------
	

	/*// downloaded data
	public void insertMerchandiserList(MerchndiserGetterSetter data) {

		db.delete("MERCHANDISER_LIST", null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getmerc_id().size(); i++) {

				values.put("MERCHANDISER_ID", data.getmerc_id().get(i));
				values.put("MERCHANDISER", data.getmerc_name().get(i));
				values.put("MER_SCORE", 0);
				values.put("STATUS", "D");


				db.insert("MERCHANDISER_LIST", null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.toString());
		}

	}
	
	// getStoreData
	public ArrayList<MerchndiserGetterSetter> getMerchandiserList() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<MerchndiserGetterSetter> list = new ArrayList<MerchndiserGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from  MERCHANDISER_LIST",null,null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					MerchndiserGetterSetter sb = new MerchndiserGetterSetter();
					sb.setmerc_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("MERCHANDISER_ID")));
					sb.setmerc_name(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("MERCHANDISER")));
					sb.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STATUS")));
				

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}	
	
	//Store data
	
	public void insertStoreData(StoreListGetterSetter data) {

		db.delete("MERCHANDISER_STORE_LIST", null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getStoreid().size(); i++) {

				values.put("STORE_CD", data.getStoreid().get(i));
				values.put("STORE_NAME", data.getStorename().get(i));
				values.put("CITY", data.getCity().get(i));
				values.put("MERCHANDISER_ID", data.getMerchandiserId().get(i));
				
				values.put("CATEGORY", data.getCategory().get(i));
				values.put("STORETYPE", data.getStoreType().get(i));
				values.put("STORE_SCORE", data.getScore().get(i));
				values.put("STATUS", "D");


				db.insert("MERCHANDISER_STORE_LIST", null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.toString());
		}

	}
	
	public int getTableExistance(){
		Cursor dbcursor = null;
       int count =0;
		try {
			
			dbcursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name='MERCHANDISER_LIST'",null);
			
			if (dbcursor != null) {
			    count = dbcursor.getCount();
				

				dbcursor.close();
				
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			
		}
		return count;
	}
	

	
	// insert nonworking data
	
	public void insertnonworkingData(NonWrkingMasterGetterSetter data) {

		db.delete("NON_WORKING_REASON", null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getNonWorkingReasonid().size(); i++) {

				values.put("REASON_ID", data.getNonWorkingReasonid().get(i));
				values.put("REASON", data.getNonWorkingReasonName().get(i));
				values.put("SUB_REASON_ID", data.getSubReason_id().get(i));
				values.put("SUB_REASON", data.getSub_reason().get(i));
				 
				values.put("ENTRY_NOT_ALLOW", data.getEntryAlow().get(i));

				values.put("IMAGE_ALLOW", data.getImageAllow().get(i));
				db.insert("NON_WORKING_REASON", null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.toString());
		}

	}
	
	public ArrayList<ReasonModel> GetNonWorkingReason() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<ReasonModel> list = new ArrayList<ReasonModel>();
		Cursor dbcursor = null;

		try {

			dbcursor = db.rawQuery("SELECT  * from NON_WORKING_REASON"
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ReasonModel sb = new ReasonModel();
					sb.setReasonid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.REASON_ID)));
					sb.setReason(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.REASON)));
//					sb.setReasonValid(dbcursor.getString(dbcursor
//							.getColumnIndexOrThrow(CommonString.REASON_VALID)));
					

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	// getting Non Working Sub Reason

	public ArrayList<ReasonModel> getSubReasonNonWorking(String reasonid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<ReasonModel> list = new ArrayList<ReasonModel>();
		Cursor dbcursor = null;

		try {

			dbcursor = db.rawQuery("SELECT  * from NON_WORKING_REASON "
					 + "  WHERE REASON_ID = '"
							+ reasonid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ReasonModel sb = new ReasonModel();
					sb.setReasonid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.REASON_ID)));
					sb.setSub_reasonId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.SUB_REASON_ID)));
					sb.setSub_reason(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.SUB_REASON)));
					

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	//insert coverage data
	public long InsertCoverageData(CoverageBean data) {

		ContentValues values = new ContentValues();

		try {
			
			db.delete(CommonString.TABLE_COVERAGE_DATA,
					CommonString.KEY_STORE_ID + "='" + data.getStoreId() 
					+ "' and "+ CommonString.KEY_VISIT_DATE
					+ "='" +data.getVisitDate()+"'", null);
			
			values.put(CommonString.KEY_STORE_ID, data.getStoreId());
			values.put(CommonString.KEY_USER_ID, data.getUserId());
			values.put(CommonString.KEY_IN_TIME, data.getInTime());
			values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
			values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
			values.put(CommonString.KEY_LATITUDE, data.getLatitude());
			values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
			values.put(CommonString.KEY_COVERAGE_STATUS, data.getCoveargestatus());
			values.put(CommonString.KEY_IMAGE_SELFIE, data.getSelfieImage());
			values.put(CommonString.KEY_MERCHANDISER_ID, data.getMerchanId());
			
			values.put(CommonString.KEY_COVERAGE_REMARK, data.getCoverageReamark());
			values.put(CommonString.KEY_DATA_SOURCE, data.getDataSource());
			values.put(CommonString.KEY_DEV_REASON, data.getDavId());
			
			
			
	/////////////////////////////////////////////////////////////////////////////////////		
			
			
			
			
			

			return db.insert(CommonString.TABLE_COVERAGE_DATA, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Closes Data ",
					ex.toString());
		}
		return 0;
	}
	
	public ArrayList<StoreListGetterSetter> getStoreMerchandiserWise(String merId) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<StoreListGetterSetter> list = new ArrayList<StoreListGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT STORE_CD, STORE_NAME,STATUS FROM MERCHANDISER_STORE_LIST where MERCHANDISER_ID ='"+ merId +"'",null,null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					StoreListGetterSetter sb = new StoreListGetterSetter();
					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STORE_CD")));
					sb.setStorename(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STORE_NAME")));
			
					sb.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STATUS")));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	
	
	public ArrayList<GATEbEAN> getMerchandiserListforAttendence() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<GATEbEAN> list = new ArrayList<GATEbEAN>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from  MERCHANDISER_LIST",null,null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					GATEbEAN sb = new GATEbEAN();
					sb.setEID(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("MERCHANDISER_ID")));
					sb.setNAME(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("MERCHANDISER")));
					sb.setAttendanceid("0");
					sb.setAttendance_position(0);

					sb.setPresent("NO");

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}	
	
	
	public long insertGateMeetingCoverage(CoverageBean data) {

		ContentValues values = new ContentValues();

		try {
			
			db.delete(CommonString.TABLE_GATE_COVERAGE_DATA,
					CommonString.KEY_VISIT_DATE + "='" + data.getVisitDate() 
					+"'", null);
			
			
			values.put(CommonString.KEY_USER_ID, data.getUserId());
			values.put(CommonString.KEY_IN_TIME, data.getInTime());
			values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
			values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
			values.put(CommonString.KEY_LATITUDE, data.getLatitude());
			values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
			values.put(CommonString.KEY_IMAGE, data.getImage());
			values.put(CommonString.KEY_IMAGE1, data.getImage());
			values.put(CommonString.KEY_IMAGE2, data.getImage());
			values.put(CommonString.KEY_MEET, data.getReason());
			values.put(CommonString.KEY_STATUS, data.getStatus());
		
		
			

			return db.insert(CommonString.TABLE_GATE_COVERAGE_DATA, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Closes Data ",
					ex.toString());
		}
		return 0;
	}
	
	// GATE METING

		public void InsertGATe_merlist(ArrayList<GATEbEAN> gate, long l) {

			ContentValues values = new ContentValues();

			for (int i = 0; i < gate.size(); i++) {
				try {

					values.put(CommonString.KEY_COMMMON_ID, l);
					values.put(CommonString.Key_NAME, gate.get(i).getNAME());
					values.put(CommonString.Key_EMPID, gate.get(i).getEID());
					values.put(CommonString.KEY_PRESENT, gate.get(i).getPresent());
					

					db.insert(CommonString.TABLE_GATE_MER_DETAIL, null, values);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
			}

		}
		
		public void Update_GATe_merlist(ArrayList<GATEbEAN> gate, long l) {

			ContentValues values = new ContentValues();

			for (int i = 0; i < gate.size(); i++) {
				try {

					values.put(CommonString.KEY_PRESENT, gate.get(i).getPresent());
					

					db.update(CommonString.TABLE_GATE_MER_DETAIL, values,
							CommonString.Key_EMPID + "='" + gate.get(i).getEID()
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
			}

		}
		// Distrbutor List
		public void insertDistributorList(SupervisorGetterSetter data) {

			db.delete("DISTRIBUTOR_LIST", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getSupervisorId().size(); i++) {

					values.put("KEYACCOUNT_CD", data.getSupervisorId().get(i));
					values.put("KEYACCOUNT", data.getSupervisorname().get(i));
				
					db.insert("DISTRIBUTOR_LIST", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}

		
		public ArrayList<SupervisorGetterSetter> getDistributorList() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<SupervisorGetterSetter> list = new ArrayList<SupervisorGetterSetter>();
			Cursor dbcursor = null;

			try {

				dbcursor = db.rawQuery("SELECT  * from DISTRIBUTOR_LIST ", null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						SupervisorGetterSetter sb = new SupervisorGetterSetter();
						sb.setSupervisorname(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("KEYACCOUNT")));
						sb.setSupervisorId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("KEYACCOUNT_CD")));

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}
		
		
		//update storelist
		
		public void updateStoreList(String status,String storeid) {

			ContentValues values = new ContentValues();

			
				try {

					values.put(CommonString.KEY_STATUS, status);
					

					db.update("MERCHANDISER_STORE_LIST", values,
							  "STORE_CD ='" + storeid
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
			

		}
		
		// update merchandiser list
		
		public void updateMerchandiserList(String status,String merId) {

			ContentValues values = new ContentValues();

			
				try {

					values.put(CommonString.KEY_STATUS, status);
					

					db.update("MERCHANDISER_LIST", values,
							  "MERCHANDISER_ID ='" + merId
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
		}
		
		// update coverage
		
		
		public void updateCoverage(String status,String storeid) {

			ContentValues values = new ContentValues();

			
				try {

					values.put(CommonString.KEY_COVERAGE_STATUS, status);
					

					db.update("COVERAGE_DATA", values,
							  "STORE_ID ='" + storeid
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
			

		}
		
		// update attendence data
		public void updateAttendence(String status,String visitdate) {

			ContentValues values = new ContentValues();

			
				try {

					values.put(CommonString.KEY_STATUS, status);
					

					db.update("ATTENDENCE_DATA", values,
							  "VISIT_DATE ='" + visitdate
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
			

		}
		
		
		public void updateGateMeeting(String status,String visitdate) {

			ContentValues values = new ContentValues();

			
				try {

					values.put(CommonString.KEY_STATUS, status);
					

					db.update("GATE_COVERAGE_DATA", values,
							  "VISIT_DATE ='" + visitdate
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
			

		}
		
		// insert Designation
		
		public void insertDesignation(DesignationGetterSetter data) {

			db.delete("DESIGNATION", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getDesigId().size(); i++) {

					values.put("DESIGNATION_CD", data.getDesigId().get(i));
					values.put("DESIGNATION", data.getDesig().get(i));
				
					db.insert("DESIGNATION", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}
		// Merchandiser Journey Plan
		public void insertMerchandiserJourney(JourneyPlanGetterSetter data) {

			db.delete("JOURNEY_PLAN_SUPERVISOR", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getMerchanId().size(); i++) {

					values.put("MERCHANDISER_CD", data.getMerchanId().get(i));
					values.put("VISIT_DATE", data.getVisitdate().get(i));
				
					db.insert("JOURNEY_PLAN_SUPERVISOR", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}
		
		public ArrayList<DesignationGetterSetter> getDesignation() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<DesignationGetterSetter> list = new ArrayList<DesignationGetterSetter>();
			Cursor dbcursor = null;

			try {

				dbcursor = db.rawQuery("SELECT  * from DESIGNATION", null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						DesignationGetterSetter sb = new DesignationGetterSetter();
						sb.setDesigId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DESIGNATION_CD")));
						sb.setDesig(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DESIGNATION")));

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}
		
		public ArrayList<MerchndiserGetterSetter> getjcpMerchandiserList() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<MerchndiserGetterSetter> list = new ArrayList<MerchndiserGetterSetter>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT  * from  MERCHANDISER_LIST INNER JOIN JOURNEY_PLAN_SUPERVISOR ON MERCHANDISER_LIST.MERCHANDISER_ID = JOURNEY_PLAN_SUPERVISOR.MERCHANDISER_CD",
						
						null,null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						MerchndiserGetterSetter sb = new MerchndiserGetterSetter();
						sb.setmerc_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MERCHANDISER_ID")));
						sb.setmerc_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MERCHANDISER")));
						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STATUS")));
					

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}	
		
		
		public ArrayList<MerchndiserGetterSetter> getRemainMerchandiser() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<MerchndiserGetterSetter> list = new ArrayList<MerchndiserGetterSetter>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT * FROM MERCHANDISER_LIST WHERE MERCHANDISER_ID NOT IN (select MERCHANDISER_CD from JOURNEY_PLAN_SUPERVISOR);",
						
						null,null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						MerchndiserGetterSetter sb = new MerchndiserGetterSetter();
						sb.setmerc_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MERCHANDISER_ID")));
						sb.setmerc_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MERCHANDISER")));
						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STATUS")));
					

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}
		
	
		
		// getCoverageData
		public ArrayList<CoverageBean> getCoverageData(String visitdate) {

			ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
			Cursor dbcursor = null;

			try {

				
					dbcursor = db.rawQuery("SELECT  * from "
							+ CommonString.TABLE_COVERAGE_DATA + " where "
							+ CommonString.KEY_VISIT_DATE + "='" + visitdate + "'",
							null);
		
					
				if (dbcursor != null) {

					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
                      CoverageBean sb = new CoverageBean();
						
						sb.setStoreId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
						sb.setUserId((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
						sb.setInTime(((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
						sb.setOutTime(((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
						sb.setVisitDate((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
						sb.setLatitude(((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
						sb.setLongitude(((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
					
				
						sb.setSelfieImage((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_IMAGE_SELFIE))))));

						sb.setCoveargestatus((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_STATUS))))));
						
						sb.setMerchanId((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_MERCHANDISER_ID))))));
						
						
						sb.setCoverageReamark((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK))))));

						sb.setDataSource((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_DATA_SOURCE))))));
						
						sb.setDavId((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_DEV_REASON))))));
								

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
						e.toString());

			}

			return list;

		}
		
		/// get store Status
		public StoreBean getStoreStatus(String id) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");

			StoreBean sb = new StoreBean();

			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT  * from  MERCHANDISER_STORE_LIST"
						 + "  WHERE STORE_CD = '"
						+ id + "'", null);

				if (dbcursor != null) {
					int numrows = dbcursor.getCount();

					dbcursor.moveToFirst();
					for (int i = 0; i < numrows; i++) {

						sb.setStoreid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));
						
						
						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString.KEY_STATUS)));

						dbcursor.moveToNext();

					}

					dbcursor.close();

				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return sb;

		}
		
		
		//deviation reason
		
		
		public void insertDeviationReason(Deviation_Reason data) {

			db.delete("DEVIATION_REASON", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getdevi_id().size(); i++) {

					values.put("DREASON_ID", Integer.parseInt(data.getdevi_id().get(i)));
					values.put("DREASON", data.getdevi_name().get(i));
				
					db.insert("DEVIATION_REASON", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}
		
		// get deviation Reason
		
		public ArrayList<Deviation_Reason> getDeviationReason() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<Deviation_Reason> list = new ArrayList<Deviation_Reason>();
			Cursor dbcursor = null;

			try {

				dbcursor = db.rawQuery("SELECT  * from DEVIATION_REASON", null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						Deviation_Reason sb = new Deviation_Reason();
						sb.setdevi_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DREASON_ID")));
						sb.setdevi_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DREASON")));

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}
		
		
		//insert coverage data
		public long InsertStoreData(CoverageBean data,String mid) {

			ContentValues values = new ContentValues();

			try {
				
				db.delete(CommonString.TABLE_STORE_DATA,
						 CommonString.KEY_STORE_ID
						+ "='" +data.getStoreId()+"'", null);
				values.put(CommonString.KEY_MID, mid);
				values.put(CommonString.KEY_STORE_ID, data.getStoreId());
				
				values.put(CommonString.KEY_ISSUE, data.getRemarkis());
				values.put(CommonString.KEY_IMAGE, data.getImage());
				values.put(CommonString.KEY_IMAGE_1, data.getImage1());
				values.put(CommonString.KEY_IMAGE_2, data.getImage2());
				values.put(CommonString.KEY_REMARK, data.getRemark());
				values.put(CommonString.KEY_STATUS, data.getStatus());
				
		/////////////////////////////////////////////////////////////////////////////////////		
	
				return db.insert(CommonString.TABLE_STORE_DATA, null, values);

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Closes Data ",
						ex.toString());
			}
			return 0;
		}
		
		
		// get Coverage Store data
		
		public ArrayList<StoreBean> getCoverageStoreData(String string,String visitdate) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<StoreBean> list = new ArrayList<StoreBean>();
			Cursor dbcursor = null;

			try {
//				dbcursor = db.rawQuery("SELECT  * from  "+ CommonString.TABLE_STORE_DATA + " where "
//						+ CommonString.KEY_STORE_ID + " = '" + string + "'",
//						null,null);
				
	dbcursor = db.rawQuery("SELECT IMAGE1,IMAGE2,IMAGE3,ISSUE,KEY_REMARK,STORE_ID,CATEGORY_ID,CATEGORY,"
						+ "BRAND_ID,BRAND,QUANTITY,REMARK_STORE from " + CommonString.TABLE_STORE_DATA +" D" +" INNER JOIN "+CommonString.TABLE_HEADER_DATA 
				    	+" H ON D._id=H.COMMONID INNER JOIN "+CommonString.TABLE_CHILD_DATA +" C ON C.COMMONID=H._id where " 
						+ CommonString.KEY_STORE_ID + " = '" + string + "'",
					    	null,null);

			
				

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						StoreBean sb = new StoreBean();
						sb.setImage1(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE1")));
						sb.setImage2(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE2")));
						sb.setImage3(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE3")));
						sb.setIssue(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("ISSUE")));
						
						sb.setRemark(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("KEY_REMARK")));
//						sb.setStatus(dbcursor.getString(dbcursor
//								.getColumnIndexOrThrow("STATUS")));
						sb.setStoreid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STORE_ID")));
						
						
						sb.setCategoryid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("CATEGORY_ID")));
						sb.setBrandid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("BRAND_ID")));
						sb.setAvail(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("QUANTITY")));
						sb.setRemark_Store(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("REMARK_STORE")));
						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}
		// insert attendence data
		public long insertAttendenceData(AttendenceBean data) {

			ContentValues values = new ContentValues();

			try {
				
				db.delete(CommonString.TABLE_ATTENDENCE_DATA, CommonString.KEY_VISIT_DATE
						+ "='" +data.getVisitDate()+"'", null);
				
				values.put(CommonString.KEY_DISTRIBUTOR_ID, data.getDistrbutorId());
				values.put(CommonString.KEY_USER_ID, data.getUserId());
				values.put(CommonString.KEY_IN_TIME, data.getIntme());
				values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
				values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
				values.put(CommonString.KEY_LATITUDE, data.getLat());
				values.put(CommonString.KEY_LONGITUDE, data.getLon());
				values.put(CommonString.KEY_STATUS, data.getStatus());
				values.put(CommonString.KEY_IMAGE_SELFIE, data.getImage());
				
				values.put(CommonString.KEY_REASON, data.getReason());
				
				return db.insert(CommonString.TABLE_ATTENDENCE_DATA
						, null, values);

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Closes Data ",
						ex.toString());
			}
			return 0;
		}
		
	// get Attendence Data
		
		public ArrayList<AttendenceBean> getAttendenceData(String string,String visitdate) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<AttendenceBean> list = new ArrayList<AttendenceBean>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT  * from  "+ CommonString.TABLE_ATTENDENCE_DATA + " where "
						+ CommonString.KEY_USER_ID + " = '" + string + "'"+" AND "+ CommonString.KEY_VISIT_DATE +"='"+visitdate+"'",
						
						null,null);
				

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						
						
						
						AttendenceBean sb = new AttendenceBean();
						sb.setDistrbutorId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DISTRIBUTOR_ID")));
						sb.setUserId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("USER_ID")));
						sb.setIntme(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IN_TIME")));
						sb.setOutTime(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("OUT_TIME")));
						
						sb.setLat(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("LATITUDE")));
						sb.setLon(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("LONGITUDE")));
						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STATUS")));
						
						sb.setReason(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("REASON")));
						sb.setImage(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE_SELFIE")));
						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}
		
		// get gate meeting data
		public ArrayList<GATEbEAN> getGateMeetingData(String visitdate) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<GATEbEAN> list = new ArrayList<GATEbEAN>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT  * from  GATE_COVERAGE_DATA INNER JOIN GATE_MER_DETAIL ON GATE_COVERAGE_DATA._id = GATE_MER_DETAIL.COMMONID "+
			" AND "+CommonString.KEY_VISIT_DATE +"='"+visitdate+"'",
						
						null,null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						GATEbEAN sb = new GATEbEAN();
						sb.setDate(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("VISIT_DATE")));
						sb.setDesigid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MEET")));
						sb.setImage1(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE1")));
						sb.setImage2(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("cam1")));
						sb.setImage3(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("cam2")));
						
						sb.setEID(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("EMPID")));
						sb.setPresent(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("PRESENT")));
						
						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STATUS")));
					

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}	
		
		
	//insert Secondary Window data
		
		
		public void insertSecondaryWindowData(Sup_Window data) {

			db.delete("SUP_PRI_WINDOW", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getCat_id().size(); i++) {

					values.put("CATEGORY_CD", Integer.parseInt(data.getCat_id().get(i)));
					values.put("CATEGORY", data.getCat_name().get(i));
					values.put("BRAND_CD", Integer.parseInt(data.getBrand_id().get(i)));
					values.put("BRAND", data.getBrand_name().get(i));
				
					db.insert("SUP_PRI_WINDOW", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}
		
		
		// get Secondary Window Data
		
		
		
		public ArrayList<SecondaryWindowBean> getSecondaryWindowDataHeader() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<SecondaryWindowBean> list = new ArrayList<SecondaryWindowBean>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_CD , CATEGORY from SUP_PRI_WINDOW " + " GROUP BY CATEGORY", null);

						
					

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						SecondaryWindowBean sb = new SecondaryWindowBean();
						sb.setCate_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("CATEGORY_CD")));
						sb.setCate_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("CATEGORY")));
	
					

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}	
		
		
		//insert Performance data
		
		
				public void insertSupPerformance(Sup_Performance data) {

					db.delete("SUP_PERFORMANCE", null, null);
					ContentValues values = new ContentValues();

					try {

							values.put("MANDAYS", Integer.parseInt(data.getMandays()));
							values.put("COVERAGE", Integer.parseInt(data.getCoverage()));
							values.put("MERCHANDISED", Integer.parseInt(data.getMerchandised()));
							values.put("COMPLIANCE", Integer.parseInt(data.getCompliance()));
						
							db.insert("SUP_PERFORMANCE", null, values);


					} catch (Exception ex) {
						Log.d("Database Exception while Insert Store Data ",
								ex.toString());
					}

				}
				
				
				//get Performance data
				public ArrayList<Sup_Performance> getPerformanceData() {

					Log.d("FetchingPerformance data--------------->Start<------------",
							"------------------");
					ArrayList<Sup_Performance> list = new ArrayList<Sup_Performance>();
					Cursor dbcursor = null;

					try {
						dbcursor = db.rawQuery("SELECT  * from  SUP_PERFORMANCE",null,null);
						
						if (dbcursor != null) {
							dbcursor.moveToFirst();
							while (!dbcursor.isAfterLast()) {
								
								Sup_Performance sb = new Sup_Performance();
								
								sb.setMandays(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MANDAYS")));
								sb.setCoverage(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("COVERAGE")));
								sb.setMerchandised(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MERCHANDISED")));
								sb.setCompliance(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("COMPLIANCE")));

								list.add(sb);
								
								dbcursor.moveToNext();
							}
							dbcursor.close();
							return list;
						}

					} catch (Exception e) {
						Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
								e.toString());
						return list;
					}

					Log.d("FetchingStoredat---------------------->Stop<-----------",
							"-------------------");
					return list;

				}	
				
				
				
				//insert Merchandiser data
				
				
				public void insertSupMerchandiserData(Sup_Merchandiser data) {

					db.delete("SUP_MERCHANDISER_PERFORMANCE", null, null);
					ContentValues values = new ContentValues();

					try {

						for (int i = 0; i < data.getMerchandiser().size(); i++) {

							values.put("MERCHANDISER", data.getMerchandiser().get(i));
							values.put("MANDAYS", Integer.parseInt(data.getMandays().get(i)));
							values.put("COVERAGE", Integer.parseInt(data.getCoverage().get(i)));
							values.put("MERCHANDISED", Integer.parseInt(data.getMerchandised().get(i)));
							values.put("COMPLIANCE", Integer.parseInt(data.getCompliance().get(i)));
						
							db.insert("SUP_MERCHANDISER_PERFORMANCE", null, values);

						}

					} catch (Exception ex) {
						Log.d("Database Exception while Insert Store Data ",
								ex.toString());
					}

				}
				
				
				//get Merchendiserv Performance data
				public ArrayList<Sup_Merchandiser> getMechendiserPerformanceData() {

					Log.d("FetchingPerformance data--------------->Start<------------",
							"------------------");
					ArrayList<Sup_Merchandiser> list = new ArrayList<Sup_Merchandiser>();
					Cursor dbcursor = null;

					try {
						dbcursor = db.rawQuery("SELECT  * from  SUP_MERCHANDISER_PERFORMANCE",null,null);
						
						if (dbcursor != null) {
							dbcursor.moveToFirst();
							while (!dbcursor.isAfterLast()) {
								
								Sup_Merchandiser sb = new Sup_Merchandiser();
								
								sb.setMerchandiser(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MERCHANDISER")));
								sb.setMandays(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MANDAYS")));
								sb.setCoverage(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("COVERAGE")));
								sb.setMerchandised(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MERCHANDISED")));
								sb.setCompliance(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("COMPLIANCE")));

								list.add(sb);
								
								dbcursor.moveToNext();
							}
							dbcursor.close();
							return list;
						}

					} catch (Exception e) {
						Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
								e.toString());
						return list;
					}

					Log.d("FetchingStoredat---------------------->Stop<-----------",
							"-------------------");
					return list;

				}	
				
				
		
		// get Child data
		
		public ArrayList<SecondaryWindowBean> getSecondaryWindowDataChild(String CateId) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<SecondaryWindowBean> list = new ArrayList<SecondaryWindowBean>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT BRAND_CD, BRAND from SUP_PRI_WINDOW " +
                        " WHERE CATEGORY_CD ="+ CateId, null);



				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						SecondaryWindowBean sb = new SecondaryWindowBean();
						sb.setBrand_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("BRAND_CD")));
						sb.setBrand_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("BRAND")));
	
						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}
		
		public void insertHeaderData(Sup_Window data) {

			
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getCat_id().size(); i++) {

					values.put("CATEGORY_ID", data.getCat_id().get(i));
					values.put("CATEGORY", data.getCat_name().get(i));
					values.put("STATUS", Integer.parseInt(data.getBrand_id().get(i)));
					values.put("COMMONID", data.getBrand_name().get(i));
				
					db.insert("HEADER_DATA", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}
		
		// insert child data
		
		
	public void insertChildData(Sup_Window data) {

			
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getCat_id().size(); i++) {

					values.put("BRAND_CD", data.getCat_id().get(i));
					values.put("BRAND", data.getCat_name().get(i));
					values.put("STATUS", Integer.parseInt(data.getBrand_id().get(i)));
					values.put("COMMONID", data.getBrand_name().get(i));
					values.put("KEY_REMARK", Integer.parseInt(data.getBrand_id().get(i)));
					values.put("QUANTITY", data.getBrand_name().get(i));
					
					
					db.insert("CHILD_DATA", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}
	
	
	public void InsertWindowData(long mid,
            HashMap<SecondaryWindowBean, List<SecondaryWindowBean>> data,List<SecondaryWindowBean> save_listDataHeader) {

     ContentValues values = new ContentValues();
     ContentValues values1 = new ContentValues();

     try {

            db.beginTransaction();

            for (int i = 0; i < save_listDataHeader.size(); i++) {

                
                  values.put(CommonString.KEY_CATEGORY_ID, save_listDataHeader.get(i)
                          .getCate_id());

                  values.put(CommonString.KEY_CATEGORY, save_listDataHeader.get(i)
                                .getCate_name());
                  values.put("STATUS", "P");
                  
                  values.put("COMMONID", String.valueOf(mid));

                  long l = db.insert(CommonString.TABLE_HEADER_DATA,
                                null, values);
                  
                  for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                         values1.put(CommonString.KEY_COMMMON_ID, l);
                         values.put("STATUS", "P");
                       values1.put(CommonString.KEY_BRAND_ID,
                                     data.get(save_listDataHeader.get(i)).get(j)
                                                   .getBrand_id());
                         
                       values1.put(CommonString.KEY_BRAND,
                               data.get(save_listDataHeader.get(i)).get(j)
                                             .getBrand_name());
                       
                       
                         values1.put(CommonString.KEY_AVAILABILITY,
                                       data.get(save_listDataHeader.get(i)).get(j)
                                                     .getAvailable());
                         
                         values1.put(CommonString.KEY_REMARK_STORE,
                                       data.get(save_listDataHeader.get(i)).get(j)
                                                     .getRemark());
                         

                         db.insert(CommonString.TABLE_CHILD_DATA,
                                       null, values1);

                  }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
     } catch (Exception ex) {
            Log.d("Database Exception while Inserting Asset Data ",
                         ex.toString());
     }

}
	*/
	

}
