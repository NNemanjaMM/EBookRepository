package com.nemanjam.ebook.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;

import com.nemanjam.ebook.lucene.analyze.SerbianAnalyzer;
import com.nemanjam.ebook.service.GeoBookService;

public final class UDDIndexer {
	
	@Autowired
	private GeoBookService geoBookService;
	
	private static final Version matchVersion = Version.LUCENE_4_9;
	public static final Path rootLocation = Paths.get("storage/indexes");
	private final Analyzer analyzer = new SerbianAnalyzer();
	
	private IndexWriter indexWriter;		
	private IndexWriterConfig iwc = new IndexWriterConfig(matchVersion, analyzer);
	
	private Directory indexDir;
	
	public UDDIndexer() {
		this(false);
	}
	
	public UDDIndexer(boolean restart){
		try{
			this.indexDir = new SimpleFSDirectory(new File(rootLocation.toString()));
			if(restart){
				iwc.setOpenMode(OpenMode.CREATE);
				this.indexWriter = new IndexWriter(indexDir, iwc);
				this.indexWriter.deleteAll(); //nije potrebno
				this.indexWriter.commit();
				this.indexWriter.close();
			}
		}catch(IOException ioe){
			throw new IllegalArgumentException("Path not correct");
		}
	}
	
	private void openIndexWriter() throws IOException{
		iwc = new IndexWriterConfig(matchVersion, analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		this.indexWriter = new IndexWriter(indexDir, iwc);
	}	
		
	public IndexWriter getIndexWriter(){
		return this.indexWriter;
	}
	
	public Directory getIndexDir(){
		return this.indexDir;
	}
	
	/**
	 * 
	 * @param doc - document which will be indexed
	 * @return - true if adding was successful
	 */
	public boolean addDocument(Document doc){ //indeksiranje tacno jednog dokumenta
		try {
			openIndexWriter();
			this.indexWriter.addDocument(doc);
			this.indexWriter.commit();
			this.indexWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * @param doc - the document which will be updated
	 * @param fields - array of updating fields
	 * @return true if update was successful, othewise false
	 */
	public boolean updateDocument(Document doc, IndexableField... fields){
		String id = doc.get("id");
		replaceFields(doc, fields);
		
		try{
			synchronized (this) {
				openIndexWriter();
				this.indexWriter.updateDocument(new Term("id", id), doc);
				this.indexWriter.forceMergeDeletes();
				this.indexWriter.deleteUnusedFiles();
				this.indexWriter.commit();
				this.indexWriter.close();
			}
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	public void replaceFields(Document doc, IndexableField... fields){
		for(IndexableField field : fields){
			doc.removeFields(field.name());
		}
		for(IndexableField field : fields){
			doc.add(field);
		}
	}
	
	/**
	 * @param id - ID of the document which will be updated
	 * @param fields - array of updating fields
	 * @return - true if update was successful, false otherwise
	 */
	public boolean updateDocument(int id, IndexableField... fields){
		try {
			DirectoryReader reader = DirectoryReader.open(indexDir);
			return updateDocument(reader.document(id), fields);
		} catch (IOException e) {
			return false;
		}
	}
		
	/**
	 * Delete a single document
	 * @param doc
	 * @return
	 */
	public boolean deleteDocument(Document doc){
		if(doc == null) return false;
		//obrisati tacno jedan dokument i to ovaj prosledjeni
		Term delTerm = new Term("id", doc.get("id"));
		try {
			synchronized (this) {
				openIndexWriter();
				this.indexWriter.deleteDocuments(delTerm);
				this.indexWriter.deleteUnusedFiles();
				this.indexWriter.forceMergeDeletes();
				this.indexWriter.commit();
				this.indexWriter.close();
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * Delete a single document
	 * @param id
	 * @return
	 */
	public boolean deleleDocument(int id){
		try {
			DirectoryReader reader = DirectoryReader.open(indexDir);
			return deleteDocument(reader.document(id));
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * Delete all documents which have a field named fieldName with value fieldValue
	 * @param fieldName - name of the field
	 * @param fieldValue - value of the field
	 * @return
	 */
	public boolean deleteDocuments(String fieldName, String fieldValue){
		return deleteDocuments(new Term(fieldName, fieldValue));
	}
	
	/**
	 * Delete all documents which contain any of the given terms
	 * @param fieldName - name of the field
	 * @param fieldValue - value of the field
	 * @return
	 */
	public boolean deleteDocuments(Term... delTerms){
		try {
			synchronized (this) {
				openIndexWriter();
				this.indexWriter.deleteDocuments(delTerms);
				this.indexWriter.forceMergeDeletes();
				this.indexWriter.deleteUnusedFiles();
				this.indexWriter.commit();
				this.indexWriter.close();
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	
	/**
	 * @return all indexed lucene documents
	 */
	public Document[] getAllDocuments(){
		//collect and return all documents
		try {
			DirectoryReader reader = DirectoryReader.open(indexDir);
			Document[] docs = new Document[reader.maxDoc()];
			for(int i = 0; i < reader.maxDoc(); i++){
				docs[i] = reader.document(i);
			}
			return docs;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
