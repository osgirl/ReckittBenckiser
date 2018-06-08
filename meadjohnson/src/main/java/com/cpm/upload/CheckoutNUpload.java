package com.cpm.upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;

import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.message.AlertMessage;

import com.cpm.xmlGetterSetter.AssetInsertdataGetterSetter;
import com.cpm.xmlGetterSetter.CallsGetterSetter;
import com.cpm.xmlGetterSetter.CompetitionPromotionGetterSetter;
import com.cpm.xmlGetterSetter.DeepFreezerTypeGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetionCompanyGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetitorGetterSetter;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlGetterSetter.FoodStoreInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.POIGetterSetter;
import com.cpm.xmlGetterSetter.PromotionInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.StockGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;
import com.cpm.xmlHandler.FailureXMLHandler;

public class CheckoutNUpload extends Activity{

	ArrayList<JourneyPlanGetterSetter> jcplist;
	GSKDatabase db;
	private SharedPreferences preferences;
	private String username, visit_date, store_id, store_intime, store_out_time,date,prev_date,result;

	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	private Data data;
	public static String currLatitude = "0.0";
	public static String currLongitude = "0.0";
	ArrayList<CoverageBean> coverageBean;
	
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	private int factor, k;
	String app_ver;
	String datacheck = "";
	String[] words;
	String validity, storename;
	int mid;
	String sod = "";
	String total_sku = "";
	String sku = "";
	String sos_data = "";
	String category_data = "";
	String errormsg = "",status;
	boolean up_success_flag=true;
	static int counter = 1;
	
	String Path;
	boolean image_valid;
	
	private ArrayList<DeepFreezerTypeGetterSetter> deepfreezerData = new ArrayList<DeepFreezerTypeGetterSetter>();
	private ArrayList<FacingCompetitorGetterSetter> facingCompetitorData = new ArrayList<FacingCompetitorGetterSetter>();

	private ArrayList<FoodStoreInsertDataGetterSetter> foodStoredata = new ArrayList<FoodStoreInsertDataGetterSetter>();
	private ArrayList<CallsGetterSetter> callsData = new ArrayList<CallsGetterSetter>();
	private FailureGetterSetter failureGetterSetter = null;

	private ArrayList<AssetInsertdataGetterSetter> assetInsertdata = new ArrayList<AssetInsertdataGetterSetter>();
	private ArrayList<PromotionInsertDataGetterSetter> promotionData = new ArrayList<PromotionInsertDataGetterSetter>();
	//private ArrayList<FoodStoreInsertDataGetterSetter> foodStoredata = new ArrayList<FoodStoreInsertDataGetterSetter>();
	private ArrayList<StockNewGetterSetter> stockData = new ArrayList<StockNewGetterSetter>();
	private ArrayList<StockNewGetterSetter> stockImgData = new ArrayList<StockNewGetterSetter>();
	ArrayList<POIGetterSetter> poiData=new ArrayList<POIGetterSetter>();
	ArrayList<POIGetterSetter> competitionpoiData=new ArrayList<POIGetterSetter>();
	ArrayList<FacingCompetionCompanyGetterSetter> facingcompetition=new ArrayList<FacingCompetionCompanyGetterSetter>();
	ArrayList<CompetitionPromotionGetterSetter> promotioncompetitionData = new ArrayList<CompetitionPromotionGetterSetter>();
	private ArrayList<POIGetterSetter> poiInsertdata = new ArrayList<POIGetterSetter>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.checkout_n_upload);

		db = new GSKDatabase(this);
		db.open();

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		app_ver = preferences.getString(CommonString.KEY_VERSION, "");
		/*store_intime = preferences
				.getString(CommonString.KEY_STORE_IN_TIME, "");*/
		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		currLatitude = preferences.getString(CommonString.KEY_LATITUDE, "0.0");
		currLongitude = preferences
				.getString(CommonString.KEY_LONGITUDE, "0.0");

		Path= Environment.getExternalStorageDirectory()
				+ "/CapitalFoods_Images/";
		
		if(!isCheckoutDataExist()){
			new UploadTask(this).execute();
		}

	}

	public boolean isCheckoutDataExist(){

		boolean flag=false;

		jcplist=db.getAllJCPData();

		for(int i=0;i<jcplist.size();i++){

			if(!jcplist.get(i).getVISIT_DATE().get(0).equals(visit_date)){
				
				prev_date=jcplist.get(i).getVISIT_DATE().get(0);
				
				if(jcplist.get(i).getCheckOutStatus().get(0).equals(CommonString.KEY_VALID)){
					
					store_id = jcplist.get(i).getStore_cd().get(0);

					coverageBean = db.getCoverageSpecificData(store_id);
					
					if(coverageBean.size()>0){

						flag=true;
						
						username = coverageBean.get(0).getUserId();
						store_intime = coverageBean.get(0).getInTime();
						store_out_time = coverageBean.get(0).getOutTime();
						if(store_out_time ==null || store_out_time.equals("")){
							store_out_time = getCurrentTime();
						}
						date = coverageBean.get(0).getVisitDate();
						
						new BackgroundTask(this).execute();
					}

				}

			}
		}

		return flag;
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
			dialog.setContentView(R.layout.custom);
			dialog.setTitle("Uploading Checkout Data");
			dialog.setCancelable(false);
			dialog.show();
			pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
			percentage = (TextView) dialog.findViewById(R.id.percentage);
			message = (TextView) dialog.findViewById(R.id.message);

		}

		@SuppressWarnings("deprecation")
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {

				//String result = "";
				data = new Data();

				data.value = 20;
				data.name = "Checked out Data Uploading";
				publishProgress(data);


				String onXML = "[STORE_CHECK_OUT_STATUS][USER_ID]"
						+ username
						+ "[/USER_ID]" + "[STORE_ID]"
						+ store_id
						+ "[/STORE_ID][LATITUDE]"
						+ currLatitude
						+ "[/LATITUDE][LOGITUDE]"
						+ currLongitude
						+ "[/LOGITUDE][CHECKOUT_DATE]"
						+ date
						+ "[/CHECKOUT_DATE][CHECK_OUTTIME]"
						+ store_out_time
						+ "[/CHECK_OUTTIME][CHECK_INTIME]"
						+ store_intime
						+ "[/CHECK_INTIME][CREATED_BY]"
						+ username
						+ "[/CREATED_BY][/STORE_CHECK_OUT_STATUS]";


				final String sos_xml = "[DATA]" + onXML
						+ "[/DATA]";

				SoapObject request = new SoapObject(
						CommonString.NAMESPACE,
						"Upload_Store_ChecOut_Status");
				request.addProperty("onXML", sos_xml);
				/*request.addProperty("KEYS", "CHECKOUT_STATUS");
			request.addProperty("USERNAME", username);*/
				//request.addProperty("MID", mid);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				HttpTransportSE androidHttpTransport = new HttpTransportSE(
						CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION+"Upload_Store_ChecOut_Status",
						envelope);
				Object result = (Object) envelope.getResponse();


				if (result.toString().equalsIgnoreCase(
						CommonString.KEY_NO_DATA)) {
					return "Upload_Store_ChecOut_Status";
				}

				if (result.toString().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					return "Upload_Store_ChecOut_Status";
				}

				// for failure



				data.value = 100;
				data.name = "Checkout Done";
				publishProgress(data);

				db.updateCoverageStoreOutTime(store_id,date , store_out_time, CommonString.KEY_C);
				
				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_SUCCESS_chkout)) {

					SharedPreferences.Editor editor = preferences.edit();
					editor.putString(CommonString.KEY_STOREVISITED, "");
					editor.putString(CommonString.KEY_STOREVISITED_STATUS, "");
					editor.putString(CommonString.KEY_STORE_IN_TIME, "");
					editor.putString(CommonString.KEY_LATITUDE, "");
					editor.putString(CommonString.KEY_LONGITUDE, "");
					editor.commit();

					db.updateStoreStatusOnCheckout(store_id, visit_date,
							CommonString.KEY_C);

				} else {
					if (result.toString().equalsIgnoreCase(
							CommonString.KEY_FALSE)) {

						return CommonString.METHOD_Checkout_StatusNew;
					}

					// for failure

				}
				return CommonString.KEY_SUCCESS;

			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						CheckoutNUpload.this,
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
						CheckoutNUpload.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION,
						"socket", e);
				// counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {

						message.showMessage();
						// TODO Auto-generated method stub
						/*
						 * if (counter < 10) { new
						 * BackgroundTask(CheckOutUploadActivity
						 * .this).execute(); } else { message.showMessage();
						 * counter =1; }
						 */
					}
				});
			} catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						CheckoutNUpload.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
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

				new UploadTask(CheckoutNUpload.this).execute();
				
			} else if (!result.equals("")) {
				/*AlertMessage message = new AlertMessage(
					CheckOutStoreActivity.this, CommonString.ERROR + result, "success", null);
			message.showMessage();*/

				Toast.makeText(getApplicationContext(), "Network Error Try Again", Toast.LENGTH_SHORT).show();
				finish();

			}

		}

	}

	class Data {
		int value;
		String name;
	}
	
	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}

	
	public String UploadImage(String path,String store_cd) throws Exception {

		errormsg = "";
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(Path + path, o);

		// The new size we want to scale to
		final int REQUIRED_SIZE = 1024;

		// Find the correct scale value. It should be the power of 2.
		int width_tmp = o.outWidth, height_tmp = o.outHeight;
		int scale = 1;

		while (true) {
			if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
				break;
			width_tmp /= 2;
			height_tmp /= 2;
			scale *= 2;
		}

		// Decode with inSampleSize
		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize = scale;
		Bitmap bitmap = BitmapFactory.decodeFile(
				Path + path, o2);

		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
		byte[] ba = bao.toByteArray();
		String ba1 = Base64.encodeBytes(ba);

		SoapObject request = new SoapObject(CommonString.NAMESPACE,
				CommonString.METHOD_UPLOAD_IMAGE);

		String[] split = path.split("/");
		String path1 = split[split.length - 1];

		request.addProperty("img", ba1);
		request.addProperty("name", path1);
		request.addProperty("FolderName", "StoreImages");

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				CommonString.URL);

		androidHttpTransport
				.call(CommonString.SOAP_ACTION_UPLOAD_IMAGE,
						envelope);
		Object result = (Object) envelope.getResponse();

		if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

			if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
				return CommonString.KEY_FALSE;
			}

			SAXParserFactory saxPF = SAXParserFactory.newInstance();
			SAXParser saxP = saxPF.newSAXParser();
			XMLReader xmlR = saxP.getXMLReader();

			// for failure
			FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
			xmlR.setContentHandler(failureXMLHandler);

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(result.toString()));
			xmlR.parse(is);

			failureGetterSetter = failureXMLHandler
					.getFailureGetterSetter();

			if (failureGetterSetter.getStatus().equalsIgnoreCase(
					CommonString.KEY_FAILURE)) {
				errormsg = failureGetterSetter.getErrorMsg();
				return CommonString.KEY_FAILURE;
			}
		} else {
			new File(Path + path).delete();
			SharedPreferences.Editor editor = preferences
					.edit();
			editor.putString(CommonString.KEY_STOREVISITED_STATUS, "");
			editor.commit();
		}

		return "";
	}

	private class UploadTask extends AsyncTask<Void, Data, String> {
		private Context context;

		UploadTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new Dialog(context);
			dialog.setContentView(R.layout.custom_upload);
			dialog.setTitle("Uploading Data");
			dialog.setCancelable(false);
			dialog.show();
			pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
			percentage = (TextView) dialog.findViewById(R.id.percentage);
			message = (TextView) dialog.findViewById(R.id.message);
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {

				data = new Data();

				/*HttpParams myParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(myParams, 10000);
				HttpConnectionParams.setSoTimeout(myParams, 10000);
				HttpClient httpclient = new DefaultHttpClient();
				InputStream inputStream = null;*/

				coverageBeanlist = db.getCoverageData(prev_date);

				if (coverageBeanlist.size() > 0) {

					if (coverageBeanlist.size() == 1) {
						factor = 50;
					} else {

						factor = 100 / (coverageBeanlist.size());
					}
				}

				for (int i = 0; i < coverageBeanlist.size(); i++) {

					/* storestatus = db.getStoreStatus(coverageBeanlist.get(
							 i).getStoreId());
					 */
					//					if (upload_status) {
					//						storestatus.setCheckout_status("C");
					//					}

					//					if ((storestatus.getCheckout_status().equalsIgnoreCase(
					//
					//					CommonString.KEY_L) || storestatus.getCheckout_status()
					//							.equalsIgnoreCase(
					//
					//							CommonString.KEY_C))) {

					// if (true) {

					if (!coverageBeanlist.get(i).getStatus()
							.equalsIgnoreCase(CommonString.KEY_D) && !coverageBeanlist.get(i).getStatus()
							.equalsIgnoreCase(CommonString.KEY_CHECK_IN)) {


						String onXML = "[DATA][USER_DATA][STORE_CD]"
								+ coverageBeanlist.get(i).getStoreId()
								+ "[/STORE_CD]" + "[VISIT_DATE]"
								+ coverageBeanlist.get(i).getVisitDate()
								+ "[/VISIT_DATE][LATITUDE]"
								+ coverageBeanlist.get(i).getLatitude()
								+ "[/LATITUDE][APP_VERSION]"
								+ app_ver
								+ "[/APP_VERSION][LONGITUDE]"
								+ coverageBeanlist.get(i).getLongitude()
								+ "[/LONGITUDE][IN_TIME]"
								+ coverageBeanlist.get(i).getInTime()
								+ "[/IN_TIME][OUT_TIME]"
								+ coverageBeanlist.get(i).getOutTime()
								+ "[/OUT_TIME][UPLOAD_STATUS]"
								+ "N"
								+ "[/UPLOAD_STATUS][USER_ID]" + username
								+ "[/USER_ID][IMAGE_URL]" + coverageBeanlist.get(i).getImage()
								+ "[/IMAGE_URL][REASON_ID]"
								+ coverageBeanlist.get(i).getReasonid()
								+ "[/REASON_ID][REASON_REMARK]"
								+ coverageBeanlist.get(i).getRemark()
								+ "[/REASON_REMARK][/USER_DATA][/DATA]";


						SoapObject request = new SoapObject(
								CommonString.NAMESPACE,
								CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE);
						request.addProperty("onXML", onXML);

						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);

						HttpTransportSE androidHttpTransport = new HttpTransportSE(
								CommonString.URL);

						androidHttpTransport.call(
								CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE,
								envelope);
						Object result = (Object) envelope.getResponse();

						datacheck = result.toString();
						datacheck = datacheck.replace("\"", "");
						words = datacheck.split("\\;");
						validity = (words[0]);

						if (validity
								.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
							db.updateCoverageStatus(coverageBeanlist
									.get(i).getMID(), CommonString.KEY_P);

							db.updateStoreStatusOnLeave(
									coverageBeanlist.get(i).getStoreId(),
									coverageBeanlist.get(i).getVisitDate(),
									CommonString.KEY_P);
						} else {
							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FALSE)) {
								return CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE;
							}
							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE;
							}

						}

						mid = Integer.parseInt((words[1]));
						//	mid=1;
						//mid = Integer.parseInt(result);

						//							if (!(coverageBeanlist.get(i).getReasonid()
						//									.equalsIgnoreCase("") || coverageBeanlist
						//									.get(i).getReasonid().equalsIgnoreCase("0"))) {
						//
						//								System.out.println("");
						//							} else {

						//	JSONArray collectiontoSend = new JSONArray();

						//	uploading Deepfreezer data

						data.value =30;
						data.name = "Uploading";

						publishProgress(data);

						String final_xml = "";
							/*onXML = "";
						deepfreezerData = db.getDFTypeUploadData(coverageBeanlist.get(i).getStoreId());

						if (deepfreezerData.size()>0) {

							for (int j = 0; j < deepfreezerData.size(); j++) {

								String data = "[DEEPFREEZER_DATA][FID]"
										+ deepfreezerData.get(j).getFid()
										+ "[/FID]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[STATUS]"
										+ deepfreezerData.get(j).getStatus()
										+ "[/STATUS]"
										+ "[REMARK]"
										+ deepfreezerData.get(j).getRemark()
										+ "[/REMARK]"
										+ "[/DEEPFREEZER_DATA]";
									onXML=onXML+data;

							}

							String finaldata = "[DATA]"+onXML+"[/DATA]";
							*//*final String sos_xml = "[MID]"
										+ mid
										+ "[/MID][KEY]"
										+ "DEEPFREEZER_DATA"
										+ "[/KEY][XMLDATA]"
										+ onXML
										+ "[/XMLDATA][USERNAME]"
										+ username
										+ "[/USERNAME]";*//*


							 request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", finaldata);
							request.addProperty("KEYS", "DEEPFREEZER_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							 envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

						androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

						}*/


						//	uploading Facing Competitor data

						/*final_xml = "";
						onXML = "";
						facingCompetitorData = db.getFacingCompetitorData(coverageBeanlist.get(i).getStoreId());

						if (facingCompetitorData.size()>0) {

							for (int j = 0; j < facingCompetitorData.size(); j++) {

								onXML = "[FACING_COMPETITOR_DATA][CATEGORY_CD]"
										+ facingCompetitorData.get(j).getCategory_cd()
										+ "[/CATEGORY_CD]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[MCCAIN_DF]"
										+ facingCompetitorData.get(j).getMccaindf()
										+ "[/MCCAIN_DF]"
										+ "[STORE_DF]"
										+ facingCompetitorData.get(j).getStoredf()
										+ "[/STORE_DF]"
										+ "[/FACING_COMPETITOR_DATA]";

								final_xml = final_xml + onXML;

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							 request = new SoapObject(
										CommonString.NAMESPACE,
										CommonString.METHOD_UPLOAD_XML);
								request.addProperty("XMLDATA", sos_xml);
								request.addProperty("KEYS", "FACING_COMPETITOR_DATA");
								request.addProperty("USERNAME", username);
								request.addProperty("MID", mid);

								 envelope = new SoapSerializationEnvelope(
										SoapEnvelope.VER11);
								envelope.dotNet = true;
								envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
										CommonString.URL);

								androidHttpTransport.call(
										CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
										envelope);
								result = (Object) envelope.getResponse();


								if (result.toString().equalsIgnoreCase(
										CommonString.KEY_NO_DATA)) {
									return CommonString.METHOD_UPLOAD_XML;
								}

								if (result.toString().equalsIgnoreCase(
										CommonString.KEY_FAILURE)) {
									return CommonString.METHOD_UPLOAD_XML;
								}

							}*/




						//	uploading Asset data

						final_xml = "";
						onXML = "";
						assetInsertdata = db.getAssetUpload(coverageBeanlist.get(i).getStoreId());

						if (assetInsertdata.size()>0) {

							for (int j = 0; j < assetInsertdata.size(); j++) {

								onXML = "[ASSET_DATA][ASSET_CD]"
										+ assetInsertdata.get(j).getAsset_cd()
										+ "[/ASSET_CD]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[PRESENT]"
										+ assetInsertdata.get(j).getPresent()
										+ "[/PRESENT]"
										+ "[BRAND_CD]"
										+ assetInsertdata.get(j).getBrand_cd()
										+ "[/BRAND_CD]"
										+ "[REMARK]"
										+ assetInsertdata.get(j).getRemark()
										+ "[/REMARK]"
										+ "[IMAGE_URL]"
										+ assetInsertdata.get(j).getImg()
										+ "[/IMAGE_URL]"
										+ "[/ASSET_DATA]";

								final_xml = final_xml + onXML;

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", sos_xml);
							request.addProperty("KEYS", "ASSET_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}



						}


//							uploading Promotion data

						final_xml = "";
						onXML = "";
						promotionData = db.getPromotionUpload(coverageBeanlist.get(i).getStoreId());

						if (promotionData.size()>0) {

							for (int j = 0; j < promotionData.size(); j++) {

								onXML = "[PROMOTION_DATA]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[PID]"
										+ promotionData.get(j).getPid()
										+ "[/PID]"
										+ "[PROMOTION]"
										+ promotionData.get(j).getPromotion_txt()
										+ "[/PROMOTION]"
										+ "[PRESENT]"
										+ promotionData.get(j).getPresent()
										+ "[/PRESENT]"
										+ "[REMARK]"
										+ promotionData.get(j).getRemark()
										+ "[/REMARK]"
										+ "[IMAGE_URL]"
										+ promotionData.get(j).getImg()
										+ "[/IMAGE_URL]"
										+ "[BRAND_CD]"
										+ promotionData.get(j).getBrand_cd()
										+ "[/BRAND_CD]"
										+ "[/PROMOTION_DATA]";

								final_xml = final_xml + onXML;

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", sos_xml);
							request.addProperty("KEYS", "PROMOTION_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

						}


						//		uploading Food Store data

						/*final_xml = "";
						onXML = "";
						foodStoredata = db.getFoodStoreUpload(coverageBeanlist.get(i).getStoreId());

						if (foodStoredata.size()>0) {

							for (int j = 0; j < foodStoredata.size(); j++) {

								onXML = "[FOOD_STORE_DATA][SKU_CD]"
										+ foodStoredata.get(j).getSku_cd()
										+ "[/SKU_CD]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[ACTUAL_LISTED]"
										+ foodStoredata.get(j).getActual_listed()
										+ "[/ACTUAL_LISTED]"
										+ "[MCCAIN_DF]"
										+ foodStoredata.get(j).getMccain_df()
										+ "[/MCCAIN_DF]"
										+ "[STORE_DF]"
										+ foodStoredata.get(j).getStore_df()
										+ "[/STORE_DF]"
										+ "[MTD_SALES]"
										+ foodStoredata.get(j).getMtd_sales()
										+ "[/MTD_SALES]"
										+ "[PACKING_SIZE]"
										+ foodStoredata.get(j).getPacking_size()
										+ "[/PACKING_SIZE]"
										+ "[BRAND_CD]"
										+ foodStoredata.get(j).getBrand_cd()
										+ "[/BRAND_CD]"
										+ "[/FOOD_STORE_DATA]";

								final_xml = final_xml + onXML;

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";


							request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", sos_xml);
							request.addProperty("KEYS", "FOOD_STORE_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							 envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

						androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

						}*/

//						uploading Stock data

						final_xml = "";
						onXML = "";
						stockData = db.getStockUpload(coverageBeanlist.get(i).getStoreId());

						if (stockData.size()>0) {

							for (int j = 0; j < stockData.size(); j++) {

								/*String actual_listed;
								if(stockData.get(j).getActual_listed().equalsIgnoreCase("yes")){
									actual_listed="1";
								}else{
									actual_listed="0";
								}*/

								onXML = "[STOCK_DATA][SKU_CD]"
										+ stockData.get(j).getSku_cd()
										+ "[/SKU_CD]"
										+ "[BRAND_CD]"
										+ stockData.get(j).getBrand_cd()
										+ "[/BRAND_CD]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[OPENING_TOTAL_STOCK]"
										+ stockData.get(j).getOpenning_total_stock()
										+ "[/OPENING_TOTAL_STOCK]"
										+ "[FACING]"
										+ stockData.get(j).getOpening_facing()
										+ "[/FACING]"
										+ "[STOCK_UNDER_DAYS]"
										+ stockData.get(j).getStock_under45days()
										+ "[/STOCK_UNDER_DAYS]"
										+ "[CLOSING_STOCK]"
										+ stockData.get(j).getClosing_stock()
										+ "[/CLOSING_STOCK]"
										+ "[MIDDAY_TOTAL_STOCK]"
										+ stockData.get(j).getTotal_mid_stock_received()
										+ "[/MIDDAY_TOTAL_STOCK]"
										+ "[/STOCK_DATA]";

								final_xml = final_xml + onXML;

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", sos_xml);
							request.addProperty("KEYS", "STOCK_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}


						}

						//uploading Stock Image data
						final_xml = "";
						onXML = "";
						stockImgData = db.getHeaderStockImageData(coverageBeanlist.get(i).getStoreId(), prev_date);

						if (stockImgData.size()>0) {

							for (int j = 0; j < stockImgData.size(); j++) {

								/*String actual_listed;
								if(stockData.get(j).getActual_listed().equalsIgnoreCase("yes")){
									actual_listed="1";
								}else{
									actual_listed="0";
								}*/

								if(stockImgData.get(j).getImg_cam()!=null){
									onXML = "[STOCK_IMAGE_DATA]"
											+ "[BRAND_CD]"
											+ stockImgData.get(j).getBrand_cd()
											+ "[/BRAND_CD]"
											+ "[MID]"
											+ mid
											+ "[/MID]"
											+ "[CREATED_BY]"
											+ username
											+ "[/CREATED_BY]"
											+ "[STOCK_IMAGE]"
											+ stockImgData.get(j).getImg_cam()
											+ "[/STOCK_IMAGE]"
											+ "[/STOCK_IMAGE_DATA]";

									final_xml = final_xml + onXML;
								}

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", sos_xml);
							request.addProperty("KEYS", "STOCK_IMAGE_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}


						}


						//uploading Additional poi data

						final_xml = "";
						onXML = "";
						poiData = db.getPOIData(coverageBeanlist.get(i).getStoreId());

						if (poiData.size()>0) {

							for (int j = 0; j < poiData.size(); j++) {

								/*String actual_listed;
								if(stockData.get(j).getActual_listed().equalsIgnoreCase("yes")){
									actual_listed="1";
								}else{
									actual_listed="0";
								}*/

								onXML = "[POI_DATA][CATEGORY_CD]"
										+ poiData.get(j).getCategory_cd()
										+ "[/CATEGORY_CD]"
										+ "[ASSET_CD]"
										+ poiData.get(j).getAsset_cd()
										+ "[/ASSET_CD]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[REMARK]"
										+ poiData.get(j).getRemark()
										+ "[/REMARK]"
										+ "[BRAND_CD]"
										+ poiData.get(j).getBrand_cd()
										+ "[/BRAND_CD]"
										+ "[IMAGE_POI]"
										+ poiData.get(j).getImage()
										+ "[/IMAGE_POI]"

										+ "[/POI_DATA]";

								final_xml = final_xml + onXML;

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", sos_xml);
							request.addProperty("KEYS", "POI_NEW_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}


						}


						//uploading Competition Additional poi data

						final_xml = "";
						onXML = "";
						competitionpoiData = db.getCompetitionPOIData(coverageBeanlist.get(i).getStoreId());

						if (competitionpoiData.size()>0) {

							for (int j = 0; j < competitionpoiData.size(); j++) {

								/*String actual_listed;
								if(stockData.get(j).getActual_listed().equalsIgnoreCase("yes")){
									actual_listed="1";
								}else{
									actual_listed="0";
								}*/

								onXML = "[COMPETITION_POI_DATA][CATEGORY_CD]"
										+ competitionpoiData.get(j).getCategory_cd()
										+ "[/CATEGORY_CD]"
										+ "[ASSET_CD]"
										+ competitionpoiData.get(j).getAsset_cd()
										+ "[/ASSET_CD]"
										+ "[COMPANY_CD]"
										+ competitionpoiData.get(j).getCompany_cd()
										+ "[/COMPANY_CD]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[REMARK]"
										+ competitionpoiData.get(j).getRemark()
										+ "[/REMARK]"

										+ "[/COMPETITION_POI_DATA]";

								final_xml = final_xml + onXML;

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", sos_xml);
							request.addProperty("KEYS", "COMPETITION_POI_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}


						}

						//uploading Competition Faceup data

						final_xml = "";
						onXML = "";
						facingcompetition = db.getFacingCompetitorData(coverageBeanlist.get(i).getStoreId());

						if (facingcompetition.size()>0) {

							for (int j = 0; j < facingcompetition.size(); j++) {

								/*String actual_listed;
								if(stockData.get(j).getActual_listed().equalsIgnoreCase("yes")){
									actual_listed="1";
								}else{
									actual_listed="0";
								}*/

								// INTEGER, CATEGORY VARCHAR, FACING VARCHAR

								onXML = "[COMPETITION_FACEUP_DATA][COMPANY_CD]"
										+ facingcompetition.get(j).getCompany_cd()
										+ "[/COMPANY_CD]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[FACING]"
										+ facingcompetition.get(j).getFacing()
										+ "[/FACING]"
										+ "[/COMPETITION_FACEUP_DATA]";

								final_xml = final_xml + onXML;

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", sos_xml);
							request.addProperty("KEYS", "COMPETITION_FACEUP_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}


						}


						//uploading Competition Promotion data

						final_xml = "";
						onXML = "";
						promotioncompetitionData = db.getCompetitionPromotionData(coverageBeanlist.get(i).getStoreId());

						if (promotioncompetitionData.size()>0) {

							for (int j = 0; j < promotioncompetitionData.size(); j++) {

								/*String actual_listed;
								if(stockData.get(j).getActual_listed().equalsIgnoreCase("yes")){
									actual_listed="1";
								}else{
									actual_listed="0";
								}*/

								onXML = "[COMPETITION_PROMOTION_DATA_NEW][CATEGORY_CD]"
										+ promotioncompetitionData.get(j).getCategory_cd()
										+ "[/CATEGORY_CD]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[PROMOTION]"
										+ promotioncompetitionData.get(j).getPromotion()
										+ "[/PROMOTION]"
										+ "[SKU_DETAIL]"
										+ promotioncompetitionData.get(j).getSku()
										+ "[/SKU_DETAIL]"
										+ "[COMPANY_CD]"
										+ promotioncompetitionData.get(j).getCompany_cd()
										+ "[/COMPANY_CD]"
										+ "[REMARK]"
										+ promotioncompetitionData.get(j).getRemark()
										+ "[/REMARK]"
										+ "[/COMPETITION_PROMOTION_DATA_NEW]";

								final_xml = final_xml + onXML;

							}

							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							request = new SoapObject(
									CommonString.NAMESPACE,
									CommonString.METHOD_UPLOAD_XML);
							request.addProperty("XMLDATA", sos_xml);
							request.addProperty("KEYS", "COMPETITION_PROMOTION_DATA");
							request.addProperty("USERNAME", username);
							request.addProperty("MID", mid);

							envelope = new SoapSerializationEnvelope(
									SoapEnvelope.VER11);
							envelope.dotNet = true;
							envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
									CommonString.URL);

							androidHttpTransport.call(
									CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
									envelope);
							result = (Object) envelope.getResponse();


							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_NO_DATA)) {
								return CommonString.METHOD_UPLOAD_XML;
							}

							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_XML;
							}


						}


						//	uploading Calls data

					/*	final_xml = "";
						onXML = "";
						callsData = db.getCallsData(coverageBeanlist.get(i).getStoreId());

						if (callsData.size()>0) {

								onXML = "[CALLS_DATA]"
										+ "[MID]"
										+ mid
										+ "[/MID]"
										+ "[CREATED_BY]"
										+ username
										+ "[/CREATED_BY]"
										+ "[TOTAL_CALLS]"
										+ callsData.get(0).getTotal_calls()
										+ "[/TOTAL_CALLS]"
										+ "[PRODUCTIVE_CALLS]"
										+ callsData.get(0).getProductive_calls()
										+ "[/PRODUCTIVE_CALLS]"
										+ "[/CALLS_DATA]";

								final_xml = onXML;


							final String sos_xml = "[DATA]" + final_xml
									+ "[/DATA]";

							 request = new SoapObject(
										CommonString.NAMESPACE,
										CommonString.METHOD_UPLOAD_XML);
								request.addProperty("XMLDATA", sos_xml);
								request.addProperty("KEYS", "CALL_DATA");
								request.addProperty("USERNAME", username);
								request.addProperty("MID", mid);

								 envelope = new SoapSerializationEnvelope(
										SoapEnvelope.VER11);
								envelope.dotNet = true;
								envelope.setOutputSoapObject(request);

							androidHttpTransport = new HttpTransportSE(
										CommonString.URL);

							 	androidHttpTransport.call(
										CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
										envelope);
								result = (Object) envelope.getResponse();


								if (result.toString().equalsIgnoreCase(
										CommonString.KEY_NO_DATA)) {
									return CommonString.METHOD_UPLOAD_XML;
								}

								if (result.toString().equalsIgnoreCase(
										CommonString.KEY_FAILURE)) {
									return CommonString.METHOD_UPLOAD_XML;
								}

							}*/



						/*status=coverageBeanlist.get(i).getStatus();
						if(status.equals(CommonString.STORE_STATUS_LEAVE)){

							String path=coverageBeanlist.get(i).getImage();
							String store_cd=coverageBeanlist.get(i).getStoreId();
							if(path!=null && !path.equals("")){
								UploadImage(path,store_cd);
							}



						}
*/
					}


					db.open();

					db.updateCoverageStatus(coverageBeanlist.get(i)
							.getMID(), CommonString.KEY_D);
					db.updateStoreStatusOnLeave(coverageBeanlist.get(i)
							.getStoreId(), coverageBeanlist.get(i)
							.getVisitDate(), CommonString.KEY_D);

					data.value =factor*(i+1);
					data.name = "Uploading";

					publishProgress(data);




					// SET COVERAGE STATUS

					String final_xml = "";
					String onXML = "";
					onXML = "[COVERAGE_STATUS][STORE_ID]"
							+ coverageBeanlist.get(i).getStoreId()
							+ "[/STORE_ID]"
							+ "[VISIT_DATE]"
							+ coverageBeanlist.get(i).getVisitDate()
							+ "[/VISIT_DATE]"
							+ "[USER_ID]"
							+ coverageBeanlist.get(i).getUserId()
							+ "[/USER_ID]"
							+ "[STATUS]"
							+ CommonString.KEY_U
							+ "[/STATUS]"
							+ "[/COVERAGE_STATUS]";

					final_xml = final_xml + onXML;

					final String sos_xml = "[DATA]" + final_xml
							+ "[/DATA]";

					SoapObject request = new SoapObject(
							CommonString.NAMESPACE,
							CommonString.MEHTOD_UPLOAD_COVERAGE_STATUS);
					request.addProperty("onXML", sos_xml);
					/*request.addProperty("KEYS", "COVERAGE_STATUS");
					request.addProperty("USERNAME", username);
					request.addProperty("MID", mid);*/

					SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					envelope.dotNet = true;
					envelope.setOutputSoapObject(request);

					HttpTransportSE androidHttpTransport = new HttpTransportSE(
							CommonString.URL);

					androidHttpTransport.call(
							CommonString.SOAP_ACTION+CommonString.MEHTOD_UPLOAD_COVERAGE_STATUS,
							envelope);
					Object result = (Object) envelope.getResponse();


					if (result.toString().equalsIgnoreCase(
							CommonString.KEY_NO_DATA)) {
						return CommonString.MEHTOD_UPLOAD_COVERAGE_STATUS;
					}

					if (result.toString().equalsIgnoreCase(
							CommonString.KEY_FAILURE)) {
						return CommonString.MEHTOD_UPLOAD_COVERAGE_STATUS;
					}


				}


			}

			catch (MalformedURLException e) {

				up_success_flag=false;

				final AlertMessage message = new AlertMessage(
						CheckoutNUpload.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});

			} catch (IOException e) {

				up_success_flag=false;

				final AlertMessage message = new AlertMessage(
						CheckoutNUpload.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket", e);

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						message.showMessage();

					}
				});
			}

			catch (Exception e) {

				up_success_flag=false;

				final AlertMessage message = new AlertMessage(
						CheckoutNUpload.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);

				e.getMessage();
				e.printStackTrace();
				e.getCause();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});
			}
			if(	up_success_flag==true){
				return CommonString.KEY_SUCCESS;
			}
			else{
				return CommonString.KEY_FAILURE;
			}


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


				/*	AlertMessage message = new AlertMessage(
							CheckoutNUpload.this,
							AlertMessage.MESSAGE_UPLOAD_DATA, "success", null);
					message.showMessage();*/

				new UploadImageTask(CheckoutNUpload.this).execute();

					//db.deleteAllTables();
			} else if(result.equals(CommonString.KEY_FAILURE) || !result.equals("") ){

				AlertMessage message = new AlertMessage(
						CheckoutNUpload.this, CommonString.ERROR + result, "success", null);
				message.showMessage();
			}


		}
	}


	private class UploadImageTask extends AsyncTask<Void, Void, String> {
		private Context context;

		UploadImageTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new Dialog(context);
			dialog.setContentView(R.layout.custom_upload);
			dialog.setTitle("Uploading Image");
			dialog.setCancelable(false);
			dialog.show();
			pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
			percentage = (TextView) dialog.findViewById(R.id.percentage);
			message = (TextView) dialog.findViewById(R.id.message);
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {

				coverageBeanlist=db.getCoverageData(prev_date);
				if(coverageBeanlist.size()>0){
					for(int i=0;i<coverageBeanlist.size();i++){

						status=coverageBeanlist.get(i).getStatus();
						if(status.equals(CommonString.STORE_STATUS_LEAVE)){

							String path=coverageBeanlist.get(i).getImage();
							if(path!=null && !path.equals("")){
								result = UploadImage(path,"StoreImages");
							}
							else{
								result = CommonString.KEY_SUCCESS;
							}

						}

						assetInsertdata = db.getAssetUpload(coverageBeanlist.get(i).getStoreId());

						if (assetInsertdata.size()>0) {

							for (int j = 0; j < assetInsertdata.size(); j++) {

								if (assetInsertdata.get(j).getImg() != null
										&& !assetInsertdata.get(j)
										.getImg().equals("")) {

									if (new File(
											Environment.getExternalStorageDirectory() + "/CapitalFoods_Images/"

													+ assetInsertdata.get(j).getImg())
											.exists()) {

										result = UploadImage(assetInsertdata.get(j).getImg(), "AssetImages");


										if (result
												.toString()
												.equalsIgnoreCase(
														CommonString.KEY_FALSE)) {

											return "Asset Images";
										} else if (result
												.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

											return "Asset Images"
													+ "," + errormsg;
										}

										runOnUiThread(new Runnable() {

											public void run() {


												message.setText("Asset Images Uploaded");
											}
										});



									}
								}

							}

						}

						promotionData = db.getPromotionUpload(coverageBeanlist.get(i).getStoreId());

						if (promotionData.size()>0) {

							for (int j = 0; j < promotionData.size(); j++) {

								if (promotionData.get(j).getImg() != null
										&& !promotionData.get(j)
										.getImg().equals("")) {

									if (new File(
											Environment.getExternalStorageDirectory() + "/CapitalFoods_Images/"

													+ promotionData.get(j).getImg())
											.exists()) {

										result = UploadImage(promotionData.get(j).getImg(), "PromotionImages");


										if (result
												.toString()
												.equalsIgnoreCase(
														CommonString.KEY_FALSE)) {

											return "Asset Images";
										} else if (result
												.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

											return "Asset Images"
													+ "," + errormsg;
										}

										runOnUiThread(new Runnable() {

											public void run() {


												message.setText("Asset Images Uploaded");
											}
										});



									}

								}

							}

						}

						poiInsertdata = db.getPOIData(coverageBeanlist.get(i).getStoreId());

						if (poiInsertdata.size()>0) {

							for (int j = 0; j < poiInsertdata.size(); j++) {

								if (poiInsertdata.get(j).getImage() != null
										&& !poiInsertdata.get(j)
										.getImage().equals("")) {

									if (new File(
											Environment.getExternalStorageDirectory() + "/CapitalFoods_Images/"

													+ poiInsertdata.get(j).getImage())
											.exists()) {

										result = UploadImage(poiInsertdata.get(j).getImage(), "POIImages");



										if (result
												.toString()
												.equalsIgnoreCase(
														CommonString.KEY_FALSE)) {

											return "POI Images";
										} else if (result
												.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

											return "POI Images"
													+ "," + errormsg;
										}

										runOnUiThread(new Runnable() {

											public void run() {

												message.setText("POI Images Uploaded");
											}
										});

									}

								}

							}

						}

						stockImgData = db.getHeaderStockImageData(coverageBeanlist.get(i).getStoreId(), prev_date);

						if (stockImgData.size()>0) {

							for (int j = 0; j < stockImgData.size(); j++) {

								if (stockImgData.get(j).getImg_cam() != null
										&& !stockImgData.get(j)
										.getImg_cam().equals("")) {

									if (new File(
											Environment.getExternalStorageDirectory() + "/CapitalFoods_Images/"

													+ stockImgData.get(j).getImg_cam())
											.exists()) {

										result =UploadImage(stockImgData.get(j).getImg_cam(),"StockImages");


										if (result
												.toString()
												.equalsIgnoreCase(
														CommonString.KEY_FALSE)) {

											return "Stock Images";
										} else if (result
												.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

											return "Stock Images"
													+ "," + errormsg;
										}

										runOnUiThread(new Runnable() {

											public void run() {

												message.setText("Stock Images Uploaded");
											}
										});

									}

								}

							}

						}

						return CommonString.KEY_SUCCESS;
					}
				}






			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						CheckoutNUpload.this,
						AlertMessage.MESSAGE_EXCEPTION, "socket_uploadimagesall", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});

			} catch (IOException e) {
				final AlertMessage message = new AlertMessage(
						CheckoutNUpload.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION,
						"socket_uploadimagesall", e);
				counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						message.showMessage();
						// TODO Auto-generated method stub
						/*
						 * if (counter < 3) { new
						 * UploadTask(UploadAllImageActivity.this).execute(); }
						 * else { message.showMessage(); counter = 1; }
						 */
					}
				});
			} catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						CheckoutNUpload.this,
						AlertMessage.MESSAGE_EXCEPTION, "socket_uploadimagesall", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();

					}
				});
			}


			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			dialog.dismiss();

			if (result.equals(CommonString.KEY_SUCCESS)) {
				db.open();
				db.deleteAllTables();

				SharedPreferences.Editor editor = preferences.edit();
				editor.putString(CommonString.KEY_STOREVISITED, "");
				editor.putString(CommonString.KEY_STOREVISITED_STATUS, "");
				editor.putString(CommonString.KEY_STORE_IN_TIME, "");
				editor.putString(CommonString.KEY_LATITUDE, "");
				editor.putString(CommonString.KEY_LONGITUDE, "");
				editor.commit();

				AlertMessage message = new AlertMessage(
						CheckoutNUpload.this,
						AlertMessage.MESSAGE_UPLOAD_IMAGE, "success", null);
				message.showMessage();

			} else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						CheckoutNUpload.this, result, "success", null);
				message.showMessage();
			}
		}



		public String UploadImage(String path, String folder_name) throws Exception {

			errormsg = "";
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(Path + path, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 1024;

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;

			while (true) {
				if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			Bitmap bitmap = BitmapFactory.decodeFile(
					Path + path, o2);

			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
			byte[] ba = bao.toByteArray();
			String ba1 = Base64.encodeBytes(ba);

			SoapObject request = new SoapObject(CommonString.NAMESPACE,
					CommonString.METHOD_UPLOAD_IMAGE);

			String[] split = path.split("/");
			String path1 = split[split.length - 1];

			request.addProperty("img", ba1);
			request.addProperty("name", path1);
			request.addProperty("FolderName", folder_name);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					CommonString.URL);

			androidHttpTransport
					.call(CommonString.SOAP_ACTION_UPLOAD_IMAGE,
							envelope);
			Object result = (Object) envelope.getResponse();

			if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

				if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
					return CommonString.KEY_FALSE;
				}

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				// for failure
				FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
				xmlR.setContentHandler(failureXMLHandler);

				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result.toString()));
				xmlR.parse(is);

				failureGetterSetter = failureXMLHandler
						.getFailureGetterSetter();

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					errormsg = failureGetterSetter.getErrorMsg();
					return CommonString.KEY_FAILURE;
				}
			} else {
				new File(Path + path).delete();
			}

			return "";
		}

	}



}
