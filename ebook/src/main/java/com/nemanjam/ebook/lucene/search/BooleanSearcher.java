package com.nemanjam.ebook.lucene.search;

import java.util.ArrayList;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;

public class BooleanSearcher {	
	
	public BooleanQuery getQuery(ArrayList<BooleanClause> clauses){
		
		BooleanQuery query = new BooleanQuery();
		for(BooleanClause clause : clauses){
			query.add(clause);
		}
		return query;
	}

}
