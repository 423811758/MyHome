package com.wzd.myhome.data;

public class GlobalConstant {
	/** 通知栏标题 */
	public final static String Notifacation_Title = "App";
	/** 本机SD卡中文件的存储目录 */
	public final static String path = "/App";
	/** SDCARD首级目录 */
	public final static String DIR_HEAD = "App";
	/** 图片文件目录 */
	public static final String IMAGE_DIR = "App/image";
	public static final String IMAGE_HEADER = "header.jpg";
	/** 网络图片缓存路径 写死在包中，不能只修改这里 */
	public static final String IMAGE_CACHE_DIR = "TamperProofLabel/urlimage";
	/** 更新文件名 */
	public final static String APK_NAME = "App.apk";
	/** 图片预览加载失败(大图） */
	public static final String IMAGE_PREVIEW_LOAD_FAILURE = "image_preview_load_failure";
	// 存储空间
	/** 未发现SD卡 */
	public static final int SDCARD_IS_UNMOUNT = 1001;
	/** 存储空间不足 */
	public static final int SDCARD_IS_FULL = 1002;
	/** 显示的通知ID */
	public final static int ONGOING_NOTIFICATION_ID = 2000;// 显示的通知ID
	public final static int UPGRADE_NOTIFICATION_ID = 2001;// 下载更新通知栏ID

	// ************************ 和帐号相关的设置 ************************************
	public static final String SP_ACCOUNT_SERVER_SETTING = "accountserversetting";
	/** 个人资料 */
	public static final String SP_ACCOUNT_PERSON_INFO = "accountPersonInfo";
	// *********************** 帐号配置文件 **********************************
	public static final String SP_ACCOUNT = "account";
	/** 帐号 */
	public static final String SP_ACCOUNT_NAME = "accountName";
	/** 密码 */
	public static final String SP_ACCOUNT_PWD = "accountPwd";
	/** 联系人id */
	public static final String SP_ACCOUNT_PERSON_ID = "accountPersonId";
	// *********************** 和帐号无关的设置 **********************************
	public static final String SP_SETTING = "setting";
	/** 程序版本 */
	public static final String SP_VERSION = "version";
	/** 手机屏幕高 */
	public static final String SP_GLOBAL_SCREEN_HEIGHT = "globalscreenheight";
	/** 手机屏幕宽 */
	public static final String SP_GLOBAL_SCREEN_WIDTH = "globalscreenwidth";
	/** 手机可见屏幕高，包括状态栏 */
	public static final String SP_GLOBAL_SCREEN_VISIABLE_HEIGHT = "globalscreenvisiableheight";
	/** 上次选择照片的路径 */
	public static final String SP_CHOOSE_PHOTO_DIR_ID = "choose_photo_dir_id";

}
