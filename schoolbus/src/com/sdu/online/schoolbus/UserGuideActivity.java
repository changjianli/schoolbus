package com.sdu.online.schoolbus;

import java.util.ArrayList;
import java.util.List;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class UserGuideActivity extends Activity {

	private ArrayList<ImageView> views;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobclickAgent.onError(this);
		views = new ArrayList<ImageView>();
		ImageView iv = new ImageView(this);
		iv.setBackgroundResource(R.drawable.lead_01);
		views.add(iv);
		iv = new ImageView(this);
		iv.setBackgroundResource(R.drawable.lead_02);
		views.add(iv);
		iv = new ImageView(this);
		iv.setBackgroundResource(R.drawable.lead_03);
		views.add(iv);
		iv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				UserGuideActivity.this.finish();
			}
		});
		//添加最后一个空图片，用于滑动到最后结束Activity
		iv = new ImageView(this);
		views.add(iv);
		
		ViewPager pager = new ViewPager(this);
		pager.setAdapter(new GuideAdapter(this, views));
		setContentView(pager);
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int arg0) {
				if(arg0 == views.size()-1)
					UserGuideActivity.this.finish();
			}
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			public void onPageScrollStateChanged(int arg0) {}
		});
	}
	
	class GuideAdapter extends PagerAdapter{

		List<ImageView> ivs;
		Context context;
		
		public GuideAdapter(Context context, List<ImageView> views) {
			ivs = views;
			this.context = context;
		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			ImageView iv = ivs.get(position);
			LayoutParams params = new LayoutParams();
			params.height = LayoutParams.FILL_PARENT;
			params.width = LayoutParams.FILL_PARENT;
			((ViewPager)container).addView(iv,0,params);
			return views.get(position);
		}
	}
}
