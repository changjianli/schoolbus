package com.sdu.online.schoolbus;

import java.util.Calendar;
import java.util.List;

import com.sdu.online.schoolbus.model.BusInfo;
import com.sdu.online.schoolbus.model.SchoolBusModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends Activity {

	private TextView startPlace,startTime,endPlace,betweenPlace,remark;
	private TextView chooseDes ,isWeekDay,schedule;
	private ImageView search;
	private String start,end,rawStart;
	private ListView listView;
	private static final String TAG = SearchActivity.class.getSimpleName();
	
	private int weekDay,scheduleType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		findViews();
		init();
		setListeners();
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case 1:
			if(resultCode == RESULT_OK){
				String rawEnd= data.getStringExtra("end");
				String endArr[] = rawEnd.split("\n");
				end = endArr.length==1||endArr[1]==null||endArr[1].equals("") ? endArr[0] : endArr[0] + endArr[1];
				chooseDes.setText(rawEnd);
			}
			break;
		}
	}



	private void findViews(){
		startPlace = (TextView) findViewById(R.id.detail_layout_from_time);
		startTime = (TextView)findViewById(R.id.detail_layout_from_time);
		endPlace = (TextView)findViewById(R.id.detail_layout_to_place);
		betweenPlace = (TextView)findViewById(R.id.detail_layout_between_place);
		remark = (TextView)findViewById(R.id.detail_layout_remark);
		search = (ImageView)findViewById(R.id.search_layout_search_image);
		chooseDes = (TextView)findViewById(R.id.search_layout_choose_des);
		isWeekDay = (TextView)findViewById(R.id.search_layout_weekday);
		listView = (ListView)findViewById(R.id.search_layout_listview);
	}
	
	private void init(){
		Intent intent = getIntent();
		rawStart = intent.getStringExtra("start");
		String result[] = rawStart.split("\n");
		start = result.length==1||result[1]==null||result[1].equals("") ? result[0] : result[0]+result[1];
		
	}
	
	private void setListeners(){
		OnClickListener l = new Listener();
		chooseDes.setOnClickListener(l);
		search.setOnClickListener(l);
		isWeekDay.setOnClickListener(l);
	}
	
	private void search(){
		SchoolBusModel model = SchoolBusModel.getInstance();
    	List<BusInfo> busInfo = model.getBus(this,start,end,1);
    	
    	BaseAdapter adapter = new BusCellAdapter(this,busInfo);
    	listView.setAdapter(adapter);
    	Log.v(TAG, busInfo.toString());
	}
	
	private  class Listener implements OnClickListener{
		public void onClick(View v) {
			if(v == chooseDes){
				Intent intent = new Intent();
				intent.setClass(SearchActivity.this, ChooseDesActivity.class);
				intent.putExtra("start", rawStart);
				startActivityForResult(intent, 1);
			}else if(v == search){
				search();
			}else if(v == isWeekDay){
				if(((TextView)v).getText().equals(getResources().getString(R.string.time_nowork)))
					((TextView)v).setText(getResources().getString(R.string.time_work));
				else
					((TextView)v).setText(getResources().getString(R.string.time_nowork));
			}
		}
		
	}

}
