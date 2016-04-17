package com.zzy.app.util;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import android.content.Context;
import android.util.Log;

import com.wzd.common.data.GlobalData;

import de.mindpipe.android.logging.log4j.LogConfigurator;

public class Log4JUtil {

	public static String LOGGER_FILE_NAME = "/app.log";

	public static String LOGGER_FILE_NAME_1 = "/app.log.1";

	public static final int MAX_BACKUP_SIZE = 4;

	private static final Logger logger = Logger.getLogger("");

	private static final String TAG = "test";

	/**
	 * 初始化日志参数
	 */
	public static void configure(Context context) {
		final LogConfigurator logConfigurator = new LogConfigurator();
		String pathStr = FileUtil.getPath_Space(context);
		if (pathStr != null) {
			logConfigurator.setFileName(pathStr + LOGGER_FILE_NAME);
			logConfigurator.setRootLevel(Level.DEBUG);
			logConfigurator.setLevel("org.apache", Level.ERROR);
			logConfigurator.setMaxBackupSize(MAX_BACKUP_SIZE);
			logConfigurator.setMaxFileSize(768 * 1024);
			logConfigurator.configure();
		}
	}

	public static String getLogFilePath(Context context) {
		String path = FileUtil.getPath_Space(context);
		return path + LOGGER_FILE_NAME;
	}

	public static void info(String msg) {
		if (GlobalData.globalContext != null) {
			String path = getLogFilePath(GlobalData.globalContext);
			File dir = new File(path);
			if (!dir.exists()) {
				configure(GlobalData.globalContext);
			}
		}
		logger.info(msg);
	}

	public static void error(String msg) {
		if (GlobalData.globalContext != null) {
			String path = getLogFilePath(GlobalData.globalContext);
			File dir = new File(path);
			if (!dir.exists()) {
				configure(GlobalData.globalContext);
			}
		}
		logger.error(msg);
	}

	public static void warn(String msg) {
		if (GlobalData.globalContext != null) {
			String path = getLogFilePath(GlobalData.globalContext);
			File dir = new File(path);
			if (!dir.exists()) {
				configure(GlobalData.globalContext);
			}
		}
		logger.warn(msg);
	}

	public static void debugInfo(String msg) {
		if (GlobalData.isDebug) {
			Log.i(TAG, msg);
		}
	}

	/*
	 * public static void configure() { String pathStr =
	 * FileUtil.getExternalPath() + GlobalConstant.path; File dir = new
	 * File(pathStr + LOGGER_FILE_NAME); if (!dir.exists()) { final
	 * LogConfigurator logConfigurator = new LogConfigurator();
	 * logConfigurator.setFileName(pathStr + LOGGER_FILE_NAME);
	 * logConfigurator.setRootLevel(Level.DEBUG);
	 * logConfigurator.setLevel("org.apache", Level.ERROR);
	 * logConfigurator.setMaxBackupSize(MAX_BACKUP_SIZE);
	 * logConfigurator.setMaxFileSize(768 * 1024); logConfigurator.configure();
	 * } }
	 */

	// /**
	// * 关闭日志功能
	// */
	// public static void closeLog(){
	// logConfigurator.setRootLevel(Level.OFF);
	// logConfigurator.setLevel("org.apache", Level.OFF);
	// }
	// /**
	// * 开启日志功能
	// */
	// public static void startLog(){
	// logConfigurator.setRootLevel(Level.DEBUG);
	// logConfigurator.setLevel("org.apache", Level.ERROR);
	// }
}
