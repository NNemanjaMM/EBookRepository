package com.nemanjam.ebook.lucene.search;

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

	public FuzzyQuery getQuery(Term term){
		if(term == null) { 
			return null;		
		}
		int maxEdits = (int) Math.floor((1 - similarity) * term.field().length());		
		maxEdits = maxEdits > 2 ? 2 : maxEdits;
		FuzzyQuery query = new FuzzyQuery(term, maxEdits);		
		return query;
	}

}
