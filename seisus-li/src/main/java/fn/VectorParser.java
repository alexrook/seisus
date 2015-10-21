package fn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author moroz
 */
public class VectorParser {

    public enum VectorType {

        X, Y, Z
    }

    private VectorType vt;

    public void setVectorType(VectorType vt) {
        this.vt = vt;
    }

    public void parse(File file) throws IOException {

        W w = W.getInstace();

     //   NumberFormat numberForm = NumberFormat.getInstance();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = reader.readLine();
            int i = 1;
            while (line != null) {
                if (checkLine(line)) {
                    String[] fields = line.split("\\s");
                    try {
                        Double frecuency = parseNumber(fields[0]);
                        switch (vt) {

                            case X:
                                w.putX(frecuency, parseNumber(fields[2])); //во всех файлах читаем 3 столбец
                                break;
                            case Y:
                                w.putY(frecuency, parseNumber(fields[2]));
                                break;
                            case Z:
                                w.putZ(frecuency, parseNumber(fields[2]));
                                break;
                        }
                    } catch (ParseException e) {
                        throw new IOException("File: " + file.getName()
                                + ", line: "
                                + i
                                + ", one of fields do not contain number");
                    }
                }
                i++;
                line = reader.readLine();
            }

        }

    }

    private Double parseNumber(String val) throws ParseException {
        NumberFormat numberFormDef = NumberFormat.getInstance();
        NumberFormat numberFormUS = NumberFormat.getInstance(Locale.US);
        if (val.contains(".")) {
            return numberFormUS.parse(val).doubleValue();
        } else {
            return numberFormDef.parse(val).doubleValue();
        }
    }

    private boolean checkLine(String line) {
        return line.matches("(([0-9,\\.]*)\\s){3}([0-9,\\.]*)");
    }

}
