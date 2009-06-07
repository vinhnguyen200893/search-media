/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.objects;

/**
 *
 * @author Administrator
 */
public class MediaObject {
    private String _songvn,_songen;
    private String _singervn,_singeren;
    private String _albumvn,_albumen;
    private String _lyric;
    private String _linkobject;
    private String _linksource;
    private String _linkmedia;
    private String _datemodified;
    private String _service;
//    private String _artist;
//    private String _size;
//    private String _length;
//    private String _type; //mp3,wma.video...
    

    public MediaObject()
    {
        this._albumen="";
        this._albumvn="";
         this._singeren="";
        this._singervn="";
        this._linkmedia="";
        this._linkobject="";
        this._lyric="";
        this._songen="";
        this._songvn="";
        this._linksource="";
        this._datemodified="";
        this._service="";

//        this._type="";
//        this._artist="";
//        this._length="";
//        this._size="";
        

    }

    /**
     * @return the _songvn
     */
    public String getSongvn() {
        return _songvn;
    }

    /**
     * @param songvn the _songvn to set
     */
    public void setSongvn(String songvn) {
        this._songvn = songvn;
    }

    /**
     * @return the _songen
     */
    public String getSongen() {
        return _songen;
    }

    /**
     * @param songen the _songen to set
     */
    public void setSongen(String songen) {
        this._songen = songen;
    }

    /**
     * @return the _singervn
     */
    public String getSingervn() {
        return _singervn;
    }

    /**
     * @param singervn the _singervn to set
     */
    public void setSingervn(String singervn) {
        this._singervn = singervn;
    }

    /**
     * @return the _singeren
     */
    public String getSingeren() {
        return _singeren;
    }

    /**
     * @param singeren the _singeren to set
     */
    public void setSingeren(String singeren) {
        this._singeren = singeren;
    }

    /**
     * @return the _albumvn
     */
    public String getAlbumvn() {
        return _albumvn;
    }

    /**
     * @param albumvn the _albumvn to set
     */
    public void setAlbumvn(String albumvn) {
        this._albumvn = albumvn;
    }

    /**
     * @return the _albumen
     */
    public String getAlbumen() {
        return _albumen;
    }

    /**
     * @param albumen the _albumen to set
     */
    public void setAlbumen(String albumen) {
        this._albumen = albumen;
    }

    /**
     * @return the _lyric
     */
    public String getLyric() {
        return _lyric;
    }

    /**
     * @param lyric the _lyric to set
     */
    public void setLyric(String lyric) {
        this._lyric = lyric;
    }

    /**
     * @return the _linkobject
     */
    public String getLinkobject() {
        return _linkobject;
    }

    /**
     * @param linkobject the _linkobject to set
     */
    public void setLinkobject(String linkobject) {
        this._linkobject = linkobject;
    }

    /**
     * @return the _linksource
     */
    public String getLinksource() {
        return _linksource;
    }

    /**
     * @param linksource the _linksource to set
     */
    public void setLinksource(String linksource) {
        this._linksource = linksource;
    }

    /**
     * @return the _linkmedia
     */
    public String getLinkmedia() {
        return _linkmedia;
    }

    /**
     * @param linkmedia the _linkmedia to set
     */
    public void setLinkmedia(String linkmedia) {
        this._linkmedia = linkmedia;
    }

    /**
     * @return the _datemodified
     */
    public String getDatemodified() {
        return _datemodified;
    }

    /**
     * @param datemodified the _datemodified to set
     */
    public void setDatemodified(String datemodified) {
        this._datemodified = datemodified;
    }

    /**
     * @return the _service
     */
    public String getService() {
        return _service;
    }

    /**
     * @param service the _service to set
     */
    public void setService(String service) {
        this._service = service;
    }

  
    

}
