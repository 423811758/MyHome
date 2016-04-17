package com.zzy.app.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzd.common.util.AndroidUtil;
import com.wzd.common.util.CommonToastUtil;
import com.wzd.myhome.R;

public class ToastUtil extends CommonToastUtil {

	/**
	 * 带图片的TOAST
	 * 
	 * @param context
	 *            上下文
	 * @param textResId
	 *            文本内容
	 * @param imageResId
	 *            图片
	 * @param isNeedTop TODO
	 */
	public static void showPicToast(Context context, int textResId,
			int imageResId, boolean isNeedTop) {
		if (!isNeedTop || AndroidUtil.isClientRunTop(context)) {// 前台运行
			showPicToast(context, context.getResources().getText(textResId),
					imageResId, true);
		}
	}

	/**
	 * 带图片的TOAST
	 * 
	 * @param context
	 *            上下文
	 * @param text
	 *            文本内容
	 * @param imageResId
	 *            图片
	 * @param isNeedTop
	 *            TODO
	 */
	public static void showPicToast(Context context, CharSequence text,
			int imageResId, boolean isNeedTop) {
		if (!isNeedTop || AndroidUtil.isClientRunTop(context)) {// 前台运行
			showPicToast(context, text, imageResId);
		}
	}

	/**
	 * 带图片的TOAST
	 * 
	 * @param context
	 *            上下文
	 * @param text
	 *            文本内容
	 * @param imageResId
	 *            图片
	 */
	private static void showPicToast(Context context, CharSequence text,
			int imageResId) {
		Toast toast = new Toast(context);
		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.custom_view_toast, null);
		v.setBackgroundResource(R.drawable.toast_bg);
		TextView tv = (TextView) v.findViewById(R.id.customToastTv);
		tv.setText(text);

		ImageView iv = (ImageView) v.findViewById(R.id.customToastIv);
		iv.setImageResource(imageResId);

		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(v);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
