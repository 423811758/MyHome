package com.wzd.myhome.service;

import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.content.pm.PackageManager;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
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
		initIM();
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

	private void initIM() {
		EMOptions options = new EMOptions();
		// 默认添加好友时，是不需要验证的，改成需要验证
		options.setAcceptInvitationAlways(false);
		// 设置是否需要已读回执
		options.setRequireAck(true);
		// 设置是否需要已送达回执
		options.setRequireDeliveryAck(false);
		int pid = android.os.Process.myPid();
		String processAppName = getAppName(pid);
		// 如果app启用了远程的service，此application:onCreate会被调用2次
		// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
		// 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process
		// name就立即返回

		if (processAppName == null
				|| !processAppName.equalsIgnoreCase(getPackageName())) {
			Log4JUtil.info("enter the service process!");
			// "com.easemob.chatuidemo"为demo的包名，换到自己项目中要改成自己包名

			// 则此application::onCreate 是被service 调用的，直接返回
			return;
		}
		// 初始化
		EMClient.getInstance().init(this, options);
		Log4JUtil.debugInfo("----------------*********");
		// 在做打包混淆时，关闭debug模式，避免消耗不必要的资源
		EMClient.getInstance().setDebugMode(true);
	}
	
	private String getAppName(int pID) {
		String processName = null;
		ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
		List l = am.getRunningAppProcesses();
		Iterator i = l.iterator();
		PackageManager pm = this.getPackageManager();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
			try {
				if (info.pid == pID) {
					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
				// Log.d("Process", "Error>> :"+ e.toString());
			}
		}
		return processName;
	}

}
