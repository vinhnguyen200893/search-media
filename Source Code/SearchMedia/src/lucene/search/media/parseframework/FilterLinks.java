package lucene.search.media.parseframework;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class FilterLinks {
    private String[] Video=new String[]{"wmv","avi","flv"};
    private String[] Audio=new String[]{"mp3","wma"};
     private String[] Protocol=new String[]{"http:","ftp:","https:","rtmp"};
    /*
     * filter all links that like same
     * to get only links that are different
     */
    public ArrayList filter(ArrayList outlinks)
    {
        ArrayList l=new ArrayList();
        boolean flag=false;
        for(int i=0;i<outlinks.size()-1;i++)
        {
             for(int j=i+1;j<outlinks.size();j++)
            {
                if(outlinks.get(i).equals(outlinks.get(j)))
                {
                    flag=true;
                    break;
                }
            }
             if(!flag) l.add(outlinks.get(i));
        }
        return l;
    }
    /*
     * return String that link to real file media
     * such as :MP3,WMV,WMA,...
     */
    public String getMediaLink(String link)
    {
        if(link.contains("?"))
            return link.substring(0,link.indexOf("?")-1);
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
}
