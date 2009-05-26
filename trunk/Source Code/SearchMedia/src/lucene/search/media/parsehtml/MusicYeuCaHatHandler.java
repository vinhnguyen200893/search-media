/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.parsehtml;

import org.apache.lucene.document.Document;
import lucene.search.media.indexer.*;
import org.cyberneko.html.parsers.DOMFragmentParser;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.w3c.dom.DocumentFragment;
import org.apache.html.dom.HTMLDocumentImpl;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import lucene.search.media.objects.MediaObject;
import lucene.search.media.parseframework.*;

/**
 *
 * @author Administrator
 */
public class MusicYeuCaHatHandler extends HtmlHandler implements DocumentHandler {
private DOMFragmentParser parser = new DOMFragmentParser();

  public Document getDocument(InputStream is)
    throws DocumentHandlerException {

    DocumentFragment node =
      new HTMLDocumentImpl().createDocumentFragment();
    try {
      parser.parse(new InputSource(is), node);
    }
    catch (IOException e) {
      throw new DocumentHandlerException(
        "Cannot parse HTML document: ", e);
    }
    catch (SAXException e) {
      throw new DocumentHandlerException(
        "Cannot parse HTML document: ", e);
    }
    org.apache.lucene.document.Document doc =
      new org.apache.lucene.document.Document();


       StringBuffer sb = new StringBuffer();
   // HtmlHandler html=new HtmlHandler();
    getText(sb, node, "title",1);
    String title = sb.toString();
   if (title != null){

     MediaObject obj=new MediaObject();
    //analysis the title
     String[]Str=AnalysisTitle(title);

    for(int i=0;i<Str.length;i++)
    {
        if(i==0)//first is name songen
          obj.setSongen(Str[i]);
        else if(i==1)//first is name song with UTF-8 encode
         obj.setSingeren(Str[i]);
        else if(i==2)//first is name song with UTF-8 encode
         obj.setSongvn(Str[i]);
        else if(i==3)//first is name song with UTF-8 encode
         obj.setSingervn(Str[i]);
    }
        //only index object obtain link media if this page has title(name of media)
        //get source link(real link on website)
        sb=new StringBuffer();
        getText(sb, node, "httrack",1);
        if(sb!=null)
            obj.setLinksource(sb.toString());



//        //get singer
//        sb=new StringBuffer();
//        getText(sb, node, "b", 4);
//        if(sb!=null)
//        {
//            obj.setSingervn(sb.toString());
//            obj.setSingeren(unicodeToAscii(sb.toString()));
//        }
//        //get album
//        sb=new StringBuffer();
//        getText(sb, node, "b", 5);
//        if(sb!=null)
//        {
//            obj.setAlbumvn(sb.toString());
//            obj.setAlbumen(unicodeToAscii(sb.toString()));
//        }
       Operators Op=new Operators();
       doc=Op.addDocumentObject(obj);

    }    
    return doc;

  }
   
 

  public  void  index(File file)
    throws FileHandlerException,DocumentHandlerException {
    if (file.canRead()) {
      if (file.isDirectory()) {
        String[] files = file.list();
        if (files != null) {
          for (int i = 0; i < files.length; i++) {
              try{
            index(new File(file, files[i]));
              }
              catch(Exception e){
                    continue;

              }
          }
        }
      }
      else {
       // System.out.println("Indexing " + file);
          if(file.getName().endsWith(".html")){
        try {
          Document doc = getDocument(new FileInputStream(file));
          if (doc != null) {
              //if(doc.getField("title")!=null)
                System.out.println(file.getAbsolutePath()+":");
                System.out.println(doc.getField("songvn").stringValue());
                System.out.println(doc.getField("songen").stringValue());
                System.out.println(doc.getField("singervn").stringValue());
                System.out.println(doc.getField("singeren").stringValue());
                System.out.println(doc.getField("linkobject").stringValue());
                System.out.println(doc.getField("linksource").stringValue());
                System.out.println(doc.getField("linkmedia").stringValue());
                System.out.println(doc.getField("albumen").stringValue());
                System.out.println(doc.getField("albumvn").stringValue());
                //writer.addDocument(doc);

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
   public static void main(String args[]) throws Exception {
    MusicYeuCaHatHandler handler = new MusicYeuCaHatHandler();
    handler.index(new File(args[0]));
//    org.apache.lucene.document.Document doc = handler.getDocument(
//      new FileInputStream(new File(args[0])));
//   // while(doc.fields().hasMoreElements()){
//    //System.out.println(doc.get("url"));
//    if(doc!=null){
//        System.out.println(doc.getField("songvn").stringValue());
//        System.out.println(doc.getField("songen").stringValue());
//        System.out.println(doc.getField("singervn").stringValue());
//        System.out.println(doc.getField("singeren").stringValue());
//        System.out.println(doc.getField("linkobject").stringValue());
//        System.out.println(doc.getField("linksource").stringValue());
//        System.out.println(doc.getField("linkmedia").stringValue());
//        System.out.println(doc.getField("albumen").stringValue());
//        System.out.println(doc.getField("albumvn").stringValue());
//
//    //doc.fields().nextElement();
//  //}
//    }

    }
}
