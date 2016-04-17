package com.wzd.myhome.setting;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.provider.MediaStore;

import com.wzd.common.data.GlobalData;
import com.wzd.common.util.AndroidUtil;
import com.wzd.myhome.data.GlobalConstant;
import com.zzy.app.util.FileTypeUtil;

/**
 * 系统设置模型类 增加属性时修改loadSystemProperty方法(根据具体的需求进行修改)
 */
public class SystemSetting {

	private static SystemSetting property;
	private SharedPreferences sp;
	/** 软件版本数字信息 */
	public String ver;
	/** 手机屏幕高 */
	public int screenHeight = 0;
	/** 手机屏幕宽 */
	public int screenWidth = 0;
	/** 手机可见屏幕高，包括状态栏 */
	public int screenVisiableHeight = 0;
	/** 上一次选择相册的路径ID */
	public String choosePhotoDirId;

	public static SystemSetting getInstance() {
		if (property == null) {
			property = new SystemSetting();
			property.loadSystemProperty(GlobalData.globalContext);
		}
		return property;
	}

	private SystemSetting() {
	}

	/**
	 * 获取配置文件写对象
	 * 
	 * @return
	 */
	private Editor getSPEditor() {
		Editor editor = sp.edit();
		return editor;
	}

	/**
	 * 读取配置文件中的系统属性
	 */
	private void loadSystemProperty(Context context) {
		if (sp == null) {
			sp = context.getSharedPreferences(GlobalConstant.SP_SETTING,
					Context.MODE_PRIVATE);
			// 获得版本信息
			// ver = sp.getString(GlobalConstant.SP_VERSION, "");
			ver = AndroidUtil.getVersion(context);

			screenHeight = sp.getInt(GlobalConstant.SP_GLOBAL_SCREEN_HEIGHT, 0);
			screenWidth = sp.getInt(GlobalConstant.SP_GLOBAL_SCREEN_WIDTH, 0);
			screenVisiableHeight = sp.getInt(
					GlobalConstant.SP_GLOBAL_SCREEN_VISIABLE_HEIGHT, 0);
			choosePhotoDirId = sp.getString(
					GlobalConstant.SP_CHOOSE_PHOTO_DIR_ID, "");
		}
	}

	/**
	 * 设置版本
	 * 
	 * @param isRingRemind
	 */
	public void setVersion(String ver) {
		Editor editor = getSPEditor();
		this.ver = ver;
		editor.putString(GlobalConstant.SP_VERSION, ver);
		editor.commit();
	}

	/**
	 * 设置手机屏幕高
	 * 
	 * @param isAutoReceivePic
	 */
	public void setGlobalScreenHeight(int globalscreenheight) {
		this.screenHeight = globalscreenheight;
		Editor editor = getSPEditor();
		editor.putInt(GlobalConstant.SP_GLOBAL_SCREEN_HEIGHT,
				globalscreenheight);
		editor.commit();
	}

	/**
	 * 设置手机屏幕宽
	 * 
	 * @param isAutoReceivePic
	 */
	public void setGlobalScreenWidth(int globalscreenwidth) {
		this.screenWidth = globalscreenwidth;
		Editor editor = getSPEditor();
		editor.putInt(GlobalConstant.SP_GLOBAL_SCREEN_WIDTH, globalscreenwidth);
		editor.commit();
	}

	/**
	 * 设置手机可见屏幕高，包括状态栏
	 * 
	 * @param isAutoReceivePic
	 */
	public void setGlobalScreenVisiableHeight(int globalscreenvisiableheight) {
		this.screenVisiableHeight = globalscreenvisiableheight;
		Editor editor = getSPEditor();
		editor.putInt(GlobalConstant.SP_GLOBAL_SCREEN_VISIABLE_HEIGHT,
				globalscreenvisiableheight);
		editor.commit();
	}
	
	/**
	 * 判断之前保存的路径里面是否还有图片
	 */
	public static boolean isAlbumHasPhoto(ContentResolver contentResolver) {
		boolean result = false;
		Cursor cursor = MediaStore.Images.Media.query(contentResolver,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				FileTypeUtil.STORE_IMAGES, MediaStore.Images.Media.BUCKET_ID
						+ " = " + SystemSetting.getInstance().choosePhotoDirId,
				null);
		if (cursor.moveToNext()) {
			result = true;
		}
		cursor.close();
		return result;
	}
	
	/**
	 * 设置上一次选取图片路径ID
	 * 
	 */
	public void setChoosePhotoDirId(String choosePhotoDirId) {
		this.choosePhotoDirId = choosePhotoDirId;
		Editor editor = getSPEditor();
		editor.putString(GlobalConstant.SP_CHOOSE_PHOTO_DIR_ID,
				choosePhotoDirId);
		editor.commit();
	}

}
