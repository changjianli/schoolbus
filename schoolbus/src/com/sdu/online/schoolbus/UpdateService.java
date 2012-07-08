package com.sdu.online.schoolbus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.sdu.online.schoolbus.model.UpdateManager;
import com.sdu.online.schoolbus.util.DialogUtils;
import com.sdu.online.schoolbus.util.DownloadUtils;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

public class UpdateService extends Service {

	public static final String TYPE_NAME="ext";
	public static final int TYPE_APP = 1,TYPE_DB=2,TYPE_ALL = 3;
	private static final String TAG = UpdateService.class.getSimpleName();
	
	private boolean autoUpdate,autoUpdateWifi;
	
	private int type;
	private AlertDialog dialog;
	private boolean needNoUpdateWarn = false;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 1:
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		init(intent);
		Thread t =new Thread(){
			public void run(){
				Log.d(TAG, "download started...");
				UpdateManager um = new UpdateManager();
				UpdateManager.DBUpdateInfo db = um.needUpdateDB(UpdateService.this);
				if(db != null){
					//update
				}
			}
		};
		t.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void finish(){
		this.stopSelf();
	}
	
	private void init(Intent intent){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		autoUpdate = sp.getBoolean("auto_update", true);
		autoUpdateWifi = sp.getBoolean("update_only_wifi", false);
		
		type = intent.getIntExtra(TYPE_NAME, 3);
		if(type != 3) needNoUpdateWarn = true; 
		
	}
	
	
	

}
