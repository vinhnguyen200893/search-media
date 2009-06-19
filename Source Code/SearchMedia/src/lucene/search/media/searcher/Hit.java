/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.searcher;

import org.apache.lucene.search.Hits;

/**
 *
 * @author Thanh Nga
 */
public class Hit {
    private long _time = 0;
    private Hits _hits;
    private long _totaldocs=0;

    public Hit(){}
    /**
     * @return the _time
     */
    public long getTime() {
        return _time;
    }

    /**
     * @param time the _time to set
     */
    public void setTime(long time) {
        this._time = time;
    }

    /**
     * @return the _hits
     */
    public Hits getHits() {
        return _hits;
    }

    /**
     * @param hits the _hits to set
     */
    public void setHits(Hits hits) {
        this._hits = hits;
    }

    /**
     * @return the _totaldocs
     */
    public long getTotaldocs() {
        return _totaldocs;
    }

    /**
     * @param totaldocs the _totaldocs to set
     */
    public void setTotaldocs(long totaldocs) {
        this._totaldocs = totaldocs;
    }


}
