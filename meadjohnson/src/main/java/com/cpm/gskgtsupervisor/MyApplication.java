package com.cpm.gskgtsupervisor;


import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

import com.cpm.capitalfoods.R;

@ReportsCrashes(formKey = "", 
		mailTo = "neha.tiwari@in.cpm-int.com",
		customReportContent = {
		ReportField.APP_VERSION_CODE,
		ReportField.APP_VERSION_NAME,
		ReportField.PHONE_MODEL,
		ReportField.DEVICE_ID,
		ReportField.USER_CRASH_DATE,
		ReportField.SHARED_PREFERENCES,
		ReportField.ANDROID_VERSION,
		ReportField.CUSTOM_DATA,
		ReportField.STACK_TRACE, 
		ReportField.SHARED_PREFERENCES,
		}, 
		//mode = ReportingInteractionMode.TOAST,
		resToastText = R.string.app_name)
 
public class MyApplication extends Application {

	
	/** Called when the activity is first created. */

	public void onCreate() {
		 super.onCreate();
		// The following line triggers the initialization of ACRA
	
		ACRA.init(this);
	
	}



}