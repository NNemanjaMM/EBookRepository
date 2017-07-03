package com.nemanjam.ebook.lucene.analyze;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

/**
 * Analyser for Lucene, it is latin - cyrilic insensitive
 * 
 * @author chenejac@uns.ac.rs
 * 
 */
public class SerbianAnalyzer extends Analyzer {

	private static final Version matchVersion = Version.LUCENE_4_9;
	
	private static final String[] SERBIAN_STOP_WORDS = {
		"biti", "ne", 
		"jesam", "sam", "jesi", "si", "je", "jesmo", "smo", "jeste", "ste", "jesu", "su",
		"nijesam", "nisam", "nijesi", "nisi", "nije", "nijesmo", "nismo", "nijeste", "niste", "nijesu", "nisu",
		"budem", "budeš", "bude", "budemo", "budete", "budu",
		"budes",
		"bih",  "bi", "bismo", "biste", "biše", 
		"bise",
		"bio", "bili", "budimo", "budite", "bila", "bilo", "bile", 
		"ću", "ćeš", "će", "ćemo", "ćete", 
		"neću", "nećeš", "neće", "nećemo", "nećete", 
		"cu", "ces", "ce", "cemo", "cete",
		"necu", "neces", "nece", "necemo", "necete",
		"mogu", "možeš", "može", "možemo", "možete",
		"mozes", "moze", "mozemo", "mozete",
		"li", "da"};

	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
		Tokenizer source = new StandardTokenizer(matchVersion, reader);
		
		//rezultat provuci kroz filtere redom
		//filter za mala slova
		TokenStream result = new LCFilter(source);
		//cirilica u latinicu filter
		result = new CyrilicToLatinFilter(result);
		//filter stop reci
		result = new StopFilter(matchVersion, result, StopFilter.makeStopSet(matchVersion, SERBIAN_STOP_WORDS));
		//filter snowball filter koji ce da upotrebi srpski stemmer
		result = new SnowballFilter(result, new SimpleSerbianStemmer());
		//filter koji ce da ukloni akcente - ascii folding
		result = new ASCIIFoldingFilter(result);
		
		// redosled filtera je veoma bitan !!! zasto?
		return new TokenStreamComponents(source, result);
	}

}
