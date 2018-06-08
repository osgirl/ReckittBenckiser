package com.cpm.gskgtsupervisor;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.autoupdate.AutoupdateActivity;

import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.TableBean;
import com.cpm.message.AlertMessage;
import com.cpm.web.ShowUpdateActivity;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlGetterSetter.LoginGetterSetter;
import com.cpm.xmlGetterSetter.QuestionGetterSetter;
import com.cpm.xmlHandler.XMLHandlers;
import com.cpm.capitalfoods.R;


public class LoginActivity extends Activity implements OnClickListener,
		LocationListener {

	private EditText mUsername, mPassword;
	private Button mLogin;
	// private RelativeLayout login_remember;
	// private ImageView login_remembericon;

	private String username, password, p_username, p_password;
	private double latitude = 0.0, longitude = 0.0;
	private int versionCode;
	private boolean isChecked;

	private LocationManager locmanager = null;
	private SharedPreferences preferences = null;
	private SharedPreferences.Editor editor = null;
	private Intent intent = null;
	GSKDatabase database;
	static int counter = 1;
	// TextView login_version;
	String app_ver;
	int eventType;
	LoginGetterSetter lgs = null;
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();

	private QuestionGetterSetter questionGetterSetter;
	String right_answer ,rigth_answer_cd= "",qns_cd,ans_cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ContentResolver.setMasterSyncAutomatically(false);

		// login_version = (TextView) findViewById(R.id.login_versiontext);

		mUsername = (EditText) findViewById(R.id.login_usertextbox);
		mPassword = (EditText) findViewById(R.id.login_locktextbox);
		// login_remember = (RelativeLayout)
		// findViewById(R.id.login_rememberme);
		// login_remembericon = (ImageView)
		// findViewById(R.id.login_remembermeicon);
		
		//mUsername.setText("testmer");
		//mPassword.setText("cpm123");

		mLogin = (Button) findViewById(R.id.login_loginbtn);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		editor = preferences.edit();

		p_username = preferences.getString(CommonString.KEY_USERNAME, null);
		p_password = preferences.getString(CommonString.KEY_PASSWORD, null);
		isChecked = preferences.getBoolean(CommonString.KEY_REMEMBER, false);

		try {
			app_ver =String.valueOf(getPackageManager().getPackageInfo(getPackageName(), 0).versionCode);

			// login_version.setText("Parinaam Version " + app_ver);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		database = new GSKDatabase(this);
//		database.open();

		if (!isChecked) {
			// login_remembericon.setImageResource(R.drawable.deactive_radio_box);
		} else {
			mUsername.setText(p_username);
			mPassword.setText(p_password);
		}

		mLogin.setOnClickListener(this);
		// login_remember.setOnClickListener(this);

		locmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		boolean enabled = locmanager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		// Check if enabled and if not send user to the GSP settings
		// Better solution would be to display a dialog and suggesting to
		// go to the settings
		if (!enabled) {

			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					LoginActivity.this);

			// Setting Dialog Title
			alertDialog.setTitle("GPS IS DISABLED...");

			// Setting Dialog Message
			alertDialog.setMessage("Click ok to enable GPS.");

			// Setting Positive "Yes" Button
			alertDialog.setPositiveButton("YES",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

							intent = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							startActivity(intent);
						}
					});

			// Setting Negative "NO" Button
			alertDialog.setNegativeButton("NO",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// Write your code here to invoke NO event

							dialog.cancel();
						}
					});

			// Showing Alert Message
			alertDialog.show();

		}

		// Create a Folder for Images
		File file = new File(Environment.getExternalStorageDirectory(),
				"Meadjohnson_Images");
		if (!file.isDirectory()) {
			file.mkdir();
		}

		// mUsername.setText("testmer");
		// mPassword.setText("cpm123");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		locmanager.removeUpdates(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		username = mUsername.getText().toString().trim();
		password = mPassword.getText().toString().trim();

		switch (v.getId()) {
		case R.id.login_loginbtn:
			
			
			if (username.length() == 0) {
				showToast("Please enter username");
			} else if (password.length() == 0) {
				showToast("Please enter password");
			} else {

				p_username = preferences.getString(CommonString.KEY_USERNAME,
						null);
				p_password = preferences.getString(CommonString.KEY_PASSWORD,
						null);
				// If no preferences are stored
				if (p_username == null && p_password == null) {

					if (CheckNetAvailability()) {
						new AuthenticateTask().execute();

					} else {
						showToast("No Network and first login");
					}
				}
				// If preferences are stored
				else {
					if (username.equals(p_username)) {
						if (CheckNetAvailability()) {
							new AuthenticateTask().execute();
						} else if (password.equals(p_password)) {
							intent = new Intent(this, MainMenuActivity.class);
							startActivity(intent);
							this.finish();

							showToast("No Network and offline login");
						} else {
							showToast("Incorrect Password");
						}
					} else {
						showToast("Incorrect Username");
					}
				}
			}
			break;

		}
	}

	private class AuthenticateTask extends AsyncTask<Void, Void, String> {
		private ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new ProgressDialog(LoginActivity.this);
			dialog.setTitle("Login");
			dialog.setMessage("Authenticating....");
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {

				String resultHttp="";

				versionCode = getPackageManager().getPackageInfo(
						getPackageName(), 0).versionCode;

				String userauth_xml = "[DATA]" + "[USER_DATA][USER_ID]"
						+ username + "[/USER_ID]" + "[Password]" + password
						+ "[/Password]" + "[IN_TIME]" + getCurrentTime()
						+ "[/IN_TIME]" + "[LATITUDE]" + latitude
						+ "[/LATITUDE]" + "[LONGITUDE]" + longitude
						+ "[/LONGITUDE]" + "[APP_VERSION]" + app_ver
						+ "[/APP_VERSION]" + "[ATT_MODE]OnLine[/ATT_MODE]"
						 + "[/USER_DATA][/DATA]";

				SoapObject request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_LOGIN);
				
				request.addProperty("onXML", userauth_xml);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				HttpTransportSE androidHttpTransport = new HttpTransportSE(
						CommonString.URL);

				androidHttpTransport.call(CommonString.SOAP_ACTION_LOGIN,
						envelope);

				Object result = (Object) envelope.getResponse();

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

					final AlertMessage message = new AlertMessage(
							LoginActivity.this, AlertMessage.MESSAGE_FAILURE,
							"login", null);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							message.showMessage();
						}
					});

				} else if (result.toString().equalsIgnoreCase(
						CommonString.KEY_FALSE)) {

					final AlertMessage message = new AlertMessage(
							LoginActivity.this, AlertMessage.MESSAGE_FALSE,
							"login", null);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							message.showMessage();
						}
					});

				} else if (result.toString().equalsIgnoreCase(
						CommonString.KEY_CHANGED)) {

					final AlertMessage message = new AlertMessage(
							LoginActivity.this, AlertMessage.MESSAGE_CHANGED,
							"login", null);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							message.showMessage();
						}
					});

				} else {

					XmlPullParserFactory factory = XmlPullParserFactory
							.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();

					xpp.setInput(new StringReader(result.toString()));
					xpp.next();
					eventType = xpp.getEventType();
					FailureGetterSetter failureGetterSetter = XMLHandlers
							.failureXMLHandler(xpp, eventType);

					if (failureGetterSetter.getStatus().equalsIgnoreCase(
							CommonString.KEY_FAILURE)) {
						final AlertMessage message = new AlertMessage(
								LoginActivity.this, CommonString.METHOD_LOGIN
										+ failureGetterSetter.getErrorMsg(),
								"login", null);
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								message.showMessage();
							}
						});
					}
					else {

						try {
							// For String source

							xpp.setInput(new StringReader(result.toString()));
							xpp.next();
							eventType = xpp.getEventType();
							lgs = XMLHandlers.loginXMLHandler(xpp, eventType);

						} catch (XmlPullParserException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

						// PUT IN PREFERENCES
						editor.putString(CommonString.KEY_USERNAME, username);
						editor.putString(CommonString.KEY_PASSWORD, password);
						editor.putString(CommonString.KEY_VERSION,
								lgs.getVERSION());

						editor.putString(CommonString.KEY_PATH, lgs.getPATH());

						//----------------------------------
//Temp	editor.putString(CommonString.KEY_DATE, lgs.getDATE());

						editor.putString(CommonString.KEY_DATE, lgs.getDATE());
						
						editor.putString(CommonString.KEY_USER_TYPE, lgs.getRIGHTNAME());

						editor.commit();

						//return CommonString.KEY_SUCCESS;

						request = new SoapObject(CommonString.NAMESPACE,
								CommonString.METHOD_NAME_UNIVERSAL_DOWNLOAD);
						request.addProperty("UserName", username);
						request.addProperty("Type", "TODAY_QUESTION");

						envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);

						androidHttpTransport = new HttpTransportSE(
								CommonString.URL);

						androidHttpTransport.call(CommonString.SOAP_ACTION_UNIVERSAL,
								envelope);
						result = (Object) envelope.getResponse();

						if(result.toString()!=null){

							xpp.setInput(new StringReader(result.toString()));
							xpp.next();
							eventType = xpp.getEventType();

							questionGetterSetter = XMLHandlers.QuestionXMLHandler(xpp, eventType);

							if(questionGetterSetter.getQuestion_cd().size()>0){
								resultHttp = CommonString.KEY_SUCCESS;
								String qnsTable = questionGetterSetter.getTable_question_today();
								//TableBean.setQuestiontable(qnsTable);

							}else{
								return CommonString.KEY_SUCCESS;
							}

						}

						return resultHttp;

					}
				}

				return "";

			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						LoginActivity.this, AlertMessage.MESSAGE_EXCEPTION,
						"acra_login", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});

			} catch (IOException e) {
				final AlertMessage message = new AlertMessage(
						LoginActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket_login", e);

				counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (counter < 3) {
							new AuthenticateTask().execute();
						} else {
							message.showMessage();
							counter = 1;
						}
					}
				});
			}

			catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						LoginActivity.this, AlertMessage.MESSAGE_EXCEPTION,
						"acra_login", e);
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

			if (result.equals(CommonString.KEY_SUCCESS)) {
		
//				database.open();
				if (preferences.getString(CommonString.KEY_VERSION, "").equals(
						Integer.toString(versionCode))) {
					String visit_date = preferences.getString(CommonString.KEY_DATE, "");

					//Quiz temporary removed
					if(questionGetterSetter.getAnswer_cd().size()>0 && questionGetterSetter.getStatus().get(0).equals("N") && !preferences.getBoolean(CommonString.KEY_IS_QUIZ_DONE+visit_date, false)){
						//	if(false){
						for(int i=0;i<questionGetterSetter.getRight_answer().size();i++){

							if(questionGetterSetter.getRight_answer().get(i).equals("1")){
								right_answer = questionGetterSetter.getAnswer().get(i);
								rigth_answer_cd = questionGetterSetter.getAnswer_cd().get(i);
								break;
							}

						}

						final AnswerData answerData = new AnswerData();

						final Dialog dialog = new Dialog(LoginActivity.this);
						dialog.setTitle("Todays Question");
						dialog.setCancelable(false);
						//dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.todays_question_layout);

						((TextView) dialog.findViewById(R.id.tv_qns)).setText(questionGetterSetter.getQuestion().get(0));
						Button btnsubmit = (Button) dialog.findViewById(R.id.btnsubmit);
						RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radiogrp);

						for (int i = 0; i < questionGetterSetter.getAnswer_cd().size(); i++) {
							RadioButton rdbtn = new RadioButton(LoginActivity.this);
							rdbtn.setId(i);
							rdbtn.setText(questionGetterSetter.getAnswer().get(i));
							//ll.addView(rdbtn);
							radioGroup.addView(rdbtn);
						}


						radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(RadioGroup group, int checkedId) {

								answerData.setAnswer_id(questionGetterSetter.getAnswer_cd().get(checkedId));
								answerData.setRight_answer(questionGetterSetter.getRight_answer().get(checkedId));
							}
						});
						btnsubmit.setOnClickListener(new View.OnClickListener(){

							@Override
							public void onClick(View v) {

								if(answerData.getAnswer_id()== null || answerData.getAnswer_id().equals("")){
									Snackbar.make(mLogin, "First select an answer", Snackbar.LENGTH_SHORT).show();
								}
								else {

									dialog.cancel();

									String ansisright="";

									if(answerData.getRight_answer().equals("1")){
										ansisright="Your Answer Is Right!";
									}
									else{
										ansisright="Your Answer is Wrong! \nRight Answer Is :- "+right_answer;
									}

									final Dialog ans_dialog = new Dialog(LoginActivity.this);
									ans_dialog.setTitle("Answer");
									ans_dialog.setCancelable(false);
									//dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
									ans_dialog.setContentView(R.layout.show_answer_layout);

									((TextView) ans_dialog.findViewById(R.id.tv_ans)).setText(ansisright);
									Button btnok = (Button) ans_dialog.findViewById(R.id.btnsubmit);

									btnok.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {

											answerData.setQuestion_id(questionGetterSetter.getQuestion_cd().get(0));
											answerData.setUsername(username);
											answerData.setVisit_date(lgs.getDATE());

											new AnswerTodayTask().execute(answerData);

											ans_dialog.cancel();
										}
									});

									ans_dialog.show();

								}

							}
						});

						dialog.show();
					}
					else{

						intent = new Intent(getBaseContext(),
								MainMenuActivity.class);
						startActivity(intent);

						finish();

					}

				} else {
					intent = new Intent(getBaseContext(),
							AutoupdateActivity.class);

					intent.putExtra(CommonString.KEY_PATH,
							preferences.getString(CommonString.KEY_PATH, ""));
					startActivity(intent);
					finish();
				}

			}

			dialog.dismiss();
		}

	}

	public boolean CheckNetAvailability() {

		boolean connected = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState() == NetworkInfo.State.CONNECTED
				|| connectivityManager.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
			// we are connected to a network
			connected = true;
		}
		return connected;
	}

	private void showToast(String message) {
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
	}

	// for location
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude();
		longitude = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	public void onButtonClick(View v) {

		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);

	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);

	}

	class AnswerData{

		public String question_id, answer_id, username, visit_date, right_answer;

		public String getQuestion_id() {
			return question_id;
		}

		public void setQuestion_id(String question_id) {
			this.question_id = question_id;
		}

		public String getAnswer_id() {
			return answer_id;
		}

		public void setAnswer_id(String answer_id) {
			this.answer_id = answer_id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getVisit_date() {
			return visit_date;
		}

		public void setVisit_date(String visit_date) {
			this.visit_date = visit_date;
		}

		public String getRight_answer() {
			return right_answer;
		}

		public void setRight_answer(String right_answer) {
			this.right_answer = right_answer;
		}
	}


	//Asynctask to submit answer
	class AnswerTodayTask extends AsyncTask<AnswerData, Void, String>{

		private ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(LoginActivity.this);
			dialog.setTitle("Todays Question");
			dialog.setMessage("Submitting Answer..");
			dialog.setCancelable(false);
			dialog.show();

		}

		@Override
		protected String doInBackground(AnswerData... params) {

			try{

				AnswerData answerData =	params[0];

				String resultHttp="";
				versionCode = getPackageManager().getPackageInfo(
						getPackageName(), 0).versionCode;

				qns_cd = answerData.getQuestion_id();
				ans_cd = answerData.getAnswer_id();

				String userauth_xml = "[DATA]" + "[TODAY_ANSWER][USER_ID]"
						+ answerData.getUsername() + "[/USER_ID]" + "[QUESTION_ID]" + answerData.getQuestion_id()
						+ "[/QUESTION_ID]" + "[ANSWER_ID]" + answerData.getAnswer_id()
						+ "[/ANSWER_ID]" + "[VISIT_DATE]" + answerData.getVisit_date()
						+ "[/VISIT_DATE]"
						+ "[/TODAY_ANSWER][/DATA]";

				SoapObject request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_UPLOAD_XML);

				request.addProperty("XMLDATA", userauth_xml);
				request.addProperty("KEYS", "TODAYS_ANSWER");
				request.addProperty("USERNAME", answerData.getUsername());

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				HttpTransportSE androidHttpTransport = new HttpTransportSE(
						CommonString.URL);

				androidHttpTransport.call(CommonString.SOAP_ACTION+CommonString.METHOD_UPLOAD_XML,
						envelope);

				Object result = (Object) envelope.getResponse();

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

					/*final AlertMessage message = new AlertMessage(
							LoginActivity.this, AlertMessage.MESSAGE_FAILURE,
							"login", null);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							message.showMessage();
						}
					});*/

				} else if (result.toString().equalsIgnoreCase(
						CommonString.KEY_FALSE)) {

				/*	final AlertMessage message = new AlertMessage(
							LoginActivity.this, AlertMessage.MESSAGE_FALSE,
							"login", null);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							message.showMessage();
						}
					});*/

				} else{
					String visit_date = preferences.getString(CommonString.KEY_DATE, null);
					editor.putBoolean(CommonString.KEY_IS_QUIZ_DONE + visit_date, true);
					editor.commit();
					return CommonString.KEY_SUCCESS;
				}

				return "";

			}catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						LoginActivity.this, AlertMessage.MESSAGE_EXCEPTION,
						"acra_login", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});

			} catch (IOException e) {
				final AlertMessage message = new AlertMessage(
						LoginActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket_login", e);

				counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (counter < 3) {
							new AuthenticateTask().execute();
						} else {
							message.showMessage();
							counter = 1;
						}
					}
				});
			}

			catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						LoginActivity.this, AlertMessage.MESSAGE_EXCEPTION,
						"acra_login", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (result.equals(CommonString.KEY_SUCCESS)) {

				intent = new Intent(getBaseContext(),
						MainMenuActivity.class);
				startActivity(intent);

				finish();
			}
			else{
				//Save question cd and ans cd here for upload

				String visit_date = preferences.getString(CommonString.KEY_DATE, null);
				editor.putString(CommonString.KEY_QUESTION_CD+visit_date, qns_cd);
				editor.putString(CommonString.KEY_ANSWER_CD+visit_date, ans_cd);
				editor.commit();

				intent = new Intent(getBaseContext(),
						MainMenuActivity.class);
				startActivity(intent);

				finish();
			}
		}

	}
}
