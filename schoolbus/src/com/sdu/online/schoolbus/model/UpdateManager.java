package com.sdu.online.schoolbus.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import com.sdu.online.schoolbus.util.DownloadUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

public class UpdateManager {

	public static final String TAG = UpdateManager.class.getSimpleName(); 
	private String appUrlStr = "http://sheling.co.de/update_app.xml",
			dbUrlStr = "http://sheling.co.de/update_db_upgrade.xml";
	private static String dbFileName = "db.xml",appFileName = "app.xml";
	private Context context;
	/**方法为阻塞的。应用线程调用*/
	public DBUpdateInfo needUpdateDB(Context context){
		this.context = context;
		DBUpdateInfo db = null;
		try {
			downloadXML(1);
			db = parseDB();
			//若版本相同，无需更新
			if(db != null) if(checkVersion(db)) db = null;
		}catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return db;
	}
	
	public APPUpdateInfo needUpdateApp(Context context){
		this.context = context;
		APPUpdateInfo app = null;
		try {
			downloadXML(2);
			app = parseApp();
			//若版本相同，无需更新
			if(app != null) if(checkVersion(app)) app = null;
		}catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return app;
	}
	
	private void downloadXML(int type) throws FileNotFoundException, SocketTimeoutException{
		
		FileOutputStream ops = null;
		switch(type){
		case 1:
			ops = context.openFileOutput(dbFileName,0);
//			ops = new FileOutputStream(new File("/sdcard/t.xml"));
			DownloadUtils.download(dbUrlStr, ops);
			break;
		case 2:
			ops = context.openFileOutput(appFileName,0);
			DownloadUtils.download(appUrlStr, ops);
			break;
		}
		Log.d(TAG, "download finished");
	}
	
	private DBUpdateInfo parseDB() throws XmlPullParserException, IOException{
        DBUpdateInfo db = null;
		try {
			FileInputStream ips = context.openFileInput(dbFileName);
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();  
            XmlPullParser parser = xmlPullParserFactory.newPullParser();  
            parser.setInput(ips, "utf-8");  
            int eventType = parser.getEventType();  
            while(XmlPullParser.END_DOCUMENT!=eventType){  
                String nodeName = parser.getName();  
                switch (eventType) {  
                case XmlPullParser.START_TAG:  
                    if(nodeName.equals("db")){  
                    	 db= new DBUpdateInfo();
                    	 Log.d(TAG, "db");
                    }else 
                    	if(nodeName.equals("version")){  
                        db.version = parser.nextText(); 
                   	 Log.d(TAG, "update"); 
                    }else if (nodeName.equals("time")){
                    	db.time = parser.nextText();
                    }else if(nodeName.equals("md5")){  
                        db.md5 = parser.nextText();
                   	 Log.d(TAG, "md5");  
                    }else if(nodeName.equals("size")){
                    	db.size = Long.parseLong(parser.nextText());
                   	 Log.d(TAG, "size");
                    }
                    else if(nodeName.equals("url")){
                    	db.url = parser.nextText();
                   	 Log.d(TAG, "url");
                    }
                    break;
                default:  
                    break;  
                }
                eventType = parser.next();
            }  
//            Log.d(TAG, db.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return db;
	}
	
	private APPUpdateInfo parseApp() throws XmlPullParserException, IOException{
        APPUpdateInfo app= null;
		try {
			FileInputStream ips = context.openFileInput(appFileName);
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();  
            XmlPullParser parser = xmlPullParserFactory.newPullParser();  
            parser.setInput(ips,"utf-8");
            int eventType = parser.getEventType();  
            while(XmlPullParser.END_DOCUMENT!=eventType){  
                String nodeName = parser.getName();  
                switch (eventType) {  
                case XmlPullParser.START_TAG:  
                    if(nodeName.equals("app")){  
                    	 app= new APPUpdateInfo();
                    	 Log.d(TAG, "app");
                    }else 
                    	if(nodeName.equals("version")){  
                        app.version = parser.nextText(); 
                   	 Log.d(TAG, "update"); 
                    }else if (nodeName.equals("time")){
                    	app.time = parser.nextText();
                    }else if(nodeName.equals("md5")){  
                        app.md5 = parser.nextText();
                   	 Log.d(TAG, "md5");  
                    }else if(nodeName.equals("size")){
                    	app.size = Long.parseLong(parser.nextText());
                   	 Log.d(TAG, "size");
                    }
                    else if(nodeName.equals("url")){
                    	app.url = parser.nextText();
                   	 Log.d(TAG, "url");
                    }else if(nodeName.equals("note")){
                    	app.note = parser.nextText();
                    }
                    break;
                default:  
                    break;  
                }
                eventType = parser.next();
            }  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return app;
	}
	
	/**
	 * @return true 如果当前版本与最新版一致*/
	private boolean checkVersion(DBUpdateInfo db){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		String pastVersion = sp.getString("db_version", null);
		int past = Integer.parseInt(pastVersion);
		if(Integer.parseInt(db.version) <= past){
			return true;
		}
		return false;
	}

	/**
	 * @return true 如果当前版本与最新版一致*/
	private boolean checkVersion(APPUpdateInfo app){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		String pastVersion = sp.getString("app_version", null);
		float past = Float.parseFloat(pastVersion);
		if(Float.parseFloat(app.version) <= past){
			return true;
		}
		return false;
	}
	
	public class DBUpdateInfo{
		public String version;
		public String md5;
		public long size;
		public String url;
		public String time;
		@Override
		public String toString() {
			return "版本号: "+version+"\n大小: "+size+"\n更新时间: "+time;
		}
		
	}
	
	public class APPUpdateInfo{
		public String version;
		public String md5;
		public long size;
		public String url;
		public String time;
		public String note;
		public String toString() {
			return "版本号: "+version+"\n大小: "+size+"\n更新时间: "+time+"\n新特性: "+note;
		}
	}
}
