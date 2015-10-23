package fn.map;

import java.util.LinkedHashSet;

/**
 * @author moroz
 */
public class MapFactory {

    public static LinkedHashSet<Imap> getMaps() {

        LinkedHashSet<Imap> result = new LinkedHashSet();
        result.add(new BaseMap()); //печать без преобразования
        result.add(new RadGravAccelMap()); //частота в радианы/сек, векторы в доли от 'g'
        result.add(new MaxVector2ColMap());//найти max vector в колонках и напечатать эту колоку наряду с частотой
        result.add(new CosXCosYMap()); // посчитать Ymax/(Xmax+Ymax+Zmax) и Xmax/(Xmax+Ymax+Zmax) и sqrt от этих значений
        return result;
    }

}
