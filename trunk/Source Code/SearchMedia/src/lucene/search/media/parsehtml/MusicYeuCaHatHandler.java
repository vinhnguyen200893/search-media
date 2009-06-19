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
    getTitle(sb, node);
    String title = sb.toString();
   if (title != null){
//only index object obtain link media if this page has title(name of media)
     MediaObject obj=new MediaObject();
     obj.setIdservice("MUSIC_YEU_CA_HAT");
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
        
     //get lyrics
     sb=new StringBuffer();
        getTextOfTable(sb, node, "table",22);
        if(sb!=null)
        {
            obj.setLyric(sb.toString());
            String  embed=encodeEmbedTag(sb.toString());
            obj.setLinkobject(embed);
        }
     //get source link(real link on website)
        sb=new StringBuffer();
        getComment(sb, node, 1);
        if(sb!=null){
            String[]linkreal=AnalysisLinkComment(sb.toString());
            obj.setLinksource(linkreal[0]);
            obj.setDatemodified(linkreal[1]);
        }
        Operators Op=new Operators();
      doc=Op.addDocumentObject(obj);

    }
    return doc;
  }
  public String encodeEmbedTag(String sub)
  {
      int begin=sub.lastIndexOf("document.write('");
      int end=-1;
       if(begin!=-1) 
       {
           end=sub.lastIndexOf(");")+2;
           return "<script  type=\"text/javascript\"> "+sub.substring(begin,end)+" </script>";
       }
       else
           return "";
        
  }
   public static void main(String args[]) throws Exception {
    MusicYeuCaHatHandler handler = new MusicYeuCaHatHandler();
//    handler.index(new File(args[0]));
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
        System.out.println(doc.getField("lyric").stringValue());

    //doc.fields().nextElement();
  //}
    }

    }
}
