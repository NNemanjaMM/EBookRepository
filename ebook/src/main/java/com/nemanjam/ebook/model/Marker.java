package com.nemanjam.ebook.model;

public class Marker {

	private String title;
	private String author;
	private String color;
	private double lat;
	private double lon;
	
	
	public Marker() {
		super();
	}
	
	public Marker(String title, String author, double lat, double lon, String color) {
		super();
		this.title = title;
		this.author = author;
		this.lat = lat;
		this.lon = lon;
		this.color = color;
	}

	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	public String getColor() {
		return color;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}	

	public void setColor(String color) {
		this.color = color;
	}
	
}
