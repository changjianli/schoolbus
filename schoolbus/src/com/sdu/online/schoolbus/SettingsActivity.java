package com.sdu.online.schoolbus;

import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.util.Log;
import android.widget.Toast;
import com.sdu.online.schoolbus.model.UpdateManager;
import com.sdu.online.schoolbus.model.UpdateManager.APPUpdateInfo;
import com.sdu.online.schoolbus.model.UpdateManager.DBUpdateInfo;
import com.sdu.online.schoolbus.util.DialogUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.UMFeedbackService;

public class SettingsActivity extends PreferenceActivity {

	
	private CheckBoxPreference autoUpdate,autoUpdateWifi;
	private Preference appVersion,dbVersion,colorTheme;
	//等待对话框
	private AlertDialog dialog;
	
	private static final String TAG = SettingsActivity.class.getSimpleName();
	//type=0,正常进入,type=1,选择皮肤
	private int type = 0;
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
		MobclickAgent.onError(this);
		getExtra();
		addPreferencesFromResource(R.xml.preference);
		findKeys();
		listen();
	}

	@Override
	protected void onResume() {
		init();
	    MobclickAgent.onResume(this);
		super.onResume();
	}
	
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPause(this);
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.activity_anim_stay, R.anim.settings_anim_out);
	}

	/**接受传过来的参数*/
	private void getExtra(){
		Intent intent = getIntent();
		type  = intent.getIntExtra("type", 0);
		switch(type){
		case 1:
			showSelectThemeDialog();
			break;
		default:
			break;
		}
	}

	private void findKeys(){
		autoUpdate = (CheckBoxPreference) findPreference("auto_update");
		autoUpdateWifi = (CheckBoxPreference) findPreference("update_only_wifi");
		appVersion = findPreference("update_app");
		dbVersion = findPreference("update_db");
		colorTheme = findPreference("theme");
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
		switch(sps.getInt("theme", 1)){
		case 1:
			text += "天空蓝";
			break;
		case 2:
			text += "公主粉";
			break;
		case 3:
			text += "质感灰";
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
			UMFeedbackService.openUmengFeedbackSDK(this);
		}else if(preference.getKey().equals("update_db")){
			if(checkNetWorkState())	showUpdateDialogDB();
		}else if(preference.getKey().equals("update_app")){
			if(checkNetWorkState())	showUpdateDialogApp();
		}else if(preference.getKey().equals("theme")){
			showSelectThemeDialog();
		}else if(preference.getKey().equals("user_guide")){
			Intent intent = new Intent(this,UserGuideActivity.class);
			startActivity(intent);
		}
		listen();
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
	
	/**显示选择主题对话框*/
	private void showSelectThemeDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		String[] items = {"天空蓝","公主粉","质感灰"};
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		int selected= sp.getInt("theme", 1) -1;
		final Editor editor = sp.edit();
		builder.setTitle("请选择主题");
		builder.setSingleChoiceItems(items, selected, new AlertDialog.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				editor.putInt("theme", which+1);
				editor.commit();
				dialog.dismiss();
				colorThemeChanaged();
				if(type==1) SettingsActivity.this.finish();
			}
		}).show();
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
	
	/**发送邮件反馈的方法
	 * @deprecated*/
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
