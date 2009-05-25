/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.parsehtml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Administrator
 */
public class HtmlHandler {
//get element at specific position
    int step=1;
    int step_object=1;
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
                        continue;
                    }
                    /*
                    Â 194
                    Ấ 7844
                    Ầ 7846
                    Ẩ 7848
                    Ẫ 7850
                    Ậ 7852
                    */
                    if (c == 194 || c == 7844 || c == 7846 || c == 7848 || c == 7850 || c==7852)
                    {
                        dl += 'A';
                        continue;
                    }
                    /*
                    Ă 258
                    Ắ 7854
                    Ằ 7856
                    Ẳ 7858
                    Ẵ 7860
                    Ặ 7862
                    */
                    if (c == 258 || c == 7854 || c == 7856 || c == 7858 || c == 7860 || c == 7862)
                    {
                        dl += 'A';
                        continue;
                    }
                    /*
                    E 69
                    È 200
                    É 201
                    Ẹ 7864
                    Ẻ 7866
                    Ẽ 7868
                    */
                    if (c == 200 || c == 201 || c == 7864 || c == 7866 || c == 7868 )
                    {
                        dl += 'E';
                        continue;
                    }

                    /*
                    Ê 202
                    Ế 7870
                    Ề 7872
                    Ể 7874 ể 7875
                    Ễ 7876 ễ 7877
                    Ệ 7878 ệ 7879
                    */
                    if (c == 202 || c == 7870 || c == 7872 || c == 7874 || c == 7876 || c == 7878)
                    {
                        dl += 'E';
                        continue;
                    }
                    /*
                    I 73
                    Ì 204
                    Í 205
                    Ĩ 296
                    Ỉ 7880
                    Ị 7882
                    */
                    if (c == 204 || c == 205 || c == 296 || c == 7880 ||c==7882 )
                    {
                        dl += 'I';
                        continue;
                    }
                    /*
                    O 79
                    Ò 210
                    Ó 211
                    Õ 213
                    Ọ 7884
                    Ỏ 7886
                    */
                    if (c == 210 || c == 211 || c == 213 || c == 7884 || c == 7886 )
                    {
                        dl += 'O';
                        continue;
                    }
                    /*
                    Ô 212
                    Ố 7888
                    Ồ 7890
                    Ổ 7892
                    Ỗ 7894
                    Ộ 7896
                    */
                    if (c == 212 || c == 7888 || c == 7890 || c == 7892 || c == 7894 ||c==7896)
                    {
                        dl += 'O';
                        continue;
                    }
                    /*
                    Ơ 416
                    Ớ 7898
                    Ờ 7900
                    Ở 7902
                    Ỡ 7904
                    Ợ 7906
                    */
                    if (c == 416 || c == 7898 || c == 7900 || c == 7902 || c == 7904 ||c==7906)
                    {
                        dl += 'O';
                        continue;
                    }
                    /*
                    U 85
                    Ù 217
                    Ú 218
                    Ũ 360
                    Ụ 7908
                    Ủ 7910
                    */
                    if (c == 217 || c == 218 || c == 360 || c == 7908 || c == 7910)
                    {
                        dl += 'U';
                        continue;
                    }
                    /*
                    Ư 431
                    Ứ 7912
                    Ừ 7914
                    Ử 7916
                    Ữ 7918
                    Ự 7920
                    */
                    if (c == 431 || c == 7912 || c == 7914 || c == 7916 || c == 7918 || c== 7920)
                    {
                        dl += 'U';
                        continue;
                    }
                    /*
                    Y 89
                    Ý 221
                    Ỳ 7922
                    Ỵ 7924
                    Ỷ 7926
                    Ỹ 7928
                    */
                    if (c == 221 || c == 7922 || c == 7924 || c == 7926 || c == 7928)
                    {
                        dl += 'Y';
                        continue;
                    }
                    /*
                    Đ 208 272
                    */
                     if (c == 208 || c == 272)
                    {
                        dl += 'D';
                        continue;
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
                return dl;
            }
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
            if (getText(sb, children.item(i), element,position)) {
              return true;
            }
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
    protected  void getObjects(StringBuffer sb, Node node)
    {
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
    protected  boolean getObjects(StringBuffer sb, Node node,String element,int pos)
    {

          if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
            //  if (node.getNodeName().equalsIgnoreCase("object")) {
             if(step_object==pos){
            getObjects(sb, node);
            sb.append("\n");
            return true;
            }
             step_object++;
          }
        }
        NodeList children = node.getChildNodes();
        if (children != null) {
          int len = children.getLength();
          for (int i = 0; i < len; i++) {
              getObjects(sb, children.item(i), element,pos);
    //        if (getObjects(sb, children.item(i), element)) {
    //          return true;
            //}
          }
        }
        return false;
    }
	
	 protected  boolean getTableNode(StringBuffer sb, Node node,String element,int pos,ArrayList childtags,ArrayList childvalue)
    {

          if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
				if(step_object==pos){
				getTableNode(sb, node);
				return true;
				}
				 step_object++;
			}
		}
        NodeList children = node.getChildNodes();
        if (children != null) {
          int len = children.getLength();
          for (int i = 0; i < len; i++) {
              getObjects(sb, children.item(i), element,pos);
    //        if (getObjects(sb, children.item(i), element)) {
    //          return true;
            //}
          }
        }
        return false;
    }
	 protected  boolean getTableNode(StringBuffer sb, Node node,ArrayList childtag,ArrayList childvalue)
    {
		
		int stepchild=0;int numchild=childtag.size();int posvalue=0; 		          		
        NodeList children = node.getChildNodes();
        if (children != null)
		{
          int len = children.getLength();
          for (int i = 0; i < len; i++) {		  
            if (children.item[i].getNodeName().equalsIgnoreCase((childtag.item[stepchild].toString()))) {
				if(stepchild==(int)childvalue.item[posvalue].toString()){
				sb.append(children.item[i].getValue()+"\n");
				//getNode(sb,children.item[i]);
				//return true;
				}
				stepchild++;
			}
			}
		}
              
    }
	
	 protected  boolean getTextOfTable(StringBuffer sb, Node node,String element,int pos)
    {

          if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
				if(step_object==pos){
				NodeList children = node.getChildNodes();
				if (children != null) {
				  int len = children.getLength();
				  for (int i = 0; i < len; i++) {
						getText(sb,children.item[i]);
				return true;
				}
				 step_object++;
			 }
			}
		}
	    NodeList children = node.getChildNodes();
        if (children != null) {
          int len = children.getLength();
          for (int i = 0; i < len; i++) {
              getObjects(sb, children.item(i), element,pos);
    //        if (getObjects(sb, children.item(i), element)) {
    //          return true;
            //}
          }
        }
        return false;
    }
	 protected  boolean getById(StringBuffer sb, Node node,String element,String id)
    {

          if (node.getNodeType() == Node.ELEMENT_NODE) {
            if (element.equalsIgnoreCase(node.getNodeName())) {
				if(node.getParameters.item[0].getNodeName.equalsIgnoreCase("id")){
					getText(sb,children.item[i]);
					return true;
				}			
		
			}
			}
		
	    NodeList children = node.getChildNodes();
        if (children != null) {
          int len = children.getLength();
          for (int i = 0; i < len; i++) {
              getObjects(sb, children.item(i), element,pos);
    //        if (getObjects(sb, children.item(i), element)) {
    //          return true;
            //}
          }
        }
        return false;
    }

}
