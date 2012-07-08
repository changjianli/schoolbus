package com.sdu.online.schoolbus;

import com.sdu.online.schoolbus.model.UpdateManager;
import com.sdu.online.schoolbus.util.DialogUtils;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

public class SettingsActivity extends PreferenceActivity {

	private CheckBoxPreference autoUpdate,autoUpdateWifi;
	private Preference appVersion,dbVersion;
	//等待对话框
	private AlertDialog dialog;
	
	private static final String TAG = SettingsActivity.class.getSimpleName();
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case -1:
				dialog.dismiss();
				DialogUtils.showNoUpdateDialog(SettingsActivity.this,"当前数据库版本已为最新！");
				break;
			case 1:
				dialog.dismiss();
				
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
		
		findKeys();
		init();
		listen();
	}
	
	private void findKeys(){
		autoUpdate = (CheckBoxPreference) findPreference("auto_update");
		autoUpdateWifi = (CheckBoxPreference) findPreference("update_only_wifi");
		appVersion = findPreference("update_app");
		dbVersion = findPreference("update_db");
	}
	
	private void init(){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		appVersion.setSummary("当前软件版本: "+sp.getString("app_version", null));
		dbVersion.setSummary("当前数据库版本: "+sp.getString("db_version", null));
	}
	
	private void listen(){
		if(autoUpdate.isChecked()){
			autoUpdateWifi.setEnabled(true);
		}else autoUpdateWifi.setEnabled(false);
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if(preference.getKey().equals("share")){
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "学生在线推出了一款移动校车查询助手啦！很棒，只有android智能机才有哦~~亲们快去下吧~~!");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
		}else if(preference.getKey().equals("update_db")){
			dialog = DialogUtils.showProcessDialog(this);
			Thread t = new Thread(){
				public void run(){
					UpdateManager um = new UpdateManager();
					UpdateManager.DBUpdateInfo db = um.needUpdateDB(SettingsActivity.this);
					Log.d(TAG, "get db");
					if(db!= null){
						Message message = Message.obtain();
						message.what = 1;
						message.obj = db;
						handler.handleMessage(message);
					}else{
						Message message = Message.obtain();
						message.what = -1;
						handler.handleMessage(message);
					}
				}
			};
			t.start();
			
		}else if(preference.getKey().equals("update_app")){
			
		}
		listen();
		return super.onPreferenceTreeClick(preferenceScreen, preference);
		
	}
	
	

}
