package com.sdu.online.schoolbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WarnDialog extends Activity {
	Button cancleButton,downloadButton;
	TextView tv;
	String url,version;
	//1为db,2为apk
	int type;
	private static final String TAG = WarnDialog.class.getSimpleName();
	private static final String STORAGE_PATH=Environment.getDownloadCacheDirectory().toString();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.warn);
		
		cancleButton = (Button) findViewById(R.id.warn_button_cancle);
		downloadButton = (Button)findViewById(R.id.warn_button_download);
		tv = (TextView)findViewById(R.id.warn_textView);
		Listener l = new Listener();
		cancleButton.setOnClickListener(l);
		downloadButton.setOnClickListener(l);
		init();
	}
	
	private void init(){
		Intent intent = getIntent();
		String text = intent.getStringExtra("text");
		url = intent.getStringExtra("url");
		type = intent.getIntExtra("type", 0);
//		text = text.replaceAll("<br/>", "\n");
		version = intent.getStringExtra("version");
		tv.setText(text);
		Log.v(TAG, text);
	}
	class Listener implements OnClickListener{
		public void onClick(View v) {
			if(v == cancleButton){
				WarnDialog.this.finish();
			}else if(v == downloadButton){			
				Intent intent=new Intent();
				intent.setClass(WarnDialog.this, UpdateActivity.class);
				intent.putExtra("url", url);
				intent.putExtra("type", type);
				intent.putExtra("version", version);
				startActivity(intent);
				WarnDialog.this.finish();
				
			}
		}
	}
}
