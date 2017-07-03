package com.nemanjam.ebook.lucene.search;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.PhraseQuery;

public class PhraseSearcher {
	
	public PhraseQuery getQuery(String fieldName, String... values){
		
		if(values.length == 0) return null;
		PhraseQuery query = new PhraseQuery();
		for(String value : values){
			query.add(new Term(fieldName, value));
		}
		return query;
	}
	
}
