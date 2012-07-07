package com.sdu.online.schoolbus.sql;

import java.io.File;
import java.io.IOException;
import com.sdu.online.schoolbus.util.FileUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DataBaseHelper extends SQLiteOpenHelper{
	public static final String DATABASE_FILE_NAME = "bus.db";
	private static final int VERSION = 1;
	private Context context;
	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public DataBaseHelper(Context context){	
		super(context,DATABASE_FILE_NAME,null,VERSION);
		this.context=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			File file=context.getDatabasePath(DATABASE_FILE_NAME);
			FileUtils.copyfile(context.getAssets().open(DATABASE_FILE_NAME),file, true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
