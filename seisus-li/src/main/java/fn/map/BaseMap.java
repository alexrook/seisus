package fn.map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

/**
 *
 * @author moroz
 */
public class BaseMap implements Imap{
    
    private TreeMap<Double, Double[]> data;
    
    
    public BaseMap(){
        
    }
    
    @Override
    public void map(TreeMap<Double, Double[]> data) {
        this.data=data;
    }

    @Override
    public TreeMap<Double, Double[]> getData() {
        return this.data;
    }

    @Override
    public String getPrintedName() {
        return null;
    }

    @Override
    public String getDescription() {
        return "no translate";
    }

    @Override
    public void write(String name) throws IOException {
      
        name=name!=null?name+"-w.txt":"w.txt";
        
        StringBuilder errors;
        
        try (BufferedWriter w = new BufferedWriter(new FileWriter(new File(name)))) {
            errors = new StringBuilder();
            int i = 1;
            for (Double frecuency :getData().keySet()) {
                Double[] vectors = getData().get(frecuency);
                for (Double v : vectors) {
                    if (v == null) {
                        errors.append("WARN ! frecuency:")
                                .append(frecuency)
                                .append(" contains empty values(line in file:")
                                .append(i)
                                .append(")\n");
                    }
                }
                w.append(frecuency.toString());
                w.append("\t");
                w.append(vectors[0].toString());
                w.append("\t");
                w.append(vectors[1].toString());
                w.append("\t");
                w.append(vectors[2].toString());
                w.append(System.lineSeparator());
                i++;
            }
        }

        if (errors.length() > 0) {
            try (FileWriter errorsf = new FileWriter("errors.txt")) {
                errorsf.write(errors.toString());
            }
        }

    }
    
}
