package com.nemanjam.ebook.lucene.search;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

public class AnalyzedSearcher{
	protected static final Version v = Version.LUCENE_4_9;
	protected Analyzer analyzer;
	
	public AnalyzedSearcher(Analyzer analyzer){
		this.analyzer = analyzer;
	}
	
	public List<Document> search(Term term) {
		QueryParser qp = new QueryParser(v, term.field(), analyzer);
		Query query;
		try {
			query = qp.parse(term.text());
			return ResultRetriever.getResults(query);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Prosledjeni term nije u redu");
		}
	}
	
	public List<Document> search(Query query) {
		QueryParser qp = new QueryParser(v, "", analyzer);
		try {
			query = qp.parse(query.toString());
			return ResultRetriever.getResults(query);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Prosledjeni term nije u redu");
		}
	}

}
