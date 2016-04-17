package com.wzd.myhome.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.wzd.common.data.GlobalData;
import com.wzd.myhome.data.GlobalConstant;

public class AccountSetting {
	/**
	 * 获取配置文件写对象
	 * 
	 * @return
	 */
	private static SharedPreferences getSp() {
		Context context = GlobalData.globalContext;
		SharedPreferences sp = context.getSharedPreferences(
				GlobalConstant.SP_ACCOUNT, Context.MODE_PRIVATE);
		return sp;
	}

	/**
	 * 获取配置文件写对象
	 * 
	 * @return
	 */
	private static Editor getSPEditor() {
		Context context = GlobalData.globalContext;
		SharedPreferences sp = context.getSharedPreferences(
				GlobalConstant.SP_ACCOUNT, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		return editor;
	}

	/**
	 * 获取账户
	 */
	public static String getAccountName() {
		SharedPreferences sp = getSp();
		return sp.getString(GlobalConstant.SP_ACCOUNT_NAME, "");
	}

	/**
	 * 设置账户
	 */
	public static void setAccountName(String accountName) {
		Editor editor = getSPEditor();
		editor.putString(GlobalConstant.SP_ACCOUNT_NAME, accountName);
		editor.commit();
	}
	
	/**
	 * 获取密码
	 */
	public static String getAccountPwd() {
		SharedPreferences sp = getSp();
		return sp.getString(GlobalConstant.SP_ACCOUNT_PWD, "");
	}
	
	/**
	 * 设置密码
	 */
	public static void setAccountPwd(String accountPwd) {
		Editor editor = getSPEditor();
		editor.putString(GlobalConstant.SP_ACCOUNT_PWD, accountPwd);
		editor.commit();
	}
	
	/**
	 * 获取当前用户ID
	 */
	public static long getAccountPersonId() {
		SharedPreferences sp = getSp();
		return sp.getLong(GlobalConstant.SP_ACCOUNT_PERSON_ID, 0);
	}
	
	/**
	 * 设置当前用户ID
	 */
	public static void setAccountPersonId(long personId) {
		Editor editor = getSPEditor();
		editor.putLong(GlobalConstant.SP_ACCOUNT_PERSON_ID, personId);
		editor.commit();
	}
}
