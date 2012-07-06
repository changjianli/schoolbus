package com.sdu.online.schoolbus;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class ChooseDesActivity extends Activity {

	private float cellWidth,cellHeight,fontSize,margin;
	private String start,placeArr[];
	private static final String TAG = ChooseDesActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		addViews();
	}
	
	private void init(){
		cellWidth = getResources().getDimension(R.dimen.cell_width);
		cellHeight = getResources().getDimension(R.dimen.cell_height);
		fontSize = getResources().getDimension(R.dimen.choose_des_text_size);
		margin = getResources().getDimension(R.dimen.padding_medium);
		
		Intent it = getIntent();
		start = it.getStringExtra("start");
		
		placeArr = getResources().getStringArray(R.array.bus_start_array);
	}
	
	private void addViews(){
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.setMargins((int)margin, (int)margin,(int)margin,(int)margin);
		int count = 0;
		LinearLayout childLayout = null;
		OnClickListener listener = new Listener();
		for(int i=0;i<placeArr.length;i++){
			if(placeArr[i].equals(start))	continue;
			if(count % 3==0){
				LayoutParams childParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				childParams.setMargins((int)margin, (int)margin,(int)margin,(int)margin);
				childLayout = new LinearLayout(this);
				linearLayout.addView(childLayout, childParams);
			}
			TextView tv = new TextView(this);
			android.view.ViewGroup.LayoutParams par = new android.view.ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			tv.setText(placeArr[i]);
			tv.setBackgroundResource(R.color.main_color_blue);
			tv.setTextColor(Color.WHITE);
			tv.setGravity(Gravity.CENTER);
			tv.setHeight((int)cellHeight);
			tv.setWidth((int)cellWidth);
			tv.setTextSize(fontSize);
			tv.setOnClickListener(listener);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
					(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins((int)margin, (int)margin,(int)margin,(int)margin);
			tv.setLayoutParams(lp);
			childLayout.addView(tv);
			Log.d(TAG, i+" finished!");
			count++;
		}
		ScrollView sv = new ScrollView(this);
		sv.addView(linearLayout);
		setContentView(sv);
	}

	private class Listener implements OnClickListener{
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("end", ((TextView)v).getText().toString());
			setResult(RESULT_OK, intent);
			finish();
		}
	}
}
