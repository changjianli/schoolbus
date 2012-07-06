package com.sdu.online.schoolbus.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sdu.online.schoolbus.sql.DataBaseHelper;

public class SchoolBusModel {
	private static final int SUMMER_TIME=1;
	private static final int WINTER_TIME=2;
	private String Time=null;
	
	private static final String TAG=SchoolBusModel.class.getSimpleName();
	
	private static class SchoolBusModelHolder{
		public static SchoolBusModel model = new SchoolBusModel();
	}
	
	public static SchoolBusModel getInstance(){
		return SchoolBusModelHolder.model;
	}
	
	public List<BusInfo> getBus(Context context,String from,String to,int timeflag){
		List<BusInfo> buslist=new ArrayList<BusInfo>();
		DataBaseHelper dbhelper=new DataBaseHelper(context);
		SQLiteDatabase sqlDB =dbhelper.getReadableDatabase();
		Log.d(TAG, from+" "+to);
		if(timeflag==SUMMER_TIME){
			Time="summer";
		}
		else if(timeflag==WINTER_TIME){
			Time="winter";
		}
		Cursor cursor=sqlDB.rawQuery("select * from winter_time where bus_from=? and bus_to=?",new String[]{from,to});
		
		while(cursor.moveToNext()){
			BusInfo businfo=new BusInfo();
			businfo.setStartTime(cursor.getString(cursor.getColumnIndex("start_time")));
			businfo.setEndTime(cursor.getString(cursor.getColumnIndex("end_time")));
			businfo.setBusType(cursor.getInt(cursor.getColumnIndex("bus_type")));
			businfo.setFrom( new Place(from,cursor.getString(cursor.getColumnIndex("from_desc"))));
			businfo.setTo( new Place(from,cursor.getString(cursor.getColumnIndex("to_desc"))));
			buslist.add(businfo);
		}
		cursor.deactivate();
		sqlDB.close();
		return buslist;
	}
	
}
