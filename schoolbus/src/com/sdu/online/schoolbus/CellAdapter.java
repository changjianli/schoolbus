package com.sdu.online.schoolbus;

import java.util.List;

import com.sdu.online.schoolbus.model.BusInfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CellAdapter extends BaseAdapter {

	private List<BusInfo> busInfo;
	private LayoutInflater mInflater;
	private static final String TAG = CellAdapter.class.getSimpleName();
	
	public CellAdapter(Context context,List<BusInfo> busInfo){
		this.busInfo = busInfo;
		mInflater = LayoutInflater.from(context);
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
		ViewHolder holder;
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.list_cell, null);
			holder = new ViewHolder();
			holder.tv1 = (TextView) convertView.findViewById(R.id.start_text);
			holder.tv2 = (TextView) convertView.findViewById(R.id.time_text);
			holder.tv3 = (TextView) convertView.findViewById(R.id.remark_text);
			holder.iv = (ImageView)convertView.findViewById(R.id.list_cell_more);
		}else 
			holder = (ViewHolder)convertView.getTag();
		holder.tv1.setText(busInfo.get(position).getFullFrom());
		holder.tv2.setText(busInfo.get(position).getStartTime());
		holder.tv3.setText(busInfo.get(position).getRemark());
		
		final BusInfo bus = busInfo.get(position);
		holder.iv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.d(TAG, bus.toString());
			}
		});
		return convertView;
	}
	
	class ViewHolder{
		public TextView tv1;
		public TextView tv2;
		public TextView tv3;
		public ImageView iv;
	}

}
