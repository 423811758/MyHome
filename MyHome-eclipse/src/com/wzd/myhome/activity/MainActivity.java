package com.wzd.myhome.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.wzd.myhome.R;
import com.zzy.app.util.Log4JUtil;
import com.zzy.app.util.ToastUtil;

public class MainActivity extends Activity {

	private TextView mMsgTv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMsgTv = (TextView) findViewById(R.id.msg);
		mMsgTv.setText("-------------");
		Log4JUtil.debugInfo("----------------------------");
		EMClient.getInstance().login("wzd", "123456", new EMCallBack() {// 回调
					@Override
					public void onSuccess() {
						runOnUiThread(new Runnable() {
							public void run() {
								EMClient.getInstance().groupManager()
										.loadAllGroups();
								EMClient.getInstance().chatManager()
										.loadAllConversations();
								Log4JUtil.debugInfo("登陆聊天服务器成功！");
//								ToastUtil.showShortToast(MainActivity.this, "登陆聊天服务器成功！");
							}
						});
					}

					@Override
					public void onProgress(int progress, String status) {

					}

					@Override
					public void onError(int code, String message) {
						Log4JUtil.debugInfo("登陆聊天服务器失败！");
//						ToastUtil.showShortToast(MainActivity.this, "登陆聊天服务器失败！");
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
