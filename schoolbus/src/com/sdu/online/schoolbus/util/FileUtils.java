package com.sdu.online.schoolbus.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import android.os.Environment;
import android.util.Log;

public class FileUtils {
	
    private String SDCardRoot;
	
	public FileUtils(){
		SDCardRoot=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;
	}
	
	public File createFileInSDCard(String fileName,String dir)throws IOException{
		File file=new File(SDCardRoot+dir+File.separator+fileName);
		file.createNewFile();
		return file;
	}
	
	/**
	 * 创建文件夹方�?
	 * @param dir 文件夹的名字
	 * @return File
	 */
	public File creatSDDir(String dir)
	{
		File dirFile=new File(SDCardRoot+dir+File.separator);
		dirFile.mkdirs();
		return dirFile;
	}
	
	public boolean isFileExist(String fileName)
	{
		File file=new File(SDCardRoot+fileName);
		return file.exists();
	}
	
	public static void copyfile(InputStream inputstream, File toFile,Boolean rewrite ){
//		if (!fromFile.exists() || !fromFile.isFile() || !fromFile.canRead())return;
		if(!toFile.getParentFile().exists())  toFile.getParentFile().mkdirs();
		if(toFile.exists() && rewrite) toFile.delete();
		try{
			//java.io.FileInputStream fosfrom =(FileInputStream) inputstream;
			java.io.FileOutputStream fosto = new FileOutputStream(toFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = inputstream.read(bt)) > 0) {
				fosto.write(bt, 0, c); //将内容写到新文件当中
			}
			inputstream.close();
			fosto.close();
		} catch (Exception ex) {
			Log.e("readfile", ex.getMessage());
		}
	}
	
	
	
}
