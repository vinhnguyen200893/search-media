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
public class NhacCuaTuiHandler extends HtmlHandler  implements DocumentHandler {
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
    if(sb!=null)
        obj.setLinksource(AnalysisComment(sb.toString()));
    sb=new StringBuffer();
    getText(sb, node, "title",1);
    String title = sb.toString();
      //get object
      sb=new StringBuffer();
    //note :link in nhaccuatui is sourcelink not is a media link
    StringBuffer link=new StringBuffer();
    getObjects(sb, node, "object",2,false,true,link);

    if (title != null && !sb.toString().equals("")){


       obj.setLinkobject(sb.toString());
    //analysis the title
       String[]Str=AnalysisTitle(title);
       obj.setLinkobject(sb.toString());
       obj.setLinkmedia(link.toString());
    for(int i=0;i<Str.length;i++)
    {
        if(i==0)//first is name song with UTF-8 encode
          obj.setSongvn(Str[i]);
        else if(i==1)//first is name song with UTF-8 encode
         obj.setSingervn(Str[i]);
        else if(i==2)//first is name song with UTF-8 encode
         obj.setSongen(Str[i]);
        else if(i==3)//first is name song with UTF-8 encode
         obj.setSingeren(Str[i]);
    }
        //only index object obtain link media if this page has title(name of media)




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
    NhacCuaTuiHandler handler = new NhacCuaTuiHandler();
    org.apache.lucene.document.Document doc = handler.getDocument(
      new FileInputStream(new File(args[0])));
   // while(doc.fields().hasMoreElements()){
    //System.out.println(doc.get("url"));
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

    //doc.fields().nextElement();
  //}
  //  }
  }
}
