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
import lucene.search.media.link.FilterLinks;
public class NewHTMLHandler implements DocumentHandler {
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

    if (title != null){
      doc.add(Field.Text("title", title));     

    }
    sb=new StringBuffer();
    getObjects(sb, node, "object");
    if(sb!=null)
        doc.add(Field.UnStored("object", sb.toString()));
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
// private void getObjects(StringBuffer sb, Node node) {
//
//    if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
//      sb.append(" "+node.getNodeName()+" ='"+node.getNodeValue()+"'");
//
//    }
//    else if (node.getNodeType() == Node.ELEMENT_NODE) {
//      sb.append("<"+node.getNodeName());
//    }
//    NodeList children = node.getChildNodes();
//    if (children != null) {
//      int len = children.getLength();
//      for (int i = 0; i < len; i++) {
//        getObjects(sb, children.item(i));
//
//    }
//    }
// }
  private void getObjects(StringBuffer sb, Node node) {
    sb.append("<"+node.getNodeName()+" width='100' height= '40'"+ " >");
    NodeList children = node.getChildNodes();
    if (children != null) {
      int len = children.getLength();
      for (int i = 0; i < len; i++) {
        //getText(sb, children.item(i));
          Node child=children.item(i);
          if(child.getNodeType()==Node.ELEMENT_NODE){
          sb.append("<"+child.getNodeName());//<param>
          if(child.hasAttributes()){
          for(int j=0;j<child.getAttributes().getLength();j++){
              Node para=child.getAttributes().item(j);
              sb.append(" "+para.getNodeName()+" ='"+para.getNodeValue()+"'");

          }
          }
          sb.append(" />");//</param>
          }

      }
      sb.append(" </"+node.getNodeName()+">");
    }
 
  }
  private boolean getObjects(StringBuffer sb, Node node,
    String element) {

      if (node.getNodeType() == Node.ELEMENT_NODE) {
        if (element.equalsIgnoreCase(node.getNodeName())) {
        //  if (node.getNodeName().equalsIgnoreCase("object")) {
        getObjects(sb, node);
        return true;

      }
    }
    NodeList children = node.getChildNodes();
    if (children != null) {
      int len = children.getLength();
      for (int i = 0; i < len; i++) {
          getObjects(sb, children.item(i), element);
//        if (getObjects(sb, children.item(i), element)) {
//          return true;
        //}
      }
    }
    return false;
  }
  private boolean getText(StringBuffer sb, Node node,
    String element) {
    if (node.getNodeType() == Node.ELEMENT_NODE) {
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
 
  public static void main(String args[]) throws Exception {
    NewHTMLHandler handler = new NewHTMLHandler();
    org.apache.lucene.document.Document doc = handler.getDocument(
      new FileInputStream(new File(args[0])));
   // while(doc.fields().hasMoreElements()){
    //System.out.println(doc.get("url"));
        System.out.println(doc.getField("title").stringValue());
        System.out.println(doc.getField("object").stringValue());
    //doc.fields().nextElement();
  //}
  }
}
