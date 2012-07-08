package com.sdu.online.schoolbus;

import java.util.List;

import com.sdu.online.schoolbus.model.BusInfo;
import com.sdu.online.schoolbus.model.SchoolBusModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {

	private TextView isWeekDay;
	private TextView fromD,endD;
	private String start,end;
	private ListView listView;
	private SchoolBusModel model;
	private int timeflag=1;		//默认夏季表
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		findViews();
		findpath();
	}
	
	private void findpath(){
		Intent it=getIntent();
		start=it.getStringExtra("start");
		end=it.getStringExtra("end");
		fromD.setText(start);
		endD.setText(end);
		model = SchoolBusModel.getInstance();
    	List<BusInfo> busInfo = model.getBus(this,start,end,1,1);
    	if(busInfo.size()==0){
    		Toast.makeText(this,"对不起，当前没有该车次！", 3000).show();
    	}
    	BaseAdapter adapter = new BusCellAdapter(this,busInfo);
    	listView.setAdapter(adapter);
    	isWeekDay.setOnClickListener(new Listener());
	}

	private void findViews(){
		fromD=(TextView)findViewById(R.id.station_from);
		endD=(TextView)findViewById(R.id.station_end);
		isWeekDay = (TextView)findViewById(R.id.search_layout_weekday);
		listView = (ListView)findViewById(R.id.search_layout_listview);
	}	

	private  class Listener implements OnClickListener{
		public void onClick(View v) {
			
				if(((TextView)v).getText().equals(getResources().getString(R.string.time_nowork))){
					((TextView)v).setText(getResources().getString(R.string.time_work));
					List<BusInfo> busInfo = model.getBus(SearchActivity.this,start,end,timeflag,1);
			    	if(busInfo.size()==0){
			    		Toast.makeText(SearchActivity.this,"对不起，当前没有该车次！", 3000).show();
			    	}
			    	BaseAdapter adapter = new BusCellAdapter(SearchActivity.this,busInfo);
			    	listView.setAdapter(adapter);
				}				
				else{
					((TextView)v).setText(getResources().getString(R.string.time_nowork));
					List<BusInfo> busInfo = model.getBus(SearchActivity.this,start,end,timeflag,2);
			    	if(busInfo.size()==0){
			    		Toast.makeText(SearchActivity.this,"对不起，当前没有该车次！", 3000).show();
			    	}
			    	BaseAdapter adapter = new BusCellAdapter(SearchActivity.this,busInfo);
			    	listView.setAdapter(adapter);
					}
				}
		}
		
	}


