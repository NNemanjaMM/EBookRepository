package com.nemanjam.ebook.lucene.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import com.nemanjam.ebook.lucene.UDDIndexer;
import com.nemanjam.ebook.lucene.analyze.SerbianAnalyzer;

public class ResultRetriever {

	private static final Version matchVersion = Version.LUCENE_4_9;
	private static int maxHits = 10;
	private static Analyzer analyzer = new SerbianAnalyzer();
	private static QueryParser queryParser = new QueryParser(matchVersion, "", analyzer);
	
	public static void setAnalyzer(Analyzer analyzer){
		ResultRetriever.analyzer = analyzer;
		ResultRetriever.queryParser = new QueryParser(matchVersion, "", analyzer);
	}
	
	/**
	 * Pronalazi dokumente koji zavodoljavaju upit. Dokumenata ima najvise maxHits
	 * 
	 * @param query - upit po kojem ce biti trazeni dokumenti
	 * @return Sortirana lista dokumenata koji odgovaraju (analiziranom) upitu.<br> - praznu listu ukoliko ni jedan dokument ne odgovara upitu<br> - <b>null</b> ukoliko je prosledjeni upit null
	 * @throws IllegalArgumentException ako se u direktorijumu ne nalaze indeksi ili je direktorijum zakljucan
	 * @throws ParseException ako upit nije moguce parsirati
	 */
	public static List<Document> getResults(Query query){
		List<Document> docs = new ArrayList<Document>();
		
		if(query == null) return null;
		
		try {
			query = queryParser.parse(query.toString());
			Directory indexDir = new SimpleFSDirectory(new File(UDDIndexer.rootLocation.toString()));
			DirectoryReader reader = DirectoryReader.open(indexDir);
			IndexSearcher is = new IndexSearcher(reader);
			
			docs = new ArrayList<Document>();
			
			ScoreDoc[] scoreDocs = is.search(query, maxHits, Sort.INDEXORDER).scoreDocs;
			for(ScoreDoc sd : scoreDocs){
				docs.add(is.doc(sd.doc));
			}
			
		} catch (ParseException e) {
			throw new IllegalArgumentException("Upit nije moguce parsirati");
		} catch (IOException e) {
			throw new IllegalArgumentException("U prosledjenom direktorijumu ne postoje indeksi ili je direktorijum zakljucan");
		}
		
		return docs;
	}

}
