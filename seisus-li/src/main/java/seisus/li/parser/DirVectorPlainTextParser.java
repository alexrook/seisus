package seisus.li.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.IIOException;
import seisus.model.DirVector;

/**
 * @author moroz
 */
public class DirVectorPlainTextParser extends AbstractDirVectorParser {

    

    BufferedReader in;
    DirVector cur;

    String separator = "\t";

    public DirVectorPlainTextParser(Class clazz) {
        super(clazz);
    }

    public void setFieldSeparator(String separator) {
        this.separator = separator;
    }

    public void openDataSteram() {
        in = new BufferedReader(new InputStreamReader(getDataStream()));
    }

    @Override
    public boolean hasNextVector() throws IOException {
        String record = in.readLine();

        if (record != null) {
            cur = parseString(record);
        } else {
            cur = null;
        }
        return cur != null;
    }

    @Override
    public DirVector nextVector() throws IOException {
        return this.cur;
    }

    public DirVector parseString(String record) throws IOException {
        String[] fields = record.split(this.separator);
        if (fields.length < 4) {
            throw new IIOException("fileds count <4 in data record");
        }
        try {
            DirVector dirVector = newDirVector();
            return dirVector;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

}
