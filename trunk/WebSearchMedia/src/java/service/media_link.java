/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 *
 * @author Thanh Nga
 */
public class media_link {

    private String _file_xml = "";
    private String _link = "";

    /**
     * @return the _file_xml
     */
    public String getFile_xml() {
        return _file_xml;
    }

    /**
     * @param file_xml the _file_xml to set
     */
    public void setFile_xml(String file_xml) {
        this._file_xml = file_xml;
    }

    /**
     * @return the _link
     */
    public String getLink() {
        return _link;
    }

    /**
     * @param link the _link to set
     */
    public void setLink(String link) {
        this._link = link;
    }
    //FUNCTIONS

    public Node init() {
        Element root = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(_file_xml);
            root = doc.getDocumentElement(); // "people" node          


        } catch (Exception e) {
        } finally {
            return root;
        }

    }

    public boolean isTextNode(Node n) {
        return n.getNodeName().equals("#text");
    }

    //get link nhaccuatui and chacha.vn
    public void getLinkNhacCuaTui(int node_pos, String node_name) {
        Node root = init();
        if(root!=null)
        {
        Node track_list = root.getFirstChild();     // <tracklist>
        Node node_link = track_list.getChildNodes().item(node_pos);//<track>
        NodeList list = node_link.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node current = list.item(i);
            if (current.getNodeName().toUpperCase().equals(node_name.toUpperCase())) {
                this._link = current.getNodeValue();
                break;
            }

        }
        }
        
    }

    //get link for musictamtay.vn +muzic9.vn
    public void getLinkMusicTamTay(String node_name) {
        Node root = init();
        if(root!=null)
        {
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node current = list.item(i);
            if (current.getNodeName().toUpperCase().equals(node_name.toUpperCase())) {
                NodeList n=current.getChildNodes();
                for (int j = 0; j < current.getChildNodes().getLength(); j++) {
                     Node node=n.item(j);
                    if (node.getNodeName().toUpperCase().equals("REF"))                        
                    {
                        this._link = node.getAttributes().item(0).getNodeValue();
                        return;
                    }
                }

            }

        }
        }
    }
}
