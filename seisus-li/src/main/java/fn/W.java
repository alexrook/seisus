package fn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

/**
 * @author moroz
 */
public class W {

    private static W instance;

    private final TreeMap<Double, Double[]> data;

    private W() {
        data = new TreeMap<>();
    }

    public static W getInstace() {
        if (instance != null) {
            return instance;
        } else {
            instance = new W();
            return instance;
        }

    }

    public void putX(Double frecuency, Double x) {
        put(frecuency, x, 0);
    }

    public void putY(Double frecuency, Double y) {
        put(frecuency, y, 1);
    }

    public void putZ(Double frecuency, Double z) {
        put(frecuency, z, 2);
    }

    private void put(Double frecuency, Double val, int position) {
        Double[] vectors = data.get(frecuency);

        if (vectors == null) {
            vectors = new Double[3];
            //?
            vectors[0] = null;
            vectors[1] = null;
            vectors[2] = null;

            data.put(frecuency, vectors);
        }

        vectors[position] = val;
    }

    public void write() throws IOException {

        StringBuilder errors;
        try (BufferedWriter w = new BufferedWriter(new FileWriter(new File("w.txt")))) {
            errors = new StringBuilder();
            int i = 1;
            for (Double frecuency : data.keySet()) {
                Double[] vectors = data.get(frecuency);
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
