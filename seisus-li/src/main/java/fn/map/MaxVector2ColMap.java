package fn.map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

/**
 * @author moroz
 */
public class MaxVector2ColMap implements Imap {

    private final TreeMap<Double, Double[]> data;

    private int maxVectorCol = 0;
    
    
    
    public MaxVector2ColMap(){
         data = new TreeMap<>();
    }

    @Override
    public void map(TreeMap<Double, Double[]> data) {
        for (Double frecuency : data.keySet()) {

            Double[] vectors = data.get(frecuency);
            if (vectors != null) {
                int i = 0;
                Double old = Double.MIN_VALUE;
                for (Double vector : vectors) {
                    if (vector > old) {
                        this.maxVectorCol = i;
                        old = vector;
                    }
                    i++;
                }
            }

            this.data.put(frecuency, vectors);
        }
    }

    @Override
    public TreeMap<Double, Double[]> getData() {
        return this.data;
    }

    @Override
    public String getPrintedName() {
        return "mv2c";
    }

    @Override
    public String getDescription() {
        return "find max vector and prints two columns: frecuency and column with max vector";
    }

    @Override
    public void write(String name) throws IOException {
        name = name != null ? name + "-w.txt" : "w.txt";
        StringBuilder errors;
        try (BufferedWriter w = new BufferedWriter(new FileWriter(new File(name)))) {
            errors = new StringBuilder();
            int i = 1;
            for (Double frecuency : getData().keySet()) {
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
                w.append(vectors[this.maxVectorCol].toString());
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
