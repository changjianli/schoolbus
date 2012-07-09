package com.sdu.online.schoolbus;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

public class UpdateActivity extends Activity {
	private String url;
	private int type;
	private  String storagepath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		storagepath=this.getCacheDir().toString();
		Intent intent=getIntent();
		url=intent.getStringExtra("url");
		type = intent.getIntExtra("type", 0);
		if(type==2){
			downloadApk(url);
		}
		if(type==1){
			
		}
	}
    public  File getFileFromServer(String path,ProgressDialog pd)throws Exception{
    	if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
    		URL url=new URL(path);
    		String fileName=path.substring(path.lastIndexOf("/")+1);
    		HttpURLConnection conn=(HttpURLConnection)url.openConnection();
    		conn.setConnectTimeout(5000);
    		int filesize=conn.getContentLength();
    		pd.setMax(filesize);
    		InputStream is=conn.getInputStream();
    		File file=new File(storagepath,fileName);
    		FileOutputStream fos=new FileOutputStream(file);
    		BufferedInputStream bis=new BufferedInputStream(is);
    		int length;
    		int total=0;
    		byte[] buffer=new byte[1024];
    		while((length=bis.read(buffer))!=-1){
    			fos.write(buffer, 0, length);
    			fos.flush();
    			total+=length;
    			pd.setProgress(total);
    			
    		}
    		fos.close();
    		bis.close();
    		is.close();
    		return file;
    	}
    	
    	
		return null;
    	
    }
    protected void downloadApk(String urlpath) {
    	final String path=urlpath;
		final ProgressDialog pd;
		pd=new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("正在下载更新。。。");
		pd.show();
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					File file=getFileFromServer(path, pd);
					sleep(3000);
					installApk(file);
					pd.dismiss();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}.start();
	}
    protected void installApk(File file){
    	Intent intent=new Intent();
    	intent.setAction(Intent.ACTION_VIEW);
    	intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
    	startActivity(intent);
    	UpdateActivity.this.finish();
    }
}
