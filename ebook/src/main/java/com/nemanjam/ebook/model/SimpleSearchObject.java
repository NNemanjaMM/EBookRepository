package com.nemanjam.ebook.model;


public class SimpleSearchObject {

	private String criteria;
	private String textvalue;
	private String languagevalue;	
	private String query;
	
	
	public SimpleSearchObject() {
		
	}

	public SimpleSearchObject(String criteria, String textvalue,
			String languagevalue, String query) {
		super();
		this.criteria = criteria;
		this.textvalue = textvalue;
		this.languagevalue = languagevalue;
		this.query = query;
	}
	

	public String getCriteria() {
		return criteria;
	}

	public String getTextvalue() {
		return textvalue;
	}

	public String getLanguagevalue() {
		return languagevalue;
	}

	public String getQuery() {
		return query;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public void setTextvalue(String textvalue) {
		this.textvalue = textvalue;
	}

	public void setLanguagevalue(String languagevalue) {
		this.languagevalue = languagevalue;
	}

	public void setQuery(String query) {
		this.query = query;
	}	
	
	public String getValue() {
		if (criteria.equals("language")) {
			return languagevalue;
		} else {
			return textvalue;
		}
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
	
}
