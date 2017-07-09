package com.nemanjam.ebook.model;

public class Marker {

	private String title;
	private double lat;
	private double lon;
	
	
	public Marker() {
		super();
	}
	
	public Marker(String title, double lat, double lon) {
		super();
		this.title = title;
		this.lat = lat;
		this.lon = lon;
	}

	
	public String getTitle() {
		return title;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}	
	
}
