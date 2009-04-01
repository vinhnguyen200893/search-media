package lucene.search.media.indexer;

import lucene.search.media.parseframework.*;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexReader;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Date;
import lucene.search.media.analysis.GetAnalyzer;
import org.apache.lucene.store.RAMDirectory;

/**
 * A File Indexer capable of recursively indexing a directory tree.
 */
//import net.javacoding.jspider.tool.impl.*;
public class FileIndexer
{

  
  protected FileHandler fileHandler;

  public FileIndexer(Properties props) throws IOException {
    fileHandler = new ExtensionFileHandler(props);
  }
  
  public void index(IndexWriter writer, File file)
    throws FileHandlerException {
    if (file.canRead()) {
      if (file.isDirectory()) {
        String[] files = file.list();
        if (files != null) {
          for (int i = 0; i < files.length; i++) {
              try{
            index(writer, new File(file, files[i]));
              }
              catch(Exception e){
                    continue;
              }
          }
        }
      }
      else {
       // System.out.println("Indexing " + file);
        try {
          Document doc = fileHandler.getDocument(file);
          if (doc != null) {
              if(doc.getField("title")!=null)
                System.out.println(doc.getField("title").stringValue());
               //System.out.println(doc.getField("body").stringValue());
                System.out.println(doc.getField("url").stringValue());
              
              writer.addDocument(doc);
            
          }
          else {
            System.err.println("Cannot handle "
              + file.getAbsolutePath() + "; skipping");
          }
        }
        catch (IOException e) {
          System.err.println("Cannot index "
            + file.getAbsolutePath() + "; skipping ("
            + e.getMessage() + ")");

        }
        finally{

        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 3) {
      usage();
      System.exit(0);
    }

    Properties props = new Properties();
    props.load(new FileInputStream(args[0]));

    //set parameters
    SetupParameters Pa=new SetupParameters();
    Pa.setDir(new RAMDirectory(args[2]));
    Pa.setAnalyzer(GetAnalyzer.getAnalysis());
    Pa.setWriter(new IndexWriter(Pa.getDir(), Pa.getAnalyzer(), true));
    FileIndexer indexer = new FileIndexer(props);
    long start = new Date().getTime();
    indexer.index(Pa.getWriter(), new File(args[1]));
    Pa.getWriter().optimize();
    Pa.getWriter().close();
    long end = new Date().getTime();

    System.out.println();
    IndexReader reader = Pa.getReader().open(Pa.getDir());
    System.out.println("Documents indexed: " + reader.numDocs());
    System.out.println("Total time: " + (end - start) + " ms");
    
    reader.close();
  }

  private static void usage() {
    System.err.println("USAGE: java "
      + FileIndexer.class.getName()
      + " /path/to/properties /path/to/file/or/directory"
      + " /path/to/index");
  }
}
