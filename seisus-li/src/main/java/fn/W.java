package fn;

import java.util.TreeMap;

/**
 * @author moroz
 */
public class W {

    private static W instance;

    private TreeMap<Double, Double[]> data;

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

    public TreeMap<Double, Double[]> getData() {
        return data;
    }

    public void setData(TreeMap<Double, Double[]> data) {
        this.data = data;
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

   

}
