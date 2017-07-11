package com.nemanjam.ebook.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "geoBook")
public class GeoBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String title;

	@Column
	private String author;

	@Column
	private String filename;

	@Column
	private String places;

	@OneToMany
	private List<GeoLocation> locations;

	public GeoBook() {
		super();
	}

	public GeoBook(String title, String author, String filename, 
			List<GeoLocation> locations, String places) {
		super();
		this.title = title;
		this.author = author;
		this.filename = filename;
		this.locations = locations;
		this.places = places;
	}

	public GeoBook(Integer id, String title, String author, String filename,
			List<GeoLocation> locations, String places) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.filename = filename;
		this.locations = locations;
		this.places = places;
	}

	public Integer getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getFilename() {
		return filename;
	}

	public List<GeoLocation> getLocations() {
		return locations;
	}

	public String getPlaces() {
		return places;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setLocations(List<GeoLocation> locations) {
		this.locations = locations;
	}

	public void setPlaces(String places) {
		this.places = places;
	}
	
}
