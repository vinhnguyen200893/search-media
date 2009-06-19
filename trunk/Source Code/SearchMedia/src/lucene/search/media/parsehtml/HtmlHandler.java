/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lucene.search.media.parsehtml;

import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Administrator
 */
public class HtmlHandler {
//get element at specific position

    public int step = 1;
    public int step_object = 1;
    public int step_child = 1;
    /*
     * convert text from unicode to ascii
     */

    public String unicodeToAscii(String text) {

        String dl = "";
        //text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            /* A 65
            À 192
            Á 193
            Ã 195
            Ạ 7840
            Ả 7842
             */
            if (c == 192 || c == 193 || c == 195 || c == 7840 || c == 7842) {
                dl += 'A';

            }
          /* 225  a'
           224  a`
          7843 a?
           227   a~
           7841 a.
           */
            if (c == 225 || c == 224 || c == 7843 || c == 227 || c == 7841) {
                dl += 'a';

            }

            /*
            Â 194
            Ấ 7844
            Ầ 7846
            Ẩ 7848
            Ẫ 7850
            Ậ 7852
             */ else if (c == 194 || c == 7844 || c == 7846 || c == 7848 || c == 7850 || c == 7852) {
                dl += 'A';

            }
            /* 7845 a^ a^' a^` a^? a^~ a^.
               7847
               7849
               7851
               7853
            */
            else if (c == 7845 || c == 7847 || c == 7849 || c == 7851 || c == 7853 || c == 197) {
                dl += 'a';

            }
             /*
            Ă 258
            Ắ 7854
            Ằ 7856
            Ẳ 7858
            Ẵ 7860
            Ặ 7862
             */ else if (c == 258 || c == 7854 || c == 7856 || c == 7858 || c == 7860 || c == 7862) {
                dl += 'A';

            }
           /* 7855 a(' a(` a(? a(~.
           7857
           7859
           7861
           7863
            */
            else if (c==259||c == 258 || c == 7855 || c == 7857 || c == 7859 || c == 7861 || c == 7863) {
                dl += 'a';

            }
            /*
            E 69
            È 200
            É 201
            Ẹ 7864
            Ẻ 7866
            Ẽ 7868
             */ else if (c == 200 || c == 201 || c == 7864 || c == 7866 || c == 7868) {
                dl += 'E';

            }
           /* 233  e' e` e? e~ e.
           232
           7867
           7869
           7865
            */
             else if (c==234||c == 232 || c == 233 || c == 7865 || c == 7867 || c == 7869) {
                dl += 'e';

            }
            /*
            Ê 202
            Ế 7870
            Ề 7872
            Ể 7874 ể 7875
            Ễ 7876 ễ 7877
            Ệ 7878 ệ 7879
             */ else if (c == 202 || c == 7870 || c == 7872 || c == 7874 || c == 7876 || c == 7878) {
                dl += 'E';

            }
           /* 7871 e^' e^` e^?  e^~  e^.
           7873
           7875
           7877
           7879
            */
             else if (c == 203 || c == 7871 || c == 7873 || c == 7875 || c == 7877 || c == 7879) {
                dl += 'e';

            }
            /*
            I 73
            Ì 204
            Í 205
            Ĩ 296
            Ỉ 7880
            Ị 7882
             */ else if (c == 204 || c == 205 || c == 296 || c == 7880 || c == 7882) {
                dl += 'I';

            }
            /*237 i' i` i?  i~  i.
           236
           7881
           297
           7883
            */
            else if (c == 237 || c == 236 || c == 297 || c == 7881 || c == 7883) {
                dl += 'i';

            }
            /*
            O 79
            Ò 210
            Ó 211
            Õ 213
            Ọ 7884
            Ỏ 7886
             */ else if (c == 210 || c == 211 || c == 213 || c == 7884 || c == 7886) {
                dl += 'O';

            }
            /*
             * 243 o' o` o?  o~  o.
               242
               7887
               245
               7885
             */
             else if (c == 243 || c == 242 || c==245||c == 7885 || c == 7887) {
                dl += 'o';

            }
            
            /*
            Ô 212
            Ố 7888
            Ồ 7890
            Ổ 7892
            Ỗ 7894
            Ộ 7896
             */ else if (c == 212 || c == 7888 || c == 7890 || c == 7892 || c == 7894 || c == 7896) {
                dl += 'O';

            }
            /*
             7889 o^' o^` o^?  o^~  o^.
               7891
               7893
               7895
               7897
             */
             else if (c==244||c == 7889 || c == 7891 || c == 7893 || c == 7895 || c == 7897) {
                dl += 'o';

            }

            /*
            Ơ 416
            Ớ 7898
            Ờ 7900
            Ở 7902
            Ỡ 7904
            Ợ 7906
             */ else if (c == 416 || c == 7898 || c == 7900 || c == 7902 || c == 7904 || c == 7906) {
                dl += 'O';

            }
            /*
             7899 o*' o*` o*?  o*~  o*.
               7901
               7903
               7905
               7907
             */
             else if (c == 417 || c == 7899 || c == 7901 || c == 7903 || c == 7905 || c == 7907) {
                dl += 'o';

            }
            /*
            U 85
            Ù 217
            Ú 218
            Ũ 360
            Ụ 7908
            Ủ 7910
             */ else if (c == 217 || c == 218 || c == 360 || c == 7908 || c == 7910) {
                dl += 'U';

            }
            /*
             *250  u' u` u? u~ u.
           249
           7911
           361
           7909
             */
            else if (c == 250 || c == 249 || c == 7911 || c == 361 || c == 7909) {
                dl += 'u';

            }
            /*
            Ư 431
            Ứ 7912
            Ừ 7914
            Ử 7916
            Ữ 7918
            Ự 7920
             */ else if (c == 431 || c == 7912 || c == 7914 || c == 7916 || c == 7918 || c == 7920) {
                dl += 'U';

            }
            /*
              7913 u*' u*` u*? u*~ u*.
               7915
               7917
               7919
               7921
             */
            else if (c == 7913 || c == 7915 || c == 7917 || c == 7919 || c == 7921) {
                dl += 'u';

            }

            /*
            Y 89
            Ý 221
            Ỳ 7922
            Ỵ 7924
            Ỷ 7926
            Ỹ 7928
             */ else if (c == 221 || c == 7922 || c == 7924 || c == 7926 || c == 7928) {
                dl += 'Y';

            }
            /*
             253 y' y` ...
           7923
           7927
           7929
           7925
             */
           else if (c == 253 || c == 7923 || c == 7925 || c == 7927 || c == 7929) {
                dl += 'Y';

            }
            /*
            Đ 208 272
             */ else if (c == 208 || c == 272) {
                dl += 'D';

            }
             else if (c == 273) {
                dl += 'd';

            }
            else {               
                dl += text.charAt(i);
            }
        }
        return dl;
    }

    /*
     * get Text of Node at position with specific name
     */
    protected boolean getLinkSource(StringBuffer sb, Node node, String element) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                getText(sb, node);

            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                getLinkSource(sb, children.item(i), element);

            }
        }
        return false;

    }      
    protected  void getComment(StringBuffer sb,Node node,int pos)
    {        
        if (node.getNodeType() == Node.COMMENT_NODE)
        {
           // if(pos==step_object )
           // {
               sb.append(node.getNodeValue().toString()+"\n");            
         
              // return;

           // }
           // step_object++;
        }

        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                getComment(sb, children.item(i), pos);
                    
            }

            //return ;
        }
        return ;


    }
    /*
     * get Text of Node at position with specific name
     */

    protected boolean getText(StringBuffer sb, Node node, String element, int position) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                if (step == position) {
                    getText(sb, node);
                    return true;
                }
                step++;
            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
               if(getText(sb, children.item(i), element, position))
                   return true;

            }           
        }
        return false;
    }

    protected void getText(StringBuffer sb, Node node) {
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

    }
    /*
     * get Text of Node at position with specific name
     */

    protected boolean getTitle(StringBuffer sb, Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (node.getNodeName().equalsIgnoreCase("head")) {
                getText(sb, node, "title", 1);
                return true;

            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                getTitle(sb, children.item(i));

            }
        }
        return false;
    }


    /*
     * get attribute of node
     */
    protected void getAttributes(StringBuffer sb, Node node, boolean parent, boolean getlink, StringBuffer link) {
        if (node.hasAttributes()) {
            for (int j = 0; j < node.getAttributes().getLength(); j++) {
                Node para = node.getAttributes().item(j);
                sb.append(" " + para.getNodeName() + " ='" + para.getNodeValue() + "'");
                if (getlink && para.getNodeName().equalsIgnoreCase("src"))//link media in <PARAM> tag
                {
                    if (link.toString().endsWith("")) {
                        link.append(para.getNodeValue());
                    }
                }
            }
            if (parent)//current node is a parent node
            {
                sb.append(" >");
            }
        }
    }
    /*
     * get object tag in HTML page
     */
    protected void getObjects(StringBuffer sb, Node node, boolean getlink, StringBuffer link) {

        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                //getText(sb, children.item(i));
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    sb.append("<" + child.getNodeName());//<param>
                    getAttributes(sb, child, false, getlink, link);
                    sb.append(" />");//</param>
                }

            }

        }
    }
    protected boolean getObjects(StringBuffer sb, Node node, String element, int pos, boolean width_height, boolean getlink, StringBuffer link) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                //  if (node.getNodeName().equalsIgnoreCase("object")) {
                if (step_object == pos) {
                    sb.append("<" + node.getNodeName() + " ");
                    if (width_height) {
                        sb.append(" width='300' height= '50'" + " >");
                    } else {
                        getAttributes(sb, node, true, getlink, link);
                    }
                    getObjects(sb, node, getlink, link);
                    sb.append(" </" + node.getNodeName() + ">");
                    return true;
                }
                step_object++;
            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                if (getObjects(sb, children.item(i), element, pos, width_height, getlink, link)) //if (getObjects(sb, children.item(i), element)) {
                {
                    return true;
                }
            //}
            }
           // return true;
        }
        return false;

    }
         
   //clip.vn 
    protected  boolean getObjects_clipvn(StringBuffer sb, Node node,String element,int pos,boolean getlink,StringBuffer link)
    {
        return getObjects(sb,node,element,pos,false,getlink,link);
    } //clip.vn
     /*

     * get tag child in table
     * childtag: name of tags
     * childvalue: postion of child tag
     */

    protected boolean getTableNode(StringBuffer sb, Node node, String element, int pos, ArrayList childtags, ArrayList childvalue) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                if (step_object == pos) {
                    getTableNode(sb, node, childtags, childvalue);
                    return true;
                }
                step_object++;
            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
               if(getTableNode(sb, children.item(i), element, pos, childtags, childvalue))
                   return true;
            //        if (getObjects(sb, children.item(i), element)) {
            //          return true;
            //}
            }
        }
        return false;
    }
    int stepchild = 1;
    int posvalue = 0;

    protected void getTableNode(StringBuffer sb, Node node, ArrayList childtag, ArrayList childvalue) {
        if (posvalue < childtag.size()) {
            if (node.getNodeName().equalsIgnoreCase((childtag.get(posvalue).toString()))) {
                if (stepchild == Integer.parseInt(childvalue.get(posvalue).toString())) {
                    if (childtag.get(posvalue).toString().equalsIgnoreCase("a")) {
                        if (node.hasAttributes()) {
                            for (int j = 0; j < node.getAttributes().getLength(); j++) {
                                Node para = node.getAttributes().item(j);
                                if (para.getNodeName().equalsIgnoreCase("href")) {
                                    sb.append(para.getNodeValue());
                                }

                            }
                        }
                    } else {
                        getText(sb, node);
                        sb.append(";");
                    }
                    posvalue++;

                }
                stepchild++;
            }
            NodeList children = node.getChildNodes();
            if (children != null) {
                int len = children.getLength();
                for (int i = 0; i < len; i++) {
                    getTableNode(sb, children.item(i), childtag, childvalue);
                }
                return;
            }
        }
    }

    /*
     * get  tag child in table
     */
    protected boolean getChildTagInTable(StringBuffer sb, Node node, String element, int pos, String child, int poschild) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                if (step_object == pos) {
                    //getChildTagInTable(sb, node, child, poschild);
                    //  getText(sb, node);
                    getText(sb, node, element, poschild);
                    return true;
                }
                step_object++;
            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
               if(getChildTagInTable(sb, children.item(i), element, pos, child, poschild))
                   return true;
            //        if (getObjects(sb, children.item(i), element)) {
            //          return true;
            //}
            }
        }
        return false;
    }

    protected boolean getChildTagInTable(StringBuffer sb, Node node, String child, int childpos) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (child.equalsIgnoreCase(node.getNodeName())) {
                if (step_object == childpos) {
                    getText(sb, node);
                    return true;
                }
                step_object++;
            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
               if(getChildTagInTable(sb, children.item(i), child, childpos))
                   return true;
            //        if (getObjects(sb, children.item(i), element)) {
            //          return true;
            //}
            }
        }
        return false;
    }

    protected String[] AnalysisTitle(String sb) {
        String[] sub = sb.split("[-|]");
        return sub;
    }

    protected String AnalysisComment(String sb) {
        String[] sub = sb.split("\n");
        int begin = sub[0].lastIndexOf("from") + 5;
        int end = sub[0].indexOf("by");
        String linksource = sub[0].substring(begin, end);
        return linksource;
    }


    protected  String AnalysisComment_phimtogo(String sb)
    {
        String[]sub=sb.split("\n");
        int begin=sub[1].lastIndexOf("from")+5;
        int end=sub[1].indexOf("by");
        String linksource=sub[1].substring(begin,end);
        return linksource;
    }
    
    protected String[] AnalysisLinkComment(String sb) {
        String[] sub = sb.split("\n");
        String[] linksource = new String[2];
        if(sub.length>0){
        int begin = sub[0].lastIndexOf("from") + 5;
        int end = sub[0].indexOf("by");
        
        linksource[0] = sub[0].substring(begin, end);

        //get modified date
        begin = sub[0].indexOf(",") + 1;
        linksource[1] = sub[0].substring(begin, sub[0].length());

        
        }
        return linksource;
    }

    protected boolean getTextOfTag(StringBuffer sb, Node node, String element) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                //  if (node.getNodeName().equalsIgnoreCase("object")) {
                if (node.hasAttributes()) {
                    Node para = node.getAttributes().item(0);
                    if (para.getNodeName().equalsIgnoreCase("class") && para.getNodeValue().equalsIgnoreCase("gen")) {
                        getText(sb, node);
                        return true;
                    }

                }


            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                if (getTextOfTag(sb, children.item(i), element)) //if (getObjects(sb, children.item(i), element)) {
                {
                    return true;
                }
            //}
            }
        }
        return false;
    }

    protected boolean getTextOfTable(StringBuffer sb, Node node, String element, int pos) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                if (node.getNodeName().equalsIgnoreCase(element)) {
                    if (step == pos) {
                        getText(sb, node);
                        return true;
                    //  return getChildTagInTable(sb, node, child, poschild);
                    }
                    step++;
                }
            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                if (getTextOfTable(sb, children.item(i), element, pos)) //if (getObjects(sb, children.item(i), element)) {
                {
                    return true;
                }
            //}
            }
        }
        return false;
    }

    protected boolean getTextChildOfTable(StringBuffer sb, Node node, String element, int pos) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                if (node.getNodeName().equalsIgnoreCase(element)) {
                    if (step_child == pos) {
                        getText(sb, node);
                        return true;
                    }
                    step_child++;
                }
            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                if (getTextChildOfTable(sb, children.item(i), element, pos)) //if (getObjects(sb, children.item(i), element)) {
                {
                    return true;
                }
            //}
            }
        }
        return false;
    }

    public String generateObject(String link, int width, int height) {
        String object = "";
        object += "<object height=\"" + height + "\" width=\"" + width + "\" type=\"application/x-oleobject\"";

        object += " standby=\"Loading Microsoft Windows Media Player components...\"";

        object += " codebase=\"http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,0,0,0\"";

        object += " classid=\"clsid:6BF52A52-394A-11d3-B153-00C04F79FAA6\" id=\"wPlayer\">";
        object += " <param value=\"" + link + "\"";
        object += " name=\"URL\"/>";

        object += " <param value=\"999\" name=\"playCount\"/>";
        object += " <param value=\"true\" name=\"showStatusbar\"/>";
        object += " <param value=\"0\" name=\"autostart\"/>";
        object += " <param value=\"100\" name=\"volume\"/>";
        object += " <param value=\"0\" name=\"enableContextMenu\"/>";
        object += " <param value=\"1\" name=\"showdisplay\"/>";
        object += " <param value=\"0\" name=\"enableErrorDialogs\"/>";
        object += " <param value=\"1\" name=\"autorewind\"/>";
        object += " <param value=\"1\" name=\"ShowControls\"/>";
        object += " <embed height=\"" + height + "\" width=\"" + width + "\" src=\"" + link + "\"";
        object += " volume=\"70\" playcount=\"999\" autorewind=\"1\" enabled=\"1\" enablecontextmenu=\"0\" showcontrols=\"1\" showstatusbar=\"1\" pluginspage=\"http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,0,0,0\"";
        object += " autostart=\"0\" type=\"application/x-mplayer2\"/>";
        object += " </object>";
        return object;
    }
    //CHA CHA .VN

    protected boolean getEmbedInput(StringBuffer sb, Node node, String element, int pos,String child_element) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                if (step_object == pos) {
                     getEmbedInputValue(sb, node, child_element);
                     return true;
                }
                step_object++;
            }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
            int len = children.getLength();
            for (int i = 0; i < len; i++) {
                if(getEmbedInput(sb, children.item(i), element, pos,child_element)) //if (getObjects(sb, children.item(i), element)) {
                   return true;

            }
        }
        return false;
    }

    protected void getEmbedInputValue(StringBuffer sb, Node node, String element) {

        if (node.hasAttributes()) {
            for (int j = 0; j < node.getAttributes().getLength(); j++) {
                Node para = node.getAttributes().item(j);
                if (para.getNodeName().equalsIgnoreCase(element))//link media in <PARAM> tag
                {
                    sb.append(para.getNodeValue());
                    return;
                }
            }
        }      
    }

//CODE FOR AMNHAC.TIMNHANH.COM

protected  String getLinkInTag(String source, String begin, String end, boolean getbegin) {
        String Strobject = "";
//        String reg = begin + "*?" + end;
//        Strobject = source.split(reg)[pos_get];
        int be = source.indexOf(begin) + begin.length();
        int en = source.indexOf(end) + end.length();
        if (getbegin) {
            be = source.indexOf(begin);
        }
        Strobject = source.substring(be, en);
        return Strobject;
    }


 // END CODE FOR AMNHAC.TIMNHANH.COM
    public static void main(String args[]) throws Exception {
//    HoangHuyNetHandler handler = new HoangHuyNetHandler();
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
    }
}
