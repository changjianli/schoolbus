package com.sdu.online.schoolbus;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {

	LayoutInflater mInflater;
	List<View> views;
	Context context;
	
	private static final String TAG = ViewPagerAdapter.class.getSimpleName();
	
	public ViewPagerAdapter(Context context,List<View> views){
		this.views = views;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeViewAt(0);
	}

	@Override
	public Object instantiateItem(View container, int position) {
		ViewHolder holder;

			holder = new ViewHolder();
			holder.tv1 = (TextView) views.get(position).findViewById(R.id.select_from_tv1);
			holder.tv2 = (TextView) views.get(position).findViewById(R.id.select_from_tv2);
			holder.tv3 = (TextView) views.get(position).findViewById(R.id.select_from_tv3);
			holder.tv4 = (TextView) views.get(position).findViewById(R.id.select_from_tv4);
			holder.tv5 = (TextView) views.get(position).findViewById(R.id.select_from_tv5);
			holder.tv6 = (TextView) views.get(position).findViewById(R.id.select_from_tv6);
			setListener(holder.tv1);
			setListener(holder.tv2);
			setListener(holder.tv3);
			setListener(holder.tv4);
			setListener(holder.tv5);
			setListener(holder.tv6);

		switch(position){
		case 0:
			holder.tv1.setText(R.string.start_palce_xing);
			holder.tv2.setText(R.string.start_palce_zhong);
			holder.tv3.setText(R.string.start_palce_bao);
			holder.tv4.setText(R.string.start_palce_qian);
			holder.tv5.setText(R.string.start_palce_hong);
			holder.tv6.setText(R.string.start_palce_ruan);
			Log.d(TAG, "position 0");
			break;
		case 1:
			holder.tv1.setText(R.string.start_palce_san);
			holder.tv2.setText(R.string.start_palce_wu);
			holder.tv3.setText(R.string.start_palce_ma);
			holder.tv4.setText(R.string.start_palce_nan);
			holder.tv5.setText(R.string.start_palce_yan);
			holder.tv6.setText(R.string.start_palce_shun);
			Log.d(TAG, "position 1");
			break;
		}
		LayoutParams params = new LayoutParams();
		params.height = LayoutParams.FILL_PARENT;
		params.width = LayoutParams.FILL_PARENT;
		((ViewPager)container).addView(views.get(position), 0,params);
		return views.get(position);
	}
	
	private void setListener(final View view){
		view.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, ChooseDesActivity.class);
				intent.putExtra("start", ((TextView)view).getText().toString());
				context.startActivity(intent);
			}
		});
	}

	class ViewHolder{
		TextView tv1,tv2,tv3,tv4,tv5,tv6;
	}
	
}
