package com.sdu.online.schoolbus;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.sdu.online.schoolbus.R;
import com.sdu.online.schoolbus.sql.DataBaseHelper;
import com.sdu.online.schoolbus.util.FileUtils;


public class SplashActivity extends Activity {
	private SharedPreferences sp;
	public static final String DATABASE_FILE_NAME = "bus.db";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		init();
		String storagepath=Environment.getExternalStorageDirectory().toString()+File.separator+"schoolbus";
		try {
			File file=this.getDatabasePath(DATABASE_FILE_NAME);
			if(!file.exists()){
				Log.v("ddddd", "file not exisist!");
			FileUtils.copyfile(this.getAssets().open(DATABASE_FILE_NAME),file, true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createDir(storagepath);
		   new Handler().postDelayed(new Runnable() {
	             public void run() {
	                 /* Create an Intent that will start the Main WordPress Activity. */
	            	Intent intent=new Intent(SplashActivity.this,SelectFromActivity.class);
	         		startActivity(intent);
	         		finish();
	             }
	         }, 2000); 
		
	}
	public void createDir(String path){
		File dir=new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
}   
	private void init(){
		//一些初始化配置
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		PackageInfo info = null;
		Editor editor= sp.edit();
		try {
			 info = getPackageManager().getPackageInfo(this.getPackageName(), 0);
		} catch (NameNotFoundException e) {e.printStackTrace();}
		if(sp.getString("app_version",null) == null){
			editor.putString("app_version", info.versionName);
			Log.v("------", "app_version"+info.versionName);
		}if(sp.getString("db_version", null) == null){
			editor.putString("db_version", "20120707");
		}
		//初始主题设置，默认主题1
		if(sp.getInt("theme", 0) == 0){
			editor.putInt("theme", 1);
		}
		editor.commit();
		if(sp.getBoolean("auto_update", true)){
			Intent intent = new Intent();
			intent.setClass(this, UpdateService.class);
			startService(intent);
		}
		
	}
}
