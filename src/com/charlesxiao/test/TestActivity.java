package com.charlesxiao.test;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.charlesxiao.base.BaseActivity;
import com.charlesxiao.duangapp.R;
import com.charlesxiao.widget.AuthCode;

public class TestActivity extends BaseActivity {

	private ImageView authCodeIv;
	private String code;
	private EditText authCodeEt;
	private Button authCodeBtn;

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
		authCodeEt = (EditText) findViewById(R.id.et_authcode);
		authCodeBtn = (Button) findViewById(R.id.btn_authcode);
	}

	@Override
	public void showContent() {
		authCodeIv.setImageBitmap(AuthCode.getInstance().createBitmap());
		code = AuthCode.getInstance().getCode();
	}

	@Override
	public void initLisenter() {
		// 测试本地生成验证码类功能
		authCodeIv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				authCodeIv
						.setImageBitmap(AuthCode.getInstance().createBitmap());
				code = AuthCode.getInstance().getCode();
			}
		});

		authCodeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				checkAuthCode();
			}
		});

	}

	/**
	 * 判断输入验证码是否正确(区分大小写)
	 */
	protected void checkAuthCode() {
		String etCode = authCodeEt.getText().toString();
		if (etCode != null && !etCode.equals("")) {
			if (etCode.equals(code)) {
				Toast.makeText(TestActivity.this, "验证码输入正确！",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(TestActivity.this, "验证码输入不正确！",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
