<<<<<<< .mine
package lucene.search.media.indexer;

import lucene.search.media.parseframework.*;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexReader;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import lucene.search.media.system.Directories;
import java.util.Properties;
import java.util.Date;
import lucene.search.media.analysis.GetAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;



/**
 * A File Indexer capable of recursively indexing a directory tree.
 */
//import net.javacoding.jspider.tool.impl.*;
public class FileIndexer
{
  protected FileHandler fileHandler;
    /*
     * index specific directory-all file in one directory
     */
    private   long indexDirectory(String directory,String index, SetupParameters Pa) throws FileHandlerException, IOException {
            long sumDocs=0;
            Directory di = FSDirectory.getDirectory(new File(index), true);
            Pa.setDir(di);
            Pa.setWriter(new IndexWriter(Pa.getDir(), Pa.getAnalyzer(), true));
            index(Pa.getWriter(),new File(directory));
            Pa.getWriter().optimize();
            Pa.getWriter().close();
            IndexReader reader = Pa.getReader().open(Pa.getDir());
            sumDocs+=reader.numDocs();
            reader.close();
        return sumDocs;
    }
    
    /*
     * index all child directories(only first level directories) in parent directory
     * and indexed data is stored in the same name source directory
     */
    private  long indexDirectories(String parent,String[]dirs, String index, SetupParameters Pa) throws FileHandlerException, IOException {
        long sumDocs=0;
        //index each directory in parent directory
        for (int i = 0; i < dirs.length; i++) {
            String dir_index=index +"/"+ dirs[i];
            if((index.endsWith("\\"))||(index.endsWith("/"))){
                 dir_index = index + dirs[i];
            }
            Directory di = FSDirectory.getDirectory(new File(dir_index), true);
            Pa.setDir(di);
            Pa.setWriter(new IndexWriter(Pa.getDir(), Pa.getAnalyzer(), true));
            this.index(Pa.getWriter(), new File(parent+"\\"+dirs[i]));
           
            Pa.getWriter().optimize();
            Pa.getWriter().close();
            IndexReader reader = Pa.getReader().open(Pa.getDir());
            sumDocs+=reader.numDocs();
            reader.close();
        }
        return sumDocs;
    }
  
 
  public FileIndexer(Properties props) throws IOException {
    fileHandler = new ExtensionFileHandler(props);
  }
    public  void  index(IndexWriter writer, File file)
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
              //if(doc.getField("title")!=null)
                System.out.println(doc.getField("title").stringValue());
                System.out.println(doc.getField("song").stringValue());
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
    Pa.setAnalyzer(GetAnalyzer.getAnalysis());    
    FileIndexer indexer = new FileIndexer(props);

    long start = new Date().getTime();
    long numDocs=0;
    if(args.length==4 && args[3].equals("1"))//index 1 directory
    {
       numDocs= indexer.indexDirectory(args[1],args[2], Pa);
    }
    else if(args.length==4 && args[3].equals("0"))//index many directory at the time
    {
        Directories d=new Directories();
        String[] dirs = d.getListDirectories(new File(args[1]));
       
        numDocs = indexer.indexDirectories(args[1],dirs, args[2], Pa);
    }
   
    long end = new Date().getTime();
    System.out.println("Documents indexed: " + numDocs);
    System.out.println("Total time: " + (end - start) + " ms");    
    
  }

  /*
   * index many options
   * if agrs.length =3 && args[3]=0 : index many directories in a directory
   * if agrs.length =3 && args[3]=1 : index one directory
   * if agrs[1] is a number: index directories args[4],args[5]...
   * if agrs[1] is a number && args[2] is 'same': index directories args[4],args[5]...
   * and the name of directory index is the same directory data
   */ 
  private static void usage() {
    System.err.println("USAGE: java "
      + FileIndexer.class.getName()
      + " /path/to/properties /path/to/file/or/directory"
      + " /path/to/index");
  }
}
=======
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
>>>>>>> .r13
