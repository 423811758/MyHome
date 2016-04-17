package com.zzy.app.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

import com.wzd.common.util.CommonFileUtil;
import com.wzd.myhome.data.GlobalConstant;

public class FileUtil extends CommonFileUtil {

	/**
	 * 得到本机SD卡的存储目录--需要判断空间
	 * 
	 * @return
	 */
	public static String getPath_Space(Context context) {
		if (getExternalPath() != null && FileUtil.getExternalStorageSize() > 0) {
			File dir = new File(getExternalPath() + GlobalConstant.path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			return getExternalPath() + GlobalConstant.path;
		} else {
			// 设置手机程序内部存储空间目录
			return getInternalPath_Space(context);
		}
	}

	/**
	 * 得到本机SD卡的存储目录--不需要判断空间
	 * 
	 * @return
	 */
	public static String getPath_NoSpace(Context context) {
		if (getExternalPath() != null) {
			return getExternalPath() + GlobalConstant.path;
		} else {
			// 设置手机程序内部存储空间目录
			return getInternalPath_NoSpace(context);
		}
	}

	/**
	 * 获得图片路径
	 * 
	 * @return
	 */
	public static String getPicPath(String filename) {
		String filepath = mkDirs(GlobalConstant.IMAGE_DIR) + "/" + filename;
		return filepath;
	}

	public static String getPicPath() {
		String filepath = mkDirs(GlobalConstant.IMAGE_DIR);
		return filepath;
	}

	/**
	 * 图片缓存路径
	 * 
	 * @return
	 */
	public static String getPicCachePath() {
		String filepath = mkDirs(GlobalConstant.IMAGE_CACHE_DIR);
		return filepath;
	}

	/**
	 * 得到本机SD卡的目录
	 * 
	 * @return
	 */
	public static String getExternalPath() {
		return (FileUtil.getExternalStorageDirectory() == null) ? null
				: FileUtil.getExternalStorageDirectory().getPath();
	}

	/**
	 * 得到本机程序内部的存储目录--需要判断空间
	 * 
	 * @return
	 */
	public static String getInternalPath_Space(Context context) {
		if (FileUtil.getInternalStorageSizeByPath(context.getFilesDir()
				.getParent()) >= MIN_SPACE_SIZE) {
			return context.getFilesDir().getPath();
		} else
			return null;

	}

	/**
	 * 得到本机程序内部的存储目录--不需要判断空间
	 * 
	 * @return
	 */
	public static String getInternalPath_NoSpace(Context context) {
		return context.getFilesDir().getPath();
	}
	
	public static String getTargetName() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = "image_" + format.format(date) + ".jpg";
		return str;
	}

	public static String getTargetPath() {
		String img_path = "";
		img_path = FileUtil.mkDirs(GlobalConstant.IMAGE_DIR);
		return img_path;
	}
}
