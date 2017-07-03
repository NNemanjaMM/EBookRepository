package com.nemanjam.ebook.lucene.search;

import com.nemanjam.ebook.lucene.analyze.SerbianAnalyzer;

public class SerbianSearcher extends AnalyzedSearcher {
	
	public SerbianSearcher(){
		super(new SerbianAnalyzer());
	}
}
