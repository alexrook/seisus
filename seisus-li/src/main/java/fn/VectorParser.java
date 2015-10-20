package fn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;

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

        NumberFormat numberForm = NumberFormat.getInstance();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = reader.readLine();
            int i = 1;
            while (line != null) {
                if (checkLine(line)) {
                    String[] fields = line.split("\\s");
                    try {
                        Double frecuency = numberForm.parse(fields[0]).doubleValue();
                        switch (vt) {

                            case X:
                                w.putX(frecuency, numberForm.parse(fields[2]).doubleValue()); //во всех файлах читаем 3 столбец
                                break;
                            case Y:
                                w.putY(frecuency, numberForm.parse(fields[2]).doubleValue());
                                break;
                            case Z:
                                w.putZ(frecuency, numberForm.parse(fields[2]).doubleValue());
                                break;
                        }
                    } catch (ParseException e) {
                        throw new IOException("File: " + file.getName() + ", line: " + i + ", one of fields do not contain number");
                    }
                }
                i++;
                line = reader.readLine();
            }

        }

    }

    private boolean checkLine(String line) {
        return line.matches("(([0-9,\\.]*)\\s){3}([0-9,\\.]*)");
    }

}
