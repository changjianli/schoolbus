package com.sdu.online.schoolbus.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sdu.online.schoolbus.sql.DataBaseHelper;

public class SchoolBusModel {
	private static final int SUMMER_TIME=0;
	private static final int WINTER_TIME=1;
	private String Time=null;
	public List<BusInfo> getBus(Context context,String from,String to,int timeflag){
		List<BusInfo> buslist=new ArrayList<BusInfo>();
		DataBaseHelper dbhelper=new DataBaseHelper(context);
		SQLiteDatabase sqlDB =dbhelper.getReadableDatabase();
		if(timeflag==SUMMER_TIME){
			Time="summer";
		}
		else if(timeflag==WINTER_TIME){
			Time="winter";
		}
		Cursor cursor=sqlDB.rawQuery("select * from ?_time where bus_from=? and bus_to=?",new String[]{Time,from,to});
		while(cursor.moveToNext()){
			BusInfo businfo=new BusInfo();
			businfo.setStartTime(cursor.getString(cursor.getColumnIndex("start_time")));
			businfo.setEndTime(cursor.getString(cursor.getColumnIndex("end_time")));
			businfo.setBusType(cursor.getInt(cursor.getColumnIndex("bus_type")));
			businfo.setFromDesc(cursor.getString(cursor.getColumnIndex("from_desc")));
			businfo.setToDesc(cursor.getString(cursor.getColumnIndex("to_desc")));
		}
		return buslist;
	}
	
}
