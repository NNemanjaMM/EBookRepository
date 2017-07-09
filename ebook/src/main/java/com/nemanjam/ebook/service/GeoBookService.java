package com.nemanjam.ebook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanjam.ebook.crud.IGeoBookCRUD;
import com.nemanjam.ebook.model.entity.GeoBook;
import com.nemanjam.ebook.model.entity.GeoLocation;

@Service
public class GeoBookService {

	@Autowired
	private IGeoBookCRUD geoBookCrud;

	@Autowired
	private StorageService storageService;
	
	public List<GeoBook> getAllGeoBooks() {
		ArrayList<GeoBook> geoBooks = new ArrayList<GeoBook>();		
		geoBookCrud.findAll().forEach(geoBooks::add);		
		return geoBooks;
	}
	
	public GeoBook getGeoBookById(int id) {
		return geoBookCrud.findOne(id);
	}
	
	public List<GeoBook> getGeoBooksForLocation(GeoLocation location) {
		ArrayList<GeoBook> geoBooks = new ArrayList<GeoBook>();	
		
		return geoBooks;
	}

	public GeoBook getBookInfo(String fileName) {
		
		return null;
	}

	public void addGeoBook(GeoBook book) {
		
	}

	public List<GeoBook> searchForBooks(GeoLocation location) {
		
		return null;
	}
	
	
}
