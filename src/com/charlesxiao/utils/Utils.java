package com.charlesxiao.utils;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author xiaoyong 常用工具方法
 */
public class Utils {
	/**
	 * 打卡软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}

	/**
	 * 读取指定asset目录中的图片文件为 Drawable
	 * 
	 * @param context
	 * @param imageFileName
	 * @return null if exception happened.
	 */
	public static Drawable getDrawableFromAssets(Context context,
			String imageFileName) {
		Drawable result = null;
		AssetManager assetManager = context.getAssets();
		InputStream is = null;
		try {
			is = assetManager.open(imageFileName);
			result = Drawable.createFromStream(is, null);
			is.close();
			is = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 检查网络状态,wifiOnly控制是否只检查wifi状态
	 * 
	 * @param c
	 * @param wifiOnly
	 * @return
	 */
	public static boolean isNetworkAvailable(Context c, boolean wifiOnly) {
		if (c == null) {
			return false;
		}

		Context context = c.getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity != null) {

			NetworkInfo[] info = connectivity.getAllNetworkInfo();

			if (info != null) {

				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						if (!wifiOnly) {
							info = null;
							return true;

						} else if (info[i].getType() == ConnectivityManager.TYPE_WIFI) {
							info = null;
							return true;
						}
					}
				}

			}
		}
		return false;
	}

	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity) {
		Intent intent = new Intent("/");
		ComponentName cm = new ComponentName("com.android.settings",
				"com.android.settings.WirelessSettings");
		intent.setComponent(cm);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivityForResult(intent, 0);
	}
}
