/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.parsehtml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Administrator
 */
public class HtmlHandler {
//get element at specific position
   public int step=1;
   public int step_object=1;
 
    /*
     * convert text from unicode to ascii
     */
    public String unicodeToAscii(String text)
    {
                String dl = "";
                text = text.toUpperCase();

                for (int i = 0; i < text.length(); i++)
                {
                    char c = text.charAt(i);
                    /* A 65
                    À 192
                    Á 193
                    Ã 195
                    Ạ 7840
                    Ả 7842
                    */
                    if (c == 192 || c == 193 || c == 195 || c == 7840 || c == 7842)
                    {
                        dl += 'A';
                       
                    }
                    /*
                    Â 194
                    Ấ 7844
                    Ầ 7846
                    Ẩ 7848
                    Ẫ 7850
                    Ậ 7852
                    */
                    else if (c == 194 || c == 7844 || c == 7846 || c == 7848 || c == 7850 || c==7852)
                    {
                        dl += 'A';
                       
                    }
                    /*
                    Ă 258
                    Ắ 7854
                    Ằ 7856
                    Ẳ 7858
                    Ẵ 7860
                    Ặ 7862
                    */
                    else if (c == 258 || c == 7854 || c == 7856 || c == 7858 || c == 7860 || c == 7862)
                    {
                        dl += 'A';
                     
                    }
                    /*
                    E 69
                    È 200
                    É 201
                    Ẹ 7864
                    Ẻ 7866
                    Ẽ 7868
                    */
                    else if (c == 200 || c == 201 || c == 7864 || c == 7866 || c == 7868 )
                    {
                        dl += 'E';
                      
                    }

                    /*
                    Ê 202
                    Ế 7870
                    Ề 7872
                    Ể 7874 ể 7875
                    Ễ 7876 ễ 7877
                    Ệ 7878 ệ 7879
                    */
                    else if (c == 202 || c == 7870 || c == 7872 || c == 7874 || c == 7876 || c == 7878)
                    {
                        dl += 'E';
                     
                    }
                    /*
                    I 73
                    Ì 204
                    Í 205
                    Ĩ 296
                    Ỉ 7880
                    Ị 7882
                    */
                    else if (c == 204 || c == 205 || c == 296 || c == 7880 ||c==7882 )
                    {
                        dl += 'I';
                      
                    }
                    /*
                    O 79
                    Ò 210
                    Ó 211
                    Õ 213
                    Ọ 7884
                    Ỏ 7886
                    */
                    else if (c == 210 || c == 211 || c == 213 || c == 7884 || c == 7886 )
                    {
                        dl += 'O';
                      
                    }
                    /*
                    Ô 212
                    Ố 7888
                    Ồ 7890
                    Ổ 7892
                    Ỗ 7894
                    Ộ 7896
                    */
                    else if (c == 212 || c == 7888 || c == 7890 || c == 7892 || c == 7894 ||c==7896)
                    {
                        dl += 'O';
                       
                    }
                    /*
                    Ơ 416
                    Ớ 7898
                    Ờ 7900
                    Ở 7902
                    Ỡ 7904
                    Ợ 7906
                    */
                    else if (c == 416 || c == 7898 || c == 7900 || c == 7902 || c == 7904 ||c==7906)
                    {
                        dl += 'O';
                      
                    }
                    /*
                    U 85
                    Ù 217
                    Ú 218
                    Ũ 360
                    Ụ 7908
                    Ủ 7910
                    */
                    else if (c == 217 || c == 218 || c == 360 || c == 7908 || c == 7910)
                    {
                        dl += 'U';
                       
                    }
                    /*
                    Ư 431
                    Ứ 7912
                    Ừ 7914
                    Ử 7916
                    Ữ 7918
                    Ự 7920
                    */
                    else if (c == 431 || c == 7912 || c == 7914 || c == 7916 || c == 7918 || c== 7920)
                    {
                        dl += 'U';
                  
                    }
                    /*
                    Y 89
                    Ý 221
                    Ỳ 7922
                    Ỵ 7924
                    Ỷ 7926
                    Ỹ 7928
                    */
                    else if (c == 221 || c == 7922 || c == 7924 || c == 7926 || c == 7928)
                    {
                        dl += 'Y';
                     
                    }
                    /*
                    Đ 208 272
                    */
                    else  if (c == 208 || c == 272)
                    {
                        dl += 'D';
                       
                    }
                    //if (c == 'É' || c == 'È' || c == 'Ẻ' || c == 'Ẽ' || c == 'Ẹ')
                    //{
                    //    dl += 'E';
                    //    continue;
                    //}
                    //if (c == 'Ề' || c == 'È' || c == 'Ể' || c == 'Ễ' || c == 'Ệ' ||c== 'Ê')
                    //{
                    //    dl += 'E';
                    //    continue;
                    //}
                    //if (c == 'Í' || c == 'Ì' || c == 'Ỉ' || c == 'Ĩ' || c == 'Ị')
                    //{
                    //    dl += 'I';
                    //    continue;
                    //}
                    //if (c == 'Ó' || c == 'Ò' || c == 'Ỏ' || c == 'Õ' || c == 'Ọ')
                    //{
                    //    dl += 'O';
                    //    continue;
                    //}
                    //if (c == 'Ố' || c == 'Ồ' || c == 'Ổ' || c == 'Ỗ' || c == 'Ộ' ||c=='Ô')
                    //{
                    //    dl += 'O';
                    //    continue;
                    //}
                    //if (c == 'Ớ' || c == 'Ờ' || c == 'Ở' || c == 'Ỡ' || c == 'Ợ'|| c=='Ơ')
                    //{
                    //    dl += 'O';
                    //    continue;
                    //}
                    //if (c == 'Ú' || c == 'Ù' || c == 'Ủ' || c == 'Ũ' || c == 'Ụ' )
                    //{
                    //    dl += 'U';
                    //    continue;
                    //}
                    //if (c == 'Ứ' || c == 'Ừ' || c == 'Ử' || c == 'Ữ' || c == 'Ự' || c == 'Ư')
                    //{
                    //    dl += 'U';
                    //    continue;
                    //}

                    //if (c == 'Đ' || c == 208)
                    //{
                    //    dl += 'D';
                    //}
                    //if (c == 7870)
                    //{
                    //    dl += 'E';
                    //}
                    else
                    {
                        //if (c < 65 || (c > 90 && c < 96) || c > 122)
                        //    dl += ' ';
                       // else
                            dl += text.charAt(i);
                    }
                }
                return dl.toLowerCase();
            }

     /*
     * get Text of Node at position with specific name
     */
    protected  boolean getLinkSource(StringBuffer sb, Node node,String element)
    {
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
            if(pos==step_object )
            {
               sb.append(node.getNodeValue().toString()+"\n");            
         
               return;               
            }
             step_object++;
        }
       
            NodeList children = node.getChildNodes();
            if (children != null) {
              int len = children.getLength();
              for (int i = 0; i < len; i++) {
                getComment( sb,children.item(i),pos);

              }
              return;
            }
      }
    /*
     * get Text of Node at position with specific name
     */
    protected  boolean getText(StringBuffer sb, Node node,String element,int position)
    {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
                if(step==position){
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
            getText(sb, children.item(i), element,position);
                         
          }
        }
        return false;
      }
    protected  void getText(StringBuffer sb, Node node)
    {
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
    protected  boolean getTitle(StringBuffer sb, Node node)
    {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (node.getNodeName().equalsIgnoreCase("head"))
            {
                    getText(sb, node,"title",1);
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
    protected  void getAttributes( StringBuffer sb,Node node,boolean parent,boolean getlink,StringBuffer link)
    {
        if(node.hasAttributes()){
              for(int j=0;j<node.getAttributes().getLength();j++){
                  Node para=node.getAttributes().item(j);
                   sb.append(" "+para.getNodeName()+" ='"+para.getNodeValue()+"'");
                   if(getlink &&para.getNodeName().equalsIgnoreCase("src"))//link media in <PARAM> tag
                   {
                       if(link.toString().endsWith(""))
                       link.append(para.getNodeValue());
                   }
                }
             if(parent)//current node is a parent node
                 sb.append(" >");
        }
    }
    /*
     * get object tag in HTML page
     */
    protected  void getObjects(StringBuffer sb, Node node,boolean getlink,StringBuffer link)
    {
        
        NodeList children = node.getChildNodes();
        if (children != null) {
          int len = children.getLength();
          for (int i = 0; i < len; i++) {
            //getText(sb, children.item(i));
              Node child=children.item(i);
              if(child.getNodeType()==Node.ELEMENT_NODE){
              sb.append("<"+child.getNodeName());//<param>
              getAttributes(sb, child,false,getlink,link);
              sb.append(" />");//</param>
              }

          }
        
        }
      }
    protected  boolean getObjects(StringBuffer sb, Node node,String element,int pos,boolean width_height,boolean getlink,StringBuffer link)
    {
          if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
            //  if (node.getNodeName().equalsIgnoreCase("object")) {
             if(step_object==pos){
                 sb.append("<"+node.getNodeName()+" ");
                if(width_height)
                    sb.append(" width='300' height= '50'"+ " >");
                else
                    getAttributes(sb, node,true,getlink,link);
                getObjects(sb, node,getlink,link);
                  sb.append(" </"+node.getNodeName()+">");
                 return true;
            }
             step_object++;
          }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
          int len = children.getLength();
          for (int i = 0; i < len; i++) {
              if(getObjects(sb, children.item(i), element,pos,width_height,getlink,link))
            //if (getObjects(sb, children.item(i), element)) {
              return true;
           //}
          }
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
    protected  boolean getTableNode(StringBuffer sb, Node node,String element,int pos,ArrayList childtags,ArrayList childvalue)
    {

          if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
				if(step_object==pos){
				getTableNode(sb, node,childtags,childvalue);
				return true;
				}
				 step_object++;
			}
		}
        NodeList children = node.getChildNodes();
        if (children != null) {
          int len = children.getLength();
          for (int i = 0; i < len; i++) {
              getTableNode(sb, children.item(i), element,pos,childtags,childvalue);
    //        if (getObjects(sb, children.item(i), element)) {
    //          return true;
            //}
          }
        }
        return false;
    }

    int stepchild=1;
    int posvalue=0;
	 protected  void getTableNode(StringBuffer sb, Node node,ArrayList childtag,ArrayList childvalue)
    {

        if(posvalue<childtag.size())
        {
            if (node.getNodeName().equalsIgnoreCase((childtag.get(posvalue).toString())))
            {
                    if(stepchild==Integer.parseInt(childvalue.get(posvalue).toString())){
                        if(childtag.get(posvalue).toString().equalsIgnoreCase("a"))
                        {
                            if(node.hasAttributes()){
                            for(int j=0;j<node.getAttributes().getLength();j++){
                              Node para=node.getAttributes().item(j);
                               if(para.getNodeName().equalsIgnoreCase("href"))
                                sb.append(para.getNodeValue());
                               
                            }
                        }
                        }
                        else
                        {
                        getText(sb, node);
                        sb.append(";");
                        }
                        posvalue++;

                    }
                    stepchild++;
            }
            NodeList children = node.getChildNodes();
            if (children != null)
            {
              int len = children.getLength();
              for (int i = 0; i < len; i++) {
                    getTableNode(sb, children.item(i), childtag, childvalue);
                }
              return;
            }
        }
    }

    protected  String[] AnalysisTitle(String sb)
    {
    String[]sub=sb.split("[-|]");
    return sub;
    }
    protected  String AnalysisComment(String sb)
    {
        String[]sub=sb.split("\n");
        int begin=sub[0].lastIndexOf("from")+5;
        int end=sub[0].indexOf("by");
        String linksource=sub[0].substring(begin,end);
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
    protected  boolean getTextOfTag(StringBuffer sb, Node node,String element)
    {

          if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
            //  if (node.getNodeName().equalsIgnoreCase("object")) {
                if(node.hasAttributes()){             
                Node para=node.getAttributes().item(0);
                if(para.getNodeName().equalsIgnoreCase("class") && para.getNodeValue().equalsIgnoreCase("gen"))
                {
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
              if(getTextOfTag(sb, children.item(i), element))
            //if (getObjects(sb, children.item(i), element)) {
              return true;
           //}
          }
        }
        return false;
      }

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
