package com.jason.lucene.sample;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class HelloLucence
{
	
	//@Test
	public void index() throws CorruptIndexException,
			LockObtainFailedException, IOException
	{
//		Directory directory = new RAMDirectory();
		Directory directory = FSDirectory.open(new File("E:/lucenc/index"));
		 
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36,
				new StandardAnalyzer(Version.LUCENE_36));
		IndexWriter indexWriter = null; 
		indexWriter = new IndexWriter(directory, config);

		Document document;
		File files = new File("E:/lucenc/file");
		for (File file : files.listFiles())
		{
			document = new Document();
			document.add(new Field("content", new FileReader(file)));
			document.add(new Field("name",file.getName(), Field.Store.YES,Field.Index.NOT_ANALYZED));
			document.add(new Field("path", file.getAbsolutePath(), Field.Store.YES, Index.NOT_ANALYZED));
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}
	
	@Test
	public void searcher() throws IOException, ParseException{
		Directory directory = FSDirectory.open(new File("E:/lucenc/index"));
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher=new IndexSearcher(indexReader);
		QueryParser parser=new QueryParser(Version.LUCENE_36, "content", new StandardAnalyzer(Version.LUCENE_36));
		Query query=parser.parse("java");
		TopDocs docs=indexSearcher.search(query, 10);
		ScoreDoc[] scoreDocs = docs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs)
		{
			Document document=indexSearcher.doc(scoreDoc.doc);
			System.out.println(document.get("name")+":"+document.get("path"));
		}
		
		indexReader.clone();
	}
}
