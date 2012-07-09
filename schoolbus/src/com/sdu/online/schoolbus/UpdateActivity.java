package com.sdu.online.schoolbus;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends Activity {
	private String url;
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
					Toast.makeText(UpdateActivity.this, "文件下载完成！", 3000).show();
					break;
				case -1:
					String error=msg.getData().getString("error");
					Toast.makeText(UpdateActivity.this, error,3000).show();
					break;
				}
			}
			super.handleMessage(msg);
		}
			
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);
		findView();
		storagepath=Environment.getExternalStorageDirectory().toString()+File.separator+"schoolbus";
		createDir(storagepath);
		Intent intent=getIntent();
		url=intent.getStringExtra("url");
		type = intent.getIntExtra("type", 0);
		if(type==2){
			//downloadApk(url);	
		 	new Thread(){		 		
		 		@Override
	 			public void run() {
		 						// TODO Auto-generated method stub				
			try {
				File file=downfile(url,storagepath);
				installApk(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 		}
		 	}.start();
			
		}
		if(type==1){
			//downloadDB(url,storagepath);
			new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						downfile(url,storagepath);
						UpdateActivity.this.finish();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				
			}.start();
		}
	}
	public void findView(){
		pb=(ProgressBar) findViewById(R.id.progressBar);
		progress=(TextView)findViewById(R.id.progress);
	}
	public void createDir(String path){
			File dir=new File(path);
			if(!dir.exists()){
				dir.mkdirs();
			}
	}   
	private  File downfile(String url,String storage)throws IOException{
		
    	String fileName=url.substring(url.lastIndexOf("/")+1);
    	URL myURL=new URL(url);
    	HttpURLConnection conn=(HttpURLConnection)myURL.openConnection();
    	conn.setConnectTimeout(5000);
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
			// TODO: handle exception
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
//    public  File getFileFromServer(String path,String storagepath,ProgressDialog pd)throws Exception{
//    	if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//    		URL url=new URL(path);
//    		String fileName=path.substring(path.lastIndexOf("/")+1);
//    		HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//    		conn.setConnectTimeout(5000);
//    		int filesize=conn.getContentLength();
//    		pd.setMax(filesize);
//    		InputStream is=conn.getInputStream();
//    		File file=new File(storagepath,fileName);
//    		FileOutputStream fos=new FileOutputStream(file);
//    		BufferedInputStream bis=new BufferedInputStream(is);
//    		int length;
//    		int total=0;
//    		byte[] buffer=new byte[1024];
//    		while((length=bis.read(buffer))!=-1){
//    			fos.write(buffer, 0, length);
//    			fos.flush();
//    			total+=length;
//    			pd.setProgress(total);
//    			
//    		}
//    		fos.close();
//    		bis.close();
//    		is.close();
//    		conn.disconnect();
//    		return file;
//    	}
//    	
//    	
//		return null;
//    	
//    }
//    protected void downloadDB(final String urlpath,final String storage){
//    	final ProgressDialog pd;
//    	pd=new ProgressDialog(this);
//    	pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//    	pd.setMessage("正在更新数据库!");
//    	pd.show();
//    	new Thread(){
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					getFileFromServer(urlpath, storage, pd);
//					
//					pd.setMessage("更新数据库完成!");
//					sleep(3000);
//					pd.dismiss();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//    		
//    	}.start();
//    }
//    protected void downloadApk(String urlpath) {
//    	final String path=urlpath;
//		final ProgressDialog pd;
//		pd=new ProgressDialog(this);
//		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//		pd.setMessage("正在下载更新。。。");
//		pd.show();
//		new Thread(){
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try{
//					File file=getFileFromServer(path,storagepath, pd);
//					sleep(3000);
//					installApk(file);
//					pd.dismiss();
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//			
//		}.start();
//	}

}
