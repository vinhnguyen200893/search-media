/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.parsehtml;

import org.apache.lucene.document.Document;
import org.cyberneko.html.parsers.DOMFragmentParser;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.w3c.dom.DocumentFragment;
import org.apache.html.dom.HTMLDocumentImpl;
import java.io.InputStream;
import java.io.IOException;
import lucene.search.media.indexer.Operators;
import lucene.search.media.objects.MediaObject;
import lucene.search.media.parseframework.*;
/**
 *
 * @author Administrator
 */
public class VnMusicHandler extends HtmlHandler implements DocumentHandler {
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

     MediaObject obj=new MediaObject();
    StringBuffer sb = new StringBuffer();
     getComment(sb, node, 1);
     if(sb!=null){
            String[]linkreal=AnalysisLinkComment(sb.toString());
            obj.setLinksource(linkreal[0]);
            obj.setDatemodified(linkreal[1]);
        }
    //flag to assign to index or no
   
   // HtmlHandler html=new HtmlHandler();
    sb=new StringBuffer();
    getText(sb, node, "h1",1);
    //get object
    StringBuffer Strobj=new StringBuffer();
    StringBuffer link=new StringBuffer();
    getObjects(Strobj, node, "object",2,false,true,link);
    if (sb.toString() != null){
        obj.setSongvn(sb.toString());
        obj.setSongen(unicodeToAscii(sb.toString()));  
        obj.setLinkobject(Strobj.toString());
        obj.setLinkmedia(link.toString());
        //only index object obtain link media if this page has title(name of media)
        //get source link(real link on website)
        

               
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
    public static void main(String args[]) throws Exception {
//    VnMusicHandler handler = new VnMusicHandler();
//
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
//
   }
}
