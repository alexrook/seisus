package fn.map;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author moroz
 *
 * перевод частоты в радианы значений векторов - в доли ускорения свободного
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
            Double[] newvec = new Double[3];
            int i = 0;
            if (vectors != null) {
                for (Double vector : vectors) {
                    newvec[i] = vector / 9.81;
                    i++;
                }
            }
            this.data.put(frecuency * 6.28, newvec);
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
        name = name != null ? name  : getPrintedName();
        super.write(name);
    }

}
