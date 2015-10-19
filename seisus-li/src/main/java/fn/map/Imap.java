package fn.map;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author moroz
 */
public interface Imap {
    
    void map(TreeMap<Double, Double[]> data);
    
    TreeMap<Double, Double[]> getData();
    
    String getPrintedName();
    String getDescription();
    
    public void write(String name) throws IOException;
    
}
