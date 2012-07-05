package com.sdu.online.schoolbus.model;
import java.util.ArrayList;

public class BusInfo {
	
	public static final int BUS_TYPE_EVERYDAY=0;
	public static final int BUS_TYPE_WEEKDAY=1;
	public static final int BUS_TYPE_NOWEEKDAY=2;
	
	
	private int id;
	private Place from;
	private String startTime;
	private String endTime;
	private String fromDesc;
	private String toDesc;
	private int busType;
	private String remark;
	private ArrayList<String> busBetween;
	private ArrayList<String> betweenDesc;
	public ArrayList<String> getBetweenDesc() {
		return betweenDesc;
	}
	public ArrayList<String> getBusBetween() {
		return busBetween;
	}
	public int getBusType() {
		return busType;
	}
	public String getEndTime() {
		return endTime;
	}
	public Place getFrom() {
		return from;
	}
	public String getFromDesc() {
		return fromDesc;
	}
	public int getId() {
		return id;
	}
	public String getRemark() {
		return remark;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getToDesc() {
		return toDesc;
	}
	public void setBetweenDesc(ArrayList<String> betweenDesc) {
		this.betweenDesc = betweenDesc;
	}
	public void setBusBetween(ArrayList<String> busBetween) {
		this.busBetween = busBetween;
	}
	public void setBusType(int busType) {
		this.busType = busType;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setFrom(Place from) {
		this.from = from;
	}
	public void setFromDesc(String fromDesc) {
		this.fromDesc = fromDesc;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setToDesc(String toDesc) {
		this.toDesc = toDesc;
	}
	
	
}
