/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.indexer;



import java.io.IOException;
import java.util.ArrayList;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import lucene.search.media.objects.*;
/**
 *
 * @author Administrator
 */
public class Operators {

    /*
     * add new Document with new value array and new name  array
     */
    public Document addDocumentObject(MediaObject obj)
    {
        Document d=new Document();
        d.add(Field.Text("songen",obj.getSongen()));
        d.add(Field.Text("songvn",obj.getSongvn()));
        d.add(Field.Text("singeren",obj.getSingeren()));
        d.add(Field.Text("singervn",obj.getSingervn()));
        d.add(Field.Keyword("linksource",obj.getLinksource()));
        d.add(Field.Keyword("date",obj.getDatemodified()));
        d.add(Field.Keyword("linkmedia",obj.getLinkmedia()));
        d.add(Field.Keyword("service",obj.getService()));
        d.add(Field.Text("idservice",obj.getIdservice()));
        d.add(Field.UnIndexed("linkobject",obj.getLinkobject()));
        d.add(Field.Text("albumen",obj.getAlbumen()));
        d.add(Field.Text("albumvn",obj.getAlbumvn()));       
        d.add(Field.Text("lyric",obj.getLyric()));
        
        return d;
    }
   
    /*
     * add new Document with new value array and one name
     */ 
    public Document addDocument(String name, ArrayList value)
    {
        Document d=new Document();
        for(int i=0;i<value.size();i++)
            d.add(Field.Text(name, value.get(i).toString()));
        return d;
    }
    /*
     * remove Document at index
     */
    public boolean removeDocument(Directory d,IndexReader w,Term t)
    {
        boolean flag=false;
        try{
            w.open(d);
            w.delete(t);
            w.close();
            flag=true;
        }
        catch(IOException ex)
        {

            ex.printStackTrace();
        }
        finally{
            return flag;
        }
    }
     /*
     * update Document at index
     */
    public boolean updateDocument(Directory d,IndexReader r,IndexWriter w,Term t,Document doc)
    {
        boolean flag=false;
        try{
           removeDocument(d, r, t);
           w.addDocument(doc);
           w.optimize();
           w.close();
           flag=true;

        }
        catch(IOException ex)
        {

            ex.printStackTrace();
        }
        finally{
            return flag;
        }
    }

    /*
     * set boost value to a Document
     */
    public void setBoostDocument(Document d,float value)
    {
        d.setBoost(value);

    }
    /*
     * set boost value to a Document Field
     */
    public void setBoostField(Field f,float value)
    {
        f.setBoost(value);

    }
}
