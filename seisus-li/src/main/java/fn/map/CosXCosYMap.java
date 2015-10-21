package fn.map;

import fn.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

/**
 * @author moroz
 */
public class CosXCosYMap extends BaseMap {

    private TreeMap<Double, Double[]> data;

    Double maxX = Double.MIN_VALUE,
            maxY = Double.MIN_VALUE,
            maxZ = Double.MIN_VALUE;

    private static final String PRINTED_NAME = "cxcy";

    public CosXCosYMap() {
        data = new TreeMap<>();
        print = Utils.getBoolProperty(PRINTED_NAME + ".print");
    }

    @Override
    public void map(TreeMap<Double, Double[]> data) {

        for (Double frecuency : data.keySet()) {
            Double[] vectors = data.get(frecuency);
            if (vectors != null) {
                maxX = vectors[0] > maxX ? vectors[0] : maxX;
                maxY = vectors[1] > maxY ? vectors[1] : maxY;
                maxZ = vectors[2] > maxZ ? vectors[2] : maxZ;
            }
        }

        this.data = data;

    }

    @Override
    public TreeMap<Double, Double[]> getData() {
        return this.data;
    }

    @Override
    public String getPrintedName() {
        return "cxcy";
    }

    @Override
    public String getDescription() {
        return "calculate cos(X)*cos(X) and cos(Y)*cos(Y)";
    }

    @Override
    public void writeBase(String name) throws IOException {

        try (BufferedWriter w = new BufferedWriter(new FileWriter(new File(name)))) {

            Double cosXcosX = maxX / (maxX + maxY + maxZ);
            Double cosYcosY = maxY / (maxX + maxY + maxZ);
           

            w.append("cos(X)*cos(X)=")
                    .append(getFormatNumber(cosXcosX))
                    .append(System.lineSeparator());

            w.append("cos(Y)*cos(Y)=")
                    .append(getFormatNumber(cosYcosY))
                    .append(System.lineSeparator());
        }

    }

}
