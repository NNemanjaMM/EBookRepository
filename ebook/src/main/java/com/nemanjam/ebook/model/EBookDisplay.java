package com.nemanjam.ebook.model;

import com.nemanjam.ebook.model.entity.CategoryEntity;
import com.nemanjam.ebook.model.entity.EBookEntity;
import com.nemanjam.ebook.model.entity.LanguageEntity;

public class EBookDisplay {

	private Integer id;
	private String title;
	private String author;
	private String keywords;
	private Integer publicationYear;
	private String filename;
	private String MIME;
	private CategoryEntity category;	
	private LanguageEntity language;	
	
	private String summary;

	public EBookDisplay(EBookEntity eBook, String summary) {
		this.id = eBook.getId();
		this.title = eBook.getTitle();
		this.author = eBook.getAuthor();
		this.keywords = eBook.getKeywords();
		this.publicationYear = eBook.getPublicationYear();
		this.filename = eBook.getFilename();
		this.MIME = eBook.getMIME();
		this.category = eBook.getCategory();	
		this.language = eBook.getLanguage();	
		
		this.summary = summary;
	}
	
	
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getKeywords() {
		return keywords;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public String getFilename() {
		return filename;
	}

	public String getMIME() {
		return MIME;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public LanguageEntity getLanguage() {
		return language;
	}

	public String getSummary() {
		return summary;
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

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setMIME(String mIME) {
		MIME = mIME;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public void setLanguage(LanguageEntity language) {
		this.language = language;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
