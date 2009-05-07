<<<<<<< .mine
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.indexer;


import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.store.RAMDirectory;

/**
 *
 * @author Administrator
 */

public class SetupParameters {
    private Directory dir;
    private IndexReader reader;
    private IndexWriter writer;
    private Analyzer analyzer=new StopAnalyzer();
    private float boost = 1.0f;
    private int mergeFactor;//default is 10
    private int maxMergeDocs;//default is Integer.MAX_VALUE
    private int minMergeDocs;//default is 10


    public SetupParameters() {
        this.setMergeFactor(1000);
        
    }
     public SetupParameters(Directory d,IndexReader r,IndexWriter w,Analyzer a) {
        
        this.setDir(d);
        this.setAnalyzer(a);
        this.setReader(r);
        this.setWriter(w);
    
     }

    /**
     * @return the dir
     */
    public Directory getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(Directory dir) {
        this.dir = dir;
    }

    /**
     * @return the reader
     */
    public IndexReader getReader() {
        return reader;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(IndexReader reader) {
        this.reader = reader;
    }

    /**
     * @return the writer
     */
    public IndexWriter getWriter() {
        return writer;
    }

    /**
     * @param writer the writer to set
     */
    public void setWriter(IndexWriter writer) {
        this.writer = writer;
    }

    /**
     * @return the boost
     */
    public float getBoost() {
        return boost;
    }

    /**
     * @param boost the boost to set
     */
    public void setBoost(float boost) {
        this.boost = boost;
    }

    /**
     * @return the mergeFactor
     */
    public int getMergeFactor() {
        return mergeFactor;
    }

    /**
     * @param mergeFactor the mergeFactor to set
     */
    public void setMergeFactor(int mergeFactor) {
        this.mergeFactor = mergeFactor;
    }

    /**
     * @return the maxMergeDocs
     */
    public int getMaxMergeDocs() {
        return maxMergeDocs;
    }

    /**
     * @param maxMergeDocs the maxMergeDocs to set
     */
    public void setMaxMergeDocs(int maxMergeDocs) {
        this.maxMergeDocs = maxMergeDocs;
    }

    /**
     * @return the minMergeDocs
     */
    public int getMinMergeDocs() {
        return minMergeDocs;
    }

    /**
     * @param minMergeDocs the minMergeDocs to set
     */
    public void setMinMergeDocs(int minMergeDocs) {
        this.minMergeDocs = minMergeDocs;
    }

    /**
     * @return the analyzer
     */
    public Analyzer getAnalyzer() {
        return analyzer;
    }

    /**
     * @param analyzer the analyzer to set
     */
    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }


}
=======
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.indexer;


import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;

/**
 *
 * @author Administrator
 */

public class SetupParameters {
    private Directory dir;
    private IndexReader reader;
    private IndexWriter writer;
    private Analyzer analyzer=new SimpleAnalyzer();
    private float boost = 1.0f;
    private int mergeFactor;//default is 10
    private int maxMergeDocs;//default is Integer.MAX_VALUE
    private int minMergeDocs;//default is 10


    public SetupParameters() {
        this.setMergeFactor(1000);
    }
     public SetupParameters(Directory d,IndexReader r,IndexWriter w,Analyzer a) {
        
        this.setDir(d);
        this.setAnalyzer(a);
        this.setReader(r);
        this.setWriter(w);
    
     }

    /**
     * @return the dir
     */
    public Directory getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(Directory dir) {
        this.dir = dir;
    }

    /**
     * @return the reader
     */
    public IndexReader getReader() {
        return reader;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(IndexReader reader) {
        this.reader = reader;
    }

    /**
     * @return the writer
     */
    public IndexWriter getWriter() {
        return writer;
    }

    /**
     * @param writer the writer to set
     */
    public void setWriter(IndexWriter writer) {
        this.writer = writer;
    }

    /**
     * @return the boost
     */
    public float getBoost() {
        return boost;
    }

    /**
     * @param boost the boost to set
     */
    public void setBoost(float boost) {
        this.boost = boost;
    }

    /**
     * @return the mergeFactor
     */
    public int getMergeFactor() {
        return mergeFactor;
    }

    /**
     * @param mergeFactor the mergeFactor to set
     */
    public void setMergeFactor(int mergeFactor) {
        this.mergeFactor = mergeFactor;
    }

    /**
     * @return the maxMergeDocs
     */
    public int getMaxMergeDocs() {
        return maxMergeDocs;
    }

    /**
     * @param maxMergeDocs the maxMergeDocs to set
     */
    public void setMaxMergeDocs(int maxMergeDocs) {
        this.maxMergeDocs = maxMergeDocs;
    }

    /**
     * @return the minMergeDocs
     */
    public int getMinMergeDocs() {
        return minMergeDocs;
    }

    /**
     * @param minMergeDocs the minMergeDocs to set
     */
    public void setMinMergeDocs(int minMergeDocs) {
        this.minMergeDocs = minMergeDocs;
    }

    /**
     * @return the analyzer
     */
    public Analyzer getAnalyzer() {
        return analyzer;
    }

    /**
     * @param analyzer the analyzer to set
     */
    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }


}
>>>>>>> .r13
