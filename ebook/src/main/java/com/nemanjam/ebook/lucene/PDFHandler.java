package com.nemanjam.ebook.lucene;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import com.nemanjam.ebook.exception.ExceptionIncompleteIndexDocument;
import com.nemanjam.ebook.model.entity.GeoBook;
import com.nemanjam.ebook.model.entity.GeoLocation;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.shape.Point;

public class PDFHandler {
	
	private SpatialContext ctx = SpatialContext.GEO;

		public Document getDocumentFromGeoBook(GeoBook book) {

	    Document doc = new Document();

		doc.add(new TextField("id", book.getId() + "", Store.YES));
		doc.add(new TextField("title", book.getTitle().trim(), Store.YES));
		doc.add(new TextField("author", book.getAuthor(), Store.YES));
		doc.add(new TextField("filename", book.getFilename().trim(), Store.YES));
		doc.add(new TextField("places", book.getPlaces().trim(), Store.YES));
		
		for (GeoLocation location : book.getLocations()) {
			Point point = ctx.makePoint(location.getLongitude(), location.getLatitude());
			for (IndexableField f : GeoIndexer.strategy.createIndexableFields(point)) {
				doc.add(f);
			}
			doc.add(new StoredField(GeoIndexer.strategy.getFieldName(), point.getX() + " " + point.getY()));
		}
		
		return doc;
	}	

	public GeoBook getGeoBookFromDocument(Document doc) {
		
		int id = Integer.parseInt(doc.get("id"));
		String title = doc.get("title");
		String author = doc.get("author");
		String fileName = doc.get("filename");
		String places = doc.get("places");

		List<GeoLocation> locations = new ArrayList<GeoLocation>();
		String locationString = doc.get(GeoIndexer.strategy.getFieldName());
		do {
			GeoLocation location = new GeoLocation(locationString);
			locations.add(location);
			doc.removeField(GeoIndexer.strategy.getFieldName());
			locationString = doc.get(GeoIndexer.strategy.getFieldName());
		} while(locationString != null);
		

		GeoBook geoBook = new GeoBook(id, title, author, fileName, locations, places);
		
		return geoBook;
	}
	
	public GeoBook getGeoBookFromPDFFile(File file) throws ExceptionIncompleteIndexDocument {
		GeoBook geoBook = new GeoBook();
		String fileName = file.getName();
		
		geoBook.setFilename(fileName);
		String error = "";
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			PDDocument pdf = parser.getPDDocument();
			PDDocumentInformation info = pdf.getDocumentInformation();
						
			String author = info.getAuthor();
			if(author != null && !author.trim().equals("")) {
				geoBook.setAuthor(author);
			} else {
				author = info.getCreator();
				if(author != null && !author.trim().equals("")) {
					geoBook.setAuthor(author);
				} else {
					geoBook.setAuthor("");
				}
			}
			
			String title = info.getTitle();
			if(title != null && !title.trim().equals("")) {
				geoBook.setTitle(title);
			} else {
				geoBook.setTitle("");
			}
						
			pdf.close();
		} catch (Exception e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
			error = "Document is incomplete. An exception occured";
		}
		
		if(!error.equals("")) {
			throw new ExceptionIncompleteIndexDocument(error.trim() + " -> " + file.getName());
		}
		
		return geoBook;
	}
}