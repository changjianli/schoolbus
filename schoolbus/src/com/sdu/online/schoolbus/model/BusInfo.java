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
	private Place to;
	private int busType;
	private String remark;
	private String busBetween;

	public String getBusBetween() {
		return busBetween;
	}
	public int getBusType() {
		return busType;
	}
	public String getEndTime() {
		return endTime;
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
	
	public void setBusBetween(String busBetween){
		this.busBetween=busBetween;
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
	public void setId(int id) {
		this.id = id;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Place getTo() {
		return to;
	}
	public void setTo(Place to) {
		this.to = to;
	}
	public Place getFrom() {
		return from;
	}
	
	public String getFullFrom(){
		return from.getFullName();
	}
	
	public String getFullTo(){
		return to.getFullName();
	}
	@Override
	public String toString() {
		return "BusInfo [id=" + id + ", from=" + from + ", startTime="
				+ startTime + ", endTime=" + endTime + ", to=" + to
				+ ", busType=" + busType + ", remark=" + remark
				+ ", busBetween=" + busBetween 
				+ "]";
	}
	
}
