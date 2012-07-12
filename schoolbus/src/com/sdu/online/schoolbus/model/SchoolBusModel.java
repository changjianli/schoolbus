package com.sdu.online.schoolbus.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;

import com.sdu.online.schoolbus.sql.DataBaseHelper;

public class SchoolBusModel {
	public static final int SUMMER_TIME=1;
	public static final int WINTER_TIME=2;
	public static final int WEEK_DAY = 1;
	public static final int NO_WEEK_DAY = 2;
	
	private String Time=null;
	
	private static final String TAG=SchoolBusModel.class.getSimpleName();
	
	private static class SchoolBusModelHolder{
		public static SchoolBusModel model = new SchoolBusModel();
	}
	
	public static SchoolBusModel getInstance(){
		return SchoolBusModelHolder.model;
	}
	
	public List<BusInfo> getBus(Context context,String from,String to,int busType,int schedule){
		List<BusInfo> buslist=new ArrayList<BusInfo>();
		DataBaseHelper dbhelper=new DataBaseHelper(context);
		SQLiteDatabase sqlDB =dbhelper.getReadableDatabase();
		Log.d(TAG, from+" "+to);
//		if(timeflag==SUMMER_TIME){
//			Time="summer";
//		}
//		else if(timeflag==WINTER_TIME){
//			Time="winter";
//		}
		String sql;
		if(schedule == SUMMER_TIME){
			sql = "select * from summer_time where bus_type in (0,?) and id in (select id from search_table where from_=? and to_=?)";
		}else sql = "select * from winter_time where bus_type in (0,?) and id in (select id from search_table where from_=? and to_=?)";
		Cursor cursor=sqlDB.rawQuery(sql,new String[]{busType+"",from,to});
		
		while(cursor.moveToNext()){
			BusInfo businfo=new BusInfo();
			businfo.setStartTime(cursor.getString(cursor.getColumnIndex("start_time")));
			businfo.setEndTime(cursor.getString(cursor.getColumnIndex("end_time")));
			businfo.setBusType(cursor.getInt(cursor.getColumnIndex("bus_type")));
			businfo.setFrom( new Place(cursor.getString(cursor.getColumnIndex("bus_from")),cursor.getString(cursor.getColumnIndex("from_desc"))));
			businfo.setTo( new Place(cursor.getString(cursor.getColumnIndex("bus_to")),cursor.getString(cursor.getColumnIndex("to_desc"))));
			businfo.setBusBetween(cursor.getString(cursor.getColumnIndex("bus_between")));
			buslist.add(businfo);
		}
		cursor.deactivate();
		sqlDB.close();
		Collections.sort(buslist);
		return buslist;
	}
	
	public int getSchedule(Context context){
		DataBaseHelper dbhelper=new DataBaseHelper(context);
		SQLiteDatabase sqlDB =dbhelper.getReadableDatabase();
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MMdd");
		String datetime=sdf.format(date);	
		Log.v(TAG, datetime);
		String sql = "select id from summer_winter where start_<= ? and end_ >= ?";
		Cursor cursor = sqlDB.rawQuery(sql, new String[]{datetime,datetime});
		int schedule = -1;
		if(cursor.moveToFirst()){
			schedule = cursor.getInt(cursor.getColumnIndex("id"));
			
		}
		cursor.deactivate();
		sqlDB.close();
		Log.v(TAG, "type:"+schedule);
		return schedule;
	}
}
