
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lucene.search.media.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;

/**
 *
 * @author Administrator
 */
public class GetAnalyzer {

    public static Analyzer getAnalysis()
    {
        Analyzer an=new StopAnalyzer();
        return an;
    }

}

