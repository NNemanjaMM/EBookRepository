package com.nemanjam.ebook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ebook")
public class EBookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	@Size(max = 80, min = 1, message = "Book's title must contain between 1 and 80 characters!")
	private String title;

	@Column(nullable = true)
	@Size(max = 120, min = 1, message = "If typed, book's author must contain between 1 and 120 characters!")
	private String author;

	@Column(nullable = true)
	@Size(max = 120, min = 1, message = "If typed, book's keywords must contain between 1 and 120 characters!")
	private String keywords;

	@Column(nullable = true)
	private Integer publicationYear;

	@Column(nullable = false)
	@Size(max = 200, min = 1)
	private String filename;

	@Column(nullable = true)
	@Size(max = 100, min = 1, message = "Book's MIME must contain between 1 and 100 characters!")
	private String MIME;

	
	@ManyToOne
	private CategoryEntity category;
	
	@ManyToOne	
	private LanguageEntity language;
	
	@ManyToOne
	private UserEntity user;

	
	public EBookEntity() {
		
	}
	
	public EBookEntity(Integer id, String title, String author,
			String keywords, Integer publicationYear, String filename,
			String mIME, CategoryEntity category, LanguageEntity language,
			UserEntity user) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.publicationYear = publicationYear;
		this.filename = filename;
		MIME = mIME;
		this.category = category;
		this.language = language;
		this.user = user;
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


	public UserEntity getUser() {
		return user;
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


	public void setUser(UserEntity user) {
		this.user = user;
	}
}
