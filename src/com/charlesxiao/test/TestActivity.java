package com.charlesxiao.test;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.charlesxiao.base.BaseActivity;
import com.charlesxiao.duangapp.R;
import com.charlesxiao.widget.AuthCode;

public class TestActivity extends BaseActivity {

	private ImageView authCodeIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();

	}

	@Override
	public void setContentView() {
		setContentView(R.layout.activity_test);

	}

	@Override
	public void findViews() {
		authCodeIv = (ImageView) findViewById(R.id.iv_authcode);

	}

	@Override
	public void showContent() {
		authCodeIv.setImageBitmap(AuthCode.getInstance().createBitmap());
	}

	@Override
	public void initLisenter() {
		// 测试本地生成验证码类功能
		authCodeIv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				authCodeIv
						.setImageBitmap(AuthCode.getInstance().createBitmap());
			}
		});

	}

}
