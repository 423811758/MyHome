package com.wzd.common.data;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.wzd.common.util.AndroidUtil;
import com.wzd.myhome.setting.SystemSetting;

public class GlobalData extends CommonGlobalData {

	public static Account currentAccount = null;
	public static String LOGIN_TOKEN = "";
	public static String USER_ROLE = "";
	public static final String CHECKSUM_KEY = "DB1ABBCFC2FEAE8CA174C13A063BFD41";
	/** 是否调试模式 */
	public static final boolean isDebug = false;
	/** 获取设备号 */
	public static String IMEI_NUM = "";
	/** 每次加载的数量 */
	public static final int MAX_THIRTY_LOAD_COUNT_EVERY_TIME = 20;
	public static final int MAX_IMAGE_COUNT = 6;

	public static String getCurrentIMEI() {
		if (IMEI_NUM.endsWith("")) {
			IMEI_NUM = AndroidUtil.getDeviceIMEI();
		}
		return IMEI_NUM;
	}

	/**
	 * 获得可见屏幕高，包括状态栏
	 * 
	 * @param islandscape
	 *            TODO
	 */
	public static int getScreenVisiableHeight() {
		if (SystemSetting.getInstance().screenVisiableHeight != 0)
			return SystemSetting.getInstance().screenVisiableHeight;
		if (SystemSetting.getInstance().screenVisiableHeight != 0)
			return SystemSetting.getInstance().screenVisiableHeight;
		return 0;
	}

	/**
	 * 是否已登录
	 * 
	 * @param isStartLoginActivity
	 *            TODO
	 */
	public static boolean checkIsLogin(boolean isStartLoginActivity) {
		boolean result = (currentAccount != null);
		if (!result && isStartLoginActivity) {
			// TODO
			// LoginActivity.startLoginActivity(globalContext);
		}
		return result;
	}

	/**
	 * 获得屏幕宽
	 * 
	 * @param islandscape
	 *            TODO
	 */
	public static int getScreenWidth(boolean islandscape) {
		if (islandscape) {
			return getScreenHeight(false);
		}
		if (SystemSetting.getInstance().screenWidth != 0)
			return SystemSetting.getInstance().screenWidth;
		WindowManager manager = (WindowManager) GlobalData.globalContext
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		SystemSetting.getInstance().setGlobalScreenWidth(
				Math.min(display.getWidth(), display.getHeight()));
		return SystemSetting.getInstance().screenWidth;
	}

	/**
	 * 获得屏幕高
	 * 
	 * @param islandscape
	 *            TODO
	 */
	public static int getScreenHeight(boolean islandscape) {
		if (islandscape) {
			return getScreenWidth(false);
		}
		if (SystemSetting.getInstance().screenHeight != 0)
			return SystemSetting.getInstance().screenHeight;
		WindowManager manager = (WindowManager) GlobalData.globalContext
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		SystemSetting.getInstance().setGlobalScreenHeight(
				Math.max(display.getWidth(), display.getHeight()));
		return SystemSetting.getInstance().screenHeight;
	}
}
