package com.nemanjam.ebook.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.spatial.SpatialStrategy;
import org.apache.lucene.spatial.prefix.RecursivePrefixTreeStrategy;
import org.apache.lucene.spatial.prefix.tree.GeohashPrefixTree;
import org.apache.lucene.spatial.query.SpatialArgs;
import org.apache.lucene.spatial.query.SpatialOperation;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import com.nemanjam.ebook.lucene.analyze.SerbianAnalyzer;
import com.nemanjam.ebook.model.entity.GeoLocation;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Point;

public class GeoIndexer {

	private SpatialContext ctx = SpatialContext.GEO;
	public static SpatialStrategy strategy = new RecursivePrefixTreeStrategy(new GeohashPrefixTree(SpatialContext.GEO, 11), "location");
	private Directory directory;	

	private static final Version matchVersion = Version.LUCENE_4_9;
	public static final Path rootLocation = Paths.get("storage/indexes");

	private IndexWriterConfig iwConfig = new IndexWriterConfig(matchVersion, new SerbianAnalyzer());
	private IndexWriter indexWriter;

	
	public GeoIndexer() {
		this(true);
	}
	
	public GeoIndexer(boolean restart) {
	    
	    try {
			this.directory = new SimpleFSDirectory(new File(rootLocation.toString()));
	    } catch (IOException e) {
			e.printStackTrace();
		}

		try {
			this.indexWriter = new IndexWriter(directory, iwConfig);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void indexDocument(Document document) {
	    	 		
	    try {
			indexWriter.addDocument(document);
			indexWriter.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Document> search(GeoLocation location) throws Exception {
		List<Document> results = new ArrayList<Document>();
	    IndexReader indexReader = DirectoryReader.open(directory);
	    IndexSearcher indexSearcher = new IndexSearcher(indexReader);	    

	    /*
	    Sort idSort = new Sort(new SortField("title", SortField.Type.STRING));
	    SpatialArgs args = new SpatialArgs(SpatialOperation.Intersects, ctx.makeCircle(location.getLongitude(), location.getLatitude(), DistanceUtils.dist2Degrees(50, DistanceUtils.EARTH_MEAN_RADIUS_KM)));
	    Filter filter = strategy.makeFilter(args);
	    TopDocs docs = indexSearcher.search(new MatchAllDocsQuery(), filter, 10, idSort);	      
	    */
	    
		Point pt = ctx.makePoint(location.getLongitude(), location.getLatitude());
		ValueSource valueSource = strategy.makeDistanceValueSource(pt, DistanceUtils.EARTH_MEAN_RADIUS_KM);//the distance (in km)
		Sort distSort = new Sort(valueSource.getSortField(false)).rewrite(indexSearcher);//false=asc dist
		
	    SpatialArgs args = new SpatialArgs(SpatialOperation.Intersects, ctx.makeCircle(location.getLongitude(), location.getLatitude(), DistanceUtils.dist2Degrees(50, DistanceUtils.EARTH_MEAN_RADIUS_KM)));
	    Filter filter = strategy.makeFilter(args);
		TopDocs docs = indexSearcher.search(new MatchAllDocsQuery(), filter, 10, distSort);
		
		for (int i = 0; i < docs.scoreDocs.length; i++) {
			results.add(indexSearcher.doc(docs.scoreDocs[i].doc));
		}

	    indexReader.close();
	    return results;
	  }

}
