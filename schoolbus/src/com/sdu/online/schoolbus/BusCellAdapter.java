package com.sdu.online.schoolbus;

import java.util.List;
import com.sdu.online.schoolbus.model.BusInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class BusCellAdapter extends BaseAdapter {
	private List<BusInfo> busInfo;
	private LayoutInflater mInflater;
	private static final String TAG = BusCellAdapter.class.getSimpleName();
	private ViewHolder clickedHolder;
	private ListView listView;
	private int selected = -1;
	
	private int resid,color;
	public BusCellAdapter(Context context,List<BusInfo> busInfo,ListView listView){
		this.busInfo = busInfo;
		mInflater = LayoutInflater.from(context);
		this.listView = listView;
		
		//初始化背景资源信息
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		int theme = sp.getInt("theme", 1);
		switch(theme){
		case 1:
			resid = R.drawable.btn_bg_01;
			color = R.color.main_color_01_alpha;
			break;
		case 2:
			resid = R.drawable.btn_bg_02;
			color = R.color.main_color_02_alpha;
			break;
		case 3:
			resid = R.drawable.btn_bg_03;
			color = R.color.main_color_03_alpha;
			break;
		}
	}
	
	public int getCount() {
		return busInfo.size();
	}

	public Object getItem(int position) {
		return busInfo.get(position);
	}

	public long getItemId(int position) {
		return busInfo.get(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup viewParent) {
		final ViewHolder holder;
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.bus_list_cell, null);
			holder = new ViewHolder();
			holder.tv1 = (TextView) convertView.findViewById(R.id.start_text);
			holder.tv2 = (TextView) convertView.findViewById(R.id.time_text);
			holder.tv3 = (TextView) convertView.findViewById(R.id.remark_text);
			holder.layout = (LinearLayout)convertView.findViewById(R.id.detail_layout);
			holder.detailStartTime = (TextView)convertView.findViewById(R.id.detail_layout_from_time);
			holder.detailStartPlace = (TextView)convertView.findViewById(R.id.detail_layout_from_place);
			holder.detailBetweenPlace= (TextView)convertView.findViewById(R.id.detail_layout_between_place);
			holder.detailEndPlace= (TextView)convertView.findViewById(R.id.detail_layout_to_place);
			holder.detailRemark = (TextView)convertView.findViewById(R.id.detail_layout_remark);
			holder.title = (LinearLayout)convertView.findViewById(R.id.bus_list_cell_entry);
			convertView.setTag(holder);
		}else 
			holder = (ViewHolder)convertView.getTag();
		holder.tv2.setText(busInfo.get(position).getFullFrom());
		holder.tv1.setText(busInfo.get(position).getStartTime());
		holder.detailStartPlace.setText(busInfo.get(position).getFullFrom());
		holder.detailEndPlace.setText(busInfo.get(position).getFullTo());
		holder.detailStartTime.setText(busInfo.get(position).getStartTime());
		String remark = "";
		if(busInfo.get(position).getRemark() != null)
			remark = busInfo.get(position).getRemark();
		remark += "["+ (busInfo.get(position).getBusType() == 0 ? "班车":"交通车")+"]";
		holder.detailRemark.setText(remark);
		String betweenBuses = busInfo.get(position).getBusBetween();
		holder.detailBetweenPlace.setText(betweenBuses);
		
		if(selected != position)	holder.layout.setVisibility(View.GONE);
		else if(selected != -1)	holder.layout.setVisibility(View.VISIBLE);
		
		final int p = position;
		final BusInfo bus = busInfo.get(position);
		holder.title.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				listView.setSelection(p);
				Log.d(TAG, "clicked: "+p);
				Log.d(TAG, bus.toString());
				if(clickedHolder != null && clickedHolder != holder){
					clickedHolder.layout.setVisibility(View.GONE);
					Log.d(TAG, "some holder gone");
				}
				if(holder.layout.getVisibility() != View.VISIBLE){	
					holder.layout.setVisibility(View.VISIBLE); 
					selected = p;Log.d(TAG, " the holder layout visiable");
				}else {
					holder.layout.setVisibility(View.GONE); 
					Log.d(TAG, "the holder layout gone");
					selected = -1;
				}
				clickedHolder = holder;
			}
		});
		
		holder.title.setBackgroundResource(resid);
		holder.layout.setBackgroundResource(color);
		return convertView;
	}
	
	class ViewHolder{
		public TextView tv1;
		public TextView tv2;
		public TextView tv3;
		public TextView detailStartTime;
		public TextView detailStartPlace;
		public TextView detailBetweenPlace;
		public TextView detailEndPlace;
		public TextView detailRemark;
		public LinearLayout layout;
		public LinearLayout title;
	}

}
