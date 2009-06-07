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
import lucene.search.media.objects.MediaObject;
import lucene.search.media.parseframework.*;

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
        // HtmlHandler html=new HtmlHandler();
        MediaObject obj = new MediaObject();

        //init arraylist to store tags and value
        ArrayList tags = new ArrayList();
        for (int i = 0; i < 3; i++) {
            tags.add("b");
        }
        tags.add("a");
        ArrayList tagsvalue = new ArrayList();
        for (int i = 1; i <= 3; i++) {
            tagsvalue.add(i);
        }
        tagsvalue.add(9);//<a> tag contains direct link

        //get nodes in <table> tag
        getTableNode(sb, node, "table", 4, tags, tagsvalue);
        //split string to get real value
        if (sb != null) {
            String[] values = sb.toString().split(";");
            for (int j = 0; j < values.length; j++) {
                if (j == 0) {
                    obj.setSongvn(values[j]);
                    obj.setSongen(unicodeToAscii(values[j]));
                } else if (j == 1) {
                    obj.setSingervn(values[j]);
                    obj.setSingeren(unicodeToAscii(values[j]));
                } else if (j == 2) {
                    obj.setAlbumvn(values[j]);
                    obj.setAlbumen(unicodeToAscii(values[j]));
                } else if (j == 3) {
                    obj.setLinkmedia(values[j]);
                    obj.setLinkobject(generateObject(values[j], 200, 60));

                }
            }
            sb = new StringBuffer();
            getComment(sb, node, 1);
            if (sb != null) {
                String[] linkreal = AnalysisLinkComment(sb.toString());
                obj.setLinksource(linkreal[0]);
                obj.setDatemodified(linkreal[1]);
            }
            sb = new StringBuffer();
            getChildTagInTable(sb, node, "table", 6, "div", 1);
            //getText(sb, node, "table",7);
            if (sb != null) {
                obj.setLyric(sb.toString());
            }
            Operators Op = new Operators();
            doc = Op.addDocumentObject(obj);

        }
        return doc;

    }

    public static void main(String args[]) throws Exception {
        HoangHuyNetHandler handler = new HoangHuyNetHandler();
        //handler.index(new File(args[0]));
        org.apache.lucene.document.Document doc = handler.getDocument(
                new FileInputStream(new File(args[0])));
        // while(doc.fields().hasMoreElements()){
        //System.out.println(doc.get("url"));
        if (doc != null) {
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
            System.out.println("ALBUM");
            System.out.println(doc.getField("albumen").stringValue());
            System.out.println(doc.getField("albumvn").stringValue());

            System.out.println("LYRICS");
            System.out.println(doc.getField("lyric").stringValue());
        //doc.fields().nextElement();

        }

    }
}
