package com.sdu.online.schoolbus;

import java.net.URI;
import java.net.URL;
import java.util.List;

import org.apache.http.protocol.HTTP;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v4.content.IntentCompat;
import android.util.Log;
import android.widget.Toast;

import com.sdu.online.schoolbus.model.UpdateManager;
import com.sdu.online.schoolbus.model.UpdateManager.APPUpdateInfo;
import com.sdu.online.schoolbus.model.UpdateManager.DBUpdateInfo;
import com.sdu.online.schoolbus.util.DialogUtils;

public class SettingsActivity extends PreferenceActivity {

	private CheckBoxPreference autoUpdate,autoUpdateWifi;
	private Preference appVersion,dbVersion,colorTheme;
	//等待对话框
	private AlertDialog dialog;
	
	private static final String TAG = SettingsActivity.class.getSimpleName();
	//根据下载DB或下载app不同返回信息刷新界面
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
				Intent intent = new Intent();
				intent.setClass(SettingsActivity.this, WarnDialog.class);
				DBUpdateInfo db = (DBUpdateInfo) msg.obj;
				intent.putExtra("text", db.toString());
				intent.putExtra("type", 1);
				intent.putExtra("url", db.url);
				intent.putExtra("version",db.version);
				startActivity(intent);
				break;
			case 2:
				dialog.dismiss();
				Intent it = new Intent();
				it.setClass(SettingsActivity.this, WarnDialog.class);
				APPUpdateInfo app = (APPUpdateInfo) msg.obj;
				it.putExtra("text", app.toString());
				it.putExtra("type", 2);
				it.putExtra("url", app.url);
				it.putExtra("version",app.version);
				startActivity(it);
				break;
			case -2:
				dialog.dismiss();
				DialogUtils.showNoUpdateDialog(SettingsActivity.this, "当前软件已为最新！");
			}
			super.handleMessage(msg);
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
		findKeys();
		listen();
	}
	
	



	@Override
	protected void onResume() {
		init();
		super.onResume();
	}


	private void findKeys(){
		autoUpdate = (CheckBoxPreference) findPreference("auto_update");
		autoUpdateWifi = (CheckBoxPreference) findPreference("update_only_wifi");
		appVersion = findPreference("update_app");
		dbVersion = findPreference("update_db");
		colorTheme = findPreference("color_theme");
	}
	
	private void init(){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		appVersion.setSummary("当前软件版本: "+sp.getString("app_version", null));
		dbVersion.setSummary("当前数据库版本: "+sp.getString("db_version", null));
		colorThemeChanaged();
	}
	
	private void listen(){
		if(autoUpdate.isChecked()){
			autoUpdateWifi.setEnabled(true);
		}else autoUpdateWifi.setEnabled(false);

	}
	
	private void colorThemeChanaged(){
		SharedPreferences sps = PreferenceManager.getDefaultSharedPreferences(this);
		String text = "当前主题: ";
		switch(sps.getInt("color_theme", R.color.main_color_blue)){
		case R.color.main_color_blue:
			text +="深邃蓝";
			break;
		case R.color.main_color_purple:
			text += "诱惑紫";
			break;
		case R.color.main_color_green:
			text += "自然绿";
			break;
		case R.color.main_color_red:
			text += "山大红";
			break;
		}
		colorTheme.setSummary(text);
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
		}else if(preference.getKey().equals("about_us")){
			Intent intent = new Intent();
			intent.setClass(this, About.class);
			startActivity(intent);
		}else if(preference.getKey().equals("contact_us")){
			emailUs();
		}else if(preference.getKey().equals("update_db")){
			if(checkNetWorkState())	showUpdateDialogDB();
		}else if(preference.getKey().equals("update_app")){
			if(checkNetWorkState())	showUpdateDialogApp();
		}else if(preference.getKey().equals("color_theme")){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			String[] items = {"深邃蓝","诱惑紫","自然绿","山大红"};
			SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
			int selected= 0;
			switch(sp.getInt("color_theme", R.color.main_color_blue)){
			case R.color.main_color_blue:
				break;
			case R.color.main_color_purple:
				selected = 1;
				break;
			case R.color.main_color_green:
				selected = 2;
				break;
			case R.color.main_color_red:
				selected = 3;
				break;
			}
			final Editor editor = sp.edit();
			builder.setSingleChoiceItems(items, selected, new AlertDialog.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					int value1 = 0,value2 = 0;
					switch(which){
					case 0:
						value1 = R.color.main_color_blue;
						value2 = R.color.main_color_blue_alpha;
						break;
					case 1:
						value1 = R.color.main_color_purple;
						value2 = R.color.main_color_purple_alpha;
						break;
					case 2:
						value1 = R.color.main_color_green;
						value2 = R.color.main_color_green_alpha;
						break;
					case 3:
						value1 = R.color.main_color_red;
						value2 = R.color.main_color_red_alpha;
						break;
					}
					editor.putInt("color_theme", value1);
					editor.putInt("color_theme_alpha", value2);
					editor.commit();
					dialog.dismiss();
					colorThemeChanaged();
				}
			}).show();
		}
		listen();
		return super.onPreferenceTreeClick(preferenceScreen, preference);
		
	}
	
	private boolean checkNetWorkState(){
		ConnectivityManager cm=(ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo ni=cm.getActiveNetworkInfo();
		if(ni==null||!ni.isConnected()){
			AlertDialog.Builder ab=new AlertDialog.Builder(this);
			ab.setMessage("当前未连接网络！请先检查网络状态.").setPositiveButton("确定",  new AlertDialog.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).show();
			return false;
		}
		return true;
	}
	
	
	private void showUpdateDialogApp(){
		dialog = DialogUtils.showProcessDialog(this);
		Thread t = new Thread(){
			public void run(){
				UpdateManager um = new UpdateManager();
				UpdateManager.APPUpdateInfo app = um.needUpdateApp(SettingsActivity.this);
				Log.d(TAG, "get app");
				if(app!= null){
					Message message = Message.obtain();
					message.what = 2;
					message.obj = app;
					handler.handleMessage(message);
				}else{
					Message message = Message.obtain();
					message.what = -2;
					handler.handleMessage(message);
				}
			}
		};
		t.start();
	}
	
	private void showUpdateDialogDB(){
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
		
	}
	
	private void emailUs(){
		Intent mEmailIntent =  new Intent(android.content.Intent.ACTION_SENDTO);
//	    mEmailIntent.setType("gmail----/gmail---");
	    mEmailIntent.setData(Uri.parse("mailto:"));
	    String receiver = "873915231@qq.com";
	    String sub = "校车查询android应用程序反馈"; 
	    mEmailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{receiver}); 
	    mEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, sub);
	    PackageManager pManager = getPackageManager();
	    List<ResolveInfo> activities = pManager.queryIntentActivities(mEmailIntent, 0);
//	    System.out.println(activities.toString());
	    
	    if(activities.isEmpty())
	    	Toast.makeText(this, "您尚未安装gmail或同类软件,无法发送邮件!", Toast.LENGTH_SHORT).show(); 
	    else startActivity(mEmailIntent);
	}

}
