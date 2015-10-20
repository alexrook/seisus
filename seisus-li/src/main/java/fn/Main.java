package fn;

import fn.map.Imap;
import fn.map.MapFactory;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * @author moroz
 */
public class Main implements FilenameFilter {

    public static void main(String[] args) {

        Main m = new Main();

        if (args.length > 0) {
            if (args[0].toLowerCase().contains("v")) {
                m.showVersion();
                System.exit(0);
            }

            m.showUsage();

            System.exit(0);
        }

        File curDir = new File(".");
        File[] vectors = curDir.listFiles(m);

        if (vectors.length != 3) {
            m.showUsage();
            System.exit(1);
        }

        for (File file : vectors) {
            VectorParser parser = new VectorParser();
            String fileName = file.getName().toLowerCase();
            if (fileName.endsWith("x.txt")) {
                parser.setVectorType(VectorParser.VectorType.X);
            } else if (fileName.endsWith("y.txt")) {
                parser.setVectorType(VectorParser.VectorType.Y);
            } else if (fileName.endsWith("z.txt")) {
                parser.setVectorType(VectorParser.VectorType.Z);
            }
            try {
                parser.parse(file);
            } catch (IOException ex) {
                m.showError(ex);
                System.exit(1);
            }
        }

        try {

            W wInst = W.getInstace();

            for (Imap imap : MapFactory.getMaps()) {
                imap.map(wInst.getData());
                wInst.setData(imap.getData());
                imap.write(imap.getPrintedName());//преобразованые  данные
            }

        } catch (IOException ex) {
            m.showError(ex);
            System.exit(1);
        }
    }

    public void showError(Exception e) {

        System.out.println("Sorry, some error occurs:");
        System.out.println(e.getMessage());

    }

    public void showVersion() {
        System.out.println("seisus version: fn-v0.02");
    }

    public void showUsage() {
        System.out.println("Run command 'seisus'");
        System.out.println("\tin the directory that should containt 3 text files 'direction vector vibrations'");
        System.out.println("\twith names ending in 'x', 'y' or 'z' (.txt)");
        System.out.println("\tand contains 4 data column, where:");
        System.out.println("\t\t1 column - frequency");
        System.out.println("\t\t2 - acceleration factor 0.5");
        System.out.println("\t\t3 - acceleration factor 0.2");
        System.out.println("\t\t4 - acceleration factor 0.1");
        System.out.println("\t\tresult is placed in the file w.txt");
        System.out.println("See also the presence of the file errors.txt");
        System.out.println("Using  mappers:");
        int i = 1;
        for (Imap imap : MapFactory.getMaps()) {
            System.out.println("\t" + i + ". " + imap.getPrintedName() + "\t- " + imap.getDescription());
            i++;
        }

    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith("x.txt") || name.toLowerCase().endsWith("y.txt")
                || name.toLowerCase().endsWith("z.txt");
    }

}
