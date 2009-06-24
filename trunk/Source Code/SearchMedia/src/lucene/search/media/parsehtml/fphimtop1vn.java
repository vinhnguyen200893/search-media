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
public class fphimtop1vn extends HtmlHandler  implements DocumentHandler {
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
        obj.setLinksource(AnalysisComment(sb.toString()));
    }
    else
    {
        return  doc;
    }
    sb=new StringBuffer();
    getText(sb, node, "title",1);
    String title = sb.toString();
      //get object
      sb=new StringBuffer();
    //note :link in nhaccuatui is sourcelink not is a media link
    String[]Str=AnalysisTitle(title);


    if (title != null && Str[0].compareTo("Phim.Phim Online.Phim Ma.Phim Hay.Phim trực tuyến ") != 0   )
    {
      // obj.setLinkobject(sb.toString());
    //analysis the title
      
       obj.setSongvn(Str[0] );       //
       obj.setAlbumvn(Str[0]);
         //only index object obtain link media if this page has title(name of media)
       obj.setSongen(unicodeToAscii(Str[0]));
       obj.setAlbumen(unicodeToAscii(Str[0]));
       obj.setIdservice("PHIM_TOP_1");
    }else{
        return doc;        
    }
    sb=new StringBuffer();
    //getText(sb, node, "input",4);
    //getObjects_clipvn(StringBuffer sb, Node node,String element,int pos,boolean getlink, link)
    StringBuffer link = new StringBuffer();
    
    getObjects_clipvn( sb,  node, "object", 1, true, link);
    if(sb == null)
    {     
        //khi ko co tag object thi dung tag img
        getObjects_clipvn( sb,  node, "img", 10, true, link);
    }
    if ( sb != null ){
        obj.setLinkobject(sb.toString());
        obj.setLinkmedia(link.toString());
    }
   Operators Op=new Operators();
   doc=Op.addDocumentObject(obj);

    return doc;
  }
  public static void main(String args[]) throws Exception {
    fphimtop1vn handler = new fphimtop1vn();
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
    }
  }
}
