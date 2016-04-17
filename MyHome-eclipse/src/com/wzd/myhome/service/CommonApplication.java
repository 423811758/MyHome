package com.wzd.myhome.service;

import android.app.Application;

import com.wzd.common.data.GlobalData;

public class CommonApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		GlobalData.globalContext = getApplicationContext();
	}

}
