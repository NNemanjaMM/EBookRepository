package com.nemanjam.ebook.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nemanjam.ebook.model.Marker;
import com.nemanjam.ebook.model.entity.GeoBook;
import com.nemanjam.ebook.model.entity.GeoLocation;
import com.nemanjam.ebook.service.GeoBookService;
import com.nemanjam.ebook.service.StorageService;

@Controller
public class GeoController {
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private GeoBookService geoBookService;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String OpenAllGeoBook(ModelMap model) {
		addGeoBooksToModel(model);
		return "geoViewBooks";
	}
	
	@RequestMapping(value="/geobooksearch", method=RequestMethod.GET)
	public String OpenSearchGeoBook() {
		return "geoViewSearch";
	}
	
	@RequestMapping(value="/geobookadd", method=RequestMethod.GET)
	public String OpenAddGeoBook() {
		return "geoViewBookUpload";
	}
		

	@RequestMapping(value="/dogeobooksearch", method=RequestMethod.POST)
	public String DoSearchGeoBook(@RequestParam("latval") String latitude, @RequestParam("lonval") String longitude, ModelMap model) {

		double lat = Double.parseDouble(latitude);
		double lon = Double.parseDouble(longitude);			
		GeoLocation location = new GeoLocation(lat, lon);
		
		List<GeoBook> geoBooks = geoBookService.searchForBooks(location);
		
		addMarkersToModel(model, geoBooks);
		model.put("books", geoBooks);	
		return "geoViewBooks";
	}

	@RequestMapping(value="/dogeobookupload", method=RequestMethod.POST)
	public String DoUploadGeoBook(@RequestParam("file") MultipartFile file, ModelMap model) {
		/*
		String savedFileName = null;
		try {
			savedFileName = storageService.store(file);		
			if (savedFileName == null) {
				model.put("error", "File was not uploaded, try again. Check if file type is .pdf, and check longitude and latitude values!");
				return "geoViewBookUpload";
			}
		} catch (Exception e) {
			model.put("error", "File was not uploaded, try again. Check if file type is .pdf, and check longitude and latitude values!");
			return "geoViewBookUpload";
		}

		GeoBook geoBook = geoBookService.getBookInfo(savedFileName);
		model.put("book", geoBook);
		*/
		return "geoViewBookAdd";
	}

	@RequestMapping(value="/dogeobookadd", method=RequestMethod.POST)
	public String DoAddGeoBook(@RequestParam("title") String title, @RequestParam("author") String author, 
			@RequestParam("fileName") String file, @RequestParam("locations") String locationsJSON, 
			ModelMap model) {
		
		ObjectMapper mapper = new ObjectMapper();
		List<GeoLocation> list = new ArrayList<GeoLocation>();
		try {
			list = mapper.readValue(locationsJSON, TypeFactory.defaultInstance().constructCollectionType(List.class, GeoLocation.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GeoBook book = new GeoBook(title, author, file, list);		
		geoBookService.addGeoBook(book);
		
		addGeoBooksToModel(model);
		return "geoViewBooks";
	}
	

	@RequestMapping(value="/bookdownload", method=RequestMethod.GET)
	public ResponseEntity<Resource> BookDownload(@RequestParam("bookId") String bookId, ModelMap model) {
		
		int id = Integer.parseInt(bookId);
		GeoBook book = geoBookService.getGeoBookById(id);
		
		if (book == null) {	
			model.put("download_error", "Selected e-Book was not found");		
			return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
		}
		
		String downloadFileName = book.getTitle() + ".pdf";
		String fileName = book.getFilename();
		
		Resource res = storageService.loadFile(fileName);
	    InputStreamResource resource = null;
	    long contentLength = 0;
		try {
			resource = new InputStreamResource(res.getInputStream());
		    contentLength = res.getFile().length();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    return ResponseEntity.ok()
	            .contentLength(contentLength)
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .header("Content-disposition", "attachment; filename=" + downloadFileName)
	            .body(resource);
	}
	
	
	private void addGeoBooksToModel(ModelMap model) {
		List<GeoBook> books = geoBookService.getAllGeoBooks();
		Collections.sort(books, (GeoBook b1, GeoBook b2) -> b1.getTitle().compareTo(b2.getTitle()));
		addMarkersToModel(model, books);
		model.put("books", books);	
	}
	
	private void addMarkersToModel(ModelMap model, List<GeoBook> books) {
		List<Marker> markers = new ArrayList<Marker>();
		if (books != null) {
			for (GeoBook geoBook : books) {
				for (GeoLocation location : geoBook.getLocations()) {
					markers.add(new Marker(geoBook.getTitle(), location.getLatitude(), location.getLongitude()));
				}
			}
		}
		model.put("markers", markers);	
	}

}
