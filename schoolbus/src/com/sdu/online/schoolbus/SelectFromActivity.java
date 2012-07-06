package com.sdu.online.schoolbus;

import java.util.ArrayList;

import com.sdu.online.schoolbus.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
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
		
		
		ArrayList<View> mListViews = new ArrayList<View>();
		mListViews.add(getLayoutInflater().inflate(R.layout.select_from_pager, null));
		mListViews.add(getLayoutInflater().inflate(R.layout.select_from_pager, null));
		
		ViewPagerAdapter adapter = new ViewPagerAdapter(this,mListViews);
		pager.setAdapter(adapter);
		
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
		
	}
	

}
