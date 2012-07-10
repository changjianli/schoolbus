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
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
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
	private NetworkInfo ni;
	private State wifi;
	private int type;
	private boolean needNoUpdateWarn = false;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		init();
		if((ni!=null&&ni.isConnected()&&!autoUpdateWifi)||(ni!=null&&ni.isConnected()&&wifi==ni.getState())){
		Thread t =new Thread(){
			public void run(){
				Log.d(TAG, "download started...");
				UpdateManager um = new UpdateManager();
				UpdateManager.DBUpdateInfo db = um.needUpdateDB(UpdateService.this);
				if(db != null) notifyUser(db);
				UpdateManager.APPUpdateInfo app = um.needUpdateApp(UpdateService.this);
				if(app != null) notifyUser(app);
				//系统随时可以停止服务
				UpdateService.this.stopSelf();
			}
		};
		t.start();
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void init(){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		autoUpdate = sp.getBoolean("auto_update", true);
		autoUpdateWifi = sp.getBoolean("update_only_wifi", false);
		ConnectivityManager cm=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
		wifi=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		ni=cm.getActiveNetworkInfo();
	}
	
	
	private void notifyUser(UpdateManager.DBUpdateInfo db){
		NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);               
		Notification n = new Notification(R.drawable.ic_launcher, "校车查询数据库有更新", System.currentTimeMillis());             
		n.flags = Notification.FLAG_AUTO_CANCEL;
		Intent i = new Intent(this, UpdateActivity.class);
		i.putExtra("type", 1);
		i.putExtra("url", db.url);
		i.putExtra("version", db.version);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);           
		//PendingIntent
		PendingIntent contentIntent = PendingIntent.getActivity(this,R.string.app_name,i,PendingIntent.FLAG_UPDATE_CURRENT);
		n.setLatestEventInfo(this,"数据库可更新","点击以更新到最新版数据库,保持最新的校车信息",contentIntent);
		nm.notify(R.string.app_name, n);
	}
	
	private void notifyUser(UpdateManager.APPUpdateInfo app){
		NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);               
		Notification n = new Notification(R.drawable.ic_launcher, "校车查询软件有更新", System.currentTimeMillis());             
		n.flags = Notification.FLAG_AUTO_CANCEL;
		Intent i = new Intent(this, UpdateActivity.class);
		i.putExtra("type", 2);
		i.putExtra("url", app.url);
		i.putExtra("version", app.version);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);           
		//id+1是为了与上一条避免重复而覆盖
		PendingIntent contentIntent = PendingIntent.getActivity(this,R.string.app_name+1,i,PendingIntent.FLAG_UPDATE_CURRENT);
		n.setLatestEventInfo(this,"应用程序可更新","点击升级校车查询",contentIntent);
		nm.notify(R.string.app_name+1, n);
	}

}
