package com.wzd.myhome.activity.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.wzd.common.data.GlobalData;
import com.wzd.common.util.ZzyProgressDiologUtil;
import com.wzd.myhome.R;
import com.wzd.myhome.setting.SystemSetting;
import com.zzy.app.util.ToastUtil;

public abstract class CoreActivity extends FragmentActivity {

	/** 系统输入管理对象 */
	private InputMethodManager inputMethod;

	protected Dialog pd;

	protected Activity mActivity;
	protected Context mContext;

	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (getMainContentViewId() != 0) {
			setContentView(getMainContentViewId()); // set view
		}

		mActivity = this;
		mContext = getApplicationContext();

		initComponent();

		initData();
	}

	/**
	 * 显示等待对话框
	 * 
	 * @param isCanCancel
	 *            TODO
	 */
	public void showDialog(boolean isCanCancel) {
		if (!isCanCancel) {
			pd = ZzyProgressDiologUtil.createCustomeDialogDisCancle(this,
					R.string.please_wait);
		} else {
			pd = ZzyProgressDiologUtil.createCustomeDialog(this,
					R.string.please_wait);
		}
		pd.show();
	}

	/**
	 * 隐藏对话框
	 */
	public void hideDialog() {
		if (pd != null && pd.isShowing()) {
			pd.dismiss();
		}
	}

	protected void superOnResume() {
		super.onResume();
		// GlobalData.isJumping = false;
	}

	protected void superOnStart() {
		super.onStart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		// logger.info("onStart");
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 取消通知栏图标
		new Handler().post(new Runnable() {// 设置字体为系统默认大小，不受系统设置影响

					@Override
					public void run() {
						Resources resource = getResources();
						Configuration c = resource.getConfiguration();
						c.fontScale = (float) 1.0;
						resource.updateConfiguration(c,
								resource.getDisplayMetrics());
					}
				});
	}

	/**
	 * 隐藏键盘
	 */
	public void hideKeyboard() {
		if (this.getCurrentFocus() != null) {
			inputMethod.hideSoftInputFromWindow(
					// 隐藏软键盘
					this.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * 显示键盘
	 */
	public void showKeyboard(EditText inputEt) {
		inputMethod.showSoftInput(inputEt, 0);
	}

	/**
	 * 切换键盘显示/隐藏
	 */
	public void toggleKeyboard() {
		inputMethod.toggleSoftInput(0, 0);
	}

	protected int getVisiableHeight() {
		int height = GlobalData.getScreenVisiableHeight();
		if (height != 0)
			return height;
		Rect frame = new Rect();
		getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		SystemSetting.getInstance().setGlobalScreenVisiableHeight(frame.bottom);
		return frame.bottom;
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	protected void onPause() {
		ToastUtil.cancelToast();
		hideKeyboard();
		super.onPause();
	}

	protected void superOnStop() {
		super.onStop();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		try {
			super.onSaveInstanceState(outState);
		} catch (Exception e) {
			Log.e("CoreActivity", "onSaveInstanceState..."
					+ getClass().getName());
			e.printStackTrace();
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		try {
			super.onRestoreInstanceState(savedInstanceState);
		} catch (Exception e) {
			Log.e("CoreActivity", "onRestoreInstanceState..."
					+ getClass().getName());
			e.printStackTrace();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_UP:
			audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND
							| AudioManager.FLAG_SHOW_UI);
			return true;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND
							| AudioManager.FLAG_SHOW_UI);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View v = getCurrentFocus();
			if (isShouldHideKeyboard(v, ev)) {
				hideKeyboard();
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
	 * 
	 * @param v
	 * @param event
	 * @return
	 */
	private boolean isShouldHideKeyboard(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] l = { 0, 0 };
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
					+ v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				// 点击EditText的事件，忽略它。
				return false;
			} else {
				return true;
			}
		}
		// 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
		return false;
	}

	/**
	 * 初始化组件
	 */
	protected abstract void initComponent();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 布局ID
	 */
	protected abstract int getMainContentViewId();

}
