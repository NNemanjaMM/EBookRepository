package com.nemanjam.ebook.lucene.search;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;


public class FuzzySearcher {
	protected float similarity;
	
	public FuzzySearcher(float similarity){
		if(similarity <= 0 || similarity > 1){
			throw new IllegalArgumentException("Slicnost mora biti broj iz opsega (0,1]");
		} else {
			this.similarity = similarity;
		}
	}

	public List<Document> search(Term term){
		if(term == null) return null;		
		int maxEdits = (int) Math.floor((1 - similarity)*term.field().length());		
		FuzzyQuery query = new FuzzyQuery(term, maxEdits);		
		return ResultRetriever.getResults(query);
	}

}
