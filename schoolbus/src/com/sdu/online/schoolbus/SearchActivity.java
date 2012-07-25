package com.sdu.online.schoolbus;

import java.util.Calendar;
import java.util.List;
import com.sdu.online.schoolbus.model.BusInfo;
import com.sdu.online.schoolbus.model.SchoolBusModel;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {

	private TextView chooseDes ,isWeekDay,schedule,title;
	private ImageView search,settingsView,themeView;
	private String start,end,rawStart;
	private ListView listView;
	private LinearLayout wrapper,topLayout,noFoundLayout;
	private ScrollView sv;
	private RelativeLayout listLayout,titleLayout,bottomLayout,mainLayout;
	private static final String TAG = SearchActivity.class.getSimpleName();
	
	private int weekDay,scheduleType;

	private float cellWidth,cellHeight,fontSize,margin;
	private String placeArr[];
	
	/**存储所有可点击的组件的背景资源id*/
	int resid = 0;
	
	/**用于完成延迟移除目的地的选择view等UI操作的Handler*/
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 1:
				/**移除用于显示目的地选择的view*/
				Log.v(TAG, "gone...");
				sv.removeAllViews();
				sv.setVisibility(View.GONE);
				//把背景去掉，以防动画造成的显示问题
				sv.setBackgroundColor(Color.parseColor("#00ffffff"));
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		findViews();
		setListeners();
		init();
	}

	
	
	@Override
	protected void onResume() {
		initTheme();
		super.onResume();
	}



	private void findViews(){
		search = (ImageView)findViewById(R.id.search_layout_search_image);
		chooseDes = (TextView)findViewById(R.id.search_layout_choose_des);
		isWeekDay = (TextView)findViewById(R.id.search_layout_weekday);
		listView = (ListView)findViewById(R.id.search_layout_listview);
		schedule  = (TextView)findViewById(R.id.search_layout_schedule);
		wrapper = (LinearLayout)findViewById(R.id.search_layout_wrapper);
		topLayout = (LinearLayout)findViewById(R.id.search_layout_top);
		listLayout = (RelativeLayout)findViewById(R.id.search_layout_list_layout);
		title = (TextView)findViewById(R.id.search_tv_title);
		titleLayout = (RelativeLayout)findViewById(R.id.select_from_layout_top);
		noFoundLayout = (LinearLayout)findViewById(R.id.search_layout_no_found_layout);
		sv = (ScrollView)findViewById(R.id.search_sv_des_all);
		bottomLayout = (RelativeLayout)findViewById(R.id.select_from_layout_bottom);
		mainLayout = (RelativeLayout)findViewById(R.id.search_main);
		settingsView = (ImageView)findViewById(R.id.settings);
		themeView = (ImageView)findViewById(R.id.change_theme_iv);
	}
	
	private void init(){
		Intent intent = getIntent();
		rawStart = intent.getStringExtra("start");
		start = rawStart.replaceAll("\n", "");
		//确定工作日非工作日
		int day=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
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
		
		/*添加选择目的地的布局时需要用到的*/
		cellWidth = getResources().getDimension(R.dimen.cell_width);
		cellHeight = getResources().getDimension(R.dimen.cell_height);
		fontSize = getResources().getDimension(R.dimen.choose_des_text_size);
		margin = getResources().getDimension(R.dimen.padding_medium);
		placeArr = getResources().getStringArray(R.array.bus_start_array);
	}
	
	/**初始化主题*/
	private void initTheme(){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		int theme = sp.getInt("theme",1);
		switch(theme){
		case 1:
			resid = R.drawable.btn_bg_01;
			titleLayout.setBackgroundResource(R.drawable.bg_title_01);
			bottomLayout.setBackgroundResource(R.drawable.bg_bottom_01);
			mainLayout.setBackgroundResource(R.drawable.bg_01);
			break;
		case 2:
			resid = R.drawable.btn_bg_02;
			titleLayout.setBackgroundResource(R.drawable.bg_title_02);
			bottomLayout.setBackgroundResource(R.drawable.bg_bottom_02);
			mainLayout.setBackgroundResource(R.drawable.bg_02);
			break;
		case 3:
			resid = R.drawable.btn_bg_03;
			titleLayout.setBackgroundResource(R.drawable.bg_title_03);
			bottomLayout.setBackgroundResource(R.drawable.bg_bottom_03);
			mainLayout.setBackgroundResource(R.drawable.bg_03);
			break;
		}
		search.setBackgroundResource(resid);
		chooseDes.setBackgroundResource(resid);
		isWeekDay.setBackgroundResource(resid);
	}
	
	private void setListeners(){
		OnClickListener l = new Listener();
		chooseDes.setOnClickListener(l);
		search.setOnClickListener(l);
		isWeekDay.setOnClickListener(l);
		settingsView.setOnClickListener(l);
		themeView.setOnClickListener(l);
	}
	/**选择目的地的响应*/
	private void chooseDes(){
		if(sv.getVisibility() == View.GONE)	{
			/**解决依然显示sv的最笨方法*/
			sv.setBackgroundResource(R.drawable.bg_des);
			startDesInAnim();
			addLinearLayout();
			sv.setVisibility(View.VISIBLE);
		}else{
			startDesOutAnim();
			removeLayoutWithDelay();
		}
		
	}
	/**搜索的响应*/
	private void search(){
		startFadeInAnim();
		SchoolBusModel model = SchoolBusModel.getInstance();
    	List<BusInfo> busInfo = model.getBus(this,start,end,weekDay,scheduleType);
    	if(!busInfo.isEmpty()){
        	BusCellAdapter adapter = new BusCellAdapter(this,busInfo,listView);
        	listView.setAdapter(adapter);
        	noFoundLayout.setVisibility(View.GONE);
        	listView.setVisibility(View.VISIBLE);
    	}else{
    		listView.setVisibility(View.GONE);
    		noFoundLayout.setVisibility(View.VISIBLE);
    	}
    	Log.v(TAG, busInfo.toString());
	}
	
	/**结果显示的渐入动画*/
	private void startFadeInAnim(){
		Animation anima = new AlphaAnimation(0, 1);
		anima.setFillAfter(true);
		anima.setDuration(300);
		listLayout.startAnimation(anima);
	}
	
	/**开始选择目的地弹出的入动画*/
	private void startDesInAnim(){
		/*上面的选择栏的上移动画*/
		RelativeLayout.LayoutParams pars = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		pars.addRule(RelativeLayout.BELOW,R.id.select_from_layout_top);
    	wrapper.setLayoutParams(pars);
		float fromXDelta = topLayout.getLeft();
		float fromYDelta = topLayout.getTop();
//		float toXDelta = title.getLeft();
//		float toYDelta = title.getBottom();
		float toXDelta = wrapper.getLeft();
		float toYDelta = wrapper.getTop() - titleLayout.getHeight();
		Animation anim = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
		anim.setFillAfter(true);
		anim.setDuration(300);
		wrapper.startAnimation(anim);
		
		/*选择目的地的入动画*/
		Animation as = AnimationUtils.loadAnimation(this, R.anim.choose_des_anim_in);
		as.setFillAfter(true);
		as.setStartOffset(250);
		sv.startAnimation(as);
		
	}
	/**选择目的地面板的出动画，由用户选择了目的地之后调用*/
	private void startDesOutAnim(){
		Animation as = AnimationUtils.loadAnimation(this, R.anim.choose_des_anim_out);
		as.setFillAfter(true);
		sv.startAnimation(as);
	}
	private boolean checkInput(){
		if(chooseDes.getText().equals(getResources().getString(R.string.choose_des_tip))){
			Toast.makeText(this, "请先选择目的地", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	/**添加目的地的布局*/
	private void addLinearLayout(){
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.setMargins((int)margin, (int)margin,(int)margin,(int)margin);
		int count = 0;
		LinearLayout childLayout = null;
		OnClickListener listener = new TextClickedListener();
		/**保存最后一个tv在该行的位置*/
		int lastTag = 0;
		for(int i=0;i<placeArr.length;i++){
			lastTag = count % 3;
			if(placeArr[i].equals(rawStart))	continue;
			if(lastTag==0){
				LayoutParams childParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				childParams.setMargins((int)margin, (int)margin,(int)margin,(int)margin);
				childLayout = new LinearLayout(this);
				linearLayout.addView(childLayout, childParams);
			}
			TextView tv = new TextView(this);
			tv.setText(placeArr[i]);
			tv.setBackgroundResource(resid);
			tv.setTextColor(Color.WHITE);
			tv.setGravity(Gravity.CENTER);
			tv.setHeight((int)cellHeight);
			tv.setWidth((int)cellWidth);
			tv.setTextSize(fontSize);
			tv.setOnClickListener(listener);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
					(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins((int)margin, (int)margin,(int)margin,(int)margin);
			//设置权重控制三块大小相同
			lp.weight = 1f;
			tv.setLayoutParams(lp);
			childLayout.addView(tv);
			Log.d(TAG, i+" finished!");
			count++;
		}
		while(lastTag <2){
			//为了保持权重不变，添加其他空块，保持所有块大小一致
			TextView tv = new TextView(this);
			tv.setBackgroundColor(Color.parseColor("#00000000"));
			tv.setHeight((int)cellHeight);
			tv.setWidth((int)cellWidth);
			tv.setTextSize(fontSize);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
					(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins((int)margin, (int)margin,(int)margin,(int)margin);
			//设置权重控制三块大小相同
			lp.weight = 1f;
			tv.setLayoutParams(lp);
			childLayout.addView(tv);
			lastTag ++;
		}
		sv.addView(linearLayout);
	}
	
	private void removeLayoutWithDelay(){
		//延迟时间与动画时间一致
		handler.postDelayed(new Runnable() {
			public void run() {
				Message msg= Message.obtain();
				msg.what = 1;
				handler.handleMessage(msg);
			}
		}, 200);
	}

	
	/**监听搜索上方布局的监听器,以及设置与换肤的监听*/
	private  class Listener implements OnClickListener{
		public void onClick(View v) {
			if(v == chooseDes){
				/*清除其他布局*/
				noFoundLayout.setVisibility(View.GONE);
				listView.setVisibility(View.GONE);
				chooseDes();
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
			}else if(v == settingsView){
				Intent intent = new Intent();
				intent.setClass(SearchActivity.this, SettingsActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.settings_anim_in,R.anim.activity_anim_stay);
				SearchActivity.this.finish();
			}else if(v == themeView){
				Intent intent = new Intent();
				intent.setClass(SearchActivity.this, SettingsActivity.class);
				intent.putExtra("type", 1);
				startActivity(intent);
				SearchActivity.this.finish();
			}
		}
		
	}
	
	/**监听选择目的地布局的监听器*/
	private class TextClickedListener implements OnClickListener{
		/**点击之后更改目的地的文字*/
		public void onClick(View v) {
			String rawEnd = ((TextView)v).getText().toString();
			chooseDes.setText(rawEnd);
			end = rawEnd.replaceAll("\n", "");
			/*动画开始*/
			startDesOutAnim();
			/**取消布局*/
			removeLayoutWithDelay();
			
		}
	}
}
