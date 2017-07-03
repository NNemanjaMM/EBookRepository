package com.nemanjam.ebook.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nemanjam.ebook.crud.IEBookCRUD;
import com.nemanjam.ebook.exception.ExceptionIncompleteIndexDocument;
import com.nemanjam.ebook.lucene.PDFHandler;
import com.nemanjam.ebook.lucene.UDDIndexer;
import com.nemanjam.ebook.lucene.search.BooleanSearcher;
import com.nemanjam.ebook.lucene.search.FuzzySearcher;
import com.nemanjam.ebook.lucene.search.PhraseSearcher;
import com.nemanjam.ebook.lucene.search.SerbianSearcher;
import com.nemanjam.ebook.model.AdvancedSearchObject;
import com.nemanjam.ebook.model.SimpleSearchObject;
import com.nemanjam.ebook.model.entity.EBookEntity;
import com.nemanjam.ebook.validation.ObjectHelper;

@Service
public class EBookService {

	@Autowired
	private IEBookCRUD ebookCrud;

	@Autowired
	private StorageService storageService;
	
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
	
	
	public List<EBookEntity> getSimpleSearchResults(SimpleSearchObject params) {
		ArrayList<EBookEntity> books;
		List<Document> docs;
		
		docs = simpleSearch(params);
		books = getBooksFromDocuments(docs);
		
		return books;
	}
	
	public List<EBookEntity> getAdvancedSearchResults(AdvancedSearchObject params) {

		Occur occur = params.isAndOperation() ? Occur.MUST : Occur.SHOULD;		
		ArrayList<BooleanClause> clauses;
		BooleanSearcher searcher = new BooleanSearcher(); 
		
		clauses = getClauses(params, occur);
		
		List<Document> docs = searcher.search(clauses);
		ArrayList<EBookEntity> books = getBooksFromDocuments(docs);
		
		return books;
	}

	private List<Document> simpleSearch(SimpleSearchObject params) {
		
		Term t = new Term(params.getCriteria(), params.getValue());
		
		if(params.isSimpleQuery()) {
			return new SerbianSearcher().search(t);
		} 
		
		if(params.isPhraseQuery()) {
			return new PhraseSearcher().search(params.getCriteria(), params.getValue());
		}
		
		if(params.isFuzzyQuery()) {
			return new FuzzySearcher((float)0.6).search(t);
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
	
	private ArrayList<EBookEntity> getBooksFromDocuments(List<Document> docs) {
		ArrayList<EBookEntity> books = new ArrayList<EBookEntity>();
		EBookEntity book;
		String idStr;
		int id;
		
		if (ObjectHelper.hasElements(docs)) {
			for (Document doc : docs) {
				idStr = doc.get("id");
				id = Integer.parseInt(idStr);
				book = findEBook(id);
				if(ObjectHelper.hasElements(book)){
					books.add(book);
				}
			}
		} else {
			books = null;
		}
		return books;
	}
	
}
