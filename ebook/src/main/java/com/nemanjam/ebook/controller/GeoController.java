package com.nemanjam.ebook.controller;

import java.io.IOException;
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

import com.nemanjam.ebook.model.entity.GeoBook;
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
	public String OpenSearchGeoBook(ModelMap model) {

		return "geoViewSearch";
	}

	@RequestMapping(value="/geobookadd", method=RequestMethod.GET)
	public String OpenAddGeoBook(ModelMap model) {

		return "geoViewBookAdd";
	}

	@RequestMapping(value="/dogeobooksearch", method=RequestMethod.POST)
	public String DoSearchGeoBook(@RequestParam("type") String type, @RequestParam("place") String place,
			@RequestParam("latval") String latitude, @RequestParam("lonval") String longitude, ModelMap model) {

		if (type.equals("map")) {
			System.out.println("param mapa: " + latitude + " " + longitude);
		} else {
			System.out.println("param mesto: " + place);
		}
		
		return "geoViewSearch";
	}

	@RequestMapping(value="/dogeobookadd", method=RequestMethod.POST)
	public String DoAddGeoBook(@RequestParam("file") MultipartFile file, ModelMap model) {

		String savedFileName = null;
		try {
			savedFileName = storageService.store(file);		
			if (savedFileName == null) {
				model.put("error", "File was not uploaded, try again. Check if file type is .pdf, and check longitude and latitude values!");
				return "geoViewBookAdd";
			}
		} catch (Exception e) {
			model.put("error", "File was not uploaded, try again. Check if file type is .pdf, and check longitude and latitude values!");
			return "geoViewBookAdd";
		}

		addGeoBooksToModel(model);
		return "geoViewBooks";
	}
	
	private void addGeoBooksToModel(ModelMap model) {
		List<GeoBook> books = geoBookService.getAllGeoBooks();
		Collections.sort(books, (GeoBook b1, GeoBook b2) -> b1.getTitle().compareTo(b2.getTitle()));
		model.put("books", books);	
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

}
