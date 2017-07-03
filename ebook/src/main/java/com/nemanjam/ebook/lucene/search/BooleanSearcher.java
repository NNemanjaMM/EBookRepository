package com.nemanjam.ebook.lucene.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;

public class BooleanSearcher {	
	public List<Document> search(ArrayList<BooleanClause> clauses){
		BooleanQuery query = new BooleanQuery();
		for(BooleanClause clause : clauses){
			query.add(clause);
		}
		return ResultRetriever.getResults(query);
	}

}
