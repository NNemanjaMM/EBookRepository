package com.nemanjam.ebook.lucene.search;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.PhraseQuery;

public class PhraseSearcher {
	public List<Document> search(String fieldName, String... values){
		if(values.length == 0) return null;
		PhraseQuery query = new PhraseQuery();
		for(String value : values){
			query.add(new Term(fieldName, value));
		}
		return ResultRetriever.getResults(query);
	}
}
