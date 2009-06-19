package lucene.search.media.indexer;

import lucene.search.media.parseframework.*;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexReader;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import lucene.search.media.system.Directories;
import java.util.Properties;
import java.util.Date;
import lucene.search.media.analysis.GetAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;



/**
 * A File Indexer capable of recursively indexing a directory tree.
 */
//import net.javacoding.jspider.tool.impl.*;
public class Indexer
{
  protected FileHandler fileHandler;
    /*
     * index specific directory-all file in one directory
     */
    private   long indexDirectory(String directory,String index, SetupParameters Pa) throws FileHandlerException, IOException {
            long sumDocs=0;
            Directory di = FSDirectory.getDirectory(new File(index), true);
            //RAMDirectory di = new RAMDirectory(new Directory());
            Pa.setDir(di);
            Pa.setWriter(new IndexWriter(Pa.getDir(), Pa.getAnalyzer(), true));

            //get name of directory contains website to index
            int begin=directory.lastIndexOf("\\");
            if(begin==-1) begin=directory.lastIndexOf("/");
            int end=directory.length();
            String dir_site=directory.substring(begin+1, end).toLowerCase();
            index(dir_site,Pa.getWriter(),new File(directory));
            
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
            System.out.println("\t-----FOLDER----- :"+dirs[i].toUpperCase());
            String dir_index=index +"/"+ dirs[i];
            if((index.endsWith("\\"))||(index.endsWith("/"))){
                 dir_index = index + dirs[i];
            }
            Directory di = FSDirectory.getDirectory(new File(dir_index), true);
            Pa.setDir(di);
            Pa.setWriter(new IndexWriter(Pa.getDir(), Pa.getAnalyzer(), true));

//             //get name of directory contains website to index
//            int begin=dirs[i].lastIndexOf("\\");
//            if(begin==-1) begin=dirs[i].lastIndexOf("/");
//            int end=dirs[i].length()-1;
//            String dir_site=dirs[i].substring(begin, end);
            this.index(dirs[i].toLowerCase(),Pa.getWriter(), new File(parent+"\\"+dirs[i]));
           
            Pa.getWriter().optimize();
            Pa.getWriter().close();
            IndexReader reader = Pa.getReader().open(Pa.getDir());
            sumDocs+=reader.numDocs();
            reader.close();
            
        }
        return sumDocs;
    }
   
  public Indexer(Properties props) throws IOException {
    fileHandler = new ExtensionFileHandler(props);
  }
    public  void  index(String dir,IndexWriter writer, File file)
    throws FileHandlerException {
    if (file.canRead()) {
      if (file.isDirectory()) {
        String[] files = file.list();
        if (files != null) {
          for (int i = 0; i < files.length; i++) {
              try{
            index(dir,writer, new File(file, files[i]));
              }
              catch(Exception e){
                    continue;
                  
              }
          }
        }
      }
      else {
        if(file.getName().endsWith(".html")||file.getName().endsWith(".htm")){
          // System.out.println("Indexing " + file);
        try {
          Document doc = fileHandler.getDocument(dir,file);
          if (doc != null) {
                System.out.println("FILE :");
               System.out.println(file.getAbsolutePath()+":");
               System.out.println("SONG");
                System.out.println(doc.getField("songvn").stringValue());
                System.out.println(doc.getField("songen").stringValue());
                System.out.println("SINGER");
                System.out.println(doc.getField("singervn").stringValue());
                System.out.println(doc.getField("singeren").stringValue());
                System.out.println("OBJECT");
                System.out.println(doc.getField("linkobject").stringValue());
                System.out.println("LINK SOURCE");
                System.out.println(doc.getField("linksource").stringValue());
                System.out.println("DATE  MODIFIED");
               System.out.println(doc.getField("date").stringValue());

                System.out.println("LINK MEDIA");
                System.out.println(doc.getField("linkmedia").stringValue());
                 System.out.println("SERVICE");
                System.out.println(doc.getField("service").stringValue());
                
                System.out.println("ALBUM");
                System.out.println(doc.getField("albumen").stringValue());
                System.out.println(doc.getField("albumvn").stringValue());

                System.out.println("LYRICS");
                System.out.println(doc.getField("lyric").stringValue());
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
  }

  public static void main(String[] args) throws Exception {
    if (args.length <3) {
      usage();
      System.exit(0);
    }

    try{
    Properties prop = new Properties();

    prop.load(new FileInputStream(System.getProperty("user.dir")+"/conf/sitehandler.properties"));

    //set parameters
    SetupParameters Pa=new SetupParameters();    
    Pa.setAnalyzer(GetAnalyzer.getAnalysis());    
    Indexer indexer = new Indexer(prop);

    long start = new Date().getTime();
    long numDocs=0;
    if(args.length==3 && args[2].equals("1"))//index 1 directory
    {
       numDocs= indexer.indexDirectory(args[0],args[1], Pa);
    }
    else if(args.length==3 && args[2].equals("0"))//index many directory at the time
    {
        Directories d=new Directories();
        String[] dirs = d.getListDirectories(new File(args[0]));
        numDocs = indexer.indexDirectories(args[0],dirs, args[1], Pa);
    }
   
    long end = new Date().getTime();
    System.out.println("Documents indexed: " + numDocs);
    System.out.println("Total time: " + (end - start) + " ms");    
    }
    catch(FileNotFoundException ex)
    {
        System.out.println("file 'sitehandler.properties' in config directory not found");
        System.out.println("please check again");}
  }

  /*
   * index many options
   * if agrs.length =3 && args[2]=0 : index many directories in a directory
   * if agrs.length =3 && args[2]=1 : index one directory
     */ 
  private static void usage() {
    System.err.println("USAGE: java "
      + Indexer.class.getName()
      + " 1.Index one directory(Video/Audio):/path/to/data /path/to/indexed/directory 1 "
      + " 2.Index many directories(Video/Audio):/path/to/data /path/to/indexed/directory 0 ");
  }
}
