/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lucene.search.media.indexer;

import java.io.File;
import java.util.Date;
import lucene.search.media.system.Directories;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author Thanh Nga
 */
public class Merger {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            usage();
            System.exit(0);
        }

        try {

            long start = new Date().getTime();
            long numDocs = 0;

            Directories d = new Directories();
            String[] indexes = d.getListDirectories(new File(args[0]));
            Directory[] dirs = new Directory[indexes.length];
            for (int i = 0; i < indexes.length; i++) {
                System.out.println("Adding " + indexes[i]);
                dirs[i] =  FSDirectory.getDirectory(args[0]+"/"+indexes[i], false);
            }
            IndexWriter writer = new IndexWriter(new File(args[1]), null, true);
            writer.addIndexes(dirs);
            writer.close();


            IndexReader reader =IndexReader.open(new File(args[1]));
            numDocs+=reader.numDocs();
            reader.close();
            
            long end = new Date().getTime();
            System.out.println("Documents merged: " + numDocs);
            System.out.println("Total time: " + (end - start) + " ms");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*
     * index many options
     * if agrs.length =3 && args[2]=0 : index many directories in a directory
     * if agrs.length =3 && args[2]=1 : index one directory
     */
    private static void usage() {
        System.err.println("USAGE: java " + Indexer.class.getName() + " 1.Merge index directories to one directory:/path/to/directory /path/to/merger directory ");


    }
}
