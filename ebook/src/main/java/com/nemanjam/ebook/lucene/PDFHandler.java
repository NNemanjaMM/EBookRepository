package com.nemanjam.ebook.lucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;

import com.nemanjam.ebook.entity.db.EBookEntity;
import com.nemanjam.ebook.exception.ExceptionIncompleteIndexDocument;
import com.nemanjam.ebook.service.StorageService;

public class PDFHandler {

	public Document getDocument(EBookEntity bookMetaData) throws ExceptionIncompleteIndexDocument {
		String filePath = StorageService.rootLocation.resolve(bookMetaData.getFilename()).toString();
		File file = new File(filePath);
		Document doc = new Document();
		String error;
		
		try {
			addDocMetaData(bookMetaData, doc);
			error = addDocContent(file, doc);					
		} catch (Exception e) {
			System.out.println("PDF doc " + bookMetaData.getTitle() + " error.");
			error = "Document is incomplete. An exception occured";
		}
		
		if(ObjectHelper.hasElements(error)){
			throw new ExceptionIncompleteIndexDocument(error.trim());
		}
		
		return doc;
	}

	private void addDocMetaData(EBookEntity bookMetaData, Document doc) {
		String author = bookMetaData.getAuthor();
		String keywords = bookMetaData.getKeywords();

		//add id
		doc.add(new TextField("id", bookMetaData.getId() + "", Store.YES));
		
		//add title
		doc.add(new TextField("title", bookMetaData.getTitle().trim(), Store.YES));
		
		//add file name
		doc.add(new TextField("name", bookMetaData.getFilename().trim(), Store.YES));
				
		//add author
		if(ObjectHelper.hasElements(author)){
			doc.add(new TextField("author", author.trim(), Store.YES));
		}
		
		//add language
		if(ObjectHelper.hasElements(bookMetaData.getLanguage()) && ObjectHelper.hasElements(bookMetaData.getLanguage().getName())){
			doc.add(new TextField("language", bookMetaData.getLanguage().getName().trim(), Store.YES));
		}

		//add keywords if they exist
		if(ObjectHelper.hasElements(keywords)) {
			String[] kws = keywords.trim().split(",");
			for(String kw : kws){
				if(ObjectHelper.hasElements(kw)){
					doc.add(new TextField("keywords", kw, Store.YES));
				}
			}
		}
	}

	private String addDocContent(File file, Document doc) {
		String content = getTextContentFromPDF(file);
		String error = "";
		
		if(ObjectHelper.hasElements(content)){
			doc.add(new TextField("content", content, Store.YES));
		} else {
			error += "Document without text\n";
		}
		
		return error;
	}
	
	public String getTextContentFromPDF(File file) {
		String text = "";
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			PDDocument pdf = parser.getPDDocument();			
			PDFTextStripper stripper = new PDFTextStripper("utf-8");
			text = stripper.getText(pdf);
			pdf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		return text.trim();
	}
	

	public EBookEntity getEBookFromPDF(File file) throws ExceptionIncompleteIndexDocument {
		EBookEntity eBook = new EBookEntity();
		String fileName = file.getName();
		
		eBook.setFilename(fileName);
		String error = "";
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			PDDocument pdf = parser.getPDDocument();
			PDDocumentInformation info = pdf.getDocumentInformation();
			
			String mime = Files.probeContentType(file.toPath());
			if(mime != null && !mime.equals("")) {
				eBook.setMIME(mime);
			} else {
				eBook.setMIME("");
			}
			
			String author = info.getAuthor();
			if(author != null && !author.trim().equals("")) {
				eBook.setAuthor(author);
			} else {
				author = info.getCreator();
				if(author != null && !author.trim().equals("")) {
					eBook.setAuthor(author);
				} else {
					eBook.setAuthor("");
				}
			}
			
			String title = info.getTitle();
			if(title != null && !title.trim().equals("")) {
				eBook.setTitle(title);
			} else {
				eBook.setTitle("");
			}
			
			Calendar creatinDate = info.getCreationDate();
			if(creatinDate != null) {
				int creationYear = creatinDate.get(Calendar.YEAR);
				eBook.setPublicationYear(creationYear);
			}
			
			String keywords = info.getKeywords();
			StringBuilder keywordsBuilder = new StringBuilder("");
			if(keywords != null) {				
				String[] kws = keywords.trim().split(" ");
				for(String kw : kws) {
					if(!kw.trim().equals("")) {
						keywordsBuilder.append(kw);
					}
				}
			}
			eBook.setKeywords(keywordsBuilder.toString());
			
			pdf.close();
		} catch (Exception e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
			error = "Document is incomplete. An exception occured";
		}
		
		if(!error.equals("")) {
			throw new ExceptionIncompleteIndexDocument(error.trim() + " -> " + file.getName());
		}
		
		return eBook;
	}
}
