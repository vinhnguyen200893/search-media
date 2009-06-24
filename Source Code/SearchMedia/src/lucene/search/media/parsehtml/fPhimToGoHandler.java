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
import java.io.FileInputStream;
import java.io.File;
import lucene.search.media.indexer.Operators;
import lucene.search.media.objects.MediaObject;
import lucene.search.media.parseframework.*;
/**
 *
 * @author Administrator
 */
public class fPhimToGoHandler extends HtmlHandler  implements DocumentHandler {
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
    MediaObject obj=new MediaObject();

       //get source link(real link on website)
    //sb contains array comment and only first item in this array  is available
    getComment(sb, node, 1);
    if(sb!=null){
        obj.setLinksource(AnalysisComment_phimtogo(sb.toString()));
    }
    else
    {
        return doc;
    }
    sb=new StringBuffer();
    getText(sb, node, "title",1);
    String title = sb.toString();
      //get object
    sb=new StringBuffer();

    StringBuffer link=new StringBuffer();
    getObjects(sb, node, "object",1,false,true,link);

    if (title != null && !sb.toString().equals("")){
    
        obj.setLinkobject(sb.toString());
    
      //link play media
      obj.setLinkmedia(link.toString());
      
      //link tach xml . ko dc thi phai
      obj.setService(link.toString());

//      obj.setService(getLinkInTag_phimtogo(link.toString(), "src='", "'"));
      obj.setIdservice("PHIM_TO_GO");
        //analysis the title
       String[]Str=AnalysisTitle(title);                   
       obj.setSongvn(Str[1]);
         //only index object obtain link media if this page has title(name of media)
       obj.setSongen(unicodeToAscii(Str[1]));

        obj.setAlbumvn(Str[1]);
         //only index object obtain link media if this page has title(name of media)
       obj.setAlbumen(unicodeToAscii(Str[1]));


       Operators Op=new Operators();
       doc=Op.addDocumentObject(obj);
    }
    return doc;
  }

   
    protected String getLinkInTag_phimtogo(String source, String begin, String end) {
        String Strobject = "";
//        String reg = begin + "*?" + end;
//        Strobject = source.split(reg)[pos_get];
        int be = source.indexOf(begin) + begin.length();
        
        String s = source.substring(be);
        int en = s.indexOf(end);

        Strobject = s.substring(0, en);
        return Strobject;
    }
  public static void main(String args[]) throws Exception {
    fPhimToGoHandler handler = new fPhimToGoHandler();
    org.apache.lucene.document.Document doc = handler.getDocument(
      new FileInputStream(new File(args[0])));
   // while(doc.fields().hasMoreElements()){
    //System.out.println(doc.get("url"));
    if(doc!=null){
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

        System.out.println("LINK MEDIA");
        System.out.println(doc.getField("linkmedia").stringValue());

        System.out.println("SERVICE");
        System.out.println(doc.getField("service").stringValue());

        System.out.println("ALBUM");
        System.out.println(doc.getField("albumen").stringValue());
        System.out.println(doc.getField("albumvn").stringValue());

      //  doc.fields().nextElement();
    }
  }
}
