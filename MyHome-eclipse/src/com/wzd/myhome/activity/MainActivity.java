package com.wzd.myhome.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.wzd.myhome.R;
import com.zzy.app.util.Log4JUtil;

public class MainActivity extends Activity {

	private TextView mMsgTv;
	private Button mCheckUpdateBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMsgTv = (TextView) findViewById(R.id.msg);
		mCheckUpdateBtn = (Button) findViewById(R.id.check_update_btn);
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
								// ToastUtil.showShortToast(MainActivity.this,
								// "登陆聊天服务器成功！");
							}
						});
					}

					@Override
					public void onProgress(int progress, String status) {

					}

					@Override
					public void onError(int code, String message) {
						Log4JUtil.debugInfo("登陆聊天服务器失败！");
						// ToastUtil.showShortToast(MainActivity.this,
						// "登陆聊天服务器失败！");
					}
				});

		mCheckUpdateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PgyUpdateManager.register(MainActivity.this,
						new UpdateManagerListener() {

							@Override
							public void onUpdateAvailable(final String result) {

								// 将新版本信息封装到AppBean中
								final AppBean appBean = getAppBeanFromString(result);
								new AlertDialog.Builder(MainActivity.this)
										.setTitle("更新")
										.setMessage(appBean.getReleaseNote())
										.setNegativeButton(
												"确定",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														startDownloadTask(
																MainActivity.this,
																appBean.getDownloadURL());
													}
												}).show();
							}

							@Override
							public void onNoUpdateAvailable() {
							}
						});
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
