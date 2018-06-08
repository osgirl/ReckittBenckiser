package com.cpm.download;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.TableBean;
import com.cpm.capitalfoods.R;
import com.cpm.fragment.MainFragment;
import com.cpm.message.AlertMessage;
import com.cpm.xmlGetterSetter.AssetMasterGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.ColdroomClosingGetterSetter;
import com.cpm.xmlGetterSetter.CompanyGetterSetter;
import com.cpm.xmlGetterSetter.DeepFreezerGetterSetter;
import com.cpm.xmlGetterSetter.DesignationGetterSetter;
import com.cpm.xmlGetterSetter.Deviation_Reason;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.MappingAssetGetterSetter;
import com.cpm.xmlGetterSetter.MappingAvailabilityGetterSetter;
import com.cpm.xmlGetterSetter.MappingPromotionGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingReasonGetterSetter;
import com.cpm.xmlGetterSetter.PerformanceGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;

import com.cpm.xmlGetterSetter.Sup_Merchandiser;
import com.cpm.xmlGetterSetter.Sup_Performance;
import com.cpm.xmlGetterSetter.Sup_Window;
import com.cpm.xmlGetterSetter.SupervisorGetterSetter;
import com.cpm.xmlHandler.XMLHandlers;

public class CompleteDownloadActivity extends AppCompatActivity {
	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	private Data data;
	int eventType;
	//MerchndiserGetterSetter merchandiserData;
	JourneyPlanGetterSetter jcpgettersetter;
	SkuMasterGetterSetter skumastergettersetter;
	
	
	
	MappingAvailabilityGetterSetter mappingavailgettersetter;
	MappingPromotionGetterSetter mappingprormotgettersetter;
	MappingAssetGetterSetter mappingassetgettersetter;
	DeepFreezerGetterSetter deepfreezgettersetter;
	AssetMasterGetterSetter assetmastergettersetter;
	CompanyGetterSetter companyGetterSetter;
	NonWorkingReasonGetterSetter nonworkinggettersetter;
	CategoryMasterGetterSetter categorygettersetter;
	
	PerformanceGetterSetter performanceGetterSetter;
	
	ColdroomClosingGetterSetter closingGetterSetter;
	
	//NonWrkingMasterGetterSetter nonworkingData;
	SupervisorGetterSetter ditributorlist;
	//StoreListGetterSetter storeTable;
	GSKDatabase db;
	DesignationGetterSetter designation;
	JourneyPlanGetterSetter journeyplanMerchan;
	Deviation_Reason deviation_Reason;
	Sup_Window supWin;
	Sup_Performance supperformance;
	Sup_Merchandiser supMerchandiser;

	TableBean tb;
	String _UserId;
	private SharedPreferences preferences;
	
	boolean flag_cold_stock=true;

	boolean promotion_flag=true;
	boolean asset_flag=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.mainpage);

		setContentView(R.layout.activity_main_menu);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		_UserId = preferences.getString(CommonString.KEY_USERNAME, "");
		tb = new TableBean();
		db = new GSKDatabase(this);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);


		new BackgroundTask(this).execute();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// db.open();

		FragmentManager fragmentManager = getSupportFragmentManager();


		MainFragment cartfrag = new MainFragment();

		fragmentManager.beginTransaction()
				.replace(R.id.frame_layout, cartfrag)
				.commit();

	}

	//	@Override
	//	protected void onStop() {
	//		// TODO Auto-generated method stub
	//		super.onStop();
	////		db.close();
	//	}
	class Data {
		int value;
		String name;
	}

	private class BackgroundTask extends AsyncTask<Void, Data, String> {
		private Context context;

		BackgroundTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new Dialog(context);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom);
			//dialog.setTitle("Download Files");
			dialog.setCancelable(false);
			dialog.show();
			pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
			percentage = (TextView) dialog.findViewById(R.id.percentage);
			message = (TextView) dialog.findViewById(R.id.message);

		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String resultHttp="";
			try {

				data = new Data();

				// JCP

				XmlPullParserFactory factory = XmlPullParserFactory
						.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();

				SoapObject request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "JOURNEY_PLAN");

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				HttpTransportSE androidHttpTransport = new HttpTransportSE(
						CommonString.URL);

				androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL,
						envelope);
				Object result = (Object) envelope.getResponse();

				if(result.toString()!=null){

					xpp.setInput(new StringReader(result.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					jcpgettersetter = XMLHandlers.JCPXMLHandler(xpp, eventType);

					if(jcpgettersetter.getStore_cd().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						String jcpTable = jcpgettersetter.getTable_journey_plan();
						TableBean.setjcptable(jcpTable);

					}else{
						return "JOURNEY_PLAN";
					}

					data.value = 10;
					data.name = "JCP Data Downloading";

				}

				publishProgress(data);

				
				// Store List Master
				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "SKU_MASTER");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				result = (Object) envelope.getResponse();

				if(result.toString()!=null){
					xpp.setInput(new StringReader(result.toString()));
					xpp.next();
					eventType = xpp.getEventType();
					skumastergettersetter =XMLHandlers.storeListXML(xpp, eventType);
					if(skumastergettersetter.getSku_cd().size()>0){
						String skutable = skumastergettersetter.getSku_master_table();
						if(skutable!=null){
							resultHttp = CommonString.KEY_SUCCESS;
							TableBean.setSkumastertable(skutable);
						}
					}else{
						return "SKU_MASTER";
					}

					data.value = 20;
					data.name = "Store Data Download";
				}

				publishProgress(data);
				
				

				// Mapping Availability data

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "MAPPING_AVAILABILITY");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultnonWorking = (Object) envelope.getResponse();

				if(resultnonWorking.toString()!=null){

					xpp.setInput(new StringReader(resultnonWorking.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					mappingavailgettersetter = XMLHandlers.mappingavailXML(xpp, eventType);

					if(mappingavailgettersetter.getMapping_avail_table() !=null){
						String mappingtable = mappingavailgettersetter.getMapping_avail_table();
						TableBean.setMappingavailtable(mappingtable);
					}
					
					if(mappingavailgettersetter.getSku_cd().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						data.value = 30;
						data.name = "Mapping data Downloading";

					}/*else{
						return "MAPPING_AVAILABILITY";
					}*/

				}

				publishProgress(data);


				// Mapping Promotion data


				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "MAPPING_PROMOTION");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultmapping = (Object) envelope.getResponse();

				if(resultmapping.toString()!=null){

					xpp.setInput(new StringReader(resultmapping.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					mappingprormotgettersetter = XMLHandlers.mappingpromotXML(xpp, eventType);

					if(mappingprormotgettersetter.getMapping_promotion_table() !=null){
						String mappingtable = mappingprormotgettersetter.getMapping_promotion_table();
						TableBean.setMappingpromotable(mappingtable);
					}
					
					if(mappingprormotgettersetter.getPid().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						
						data.value = 40;
						data.name = "Mapping Promotion Data Downloading";

					}else{
						//return "MAPPING_PROMOTION";
						promotion_flag=false;
					}

				}

				publishProgress(data);

				
				// Mapping Asset data


				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "MAPPING_ASSET");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultmappingasst = (Object) envelope.getResponse();

				if(resultmappingasst.toString()!=null){

					xpp.setInput(new StringReader(resultmappingasst.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					mappingassetgettersetter = XMLHandlers.mappingassetXML(xpp, eventType);
					
					if(mappingassetgettersetter.getMapping_asset_table() !=null){
						String mappingtable = mappingassetgettersetter.getMapping_asset_table();
						TableBean.setMappingassettable(mappingtable);
					}

					if(mappingassetgettersetter.getStore_cd().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						
						data.value = 50;
						data.name = "Mapping Asset Data Downloading";

					}else{
						//return "MAPPING_ASSET";
						asset_flag = false;
					}

				}

				publishProgress(data);


				// Asset Master data

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "ASSET_MASTER");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultasset = (Object) envelope.getResponse();

				if(resultasset.toString()!=null){

					xpp.setInput(new StringReader(resultasset.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					assetmastergettersetter = XMLHandlers.assetMasterXML(xpp, eventType);

					if(assetmastergettersetter.getAsset_cd().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						String assettable = assetmastergettersetter.getAsset_master_table();
						TableBean.setAssetmastertable(assettable);

					}else{
						return "ASSET_MASTER";
					}

					data.value = 70;
					data.name = "Asset Master Data Downloading";

				}

				publishProgress(data);


				// Company Master data

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "COMPANY_MASTER");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultcompany = (Object) envelope.getResponse();

				if(resultcompany.toString()!=null){

					xpp.setInput(new StringReader(resultcompany.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					companyGetterSetter = XMLHandlers.comapnyMasterXML(xpp, eventType);

					if(companyGetterSetter.getCompany_cd().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						String companytable = companyGetterSetter.getCompany_master_table();
						TableBean.setCompanytable(companytable);

					}else{
						return "COMPANY_MASTER";
					}

					data.value = 80;
					data.name = "Company Master Data Downloading";

				}

				publishProgress(data);

				//Non Working Reason data

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "NON_WORKING_REASON_NEW");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultnonworking = (Object) envelope.getResponse();

				if(resultnonworking.toString()!=null){

					xpp.setInput(new StringReader(resultnonworking.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					nonworkinggettersetter = XMLHandlers.nonWorkinReasonXML(xpp, eventType);

					if(nonworkinggettersetter.getReason_cd().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						String nonworkingtable = nonworkinggettersetter.getNonworking_table();
						TableBean.setNonworkingtable(nonworkingtable);

					}else{
						return "NON_WORKING_REASON_NEW";
					}

					data.value = 90;
					data.name = "Non Working Reason Downloading";

				}

				publishProgress(data);

				
				// DeepFreezer Master data

/*				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "DEEPFREEZER_MASTER");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultdeepfreezer = (Object) envelope.getResponse();

				if(resultdeepfreezer.toString()!=null){

					xpp.setInput(new StringReader(resultdeepfreezer.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					deepfreezgettersetter = XMLHandlers.deepfreezXML(xpp, eventType);

					if(deepfreezgettersetter.getFid().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						String mappingtable = deepfreezgettersetter.getDeepfreeztable();
						TableBean.setDeepfreezertable(mappingtable);

					}else{
						return "DEEPFREEZER_MASTER";
					}

					data.value = 60;
					data.name = "DeepFreezer Data Downloading";

				}

				publishProgress(data);
				

				
				
				//Performance Data
				
				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "MY_PERFORMANCE");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultperformance = (Object) envelope.getResponse();

				if(resultperformance.toString()!=null){

					xpp.setInput(new StringReader(resultperformance.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					performanceGetterSetter = XMLHandlers.performanceXML(xpp, eventType);

					if(performanceGetterSetter.getStore_cd().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						String performancetable = performanceGetterSetter.getPerformance_table();
						TableBean.setPerformancetable(performancetable);

					}else{
						return "MY_PERFORMANCE";
					}
                          
					data.value = 75;
					data.name = "Performance Data Downloading";

				}

				publishProgress(data);
				
				//---------------
				//closing cold stock data
				
				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "COLD_STOCK");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultclosing = (Object) envelope.getResponse();

				if(resultclosing.toString()!=null){

					xpp.setInput(new StringReader(resultclosing.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					closingGetterSetter = XMLHandlers.closincoldXML(xpp, eventType);

					if(closingGetterSetter.getStore_cd().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						String closingcoldtable = closingGetterSetter.getClosing_cold_table();
						TableBean.setClosingcoldtable(closingcoldtable);

					}else if(closingGetterSetter.getClosing_cold_table()!=null){
						String closingcoldtable = closingGetterSetter.getClosing_cold_table();
						TableBean.setClosingcoldtable(closingcoldtable);
						flag_cold_stock=false;
					}
					else{
						return "COLD_STOCK";
					}
                          
					data.value = 75;
					data.name = "Coldroom Data Downloading";

				}

				publishProgress(data);
				


				
				//Category master data

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);

				request.addProperty("UserName", _UserId);
				request.addProperty("Type", "CATEGORY_MASTER");

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_UNIVERSAL, envelope);
				Object	resultcategorymaster = (Object) envelope.getResponse();

				if(resultcategorymaster.toString()!=null){

					xpp.setInput(new StringReader(resultcategorymaster.toString()));
					xpp.next();
					eventType = xpp.getEventType();

					categorygettersetter = XMLHandlers.categoryMasterXML(xpp, eventType);

					if(categorygettersetter.getCategory_cd().size()>0){
						resultHttp = CommonString.KEY_SUCCESS;
						String categorytable = categorygettersetter.getCategory_master_table();
						TableBean.setCategorymastertable(categorytable);

					}else{
						return "CATEGORY_MASTER";
					}
                          
					data.value = 90;
					data.name = "Category Master Downloading";

				}
*/
				publishProgress(data);


				db.open();
				db.insertJCPData(jcpgettersetter);
				db.insertSkuMasterData(skumastergettersetter);
				db.insertMappingAvailData(mappingavailgettersetter);
				if(promotion_flag){
					db.insertMappingPromotionData(mappingprormotgettersetter);
				}

				if(asset_flag){
					db.insertMappingAssetData(mappingassetgettersetter);
				}

				//db.insertDeepFreezerData(deepfreezgettersetter);
				db.insertAssetMasterData(assetmastergettersetter);
				db.insertCompanyMasterData(companyGetterSetter);
				db.insertNonWorkingReasonData(nonworkinggettersetter);
				/*db.insertCategoryMasterData(categorygettersetter);
				db.insertPerformanceData(performanceGetterSetter);
				
				if(flag_cold_stock){
					db.insertClosingColdData(closingGetterSetter);
				}*/
				
				
				data.value = 100;
				data.name = "Finishing";
				publishProgress(data);

				return resultHttp;

			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});

			} catch (IOException e) {
				final AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket", e);

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						message.showMessage();

					}
				});

			}

			catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_EXCEPTION + e, "download", e);

				e.getMessage();
				e.printStackTrace();
				e.getCause();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {

						message.showMessage();
					}
				});
			}

			return "";
		}

		@Override
		protected void onProgressUpdate(Data... values) {
			// TODO Auto-generated method stub

			pb.setProgress(values[0].value);
			percentage.setText(values[0].value + "%");
			message.setText(values[0].name);

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();

			if (result.equals(CommonString.KEY_SUCCESS)) {
				AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_DOWNLOAD, "success", null);
				message.showMessage();
			} else  {
				AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_JCP_FALSE + result, "success", null);
				message.showMessage();
			}


		}

	}



}
