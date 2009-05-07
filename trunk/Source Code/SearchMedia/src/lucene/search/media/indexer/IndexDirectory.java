/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.indexer;

import java.io.File;
import java.io.IOException;
import lucene.search.media.parseframework.FileHandlerException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author Administrator
 * index one or more directory follow admin's option
 */
public class IndexDirectory {
    /*
     * index specific directory
     * @param directory : name of source to index
     * @param index : name of directory to store indexed data
     * @param Pa : SetupParameters object
     * SetupParameters
     */
 private static long indexDirectory(String directory,String index, SetupParameters Pa, FileIndexer indexer) throws FileHandlerException, IOException {
            long sumDocs=0;
            Directory di = FSDirectory.getDirectory(new File(index), true);
            Pa.setDir(di);
            Pa.setWriter(new IndexWriter(Pa.getDir(), Pa.getAnalyzer(), true));
            indexer.index(Pa.getWriter(), new File(directory));
            Pa.getWriter().optimize();
            Pa.getWriter().close();
            IndexReader reader = Pa.getReader().open(Pa.getDir());
            sumDocs+=reader.numDocs();
            reader.close();
        return sumDocs;
    }
}
