package fn.map;

import java.util.LinkedHashSet;

/**
 * @author moroz
 */
public class MapFactory {

    public static LinkedHashSet<Imap> getMaps() {

        LinkedHashSet<Imap> result = new LinkedHashSet();
        result.add(new BaseMap()); //печать без преобразования
        result.add(new RadGravAccelMap()); //частота в радианы.сек, векторы в доли от 'g'
        result.add(new MaxVector2ColMap());
        return result;
    }

}
