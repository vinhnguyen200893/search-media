package lucene.search.media.link;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import lucene.search.media.parsehtml.Outlink;
/**
 *
 * @author Administrator
 */
public class FilterLinks {
    private String[] Video=new String[]{"wmv","avi","flv"};
    private String[] Audio=new String[]{"mp3","wma"};
     private String[] Protocol=new String[]{"http:","ftp:","https:","rtmp"};
     private String[] Html=new String[]{"html","htm","mht"};
    /*
     * filter all links that like same
     * to get only links that are different
     */
    public ArrayList filter(ArrayList outlinks)
    {
        ArrayList l=new ArrayList();
        boolean flag=false;
        int j=0;
        for(int i=0;i<outlinks.size()-1;i++)
        {
             for(j=i+1;j<outlinks.size();j++)
            {
                if(outlinks.get(i).equals(outlinks.get(j)))
                {
                    flag=true;
                    break;
                }
            }
             if(!flag) l.add(outlinks.get(i));
             flag=false;
        }
        //l.add(outlinks.get(j));
        return l;
    }

      public ArrayList filterMediaLinks(ArrayList outlinks)
    {
        ArrayList l=new ArrayList();
        l=filter(outlinks);
        Outlink []links = (Outlink[])l.toArray(new Outlink[l.size()]);
            if(links!=null&&links.length>0){
                l.clear();
                for(int i=0;i<links.length;i++){
                if(checkProtocol(links[i].getToUrl())){//if a url not a anchor
                    if(checkMediaLink(links[i].getToUrl())){//if url is a media file
                    l.add(getMediaLink(links[i].getToUrl()));//get real file media
                    //doc.add(Field.Keyword("url",links[i].getToUrl()));//get real file media

                    }
                }
            }
            }
        //l.add(outlinks.get(j));
        return l;
    }

    /*
     * return String that link to real file media
     * such as :MP3,WMV,WMA,...
     */
    public String getMediaLink(String link)
    {
        if(link.contains("?"))
           link=link.substring(0,link.indexOf("?"));
        //remove all character is path (../ )
        if(link.contains("../"))
        {
            //get all ../
            int i=link.lastIndexOf("../");
            if(i!=-1)
                link=link.substring(i+3);

        }

        //if links is not begin with http
        if(link.startsWith("http://"))
        {
            return "";
        }
        return link;
    }
    /*
     * check a link is media file or not
     */ 
    public boolean checkVideoLink(String link)
    {
        for(int i=0;i<this.Video.length;i++){
            if(link.endsWith(this.Video[i]))
                return true;

        }
        return false;
    }
    public boolean checkAudioLink(String link)
    {
        for(int i=0;i<this.Audio.length;i++){
            if(link.endsWith(this.Audio[i]))
                return true;

        }
        return false;
    }

    public boolean checkMediaLink(String link)
    {
        if(checkAudioLink(link)) return true;
        else if(checkVideoLink(link)) return true;
        //else if(checkHtmlLink(link)) return true;
        else return false;
    }
    /*
     * check a link is media file or not
     */
    public boolean checkProtocol(String link)
    {
        for(int i=0;i<this.Protocol.length;i++){
            if(link.startsWith(this.Protocol[i]))
                return true;

        }     
        return false;
    }
    /*
     * check a link is html file or not
     */
    public boolean checkHtmlLink(String link)
    {
        for(int i=0;i<this.Html.length;i++){
            if(link.startsWith(this.Html[i]))
                return true;

        }
        return false;
    }
    /*
     * replace any characters is not a link
  
     */
    public String replaceCharacters (String link){

        if(link.contains("../"))
        {
            //get all ../
            int i=link.lastIndexOf("../");
            if(i!=-1)
                link=link.substring(i);

        }
       return link;
    }
}
