package lucene.search.media.searcher;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Hits;
import org.apache.lucene.queryParser.QueryParser;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import lucene.search.media.objects.QueryObject;
import org.apache.lucene.analysis.*;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.RAMDirectory;

/**
 * This code was originally written for
 * Erik's Lucene intro java.net article
 */
public class Searcher {

    public Hit search(File indexDir, String field, String q)
            throws Exception {
        RAMDirectory ram = new RAMDirectory(indexDir);
        //Directory fsDir = FSDirectory.getDirectory(indexDir, false);
        IndexSearcher is = new IndexSearcher(ram);
        Query query = QueryParser.parse(q, field, new StopAnalyzer());

        long start = new Date().getTime();
        Hits hits = is.search(query);
        long end = new Date().getTime();

        Hit h = new Hit();
        h.setTotaldocs(hits.length());
        h.setTime(end - start);
        h.setHits(hits);
        return h;


    }

    public Hit searchAdvance(File indexDir, QueryObject q)
            throws Exception {

        Map map = q.getQuery();
        TermQuery term;
        BooleanQuery query = new BooleanQuery();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            // Get key
            Object key = it.next();
            // Iterate over the values in the map
            Object value = map.get(key);
            term = new TermQuery(new Term(key.toString(), value.toString()));
            query.add(term, true, false);

        }
        RAMDirectory ram = new RAMDirectory(indexDir);
        //Directory fsDir = FSDirectory.getDirectory(indexDir, false);
        IndexSearcher is = new IndexSearcher(ram);

        long start = new Date().getTime();
        Hits hits = is.search(query);
        long end = new Date().getTime();

        Hit h = new Hit();
        h.setTotaldocs(hits.length());
        h.setTime(end - start);
        h.setHits(hits);
        return h;


    }
}
