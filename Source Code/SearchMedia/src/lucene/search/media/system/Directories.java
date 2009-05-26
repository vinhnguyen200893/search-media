/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.system;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Directories {
    public  String[] getListDirectories(File f)
    {
        ArrayList l=new ArrayList();
         String[]dirs= f.list();

         for(int i=0;i<dirs.length;i++)
         {
             File file = new File(f,dirs[i]);
             if(file.isDirectory())
                 l.add(dirs[i]);
         }
         return (String[])l.toArray(new String[l.size()]);
    }
}
