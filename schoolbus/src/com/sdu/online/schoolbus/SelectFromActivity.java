package com.sdu.online.schoolbus;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

public class SelectFromActivity extends Activity {
	ViewPager pager;
	ImageView iv1 = null,iv2 = null,iv3 = null,settingsView,placeTip;
	SharedPreferences sp;
	int page = 0;
	RelativeLayout topLayout,bottomLayout,mainLayout;
	/*存储下面表示页数的圆点资源id*/
	int pointSelected= 0,pointNormal = 0;
	
	private static final String TAG = SelectFromActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_from_layout);
		findViews();
		sp = PreferenceManager.getDefaultSharedPreferences(this);
	}
	
	
	
	@Override
	protected void onResume() {
		//在这里进行主题的初始化
		initTheme();
		setListeners();
		pager.setCurrentItem(page);
		setPointSelected(page);
		super.onResume();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		page = savedInstanceState.getInt("page");
		pager.setCurrentItem(page);
		Log.d(TAG, "restore..."+page);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("page", page);
		Log.d(TAG, "save..."+page);
	}



	private void findViews(){
		pager = (ViewPager) findViewById(R.id.select_from_layout_viewpager);
		iv1 = (ImageView) findViewById(R.id.select_from_layout_iv1);
		iv2 = (ImageView) findViewById(R.id.select_from_layout_iv2);
		iv3 = (ImageView) findViewById(R.id.select_from_layout_iv3);
		settingsView = (ImageView)findViewById(R.id.settings);
		topLayout = (RelativeLayout)findViewById(R.id.select_from_layout_top);
		bottomLayout = (RelativeLayout)findViewById(R.id.select_from_layout_bottom);
		mainLayout = (RelativeLayout)findViewById(R.id.select_layout_main);
		placeTip = (ImageView)findViewById(R.id.iv_place_tip);
	}
	
	private void setListeners(){
		ArrayList<View> mListViews = new ArrayList<View>();
		mListViews.add(getLayoutInflater().inflate(R.layout.select_from_pager, null));
		mListViews.add(getLayoutInflater().inflate(R.layout.select_from_pager, null));
		mListViews.add(getLayoutInflater().inflate(R.layout.select_from_pager, null));
		
		ViewPagerAdapter adapter = new ViewPagerAdapter(this,mListViews);
		pager.setAdapter(adapter);
		
		settingsView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SelectFromActivity.this, SettingsActivity.class);
				startActivity(intent);
			}
		});
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int arg0) {
				setPointSelected(arg0);
			}
			
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			public void onPageScrollStateChanged(int arg0) {}
		});
	}

	/**设置滑页下方的选中点*/
	private void setPointSelected(int position){
		switch(position){
		case 0:
			iv1.setImageResource(pointSelected);
			iv2.setImageResource(pointNormal);
			iv3.setImageResource(pointNormal);
			page = 0;
			break;
		case 1:
			iv1.setImageResource(pointNormal);
			iv2.setImageResource(pointSelected);
			iv3.setImageResource(pointNormal);
			page = 1;
			break;
		case 2:
			iv1.setImageResource(pointNormal);
			iv2.setImageResource(pointNormal);
			iv3.setImageResource(pointSelected);
			page = 2;
			break;
		}
	}
	
	/**初始化主题资源*/
	private void initTheme(){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		switch(sp.getInt("theme", 1)){
		case 1:
			topLayout.setBackgroundResource(R.drawable.bg_title_01);
			bottomLayout.setBackgroundResource(R.drawable.bg_bottom_01);
			mainLayout.setBackgroundResource(R.drawable.bg_01);
			placeTip.setImageResource(R.drawable.ic_place_tip_01);
			pointSelected = R.drawable.point_selected_01;
			pointNormal = R.drawable.point_normal_01;
			break;
		case 2:
			topLayout.setBackgroundResource(R.drawable.bg_title_02);
			bottomLayout.setBackgroundResource(R.drawable.bg_bottom_02);
			mainLayout.setBackgroundResource(R.drawable.bg_02);
			placeTip.setImageResource(R.drawable.ic_place_tip_02);
			pointSelected = R.drawable.point_selected_02;
			pointNormal = R.drawable.point_normal_02;
			break;
		case 3:
			topLayout.setBackgroundResource(R.drawable.bg_title_03);
			bottomLayout.setBackgroundResource(R.drawable.bg_bottom_03);
			mainLayout.setBackgroundResource(R.drawable.bg_03);
			placeTip.setImageResource(R.drawable.ic_place_tip_03);
			pointSelected = R.drawable.point_selected_03;
			pointNormal = R.drawable.point_normal_03;
			break;
		}
	}
	
}
