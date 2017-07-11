package com.nemanjam.ebook.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanjam.ebook.crud.IGeoBookCRUD;
import com.nemanjam.ebook.crud.IGeoLocationCRUD;
import com.nemanjam.ebook.exception.ExceptionIncompleteIndexDocument;
import com.nemanjam.ebook.lucene.GeoIndexer;
import com.nemanjam.ebook.lucene.PDFHandler;
import com.nemanjam.ebook.model.entity.GeoBook;
import com.nemanjam.ebook.model.entity.GeoLocation;

@Service
public class GeoBookService {

	@Autowired
	private IGeoBookCRUD geoBookCrud;

	@Autowired
	private IGeoLocationCRUD geoLocationCrud;

	@Autowired
	private StorageService storageService;
	
	private PDFHandler pdfHandler = new PDFHandler();
	
	private GeoIndexer indexer = new GeoIndexer();	
	
	
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
		String filePath = storageService.getFilePath(fileName);
		File file = new File(filePath);
		GeoBook book = null;
		
		try {
			book = pdfHandler.getGeoBookFromPDFFile(file);
		} catch (ExceptionIncompleteIndexDocument e) {
			e.printStackTrace();
		};
		return book;
	}

	public void addGeoBook(GeoBook book) {
		List<GeoLocation> loc = null;
		if (book.getLocations() != null && book.getLocations().size() > 0) {
			geoLocationCrud.save(book.getLocations());
			loc = book.getLocations();
		}
		geoBookCrud.save(book);
		if (book.getLocations() != null && book.getLocations().size() > 0) {
			book.setLocations(loc);
		}
		Document bookDocument = pdfHandler.getDocumentFromGeoBook(book);
		indexer.indexDocument(bookDocument);
	}

	public List<GeoBook> searchForBooks(GeoLocation location) {
		List<Document> resultDocuments = new ArrayList<Document>();
		List<GeoBook> resultBooks = new ArrayList<GeoBook>();
		try {
			resultDocuments = indexer.search(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resultDocuments != null) {
			for (Document document : resultDocuments) {
				resultBooks.add(pdfHandler.getGeoBookFromDocument(document));
			}
		}
		return resultBooks;
	}	
	
}
