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
public class ChaChaVnHandler extends HtmlHandler implements DocumentHandler {

    private DOMFragmentParser parser = new DOMFragmentParser();

    public Document getDocument(InputStream is)
            throws DocumentHandlerException {

        DocumentFragment node =
                new HTMLDocumentImpl().createDocumentFragment();
        try {
            parser.parse(new InputSource(is), node);
        } catch (IOException e) {
            throw new DocumentHandlerException(
                    "Cannot parse HTML document: ", e);
        } catch (SAXException e) {
            throw new DocumentHandlerException(
                    "Cannot parse HTML document: ", e);
        }

        org.apache.lucene.document.Document doc =
                new org.apache.lucene.document.Document();


        StringBuffer sb = new StringBuffer();
        MediaObject obj = new MediaObject();

        //get source link(real link on website)
        //sb contains array comment and only first item in this array  is available
        getComment(sb, node, 1);
        if (sb != null) {
            String[] linkreal = AnalysisLinkComment(sb.toString());
            obj.setLinksource(linkreal[0]);
            obj.setDatemodified(linkreal[1]);
        }
        sb = new StringBuffer();
        getText(sb, node, "title", 1);
        String title = sb.toString();
        //get object
        sb = new StringBuffer();
        //note :link in nhaccuatui is sourcelink not is a media link
        StringBuffer link = new StringBuffer();
        getEmbedInput(sb, node, "input",8,"value");
        if (title != null && sb!=null)
        {
            //obj.setLinkobject(sb.toString().split("[]"));
            //analysis the title
            String[] Str = AnalysisTitle(title);
            obj.setLinkobject(setLink(sb.toString()));
            obj.setService(getService(sb.toString()));
            
            for (int i = 0; i < Str.length; i++) {
                if (i == 1)//first is name song with UTF-8 encode
                {
                    obj.setSongvn(Str[i]);
                    obj.setSongen(unicodeToAscii(Str[i]));
                } else if (i == 2)//first is name song with UTF-8 encode
                {
                    obj.setSingervn(Str[i]);
                    obj.setSingeren(unicodeToAscii(Str[i]));
                }
            }
            Operators Op = new Operators();
            doc = Op.addDocumentObject(obj);
        }

        return doc;

    }

    @Override
    protected String[] AnalysisTitle(String sb) {
        String[] sub = sb.split("[:-]");
        return sub;
    }
    protected  String setLink(String url)
    {
        String[] Str=url.split(" ");
        Str[3]="src=\"http://chacha.vn/embedded/v1.0/mp3player.swf\"";
        String real_link="";
        for(int i=0;i<Str.length;i++)
        {
            real_link+=Str[i]+" ";
        }
        return real_link;
    }
     protected  String getService(String url)
    {
        String[] Str=url.split("\"");
        int begin=Str[1].indexOf("file=")+5;
        return Str[1].substring(begin,Str[1].length());
    }

    public static void main(String args[]) throws Exception {
        ChaChaVnHandler handler = new ChaChaVnHandler();
        org.apache.lucene.document.Document doc = handler.getDocument(
                new FileInputStream(new File(args[0])));
               
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
    }
}
