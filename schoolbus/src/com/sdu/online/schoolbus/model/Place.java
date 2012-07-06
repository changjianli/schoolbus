package com.sdu.online.schoolbus.model;

public class Place {
	private String placeName;
	private String placeDesc;
	
	public Place(String placeName, String placeDesc) {
		super();
		this.placeName = placeName;
		this.placeDesc = placeDesc;
	}
	public String getSimpleName() {
		return placeName;
	}
	public void setSimpleName(String placeName) {
		this.placeName = placeName;
	}
	public String getDesc() {
		return placeDesc;
	}
	public void setDesc(String placeDesc) {
		this.placeDesc = placeDesc;
	}
	
	public String getFullName(){
		return placeDesc==null ? placeName : placeName + placeDesc;
	}
	@Override
	public String toString() {
		return getFullName();
	}
	
	
}
