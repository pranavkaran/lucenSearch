/*
 * Indexer.java
 *
 * Created on 6 March 2006, 13:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import org.apache.lucene.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.File;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field; 
import org.apache.lucene.document.StringField; 
import org.apache.lucene.document.TextField; 
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.LockFactory;
import org.apache.lucene.util.Version;



/**
 *
 * @author John
 */
public class Indexer {
	ArrayList<String> arrList = new ArrayList<String>();
	//PostingListClass objPLC; // = new PostingListClass();
	//ArrayList<PostingListClass> arrListPL = new ArrayList<PostingListClass>();
	
	FlieProcessing objFH = new FlieProcessing();
	ArrayList<String> arrStopWordList = objFH.CallStopWordList();
	int counter = 0;
	public void Filecall() {
		for(int i = 1; i <= 1400; i++) {
			try {
				FileReader inputFile = new FileReader(".//res//Documents//"+ i +".txt");
				BufferedReader bufferReader = new BufferedReader(inputFile);
				String line;
				String strDoc = "";
			    while ((line = bufferReader.readLine()) != null) {
			    	strDoc += line;
			    }
			    bufferReader.close();
			    System.out.println("Finished adding words Array "+i);
			    
			    //System.out.println("Indexing hotel: " + hotel);
		        IndexWriter writer = getIndexWriter(true);
		        Document doc = new Document();
		        doc.add(new StringField("id", ""+i+"", Field.Store.YES));
		        
		        //doc.add(new StringField("name", hotel.getName(), Field.Store.YES));
		        //doc.add(new StringField("city", hotel.getCity(), Field.Store.YES));
		        String fullSearchableText = strDoc;
		        
		        doc.add(new TextField("content", fullSearchableText, Field.Store.NO));
		        writer.addDocument(doc);
			}
			catch(Exception e) {
				System.out.println("Error while reading file line by line:" + e.getMessage());                      
			}
		}
	}
	
    /** Creates a new instance of Indexer */
    public Indexer() {
    }
 
    private IndexWriter indexWriter = null;
    
    public IndexWriter getIndexWriter(boolean create) throws IOException {
        if (indexWriter == null) {
        	try {
				//FSDirectory indexDir = FSDirectory.open(new File("./tmp/termdociterable"));
				Directory indexDir = FSDirectory.open(new java.io.File("./res/index-directory"));
				IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46, new StandardAnalyzer(Version.LUCENE_46));
				indexWriter = new IndexWriter(indexDir, config);
			} catch (IOException e) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO: handle exception
			}
        }
        return indexWriter;
    }    
   
    public void closeIndexWriter() throws IOException {
        if (indexWriter != null) {
            indexWriter.close();
        }
    }
    
    public void rebuildIndexes() throws IOException {
          getIndexWriter(true);
          Filecall();
          closeIndexWriter();
     }    
}
