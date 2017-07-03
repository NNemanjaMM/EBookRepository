package com.nemanjam.ebook.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanjam.ebook.crud.IEBookCRUD;
import com.nemanjam.ebook.exception.ExceptionIncompleteIndexDocument;
import com.nemanjam.ebook.lucene.PDFHandler;
import com.nemanjam.ebook.lucene.UDDIndexer;
import com.nemanjam.ebook.lucene.analyze.SerbianAnalyzer;
import com.nemanjam.ebook.lucene.search.BooleanSearcher;
import com.nemanjam.ebook.lucene.search.FuzzySearcher;
import com.nemanjam.ebook.lucene.search.PhraseSearcher;
import com.nemanjam.ebook.lucene.search.ResultRetriever;
import com.nemanjam.ebook.lucene.search.SerbianSearcher;
import com.nemanjam.ebook.model.AdvancedSearchObject;
import com.nemanjam.ebook.model.EBookDisplay;
import com.nemanjam.ebook.model.RequiredHighlight;
import com.nemanjam.ebook.model.SearchObject;
import com.nemanjam.ebook.model.SimpleSearchObject;
import com.nemanjam.ebook.model.entity.EBookEntity;
import com.nemanjam.ebook.validation.ObjectHelper;

@Service
public class EBookService {

	@Autowired
	private IEBookCRUD ebookCrud;

	@Autowired
	private StorageService storageService;

	private static Analyzer analyzer = new SerbianAnalyzer();
	
	private PDFHandler pdfHandler = new PDFHandler();
	
	private UDDIndexer indexer = new UDDIndexer();
	
	public List<EBookEntity> getAllEBooks() {
		ArrayList<EBookEntity> ebooks = new ArrayList<EBookEntity>();		
		ebookCrud.findAll().forEach(ebooks::add);		
		return ebooks;
	}

	public boolean hasEBookForCategory(int id) {
		if (ebookCrud.findByCategoryId(id).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<EBookEntity> findEBooksForCategory(Integer id) {
		return ebookCrud.findByCategoryId(id);
	}

	public List<EBookEntity> findEBooksForFilename(String filename) {
		return ebookCrud.findByFilename(filename);
	}

	public EBookEntity findEBook(Integer id) {
		return ebookCrud.findOne(id);
	}

	public void addEBook(EBookEntity ebook) {
		ebookCrud.save(ebook);
        addBookIntoIndexes(ebook);
	}

	public void updateEBook(EBookEntity ebook) {
		upodateBookIndexes(ebook, findEBook(ebook.getId()));
		ebookCrud.save(ebook);
	}

	public void deleteEBook(Integer id) {
		deleteBookFromIndexes(findEBook(id));
		ebookCrud.delete(id);
	}
	
	public EBookEntity getFileInfo(String fileName) {
		String filePath = storageService.getFilePath(fileName);
		File file = new File(filePath);
		EBookEntity eBook = null;
		try {
			eBook = pdfHandler.getEBookFromPDF(file);
		} catch (ExceptionIncompleteIndexDocument e) {
			e.printStackTrace();
		}
		return eBook;
	}
	
	private void addBookIntoIndexes(EBookEntity ebook) {
		try {
			Document docToIndex = pdfHandler.getDocument(ebook);
			indexer.addDocument(docToIndex);
		} catch (ExceptionIncompleteIndexDocument e) {
			e.printStackTrace();
		}
	}
	
	public void upodateBookIndexes(EBookEntity book, EBookEntity oldBook) {
		try {
			Document docToUpdateOld = pdfHandler.getDocument(oldBook);
			Document docToUpdateNew = pdfHandler.getDocument(book);
			indexer.deleteDocument(docToUpdateOld);
			indexer.addDocument(docToUpdateNew);
		} catch (ExceptionIncompleteIndexDocument e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBookFromIndexes(EBookEntity book) {
		try {
			Document docToUpdate = pdfHandler.getDocument(book);
			indexer.deleteDocument(docToUpdate);
		} catch (ExceptionIncompleteIndexDocument e) {
			e.printStackTrace();
		}
	}
	
	
	public List<EBookDisplay> getSimpleSearchResults(SimpleSearchObject params) {
		return simpleSearch(params);
	}
	
	public List<EBookDisplay> getAdvancedSearchResults(AdvancedSearchObject params) {

		Occur occur = params.isAndOperation() ? Occur.MUST : Occur.SHOULD;		
		ArrayList<BooleanClause> clauses;
		BooleanSearcher booleanSearcher = new BooleanSearcher(); 
		
		clauses = getClauses(params, occur);
		Query query = booleanSearcher.getQuery(clauses);
		List<RequiredHighlight> requiredHighlights = getRequiredHighlights(params);
		return getDataWithHighlights(query, requiredHighlights);
	}

	private List<EBookDisplay> simpleSearch(SimpleSearchObject params) {
		
		Term t = new Term(params.getCriteria(), params.getValue());
		List<RequiredHighlight> requiredHighlights;
		
		if(params.isSimpleQuery()) {
			Query query = new SerbianSearcher().getQuery(t);
			requiredHighlights = getRequiredHighlights(params);
			return getDataWithHighlights(query, requiredHighlights);
		} 
		
		if(params.isPhraseQuery()) {
			Query query = new PhraseSearcher().getQuery(params.getCriteria(), params.getValue());
			requiredHighlights = getRequiredHighlights(params);
			return getDataWithHighlights(query, requiredHighlights);
		}
		
		if(params.isFuzzyQuery()) {
			Query query = new FuzzySearcher((float)0.6).getQuery(t);
			requiredHighlights = getRequiredHighlights(params);
			return getDataWithHighlights(query, requiredHighlights);
		}
		
		return null;
	}
	
	private ArrayList<BooleanClause> getClauses(AdvancedSearchObject searchParams, Occur occur) {
		
		ArrayList<BooleanClause> clauses = new ArrayList<BooleanClause>();
		
		if(ObjectHelper.hasElements(searchParams.getTitle())) 
		{
			clauses.add(new BooleanClause(getQuery(searchParams, "title", searchParams.getTitle()), occur));
		}
		
		if(ObjectHelper.hasElements(searchParams.getAuthor())) 
		{
			clauses.add(new BooleanClause(getQuery(searchParams, "author", searchParams.getAuthor()), occur));
		}
		
		if(ObjectHelper.hasElements(searchParams.getKeywords())) 
		{
			clauses.add(new BooleanClause(getQuery(searchParams, "keyword", searchParams.getKeywords()), occur));
		}
			
		if(ObjectHelper.hasElements(searchParams.getContent())) 
		{
			clauses.add(new BooleanClause(getQuery(searchParams, "content", searchParams.getContent()), occur));
		}
		
		if(ObjectHelper.hasElements(searchParams.getLanguage())) 
		{
			clauses.add(new BooleanClause(getQuery(searchParams, "language", searchParams.getLanguage()), occur));
		}
		
		return clauses;
	}

	private Query getQuery(AdvancedSearchObject searchParams, String field, String val) {
		
		Term term = new Term(field, val);
		Query query = new TermQuery(term);
		
		if(searchParams.isFuzzyQuery()) {
			query = new FuzzyQuery(term);
		}
		
		if(searchParams.isPhraseQuery()) {
			PhraseQuery pq = new PhraseQuery();
			pq.add(term);
			query = pq;
		}
		
		return query;
	}
	
	private ArrayList<EBookDisplay> getBooksFromDocuments(List<Document> docs) {
		ArrayList<EBookDisplay> books = new ArrayList<EBookDisplay>();
		EBookDisplay book;
		
		if (ObjectHelper.hasElements(docs)) {
			for (Document doc : docs) {
				
				book = getBookFromDocuments(doc);
				
				if(ObjectHelper.hasElements(book)){
					books.add(book);
				}
			}
		}
		return books;
	}
	
	private EBookDisplay getBookFromDocuments(Document doc) {
		EBookDisplay bookDisplay;
		EBookEntity bookEntity;
				
		String idStr = doc.get("id");
		int id = Integer.parseInt(idStr);
		bookEntity = findEBook(id);

		bookDisplay = new EBookDisplay(bookEntity, "");

		return bookDisplay;
	}
	
	
	private List<RequiredHighlight> getRequiredHighlights(SearchObject params) {
		ArrayList<RequiredHighlight> requiredHighlight = new ArrayList<RequiredHighlight>();
		
		if (!params.isContentCriteria()) {
			return requiredHighlight;
		}
		
		String criteria = "content";
		String value = params.getContentValue();
		
		RequiredHighlight highlight = new RequiredHighlight(criteria, value, null);
		requiredHighlight.add(highlight);
		
		return requiredHighlight;
	}
	
	public List<EBookDisplay> getDataWithHighlights(Query query, List<RequiredHighlight> requiredHighlights) {
		if(query == null) {
			return null;
		}
		List<Document> docs = ResultRetriever.getResults(query);
		List<EBookDisplay> results = new ArrayList<EBookDisplay>();
		
		String temp;
		EBookDisplay data;
		Highlighter highlighter;
		DirectoryReader reader;
		try {
			reader = DirectoryReader.open(new SimpleFSDirectory(new File(UDDIndexer.rootLocation.toString())));
			for(Document doc : docs){
				data = new EBookDisplay();
				data = getBookFromDocuments(doc);
				
				temp = "";
				if(requiredHighlights != null){
					for(RequiredHighlight rh : requiredHighlights){
						try{
							highlighter = new Highlighter(new QueryScorer(query, reader, rh.getFieldName()));
							String filePath = storageService.getFilePath(data.getFilename());
							File file = new File(filePath);
							String value = pdfHandler.getDocument(pdfHandler.getEBookFromPDF(file)).get(rh.getFieldName());
							String[] tempHL = highlighter.getBestFragments(analyzer, rh.getFieldName(), value, 5);
							if(tempHL != null) {
								for (String hl : tempHL) {
									temp += hl.trim() + " ... ";
								}
							}
						}catch(Exception e){
							
						}
					}
				}
				data.setSummary(temp);
				results.add(data);
			}
			reader.close();
			return results;
		} catch (IOException e) {
		}
		throw new IllegalArgumentException("U prosledjenom direktorijumu ne postoje indeksi ili je direktorijum zakljucan");
	}
	
	
	
}
