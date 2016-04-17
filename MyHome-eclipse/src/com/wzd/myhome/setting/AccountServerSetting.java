package com.wzd.myhome.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.wzd.common.data.GlobalData;
import com.wzd.myhome.data.GlobalConstant;

public class AccountServerSetting {
	private static long currentPersonId = 0;

	private static AccountServerSetting instance;

	private SharedPreferences sp;
	private Editor editor;

	public AccountServerSetting(long personId) {
		super();
		currentPersonId = personId;
	}

	public static AccountServerSetting getInstance(long personId) {
		if (currentPersonId != personId) {
			instance = null;
			instance = new AccountServerSetting(personId);
		} else {
			if (instance == null) {
				instance = new AccountServerSetting(personId);
			}
		}
		return instance;
	}

	/**
	 * 获取配置文件写对象
	 * 
	 * @return
	 */
	public SharedPreferences getSp() {
		Context context = GlobalData.globalContext;
		if (sp == null)
			sp = context.getSharedPreferences(
					GlobalConstant.SP_ACCOUNT_SERVER_SETTING + "_"
							+ currentPersonId, Context.MODE_PRIVATE);
		return sp;
	}

	/**
	 * 获取配置文件写对象
	 * 
	 * @return
	 */
	public Editor getSPEditor() {
		Context context = GlobalData.globalContext;
		if (editor == null) {
			if (sp == null)
				sp = context.getSharedPreferences(
						GlobalConstant.SP_ACCOUNT_SERVER_SETTING + "_"
								+ currentPersonId, Context.MODE_PRIVATE);
			editor = sp.edit();
		}
		return editor;
	}

	/**
	 * 获取账户资料 JSON格式
	 */
	public String getAccountPersonInfo() {
		SharedPreferences sp = getSp();
		return sp.getString(GlobalConstant.SP_ACCOUNT_PERSON_INFO, "");
	}

	/**
	 * 设置账户资料 JSON格式
	 */
	public void setAccountPersonInfo(String accountPersonInfo) {
		Editor editor = getSPEditor();
		editor.putString(GlobalConstant.SP_ACCOUNT_PERSON_INFO,
				accountPersonInfo);
		editor.commit();
	}

}
