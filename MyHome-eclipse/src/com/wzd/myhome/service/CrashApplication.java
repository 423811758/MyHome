package com.wzd.myhome.service;

import com.wzd.myhome.setting.AccountSetting;
import com.wzd.myhome.setting.SystemSetting;
import com.zzy.app.util.Log4JUtil;
import com.zzy.app.util.StringUtil;

public class CrashApplication extends CommonApplication {

	private static final int URL_IMAGE_CACHE_SIZE = 100 * 1024 * 1024;

	static Boolean Lock = false;
	static String msg;

	@Override
	public void onCreate() {
		Log4JUtil.configure(this);
		Log4JUtil.info("onCreate");
		super.onCreate();
		initAppData();
	}

	private void initAppData() {
		SystemSetting.getInstance();
		// 初始化全局context
		CrashHandler crashHandler = CrashHandler.getInstance();
		// 注册crashHandler
		crashHandler.init(this);
		Log4JUtil.info("end onCreate");
		String account = AccountSetting.getAccountName();
		String password = AccountSetting.getAccountPwd();
		if (!StringUtil.isEmptyString(account)
				&& !StringUtil.isEmptyString(password)) {
			// LoginManager manager = new LoginManager(getApplicationContext(),
			// account, password, GlobalData.getCurrentIMEI());
			// manager.sendZzyHttpRequest();
		}
	}

}
