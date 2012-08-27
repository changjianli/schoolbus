package com.sdu.online.schoolbus;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sdu.online.schoolbus.sql.DataBaseHelper;
import com.sdu.online.schoolbus.util.FileUtils;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends Activity {
	private String url,version;
	private int type;
	private  String storagepath;
	private ProgressBar pb;
	private TextView progress;
	private int fileSize,downloadsize;
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(!Thread.currentThread().isInterrupted()){
				switch(msg.what){
				case 0:
					pb.setMax(fileSize);
					break;
				case 1:
					pb.setProgress(downloadsize);
					int result=(downloadsize*100)/fileSize;
					progress.setText(result+"%");
					break;
				case 2:
					Toast.makeText(UpdateActivity.this, "更新完成！", Toast.LENGTH_SHORT).show();
					break;
				case -1:
					String error=msg.getData().getString("error");
					Toast.makeText(UpdateActivity.this, error,Toast.LENGTH_SHORT).show();
					break;
				}
			}
			super.handleMessage(msg);
		}
			
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobclickAgent.onError(this);
		setContentView(R.layout.update);
		findView();
		storagepath=Environment.getExternalStorageDirectory().toString()+File.separator+"schoolbus";
		Intent intent=getIntent();
		url=intent.getStringExtra("url");
		type = intent.getIntExtra("type", 0);
		version = intent.getStringExtra("version");
		if(type==2){	//apk
		 	new Thread(){		 		
		 		@Override
	 			public void run() {
					try {
						File file=downfile(url,storagepath);
						installApk(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
		 		}
		 	}.start();
			
		}
		if(type==1){	//db
			new Thread(){
				@Override
				public void run() {
					try {
						File fromFile = downfile(url,storagepath);
						File file=UpdateActivity.this.getDatabasePath(DataBaseHelper.DATABASE_FILE_NAME);
						FileUtils.copyfile(fromFile,file, true);
						//更改数据库版本号
						SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this);
						Editor editor = sp.edit();
						editor.putString("db_version", version);
						editor.commit();
//						Toast.makeText(UpdateActivity.this, "数据库更新完毕", Toast.LENGTH_SHORT);
						UpdateActivity.this.finish();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}.start();
		}
	}
	
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onResume(this);
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPause(this);
	}
	
	public void findView(){
		pb=(ProgressBar) findViewById(R.id.progressBar);
		progress=(TextView)findViewById(R.id.progress);
	}

	private  File downfile(String url,String storage)throws IOException{
		
    	String fileName=url.substring(url.lastIndexOf("/")+1);
    	URL myURL=new URL(url);
    	HttpURLConnection conn=(HttpURLConnection)myURL.openConnection();
    	conn.setConnectTimeout(6000);
    	conn.setReadTimeout(3000);
    	conn.connect();
    	InputStream is=conn.getInputStream();
    	fileSize=conn.getContentLength();
    	//if(fileSize<=0)throw new RuntimeException("无法获取文件大小");
    	File file=new File(storage,fileName);
    	FileOutputStream fos=new FileOutputStream(file);
    	BufferedInputStream bis=new BufferedInputStream(is);
    	byte buf[]=new byte[1024];
    	downloadsize=0;
    	sendMsg(0);
    	do{
    		int numread=bis.read(buf);
    		if(numread==-1){
    			break;
    		}
    		fos.write(buf,0,numread);
    		fos.flush();
    		downloadsize+=numread;
    		sendMsg(1);//更新进度条
    	}while(true);
    	sendMsg(2);
    	try{
    		conn.disconnect();
    		fos.close();
    		bis.close();
    		is.close();
    	}catch (Exception ex) {
    		ex.printStackTrace();
		}
    	return file;
    }
    public void sendMsg(int arg){
    	Message msg=new Message();
    	msg.what=arg;
    	handler.sendMessage(msg);
    }
    protected void installApk(File file){	//安装APK调用
    	Intent intent=new Intent();
    	intent.setAction(Intent.ACTION_VIEW);
    	intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
    	startActivity(intent);
    	UpdateActivity.this.finish();
    }


}
