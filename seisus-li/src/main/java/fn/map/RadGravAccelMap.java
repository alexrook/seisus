package fn.map;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author moroz
 *
 * перевод частоты в радианы, значений векторов - в доли ускорения свободного
 * падения
 *
 */
public class RadGravAccelMap extends BaseMap {

    private final TreeMap<Double, Double[]> data;

    public RadGravAccelMap() {
        data = new TreeMap<>();
    }

    @Override
    public void map(TreeMap<Double, Double[]> data) {
        for (Double frecuency : data.keySet()) {

            Double[] vectors = data.get(frecuency);
            if (vectors != null) {
                for (int i = 0; i < vectors.length; i++) {
                    vectors[i] = vectors[i] / 9.81;
                }
            }
            this.data.put(frecuency * 6.28, vectors);
        }
    }

    @Override
    public TreeMap<Double, Double[]> getData() {
        return this.data;
    }

    @Override
    public String getPrintedName() {
        return "rgam";
    }

    @Override
    public String getDescription() {
        return "translated frecuency in radians, the values of the vectors - in a fraction of the gravitational acceleration";
    }

    @Override
    public void write(String name) throws IOException {
        name = name != null ? name : getPrintedName();
        super.write(name);
    }

}
