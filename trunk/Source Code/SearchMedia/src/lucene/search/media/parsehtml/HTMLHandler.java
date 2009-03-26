package lucene.search.media.parsehtml;



import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import org.cyberneko.html.parsers.DOMFragmentParser;

import org.xml.sax.SAXException;
import org.xml.sax.InputSource;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DocumentFragment;

import org.apache.html.dom.HTMLDocumentImpl;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import lucene.search.media.parseframework.*;

public class HTMLHandler implements DocumentHandler {
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

    //flag to assign to index or no
    boolean index=false;
    StringBuffer sb = new StringBuffer();
    getText(sb, node, "title");
    String title = sb.toString();

    //get body text
//    sb.setLength(0);
//    getText(sb, node);
//    String text = sb.toString();


//    if ((text != null) && (!text.equals(""))) {
//      doc.add(Field.Text("body", text));
//    }

    //get links
    //added by THANH NGA
    
    try{
    DOMContentUtils utils=new DOMContentUtils();
    ArrayList l = new ArrayList();              // extract outlinks
    URL baseTag = utils.getBase(node);
    if(baseTag==null)
        baseTag=new URL("http://");
    Outlink []links =null;
    FilterLinks fil=new FilterLinks();
    if(getLinks(baseTag, l, node))
    {        
        l=fil.filter(l);
        links = (Outlink[])l.toArray(new Outlink[l.size()]);
    }
    if(links!=null&&links.length>0){
    for(int i=0;i<links.length;i++){    	
    	if(fil.checkProtocol(links[i].getToUrl())){//if a url not a anchor
    		if(fil.checkMediaLink(links[i].getToUrl())){//if url is a media file
            doc.add(Field.Keyword("url",fil.getMediaLink(links[i].getToUrl())));//get real file media
            index=true;
            }
    	}
    }
    }
    }
    catch (Exception e){
        e.printStackTrace();
    }

    //end added
    
    if ((title != null) && (!title.equals(""))&& index==true) {
      doc.add(Field.Text("title", title));
      //code new add by Nga

    }
    return doc;
  }

  private void getText(StringBuffer sb, Node node) {
    if (node.getNodeType() == Node.TEXT_NODE) {
      sb.append(node.getNodeValue());

    }

    NodeList children = node.getChildNodes();
    if (children != null) {
      int len = children.getLength();
      for (int i = 0; i < len; i++) {
        getText(sb, children.item(i));
      }
    }
    //added by THANH NGA
//        if(node.getNodeName().toLowerCase().equals("a"))
//        sb.append(node.getAttributes().getNamedItem("href").getNodeValue());
//
  }

  private boolean getText(StringBuffer sb, Node node,
    String element) {
    if (node.getNodeType() == Node.ELEMENT_NODE) {
//        if(node.getNodeName().toLowerCase().equals("a")){
//            NamedNodeMap m=node.getAttributes();
//            for(int i=0;i<m.getLength();i++)
//                if(m.item(i).equals("href")) sb.append(m.item(i).getNodeValue());
//        }
        if (element.equalsIgnoreCase(node.getNodeName())) {
        getText(sb, node);
        return true;
      }
    }
    NodeList children = node.getChildNodes();
    if (children != null) {
      int len = children.getLength();
      for (int i = 0; i < len; i++) {
        if (getText(sb, children.item(i), element)) {
          return true;
        }
      }
    }
    return false;
  }
  public boolean getLinks(URL base, ArrayList outlinks,Node node) throws Exception
  {
      boolean flag=false;
     // URL url=new URL("file");
      DOMContentUtils utils=new DOMContentUtils();
      utils.getOutlinks(base, outlinks, node);
      if(!outlinks.isEmpty()) flag=true;
      return flag;
  }

  public static void main(String args[]) throws Exception {
    HTMLHandler handler = new HTMLHandler();
    org.apache.lucene.document.Document doc = handler.getDocument(
      new FileInputStream(new File(args[0])));
    while(doc.fields().hasMoreElements()){
    System.out.println(doc.get("url"));
    doc.fields().nextElement();
  }
  }
}
