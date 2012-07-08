package com.sdu.online.schoolbus;

import java.util.ArrayList;

import com.sdu.online.schoolbus.R.id;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableLayout;

public class SelectFromActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_from_layout);
		ViewPager pager = (ViewPager) findViewById(R.id.select_from_layout_viewpager);
		final ImageView iv1 = (ImageView) findViewById(R.id.select_from_layout_iv1);
		final ImageView iv2 = (ImageView) findViewById(R.id.select_from_layout_iv2);
		ImageView settingsView = (ImageView)findViewById(R.id.settings);

		
		ArrayList<View> mListViews = new ArrayList<View>();
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
				switch(arg0){
				case 0:
					iv1.setImageResource(R.drawable.point_selected);
					iv2.setImageResource(R.drawable.point_normal);
					break;
				case 1:
					iv1.setImageResource(R.drawable.point_normal);
					iv2.setImageResource(R.drawable.point_selected);
					break;
				}
			}
			
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		Intent intent = new Intent();
		intent.setClass(this, UpdateService.class);
		startService(intent);
		
		
		//一些初始化配置
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		PackageInfo info = null;
		Editor editor= sp.edit();
		try {
			 info = getPackageManager().getPackageInfo(this.getPackageName(), 0);
		} catch (NameNotFoundException e) {e.printStackTrace();}
		if(sp.getString("app_version",null) == null){
			editor.putString("app_version", info.versionName);
		}if(sp.getString("db_version", null) == null){
			editor.putString("db_version", "20120707");
		}
		editor.commit();
		
	}
	

}
