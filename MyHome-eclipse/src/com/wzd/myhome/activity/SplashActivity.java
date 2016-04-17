package com.wzd.myhome.activity;

import android.content.Intent;
import android.os.Handler;

import com.wzd.myhome.R;
import com.wzd.myhome.activity.base.CoreActivity;

public class SplashActivity extends CoreActivity {

	@Override
	protected void initComponent() {
		// JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
		// JPushInterface.init(this);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// ActivityUtil.skipHome(mActivity);
				Intent intent = new Intent();
				intent.setClass(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
	}

	@Override
	protected void initData() {

	}

	@Override
	protected int getMainContentViewId() {
		return R.layout.activity_splash;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
}
