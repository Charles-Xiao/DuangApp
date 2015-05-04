package com.charlesxiao.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

import com.charlesxiao.base.BaseActivity;
import com.charlesxiao.duangapp.R;
import com.charlesxiao.widget.ActionBarDrawerToggle;
import com.charlesxiao.widget.DrawerArrowDrawable;

public class MainActivity extends BaseActivity {

	private DrawerLayout mDl;
	private ListView mLv;
	private LinearLayout mLl;
	private LinearLayout mLlLr;
	private FrameLayout mFl;
	private ActionBar mABar;
	private DrawerArrowDrawable mDaDrawable;
	private ActionBarDrawerToggle mDrawerToggle;
	private String[] lvItemTexts = { "主页", "社交", "计划" };
	private int[] lvItemIcons = { R.drawable.ic_drawer, R.drawable.ic_launcher,
			R.drawable.ic_launcher };
	private DrawerAdapter mDrawerAdapter;
	private SearchView mSearchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		mSearchView = (SearchView) menu.findItem(R.id.item_menu_search)
				.getActionView();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (mDl.isDrawerOpen(mLl)) {
				mDl.closeDrawer(mLl);
			} else {
				mDl.openDrawer(mLl);
			}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setContentView() {
		setContentView(R.layout.activity_main);
	}

	@Override
	public void findViews() {
		mDl = (DrawerLayout) findViewById(R.id.dl_main_nvdrawer);
		mLv = (ListView) findViewById(R.id.lv_main_nvdrawer);
		mFl = (FrameLayout) findViewById(R.id.frame_main_content);
		mLl = (LinearLayout) findViewById(R.id.ll_main_nvdrawer);
		mLlLr = (LinearLayout) findViewById(R.id.ll_main_lr);

		mABar = getActionBar();

		mDaDrawable = new DrawerArrowDrawable(this) {
			@Override
			public boolean isLayoutRtl() {
				return false;
			}
		};

		mDrawerToggle = new ActionBarDrawerToggle(this, mDl, mDaDrawable,
				R.string.main_drawer_open, R.string.main_drawer_close) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View view) {
				super.onDrawerOpened(view);
				invalidateOptionsMenu();
			}

		};
		mDrawerAdapter = new DrawerAdapter(this);
	}

	@Override
	public void showContent() {
		mABar.setDisplayHomeAsUpEnabled(true); // 给左上角图标的左边加上一个返回的图标
		mABar.setHomeButtonEnabled(true); // 决定左上角图标是否可以点击
		mABar.setDisplayShowHomeEnabled(false); // 不显示程序图标

		mLv.setAdapter(mDrawerAdapter);

	}

	@Override
	public void initLisenter() {
		mDl.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();

		mLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

			}
		});

		mLlLr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});
		mSearchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				Toast.makeText(MainActivity.this, "search " + arg0,
						Toast.LENGTH_SHORT).show();
				return false;
			}

			@Override
			public boolean onQueryTextChange(String arg0) {

				return true;
			}
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * @author xiaoyong 填充NavigationDrawer中ListView的Adapter
	 */
	private class DrawerAdapter extends BaseAdapter {

		private Context mContext;

		public DrawerAdapter(Context context) {
			mContext = context;
		}

		@Override
		public int getCount() {
			return lvItemTexts.length;
		}

		@Override
		public Object getItem(int arg0) {
			return lvItemTexts[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				convertView = inflater.inflate(R.layout.listitem_drawer,
						parent, false);
			}
			TextView tv = (TextView) convertView
					.findViewById(R.id.tv_listitem_drawer);
			ImageView iv = (ImageView) convertView
					.findViewById(R.id.iv_listitem_drawer);
			iv.setImageResource(lvItemIcons[position]);
			tv.setText(lvItemTexts[position]);
			return convertView;
		}
	}
}
