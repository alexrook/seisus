package fn.map;

import fn.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.TreeMap;

/**
 *
 * @author moroz
 */
public class BaseMap implements Imap {

    private TreeMap<Double, Double[]> data;

    private static final String PRINTED_NAME = "w";

    protected boolean print = true;

    public void setPrint(boolean print) {
        this.print = print;
    }

    public BaseMap() {
        print = Utils.getBoolProperty(PRINTED_NAME + ".print");
    }

    public boolean isPrint() {
        return print;
    }

    @Override
    public void map(TreeMap<Double, Double[]> data) {
        this.data = data;
    }

    @Override
    public TreeMap<Double, Double[]> getData() {
        return this.data;
    }

    @Override
    public String getPrintedName() {
        return PRINTED_NAME;
    }

    @Override
    public String getDescription() {
        return "no translate";
    }

    protected String createName(String name) {
        return (name != null) && (!name.equalsIgnoreCase("w")) ? name + "-w.txt" : getPrintedName() + ".txt";
    }

    protected void writeBase(String name) throws IOException {
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
                w.append(getFormatNumber(frecuency));
                w.append("\t");
                w.append(getFormatNumber(vectors[0]));
                w.append("\t");
                w.append(getFormatNumber(vectors[1]));
                w.append("\t");
                w.append(getFormatNumber(vectors[2]));
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

    public String getFormatNumber(Double num) {
        NumberFormat numberFormUS = NumberFormat.getInstance(Locale.US);

        try {
            String numFract = Utils.getHierProperty(getPrintedName() + ".print.maxfd");
            if (numFract != null) {
                numberFormUS.setMaximumFractionDigits(Integer.parseInt(numFract));
            }
        } catch (NumberFormatException e) {
        }

        return numberFormUS.format(num);

    }

    @Override
    public void write(String name) throws IOException {
        name = createName(name);
        if (isPrint()) {
            writeBase(name);
        }
    }

}
