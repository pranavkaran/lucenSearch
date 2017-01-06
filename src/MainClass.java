import org.apache.lucene.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.Document;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.document.Document;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.IndexSearcher;
public class MainClass {

	public static void main(String[] args) {
		
		//FileSpliting objSP = new FileSpliting();
		//objSP.split();
		
        try {
        	//Indexer  indexer = new Indexer();
        	//System.out.println("rebuildIndexes");
			//indexer.rebuildIndexes();
			//System.out.println("rebuildIndexes done");
			
			// perform search on "Notre Dame museum"
	        // and retrieve the top 100 result
	        System.out.println("performSearch");
	        SearchEngine se = new SearchEngine();
	        //String strQuery = "aerodynamicheating is discussed in detail .  the loading on the";
	        //String strQuery = "lift";
	        Scanner in = new Scanner(System.in);
	        System.out.println("Enter yours search query: ");
			String strQuery = in.nextLine();
	        TopDocs topDocs;
	        ArrayList<String> arr = new ArrayList<String>();
			try {
				topDocs = se.performSearch(strQuery, 100);
				System.out.println("Results found: " + topDocs.totalHits/4);
		        ScoreDoc[] hits = topDocs.scoreDocs;
		        for (int i = 0; i < hits.length; i++) {
		            Document doc = se.getDocument(hits[i].doc);
		            if (!arr.contains(doc.get("id") + " (" + hits[i].score + ")")) {
		            	arr.add(doc.get("id") + " (" + hits[i].score + ")");
		            }
		            //System.out.println(doc.get("id") + " (" + hits[i].score + ")");
		        }
		        for (String s : arr) {
		        	System.out.println(s);
		        }
		        System.out.println("performSearch done");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
