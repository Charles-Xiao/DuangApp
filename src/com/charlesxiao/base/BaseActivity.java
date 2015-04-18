package com.charlesxiao.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	public void init() {
		setContentView();
		findViews();
		showContent();
		initLisenter();
	}

	public abstract void setContentView();

	public abstract void findViews();

	public abstract void showContent();

	public abstract void initLisenter();

}
