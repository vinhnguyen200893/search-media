/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.objects;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Thanh Nga
 */
public class QueryObject {
    private Map _query;
    public QueryObject()
    {
        this._query=new HashMap();
    }

    public void set_Map(String name,String value)
    {
        this._query.put(name, value);

    }

    /**
     * @return the _query
     */
    public Map getQuery() {
        return _query;
    }

    /**
     * @param query the _query to set
     */
    public void setQuery(Map query) {
        this._query = query;
    }

}
