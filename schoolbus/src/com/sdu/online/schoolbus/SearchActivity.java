package com.sdu.online.schoolbus;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sdu.online.schoolbus.model.BusInfo;
import com.sdu.online.schoolbus.model.SchoolBusModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {

	private TextView startPlace,startTime,endPlace,betweenPlace,remark;
	private TextView chooseDes ,isWeekDay,schedule,title;
	private ImageView search;
	private String start,end,rawStart;
	private ListView listView;
	private LinearLayout wrapper,topLayout,noFoundLayout;
	private RelativeLayout listLayout,titleLayout;
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
//		startPlace = (TextView) findViewById(R.id.detail_layout_from_time);
//		startTime = (TextView)findViewById(R.id.detail_layout_from_time);
//		endPlace = (TextView)findViewById(R.id.detail_layout_to_place);
//		betweenPlace = (TextView)findViewById(R.id.detail_layout_between_place);
//		remark = (TextView)findViewById(R.id.detail_layout_remark);
		search = (ImageView)findViewById(R.id.search_layout_search_image);
		chooseDes = (TextView)findViewById(R.id.search_layout_choose_des);
		isWeekDay = (TextView)findViewById(R.id.search_layout_weekday);
		listView = (ListView)findViewById(R.id.search_layout_listview);
		schedule  = (TextView)findViewById(R.id.search_layout_schedule);
		wrapper = (LinearLayout)findViewById(R.id.search_layout_wrapper);
		topLayout = (LinearLayout)findViewById(R.id.search_layout_top);
		listLayout = (RelativeLayout)findViewById(R.id.search_layout_list_layout);
		title = (TextView)findViewById(R.id.search_layout_title_tv);
		titleLayout = (RelativeLayout)findViewById(R.id.search_layout_title_layout);
		noFoundLayout = (LinearLayout)findViewById(R.id.search_layout_no_found_layout);
	}
	
	private void init(){
		Intent intent = getIntent();
		rawStart = intent.getStringExtra("start");
		String result[] = rawStart.split("\n");
		start = result.length==1||result[1]==null||result[1].equals("") ? result[0] : result[0]+result[1];
		//确定工作日非工作日
		Date date = new Date();
		int day = date.getDay();
		if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
			weekDay = SchoolBusModel.NO_WEEK_DAY;
			isWeekDay.setText(getResources().getString(R.string.time_nowork));
		}else{
			weekDay = SchoolBusModel.WEEK_DAY;
			isWeekDay.setText(getResources().getString(R.string.time_work));
		}
		
		SchoolBusModel model = SchoolBusModel.getInstance();
		scheduleType = model.getSchedule(this);
		if(scheduleType == SchoolBusModel.SUMMER_TIME)
			schedule.setText(getResources().getString(R.string.schedule_tip_summer));
		else schedule.setText(getResources().getString(R.string.schedule_tip_winter));
		schedule.getPaint().setFakeBoldText(true);
		title.setText("起始地: "+start);
		
		//TODO 皮肤相关
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		int color = sp.getInt("color_theme", R.color.main_color_blue);
		search.setBackgroundResource(color);
		chooseDes.setBackgroundResource(color);
		isWeekDay.setBackgroundResource(color);
		schedule.setTextColor(getResources().getColor(color));
		titleLayout.setBackgroundResource(color);
		
	}
	
	private void setListeners(){
		OnClickListener l = new Listener();
		chooseDes.setOnClickListener(l);
		search.setOnClickListener(l);
		isWeekDay.setOnClickListener(l);
	}
	
	private void search(){
		startAnim();
		SchoolBusModel model = SchoolBusModel.getInstance();
    	List<BusInfo> busInfo = model.getBus(this,start,end,weekDay,scheduleType);
    	if(!busInfo.isEmpty()){
        	BaseAdapter adapter = new BusCellAdapter(this,busInfo,listView);
        	listView.setAdapter(adapter);
        	noFoundLayout.setVisibility(View.GONE);
        	listView.setVisibility(View.VISIBLE);
    	}else{
    		listView.setVisibility(View.GONE);
    		noFoundLayout.setVisibility(View.VISIBLE);
    	}
    	wrapper.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    	Log.v(TAG, busInfo.toString());
	}
	
	/**开始动画*/
	private void startAnim(){
		float fromXDelta = topLayout.getLeft();
		float fromYDelta = topLayout.getTop();
//		float toXDelta = title.getLeft();
//		float toYDelta = title.getBottom();
		float toXDelta = wrapper.getLeft();
		float toYDelta = wrapper.getTop()-titleLayout.getHeight();
		Animation anim = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
		anim.setFillAfter(true);
		anim.setDuration(400);
		wrapper.startAnimation(anim);
		
		Animation anima = new AlphaAnimation(0, 1);
		anima.setFillAfter(true);
		anima.setDuration(500);
		anima.setStartOffset(350);
		listLayout.startAnimation(anima);
	}
	
	private boolean checkInput(){
		if(chooseDes.getText().equals(getResources().getString(R.string.choose_des_tip))){
			Toast.makeText(this, "请先选择目的地", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	private  class Listener implements OnClickListener{
		public void onClick(View v) {
			if(v == chooseDes){
				Intent intent = new Intent();
				intent.setClass(SearchActivity.this, ChooseDesActivity.class);
				intent.putExtra("start", rawStart);
				startActivityForResult(intent, 1);
			}else if(v == search){
				if(checkInput())
					search();
			}else if(v == isWeekDay){
				if(((TextView)v).getText().equals(getResources().getString(R.string.time_nowork))){
					((TextView)v).setText(getResources().getString(R.string.time_work));
					weekDay = SchoolBusModel.WEEK_DAY;
				}
				else{
					((TextView)v).setText(getResources().getString(R.string.time_nowork));
					weekDay = SchoolBusModel.NO_WEEK_DAY;	
				}
			}
		}
		
	}

}
