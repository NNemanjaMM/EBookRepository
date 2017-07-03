package com.nemanjam.ebook.model;

public class AdvancedSearchObject implements SearchObject {

	private String title;
	private String author;	
	private String keywords;
	private String language;
	private String content;
	private String operation;	
	private String query;
	
	
	public AdvancedSearchObject() {
		
	}
	
	public AdvancedSearchObject(String title, String author, String keywords,
			String language, String content, String operation, String query) {
		super();
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.language = language;
		this.content = content;
		this.operation = operation;
		this.query = query;
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

	public String getLanguage() {
		return language;
	}

	public String getContent() {
		return content;
	}

	public String getOperation() {
		return operation;
	}

	public String getQuery() {
		return query;
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

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	

	public boolean isSimpleQuery() {
		return query.equals("standard");
	} 
	
	public boolean isPhraseQuery() {
		return query.equals("phrase");
	}
	
	public boolean isFuzzyQuery() {
		return query.equals("fuzzy");	
	}
	
	public boolean isAndOperation() {
		return this.operation.equals("and");
	}


	@Override
	public boolean isContentCriteria() {
		return this.content != null && !this.content.equals("");
	}
	
	@Override
	public String getContentValue() {
		return this.content;
	}
	
}
