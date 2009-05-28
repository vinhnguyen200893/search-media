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
import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import lucene.search.media.objects.MediaObject;
import lucene.search.media.parseframework.*;
import org.w3c.dom.NodeList;

/**
 *
 * @author Administrator
 */
public class HoangHuyNetHandler extends HtmlHandler implements DocumentHandler {
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
   MediaObject obj=new MediaObject();

   //init arraylist to store tags and value
   ArrayList tags=new ArrayList();
   for(int i=0;i<3;i++) tags.add("b");
   tags.add("a");
   ArrayList tagsvalue=new ArrayList();
   for(int i=1;i<=3;i++) tagsvalue.add(i);
   tagsvalue.add(9);//<a> tag contains direct link


   //get nodes in <table> tag
   getTableNode(sb, node, "table",4, tags, tagsvalue);
   //split string to get real value
  if(sb!=null){
        String[]values=sb.toString().split(";");
       for(int j=0;j<values.length;j++)
       {
           if(j==0){
               obj.setSongvn(values[j]);
               obj.setSongen(unicodeToAscii(values[j]));
           }
           else if(j==1){
               obj.setSingervn(values[j]);
               obj.setSingeren(unicodeToAscii(values[j]));
           }
           else if(j==2){
               obj.setAlbumvn(values[j]);
               obj.setAlbumen(unicodeToAscii(values[j]));
           }
           else if(j==3){
               obj.setLinkmedia(values[j]);

           }
       }
        sb=new StringBuffer();
        //getText(sb, node, "httrack",1);
       // getComment(sb, node, 1);
        if(sb!=null)
            obj.setLinksource(sb.toString());
              
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
          if(file.getName().endsWith(".html")||file.getName().endsWith(".htm")){
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
    HoangHuyNetHandler handler = new HoangHuyNetHandler();
    //handler.index(new File(args[0]));
    org.apache.lucene.document.Document doc = handler.getDocument(
      new FileInputStream(new File(args[0])));
   // while(doc.fields().hasMoreElements()){
    //System.out.println(doc.get("url"));
    if(doc!=null){
        System.out.println(doc.getField("songvn").stringValue());
        System.out.println(doc.getField("songen").stringValue());
        System.out.println(doc.getField("singervn").stringValue());
        System.out.println(doc.getField("singeren").stringValue());
        System.out.println(doc.getField("linkobject").stringValue());
        System.out.println(doc.getField("linksource").stringValue());
        System.out.println(doc.getField("linkmedia").stringValue());
        System.out.println(doc.getField("albumen").stringValue());
        System.out.println(doc.getField("albumvn").stringValue());

    //doc.fields().nextElement();
  //}
    }

    }
}
