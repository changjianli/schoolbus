package com.sdu.online.schoolbus.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	private String appUrlStr = "http://users10.jabry.com/sheling2011/testapk/update_app.xml",
			dbUrlStr = "http://users10.jabry.com/sheling2011/testapk/update_db.xml";
	private static String dbFileName = "db.xml",appFileName = "app.xml";
	private Context context;
	/**方法为阻塞的。应用线程调用*/
	public DBUpdateInfo needUpdateDB(Context context){
		this.context = context;
		DBUpdateInfo db = null;
		try {
			downloadXML();
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
	
	private void downloadXML() throws FileNotFoundException, SocketTimeoutException{
		FileOutputStream ops = context.openFileOutput(dbFileName,0);
		DownloadUtils.download(dbUrlStr, ops);
		Log.d(TAG, "download finished");
	}
	
	private DBUpdateInfo parseDB() throws XmlPullParserException, IOException{
        DBUpdateInfo db = null;
		try {
			FileInputStream ips = context.openFileInput(dbFileName);
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();  
            XmlPullParser parser = xmlPullParserFactory.newPullParser();  
            parser.setInput(ips, "UTF-8");  
            int eventType = parser.getEventType();  
            while(XmlPullParser.END_DOCUMENT!=eventType){  
                String nodeName = parser.getName();  
                switch (eventType) {  
                case XmlPullParser.START_TAG:  
                    if(nodeName.equals("db")){  
                    	 db= new DBUpdateInfo();
                    	 Log.d(TAG, "db");
                    }else 
                    	if(nodeName.equals("update")){  
                        db.version = parser.nextText(); 
                   	 Log.d(TAG, "update"); 
                    }else if(nodeName.equals("md5")){  
                        db.md5 = parser.nextText();
                   	 Log.d(TAG, "md5");  
                    }else if(nodeName.equals("size")){
                    	db.size = Long.parseLong(parser.nextText());
                   	 Log.d(TAG, "size");
                    }else if(nodeName.equals("url")){
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
	
	private boolean checkVersion(DBUpdateInfo db){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		String pastVersion = sp.getString("db_version", "20120707");
		if(db.version.equalsIgnoreCase(pastVersion)){
			return true;
		}
		return false;
	}
	
	public class DBUpdateInfo{
		String version;
		String md5;
		long size;
		String url;
		@Override
		public String toString() {
			return "DBUpdateInfo [version=" + version + ", md5=" + md5
					+ ", size=" + size + ", url=" + url + "]";
		}
		
	}
	
	public class APPUpdateInfo{
		String version;
		String md5;
		long size;
		String url;
		String time;
		String note;
	}
}
