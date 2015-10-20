package fn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author moroz
 */
public class Utils {

    public static final String APP_CFG_NAME = "seisus-map.cfg";

    private static Utils instance;

    private Properties data = new Properties();

    private Utils() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(APP_CFG_NAME);

        if (is != null) {
            try {
                data.load(is);
            } catch (IOException ex) {

            }
        }

        try {
            data.load(new FileInputStream(APP_CFG_NAME));
        } catch (IOException ex) {
            
        }

    }

    public static Utils getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }

    }

    public static String getProperty(String name) {
        return getInstance().data.getProperty(name);
    }

    public static boolean getBoolProperty(String name) {
        String val = getProperty(name);
        boolean result = val != null ? val.matches("([Yy]es|[Tt]rue)") : false;
        return result;
    }

    static {
        instance = new Utils();
    }

}
